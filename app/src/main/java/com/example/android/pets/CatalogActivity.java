/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.android.pets.data.PetCursorAdapter;
import com.example.android.pets.data.PetsContract.FeedEntry;
import com.example.android.pets.data.PetsDatabaseHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    PetsDatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PetsDatabaseHelper(this, PetsDatabaseHelper.DB_NAME, null, PetsDatabaseHelper.DB_VERSION);

        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedEntry._ID,
                FeedEntry.COLUMN_PET_NAME,
                FeedEntry.COLUMN_PET_BREED,
                FeedEntry.COLUMN_PET_GENDER,
                FeedEntry.COLUMN_PET_WEIGHT
        };

        //Content Resolver is going into PetProvider class, where will gather data through Query method
        Cursor cursor = getContentResolver().query(FeedEntry.CONTENT_URI, projection, null, null, null);

        // Display the number of rows in the Cursor (which reflects the number of rows in the
        // pets table in the database).
        ListView displayView = findViewById(R.id.list_view_pet);

        PetCursorAdapter petAdapter = new PetCursorAdapter(this, cursor);

        displayView.setAdapter(petAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummyData();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertDummyData() {
        String nameDummy = "Toto";
        String breedDummy = "Terrier";
        int genderDummy = 1;
        int weightDummy = 7;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_PET_NAME, nameDummy);
        values.put(FeedEntry.COLUMN_PET_BREED, breedDummy);
        values.put(FeedEntry.COLUMN_PET_GENDER, genderDummy);
        values.put(FeedEntry.COLUMN_PET_WEIGHT, weightDummy);

        getContentResolver().insert(FeedEntry.CONTENT_URI, values);
    }
}

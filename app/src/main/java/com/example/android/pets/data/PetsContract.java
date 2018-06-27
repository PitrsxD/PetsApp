package com.example.android.pets.data;


import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;



public final class PetsContract {

    /**
     * URI for ContentProvider and database
     */
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";

    private PetsContract() {
    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        /*
        Possible values for gender
         */
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static final String INTEGER = " INTEGER";
        public static final String TEXT = " TEXT";
        public static final String PRIMARY_KEY = " PRIMARY KEY";
        public static final String NOT_NULL = " NOT NULL";
        public static final String AUTOINCREMENT = " AUTOINCREMENT";
        public static final String DEFAULT = " DEFAULT";
        public static final String COMMA = ",";

        /**
         * Crating table from constants
         */
        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE pets(" +
                _ID + INTEGER + PRIMARY_KEY + AUTOINCREMENT + COMMA +
                COLUMN_PET_NAME + TEXT + NOT_NULL + COMMA +
                COLUMN_PET_BREED + TEXT + COMMA +
                COLUMN_PET_GENDER + INTEGER + NOT_NULL + COMMA +
                COLUMN_PET_WEIGHT + INTEGER + DEFAULT + " 0" +
                ")";

        /**
         * Deleting table
         */
        public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

        /**
         * URI for ContentProvider and database
         */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_PETS;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + PATH_PETS;

    }
}

package com.example.it21012624supplementaryassessment.Database;

import android.provider.BaseColumns;
public final class UserProfile {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserProfile() {}

    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "USerInfo";
        public static final String COLUMN_1 = "userName";
        public static final String COLUMN_2 = "email";
        public static final String COLUMN_3 = "gender";
        public static final String COLUMN_4 = "password";
    }
}

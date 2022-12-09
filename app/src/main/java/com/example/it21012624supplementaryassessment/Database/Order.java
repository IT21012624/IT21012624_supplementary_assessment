package com.example.it21012624supplementaryassessment.Database;


import android.provider.BaseColumns;

public final class Order {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Order() {}

    /* Inner class that defines the table contents */
    public static class Orders implements BaseColumns {
        public static final String TABLE_NAME = "OrderInfo";
        public static final String COLUMN_1 = "OrNo";
        public static final String COLUMN_2 = "userName";
        public static final String COLUMN_3 = "ItemNo";
        public static final String COLUMN_4 = "Price";
    }
}


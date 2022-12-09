package com.example.it21012624supplementaryassessment.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class OrderDBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public OrderDBHandler(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Order.Orders.TABLE_NAME + " (" +
                    Order.Orders._ID + " INTEGER PRIMARY KEY," +
                    Order.Orders.COLUMN_1 + " TEXT," +
                    Order.Orders.COLUMN_2 + " TEXT," +
                    Order.Orders.COLUMN_3 + " TEXT," +
                    Order.Orders.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Order.Orders.TABLE_NAME;

    public void addInfo (String OrNo, String userName, String ItemNo, String Price){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Order.Orders.COLUMN_1, OrNo);
        values.put(Order.Orders.COLUMN_2, userName);
        values.put(Order.Orders.COLUMN_3, ItemNo);
        values.put(Order.Orders.COLUMN_4, Price);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Order.Orders.TABLE_NAME, null, values);

        return ;
    }

    public boolean updateInfo(String OrNo, String userName, String ItemNo, String Price){

        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Order.Orders.COLUMN_2, userName);
        values.put(Order.Orders.COLUMN_3, ItemNo);
        values.put(Order.Orders.COLUMN_4, Price);

        // Which row to update, based on the title
        String selection = Order.Orders.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { OrNo };


        int count = db.update(
                Order.Orders.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count>=1) {
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteInfo(String OrNo) {

        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = Order.Orders.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { OrNo };
        // Issue SQL statement.
        int deletedRows = db.delete(Order.Orders.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfo(){

        String OrNo = "LC001";
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Order.Orders.COLUMN_1,
                Order.Orders.COLUMN_2,
                Order.Orders.COLUMN_3,
                Order.Orders.COLUMN_4
        };

        // Filter results WHERE "title" = 'OrNo'
        String selection = Order.Orders.COLUMN_1+ " = ?";
        String[] selectionArgs = { OrNo };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Order.Orders.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                Order.Orders.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List OrNos = new ArrayList<>();
        while(cursor.moveToNext()) {
            String No = cursor.getString(cursor.getColumnIndexOrThrow(Order.Orders.COLUMN_1));
            OrNos.add(No);
        }
        cursor.close();
        return OrNos;
    }

    public List readAllInfo(String OrNo) {

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                Order.Orders.COLUMN_1,
                Order.Orders.COLUMN_2,
                Order.Orders.COLUMN_3,
                Order.Orders.COLUMN_4
        };

        // Filter results WHERE "title" = 'OrNo'
        String selection = Order.Orders.COLUMN_1+ "LIKE";
        String[] selectionArgs = { OrNo };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                Order.Orders.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                Order.Orders.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List OrderInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String orNo = cursor.getString(cursor.getColumnIndexOrThrow(Order.Orders.COLUMN_1));
            String user = cursor.getString(cursor.getColumnIndexOrThrow(Order.Orders.COLUMN_2));
            String itemNo = cursor.getString(cursor.getColumnIndexOrThrow(Order.Orders.COLUMN_3));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(Order.Orders.COLUMN_4));
            OrderInfo.add(orNo);//0
            OrderInfo.add(user);//1
            OrderInfo.add(itemNo);//2
            OrderInfo.add(price);//3
        }
        cursor.close();
        return OrderInfo;
    }

}


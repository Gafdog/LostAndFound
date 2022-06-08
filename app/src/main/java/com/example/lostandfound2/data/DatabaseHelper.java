package com.example.lostandfound2.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.lostandfound2.model.AllItems;
import com.example.lostandfound2.util.Util;
import com.example.lostandfound2.items;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

         String CREATE_ITEM_TABLE = "CREATE TABLE "
                + Util.TABLE_NAME + "("
                + Util.ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.NAME + " TEXT , "
                + Util.PHONE + " TEXT , "
                + Util.DESCRIPTION+ " TEXT , "
                + Util.DATE+ " TEXT , "
                + Util.LOCATION+ " TEXT , "
                 + Util.LOSTFOUND+ " TEXT , "
                 + Util.LATITUDE+ " DOUBLE , "
                + Util.LONGITUDE + " DOUBLE)";

        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_ITEM_TABLE = "DROP TABLE IF EXISTS "+ Util.TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_ITEM_TABLE,new String[]{Util.TABLE_NAME});

        onCreate(sqLiteDatabase);

    }

    public long insertItem (AllItems item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NAME, item.getItem_name());
        contentValues.put(Util.PHONE, item.getItem_Phone());
        contentValues.put(Util.DESCRIPTION, item.getItem_Description());
        contentValues.put(Util.DATE, item.getItem_Date());
        contentValues.put(Util.LOCATION, item.getItem_Location());
        contentValues.put(Util.LOSTFOUND, item.getItem_LostFound());
        contentValues.put(Util.LATITUDE, item.getLatitude());
        contentValues.put(Util.LONGITUDE, item.getLongitude());
        long newRowId = db.insert(Util.TABLE_NAME, null,contentValues);
        db.close();
        return newRowId;

    }

    public boolean fetchItem (String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.ITEM_ID}, Util.NAME + "=? and ", new String[]{name}, null,null,null);

        int numberOfRows= cursor.getCount();
        db.close();

        if (numberOfRows > 0)
            return true;
        else return false;
    }

    public ArrayList<items> GetItems(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<items> things = new ArrayList<items>();
        if (cursor.moveToFirst()){
            do{
                items thing = new items();
                //thing.setId(cursor.getInt(0));
                thing.setName(cursor.getString(1));
                thing.setPhone(cursor.getString(2));
                thing.setDescription(cursor.getString(3));
                thing.setWhen_Found(cursor.getString(4));
                thing.setLocation(cursor.getString(5));
                thing.setLostFound(cursor.getString(6));
                thing.setLatitude(cursor.getDouble(7));
                thing.setLongitude(cursor.getDouble(8));
                things.add(thing);
            } while (cursor.moveToNext());

            }
        cursor.close();
        return things;
    }

    public void DeleteItem(int item_Id){
        SQLiteDatabase db = this.getWritableDatabase();



        db.delete(Util.TABLE_NAME, Util.ITEM_ID+"=?",new String[]{String.valueOf(item_Id)});
        db.close();
    }

    public long totalItems ()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,Util.TABLE_NAME);
        db.close();

        return count;
    }


}

package com.pais.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pais.domain.house.HouseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SSL-D on 2016-08-04.
 */

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE house( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        "name TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insert(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into house (`name`) values ('"+name+"')");
        db.close();
    }
    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from house where name = "+name+"';");
        db.close();
    }
    public void update(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update house set name = "+name+"' where name = '"+name+"';");
        db.close();
    }
    public List<HouseItem> getHouseItems(){
        List<HouseItem> houseItems = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from house;",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            HouseItem item = new HouseItem();
            item.setId(id);
            item.setName(name);
            houseItems.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return houseItems;
    }
}

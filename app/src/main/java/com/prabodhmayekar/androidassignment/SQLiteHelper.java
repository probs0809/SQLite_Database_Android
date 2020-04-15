package com.prabodhmayekar.androidassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SQLiteHelper extends SQLiteOpenHelper {

    public final String FirstName = "FirstName" ;
    public final String LastName = "LastName" ;
    public final String Age = "Age";
    public final String RegNo = "RegNo";
    public final String Course = "Course";
    public final String Gender = "Gender";
    public final String Phone = "Phone";
    public final String Email = "Email";
    public final String TABLE_NAME = "registration";

    public SQLiteHelper(@Nullable Context context, @Nullable String name) {
        super(context, name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registration ( " +
                "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                FirstName + " VARCHAR(255)," +
                LastName + " VARCHAR(255)," +
                Age + " INTEGER ," +
                RegNo + " INTEGER," +
                Course + " VARCHAR(255)," +
                Gender + " VARCHAR(10)," +
                Phone + " VARCHAR(12)," +
                Email + " VARCHAR(255)); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ContentValues createContentValues(String FirstName,String LastName, int Age, int RegNo, String Course, String Gender,String Phone, String Email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.FirstName,FirstName);
        contentValues.put(this.LastName,LastName);
        contentValues.put(this.Age,Age);
        contentValues.put(this.RegNo,RegNo);
        contentValues.put(this.Course,Course);
        contentValues.put(this.Gender,Gender);
        contentValues.put(this.Phone,Phone);
        contentValues.put(this.Email,Email);
        return contentValues;
    }
    public List<Registrations> getAllRegistrations(){
        ArrayList<Registrations> rg_arrayList = new ArrayList<>();
        Cursor data = this.getReadableDatabase().rawQuery("SELECT * FROM registration;",null);
        data.moveToFirst();
        while (!data.isAfterLast()){
            rg_arrayList.add(new Registrations()
                    .setId(data.getInt(data.getColumnIndex("ID")))
                    .setFirstName(data.getString(data.getColumnIndex(FirstName)))
                    .setLastName(data.getString(data.getColumnIndex(LastName)))
                    .setAge(data.getInt(data.getColumnIndex(Age)))
                    .setRegNo(data.getInt(data.getColumnIndex(RegNo)))
                    .setCourse(data.getString(data.getColumnIndex(Course)))
                    .setGender(data.getString(data.getColumnIndex(Gender)))
                    .setPhone(data.getString(data.getColumnIndex(Phone)))
                    .setEmail(data.getString(data.getColumnIndex(Email)))
            );
            data.moveToNext();
        }
        data.close();
        return rg_arrayList;
    }

    public Registrations getRegistration(int id) throws Exception{
        Cursor data = getData.apply(id);
        data.moveToFirst();
        return new Registrations()
                .setId(data.getInt(data.getColumnIndex("ID")))
                .setFirstName(data.getString(data.getColumnIndex(FirstName)))
                .setLastName(data.getString(data.getColumnIndex(LastName)))
                .setAge(data.getInt(data.getColumnIndex(Age)))
                .setRegNo(data.getInt(data.getColumnIndex(RegNo)))
                .setCourse(data.getString(data.getColumnIndex(Course)))
                .setGender(data.getString(data.getColumnIndex(Gender)))
                .setPhone(data.getString(data.getColumnIndex(Phone)))
                .setEmail(data.getString(data.getColumnIndex(Email)));
    }

    public Supplier<Integer> numberOfRows = () -> (int)DatabaseUtils.queryNumEntries(this.getReadableDatabase(),TABLE_NAME);
    public Function<Integer, Cursor> getData = id -> this.getReadableDatabase().rawQuery("SELECT * FROM registration WHERE ID ="+ id +";",null);
    public Consumer<ContentValues> insert = contentValues -> this.getWritableDatabase().insert(TABLE_NAME,null,contentValues);
    public BiConsumer<Integer,ContentValues> update = (id,cv) -> this.getWritableDatabase().update(TABLE_NAME,cv,"ID = ?",new String[]{Integer.toString(id)});
    public Consumer<Integer> delete = (id) -> this.getWritableDatabase().delete(TABLE_NAME,"id = ?",new String[]{Integer.toString(id)});
}

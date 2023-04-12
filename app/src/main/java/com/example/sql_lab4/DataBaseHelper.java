package com.example.sql_lab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String STUDENT_TABLE = "Student_Table";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_AGE = "STUDENT_AGE";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "student.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createTableStaement = "CREATE TABLE " + STUDENT_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT ," + COLUMN_STUDENT_AGE + " INT)";
        sqLiteDatabase.execSQL(createTableStaement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
        public boolean addOne(StudentMod studentModel) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_STUDENT_NAME, studentModel.getName());
            cv.put(COLUMN_STUDENT_AGE, studentModel.getAge());

            long insert = sqLiteDatabase.insert(STUDENT_TABLE, null, cv);
            if (insert == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        public List<StudentMod> getEveryone(){

            List<StudentMod> returnList = new ArrayList<>();

            String queryString ="SELECT * FROM " + STUDENT_TABLE;
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);
            if(cursor.moveToFirst()){
                do{
                    int StudentID = cursor.getInt(0);
                    String StudentName = cursor.getString(1);
                    int StudentAge = cursor.getInt(2);

                    StudentMod newStudent = new StudentMod(StudentID,StudentName,StudentAge);
                    returnList.add(newStudent);
                }while (cursor.moveToNext());
            }
            else {

            }
            cursor.close();
            sqLiteDatabase.close();
            return returnList;
        }

}

package com.dictionaryassembly.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Note_Manager";

    private static final String TABLE_ASSEMBLY = "Assembly";
    private static final String TABLE_HISTORY = "History";

    private static final String COLUMN_ASSEMBLY_ID ="Assembly_Id";
    private static final String COLUMN_ASSEMBLY_TITLE ="Assembly_Title";
    private static final String COLUMN_ASSEMBLY_CONTENT = "Assembly_Content";
    private static final String COLUMN_ASSEMBLY_DESCRIPTION = "Assembly_Description";
    private static final String COLUMN_ASSEMBLY_IMAGELINK = "Assembly_ImageLink";
    private static final String COLUMN_ASSEMBLY_ACTIVE = "Assembly_Active";
    private static final String COLUMN_ASSEMBLY_TYPE = "Assembly_Type";
    private static final String COLUMN_ASSEMBLY_TYPEINTERRUPT = "Assembly_TypeInterrupt";

    private static final String COLUMN_HISTORY_ID ="History_Id";
    private static final String COLUMN_HISTORY_ASSEMBLY_ID ="History_Assembly_Id";
    private static final String COLUMN_HISTORY_TITLE ="History_Title";
    private static final String COLUMN_HISTORY_CONTENT = "History_Content";
    private static final String COLUMN_HISTORY_TYPE = "History_Type";
    private static final String COLUMN_HISTORY_TYPEINTERRUPT = "History_TypeInterrupt";
    private static final String COLUMN_HISTORY_DATE = "History_Date";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scriptAssembly = "CREATE TABLE " + TABLE_ASSEMBLY + "("
                + COLUMN_ASSEMBLY_ID + " TEXT PRIMARY KEY,"
                + COLUMN_ASSEMBLY_TITLE + " TEXT,"
                + COLUMN_ASSEMBLY_CONTENT + " TEXT,"
                + COLUMN_ASSEMBLY_DESCRIPTION + " TEXT,"
                + COLUMN_ASSEMBLY_IMAGELINK + " TEXT,"
                + COLUMN_ASSEMBLY_ACTIVE + " INTEGER,"
                + COLUMN_ASSEMBLY_TYPE + " TEXT,"
                + COLUMN_ASSEMBLY_TYPEINTERRUPT + " TEXT"
                + ")";

        String scriptHistory = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_HISTORY_ID + " TEXT PRIMARY KEY,"
                + COLUMN_HISTORY_ASSEMBLY_ID + " TEXT,"
                + COLUMN_HISTORY_TITLE + " TEXT,"
                + COLUMN_HISTORY_CONTENT + " TEXT,"
                + COLUMN_HISTORY_TYPE + " TEXT,"
                + COLUMN_HISTORY_TYPEINTERRUPT + " TEXT,"
                + COLUMN_HISTORY_DATE + " INTEGER"
                + ")";
        // Execute Script.
        sqLiteDatabase.execSQL(scriptAssembly);
        sqLiteDatabase.execSQL(scriptHistory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSEMBLY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }

//
//    Thao tác với bảng Assembly
//

    public List<AssemblyForm> getAllAssembly() {

        List<AssemblyForm> assemblyFormList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ASSEMBLY+" WHERE "+COLUMN_ASSEMBLY_ACTIVE+" = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AssemblyForm assemblyForm = new AssemblyForm();
                assemblyForm.setID(cursor.getString(0));
                assemblyForm.setTitle(cursor.getString(1));
                assemblyForm.setContent(cursor.getString(2));
                assemblyForm.setDescription(cursor.getString(3));
                assemblyForm.setImageLink(cursor.getString(4));
                assemblyForm.setActive(cursor.getInt(5)==1);
                assemblyForm.setType(EnumType.valueOf(cursor.getString(6)));
                assemblyForm.setTypeInterrupt(cursor.getString(7));
                // Adding note to list
                assemblyFormList.add(assemblyForm);
            } while (cursor.moveToNext());
        }

        return assemblyFormList;
    }

    public List<AssemblyForm> getByTypeAssembly(EnumType enumType) {

        List<AssemblyForm> assemblyFormList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_ASSEMBLY+" WHERE "+COLUMN_ASSEMBLY_TYPE+" = '"+enumType.name()+"' AND "+COLUMN_ASSEMBLY_ACTIVE+" = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AssemblyForm assemblyForm = new AssemblyForm();
                assemblyForm.setID(cursor.getString(0));
                assemblyForm.setTitle(cursor.getString(1));
                assemblyForm.setContent(cursor.getString(2));
                assemblyForm.setDescription(cursor.getString(3));
                assemblyForm.setImageLink(cursor.getString(4));
                assemblyForm.setActive(cursor.getInt(5)==1);
                assemblyForm.setType(EnumType.valueOf(cursor.getString(6)));
                assemblyForm.setTypeInterrupt(cursor.getString(7));
                // Adding note to list
                assemblyFormList.add(assemblyForm);
            } while (cursor.moveToNext());
        }

        return assemblyFormList;
    }

    public AssemblyForm getByIDTypeAssembly(EnumType enumType, String id) {

        AssemblyForm assemblyForm = null;
        String selectQuery = "SELECT  * FROM " + TABLE_ASSEMBLY+" WHERE "+COLUMN_ASSEMBLY_TYPE+" = '"+enumType.name()+"' AND "+COLUMN_ASSEMBLY_ID+" = '"+id+"' AND "+COLUMN_ASSEMBLY_ACTIVE+" = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                assemblyForm = new AssemblyForm();
                assemblyForm.setID(cursor.getString(0));
                assemblyForm.setTitle(cursor.getString(1));
                assemblyForm.setContent(cursor.getString(2));
                assemblyForm.setDescription(cursor.getString(3));
                assemblyForm.setImageLink(cursor.getString(4));
                assemblyForm.setActive(cursor.getInt(5)==1);
                assemblyForm.setType(EnumType.valueOf(cursor.getString(6)));
                assemblyForm.setTypeInterrupt(cursor.getString(7));
            } while (cursor.moveToNext());
        }

        return assemblyForm;
    }

    public void addAssembly(AssemblyForm assemblyForm) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ASSEMBLY_ID, assemblyForm.getID());
        values.put(COLUMN_ASSEMBLY_TITLE, assemblyForm.getTitle());
        values.put(COLUMN_ASSEMBLY_CONTENT, assemblyForm.getContent());
        values.put(COLUMN_ASSEMBLY_DESCRIPTION, assemblyForm.getDescription());
        values.put(COLUMN_ASSEMBLY_IMAGELINK, assemblyForm.getImageLink());
        values.put(COLUMN_ASSEMBLY_TYPE, assemblyForm.getType().name());
        values.put(COLUMN_ASSEMBLY_TYPEINTERRUPT, assemblyForm.getTypeInterrupt());
        values.put(COLUMN_ASSEMBLY_ACTIVE, assemblyForm.isActive()?1:0);

        sqLiteDatabase.insert(TABLE_ASSEMBLY, null, values);

        sqLiteDatabase.close();
    }

    public int updateAssembly(AssemblyForm assemblyForm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ASSEMBLY_TITLE, assemblyForm.getTitle());
        values.put(COLUMN_ASSEMBLY_CONTENT, assemblyForm.getContent());
        values.put(COLUMN_ASSEMBLY_DESCRIPTION, assemblyForm.getDescription());
        values.put(COLUMN_ASSEMBLY_IMAGELINK, assemblyForm.getImageLink());
        values.put(COLUMN_ASSEMBLY_TYPE, assemblyForm.getType().name());
        values.put(COLUMN_ASSEMBLY_TYPEINTERRUPT, assemblyForm.getTypeInterrupt());
        values.put(COLUMN_ASSEMBLY_ACTIVE, assemblyForm.isActive()?1:0);

        // updating row
        return db.update(TABLE_ASSEMBLY, values, COLUMN_ASSEMBLY_ID + " = ?",
                new String[]{String.valueOf(assemblyForm.getID())});
    }

    public void deleteAssembly(AssemblyForm assemblyForm) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ASSEMBLY, COLUMN_ASSEMBLY_ID + " = ?",
                new String[] { String.valueOf(assemblyForm.getID()) });
        db.close();
    }

    public void deleteAllAssembly(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ASSEMBLY );
    }

    public void deleteMacroAssembly(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ASSEMBLY +" WHERE "+COLUMN_ASSEMBLY_TYPE+" = '"+EnumType.MACRO.name()+"'");
    }

    public boolean checkAssembly(String title, EnumType enumType){
        String selectQuery = "SELECT  * FROM " + TABLE_ASSEMBLY+" WHERE UPPER("+COLUMN_ASSEMBLY_TITLE+") = '"+title+"' AND "+COLUMN_ASSEMBLY_TYPE+" = '"+enumType.name()+"' AND "+COLUMN_ASSEMBLY_ACTIVE+" = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.moveToFirst();
    }

//
//    Thao tác với bảng History
//
    public List<History> getAllHistory() {

        List<History> historyList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setHistoryID(cursor.getString(0));
                history.setID(cursor.getString(1));
                history.setTitle(cursor.getString(2));
                history.setContent(cursor.getString(3));
                history.setType(EnumType.valueOf(cursor.getString(4)));
                history.setTypeInterrupt(cursor.getString(5));
                history.setDate(new Date(cursor.getLong(6)));
                // Adding note to list
                historyList.add(history);
            } while (cursor.moveToNext());
        }

        return historyList;
    }

    public void addHistory(History history) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORY_ID, history.getHistoryID());
        values.put(COLUMN_HISTORY_ASSEMBLY_ID, history.getID());
        values.put(COLUMN_HISTORY_TITLE, history.getTitle());
        values.put(COLUMN_HISTORY_CONTENT, history.getContent());
        values.put(COLUMN_HISTORY_TYPE, history.getType().name());
        values.put(COLUMN_HISTORY_TYPEINTERRUPT, history.getTypeInterrupt());
        values.put(COLUMN_HISTORY_DATE, history.getDate().getTime());

        sqLiteDatabase.insert(TABLE_HISTORY, null, values);

        sqLiteDatabase.close();
    }

    public int updateHistory(History history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HISTORY_ASSEMBLY_ID, history.getID());
        values.put(COLUMN_HISTORY_TITLE, history.getTitle());
        values.put(COLUMN_HISTORY_CONTENT, history.getContent());
        values.put(COLUMN_HISTORY_TYPE, history.getType().name());
        values.put(COLUMN_HISTORY_TYPEINTERRUPT, history.getTypeInterrupt());
        values.put(COLUMN_HISTORY_DATE, history.getDate().getTime());

        // updating row
        return db.update(TABLE_HISTORY, values, COLUMN_HISTORY_ID + " = ?",
                new String[]{String.valueOf(history.getHistoryID())});
    }

    public void deleteHistory(History history) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, COLUMN_HISTORY_ID + " = ?",
                new String[] { String.valueOf(history.getHistoryID()) });
        db.close();
    }

    public void deleteAllHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HISTORY);
    }
}

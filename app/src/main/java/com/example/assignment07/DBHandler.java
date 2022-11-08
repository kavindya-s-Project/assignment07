package com.example.assignment07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHandler extends SQLiteOpenHelper {

    private Context context;
    private static final int VERSION = 1;
    private static final String DB_NAME = "library_System";
    private static final String BOOK_TABLE_NAME = "books";
    private static final String PUBLISHER_TABLE_NAME = "publisher";
    private static final String BRANCH_TABLE_NAME = "branch";
    private static final String MEMBER_TABLE_NAME = "member";

    private static final String BOOK_LENDING_TABLE_NAME = "lending_dates";

//members' login table
    private static final String LOGIN_TABLE = "login";

// define book table
    private static final String ID = "id", TITLE = "title", PUBLISHER = "publisher",
        AUTHOR = "bookAuthor", BRANCH = "branch";

//define publisher table
    private static final String NAME = "name", PUBLISHER_ADDRESS = "address",
        PUBLISHER_PHONE = "phone";

//define branch table
    private static final String BRANCH_ID = "id", BRANCH_NAME = "branch_name",
        BRANCH_ADDRESS = "address";

//    define member table
    private static final String CARD_NO = "card_no", MEMBER_NAME = "name", MEMBER_ADDRESS = "address",
            MEMBER_PHONE = "phone", UNPAID_DUE = "unpaid_dues";

//define login table
    private static final String USERNAME = "userName", PASSWORD = "password";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//     create book table
        String BOOK_TABLE_CREATE_QUERY = "CREATE TABLE "+BOOK_TABLE_NAME+
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE +" TEXT,"
                +PUBLISHER+" TEXT,"
                +AUTHOR+" TEXT,"
                +BRANCH+" TEXT"
                +");";
        db.execSQL(BOOK_TABLE_CREATE_QUERY);

//        create publisher table
        String PUBLISHER_TABLE_CREATE_QUERY = "CREATE TABLE "+PUBLISHER_TABLE_NAME+
                "("
                +NAME+ " TEXT,"
                +PUBLISHER_ADDRESS +"TEXT,"
                +PUBLISHER_PHONE+" TEXT"
                +");";
        db.execSQL(PUBLISHER_TABLE_CREATE_QUERY);

//        create branch table
        String BRANCH_TABLE_CREATE_QUERY = "CREATE TABLE "+BRANCH_TABLE_NAME+
                "("
                +BRANCH_ID+" TEXT,"
                +BRANCH_NAME+ " TEXT,"
                +BRANCH_ADDRESS +" TEXT"
                +");";
        db.execSQL(BRANCH_TABLE_CREATE_QUERY);

//        create member table
        String MEMBER_TABLE_CREATE_QUERY = "CREATE TABLE "+MEMBER_TABLE_NAME+
                "("
                +CARD_NO+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +MEMBER_NAME+ " TEXT,"
                +MEMBER_ADDRESS +" TEXT,"
                +MEMBER_PHONE+" TEXT,"
                +UNPAID_DUE+" TEXT"
                +");";
        db.execSQL(MEMBER_TABLE_CREATE_QUERY);


//create member login table
        String LOGIN_TABLE_CREATE_QUERY = "CREATE TABLE "+LOGIN_TABLE+
                "("
                +USERNAME + " TEXT PRIMARY KEY,"
                +PASSWORD + " TEXT"
                +");";
        db.execSQL(LOGIN_TABLE_CREATE_QUERY);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        String DROP_BOOK_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOK_TABLE_NAME;
        String DROP_PUBLISHER_TABLE_QUERY = "DROP TABLE IF EXISTS "+ PUBLISHER_TABLE_NAME;
        String DROP_BRANCH_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BRANCH_TABLE_NAME;
        String DROP_MEMBER_TABLE_QUERY = "DROP TABLE IF EXISTS "+ MEMBER_TABLE_NAME;
        String DROP_BOOK_LEND_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOK_LENDING_TABLE_NAME;
        String DROP_LOGIN_TABLE_QUERY = "DROP TABLE IF EXISTS " + LOGIN_TABLE;

        // Create tables again
        db.execSQL(DROP_BOOK_TABLE_QUERY);
        db.execSQL(DROP_PUBLISHER_TABLE_QUERY);
        db.execSQL(DROP_BRANCH_TABLE_QUERY);
        db.execSQL(DROP_MEMBER_TABLE_QUERY);
        db.execSQL(DROP_BOOK_LEND_TABLE_QUERY);
        db.execSQL(DROP_LOGIN_TABLE_QUERY);

        onCreate(db);
    }

    /* -------------------------------------------------------- */
    /* BOOK DB HANDLER METHODS */
    public void addBook(String title, String publisher, String author, String branch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TITLE, title);
        cv.put(PUBLISHER, publisher);
        cv.put(AUTHOR, author);
        cv.put(BRANCH, branch);
        long result = db.insert(BOOK_TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public int countBooks(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ BOOK_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + BOOK_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String title, String publisher, String author, String branch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(PUBLISHER, publisher);
        cv.put(AUTHOR, author);
        cv.put(BRANCH, branch);

        long result = db.update(BOOK_TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(BOOK_TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + BOOK_TABLE_NAME);
    }

    /* -------------------------------------------------------- */
    /* LOGIN DB HANDLER METHODS */

    // username & password handling
    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert(LOGIN_TABLE, null,contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //check username
    public Boolean checkUsername (String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from login where username = ?",
                new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //check username and password
    public Boolean checkUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from login where username = ? and password = ?",
                new String[]{username,password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    //check admin username and password
    public Boolean checkAdminUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select login where username = 'Admin' and password = '1234'",
                new String[]{username,password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean insertuserdata(String name, String address, String Cnum, String unpaid )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEMBER_NAME, name);
        contentValues.put(MEMBER_ADDRESS, address);
        contentValues.put(MEMBER_PHONE, Cnum);
        contentValues.put(UNPAID_DUE, unpaid);
        long result = DB.insert(MEMBER_TABLE_NAME, null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }

    /* -------------------------------------------------------- */
    /* MEMBER DB HANDLER METHODS */
    public Cursor getMemberData() {
                String query = "SELECT * FROM " + MEMBER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateMember(String row_id, String name, String address, String phone, String unpaid_dues){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MEMBER_NAME, name);
        cv.put(MEMBER_ADDRESS, address);
        cv.put(MEMBER_PHONE, phone);
        cv.put(UNPAID_DUE, unpaid_dues);

        long result = db.update(MEMBER_TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMemberRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(MEMBER_TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


}

package com.example.assignment07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment07.models.BookModel;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "library_System";
    private static final String BOOK_TABLE_NAME = "books";
    private static final String PUBLISHER_TABLE_NAME = "publisher";
    private static final String BRANCH_TABLE_NAME = "branch";
    private static final String MEMBER_TABLE_NAME = "member";
    private static final String AUTHOR_TABLE_NAME = "author";
    private static final String BOOK_COPY_TABLE_NAME = "copies_of_books";
    private static final String BOOK_LENDING_TABLE_NAME = "lending_dates";
//members' login table
    private static final String LOGIN_TABLE = "login";

// define book table
    private static final String TITLE = "title", ID = "id", PUBLISHER = "publisher";
//define publisher table
    private static final String NAME = "name", PUBLISHER_ADDRESS = "address",
        PUBLISHER_PHONE = "phone";
//define branch table
    private static final String BRANCH_ID = "id", BRANCH_NAME = "branch_name",
        BRANCH_ADDRESS = "address";
//    define member table
    private static final String CARD_NO = "card_no", MEMBER_NAME = "name", MEMBER_ADDRESS = "address",
            MEMBER_PHONE = "phone", UNPAID_DUE = "unpaid_dues";
//    define author table
    private static final String BOOK_ID = "book_id", AUTHOR_NAME = "author_name";
//    define copy book table
    private static final String COPY_ID = "book_id", COPY_BRANCH = "branch_id", ACCESS_NO = "access_no";
//    define lending table
    private static final String LEND_ACCESS_NUM = "access_no", LEND_BRANCH_ID = "branch_id", LEND_CARD_NO= "card_no",
            OUT_DATE = "out_date", DUE_DATE = "due_date", RETURNED_DATE = "returned_date";
//define login table
    private static final String USERNAME = "userName", PASSWORD = "password";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//     create book table
        String BOOK_TABLE_CREATE_QUERY = "CREATE TABLE "+BOOK_TABLE_NAME+
                "("
                +TITLE +" TEXT,"
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +PUBLISHER+" TEXT"
                +")";
        db.execSQL(BOOK_TABLE_CREATE_QUERY);

//        create publisher table
        String PUBLISHER_TABLE_CREATE_QUERY = "CREATE TABLE "+PUBLISHER_TABLE_NAME+
                "("
                +NAME+ " TEXT PRIMARY KEY,"
                +PUBLISHER_ADDRESS +"TEXT,"
                +PUBLISHER_PHONE+" TEXT"
                +")";
        db.execSQL(PUBLISHER_TABLE_CREATE_QUERY);

//        create branch table
        String BRANCH_TABLE_CREATE_QUERY = "CREATE TABLE "+BRANCH_TABLE_NAME+
                "("
                +BRANCH_ID+" TEXT PRIMARY KEY,"
                +BRANCH_NAME+ " TEXT,"
                +BRANCH_ADDRESS +" TEXT"
                +")";
        db.execSQL(BRANCH_TABLE_CREATE_QUERY);

//        create member table
        String MEMBER_TABLE_CREATE_QUERY = "CREATE TABLE "+MEMBER_TABLE_NAME+
                "("
                +CARD_NO+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +MEMBER_NAME+ " TEXT,"
                +MEMBER_ADDRESS +" TEXT,"
                +MEMBER_PHONE+" TEXT,"
                +UNPAID_DUE+" TEXT"
                +")";
        db.execSQL(MEMBER_TABLE_CREATE_QUERY);

//        create author table
        String AUTHOR_TABLE_CREATE_QUERY = "CREATE TABLE "+AUTHOR_TABLE_NAME+
                "("
                +BOOK_ID+" TEXT PRIMARY KEY,"
                +AUTHOR_NAME+" TEXT,"
                +"FOREIGN KEY "+ "(" +BOOK_ID+ ")"
                +"REFERENCES "
                +BOOK_TABLE_NAME+"(" +ID+ ")"
                +")";
        db.execSQL(AUTHOR_TABLE_CREATE_QUERY);

//        create copy books table
        String BOOK_COPY_TABLE_CREATE_QUERY = "CREATE TABLE "+BOOK_COPY_TABLE_NAME+
                "("
                +COPY_ID+" TEXT,"
                +COPY_BRANCH+" TEXT PRIMARY KEY,"
                +ACCESS_NO+" TEXT,"
                +"FOREIGN KEY"+"(" +COPY_BRANCH+ ")"
                +" REFERENCES "
                +BRANCH_TABLE_NAME+"(" +BRANCH_ID+ ")"
                +")";
        db.execSQL(BOOK_COPY_TABLE_CREATE_QUERY);

        //        create lending books table
        String BOOK_LENDING_TABLE_CREATE_QUERY = "CREATE TABLE "+BOOK_LENDING_TABLE_NAME+
                "("
                +LEND_ACCESS_NUM+" TEXT,"
                +LEND_BRANCH_ID+" TEXT,"
                +LEND_CARD_NO+" TEXT PRIMARY KEY,"
                +OUT_DATE+" TEXT,"
                +DUE_DATE+" TEXT,"
                +RETURNED_DATE+" TEXT,"
                +"FOREIGN KEY "+ "(" +CARD_NO+ ")"
                +"REFERENCES "
                +MEMBER_TABLE_NAME+"(" +CARD_NO+ ")"
                +")";
        db.execSQL(BOOK_LENDING_TABLE_CREATE_QUERY);

//create member login table
        String LOGIN_TABLE_CREATE_QUERY = "CREATE TABLE "+LOGIN_TABLE+
                "("
                +USERNAME + " TEXT PRIMARY KEY,"
                +PASSWORD + " TEXT"
                +")";
        db.execSQL(LOGIN_TABLE_CREATE_QUERY);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        String DROP_BOOK_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOK_TABLE_NAME;
        String DROP_PUBLISHER_TABLE_QUERY = "DROP TABLE IF EXISTS "+ PUBLISHER_TABLE_NAME;
        String DROP_BRANCH_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BRANCH_TABLE_NAME;
        String DROP_MEMBER_TABLE_QUERY = "DROP TABLE IF EXISTS "+ MEMBER_TABLE_NAME;
        String DROP_AUTHOR_TABLE_QUERY = "DROP TABLE IF EXISTS "+ AUTHOR_TABLE_NAME;
        String DROP_COPY_BOOK_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOK_COPY_TABLE_NAME;
        String DROP_BOOK_LEND_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOK_LENDING_TABLE_NAME;
        String DROP_LOGIN_TABLE_QUERY = "DROP TABLE IF EXISTS " + LOGIN_TABLE;

        // Create tables again
        db.execSQL(DROP_BOOK_TABLE_QUERY);
        db.execSQL(DROP_PUBLISHER_TABLE_QUERY);
        db.execSQL(DROP_BRANCH_TABLE_QUERY);
        db.execSQL(DROP_MEMBER_TABLE_QUERY);
        db.execSQL(DROP_AUTHOR_TABLE_QUERY);
        db.execSQL(DROP_COPY_BOOK_TABLE_QUERY);
        db.execSQL(DROP_BOOK_LEND_TABLE_QUERY);
        db.execSQL(DROP_LOGIN_TABLE_QUERY);

        onCreate(db);
    }

// add a single book
    public void addBook(BookModel bookModel) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,bookModel.getTitle());
        contentValues.put(ID,bookModel.getId());
        contentValues.put(PUBLISHER,bookModel.getPublisher());

        //save to table
        sqLiteDatabase.insert(BOOK_TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    //count books table records
    public int countBooks() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ BOOK_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Get all todos into a list
    public List<BookModel> getAllBooks(){

        List<BookModel> bookModels = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+BOOK_TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new book object
                BookModel bookModel = new BookModel();
                // mmgby6hh
                bookModel.setTitle(cursor.getString(0));
                bookModel.setId(cursor.getInt(1));
                bookModel.setPublisher(cursor.getString(2));

                //toDos [obj,objs,asas,asa]
                bookModels.add(bookModel);
            }while (cursor.moveToNext());
        }
        return bookModels;
    }

    // Delete item
    public void deleteBook(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BOOK_TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    // Get a single book
    public BookModel getSingleBook(int id){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(BOOK_TABLE_NAME,new String[]{TITLE,ID,PUBLISHER},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        BookModel bookModel;
        if(cursor != null){
            cursor.moveToFirst();
            bookModel = new BookModel(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
            return bookModel;
        }
        return null;
    }

    // Update a single book
    public int updateSingleBook(BookModel bookModel){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE,bookModel.getTitle());
        contentValues.put(ID, bookModel.getId());
        contentValues.put(PUBLISHER,bookModel.getPublisher());

        int status = db.update(BOOK_TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(bookModel.getId())});

        db.close();
        return status;
    }


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
    public Cursor getMemberData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from member", null);
        return cursor;
    }


}

package babybook.com.v1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public abstract class MainDbAdapter {

    protected SQLiteDatabase db;
    protected final Context context;
    protected DatabaseHelper dbHelper;

    protected final String DATABASE_NAME = "BABYBOOK"; //removed static//before it had static means this string variable is a constant plus it is does not need an object instance to be accessed.
    protected final int DATABASE_VERSION = 1;

    private static final String CREATE_BABY_BOOK= BabyBookDbAdapter.CREATE_BABY_BOOK;

    protected MainDbAdapter(Context _context){

        this.context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME,null, DATABASE_VERSION);


    }


    protected MainDbAdapter open() throws SQLException{


        try{
            db= dbHelper.getWritableDatabase();
        }catch(SQLException ex){

            db= dbHelper.getReadableDatabase();
        }

        return this;
    }

    protected void close(){
        db.close();

    }

    protected void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BABY_BOOK);


    }

    protected abstract void insertIntoBook(SQLiteDatabase db, String date, String title, String imagePath, String story);

       // BabyBookDbAdapter.insertIntoBook(db, date, title,imagePath, story);



    protected  Cursor readBook() {

        return BabyBookDbAdapter.readBook(db);
    }

    protected void deleteTable(){

        db.delete(BabyBookDbAdapter.TABLE_NAME, null, null);
    }


    protected static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version) {

            super(context, name, factory, version);

        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_BABY_BOOK);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w("Main DBAdapter" , "Upgrading from version " +oldVersion+ " to " +newVersion+ " which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " +BabyBookDbAdapter.TABLE_NAME);
            onCreate(db);
        }
    }





}

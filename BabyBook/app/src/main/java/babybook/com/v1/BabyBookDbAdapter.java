package babybook.com.v1;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




public class BabyBookDbAdapter extends MainDbAdapter {



    protected static final String KEY_ID = "_id";
    protected static final int INDEX_ID = 0;

    protected static final String KEY_DATE = "_date";
    protected static final int INDEX_DATE = 1;

    protected static final String KEY_TITLE = "_title";
    protected static final int INDEX_TITLE = 2;

    protected static final String KEY_IMAGE_FILEPATH = "_image";
    protected static final int INDEX_IMAGE_FILEPATH = 3;

    protected static final String KEY_STORY = "_story";
    protected static final int INDEX_STORY = 4;

    protected static final String TABLE_NAME = "babybook";

    protected static final String CREATE_BABY_BOOK = "CREATE TABLE " + TABLE_NAME +
            " (" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
            + " text not null, " + KEY_DATE + " text not null, " + KEY_IMAGE_FILEPATH
            + " text not null, " + KEY_STORY + " text not null);";


    public BabyBookDbAdapter (Context _context){

        super(_context);
    }

    protected void insertIntoBook(SQLiteDatabase db, String date, String title,String imagePath, String story){

        ContentValues newValues = new ContentValues();
        newValues.put(KEY_DATE, date);
        newValues.put(KEY_TITLE, title);
        newValues.put(KEY_IMAGE_FILEPATH,imagePath); //hard code
        newValues.put(KEY_STORY, story);

        db.insert(TABLE_NAME, null, newValues);
    }


    protected static Cursor readBook(SQLiteDatabase db){

        String[] resultSet = new String[] {KEY_ID,KEY_DATE,KEY_TITLE,KEY_IMAGE_FILEPATH,KEY_STORY};

        Cursor result = db.query(true, TABLE_NAME, resultSet, null, null, null,null, null,null);
        return result;
    }

    protected Cursor readStoryById(SQLiteDatabase db, int id){

        String where = KEY_ID + "=" + id;
        String[] resultSet = new String[] {KEY_DATE,KEY_TITLE, KEY_IMAGE_FILEPATH, KEY_STORY};
        Cursor result = db.query(TABLE_NAME, resultSet, where, null, null, null, null, null);

        return result;
    }

}

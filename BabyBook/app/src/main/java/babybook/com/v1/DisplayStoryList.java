package babybook.com.v1;

import java.util.ArrayList;


import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class DisplayStoryList extends Activity {

    private Story babyStory;
    private int id = 0;
    private String date, title, imagePath, description = null;
    private ListView babyStoryList;

   // public static MainDbAdapter dbBabyBook;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storylist);

      //  dbBabyBook = new BabyBookDbAdapter(this);
       HomePage.dbBabyBook.open();

        StorySingleton.storyObj = null;

        Cursor allStories = HomePage.dbBabyBook.readBook();

        if (allStories.moveToFirst() && allStories.getCount() != 0) {

            id = allStories.getInt(BabyBookDbAdapter.INDEX_ID);
            date = allStories.getString(BabyBookDbAdapter.INDEX_DATE);
            title = allStories.getString(BabyBookDbAdapter.INDEX_TITLE);
            imagePath = allStories.getString(BabyBookDbAdapter.INDEX_IMAGE_FILEPATH);
            description = allStories.getString(BabyBookDbAdapter.INDEX_STORY);

            babyStory = new Story(id, date, title, imagePath, description);

            StorySingleton.getInstance().addStory(babyStory);

            while (allStories.moveToNext()) {

                id = allStories.getInt(BabyBookDbAdapter.INDEX_ID);
                date = allStories.getString(BabyBookDbAdapter.INDEX_DATE);
                title = allStories.getString(BabyBookDbAdapter.INDEX_TITLE);
                imagePath = allStories.getString(BabyBookDbAdapter.INDEX_IMAGE_FILEPATH);
                description = allStories.getString(BabyBookDbAdapter.INDEX_STORY);

                babyStory = new Story(id, date, title, imagePath, description);

                StorySingleton.getInstance().addStory(babyStory);

            }
        }
//doing changes here
        babyStoryList = (ListView) findViewById(R.id.storylist);
        ArrayList<String> babyStoryArray = new ArrayList<String>();


        int size = StorySingleton.getInstance().getNumberOfStories();

        for (int i = 0; i < size; i++) {

            babyStoryArray.add(StorySingleton.getInstance().storyList.get(i).getTitle());

        }

       // StoryAdapter aa = new StoryAdapter(this,babyStoryArray);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, babyStoryArray);
        babyStoryList.setAdapter(aa);
        HomePage.dbBabyBook.close();

      /*  Button btnWrite = (Button)findViewById(R.id.btn_addStory);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent writeIntent = new Intent(DisplayStoryList.this, WritePage.class);
                startActivity(writeIntent);

            }
        });*/

       babyStoryList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent readIntent = new Intent(DisplayStoryList.this, ReadStory.class);
                readIntent.putExtra("index", (int) id);
                startActivity(readIntent);
            }
        });

    }
}
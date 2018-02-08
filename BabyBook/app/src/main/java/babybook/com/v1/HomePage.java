package babybook.com.v1;


import android.os.Bundle;
import android.app.Activity;
import android.content.*;
import android.widget.*;
import android.view.View.OnClickListener;
import android.view.View;

public class HomePage extends Activity {

    public static MainDbAdapter dbBabyBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        SharedPreferences settings = getSharedPreferences(Profile.USER_DATA,0);

        if (settings.getInt("Count", 0) == 0){

            Intent intent = new Intent(HomePage.this, Profile.class);
            startActivity(intent);
        }

        dbBabyBook = new BabyBookDbAdapter(this);
        dbBabyBook.open();

        




        String parentName = settings.getString("Parent_Name", "Mommy");
        String babyName = settings.getString("Baby_Name", "Baby");

        TextView tvWelcome = (TextView)findViewById(R.id.txt_Welcome);
        tvWelcome.setText("hey, " + parentName);

        TextView tvBabyName = (TextView)findViewById(R.id.txt_BabyName);
        tvBabyName.setText(babyName);


        Button btnWrite = (Button)findViewById(R.id.btn_addStory);
        btnWrite.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                Intent writeIntent = new Intent(HomePage.this, WritePage.class );
                startActivity(writeIntent);

            }
        });

        Button btnRead = (Button)findViewById(R.id.btn_readBook);
        btnRead.setOnClickListener(new OnClickListener(){
            public void onClick(View v){

                Intent readIntent = new Intent(HomePage.this, DisplayStoryList.class);
                startActivity(readIntent);
            }

        });

    }


}

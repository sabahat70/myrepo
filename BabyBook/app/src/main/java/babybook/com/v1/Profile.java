package babybook.com.v1;


import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.*;
import android.content.*;
import android.view.View.OnClickListener;



public class Profile extends Activity{

    public static final String USER_DATA = "User_Data";
    private String parentName, babyName, babyDOB;
    private int count = 0;
    private EditText etParentName, etBabyName, etBabyDOB;
    private Button btnStart, btnPickDate;
    public static String date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);



        //use the concept of shared preferences to save the initial data
        etParentName = (EditText)findViewById(R.id.et_parentname);
        etBabyName = (EditText)findViewById(R.id.et_babyname);
      //  etBabyDOB = (EditText)findViewById(R.id.et_dob); //create drop box
        count++;

        btnStart = (Button)findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new OnClickListener(){
                public void onClick(View v){

                    parentName = etParentName.getText().toString();
                    babyName = etBabyName.getText().toString();


                    Intent intentHomePage = new Intent(Profile.this, HomePage.class);
                    startActivity(intentHomePage);

                }
        });


    }



    @Override
    protected void onPause(){

        super.onPause();
        SharedPreferences settings = getSharedPreferences(USER_DATA,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Parent_Name", parentName);
        editor.putString("Baby_Name", babyName);
       // editor.putString("Baby_DOB", babyDOB);
        editor.putInt("Count", count);

   		editor.commit();
    }

}
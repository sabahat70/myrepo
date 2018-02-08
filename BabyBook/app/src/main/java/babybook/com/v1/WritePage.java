package babybook.com.v1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;


public class WritePage extends FragmentActivity {




    private static final int RESULT_IMAGE_LOAD_REQUEST_CODE = 1;
    private static final int RESULT_IMAGE_CAPTURE_REQUEST_CODE = 2; //http://developer.android.com/guide/components/intents-common.html#Camera
    private EditText etTitle, etStory;
    private TextView tvDate;
    private ImageView imgBaby;
    private Button btnChangeDate, btnUpload, btnTakePhoto, btnSave;
    private Uri imageUri;
    private File imageFile;
    private String filePath;


    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write);


        tvDate = (TextView) findViewById(R.id.tvDate);
        etTitle = (EditText) findViewById(R.id.et_Title);
        etStory = (EditText) findViewById(R.id.et_Story);
        imgBaby = (ImageView) findViewById(R.id.img_Baby);

        tvDate.setText(new MyDatePicker().setCurrentDate().toString());

        btnChangeDate = (Button)findViewById(R.id.btnChange);
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new MyDatePicker();
                newFragment.show(getSupportFragmentManager(), "datepicker");

            }

        });

        btnUpload = (Button) findViewById(R.id.btn_Upload);
        btnUpload.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imageIntent, RESULT_IMAGE_LOAD_REQUEST_CODE);

            }
        });

        imageFile = createImageFile();
        imageUri = Uri.fromFile(imageFile);
        btnTakePhoto = (Button) findViewById(R.id.btn_TakePhoto);
        btnTakePhoto.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePhotoIntent, RESULT_IMAGE_CAPTURE_REQUEST_CODE);

                }
            }

        });

        btnSave = (Button) findViewById(R.id.btn_Save);
        btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                HomePage.dbBabyBook.open();
                HomePage.dbBabyBook.insertIntoBook(HomePage.dbBabyBook.db, tvDate.getText().toString(), etTitle.getText().toString(), filePath.toString(), etStory.getText().toString());
                Cursor allStories = HomePage.dbBabyBook.readBook();


                while (allStories.moveToNext()) {
                    count++;

                }

               /* Intent newIntent = new Intent(WritePage.this, Testing.class);
                newIntent.putExtra("count", count);
                startActivity(newIntent);*/
                HomePage.dbBabyBook.close();

                Intent newIntent = new Intent(WritePage.this,HomePage.class);
                startActivity(newIntent);
            }

        });


    }


    protected void onPause() {

        super.onPause();
        count = 0;
    }


    private static File createImageFile() {

        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "BabyBook");

        //Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //creating media filename

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RESULT_IMAGE_LOAD_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int colIndex = cursor.getColumnIndex(filePathColumn[0]);
                filePath = cursor.getString(colIndex);

                imgBaby.setBackgroundResource(0);
                imgBaby.setImageBitmap(BitmapFactory.decodeFile(filePath.toString()));
                cursor.close();

            }
        }


        if (requestCode == RESULT_IMAGE_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK) {

            //imgBaby.setImageURI(imageUri); //this works too but gives a smaller size image and is not reliable


            if (imageFile.exists()) {

                filePath = imageFile.getAbsolutePath().toString();
                Bitmap imageBitmap = BitmapFactory.decodeFile(filePath);
                imgBaby.setBackgroundResource(0);
                imgBaby.setImageBitmap(imageBitmap);
            }


        }

    }
}

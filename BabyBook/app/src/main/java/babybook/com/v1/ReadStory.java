package babybook.com.v1;
import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadStory extends Activity {

    private ImageView image;
    private TextView tvDate, tvTitle, tvStory;
    int id;
    Story story;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);

        image = (ImageView)findViewById(R.id.imgPic);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvStory = (TextView)findViewById(R.id.tvStory);

        id = getIntent().getIntExtra("index", 0);
        story = StorySingleton.getInstance().getStory(id);

        tvDate.setText(story.getDate());
        tvTitle.setText(story.getTitle());
        tvStory.setText(story.getDescription());

        file = new File(story.getImagePath());
        if (file.exists()){
            image.setImageBitmap(BitmapFactory.decodeFile(story.getImagePath().toString()));
        }else{

        }
    }


}

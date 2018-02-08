package babybook.com.v1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.*;
import android.content.Context;
import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;

public class StoryAdapter extends ArrayAdapter<Story> {

    private Context context;

    public StoryAdapter(Context context, ArrayList<Story> storyList ) {

        super(context, 0, storyList);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Story story = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.storylisttwocol,
                          parent,false);
        }

        TextView date = (TextView)convertView.findViewById(R.id.tvdatecolumn);
        TextView title = (TextView)convertView.findViewById(R.id.tvtitlecolumn);

        date.setText(story.getDate());
        title.setText(story.getTitle());

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent readIntent = new Intent(context, ReadStory.class);
                readIntent.putExtra("index", position);
                context.startActivity(readIntent);
            }
        });
        return convertView;
    }
}

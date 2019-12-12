package com.example.tradm_but_im_legit_trying_to_do_it;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tradm_recovering_from_my_foolishness.R;

public class MainActivity extends AppCompatActivity {

    ListView list;
    String titles[] = {"Title 1", "Title2", "Title 3"};
    String desc[] = {"Desc 1", "Desc 2", "Desc 3"};
    int imgs[] = {R.drawable.app_bar, R.drawable.background, R.drawable.ic_email_black_24dp};

    //past images in drawable folder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.home_list);

        //creating instance of adapter
        MyAdapter adapter = new MyAdapter(this, titles, imgs, desc);
        //setting adapter to the list
        list.setAdapter(adapter);

        //get item clicks
        //move pages with each item click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position>=0 && position < titles.length){
                    Toast.makeText(MainActivity.this, titles[position], Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String myTitles[];
        String myDesc[];
        int[] imgs;

        MyAdapter(Context c, String[] titles, int[] imgs, String[] desc){
            super(c, R.layout.item_bar, R.id.item_title, titles);
            this.context=c;
            this.imgs=imgs;
            this.myTitles=titles;
            this.myDesc=desc;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.item_bar, parent, false);
            ImageView images = row.findViewById(R.id.item_pic);
            TextView myTitle = row.findViewById(R.id.item_title);
            TextView myDesc = row.findViewById(R.id.item_content);
            images.setImageResource(imgs[position]);
            myDesc.setText(desc[position]);
            return row;
        }
    }
}

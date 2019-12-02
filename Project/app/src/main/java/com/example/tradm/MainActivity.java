package com.example.tradm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_android, "Line1", "Line2"));
        exampleList.add(new ExampleItem(R.drawable.ic_cake, "Line3", "Line4"));
        exampleList.add(new ExampleItem(R.drawable.ic_whatshot, "Line5", "Line6"));

    }
}

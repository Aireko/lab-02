package com.example.listcity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // find reference to ListView
        cityList = findViewById(R.id.city_list);

        // declare a string array of cities
        String[] cities = {"Edmonton", "Vancouver", "Toronto", "Calgary",
                "Montreal", "Ottawa", "Winnipeg", "Quebec City",
                "Hamilton", "Victoria"};

        // Create a new ‘ArrayList’ and assign it to the reference ‘dataList’. This will contain the
        //data (the string array of cities).
        dataList = new ArrayList<>();

        // Add the data(string array containing city names) to the ‘dataList’ as shown in the
        //picture below.
        dataList.addAll(Arrays.asList(cities));

        // Now we have to link the content.xml to the ‘dataList’ so that each element will be
        //displayed in a separate row in the list.
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.city_text, dataList);

        // Finally, we connect the ‘ListView’ to the ‘ArrayAdapter’(cityAdapter) which will show
        //each ‘TextView’ in the form of a scrolling list.
        cityList.setAdapter(cityAdapter);
    }
}
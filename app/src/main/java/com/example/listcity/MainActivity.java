package com.example.listcity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button handleAddCity;
    Button handleRemoveCity;
    int selectedPosition = -1; // this tracks select4ed city position

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // find reference to ListView
        cityList = findViewById(R.id.city_list);
        handleAddCity = findViewById(R.id.add_city_button);
        handleRemoveCity = findViewById(R.id.delete_city_button);

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

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Toast.makeText(MainActivity.this,
                        "Selected: " + dataList.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // set up addcity button click listener
        handleAddCity.setOnClickListener(new View.OnClickListener() { // onclick can be replaced with lambda, should prob read about ts latter
            @Override
            public void onClick(View v) {
                showAddCityDialog();
            }
        });

        // set up removecity button click listener
        handleRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedCity();
            }
        });
    }

    // we love javadoc
    /**
     * show dialog to add a new city
     */
    private void showAddCityDialog() {
        // create an alertdialog with an edittext
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add City");

        // set up the input field
        final EditText input = new EditText(this);
        input.setHint("Enter city name");
        builder.setView(input);

        // set up the buttons
        builder.setPositiveButton("CONFIRM", (dialog, which) -> {

            String cityName = input.getText().toString().trim();

            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, cityName + " added!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, "City name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    /**
     * delete the selected city from the list
     */
    private void deleteSelectedCity() {

        if (selectedPosition != -1 && selectedPosition < dataList.size()) {

            String cityName = dataList.get(selectedPosition);
            dataList.remove(selectedPosition);
            cityAdapter.notifyDataSetChanged();

            Toast.makeText(this, cityName + " deleted!", Toast.LENGTH_SHORT).show();
            selectedPosition = -1; // reset selection
        }
        else {
            Toast.makeText(this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
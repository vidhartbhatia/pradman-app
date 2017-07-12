package com.example.vidhart.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    public static final double DENSITY = 7859.50336256;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// An adapter to convert the String[] into something that can go in the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner rodSpinner = (Spinner) findViewById(R.id.spinner_rod);

        rodSpinner.setAdapter(adapter);
    }

    public void convert(View view) {
        Spinner rodSpinner;
        EditText speedEditText, outputEditText, diameterEditText;

        rodSpinner = (Spinner) findViewById(R.id.spinner_rod);
        speedEditText = (EditText) findViewById(R.id.editText_speed);
        diameterEditText = (EditText) findViewById(R.id.editText_diameter);
        outputEditText = (EditText) findViewById(R.id.editText_output);

        // Get the input
        String rodType = (String) rodSpinner.getSelectedItem();
        double speed = Double.valueOf(speedEditText.getText().toString());
        double diameter = Double.valueOf(diameterEditText.getText().toString());

        // Convert the strings to something in our Unit enu,
        //Converter.Unit fromUnit = Converter.Unit.fromString(fromString);
       // Converter.Unit toUnit = Converter.Unit.fromString(toString);

        // Create a converter object and convert!
        //Converter converter = new Converter(fromUnit, toUnit);
        double result = calculateOutput(rodType,diameter,speed);
        outputEditText.setText(String.valueOf((int)result));
    }
    public double calculateOutput (String rod, double d, double s) {

        double area;
        switch(rod) {
            case "Round": area = Math.PI * Math.pow((d/2000.0),2);
                break;
            case "Square" : area  = d*d/1000000.0;
                break;
            case "Hexagon" : area = ((3 / (Math.pow(3, 0.5))) / 2) * d/1000.0 * d/1000.0;
                break;
            default: area = 1;
                break;
        }
        //System.out.println((int) (area * 10000) / 10000.0);
        double volume = area*s;
        double mass = DENSITY * volume /1000.0;
        return mass*3600.0;
    }


}

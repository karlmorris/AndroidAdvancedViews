package edu.temple.advancedviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import edu.temple.advancedviews.adapters.ImageAndTextAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner basicSpinner = (Spinner) findViewById(R.id.basic_spinner);

        //  Create an array adapter and assign to spinner
        ArrayAdapter<CharSequence> planetArrayAdapter =  ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_dropdown_item_1line);
        basicSpinner.setAdapter(planetArrayAdapter);

        //  Catch item selected event
        basicSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, basicSpinner.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner advancedSpinner = (Spinner) findViewById(R.id.advanced_spinner);
        advancedSpinner.setAdapter(new ImageAndTextAdapter(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_next) {
            startActivity(new Intent(this, SecondActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

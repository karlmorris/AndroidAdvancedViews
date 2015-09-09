package edu.temple.advancedviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import edu.temple.advancedviews.R;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        AutoCompleteTextView basic, advanced;

        basic = (AutoCompleteTextView) findViewById(R.id.basicAutoCompleteTextView);
        advanced = (AutoCompleteTextView) findViewById(R.id.advancedAutoCompleteTextView);
        
        ArrayAdapter<CharSequence> basicAdapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_dropdown_item_1line);
        basic.setAdapter(basicAdapter);
    }
}

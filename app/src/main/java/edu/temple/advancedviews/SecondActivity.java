package edu.temple.advancedviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import edu.temple.advancedviews.adapters.ImageAndTextAdapter;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        //  Set instance of same adapter used in MainActivity
        gridView.setAdapter(new ImageAndTextAdapter(this, 18));


        //  Set event listender
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String message = ((TextView) view.findViewById(i)).getText().toString();

                Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
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
            startActivity(new Intent(this, ThirdActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

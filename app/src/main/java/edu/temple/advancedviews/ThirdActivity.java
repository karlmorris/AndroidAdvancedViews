package edu.temple.advancedviews;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import edu.temple.advancedviews.R;

public class ThirdActivity extends Activity {

    AutoCompleteTextView basic, advanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        /**
         *  Auto complete with local data set
         */

        basic = (AutoCompleteTextView) findViewById(R.id.basicAutoCompleteTextView);

        ArrayAdapter<CharSequence> basicAdapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_dropdown_item_1line);
        basic.setAdapter(basicAdapter);



        /**
         *  Auto complete with remote data
         */

        advanced = (AutoCompleteTextView) findViewById(R.id.advancedAutoCompleteTextView);

        advanced.addTextChangedListener(new TextWatcher() {

            // We keep previous length to ensure that we only query for suggestions when the user enters new characters
            int previousTextLength;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                previousTextLength = charSequence.length();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 2 && charSequence.length() >= previousTextLength)
                    updateSuggestions(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void updateSuggestions(String substring){
        new CallSuggestionsAPI().execute(substring);
    }

    //  Updates the adapter with suggestions based on the last typed characters
    private class CallSuggestionsAPI extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... params) {
            String[] suggestions = null;
            try {
                String url = "http://cis-linux1.temple.edu/~tuf80213/courses/temple/cis3515/examples/api/autocomplete.php?substring=" + params[0];

                String response = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream())).readLine();

                try {
                    JSONObject responseObject = new JSONObject(response);
                    if (responseObject.getString("status").equals("OK")){
                        JSONArray values = responseObject.getJSONArray("values");
                        suggestions = new String[values.length()];
                        for (int i = 0; i < values.length(); i++)
                            suggestions[i] = values.getString(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
            return suggestions;
        }

        protected void onPostExecute(String[] suggestions) {
            try{
                advanced.setAdapter(new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, suggestions));
                advanced.setThreshold(2);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

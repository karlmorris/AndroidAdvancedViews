package edu.temple.advancedviews.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.temple.advancedviews.R;

/**
 * Adapter class to generate views for an adapterview
 * */
public class ImageAndTextAdapter extends BaseAdapter {

    Context c;
    int count = 6;


    public  ImageAndTextAdapter(Context c, int count){
        this(c);
        this.count = count;
    }


    public  ImageAndTextAdapter(Context c){
        this.c = c;
    }


    //  Return the total amount of items available. This might the size of an array.
    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    //  Build and return the view for a single item that will be displayed in the adapterview
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(c);
        TextView textView = new TextView(c);

        textView.setText("Item " + i);
        imageView.setImageResource(R.mipmap.ic_launcher);

        //  Give this item an ID or value so it can be retrived later

        textView.setId(i);

        LinearLayout ll = new LinearLayout(c);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(imageView);
        ll.addView(textView);

        return ll;
    }
}

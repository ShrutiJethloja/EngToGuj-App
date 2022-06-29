package com.example.gujengapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int gColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> list, int gColorResourceId)
    {
        super(context, 0, list);
        this.gColorResourceId = gColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currWord = getItem(position);

        TextView tv1 = (TextView) listItemView.findViewById(R.id.textView);
        tv1.setText(currWord.getGujTranslation());

        TextView tv2 = (TextView) listItemView.findViewById(R.id.textView2);
        tv2.setText(currWord.getDefaultTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
//        iconView.setImageResource(currWord.getImageResourceId());

        if(currWord.hasImage()) {
            imageView.setImageResource(currWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), gColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}

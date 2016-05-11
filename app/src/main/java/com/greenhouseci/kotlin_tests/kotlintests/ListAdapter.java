package com.greenhouseci.kotlin_tests.kotlintests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by priit on 5.05.16.
 */
public class ListAdapter extends ArrayAdapter<String> {

    ArrayList<String> strings;

    public ListAdapter(Context context, ArrayList<String> strings) {
        super(context, 0, strings);
        this.strings = strings;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String string = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView tvListItem = (TextView) convertView.findViewById(R.id.tv_list_item);
        TextView btnDeleteItem = (TextView) convertView.findViewById(R.id.btn_delete_item);

        tvListItem.setText(string);
        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strings.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}

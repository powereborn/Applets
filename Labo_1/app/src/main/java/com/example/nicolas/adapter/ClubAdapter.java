package com.example.nicolas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.activity.R;
import com.example.nicolas.model.Club;

import java.util.ArrayList;

/**
 * Created by Nicolas on 04/05/2015.
 */
public class ClubAdapter extends ArrayAdapter<Club> {

    private static class ViewHolder {
        private ImageView imageView;
        private TextView titleView;
        private TextView localView;
    }

    private ViewHolder viewHolder;

    public ClubAdapter(Context context, int layoutId, ArrayList<Club> items) {
        super(context, layoutId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.fragment_itemclub, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgClub);
            viewHolder.titleView = (TextView) convertView.findViewById(R.id.name);
            viewHolder.localView = (TextView) convertView.findViewById(R.id.local);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Club item = getItem(position);
        if (item!= null) {
            viewHolder.imageView.setImageResource(item.getIcon());
            viewHolder.titleView.setText(item.getNom());
            viewHolder.localView.setText(item.getLocal());
        }

        return convertView;
    }
}

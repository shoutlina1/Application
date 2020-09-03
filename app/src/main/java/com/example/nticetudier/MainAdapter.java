package com.example.nticetudier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] module;
    private int[] image;


    public MainAdapter(Context c, String[] module, int[] image) {
        context = c;
        this.module = module;
        this.image = image;
    }

    public MainAdapter(Modulefragment modulefragment, String[] module, int[] image) {
    }


    @Override
    public int getCount() {
        return module.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.row_item,null);
            ImageView imageView = convertView.findViewById(R.id.image_view);
            TextView textView =convertView.findViewById(R.id.Text_v);
            imageView.setImageResource(image[position]);
            textView.setText(module[position]);


        }

        return convertView;

    }
}
package com.example.lostandfound2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends BaseAdapter {

    ArrayList<items> itemList;
    Context context;

    private static LayoutInflater inflater = null;

    public RecyclerViewAdapter(Context context, ArrayList<items> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public items getItem(int pos) {
        return itemList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return itemList.get(pos).getId();
    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_row, null);
        }
        TextView words = convertView.findViewById(R.id.row_itemName);
        items item = itemList.get(pos);
        words.setText(item.getLostFound()+" "+item.getDescription());
        return convertView;
    }

}

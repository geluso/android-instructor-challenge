package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by moonmayor on 5/12/16.
 */
public class GroceryAdapter extends BaseAdapter {

    private final Context context;
    private List<GroceryItem> mGroceries;
    private LayoutInflater mLayoutInflator;

    public GroceryAdapter(Context context, List<GroceryItem> groceries) {
        this.context = context;
        mGroceries = groceries;
        mLayoutInflator = LayoutInflater.from(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the grocery item corresponding to this position
        final GroceryItem item = mGroceries.get(position);

        // Inflate a grocery list item view
        convertView = (View) mLayoutInflator.inflate(R.layout.grocery_list_item, null);

        // set up the checkbox and text.
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(item.isChecked);
        checkBox.setText("");

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(item.name);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", item.name);
                intent.putExtra("quantity", item.quantity);
                intent.putExtra("description", item.description);

                ((Activity) context).startActivityForResult(intent, MainActivity.EDIT_ITEM);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return mGroceries.size();
    }

    @Override
    public Object getItem(int position) {
        return mGroceries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(GroceryItem item) {
        mGroceries.add(item);
        this.notifyDataSetChanged();

    }

    public void updateItem(int position, GroceryItem item) {
        mGroceries.set(position, item);
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mGroceries.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty() {
        return mGroceries.isEmpty();
    }
}

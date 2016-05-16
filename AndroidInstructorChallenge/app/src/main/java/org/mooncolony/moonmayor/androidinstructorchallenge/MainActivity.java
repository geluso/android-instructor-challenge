package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int CREATE_ITEM = 1;
    public static final int EDIT_ITEM = 2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GroceryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);

        GroceryData data = GroceryData.getInstance();
        if (data.items == null) {
            data.items = loadGroceryList(state);
        }

        ListView listView = (ListView) findViewById(R.id.grocery_list);
        mAdapter = new GroceryAdapter(MainActivity.this, data.items);
        listView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("isChecked", false);
                intent.putExtra("name", "");
                intent.putExtra("quantity", 1);
                intent.putExtra("description", "");
                intent.putExtra("position", -1);

                startActivity(intent);
            }
        });
    }

    protected void onRestart() {
        super.onRestart();
        mAdapter.notifyDataSetChanged();
    }

    private List<GroceryItem> loadGroceryList(Bundle state) {
        Resources res = getResources();
        String[] groceries = res.getStringArray(R.array.groceries);

        List<GroceryItem> list = new ArrayList<>();
        for (String grocery : groceries) {
            GroceryItem item = new GroceryItem(grocery);
            list.add(item);
        }

        return list;
    }
}

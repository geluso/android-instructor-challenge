package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String[] groceries = res.getStringArray(R.array.groceries);

        List<GroceryItem> groceryList = new ArrayList<>();
        for (String grocery : groceries) {
            GroceryItem item = new GroceryItem(grocery);
            groceryList.add(item);
        }

        ListView listView = (ListView) findViewById(R.id.grocery_list);
        listView.setAdapter(new GroceryAdapter(MainActivity.this, groceryList));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("isChecked", false);
                intent.putExtra("name", "name");
                intent.putExtra("quantity", 1);
                intent.putExtra("description", "description");

                startActivity(intent);
            }
        });
    }

}

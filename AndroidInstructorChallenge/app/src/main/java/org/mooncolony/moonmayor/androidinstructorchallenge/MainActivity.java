package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int CREATE_ITEM = 1;
    public static final int EDIT_ITEM = 2;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private List<GroceryItem> mGroceryList;
    private GroceryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);

        loadGroceryList(state);

        ListView listView = (ListView) findViewById(R.id.grocery_list);
        mAdapter = new GroceryAdapter(MainActivity.this, mGroceryList);
        listView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("isChecked", false);
                intent.putExtra("name", "name");
                intent.putExtra("quantity", 1);
                intent.putExtra("description", "description");

                startActivityForResult(intent, CREATE_ITEM);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("result", "code 1-create 2-edit:" + requestCode);

        if (resultCode == RESULT_OK) {
            processAction(data);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("serial", "attempting serialization");
        if (mGroceryList != null) {
            savedInstanceState.putSerializable("list", (Serializable) mGroceryList);
        }
    }

    private void loadGroceryList(Bundle state) {
        Log.d("serial", "accessing saved instance");
        if (state != null && state.getSerializable("list") != null) {
            Log.d("serial", "deserializing");
            mGroceryList = (List<GroceryItem>) state.getSerializable("list");
        } else {
            Log.d("serial", "from string array");
            Resources res = getResources();
            String[] groceries = res.getStringArray(R.array.groceries);

            mGroceryList = new ArrayList<>();
            for (String grocery : groceries) {
                GroceryItem item = new GroceryItem(grocery);
                mGroceryList.add(item);
            }
        }

    }

    private void processAction(Intent intent) {
        if (intent.hasExtra("action")) {
            Log.d("action", "has action");
            String action = intent.getStringExtra("action");
            if (action.equals("create")) {
                Log.d("action", "create");
                createItem(intent);
            } else if (action.equals("update")) {
                Log.d("action", "update");
                updateItem(intent);
            } else if (action.equals("delete")) {
                Log.d("action", "delete");
                deleteItem(intent);
            }
        }

    }

    private void createItem(Intent intent) {
        GroceryItem item = GroceryItem.fromIntent(intent);
        mAdapter.addItem(item);
    }

    private void updateItem(Intent intent) {
        GroceryItem item = GroceryItem.fromIntent(intent);
        int position = intent.getIntExtra("position", -1);

        if (position != -1) {
            mAdapter.updateItem(position, item);
        }
    }

    private void deleteItem(Intent intent) {
        int position = intent.getIntExtra("position", -1);
        Log.d("action", "size:" + mAdapter.getCount());
        if (position != -1) {
            mAdapter.removeItem(position);
        }
    }

}

package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final GroceryData data = GroceryData.getInstance();

        Intent intent = getIntent();

        GroceryItem item = GroceryItem.fromIntent(intent);
        final int position = intent.getIntExtra("position", -1);

        ((EditText) findViewById(R.id.name)).setText(item.name);
        ((EditText) findViewById(R.id.quantity)).setText("" + item.quantity);
        ((TextView) findViewById(R.id.description)).setText(item.description);

        Button save = (Button) findViewById(R.id.save);
        Button delete = (Button) findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText) findViewById(R.id.name)).getText().toString();
                String description = ((EditText) findViewById(R.id.description)).getText().toString();
                int quantity = Integer.parseInt(((EditText) findViewById(R.id.quantity)).getText().toString());

                // Create the item if it didn't exist before
                GroceryItem item;
                if (position == -1) {
                    item = new GroceryItem(name, quantity, description);
                    data.items.add(item);
                } else {
                    // Update the item if it existed before.
                    item = data.items.get(position);
                    item.name = name;
                    item.description = description;
                    item.quantity = quantity;
                }

                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.items.remove(position);
                finish();
            }
        });
    }
}

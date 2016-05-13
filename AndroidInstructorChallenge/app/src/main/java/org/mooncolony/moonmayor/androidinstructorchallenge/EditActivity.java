package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int quantity = intent.getIntExtra("quantity", 1);
        String description = intent.getStringExtra("description");

        ((EditText) findViewById(R.id.name)).setText(name);
        ((EditText) findViewById(R.id.quantity)).setText("" + quantity);
        ((TextView) findViewById(R.id.description)).setText(description);
    }
}

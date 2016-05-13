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

        Intent intent = getIntent();
        final int position = intent.getIntExtra("position", -1);
        String name = intent.getStringExtra("name");
        int quantity = intent.getIntExtra("quantity", 1);
        String description = intent.getStringExtra("description");

        ((EditText) findViewById(R.id.name)).setText(name);
        ((EditText) findViewById(R.id.quantity)).setText("" + quantity);
        ((TextView) findViewById(R.id.description)).setText(description);

        Button save = (Button) findViewById(R.id.save);
        Button delete = (Button) findViewById(R.id.delete);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                intent.putExtra("action", "delete");
                intent.putExtra("position", position);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

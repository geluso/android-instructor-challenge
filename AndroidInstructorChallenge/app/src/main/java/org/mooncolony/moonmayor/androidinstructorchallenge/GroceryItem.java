package org.mooncolony.moonmayor.androidinstructorchallenge;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by moonmayor on 5/12/16.
 */
public class GroceryItem implements Serializable {
    public boolean isChecked;
    public String name;
    public int quantity;
    public String description;

    public GroceryItem(String name, int quantity, String description) {
        this.isChecked = false;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public GroceryItem(String name) {
        this(name, 1, "");
    }

    public static GroceryItem fromIntent(Intent intent) {
        String name = intent.getStringExtra("name");
        int quantity = intent.getIntExtra("quantity", 1);
        String description = intent.getStringExtra("description");

        return new GroceryItem(name, quantity, description);
    }
}

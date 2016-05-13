package org.mooncolony.moonmayor.androidinstructorchallenge;

/**
 * Created by moonmayor on 5/12/16.
 */
public class GroceryItem {
    public boolean isChecked;
    public String name;
    public String description;

    public GroceryItem(String name, String description) {
        this.isChecked = false;
        this.name = name;
        this.description = description;
    }

    public GroceryItem(String name) {
        this(name, "");
    }
}

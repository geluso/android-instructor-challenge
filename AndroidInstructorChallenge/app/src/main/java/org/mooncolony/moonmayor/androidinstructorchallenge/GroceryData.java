package org.mooncolony.moonmayor.androidinstructorchallenge;

import java.util.List;

/**
 * Created by moonmayor on 5/16/16.
 */
public class GroceryData {
    public List<GroceryItem> items;

    private static final GroceryData data = new GroceryData();
    public static GroceryData getInstance() {
        return data;
    }
}

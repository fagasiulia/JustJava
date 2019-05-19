package com.example.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    String total = "Total: $";
    String message = "Thank you!";
    int numberOfCoffeeOrdered = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increaseOrder(View view){
        numberOfCoffeeOrdered++;
        display(numberOfCoffeeOrdered);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decreaseOrder(View view){
        if(numberOfCoffeeOrdered > 0){
            numberOfCoffeeOrdered--;
            display(numberOfCoffeeOrdered);
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(numberOfCoffeeOrdered);
        displayPrice (numberOfCoffeeOrdered * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText(total + number);
    }
}
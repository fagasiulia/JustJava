package com.example.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    String name = "Iulia";
    String message = "Thank you!";
    boolean checkBoxWippedCream = false;
    boolean checkBoxChocolate = false;
    int numberOfCoffeeOrdered = 0;
    int coffePrice = 5;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increaseOrder(View view) {
        numberOfCoffeeOrdered++;
        display(numberOfCoffeeOrdered);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decreaseOrder(View view) {
        if (numberOfCoffeeOrdered > 0) {
            numberOfCoffeeOrdered--;
            display(numberOfCoffeeOrdered);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.order_summary_text_view);
        calculatePrice(numberOfCoffeeOrdered, coffePrice);
        String summary = createSummary(name, numberOfCoffeeOrdered, totalPrice, message);
        quantityTextView.setText(summary);
    }

    /**
     * @param coffesOrdered = number of coffees the cutorer ordered
     * @param price         = the price of one coffee
     * @return
     */
    public void calculatePrice(int coffesOrdered, int price) {
        totalPrice = coffesOrdered * price;
    }

    /**
     * This method displays a summary of the order
     *
     * @param name     = name of the customer
     * @param quantity = quantity of the coffees ordered
     * @param price    = total price
     * @param message  = Thank you message
     * @return
     */
    public String createSummary(String name, int quantity, int price, String message) {
        if(checkBoxWippedCream && checkBoxChocolate){
            checkBoxWippedCream = false;
            checkBoxChocolate = false;
            return "Name: " + name + "\n" + "Add wipped cream and chocolate\n" + "Quantity: " + quantity + " \n"
                    + "Price: " + price + "\n" + message;
        }
        else if(checkBoxWippedCream){
            checkBoxWippedCream = false;
            return "Name: " + name + "\n" + "Add wipped cream\n" + "Quantity: " + quantity + " \n"
                    + "Price: " + price + "\n" + message;
        }
        else if(checkBoxChocolate){
            checkBoxChocolate = false;
            return "Name: " + name + "\n" + "Add chocolate\n" + "Quantity: " + quantity + " \n"
                    + "Price: " + price + "\n" + message;
        }
        else{
            return "Name: " + name + "\n" + "Quantity: " + quantity + "\n"
                    + "Price: " + price + "\n" + message;
        }
    }

    /**
     * Check if the checkBox for the wipped cream is checked or not
     */
    public void addWippedCream(View v){
        CheckBox wippedCream = (CheckBox) findViewById(R.id.wipped_cream_checkBox);
        if (wippedCream.isChecked()){
            checkBoxWippedCream = true;
        }
    }

    /**
     * Check if the checkBox for the chocolate is checked or not
     */
    public void addChocolate(View v){
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkBox);
        if (chocolate.isChecked()){
            checkBoxChocolate= true;
        }
    }
}















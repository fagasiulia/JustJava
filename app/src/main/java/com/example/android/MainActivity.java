package com.example.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    final String message = "Thank you!";
    boolean checkBoxWippedCream = false;
    boolean checkBoxChocolate = false;
    int numberOfCoffeeOrdered = 0;
    final int coffePrice = 5;
    final int priceWippedCream = 2;
    final int priceChocolate = 1;
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
        if(numberOfCoffeeOrdered < 20) {
            numberOfCoffeeOrdered++;
            display(numberOfCoffeeOrdered);
        }
        else{
            Toast.makeText(this,"You can not order more than 20 coffees", Toast.LENGTH_SHORT).show();
        }
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
        calculatePrice(numberOfCoffeeOrdered, coffePrice, priceWippedCream, priceChocolate);

        String summary = createSummary(numberOfCoffeeOrdered, totalPrice, message);

        /**
         * When the customers presses the order button, the email app opens
         * to send the summary of the order
         */
        Intent intent = new Intent (Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java app " );
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * @param coffeesOrdered   = number of coffees the cutorer ordered
     * @param price            = the price of one coffee
     * @param wippedCreamPrice = the price of the wipped cream
     * @param chocolatePrice   = the price of the chocolate
     * @return
     */
    public void calculatePrice(int coffeesOrdered, int price, int wippedCreamPrice, int chocolatePrice ) {
        if(checkBoxWippedCream && checkBoxChocolate) {
            totalPrice = (price + wippedCreamPrice + chocolatePrice) * coffeesOrdered;
        }
        else if(checkBoxWippedCream) {
            totalPrice = (price + wippedCreamPrice) * coffeesOrdered;
        }
        else if(checkBoxChocolate) {
            totalPrice = (price + chocolatePrice) * coffeesOrdered;
        }
        else {
            totalPrice = price * coffeesOrdered;
        }
    }

    /**
     * This method displays a summary of the order
     *
     * @param quantity = quantity of the coffees ordered
     * @param price    = total price
     * @param message  = Thank you message
     * @return
     */
    public String createSummary(int quantity, int price, String message) {
        /**
         * Get the name of the customer
         */
        EditText getName = (EditText) findViewById(R.id.name_edit_text);
        String name = getName.getText().toString();

        /**
         * Check if the customer wants wipped cream or chocolate
         */
        if(checkBoxWippedCream && checkBoxChocolate){
            checkBoxWippedCream = false;
            checkBoxChocolate = false;
            return "Name: " + name + "\n" + "Add wipped cream and chocolate\n" + "Quantity: " + quantity + " \n"
                    + "Price: $" + price + "\n" + message;
        }
        else if(checkBoxWippedCream){
            checkBoxWippedCream = false;
            return "Name: " + name + "\n" + "Add wipped cream\n" + "Quantity: " + quantity + " \n"
                    + "Price: $" + price + "\n" + message;
        }
        else if(checkBoxChocolate){
            checkBoxChocolate = false;
            return "Name: " + name + "\n" + "Add chocolate\n" + "Quantity: " + quantity + " \n"
                    + "Price: $" + price + "\n" + message;
        }
        else{
            return "Name: " + name + "\n" + "Quantity: " + quantity + "\n"
                    + "Price: $" + price + "\n" + message;
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















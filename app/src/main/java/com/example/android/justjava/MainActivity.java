/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {


    /* Set the Price for a Cup and the Extras
     */
    int quantity = 0;
    int priceTopping1 = 1;
    int priceTopping2 = 2;
    int priceCup = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:"));
        mailIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mailIntent);
        }

    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
        } else quantity = 100;

        displayQuantity(quantity);

    }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
        } else quantity = 0;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number of coffees ordered
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Method for calculating the price
     *
     * @return Price for quantity cups of coffee
     */
    private int calculatePrice() {
        CheckBox checkTopping1 = (CheckBox) findViewById(R.id.topping1);
        CheckBox checkTopping2 = (CheckBox) findViewById(R.id.topping2);
        int topping1true = (checkTopping1.isChecked()) ? 1 : 0;
        int topping2true = (checkTopping2.isChecked()) ? 1 : 0;
        return (priceTopping1 * topping1true + priceTopping2 * topping2true + priceCup) * quantity;
    }

    /*
    * This method return a nice Thank You message
    *
    * @param the total of your order
    * */

    private String createOrderSummary(int price) {
        CheckBox checkTopping1 = (CheckBox) findViewById(R.id.topping1);
        CheckBox checkTopping2 = (CheckBox) findViewById(R.id.topping2);

        EditText customerNameView = (EditText) findViewById(R.id.nameeditview);
        String customerName = customerNameView.getText().toString();
        String orderText = getResources().getString(R.string.helloName, customerName);
        orderText += "\n" + getResources().getString(R.string.yourOrder, quantity);
        if (checkTopping1.isChecked()) {
            orderText += "\n" + getResources().getString(R.string.addWhippedCream);
        }
        if (checkTopping2.isChecked()) {
            orderText += "\n" + getResources().getString(R.string.addChocolate);
        }
        orderText += "\n" + getResources().getString(R.string.yourTotal, NumberFormat.getCurrencyInstance().format(price));
        orderText += "\n" + getResources().getString(R.string.thankYou);
        return orderText;
    }


    /**
     * This method displays the given text on the screen.
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = quantity *5;
        String currencyPrice = NumberFormat.getCurrencyInstance().format(price);
        String priceMessage = "Total: " + currencyPrice + "\nThank You!" ;
        displayMessage(priceMessage);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity= quantity + 1 ;
        displayQuantity(quantity);
        }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        if(quantity > 0) {
            quantity = quantity - 1;
        }
        else quantity = 0;
        displayQuantity(quantity);
        }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

/*     Just some tests on how to pull quantity from a TextView.

        private int getquantity() {
        TextView textView = (TextView) findViewById(R.id.quantity_text_view);
        String quantitys = textView.getText().toString();
        int quantity = Integer.parseInt(quantitys);
        return(quantity);
    }*/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

}
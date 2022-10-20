package au.edu.anu.cecs.linkhome.homePage.payment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import au.edu.anu.cecs.linkhome.homePage.HomePage;

/**
 * Parent class of MasterCard and Paypal
 *
 * @author Avani Dhaliwal
 */
public class PaymentMethod extends AppCompatActivity{
    /**
     * If the user confirms the payment, and the data is not scam
     * then go to the PaymentSuccessful Activity
     *
     * If the user confirms the payment, and the data is scam
     * then open a dialogue box warning the user about the potential scam
     *
     * @param extras information about weather the data is a scam or not
     * @param context either MasterCard or Paypal
     * @author Devanshi Dhall, Hao Zhang
     */
    public void confirmation(Bundle extras, AppCompatActivity context){
        //If the post might be a scam, show an Alert dialogue box
        if (extras != null && extras.getString("isScam").equals("true")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setCancelable(true);
            builder.setTitle(Html.fromHtml("<font color='#8B0000'>Alert!</font>"));
            builder.setMessage(Html.fromHtml("<font color='#8B0000'>This might be a scam. Would you still like to proceed with the payment?</font>"));

            //If the user cancels, go back to the home page
            builder.setNegativeButton("Cancel", (dialog, whichButton) -> {
                Intent homePage = new Intent(getApplicationContext(), HomePage.class);
                startActivity(homePage);
            });

            //If the user still accepts, the payment is confirmed.
            builder.setPositiveButton("Ok", (dialog, whichButton) -> {
                Intent paySuccess = new Intent(getApplicationContext(), PaymentSuccessful.class);
                startActivity(paySuccess);
            });
            builder.create();
            builder.show();
        }
        // Else confirm payment
        else {
            Intent paySuccess = new Intent(getApplicationContext(), PaymentSuccessful.class);
            startActivity(paySuccess);
        }
    }

    /**
     * Allows the user to choose a date from a calender
     *
     * @param dateEdit the date that the user edits
     * @param context either MasterCard or Paypal
     * @author Hao Zhang
     */
    public void editDate(EditText dateEdit, AppCompatActivity context){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year1, monthOfYear, dayOfMonth) ->
        {
            String text = (monthOfYear + 1) + "/" + year1;
            dateEdit.setText(text);
        }, year, month, day);
        datePickerDialog.show();
    }
}

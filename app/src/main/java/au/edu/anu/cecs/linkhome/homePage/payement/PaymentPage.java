package au.edu.anu.cecs.linkhome.homePage.payement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;
import au.edu.anu.cecs.linkhome.R;

/**
 * @author Nihar Meshram, Hao Zhang
 */
public class PaymentPage extends AppCompatActivity {

    private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        dateEdt = findViewById(R.id.paymentDates);

<<<<<<< HEAD:app/src/main/java/au/edu/anu/cecs/linkhome/HomePage/PaymentPage.java
        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
=======
        // adding click listener for our pick date button
        dateEdt.setOnClickListener(v -> {

            // the instance of the calendar.
            final Calendar c = Calendar.getInstance();

            // day, month and year.
>>>>>>> origin/main:app/src/main/java/au/edu/anu/cecs/linkhome/homePage/payement/PaymentPage.java
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

<<<<<<< HEAD:app/src/main/java/au/edu/anu/cecs/linkhome/HomePage/PaymentPage.java
            // Creating a variable for date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentPage.this, (view, year1, monthOfYear, dayOfMonth) ->
            {
                        String text = (monthOfYear + 1) + "/" + year1;
                        dateEdt.setText(text);

                    }, year, month, day);
            // Display our date picker dialog.
=======
            // variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // passing context.
                    PaymentPage.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        //setting date to the  edit text.
                        String text = (monthOfYear + 1) + "/" + year1;
                        dateEdt.setText(text);
                    },

                    // passing year, month and day for selected date in date picker.
                    year, month, day);

            // display date picker dialog.
>>>>>>> origin/main:app/src/main/java/au/edu/anu/cecs/linkhome/homePage/payement/PaymentPage.java
            datePickerDialog.show();
        });
    }
}
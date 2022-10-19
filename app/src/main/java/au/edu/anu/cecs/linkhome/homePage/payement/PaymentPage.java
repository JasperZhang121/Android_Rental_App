package au.edu.anu.cecs.linkhome.homePage.payement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;
import au.edu.anu.cecs.linkhome.R;

public class PaymentPage extends AppCompatActivity {

    private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        dateEdt = findViewById(R.id.paymentDates);

        // adding click listener for our pick date button
        dateEdt.setOnClickListener(v -> {

            // the instance of the calendar.
            final Calendar c = Calendar.getInstance();

            // day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

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
            datePickerDialog.show();
        });
    }
}
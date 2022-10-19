package au.edu.anu.cecs.linkhome.homePage.payement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import au.edu.anu.cecs.linkhome.facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;

public class Paypal extends AppCompatActivity {
    private EditText dateEdt;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        Intent intent = getIntent();

        dateEdt = findViewById(R.id.paymentDates);
        // adding click listener for our pick date button
        dateEdt.setOnClickListener(v -> {

            // the instance of the calendar.
            final Calendar c = Calendar.getInstance();

            // day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            //variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    //passing context.
                    Paypal.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // setting date to the edit text.
                        String text = (monthOfYear + 1) + "/" + year1;
                        dateEdt.setText(text);
                    },
                    // passing year, month and day for selected date in the date picker.
                    year, month, day);

            // display the date picker dialog.
            datePickerDialog.show();
        });

        Button confirm = findViewById(R.id.paymentConfirmPaypal);
        alert = findViewById(R.id.text_alert_paypal);

        confirm.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Paypal.this);

            builder.setCancelable(true);
            builder.setTitle("Alert Title");
            builder.setMessage("Alert Message");

            builder.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.cancel());
            builder.setPositiveButton("OK", (dialog, which) -> alert.setVisibility(View.VISIBLE));
            builder.show();
        });

        String payMethod = intent.getStringExtra("payPaypal");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("payPal")) paymentMaker.pay_paypal();
    }
}
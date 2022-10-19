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

public class Mastercard extends AppCompatActivity {
    private EditText dateEdt;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastercard);
        Intent intent = getIntent();
        //
        dateEdt = findViewById(R.id.paymentDates);
        // on below line we are adding click listener
        // for our pick date button
        dateEdt.setOnClickListener(v -> {
            // on below line we are getting
            // the instance of our calendar.
            final Calendar c = Calendar.getInstance();

            // on below line we are getting
            // our day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    Mastercard.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // on below line we are setting date to our edit text.
                        String text = (monthOfYear + 1) + "/" + year1;
                        dateEdt.setText(text);
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();

        });

        Button confirm = findViewById(R.id.paymentConfirm);
        alert = findViewById(R.id.alert_text);

        confirm.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Mastercard.this);

            builder.setCancelable(true);
            builder.setTitle("Alert Title");
            builder.setMessage("Alert Message");

            builder.setNegativeButton("Cancel", (dialogInterface, which) -> dialogInterface.cancel());
            builder.setPositiveButton("OK", (dialog, which) -> alert.setVisibility(View.VISIBLE));
            builder.show();
        });

        //
        String payMethod = intent.getStringExtra("payMasterCard");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("mastercard")) paymentMaker.pay_mastercard();
    }
}

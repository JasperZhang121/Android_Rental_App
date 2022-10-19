package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;


import au.edu.anu.cecs.linkhome.R;

/**
 * @author Nihar Meshram, Hao Zhang
 */
public class PaymentPage extends AppCompatActivity {


    private EditText dateEdt;
    private Button confirm;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);

        dateEdt = findViewById(R.id.paymentDates);

        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Creating a variable for date picker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(PaymentPage.this, (view, year1, monthOfYear, dayOfMonth) ->
            {
                        String text = (monthOfYear + 1) + "/" + year1;
                        dateEdt.setText(text);

                    }, year, month, day);
            // Display our date picker dialog.
            datePickerDialog.show();
        });
    }
}
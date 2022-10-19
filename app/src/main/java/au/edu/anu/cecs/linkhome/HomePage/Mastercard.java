package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import au.edu.anu.cecs.linkhome.Facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;

/**
 * @author Hao Zhang, Nihar Meshram
 */
public class Mastercard extends AppCompatActivity {
    private EditText dateEdt;
    private Button confirm;
    private TextView alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastercard);
        Intent intent = getIntent();
        dateEdt = findViewById(R.id.paymentDates);

        // Date picker functionality
        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(Mastercard.this, (view, year1, monthOfYear, dayOfMonth) ->
            {
                String text = (monthOfYear + 1) + "/" + year1;
                dateEdt.setText(text);
                },
                    year, month, day);

            // Calling show to display our date picker dialog.
            datePickerDialog.show();

        });

        confirm = findViewById(R.id.paymentConfirm);
        alert = findViewById(R.id.alert_text);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mastercard.this);
                builder.setCancelable(true);
                builder.setTitle("Alert Title");
                builder.setMessage("Alert Message");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });

        String payMethod = intent.getStringExtra("payMasterCard");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("mastercard"))
            paymentMaker.pay_mastercard();
    }
}
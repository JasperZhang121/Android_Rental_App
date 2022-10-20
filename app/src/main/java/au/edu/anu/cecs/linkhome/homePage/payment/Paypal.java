package au.edu.anu.cecs.linkhome.homePage.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import au.edu.anu.cecs.linkhome.facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.homePage.HomePage;

/**
 * @author Devanshi Dhall, Hao Zhang
 */
public class Paypal extends AppCompatActivity {
    private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        Intent intent = getIntent();

        dateEdt = findViewById(R.id.paymentDates);
        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Paypal.this, (view, year1, monthOfYear, dayOfMonth) ->
            {
                String text = (monthOfYear + 1) + "/" + year1;
                dateEdt.setText(text);
                }, year, month, day);
            datePickerDialog.show();
        });

        Button confirm = findViewById(R.id.paymentConfirmPaypal);

        confirm.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Paypal.this);
            builder.setCancelable(true);
            builder.setTitle(Html.fromHtml("<font color='#8B0000'>Alert!</font>"));
            builder.setMessage(Html.fromHtml("<font color='#8B0000'>This might be a scam. Would you still like to proceed with the payment?</font>"));

            builder.setNegativeButton("Cancel", (dialog, whichButton) -> {
                Intent homePage = new Intent(getApplicationContext(), HomePage.class);
                startActivity(homePage);
            });

            builder.setPositiveButton("Ok", (dialog, whichButton) -> {
                Intent paySuccess = new Intent(getApplicationContext(), PaymentSuccessful.class);
                startActivity(paySuccess);
            });
            builder.create();
            builder.show();
        });

        String payMethod = intent.getStringExtra("payPaypal");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("payPal"))
            paymentMaker.pay_paypal();
    }
}
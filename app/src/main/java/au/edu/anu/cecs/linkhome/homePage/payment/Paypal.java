package au.edu.anu.cecs.linkhome.homePage.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import au.edu.anu.cecs.linkhome.facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;

/**
 * User can pay through Paypal
 *
 * @author Devanshi Dhall, Hao Zhang
 */
public class Paypal extends PaymentMethod {
    private EditText dateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        Intent intent = getIntent();

        dateEdit = findViewById(R.id.payment_dates);
        dateEdit.setOnClickListener(v -> editDate(dateEdit, Paypal.this));

        Button confirm = findViewById(R.id.payment_confirm_paypal);
        confirm.setOnClickListener(v -> confirmation(intent.getExtras(), Paypal.this));

        String payMethod = intent.getStringExtra("payPaypal");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("payPal"))
            paymentMaker.payPaypal();
    }
}
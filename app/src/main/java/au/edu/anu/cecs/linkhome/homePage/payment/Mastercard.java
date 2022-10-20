package au.edu.anu.cecs.linkhome.homePage.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import au.edu.anu.cecs.linkhome.facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;

/**
 * User can pay through MasterCard
 *
 * @author Devanshi Dhall, Hao Zhang
 */
public class Mastercard extends PaymentMethod {
    private EditText dateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastercard);
        Intent intent = getIntent();

        dateEdit = findViewById(R.id.payment_dates);
        dateEdit.setOnClickListener(v -> editDate(dateEdit, Mastercard.this));

        Button confirm = findViewById(R.id.payment_confirm);
        confirm.setOnClickListener(v -> confirmation(intent.getExtras(), Mastercard.this));

        String payMethod = intent.getStringExtra("payMasterCard");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("mastercard"))
            paymentMaker.payMastercard();
    }
}

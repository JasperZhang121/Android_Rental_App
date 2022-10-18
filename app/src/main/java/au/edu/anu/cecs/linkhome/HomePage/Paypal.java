package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;

import au.edu.anu.cecs.linkhome.Facade.PaymentMaker;
import au.edu.anu.cecs.linkhome.R;

public class Paypal extends AppCompatActivity {

    //private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        Intent intent = getIntent();
        String payMethod = intent.getStringExtra("payPaypal");
        PaymentMaker paymentMaker = new PaymentMaker();
        if (payMethod.equals("payPal")) paymentMaker.pay_paypal();
    }
}
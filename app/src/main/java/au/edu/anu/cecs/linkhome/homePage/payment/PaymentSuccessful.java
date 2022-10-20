package au.edu.anu.cecs.linkhome.homePage.payment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import au.edu.anu.cecs.linkhome.R;

/**
 * Called when the user successfully confirms the payment
 *
 * @author Devanshi Dhall
 */
public class PaymentSuccessful extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
    }
}
package au.edu.anu.cecs.linkhome.homePage.posts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.homePage.payment.Mastercard;
import au.edu.anu.cecs.linkhome.homePage.payment.Paypal;
import au.edu.anu.cecs.linkhome.stateDesignPattern.LoginState;
import au.edu.anu.cecs.linkhome.stateDesignPattern.User;

/**
 * DetailedPage class gives a detailed view
 * of the current image being clicked
 * @author Avani Dhaliwal, Hao Zhang, Nihar Meshram
 */

public class DetailedPage extends AppCompatActivity {
    ImageButton mastercard;
    ImageButton paypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        TextView cityText = findViewById(R.id.detailed_page_city);
        TextView addressText = findViewById(R.id.detailed_page_address);
        TextView postalText = findViewById(R.id.detailed_page_postal);
        TextView rentText = findViewById(R.id.detailed_page_rent);
        ImageView image = findViewById(R.id.detailed_page_image);

        mastercard = findViewById(R.id.mastercard_button);
        paypal = findViewById(R.id.paypal_button);

        String city = "";
        String rent = "";
        String address = "";
        String postal = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            city = extras.getString("city");
            address = extras.getString("address");
            postal = extras.getString("postal");
            rent = extras.getString("rent");

            Glide.with(this).load(extras.getString("image")).apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true))
                    .into(image);
        }

        // Detailed information about a specific home to be on rent
        cityText.setText(city);
        addressText.setText(address);
        postalText.setText(postal);
        rentText.setText(rent);


        User user = User.getInstance();
        //Mastercard icon when clicked starts the Mastercard activity
        mastercard.setOnClickListener(v -> {
            if(user.getUserState() instanceof LoginState){
                Intent intent = new Intent(DetailedPage.this, Mastercard.class);
                intent.putExtra("payMasterCard","mastercard");
                if (extras != null) {
                    intent.putExtra("isScam", extras.getString("isScam"));
                }
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login to Pay", Toast.LENGTH_SHORT).show();
            }
        });

        //Paypal icon when clicked starts the Paypal activity
        paypal.setOnClickListener(v -> {
            if(user.getUserState() instanceof LoginState){
                Intent intent = new Intent(DetailedPage.this, Paypal.class);
                intent.putExtra("payPaypal","payPal");
                if (extras != null) {
                    intent.putExtra("isScam", extras.getString("isScam"));
                }
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login to Pay", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import au.edu.anu.cecs.linkhome.R;

/**
 * DetailedPage class gives a detailed view
 * of the current image being clicked
 */

public class DetailedPage extends AppCompatActivity {
    Button pay_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        TextView cityText = findViewById(R.id.detiledPageCity);
        TextView addressText = findViewById(R.id.detailedPageAddress);
        TextView postalText = findViewById(R.id.detailedPagePostal);
        TextView rentText = findViewById(R.id.detailedPageRent);
        ImageView image = findViewById(R.id.detailedPageImage);

        pay_btn = findViewById(R.id.detailedPagePayButton);


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

        pay_btn.setOnClickListener(v -> {
            Intent intent = new Intent(DetailedPage.this, PaymentPage.class);
            startActivity(intent);
        });

    }


}
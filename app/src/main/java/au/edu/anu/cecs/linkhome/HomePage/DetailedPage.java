package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import au.edu.anu.cecs.linkhome.R;

/**
 * DetailedPage class gives a detailed view
 * of the current image being clicked
 *
 */

public class DetailedPage extends AppCompatActivity {
    ImageButton book_btn;
    Button pay_btn;
    DatabaseReference favour;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        TextView cityText = (TextView) findViewById(R.id.detiledPageCity);
        TextView addressText = (TextView) findViewById(R.id.detailedPageAddress);
        TextView postalText = (TextView) findViewById(R.id.detailedPagePostal);
        TextView rentText = (TextView) findViewById(R.id.detailedPageRent);
        ImageView image = (ImageView) findViewById(R.id.detailedPageImage);

        book_btn = (ImageButton)findViewById(R.id.imageButton);
        pay_btn = findViewById(R.id.detailedPagePayButton);


        String city = "";
        String rent = "";

        String address = "";
        String postal = "";
        Bundle extras = getIntent().getExtras();

        if(extras != null){
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

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedPage.this, PaymentPage.class);
                startActivity(intent);
            }
        });

    }



}
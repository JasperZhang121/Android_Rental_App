package au.edu.anu.cecs.linkhome.HomePage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import au.edu.anu.cecs.linkhome.R;

public class DetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);

        TextView cityText = (TextView) findViewById(R.id.detiledPageCity);
        TextView addressText = (TextView) findViewById(R.id.detailedPageAddress);
        TextView postalText = (TextView) findViewById(R.id.detailedPagePostal);
        TextView rentText = (TextView) findViewById(R.id.detailedPageRent);
        ImageView image = (ImageView) findViewById(R.id.detailedPageImage);

        String city = "";
        String address = "";
        String postal = "";
        String rent = "";

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

        cityText.setText(city);
        addressText.setText(address);
        postalText.setText(postal);
        rentText.setText(rent);
    }
}
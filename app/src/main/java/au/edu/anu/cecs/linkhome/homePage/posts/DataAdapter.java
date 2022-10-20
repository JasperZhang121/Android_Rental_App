package au.edu.anu.cecs.linkhome.homePage.posts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.stateDesignPattern.LoginState;
import au.edu.anu.cecs.linkhome.stateDesignPattern.User;

/**
 * Adapter for DataFragment class
 *
 * @author Hao Zhang, Nihar Meshram
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private final ItemClickListener listener;
    private final Context context;
    private final ArrayList<Data> list;
    private static DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    public DataAdapter(Context context, ArrayList<Data> list, ItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        firebaseDatabase = FirebaseDatabase.getInstance();
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = list.get(position);
        holder.address.setText(data.getAddress());
        holder.city.setText(data.getCity());
        holder.postalZip.setText(data.getPostalZip());
        holder.rent.setText(data.getRent());
        Glide.with(context).load(data.getImage()).apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Contents of an item in the DataFragment
        private final ImageView imageView;
        private final TextView address;
        private final TextView city;
        private final TextView postalZip;
        private final TextView rent;
        private final CheckBox cbHeart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.database_image_view);
            address = itemView.findViewById(R.id.database_address);
            city = itemView.findViewById(R.id.database_city);
            postalZip = itemView.findViewById(R.id.databse_postal);
            rent = itemView.findViewById(R.id.database_rent);
            cbHeart = itemView.findViewById(R.id.cb_heart);

            itemView.setOnClickListener(this);
            checkBox();
        }

        public ImageView getImageView() {
            return imageView;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAdapterPosition());
        }

        /**
         * When the heart icon is unchecked,
         * remove the item from the Wishlist Page
         *
         * When the heart icon is unchecked,
         * add the item to the Wishlist Page
         * @author Devanshi Dhall
         */
        public void checkBox() {
            User user = User.getInstance();
            cbHeart.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if (user.getUserState() instanceof LoginState) {
                        addDataToFirebase(list.get(getAdapterPosition()), context);
                    } else {
                        cbHeart.setChecked(false);
                        Toast.makeText(context, "Login to add posts to Wishlist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Adds data to firebase
     * @param data the data to be added
     * @param context context of the class the method is being called from
     * @author Avani Dhaliwal
     */
    public static void addDataToFirebase(Data data, Context context) {
        String user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        databaseReference = firebaseDatabase.getReference("Bookmarks/" + user + "/" + data.getId());
        databaseReference.setValue(data).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Post saved to Wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to save item to Wishlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Removes data from firebase
     * @param id the id of the data to be deleted
     * @param context context of the class the method is being called from
     * @author Avani Dhaliwal
     */
    public static void deleteDataFromFirebase(String id, Context context) {
        String user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        databaseReference = firebaseDatabase.getReference("Bookmarks/" + user + "/" + id);
        databaseReference.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Post removed from Wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to remove post to Wishlist ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }

}

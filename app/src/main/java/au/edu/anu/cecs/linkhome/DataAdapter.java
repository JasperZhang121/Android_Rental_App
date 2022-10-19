package au.edu.anu.cecs.linkhome;

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

/**
 * @author Nihar Meshram, Hao Zhang
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private final ItemClickListener listener;
    private static Context context;
    private final ArrayList<Data> list;
    private static DatabaseReference databaseReference;
    private static FirebaseDatabase firebaseDatabase;

    public DataAdapter(Context context, ArrayList<Data> list, ItemClickListener listener) {
        DataAdapter.context = context;
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
        Data user = list.get(position);
        holder.address.setText(user.getAddress());
        holder.city.setText(user.getCity());
        holder.postalZip.setText(user.getPostalZip());
        holder.rent.setText(user.getRent());

        Glide.with(context).load(user.getImage()).apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView address;
        TextView city;
        TextView postalZip;
        TextView rent;
        CheckBox cbHeart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.DatabaseimageView);
            address = itemView.findViewById(R.id.Databaseaddress);
            city = itemView.findViewById(R.id.Databasecity);
            postalZip = itemView.findViewById(R.id.DatabasepostalZip);
            rent = itemView.findViewById(R.id.Databaserent);
            cbHeart = itemView.findViewById(R.id.cbHeart);

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

        public void checkBox() {
            cbHeart.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    addDataToFirebase(list.get(getAdapterPosition()));
                } else {
                    deleteDataFromFirebase(list.get(getAdapterPosition()).getId());
                }
            });
        }
    }

    /**
     * Add the data of item to firebase
     * @param data of Data
     * @author Avani Dhaliwal
     */
    public static void addDataToFirebase(Data data) {
        String user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        System.out.println(user);
        databaseReference = firebaseDatabase.getReference("Bookmarks/" + user + "/" + data.getId());
        databaseReference.setValue(data).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Item added to wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to add item to wishlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Delete the data of item to firebase
     * @param id of String
     * @author Avani Dhaliwal
     */

    public static void deleteDataFromFirebase(String id) {
        String user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        databaseReference = firebaseDatabase.getReference("Bookmarks/" + user + "/" + id);
        databaseReference.removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "Item Removed from wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to delete item to wishlist ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }

}

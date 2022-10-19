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

        private final ImageView imageView;
        private final TextView address;
        private final TextView city;
        private final TextView postalZip;
        private final TextView rent;
        private final CheckBox cbHeart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.DatabaseImageView);
            address = itemView.findViewById(R.id.DatabaseAddress);
            city = itemView.findViewById(R.id.DatabaseCity);
            postalZip = itemView.findViewById(R.id.DatabasePostalZip);
            rent = itemView.findViewById(R.id.DatabaseRent);
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
            User user = User.getInstance();
            cbHeart.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    if(user.getUserState() instanceof LoginState){
                        addDataToFirebase(list.get(getAdapterPosition()), context);
                    } else {
                        cbHeart.setChecked(false);
                        Toast.makeText(context, "Login to add posts to Wishlist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

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

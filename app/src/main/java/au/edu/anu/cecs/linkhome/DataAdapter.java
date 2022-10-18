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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private final ItemClickListener listener;
    private final Context context;
    private final ArrayList<Data> list;
    private final ArrayList<Integer> listImages;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    public DataAdapter(Context context, ArrayList<Data> list, ArrayList<Integer> listImages, ItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listImages = listImages;
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

        Glide.with(context).load("https://picsum.photos/id/" + listImages.get(position) + "/300/200").apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(holder.getImageView());

        user.setImage("https://picsum.photos/id/" + listImages.get(position) + "/300/200");
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
                    addDataToFirebase(address.getText().toString(),
                            city.getText().toString(),
                            postalZip.getText().toString(),
                            rent.getText().toString(),
                            getAdapterPosition());
                } else {
                    deleteDataFromFirebase(getAdapterPosition());
                }
            });
        }
    }


    private void addDataToFirebase(String address, String city, String postalZip, String rent, int id) {
        Data data = new Data(address, city, postalZip, rent);

        databaseReference = firebaseDatabase.getReference("Bookmarks/" + id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(context, "Item added to wishlist", Toast.LENGTH_SHORT).show();
                databaseReference.setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Failed to add item to wishlist" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDataFromFirebase(int id) {
        databaseReference = firebaseDatabase.getReference("Bookmarks");
        databaseReference.child(Integer.toString(id)).removeValue().addOnCompleteListener(task -> {
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

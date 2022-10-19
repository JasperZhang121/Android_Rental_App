package au.edu.anu.cecs.linkhome.homePage.bookmarks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

<<<<<<< HEAD:app/src/main/java/au/edu/anu/cecs/linkhome/BookmarkAdapter.java
/**
 * @author Avani Dhaliwal
 */
=======
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.homePage.posts.Data;
import au.edu.anu.cecs.linkhome.homePage.posts.DataAdapter;

>>>>>>> origin/main:app/src/main/java/au/edu/anu/cecs/linkhome/homePage/bookmarks/BookmarkAdapter.java
public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.MyViewHolder> {
    private final BookmarkAdapter.ItemClickListener listener;
    private final Context context;
    private final ArrayList<Data> list;

    public BookmarkAdapter(Context context, ArrayList<Data> list, ItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
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
            cbHeart.setChecked(true);
            checkBox();
        }

        /**
         * @author Devanshi Dhall
         */
        public void checkBox() {
            cbHeart.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (!isChecked) {
                    Data data = list.remove(getAdapterPosition());
                    DataAdapter.deleteDataFromFirebase(data.getId(), context);
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), list.size());
                }
            });
        }

        public ImageView getImageView() {
            return imageView;
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }
}

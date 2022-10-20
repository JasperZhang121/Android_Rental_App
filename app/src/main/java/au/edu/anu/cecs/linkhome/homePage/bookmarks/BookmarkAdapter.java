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

import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.homePage.posts.Data;
import au.edu.anu.cecs.linkhome.homePage.posts.DataAdapter;

/**
 * Adapter for BookmarkFragment class
 *
 * @author Avani Dhaliwal
 */
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

        //Contents of an item in the BookmarkFragment
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
            cbHeart.setChecked(true);
            checkBox();
        }

        /**
         * When the heart icon is unchecked,
         * remove the item from the Wishlist Page
         *
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

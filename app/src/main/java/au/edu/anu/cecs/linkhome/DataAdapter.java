package au.edu.anu.cecs.linkhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    Context context;

    ArrayList<Data> list;

    public DataAdapter(Context context, ArrayList<Data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data user = list.get(position);
        holder.address.setText(user.getAddress());
        holder.city.setText(user.getCity());
        holder.postalZip.setText(user.getPostalZip());
        holder.rent.setText(user.getRent());

        int max = 1000;
        int min = 0;
        int id = (int)(Math.random()*((max-min)+1)+min);
        Glide.with(context).load("https://picsum.photos/id/"+id+"/300/200").apply(new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true))
                .into(holder.getImageView());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;
        TextView address;
        TextView city;
        TextView postalZip;
        TextView rent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView =  itemView.findViewById(R.id.DatabaseimageView);

            this.address = itemView.findViewById(R.id.Databaseaddress);
            city = itemView.findViewById(R.id.Databasecity);
            postalZip = itemView.findViewById(R.id.DatabasepostalZip);
            rent = itemView.findViewById(R.id.Databaserent);
        }

        public ImageView getImageView(){return imageView;}
    }

}

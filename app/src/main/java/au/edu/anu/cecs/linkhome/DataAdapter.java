package au.edu.anu.cecs.linkhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView address;
        TextView city;
        TextView postalZip;
        TextView rent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.address = itemView.findViewById(R.id.Databaseaddress);
            city = itemView.findViewById(R.id.Databasecity);
            postalZip = itemView.findViewById(R.id.DatabasepostalZip);
            rent = itemView.findViewById(R.id.Databaserent);

            Data data = new Data(this.address.getText().toString(),
                    city.getText().toString(),
                    postalZip.getText().toString(),
                    Integer.parseInt(rent.getText().toString().substring(1)));
        }
    }

}

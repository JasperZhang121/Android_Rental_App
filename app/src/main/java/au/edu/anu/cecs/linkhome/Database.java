package au.edu.anu.cecs.linkhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import au.edu.anu.cecs.linkhome.AVL.AVLTree;

public class Database extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    DataAdapter DataAdapter;
    HashMap<String, AVLTree<Data>> hashMapAVL;
    ArrayList<Data> list;
    ArrayList<Integer> listImages;
    DataAdapter.ItemClickListener listener;

//    public void onBackPressed(){
//        super.onBackPressed();
//        startActivity(new Intent(Database.this, MainActivity.class));
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.Database);
        database = FirebaseDatabase.getInstance().getReference("Users");
        System.out.println("Just Checking");
        hashMapAVL = new HashMap<String, AVLTree<Data>>();
        list = new ArrayList<>();
        listImages = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setOnClickListener();

        DataAdapter = new DataAdapter(this, list, listImages, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(DataAdapter);

        database.addValueEventListener(new ValueEventListener() {
            /**
             * Read data from the JSON file in firebase and create new objects of type Data
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Data data = dataSnapshot.getValue(Data.class);
                    //Adds the data to an AVL tree according to its city.
                    assert data != null;
                    if(hashMapAVL.containsKey(data.getCity())){
                        Objects.requireNonNull(hashMapAVL.get(data.getCity())).insert(data);
                    } else {
                        hashMapAVL.put(data.getCity(), new AVLTree<>(data));
                    }

                    list.add(data);
                    int max = 1000;
                    int min = 0;
                    listImages.add((int)(Math.random()*((max-min)+1)+min));
                }
                System.out.println("LIST: " + list);
                System.out.println("HASH: " + hashMapAVL.keySet());
                DataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }

    public void setOnClickListener(){
        listener = new DataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailedPage.class);
                intent.putExtra("city", list.get(position).getCity());
                intent.putExtra("address", list.get(position).getAddress());
                intent.putExtra("postal", list.get(position).getPostalZip());
                intent.putExtra("rent", list.get(position).getRent());
                intent.putExtra("image", list.get(position).getImage());
                startActivity(intent);
            }
        };
    }
}
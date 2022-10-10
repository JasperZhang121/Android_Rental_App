package au.edu.anu.cecs.linkhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import au.edu.anu.cecs.linkhome.AVL.AVLTree;

public class Database extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    DataAdapter DataAdapter;
    HashMap<String, AVLTree<Data>> hashMapAVL;
    ArrayList<Data> list;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter = new DataAdapter(this,list);
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
}
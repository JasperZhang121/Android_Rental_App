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

public class Database extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    DataAdapter DataAdapter;
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
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataAdapter = new DataAdapter(this,list);
        recyclerView.setHasFixedSize(true);
//      recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        list = new ArrayList<>();
//        DataAdapter = new DataAdapter(this,list);
        recyclerView.setAdapter(DataAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Data user = dataSnapshot.getValue(Data.class);
                    list.add(user);
                    System.out.println("LIST: " + list);
                }

                DataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
    }
}
package au.edu.anu.cecs.linkhome.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import au.edu.anu.cecs.linkhome.BookmarkAdapter;
import au.edu.anu.cecs.linkhome.Data;
import au.edu.anu.cecs.linkhome.R;

public class BookmarkFragment extends Fragment  {
    RecyclerView recyclerView;
    DatabaseReference database;
    au.edu.anu.cecs.linkhome.BookmarkAdapter BookmarkAdapter;
    ArrayList<Data> list;
    ArrayList<Integer> listImages;
    au.edu.anu.cecs.linkhome.BookmarkAdapter.ItemClickListener listener;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.bookmark_fragment, container, false);
        recyclerView = root.findViewById(R.id.rv_bookmark);
        database = FirebaseDatabase.getInstance().getReference("Bookmarks");
        list = new ArrayList<>();
        listImages = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        BookmarkAdapter = new BookmarkAdapter(getContext(), list, listImages, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(BookmarkAdapter);

        setHasOptionsMenu(true);

        database.addValueEventListener(new ValueEventListener() {
            /**
             * Read data from the JSON file in firebase and create new objects of type Data
             * @param snapshot of the Data
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Data data = dataSnapshot.getValue(Data.class);

                    list.add(data);
                    int max = 1000;
                    int min = 0;
                    listImages.add((int)(Math.random()*((max-min)+1)+min));
                }
                System.out.println("LIST: " + list);

                BookmarkAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id==R.id.Sort1){
            Collections.sort(list, (o1, o2) -> o1.getRent().compareTo(o2.getRent()));
            BookmarkAdapter.notifyDataSetChanged();
        } else if (id==R.id.Sort2){
            Collections.sort(list, (o1, o2) -> o2.getRent().compareTo(o1.getRent()));
            BookmarkAdapter.notifyDataSetChanged();
        }
        return true;
    }

    public void setOnClickListener(){
        listener = (v, position) -> {
            Intent intent = new Intent(getContext(), DetailedPage.class);
            intent.putExtra("city", list.get(position).getCity());
            intent.putExtra("address", list.get(position).getAddress());
            intent.putExtra("postal", list.get(position).getPostalZip());
            intent.putExtra("rent", list.get(position).getRent());
            intent.putExtra("image", list.get(position).getImage());
            startActivity(intent);
        };
    }
}

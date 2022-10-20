package au.edu.anu.cecs.linkhome.homePage.posts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import au.edu.anu.cecs.linkhome.homePage.HomePageFragment;
import au.edu.anu.cecs.linkhome.avl.AVLTree;
import au.edu.anu.cecs.linkhome.R;

/**
 * Displays all items of type Data stored in Firebase
 *
 * @author Hao Zhang, Nihar Meshram
 */
public class DatabaseFragment extends HomePageFragment {
    private DataAdapter DataAdapter;
    private DataAdapter.ItemClickListener listener;
    private RecyclerView recyclerView;
    private HashMap<String, AVLTree<Data>> hashMapAVL;
    private ArrayList<Data> currentPosts;
    private ArrayList<Data> allPosts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_database, container, false);
        setOnClickListener();
        setHasOptionsMenu(true);

        //Stores all the data in AVL trees. Each city (key) has a different AVL tree (value)
        hashMapAVL = new HashMap<>();

        //Stores all the Data that is currently being displayed on the UI
        currentPosts = new ArrayList<>();

        //Setting up the recyclerView
        recyclerView = root.findViewById(R.id.database_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DataAdapter = new DataAdapter(getContext(), currentPosts, listener);
        recyclerView.setAdapter(DataAdapter);
        recyclerView.setHasFixedSize(true);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
        database.addValueEventListener(new ValueEventListener() {
            /**
             * Read data from the JSON file in firebase and create new objects of type Data
             * @param snapshot of the Data
             * @author Avani Dhaliwal, Hao Zhang, Nihar Meshram
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Data data = dataSnapshot.getValue(Data.class);
                    currentPosts.add(data);

                    //Setting data id and image
                    int max = 1000;
                    int min = 0;
                    assert data != null;
                    data.setImage("https://picsum.photos/id/" + (int) (Math.random() * ((max - min) + 1) + min) + "/300/200");
                    data.setId(Integer.toString(i));

                    //The first post is always set to scam, so that it is easy for the marker to find and test this functionality.
                    if (i == 0)
                        data.setIsScam("true");

                        //Randomly make some posts scam.
                    else if (Math.random() < 0.05) {
                        data.setIsScam("true");
                    } else {
                        data.setIsScam("false");
                    }

                    i++;

                    //Adds the data to an AVL tree according to its city.
                    String city = data.getCity().toLowerCase();
                    if (hashMapAVL.containsKey(city)) {
                        Objects.requireNonNull(hashMapAVL.get(city)).insert(data);
                    } else {
                        hashMapAVL.put(city, new AVLTree<>(data));
                    }

                    allPosts = new ArrayList<>(currentPosts);
                }
                DataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return root;
    }

    /**
     * Search bar
     * Filters the data displayed according to the user's search query
     *
     * @author Avani Dhaliwal, Devanshi Dhall
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter_menu, menu);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Try parsing the query
                try {
                    currentPosts = new ArrayList<>(filterPosts(query, hashMapAVL));
                    DataAdapter = new DataAdapter(getContext(), currentPosts, listener);
                    recyclerView.setAdapter(DataAdapter);
                    return true;
                }
                // If the user uses wrong grammar, then inform user
                catch (Exception e) {
                    Toast.makeText(getContext(), "Invalid Search", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentPosts = allPosts;
                DataAdapter = new DataAdapter(getContext(), currentPosts, listener);
                recyclerView.setAdapter(DataAdapter);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * To sort the records based on ascending or descending order
     *
     * @param item of the MenuItem
     * @return boolean
     * @author Hao Zhang
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        RecyclerView.Adapter<DataAdapter.MyViewHolder> DataAdapter;
        if (id == R.id.Sort1) {
            Collections.sort(currentPosts, Data::compareTo);
            DataAdapter = new DataAdapter(getContext(), currentPosts, listener);
            recyclerView.setAdapter(DataAdapter);
        } else if (id == R.id.Sort2) {
            Collections.sort(currentPosts, (o1, o2) -> o2.compareTo(o1));
            DataAdapter = new DataAdapter(getContext(), currentPosts, listener);
            recyclerView.setAdapter(DataAdapter);
        }
        return true;
    }

    /**
     * Called when an item is clicked
     * Starts the detailed page activity
     * @author Avani Dhaliwal
     */
    public void setOnClickListener() {
        listener = (v, position) -> goToDetailedPage(getContext(), currentPosts, position);
    }
}

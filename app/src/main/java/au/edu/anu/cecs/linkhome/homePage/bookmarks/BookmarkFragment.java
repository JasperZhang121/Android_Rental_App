package au.edu.anu.cecs.linkhome.homePage.bookmarks;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.avl.AVLTree;
import au.edu.anu.cecs.linkhome.homePage.posts.Data;

/**
 * Displays all items of type Data that a particular user has saved to their from Firebase.
 *
 * @author Avani Dhaliwal
 */
public class BookmarkFragment extends HomePageFragment {
    private BookmarkAdapter BookmarkAdapter;
    private BookmarkAdapter.ItemClickListener listener;
    private RecyclerView recyclerView;
    private HashMap<String, AVLTree<Data>> hashMapAVL;
    private ArrayList<Data> currentPosts;
    private ArrayList<Data> allPosts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.bookmark_fragment, container, false);
        setOnClickListener();
        setHasOptionsMenu(true);

        //Stores all the data in AVL trees. Each city (key) has a different AVL tree (value)
        hashMapAVL = new HashMap<>();

        //Stores all the Data that the user has bookmarked from firebase.
        currentPosts = new ArrayList<>();

        //Setting up the recyclerView
        recyclerView = root.findViewById(R.id.rv_bookmark);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BookmarkAdapter = new BookmarkAdapter(getContext(), currentPosts, listener);
        recyclerView.setAdapter(BookmarkAdapter);
        recyclerView.setHasFixedSize(true);

        String user = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Bookmarks/" + user);
        database.addValueEventListener(new ValueEventListener() {
            /**
             * Read data from the JSON file in firebase and create new objects of type Data
             * @param snapshot of the Data
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Data data = dataSnapshot.getValue(Data.class);
                    currentPosts.add(data);


                    //Adds the data to an AVL tree according to its city.
                    assert data != null;
                    String city = data.getCity().toLowerCase();
                    if (hashMapAVL.containsKey(city)) {
                        Objects.requireNonNull(hashMapAVL.get(city)).insert(data);
                    } else {
                        hashMapAVL.put(city, new AVLTree<>(data));
                    }

                    allPosts = new ArrayList<>(currentPosts);
                }
                BookmarkAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return root;
    }

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
                try {
                    currentPosts = new ArrayList<>(filterPosts(query, hashMapAVL));
                    BookmarkAdapter = new BookmarkAdapter(getContext(), currentPosts, listener);
                    recyclerView.setAdapter(BookmarkAdapter);
                    return true;
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Invalid Search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentPosts = allPosts;
                BookmarkAdapter = new BookmarkAdapter(getContext(), currentPosts, listener);
                recyclerView.setAdapter(BookmarkAdapter);
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
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Sort1) {
            Collections.sort(currentPosts, Data::compareTo);
            BookmarkAdapter = new BookmarkAdapter(getContext(), currentPosts, listener);
            recyclerView.setAdapter(BookmarkAdapter);
        } else if (id == R.id.Sort2) {
            Collections.sort(currentPosts, (o1, o2) -> o2.compareTo(o1));
            BookmarkAdapter = new BookmarkAdapter(getContext(), currentPosts, listener);
            recyclerView.setAdapter(BookmarkAdapter);
        }
        return true;
    }

    private void setOnClickListener() {
        listener = (v, position) -> goToDetailedPage(getContext(), currentPosts, position);
    }
}

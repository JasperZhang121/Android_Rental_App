package au.edu.anu.cecs.linkhome.homePage.posts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import java.util.HashSet;
import java.util.Objects;

import au.edu.anu.cecs.linkhome.avl.AVLTree;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.AndExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.EqualExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.Exp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LessExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.MoreExp;
import au.edu.anu.cecs.linkhome.tokenizer.Parser;
import au.edu.anu.cecs.linkhome.tokenizer.Tokenizer;

/**
 * @author Avani Dhaliwal
 */
public class DatabaseFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference database;
    au.edu.anu.cecs.linkhome.homePage.posts.DataAdapter DataAdapter;
    HashMap<String, AVLTree<Data>> hashMapAVL;
    ArrayList<Data> list;
    DataAdapter.ItemClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_database, container, false);
        recyclerView = root.findViewById(R.id.Database);
        database = FirebaseDatabase.getInstance().getReference("Users");
        hashMapAVL = new HashMap<>();
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setOnClickListener();
        DataAdapter = new DataAdapter(getContext(), list, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(DataAdapter);

        setHasOptionsMenu(true);

        database.addValueEventListener(new ValueEventListener() {
            /**
             * Read data from the JSON file in firebase and create new objects of type Data
             * @param snapshot of the Data
             */
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Data data = dataSnapshot.getValue(Data.class);
                    //Adds the data to an AVL tree according to its city.
                    assert data != null;
                    if (hashMapAVL.containsKey(data.getCity().toLowerCase())) {
                        Objects.requireNonNull(hashMapAVL.get(data.getCity().toLowerCase())).insert(data);
                    } else {
                        hashMapAVL.put(data.getCity().toLowerCase(), new AVLTree<>(data));
                    }

                    int max = 1000;
                    int min = 0;
                    data.setImage("https://picsum.photos/id/" + (int) (Math.random() * ((max - min) + 1) + min) + "/300/200");
                    data.setId(Integer.toString(i));
                    i++;

                    if(i == 0){
                        data.setIsScam("true");
                    }

                    list.add(data);
                }
                DataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        return root;
    }

    private ArrayList<Object> parsedList = new ArrayList<>();

    /**
     * @author Avani Dhaliwal, Devanshi Dhall
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    Tokenizer tokenizer = new Tokenizer(query);
                    Parser parser = new Parser(tokenizer);
                    parser.parseExp();

                    parsedList = parser.getFinalList();
                    HashSet<Data> filteredList = new HashSet<>();

                    boolean isAnd = false;
                    for (int i = 0; i < parsedList.size() - 1; i++) {
                        Object object = parsedList.get(i);
                        Object object2 = parsedList.get(i + 1);

                        if (isAnd) {
                            if ((object instanceof LessExp || object instanceof MoreExp) && object2 instanceof Integer) {
                                ArrayList<Data> copy = new ArrayList<>(filteredList);
                                Data data = new Data("", "", "", "$" + object2);
                                for (Data d : copy) {
                                    if (object instanceof LessExp && d.compareTo(data) > 0) {
                                        filteredList.remove(d);
                                    }
                                    if (object instanceof MoreExp && d.compareTo(data) < 0) {
                                        filteredList.remove(d);
                                    }
                                }
                            }
                            else if (object instanceof EqualExp && object2 instanceof String) {
                                ArrayList<Data> copy = new ArrayList<>(filteredList);
                                for(Data d : copy){
                                    if(!d.getCity().equals(object2)){
                                        filteredList.remove(d);
                                    }
                                }
                                filteredList.addAll(filterByCity((String) object2));
                            }
                            isAnd = false;
                        } else if (object instanceof EqualExp && object2 instanceof String) {
                            filteredList.addAll(filterByCity((String) object2));
                        } else if ((object instanceof LessExp || object instanceof MoreExp) && object2 instanceof Integer) {
                            filteredList.addAll(filterByRent((Exp) object, object2));
                        }

                        if (object instanceof AndExp) {
                            isAnd = true;
                        }
                    }

                    if(filteredList.isEmpty()){
                        Toast.makeText(getContext(), "No posts related to the search found", Toast.LENGTH_SHORT).show();
                    }

                    allPosts = new ArrayList<>(list);
                    list = new ArrayList<>(filteredList);
                    DataAdapter = new DataAdapter(getContext(), new ArrayList<>(filteredList), listener);
                    recyclerView.setAdapter(DataAdapter);
                    return true;
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Invalid Search", Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list = allPosts;
                DataAdapter = new DataAdapter(getContext(), list, listener);
                recyclerView.setAdapter(DataAdapter);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    ArrayList<Data> allPosts = new ArrayList<>();
    /**
     * @author Avani Dhaliwal, Devanshi Dhall
     * @param exp of Exp
     * @param value of Object
     * @return ArrayList<Data>
     */
    private ArrayList<Data> filterByRent(Exp exp, Object value) {
        ArrayList<Data> result = new ArrayList<>();
        Data data = new Data("", "", "", "$" + value);
        for (AVLTree<Data> tree : hashMapAVL.values()) {
            AVLTree<Data> filteredTree = tree.filterData(tree, exp, data);
            if (filteredTree != null) {
                result.addAll(filteredTree.treeToListInOrder(filteredTree));
            }
        }
        return result;
    }

    private ArrayList<Data> filterByCity(String city) {
        ArrayList<Data> result = new ArrayList<>();
        city = city.toLowerCase();
        AVLTree<Data> hashTree = hashMapAVL.get(city);
        if (hashTree != null) {
            result = hashTree.treeToListInOrder(hashTree);
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Sort1) {
            Collections.sort(list, Data::compareTo);
            DataAdapter = new DataAdapter(getContext(), list, listener);
            recyclerView.setAdapter(DataAdapter);
        } else if (id == R.id.Sort2) {
            Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
            DataAdapter = new DataAdapter(getContext(), list, listener);
            recyclerView.setAdapter(DataAdapter);
        }
        return true;
    }

    public void setOnClickListener() {
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

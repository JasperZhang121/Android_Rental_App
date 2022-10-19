package au.edu.anu.cecs.linkhome.HomePage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import au.edu.anu.cecs.linkhome.AVL.AVLTree;
import au.edu.anu.cecs.linkhome.Data;
import au.edu.anu.cecs.linkhome.DataAdapter;
import au.edu.anu.cecs.linkhome.R;
import au.edu.anu.cecs.linkhome.Tokenizer.AndExp;
import au.edu.anu.cecs.linkhome.Tokenizer.EqualExp;
import au.edu.anu.cecs.linkhome.Tokenizer.Exp;
import au.edu.anu.cecs.linkhome.Tokenizer.LessExp;
import au.edu.anu.cecs.linkhome.Tokenizer.MoreExp;
import au.edu.anu.cecs.linkhome.Tokenizer.OrExp;
import au.edu.anu.cecs.linkhome.Tokenizer.Parser;
import au.edu.anu.cecs.linkhome.Tokenizer.Tokenizer;

public class DatabaseFragment extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference database;
    au.edu.anu.cecs.linkhome.DataAdapter DataAdapter;
    HashMap<String, AVLTree<Data>> hashMapAVL;
    ArrayList<Data> list;
    ArrayList<Integer> listImages;
    DataAdapter.ItemClickListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_database, container, false);
        recyclerView = root.findViewById(R.id.Database);
        database = FirebaseDatabase.getInstance().getReference("Users");
        hashMapAVL = new HashMap<>();
        list = new ArrayList<>();
        listImages = new ArrayList<>();
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
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Data data = dataSnapshot.getValue(Data.class);
                    //Adds the data to an AVL tree according to its city.
                    assert data != null;
                    if (hashMapAVL.containsKey(data.getCity())) {
                        Objects.requireNonNull(hashMapAVL.get(data.getCity())).insert(data);
                    } else {
                        hashMapAVL.put(data.getCity(), new AVLTree<>(data));
                    }

                    list.add(data);
                    int max = 1000;
                    int min = 0;
                    listImages.add((int) (Math.random() * ((max - min) + 1) + min));
                }

                DataAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        return root;
    }

    ArrayList<Object> finalList = new ArrayList<>();
    HashSet<Data> hashSet = new HashSet<>();

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        inflater.inflate(R.menu.search,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Tokenizer tokenizer = new Tokenizer(query);
                Parser parser = new Parser(tokenizer);
                parser.parseExp();
                finalList = parser.getFinalList();
                if(finalList.get(0) instanceof EqualExp && finalList.get(1) instanceof String){
                    AVLTree<Data> hashTree = hashMapAVL.get( (String) finalList.get(1));
                    if(hashTree != null){
                        if(finalList.size()>2){
                            if(finalList.get(2) instanceof OrExp){
                                if(finalList.get(3) instanceof LessExp || finalList.get(3) instanceof MoreExp){
                                    if(finalList.get(4) instanceof Integer){
                                        Data data = new Data("", "", "",  "$"+finalList.get(4));
                                        for (AVLTree<Data> value : hashMapAVL.values()) {
                                            AVLTree<Data> filteredTree = value.filterData(value, (Exp) finalList.get(3), data);
                                            if(filteredTree!=null) {
                                                hashSet.addAll(filteredTree.treeToListInOrder(filteredTree));
                                            }
                                        }
                                    }
                                }
                            }
                            // && Operator
                            if(finalList.get(2) instanceof AndExp){
                                if(finalList.get(3) instanceof LessExp || finalList.get(3) instanceof MoreExp){
                                    if(finalList.get(4) instanceof Integer){
                                        Data data = new Data("","","","$"+(finalList.get(4)));
                                        AVLTree<Data> filteredTree = hashTree.filterData(hashTree, (Exp) finalList.get(3), data);
                                        if(filteredTree!=null) {
                                            hashSet.addAll(filteredTree.treeToListInOrder(filteredTree));
                                        }
                                    }
                                }
                            }
                        }
                        hashSet.addAll(hashTree.treeToListInOrder(hashTree));
                        ArrayList<Data> hashSetList = new ArrayList<>();
                        hashSetList.addAll(hashSet);

                        System.out.println("HASHSET: " + hashSet);
                        DataAdapter dataAdapter2 = new DataAdapter(getContext(), hashSetList, listener);
                        recyclerView.setAdapter(dataAdapter2);
                    } else {
                        Toast.makeText(getContext(), "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                filterByRent(0,1);
                return true;
            }

            public void filterByRent(int i, int j){
                if(finalList.get(i) instanceof LessExp
                        || finalList.get(i) instanceof MoreExp
                        || finalList.get(i) instanceof EqualExp){
                    if(finalList.get(j) instanceof Integer){
                        Data data = new Data("", "", "",  "$"+finalList.get(j));
                        ArrayList<Data> result = new ArrayList<>();
                        for (AVLTree<Data> value : hashMapAVL.values()) {
                            AVLTree<Data> filteredTree = value.filterData(value, (Exp) finalList.get(i), data);
                            if(filteredTree!=null) {
                                result.addAll(filteredTree.treeToListInOrder(filteredTree));
                            }
                        }
                        DataAdapter dataAdapter2 = new DataAdapter(getContext(), result, listener);
                        recyclerView.setAdapter(dataAdapter2);
                    }
                }
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerView.setAdapter(DataAdapter);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Sort1) {
            Collections.sort(list, Data::compareTo);
            DataAdapter.notifyDataSetChanged();
        } else if (id == R.id.Sort2) {
            Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
            DataAdapter.notifyDataSetChanged();
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
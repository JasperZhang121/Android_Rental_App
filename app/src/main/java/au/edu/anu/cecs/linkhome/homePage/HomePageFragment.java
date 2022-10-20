package au.edu.anu.cecs.linkhome.homePage;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import au.edu.anu.cecs.linkhome.avl.AVLTree;
import au.edu.anu.cecs.linkhome.homePage.posts.Data;
import au.edu.anu.cecs.linkhome.homePage.posts.DetailedPage;
import au.edu.anu.cecs.linkhome.tokenizer.Parser;
import au.edu.anu.cecs.linkhome.tokenizer.Tokenizer;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.AndExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.EqualExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.Exp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.LessExp;
import au.edu.anu.cecs.linkhome.tokenizer.expressions.MoreExp;

/**
 * Parent class of BookmarkFragment and DatabaseFragment
 *
 * @author Avani Dhaliwal
 */
public abstract class HomePageFragment extends Fragment {
    /**
     * @param exp  exp is an <operator>
     * @param rent the rent to filter the data by
     * @return the AVL tree corresponding to the given exp and value
     * @author Avani Dhaliwal, Devanshi Dhall
     */
    public ArrayList<Data> filterByRent(Exp exp, Object rent, HashMap<String, AVLTree<Data>> hashMapAVL) {
        ArrayList<Data> result = new ArrayList<>();
        Data data = new Data("", "", "", "$" + rent);
        for (AVLTree<Data> tree : hashMapAVL.values()) {
            AVLTree<Data> filteredTree = tree.filterData(tree, exp, data);
            if (filteredTree != null) {
                result.addAll(filteredTree.treeToListInOrder(filteredTree));
            }
        }
        return result;
    }

    /**
     * @param city       the city queried by the user
     * @param hashMapAVL a hashmap of AVL trees containing all the data
     * @return the AVL tree corresponding to the given city
     * @author Avani Dhaliwal, Devanshi Dhall
     */
    public ArrayList<Data> filterByCity(String city, HashMap<String, AVLTree<Data>> hashMapAVL) {
        ArrayList<Data> result = new ArrayList<>();
        city = city.toLowerCase();
        AVLTree<Data> hashTree = hashMapAVL.get(city);
        if (hashTree != null) {
            result = hashTree.treeToListInOrder(hashTree);
        }
        return result;
    }

    /**
     * Starts the DetailedPage activity.
     *
     * @param context      either DatabaseFragment or BookmarkFragment
     * @param currentPosts posts on display
     * @param position     the post user clicked on
     * @author Avani Dhaliwal
     */
    public void goToDetailedPage(Context context, ArrayList<Data> currentPosts, int position) {
        Intent intent = new Intent(context, DetailedPage.class);
        intent.putExtra("city", currentPosts.get(position).getCity());
        intent.putExtra("address", currentPosts.get(position).getAddress());
        intent.putExtra("postal", currentPosts.get(position).getPostalZip());
        intent.putExtra("rent", currentPosts.get(position).getRent());
        intent.putExtra("image", currentPosts.get(position).getImage());
        intent.putExtra("isScam", currentPosts.get(position).getIsScam());
        startActivity(intent);
    }

    /**
     * Filters all the data according to the user's search
     *
     * @param query      the searched query
     * @param hashMapAVL a hashmap of AVL trees containing all the data
     * @return A set of posts that meet the query request.
     * @author Avani Dhaliwal, Devanshi Dhall
     */
    public HashSet<Data> filterPosts(String query, HashMap<String, AVLTree<Data>> hashMapAVL) {

        //Tokenize
        Tokenizer tokenizer = new Tokenizer(query);
        Parser parser = new Parser(tokenizer);
        parser.parseExp();

        ArrayList<Object> parsedList = parser.getFinalList();
        HashSet<Data> filteredList = new HashSet<>();

        boolean isAnd = false;

        for (int i = 0; i < parsedList.size() - 1; i++) {
            Object object = parsedList.get(i);
            Object object2 = parsedList.get(i + 1);

            //If the searched query includes an AND operator
            if (isAnd) {
                //and the searched query is rent, filter the data accordingly
                if ((object instanceof LessExp || object instanceof MoreExp) && object2 instanceof Integer) {
                    ArrayList<Data> copy = new ArrayList<>(filteredList);
                    Data data = new Data("", "", "", "$" + object2);
                    for (Data d : copy) {
                        // if searched query is rent<INT
                        if (object instanceof LessExp && d.compareTo(data) > 0) {
                            filteredList.remove(d);
                        }
                        // if searched query is rent>INT
                        if (object instanceof MoreExp && d.compareTo(data) < 0) {
                            filteredList.remove(d);
                        }
                    }
                }
                //and the searched query is city=LETTER, filter the data accordingly
                else if (object instanceof EqualExp && object2 instanceof String) {
                    ArrayList<Data> copy = new ArrayList<>(filteredList);
                    for (Data d : copy) {
                        if (!d.getCity().equals(object2)) {
                            filteredList.remove(d);
                        }
                    }
                    filteredList.addAll(filterByCity((String) object2, hashMapAVL));
                }
                isAnd = false;
            }
            //Else if the searched query is only city or an OR operator is queried
            else if (object instanceof EqualExp && object2 instanceof String) {
                filteredList.addAll(filterByCity((String) object2, hashMapAVL));
            }
            //Else if the searched query is only rent or an OR operator is queried
            else if ((object instanceof LessExp || object instanceof MoreExp) && object2 instanceof Integer) {
                filteredList.addAll(filterByRent((Exp) object, object2, hashMapAVL));
            }

            //If an AND operator is searched by the user,
            if (object instanceof AndExp) {
                isAnd = true;
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(getContext(), "No posts related to the search found", Toast.LENGTH_SHORT).show();
        }
        return filteredList;
    }
}

package au.edu.anu.cecs.linkhome.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class LoginAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> fragmentArrayList= new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();

    public LoginAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override

    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentArrayList.size();

    }

    public void addFragment(Fragment fragment, String Title){
        fragmentArrayList.add(fragment);
        fragmentTitle.add(Title);
    }

    @Nullable
    @Override

    public CharSequence getPageTitle(int position){
        return fragmentTitle.get(position);
    }
}



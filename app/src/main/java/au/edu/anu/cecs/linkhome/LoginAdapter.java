package au.edu.anu.cecs.linkhome;

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

//    private Context context;
//    int tabsCount;

//    public LoginAdapter(FragmentManager fm, Context context, int tabsCount) {
//        super(fm);
//        this.context = context;
//        this.tabsCount = tabsCount;
//    }

//    @Override
//    public int getCount() {
//        return tabsCount;
//    }
//
//
//    public Fragment getItem(int position) {
//        switch (position){
//            case 0:
//                System.out.println("login");
//                LoginTabFragment loginTabFragment = new LoginTabFragment();
//                return loginTabFragment;
//
//            case 1:
//                System.out.printf("sign-in");
//                SignUpTabFragment signUpTabFragment = new SignUpTabFragment();
//                return signUpTabFragment;
//            default:
//                System.out.printf("null");
//                return null;
//        }
//    }



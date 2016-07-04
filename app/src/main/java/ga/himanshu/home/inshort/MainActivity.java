package ga.himanshu.home.inshort;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import butterknife.ButterKnife;
import ga.himanshu.home.inshort.Utils.VerticalViewPager;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,SecondFragment.OnFragmentInteractionListener{

    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
   // @BindView(R.id.news_recycler_view)
    RecyclerView mRecyclerView;
    VerticalViewPager Pager2;
    PagerAdapter adapter;
    String[] articleTitle;
    String[] articleName;
    String[] articleDiscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Pager2=(VerticalViewPager)findViewById(R.id.pager);


// Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

// Binds the Adapter to the ViewPager
        Pager2.setAdapter(adapter);
        ButterKnife.bind(this);

//
//        MainFragment listContactsFragment= (MainFragment) getFragmentManager().findFragmentByTag("mainFragment");
//        if(listContactsFragment==null) {
//            listContactsFragment = MainFragment.newInstance("R", "S");
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.id_fragmentContainer, listContactsFragment, "list_Fragment");
//            fragmentTransaction.commit();
//        }



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return  MainFragment.newInstance("R","S",position);
                case 1:
                    return SecondFragment.newInstance("R","S",position);
                case 2:
                    return MainFragment.newInstance("R","S",position);
                case 3:
                    return SecondFragment.newInstance("R","S",position);
                default:
                    return MainFragment.newInstance("R","S",position);
            }
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Top free";
//                case 1:
//                    return "Top paid";
//                case 2:
//                    return "Recently trending";
//                case 3:
//                    return "Delhi";
//                default:
//                    return "Mumbai";
//            }
//        }

        @Override
        public int getCount() {
            return 50;
        }
    }
}

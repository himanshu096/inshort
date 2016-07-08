package ga.himanshu.home.inshort;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import ga.himanshu.home.inshort.Utils.ActivityAnimator;
import ga.himanshu.home.inshort.Utils.RunnableToHide;
import ga.himanshu.home.inshort.Utils.RunnableToShow;
import ga.himanshu.home.inshort.Utils.VerticalViewPager;
import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Stories;
import ga.himanshu.home.inshort.services.NotifyforSucces;
import ga.himanshu.home.inshort.view.CustomiseActionBar;

public class MainActivity extends ParentActivity implements MainFragment.OnFragmentInteractionListener
        , View.OnClickListener, NotifyforSucces {

    private CustomiseActionBar customiseActionBar;
    private MenuItem menuItem;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuItem = (MenuItem) findViewById(R.id.action_refresh);
        customiseActionBar = new CustomiseActionBar(getSupportActionBar());
        dialog = ProgressDialog.show(MainActivity.this, "",
                "Wait..Getting new stories for you", true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        runToHide = new RunnableToHide(getSupportActionBar());
        runToShow = new RunnableToShow(getSupportActionBar());
        pagerConfig();
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            total = savedInstanceState.getInt("total");
            stories = savedInstanceState.getParcelable("stories");
            Pager2.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            getCatagories(this);
            getFilteredStories(this);
        }
    }


    private void pagerConfig() {
        Pager2 = new VerticalViewPager(this, this);
        Pager2 = (VerticalViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getFilteredStories(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        menuItem = menu.findItem(R.id.action_refresh);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                menuItem.setActionView(R.layout.action_progress_bar);
                menuItem.expandActionView();
                getFilteredStories(this);
                break;

            case R.id.action_catagories:
                break;
            case android.R.id.home:
                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                try {
                    ActivityAnimator anim = new ActivityAnimator();
                    anim.getClass().getMethod("flipHorizontal" + "Animation", Activity.class).invoke(anim, this);
                } catch (Exception e) {
                    Toast.makeText(this, "An error occured " + e.toString(), Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Integer total) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("total", total);
        outState.putParcelable("stories", stories);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void notifyforCategories() {
    }

    @Override
    public void notifyforCategories(List<Category> categoryList) {

    }

    @Override
    public void notifyforStories() {
        if(firstTime==1) {
            Pager2.setAdapter(adapter);
            firstTime++;
        }

        adapter.notifyDataSetChanged();
        menuItem.collapseActionView();
        menuItem.setActionView(null);
    }

    @Override
    public void notifyforStories(List<Stories> storiesList) {
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                default:
                    dialog.dismiss();
                    return MainFragment.newInstance(stories.getData().get(position));

            }
        }

        @Override
        public int getCount() {
            return stories.getData().size();
        }
    }

}
package ga.himanshu.home.inshort;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

import ga.himanshu.home.inshort.Utils.CustomAdapter;
import ga.himanshu.home.inshort.Utils.RunnableToHide;
import ga.himanshu.home.inshort.Utils.RunnableToShow;
import ga.himanshu.home.inshort.Utils.VerticalViewPager;
import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Datum;
import ga.himanshu.home.inshort.model.Stories;
import ga.himanshu.home.inshort.services.Callback;
import ga.himanshu.home.inshort.services.NotifyforSucces;
import ga.himanshu.home.inshort.services.Service;

public class ParentActivity extends AppCompatActivity implements CustomAdapter.SelectCategories {


    protected static int total = 2;
    protected VerticalViewPager Pager2;
    protected PagerAdapter adapter;
    protected Stories stories;
    protected Datum datum;
    protected static List<Integer> list = new ArrayList<>();
    protected RunnableToHide runToHide;
    protected RunnableToShow runToShow;
    protected Handler mHandler = new Handler();
    protected boolean underProcessing = false;
    protected static Integer firstTime = 1;


    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected List<String> categories_name_List = new ArrayList<>();
    public static List<Category> categories_List = new ArrayList<>();
    protected CustomAdapter customAdapter;
    protected NotifyforSucces notifyforSucces;
    public static SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
    }

    protected void getCatagories(Activity activity) {
        notifyforSucces = (NotifyforSucces) activity;
        Service service = new Service();
        service.getCategory(new Callback<List<Category>>() {
            @Override
            public void onSuccess(List<Category> response) {
                categories_List.addAll(response);
                for (int i = 0; i < categories_List.size(); i++) {
                    categories_name_List.add(categories_List.get(i).getTitle());
                }
                notifyforSucces.notifyforCategories();
            }

            @Override
            public void onFailure(Error error) {
            }

            @Override
            public void onFailure(ga.himanshu.home.inshort.model.Error error) {
            }
        });
    }


    protected void getFilteredStories(Activity activity) {

        list.clear();
        for(int i=0;i<categories_List.size();i++){
            if(sparseBooleanArray.get(categories_List.get(i).getId())){
                list.add(categories_List.get(i).getId());
            }
        }

        Log.d("hemant","Lenght of List " + list.size());

        if (!underProcessing) {
            this.underProcessing = true;
            notifyforSucces = (NotifyforSucces) activity;
            Service service = new Service();
            service.getFilteredStories(list, new Callback<Stories>() {
                @Override
                public void onSuccess(Stories response) {
                    stories = response;
                    total = response.getTotal();
                    notifyforSucces.notifyforStories();
                    Log.d("hemant", "aagya Response "+response.getData().size());
                    underProcessing = false;
                }

                @Override
                public void onFailure(Error error) {
                }

                @Override
                public void onFailure(ga.himanshu.home.inshort.model.Error error) {
                }
            });
        }
    }


    @Override
    public void selectCategories_(int i, boolean value) {

        sparseBooleanArray.put(categories_List.get(i).getId(),value);
        Log.d("hemant", "Selected Position " + String.valueOf(i));
        Log.d("hemant", "valueis " + value);
        Log.d("hemant", "Selected Id is " + String.valueOf(categories_List.get(i).getId()));
        Log.d("hemant", "Length os Sp  " + String.valueOf(sparseBooleanArray.size()));

    }
}

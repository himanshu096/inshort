package ga.himanshu.home.inshort;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import ga.himanshu.home.inshort.Utils.CustomAdapter;
import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Stories;
import ga.himanshu.home.inshort.services.NotifyforSucces;

public class HomeActivity extends ParentActivity implements
        NotifyforSucces {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.id_RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        customAdapter = new CustomAdapter(this, categories_name_List,categories_List);
        recyclerView.setAdapter(customAdapter);
        if (categories_List.size() == 0) {
            getCatagories(this);
        } else {
            for (int i = 0; i < categories_List.size(); i++) {
                categories_name_List.add(categories_List.get(i).getTitle());
            }
            customAdapter.notifyDataSetChanged();
        }

        SharedPreferences sharedPref1 = getSharedPreferences("Access", Context.MODE_PRIVATE);

        for(int i=0;i<categories_List.size();i++){
            sparseBooleanArray.put(categories_List.get(i).getId(),
                    sharedPref1.getBoolean(String.valueOf(categories_List.get(i).getId())
                            ,sparseBooleanArray.get(categories_List.get(i).getId())));
        }

        for(int i=0;i<categories_List.size();i++){
            Log.d("hemant", String.valueOf(sparseBooleanArray.get(categories_List.get(i).getId())));
        }



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                SharedPreferences sharedPref = getSharedPreferences("Access", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                for(int i=0;i<categories_List.size();i++) {
                    String str=String.valueOf(categories_List.get(i).getId());
                    Boolean value=sparseBooleanArray.get(categories_List.get(i).getId());
                    editor.putBoolean(str,value );
                }
                editor.commit();

                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void notifyforCategories() {
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyforCategories(List<Category> categoryList) {

    }

    @Override
    public void notifyforStories() {

    }

    @Override
    public void notifyforStories(List<Stories> storiesList) {

    }
}

package ga.himanshu.home.inshort.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.List;

import ga.himanshu.home.inshort.ParentActivity;
import ga.himanshu.home.inshort.R;
import ga.himanshu.home.inshort.model.Category;

/**
 * Created by Hemant Saini on 07-07-2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    private SelectCategories selectCategories;
    private Activity activity;
    private List<String> cat_list=new ArrayList<>();
    private ParentActivity parentActivity=new ParentActivity();
    public SparseBooleanArray sparseBooleanArray=new SparseBooleanArray();
    private List<Category> category_List;


    public CustomAdapter (Activity activity_,List<String> list_string,List<Category> category_List_){
        this.activity=activity_;
        this.category_List=category_List_;
        selectCategories=(SelectCategories)activity;
        cat_list=list_string;
        SharedPreferences sharedPref1 = activity.getSharedPreferences("Access", Context.MODE_PRIVATE);

        for(int i=0;i<category_List.size();i++){
            sparseBooleanArray.put(category_List.get(i).getId(),
                    sharedPref1.getBoolean(String.valueOf(category_List.get(i).getId())
                            , sparseBooleanArray.get(category_List.get(i).getId())));
        }

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        Holder holder = new Holder(row);
        return holder;

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.checkedTextView.setText(cat_list.get(position));
        holder.checkedTextView.setChecked(sparseBooleanArray.get(category_List.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return cat_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CheckedTextView checkedTextView;
        public Holder(View itemView) {
            super(itemView);
            checkedTextView=(CheckedTextView)itemView.findViewById(R.id.id_checkedTv);
            checkedTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(checkedTextView.isChecked()){
                checkedTextView.setChecked(false);
                selectCategories.selectCategories_(getLayoutPosition(),false);
           }
            else{
                checkedTextView.setChecked(true);
                selectCategories.selectCategories_(getLayoutPosition(), true);
            }
        }
    }

    public interface SelectCategories{
        public void selectCategories_(int i,boolean value);
    }
}

package ga.himanshu.home.inshort.view;

import ga.himanshu.home.inshort.R;

/**
 * Created by Hemant Saini on 07-07-2016.
 */
public class CustomiseActionBar {

    android.support.v7.app.ActionBar actionBar;

    public CustomiseActionBar(android.support.v7.app.ActionBar actionBar_){

        this.actionBar=actionBar_;
        actionBar_.show();
        actionBar_.setDisplayHomeAsUpEnabled(true);
        actionBar_.setDisplayShowTitleEnabled(false);
        actionBar_.setHomeAsUpIndicator(R.drawable.ic_menu);

    }

    public void setVisiblity(Boolean value){
        if(value)
        actionBar.show();
        else{
            actionBar.hide();
        }
    }
}

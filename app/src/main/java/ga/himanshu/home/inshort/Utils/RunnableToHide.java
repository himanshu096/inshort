package ga.himanshu.home.inshort.Utils;

/**
 * Created by Hemant Saini on 07-07-2016.
 */
public class RunnableToHide implements Runnable {
    android.support.v7.app.ActionBar actionBar;
    public RunnableToHide(android.support.v7.app.ActionBar actionBar_){
        this.actionBar=actionBar_;
    }

    @Override
    public void run() {
        actionBar.hide();
    }
}

package ga.himanshu.home.inshort.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Hemant Saini on 04-07-2016.
 */
public class VerticalViewPager extends ViewPager {

    private Context context_;
    private Activity activity;
    private Runnable runToHide;
    private Runnable runToShow;
    private int motion_event=0;
    Handler mHandler = new Handler();

    private static android.support.v7.app.ActionBar actionBar;

    public VerticalViewPager(Context context,Activity activity_) {
        super(context);
        this.context_=context;
        this.activity=activity_;
        actionBar= ((AppCompatActivity)activity)
                .getSupportActionBar();
        init();
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context_=context;
        init();

        runToHide=new Runnable() {

            @Override
            public void run() {
                if(actionBar.isShowing())
                    actionBar.hide();
            }
        };

        runToShow=new Runnable() {

            @Override
            public void run() {
                if(!actionBar.isShowing())
                    actionBar.show();
            }
        };


    }

    private void init() {
        // The majority of the magic happens here
        setPageTransformer(true, new VerticalPageTransformer());
        // The easiest way to get rid of the overscroll drawing that happens on the left and right
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    private static class VerticalPageTransformer implements ViewPager.PageTransformer {
        private static float MIN_SCALE = 1.00f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);


            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                //view.setTranslationX(1);
                view.setScaleX(1);
                view.setScaleY(1);
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);
                view.setTranslationX(-1 * view.getWidth() * position);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                view.setTranslationX(-1 * view.getWidth() * position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }

        }
    }

    private MotionEvent swapXY(MotionEvent ev) {

        // Log.d("hemant", String.valueOf(ev));


//        if(motion_event==0){
//            if(actionBar.isShowing()){
//                actionBar.hide();
//            }
//            if(!actionBar.isShowing()){
//                actionBar.show();
//                mHandler.postDelayed(runToHide,10000);
//            }
//        }

        float width = getWidth();
        float height = getHeight();

        float newX = (ev.getY() / height) * width;
        float newY = (ev.getX() / width) * height;

        ev.setLocation(newX, newY);

        return ev;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean intercepted = super.onInterceptTouchEvent(swapXY(ev));
        swapXY(ev); // return touch coordinates to original reference frame for any child views
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //      actionBar.show();
        return super.onTouchEvent(swapXY(ev));
    }


}

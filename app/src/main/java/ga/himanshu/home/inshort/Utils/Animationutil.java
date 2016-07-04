package ga.himanshu.home.inshort.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Himanshu on 6/14/2016.
 */
public class Animationutil {

    public static void animate(RecyclerView.ViewHolder holder , boolean goesDown){


        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown==true ? 100 : -800, 0);
        animatorTranslateY.setDuration(1000);


        ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView,"translationX",0,0,-20,20,-5,5,0);
        animatorTranslateX.setDuration(1000);

        animatorSet.playTogether(animatorTranslateY);


        animatorSet.start();

    }
}

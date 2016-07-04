package ga.himanshu.home.inshort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;

/**
 * Created by Himanshu on 7/3/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private LayoutInflater inflater;
    private final static int FADE_DURATION = 1000;
    private int previousPosition = 0;


    public CustomAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_custom, parent
                , false);
        CustomViewHolder holder = new CustomViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.textViewHeading.setText(String.valueOf("Heading " + position));
//        setFadeAnimation(holder.itemView);

//        if (position > previousPosition) { // We are scrolling DOWN
//
//            Animationutil.animate(holder, true);
//
//        } else { // We are scrolling UP
//
//            Animationutil.animate(holder, false);
//
//
//        }
//
//        previousPosition = position;
//
//
//        final int currentPosition = position;
//
//
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

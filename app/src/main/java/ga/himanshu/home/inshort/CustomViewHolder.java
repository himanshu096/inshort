package ga.himanshu.home.inshort;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Himanshu on 7/3/2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView textViewHeading;
    TextView textViewNews;
    ImageView imageView;

    public CustomViewHolder(View itemView) {

        super(itemView);
        textViewHeading=(TextView)itemView.findViewById(R.id.id_Heading);
        textViewNews=(TextView)itemView.findViewById(R.id.id_news);
        imageView=(ImageView)itemView.findViewById(R.id.id_newsPic);
        itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

    }
}

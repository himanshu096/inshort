package ga.himanshu.home.inshort;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ga.himanshu.home.inshort.customwebtab.CustomTabActivityHelper;
import ga.himanshu.home.inshort.model.Datum;
import ga.himanshu.home.inshort.model.Stories;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    private OnFragmentInteractionListener mListener;
    private TextView textViewHeading;
    private TextView textViewNews;
    private ImageView imageView;
    private TextView textViewcreatedAt;
    private TextView webview;
    private Datum datum1;
    Runnable mRunnable;
    Runnable mRunnable2;
    boolean actionbarvisible = false;
    Handler mHandler = new Handler();
    private File imagePath;
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;




    public MainFragment() {

    }

    public MainFragment(Datum datum) {
        datum1 = datum;
    }

    public static MainFragment newInstance(Datum datum) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putParcelable("datum", datum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            datum1 = getArguments().getParcelable("datum");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textViewHeading = (TextView) view.findViewById(R.id.id_Heading);
        textViewNews = (TextView) view.findViewById(R.id.id_news);
        textViewcreatedAt = (TextView) view.findViewById(R.id.timestamp);
        webview = (TextView) view.findViewById(R.id.ReadMore);
        webview.setOnClickListener(this);

        imageView = (ImageView) view.findViewById(R.id.id_newsPic);
        if (datum1 != null) {
            try {
                setData();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                shareIt();
            }
        });

        return view;
    }

    public void onButtonPressed(Integer total) {
        if (mListener != null) {
            mListener.onFragmentInteraction(total);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        String url = datum1.getArticleLink();
        CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
        CustomTabActivityHelper.openCustomTab(getActivity(), customTabsIntent, Uri.parse(url),
                new CustomTabActivityHelper.CustomTabFallback() {
                    @Override
                    public void openUri(Activity activity, Uri uri) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (actionbarvisible == false) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            actionbarvisible = true;
            mHandler.postDelayed(mRunnable, 5000);

        } else {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            mHandler.postDelayed(mRunnable2, 5000);
            actionbarvisible = false;

        }
        return false;

    }


    public Bitmap takeScreenshot() {
        View rootView = getActivity().findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }

    }

    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("image/*");
        String shareBody = "Shared via himanshu's phone.Read full new at "+datum1.getArticleLink();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Got a News"+datum1.getTitle());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Integer total);
    }


    private void setData() throws ParseException, IOException {
        textViewHeading.setText(datum1.getTitle());
        textViewNews.setText(datum1.getDescription());
        Picasso.with(getActivity().getApplicationContext()).load(datum1.getThumbnailUrl())
                .fit().error(R.drawable.car2).into(imageView);
        String toParse = datum1.getCreatedAt(); // Results in "2-5-2012 20:43"
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // I assume d-M, you may refer to M-d for month-day instead.
        Date date = formatter.parse(toParse); // You will need try/catch around this
        long millis = date.getTime();
        textViewcreatedAt.setText(String.valueOf(getTimeAgo(millis, getContext())));
        webview.setText("Read More . .");
    }



    public static String getTimeAgo(long time, Context ctx) {
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = new Date().getTime();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "a minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "an hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }
    }
}
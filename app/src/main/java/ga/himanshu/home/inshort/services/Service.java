package ga.himanshu.home.inshort.services;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Error;
import ga.himanshu.home.inshort.model.Stories;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cerebro on 30/04/16.
 */
public class Service {

    private final String BASE_URL = "http://news.vaetas.com/";
    public ServiceInterface service;
    private Retrofit retrofit;

    public Service() {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ServiceInterface.class);
    }



    public void getStories(Callback<Stories> callback){
        service.listStories().enqueue(new CallbackHandler<Stories>(retrofit,callback));
    }
    public void getFilteredStories(List<Integer> list,Callback<Stories> callback){
        service.listFilteresStories(list).enqueue(new CallbackHandler<Stories>(retrofit, callback));
    } public void getCategory(Callback<List<Category>> callback){
        service.getCategory().enqueue(new CallbackHandler<List<Category>>(retrofit, callback));
    }



    private class CallbackHandler<T> implements retrofit2.Callback<T> {

        private Retrofit retrofit;
        private Callback callback;

        public CallbackHandler(Retrofit retrofit, Callback callback) {
            this.retrofit = retrofit;
            this.callback = callback;
        }


        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                callback.onSuccess(response.body());
            } else {
                Log.d("codekamp", "response not isSuccessful-CKservice");
                Log.d("codekamp", String.valueOf(response.code()) + " " + String.valueOf(response.message()));
                Converter<ResponseBody, Error> errorConverter =
                        retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                try {
                    Error error = errorConverter.convert(response.errorBody());
                    callback.onFailure(error);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.d("codekamp", "response onFailure-CKservice");

            Error error = new Error(t.getMessage(), ga.himanshu.home.inshort.model.Error.ERROR_CODE_NETWORK_ERROR);
            callback.onFailure(error);
        }
    }
}

package ga.himanshu.home.inshort.services;


import java.util.List;

import ga.himanshu.home.inshort.model.Category;
import ga.himanshu.home.inshort.model.Stories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cerebro on 22/04/16.
 */
public interface ServiceInterface {

    @GET("stories")
    Call<Stories> listFilteresStories(@Query("category[]") List<Integer> integerList);
    @GET("stories")
    Call<Stories> listStories();
    @GET("categories")
    Call<List<Category>> getCategory();
}
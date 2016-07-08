package ga.himanshu.home.inshort.services;

public interface Callback<T> {


    void onSuccess(T response);



    void onFailure(Error error);


    void onFailure(ga.himanshu.home.inshort.model.Error error);
}

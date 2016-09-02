package org.simpumind.yourkindamovies.mainscreen;

import android.util.Log;

import org.simpumind.yourkindamovies.model.Result;
import org.simpumind.yourkindamovies.model.ResultList;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by SimpuMind on 9/1/16.
 */
public class MainScreenPresenter implements MainScreenContract.Presenter{

    public Retrofit retrofit;
    public MainScreenContract.View mView;
    private String api_key = "361462d7f4d9b4a8bf5422cadae4c175";


    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void loadPost() {

        retrofit.create(MovieService.class).getMovieResult(api_key).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultList>() {
                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultList resultList) {
                        mView.showPosts(resultList.getResults());
                        Log.d("ResulValue", resultList.toString() + " ff");
                    }

                });
    }

    public interface MovieService{
        @GET("/3/movie/popular")
        Observable<ResultList> getMovieResult(@Query("api_key") String api_key);
    }
}

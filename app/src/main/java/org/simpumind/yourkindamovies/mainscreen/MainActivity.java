package org.simpumind.yourkindamovies.mainscreen;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import org.simpumind.yourkindamovies.App;
import org.simpumind.yourkindamovies.R;
import org.simpumind.yourkindamovies.adapter.MovieListAdapter;
import org.simpumind.yourkindamovies.model.Result;
import org.simpumind.yourkindamovies.model.ResultList;
import org.simpumind.yourkindamovies.util.AppConstants;
import org.simpumind.yourkindamovies.util.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View, MovieListAdapter.EventListener{

    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private MovieListAdapter movieListAdapter;
    private RecyclerView recyclerView;

    @Inject
    MainScreenPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        DaggerMainScreenComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
        movieListAdapter = new MovieListAdapter();
        recyclerView.setAdapter(movieListAdapter);

        //Increment the counter before making a network request
        EspressoIdlingResource.increment();

        //Call the method in MainPresenter to make Network Request
        mainPresenter.loadPost();
    }

    @Override
    public void showPosts(List<Result> posts) {
        movieListAdapter.addAllData(posts);

        //Decrement after loading the posts
        EspressoIdlingResource.decrement();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();

        // If there is no network connection we get an error and decrement the counter because the call has finished
        EspressoIdlingResource.decrement();
    }

    @Override
    public void showComplete() {
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClick(View view, Result result) {
        ActivityOptionsCompat optionsCompat = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setTransitionName("xyz");
            optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, view, view.getTransitionName());
        }

        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra(AppConstants.MOVIE_CONTENT, result.getOverview());
        intent.putExtra(AppConstants.MOVIE_RELEASE_DATE, result.getReleaseDate());
        intent.putExtra(AppConstants.MOVIE_NAME, result.getTitle());
        intent.putExtra(AppConstants.MOVIE_PHOTO_PATH, result.getPosterPath());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, optionsCompat.toBundle());
        }
    }
}

package org.simpumind.yourkindamovies.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.simpumind.yourkindamovies.App;
import org.simpumind.yourkindamovies.R;
import org.simpumind.yourkindamovies.util.AppConstants;

public class MovieDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        findViewById(R.id.share_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(MovieDetailActivity.this)
                        .setType("text/plain")
                        .setText("Some sample text")
                        .getIntent(), "Am sharing this later"));
            }
        });

        initViews();



    }

    private void initViews() {
        TextView moview_name = (TextView) findViewById(R.id.movie_name);
        TextView release_date = (TextView) findViewById(R.id.release_date);
        TextView movie_content = (TextView) findViewById(R.id.movie_content);
        final ImageView movie_image = (ImageView) findViewById(R.id.movie_photo);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_tool);

        Intent intent = getIntent();
        String content = intent.getStringExtra(AppConstants.MOVIE_CONTENT);
        final String title = intent.getStringExtra(AppConstants.MOVIE_NAME);
        String date = intent.getStringExtra(AppConstants.MOVIE_RELEASE_DATE);
        String image_url = intent.getStringExtra(AppConstants.MOVIE_PHOTO_PATH);
        String original_title = intent.getStringExtra(AppConstants.MOVIE_ORIGINAL_TITLE);

        moview_name.setText(title);
        movie_content.setText(content);
        release_date.setText(date);
        collapsingToolbarLayout.setTitle(original_title);

        Picasso.with(getApplicationContext())
                .load("http://image.tmdb.org/t/p/w600"+image_url)
                .centerCrop()
                .resize(150,150)
                .into(movie_image);

    }

}

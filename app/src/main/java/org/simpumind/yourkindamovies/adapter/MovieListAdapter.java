package org.simpumind.yourkindamovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.simpumind.yourkindamovies.R;
import org.simpumind.yourkindamovies.mainscreen.MovieDetailActivity;
import org.simpumind.yourkindamovies.model.Result;
import org.simpumind.yourkindamovies.util.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SimpuMind on 9/1/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {

    private List<Result> results;
    private Context context;
    private EventListener eventListener;

    public MovieListAdapter(){
        super();
        results = new ArrayList<>();
    }

    public void addAllData(List<Result> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public MovieListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_layout, parent, false);
        return new MovieListHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListHolder holder, int position) {
        final Result result = results.get(position);

        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w600"+result.getPosterPath())
                .centerCrop()
                .resize(150,150)
                .into(holder.movieImage);

        holder.imageTitle.setText(result.getTitle());
        holder.release_date.setText(result.getReleaseDate());

        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //eventListener.itemClick(view, result);

                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(AppConstants.MOVIE_CONTENT, result.getOverview());
                intent.putExtra(AppConstants.MOVIE_RELEASE_DATE, result.getReleaseDate());
                intent.putExtra(AppConstants.MOVIE_NAME, result.getTitle());
                intent.putExtra(AppConstants.MOVIE_PHOTO_PATH, result.getPosterPath());
                intent.putExtra(AppConstants.MOVIE_ORIGINAL_TITLE, result.getOriginalTitle());
                    context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    protected  class MovieListHolder extends RecyclerView.ViewHolder{

        public ImageView movieImage;
        public TextView imageTitle;
        public TextView release_date;
        public LinearLayout holder;

        public MovieListHolder(View itemView) {
            super(itemView);

            holder = (LinearLayout) itemView.findViewById(R.id.holder);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_photo);
            imageTitle = (TextView) itemView.findViewById(R.id.movie_name);
            release_date = (TextView) itemView.findViewById(R.id.release_date);
        }

    }

    public interface EventListener{
        void itemClick(View view, Result result);
    }
}

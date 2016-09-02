package org.simpumind.yourkindamovies.mainscreen;

import org.simpumind.yourkindamovies.model.Result;
import org.simpumind.yourkindamovies.model.ResultList;

import java.util.List;

/**
 * Created by SimpuMind on 9/1/16.
 */
public interface MainScreenContract {

    interface View {
        void showPosts(List<Result> posts);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadPost();
    }
}

package org.simpumind.yourkindamovies;

import android.app.Application;

import org.simpumind.yourkindamovies.data.component.DaggerNetComponent;
import org.simpumind.yourkindamovies.data.component.NetComponent;
import org.simpumind.yourkindamovies.data.module.AppModule;
import org.simpumind.yourkindamovies.data.module.NetModule;

/**
 * Created by SimpuMind on 9/1/16.
 */
public class App extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.themoviedb.org/3/movie/"))
                .build();
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}

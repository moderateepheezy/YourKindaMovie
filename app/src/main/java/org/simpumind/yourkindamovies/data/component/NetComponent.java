package org.simpumind.yourkindamovies.data.component;

import org.simpumind.yourkindamovies.data.module.AppModule;
import org.simpumind.yourkindamovies.data.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by SimpuMind on 9/1/16.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit provideRetrofit();
}

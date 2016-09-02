package org.simpumind.yourkindamovies.mainscreen;

import org.simpumind.yourkindamovies.util.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SimpuMind on 9/1/16.
 */
@Module
public class MainScreenModule {

    private final MainScreenContract.View mView;

    public MainScreenModule(MainScreenContract.View mView){
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainScreenContract.View provideMainScreenContractView(){
        return mView;
    }
}

package org.simpumind.yourkindamovies.mainscreen;

import org.simpumind.yourkindamovies.data.component.NetComponent;
import org.simpumind.yourkindamovies.util.CustomScope;

import dagger.Component;

/**
 * Created by SimpuMind on 9/1/16.
 */
@CustomScope
@Component(dependencies = NetComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {
    void inject(MainActivity mainActivity);
}

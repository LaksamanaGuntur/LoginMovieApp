package co.id.loginmovieapp.di.component;

import javax.inject.Singleton;

import co.id.loginmovieapp.di.module.AppModule;
import co.id.loginmovieapp.di.module.NetworkModule;
import co.id.loginmovieapp.di.module.MainActivityModule;
import dagger.Component;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class
        }
)

public interface AppComponent {
    MainComponent plus(MainActivityModule mainActivityModule);
}

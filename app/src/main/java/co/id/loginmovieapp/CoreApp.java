package co.id.loginmovieapp;

import android.app.Application;

import co.id.loginmovieapp.di.component.AppComponent;
import co.id.loginmovieapp.di.component.DaggerAppComponent;
import co.id.loginmovieapp.di.module.AppModule;
import co.id.loginmovieapp.di.module.NetworkModule;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class CoreApp extends Application {
    private AppComponent appComponent = createAppComponent();

    private static CoreApp instance;

    public static CoreApp get() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    protected AppComponent createAppComponent() {
        return appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return  appComponent;
    }
}

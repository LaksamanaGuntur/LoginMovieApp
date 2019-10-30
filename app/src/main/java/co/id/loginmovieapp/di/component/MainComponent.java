package co.id.loginmovieapp.di.component;

import co.id.loginmovieapp.di.scope.ActivityScope;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.ui.dashboard.DashboardActivity;
import co.id.loginmovieapp.ui.dashboard.home.HomeFragment;
import co.id.loginmovieapp.ui.login.LoginActivity;
import dagger.Subcomponent;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@ActivityScope
@Subcomponent(
        modules = MainActivityModule.class
)
public interface MainComponent {
    DashboardActivity inject(DashboardActivity dashboardActivity);
    LoginActivity inject(LoginActivity loginActivity);
    HomeFragment inject(HomeFragment homeFragment);
}

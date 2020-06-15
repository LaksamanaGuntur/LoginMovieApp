package co.id.loginmovieapp.di.module;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.data.DaoMaster;
import co.id.loginmovieapp.data.DaoSession;
import co.id.loginmovieapp.di.scope.ActivityScope;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.network.NetworkService;
import co.id.loginmovieapp.repository.MainRepository;
import co.id.loginmovieapp.ui.dashboard.DashboardActivity;
import co.id.loginmovieapp.ui.dashboard.home.HomeFragment;
import co.id.loginmovieapp.ui.dashboard.home.HomePresenter;
import co.id.loginmovieapp.ui.detail.DetailActivity;
import co.id.loginmovieapp.ui.detail.DetailPresenter;
import co.id.loginmovieapp.ui.favorite.FavoriteActivity;
import co.id.loginmovieapp.ui.favorite.FavoritePresenter;
import co.id.loginmovieapp.ui.login.LoginActivity;
import co.id.loginmovieapp.ui.login.LoginPresenter;
import co.id.loginmovieapp.ui.registration.RegistrationActivity;
import co.id.loginmovieapp.ui.registration.RegistrationPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

@Module
public class MainActivityModule {
    private DashboardActivity dashboardActivity;
    private LoginActivity loginActivity;
    private RegistrationActivity registrationActivity;
    private DetailActivity detailActivity;
    private FavoriteActivity favoriteActivity;
    private HomeFragment homeFragment;

    public MainActivityModule(DashboardActivity dashboardActivity) {
        this.dashboardActivity = dashboardActivity;
    }

    public MainActivityModule(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public MainActivityModule(RegistrationActivity registrationActivity) {
        this.registrationActivity = registrationActivity;
    }

    public MainActivityModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    public MainActivityModule(FavoriteActivity favoriteActivity) {
        this.favoriteActivity = favoriteActivity;
    }

    public MainActivityModule(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    @Provides
    @ActivityScope
    DashboardActivity provideHomeActivity() {
        return dashboardActivity;
    }

    @Provides
    @ActivityScope
    LoginActivity provideLoginActivity() {
        return loginActivity;
    }

    @Provides
    @ActivityScope
    RegistrationActivity provideRegistrationActivity() {
        return registrationActivity;
    }

    @Provides
    @ActivityScope
    MainRepository provideMainRepository(NetworkService networkService) {
        return new MainRepository(networkService);
    }

    @Provides
    @ActivityScope
    DaoSession provideDaoSession() {
        String DbName = Constant.DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(CoreApp.get(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        Log.d("DB PATH", db.getPath());
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


    @Provides
    @ActivityScope
    DataModel provideDataModel(DaoSession daoSession){
        return new DataModel(daoSession);
    }

    @Provides
    @ActivityScope
    HomePresenter provideHomePresenter(MainRepository mainRepository, DataModel dataModel) {
        return new HomePresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    LoginPresenter provideLoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new LoginPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    RegistrationPresenter provideRegistrationPresenter(MainRepository mainRepository, DataModel dataModel) {
        return new RegistrationPresenter(mainRepository, dataModel);
    }

    @Provides
    @ActivityScope
    DetailPresenter provideDetailPresenter(DataModel dataModel) {
        return new DetailPresenter(dataModel);
    }

    @Provides
    @ActivityScope
    FavoritePresenter provideFavoritePresenter(DataModel dataModel) {
        return new FavoritePresenter(dataModel);
    }
}

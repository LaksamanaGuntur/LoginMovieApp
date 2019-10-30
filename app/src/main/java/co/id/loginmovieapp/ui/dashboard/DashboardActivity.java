package co.id.loginmovieapp.ui.dashboard;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.adapter.ListAdapter;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.ui.dashboard.account.AccountFragment;
import co.id.loginmovieapp.ui.dashboard.home.HomeFragment;

public class DashboardActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.bn_main)
    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    public void initializeData() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());
    }

    private boolean loadFragment(Fragment fragment){
        System.out.println("fragment : " + fragment);

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment = new HomeFragment();
                break;
            case R.id.account_menu:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }
}

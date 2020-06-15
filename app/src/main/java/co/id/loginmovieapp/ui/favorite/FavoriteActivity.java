package co.id.loginmovieapp.ui.favorite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.adapter.FavoriteAdapter;
import co.id.loginmovieapp.adapter.ListAdapter;
import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.ui.detail.DetailActivity;

public class FavoriteActivity extends AppCompatActivity implements FavoriteContract.View {
    @Inject
    FavoritePresenter mFavoritePresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private FavoriteContract.UserActionListener mUserActionListener;
    private GridLayoutManager mGridLayoutManager;
    private FavoriteAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = mFavoritePresenter;
        mFavoritePresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void setAdapter(List<FavoriteData> favoriteData) {
        mGridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new FavoriteAdapter(favoriteData, this);
        mRecyclerView.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();
    }

    public void initializeData() {
        mToolbar.setTitle("Favorite Movie");
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mFavoritePresenter.getData();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void openDetail(FavoriteData favoriteData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.MOVIE_DETAIL, favoriteData);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constant.PARAM, 2);
        intent.putExtra(Constant.MOVIE_DETAIL, b);
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mFavoritePresenter.getData();
    }
}

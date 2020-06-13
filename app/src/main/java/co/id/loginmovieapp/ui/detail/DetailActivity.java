package co.id.loginmovieapp.ui.detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.helper.Constant;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.item_thumbnail)
    ImageView mImageView;
    @BindView(R.id.txt_movie_title)
    TextView mTxtMovieTitle;
    @BindView(R.id.txt_release_date)
    TextView mTxtReleaseDate;
    @BindView(R.id.txt_movie_desc)
    TextView mTxtMovieDesc;
    @BindView(R.id.action_favorite)
    ImageView mFavorite;

    private DetailContract.UserActionListener mUserActionListener;
    private ResultData mMovie;
    private boolean mIsFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = detailPresenter;
        detailPresenter.setView(this);
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    public void initializeData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(Constant.MOVIE_DETAIL)) {
                Bundle b = intent.getBundleExtra(Constant.MOVIE_DETAIL);
                mMovie = b.getParcelable(Constant.MOVIE_DETAIL);

                Picasso.with(this)
                        .load(Constant.URL_IMAGE + mMovie.getPosterPath())
                        .into(mImageView);


                mTxtMovieTitle.setText(mMovie.getOriginalTitle());
                mTxtReleaseDate.setText(mMovie.getReleaseDate());
                mTxtMovieDesc.setText(mMovie.getOverview());

                mToolbar.setTitle(mMovie.getTitle());
                setSupportActionBar(mToolbar);
                ActionBar ab = getSupportActionBar();
                ab.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.action_favorite)
    public void setFavorite(View view) {
        if (mIsFavorite) {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart));
            mIsFavorite = false;
        } else {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_active_red));
            mIsFavorite = true;
        }
    }
}

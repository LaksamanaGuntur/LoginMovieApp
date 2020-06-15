package co.id.loginmovieapp.ui.detail;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
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
import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.helper.Constant;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    @Inject
    DetailPresenter mDetailPresenter;

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
    private FavoriteData mFavoriteData;
    private int mParam = 0;
//    private boolean mIsFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActivityComponent();
        mUserActionListener = mDetailPresenter;
        mDetailPresenter.setView(this);
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
            mParam = intent.getIntExtra(Constant.PARAM, 0);
            Bundle b = intent.getBundleExtra(Constant.MOVIE_DETAIL);

            Log.d("detail", "param : " + mParam);
            if (mParam == 1) {
                mMovie = b.getParcelable(Constant.MOVIE_DETAIL);
            } else {
                mFavoriteData = b.getParcelable(Constant.MOVIE_DETAIL);

                mMovie = new ResultData(
                        mFavoriteData.getIdLocal(),
                        mFavoriteData.getVoteCount(),
                        mFavoriteData.getId(),
                        mFavoriteData.getVideo(),
                        mFavoriteData.getVoteAverage(),
                        mFavoriteData.getTitle(),
                        mFavoriteData.getPopularity(),
                        mFavoriteData.getPosterPath(),
                        mFavoriteData.getOriginalLanguage(),
                        mFavoriteData.getOriginalTitle(),
                        mFavoriteData.getBackdropPath(),
                        mFavoriteData.getAdult(),
                        mFavoriteData.getOverview(),
                        mFavoriteData.getReleaseDate()
                );
            }

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

            checkFavoriteStatus();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.action_favorite)
    public void setFavorite(View view) {
        if (mDetailPresenter.isFavorite(mMovie)) {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart));
            mDetailPresenter.deleteFromFavorite(mMovie);
        } else {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_active_red));
            mDetailPresenter.setToFavorite(mMovie);
        }
    }

    public void checkFavoriteStatus() {
        if (mDetailPresenter.isFavorite(mMovie)) {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart_active_red));
        } else {
            mFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_heart));
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);

        super.onBackPressed();
    }
}

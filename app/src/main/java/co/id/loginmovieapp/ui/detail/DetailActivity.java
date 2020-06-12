package co.id.loginmovieapp.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private DetailContract.UserActionListener mUserActionListener;
    private ResultData mMovie;
    private boolean mIsInFavorites = false;

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
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

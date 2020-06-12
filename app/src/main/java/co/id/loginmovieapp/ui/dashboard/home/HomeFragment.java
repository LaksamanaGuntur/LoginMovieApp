package co.id.loginmovieapp.ui.dashboard.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.adapter.ListAdapter;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.ui.detail.DetailActivity;

public class HomeFragment extends Fragment implements HomeContract.View {
    @Inject
    HomePresenter mHomePresenter;

    @BindView(R.id.main_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private View mView;
    private Unbinder mUnbinder;
    private HomeContract.UserActionListener mUserActionListener;
    private GridLayoutManager mGridLayoutManager;
    private ListAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
            mUnbinder = ButterKnife.bind(this, mView);

            setupActivityComponent();
            initializeData();
        }

        ButterKnife.bind(this, mView);
        return mView;
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    @Override
    public void setAdapter(List<ResultData> resultData) {
        mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mListAdapter = new ListAdapter(resultData, getContext());
        mRecyclerView.setAdapter(mListAdapter);
    }

    @Override
    public void initializeData() {
        mUserActionListener = mHomePresenter;
        mHomePresenter.setView(this);
        mUserActionListener.getData();
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
    public void openDetail(ResultData resultData) {
        Bundle b = new Bundle();
        b.putParcelable(Constant.MOVIE_DETAIL, resultData);

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Constant.MOVIE_DETAIL, b);
        startActivity(intent);
    }
}

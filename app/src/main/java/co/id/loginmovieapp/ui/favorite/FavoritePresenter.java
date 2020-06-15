package co.id.loginmovieapp.ui.favorite;

import android.os.Parcel;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.ui.dashboard.home.HomeContract;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class FavoritePresenter implements FavoriteContract.UserActionListener {
    private static WeakReference<FavoriteContract.View> mView;
    private DataModel mDataModel;

    public FavoritePresenter(DataModel dataModel) {
        mDataModel = dataModel;
    }

    public FavoritePresenter() {

    }

    public void setView(FavoriteContract.View view) {
        mView = new WeakReference<>(view);
    }

    public FavoriteContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData() {
        getView().hideProgressBar();
        getView().setAdapter(mDataModel.getAllFavoriteData());
    }
}

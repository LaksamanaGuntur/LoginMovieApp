package co.id.loginmovieapp.ui.detail;

import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;

import java.util.List;

import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.model.DataModel;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class DetailPresenter implements DetailContract.UserActionListener {
    private DetailContract.View mView;
    private DataModel mDataModel;

    public DetailPresenter(DataModel dataModel) {
        mDataModel = dataModel;
    }

    public void setView(DetailContract.View view){
        mView = view;
    }

    @Override
    public boolean isFavorite(ResultData resultData) {
        List<FavoriteData> favoriteDataList = mDataModel.getAllFavoriteData();
        Log.d("Detail Presenter", "resultData id : " + resultData.getId());
        Log.d("Detail Presenter", "favoriteDataList : " + favoriteDataList.size());

        for(int i = 0; i < favoriteDataList.size(); i++) {
            Log.d("Detail Presenter", "favoriteDataList id : " + favoriteDataList.get(i).getId());
            if (favoriteDataList.get(i).getId() == resultData.getId()) {

                Log.d("Detail Presenter", "==== same ====");
                return true;
            }
        }

        return false;
    }

    @Override
    public void setToFavorite(ResultData resultData) {
        mDataModel.insertFavoriteData(
                new FavoriteData(
                        resultData.getIdLocal(),
                        resultData.getVoteCount(),
                        resultData.getId(),
                        resultData.getVideo(),
                        resultData.getVoteAverage(),
                        resultData.getTitle(),
                        resultData.getPopularity(),
                        resultData.getPosterPath(),
                        resultData.getOriginalLanguage(),
                        resultData.getOriginalTitle(),
                        resultData.getBackdropPath(),
                        resultData.getAdult(),
                        resultData.getOverview(),
                        resultData.getReleaseDate()
                )
        );


    }

    @Override
    public void deleteFromFavorite(ResultData resultData) {
        final Parcel parcel = Parcel.obtain();
        parcel.writeValue(resultData);

        mDataModel.deleteFavoriteData(
                new FavoriteData(
                        resultData.getIdLocal(),
                        resultData.getVoteCount(),
                        resultData.getId(),
                        resultData.getVideo(),
                        resultData.getVoteAverage(),
                        resultData.getTitle(),
                        resultData.getPopularity(),
                        resultData.getPosterPath(),
                        resultData.getOriginalLanguage(),
                        resultData.getOriginalTitle(),
                        resultData.getBackdropPath(),
                        resultData.getAdult(),
                        resultData.getOverview(),
                        resultData.getReleaseDate()
                )
        );
    }
}

package co.id.loginmovieapp.ui.favorite;


import java.util.List;

import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class FavoriteContract {

    public interface View{
        void setAdapter(List<FavoriteData> favoriteData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void openDetail(FavoriteData favoriteData);
    }

    public interface UserActionListener{
        void getData();
    }
}

package co.id.loginmovieapp.ui.detail;


import co.id.loginmovieapp.data.ResultData;

/**
 * Created by Laksamana Guntur Dzulfikar.
 * Android Developer
 */

public class DetailContract {

    public interface View{

    }

    public interface UserActionListener{
        boolean isFavorite(ResultData resultData);
        void setToFavorite(ResultData resultData);
        void deleteFromFavorite(ResultData resultData);
    }
}

package co.id.loginmovieapp.ui.dashboard.home;

import java.util.List;

import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.network.ApiResponse;

public class HomeContract {
    public interface View{
        void setAdapter(List<ResultData> resultData);
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
    }

    public interface UserActionListener{
        void getData();
        void saveData(ApiResponse apiResponse);
    }
}

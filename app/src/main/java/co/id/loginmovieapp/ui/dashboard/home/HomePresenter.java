package co.id.loginmovieapp.ui.dashboard.home;

import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.network.ApiResponse;
import co.id.loginmovieapp.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

public class HomePresenter implements HomeContract.UserActionListener {
    private HomeContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(HomeContract.View view){
        mView = view;
    }

    @Override
    public void getData() {
        mDataModel.deleteDataList();
        mView.showProgressBar();
        mMainRepository.getData()
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        mView.hideProgressBar();
                        mView.setAdapter(apiResponse.getResults());
                        saveData(apiResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        mView.hideProgressBar();
                        mView.setAdapter(mDataModel.getAllData());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void saveData(ApiResponse apiResponse) {
        for(ResultData resultData : apiResponse.getResults()){
            mDataModel.insertData(resultData);
        }
    }
}

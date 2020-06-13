package co.id.loginmovieapp.ui.dashboard.home;

import java.lang.ref.WeakReference;

import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.network.ApiResponse;
import co.id.loginmovieapp.repository.MainRepository;
import io.reactivex.subscribers.ResourceSubscriber;

public class HomePresenter implements HomeContract.UserActionListener {
    private static WeakReference<HomeContract.View> mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public HomePresenter() {

    }

    public HomePresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    public void setView(HomeContract.View view) {
        mView = new WeakReference<>(view);
    }

    public HomeContract.View getView() throws NullPointerException {
        if (mView != null){
            return mView.get();
        } else{
            throw new NullPointerException("View is unavailable");
        }
    }

    @Override
    public void getData(String movieType) {
        mDataModel.deleteDataList();
        getView().showProgressBar();
        mMainRepository.getData(movieType)
                .subscribe(new ResourceSubscriber<ApiResponse>() {
                    @Override
                    public void onNext(ApiResponse apiResponse) {
                        getView().hideProgressBar();
                        getView().setAdapter(apiResponse.getResults());
                        saveData(apiResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        //Handle when onErrorResponse From API
                        getView().hideProgressBar();
                        getView().setAdapter(mDataModel.getAllData());
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

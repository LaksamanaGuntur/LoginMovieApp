package co.id.loginmovieapp.ui.registration;

import co.id.loginmovieapp.data.ResultDataLogin;
import co.id.loginmovieapp.data.ResultDataRegister;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.repository.MainRepository;

public class RegistrationPresenter implements RegistrationContract.UserActionListener {
    private RegistrationContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public RegistrationPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(RegistrationContract.View view) {
        mView = view;
    }

    @Override
    public void saveData(String name, String email, String password) {
        mDataModel.deleteDataListRegister();
        mDataModel.insertDataRegister(new ResultDataRegister(name, email, password));

        mView.moveToHomeScreen();
    }
}

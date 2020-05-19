package co.id.loginmovieapp.ui.login;

import co.id.loginmovieapp.data.ResultDataLogin;
import co.id.loginmovieapp.model.DataModel;
import co.id.loginmovieapp.repository.MainRepository;

public class LoginPresenter implements LoginContract.UserActionListener {
    private LoginContract.View mView;
    private MainRepository mMainRepository;
    private DataModel mDataModel;

    public LoginPresenter(MainRepository mainRepository, DataModel dataModel) {
        mMainRepository = mainRepository;
        mDataModel = dataModel;
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void saveData(String email, String password) {

        try {
            if (mDataModel.getAllDataRegister().size() > 0) {
                for (int i = 0; i < mDataModel.getAllDataRegister().size(); i++) {
                    if (mDataModel.getAllDataRegister().get(i).getEmail().equals(email) &&
                            mDataModel.getAllDataRegister().get(i).getPassword().equals(password)) {

                        mView.moveToHomeScreen();
                        return;
                    }
                }

                mView.showError();
            } else {
                mView.showError();
            }
        } catch (Exception e) {
            mView.showError();
        }
    }

    @Override
    public boolean checkData() {
        try {
            if (mDataModel.getAllDataLogin().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}

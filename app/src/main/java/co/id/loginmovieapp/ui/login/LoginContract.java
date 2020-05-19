package co.id.loginmovieapp.ui.login;

public class LoginContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void moveToHomeScreen();
        void showError();
    }

    public interface UserActionListener{
        void setView(LoginContract.View view);
        void saveData(String email, String password);
        boolean checkData();
    }
}

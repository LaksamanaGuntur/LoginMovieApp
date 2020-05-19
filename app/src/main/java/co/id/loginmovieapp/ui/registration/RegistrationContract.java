package co.id.loginmovieapp.ui.registration;

public class RegistrationContract {
    public interface View{
        void initializeData();
        void showProgressBar();
        void hideProgressBar();
        void moveToHomeScreen();
    }

    public interface UserActionListener{
        void setView(RegistrationContract.View view);
        void saveData(String name, String email, String password);
    }
}

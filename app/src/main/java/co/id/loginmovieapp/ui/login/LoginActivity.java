package co.id.loginmovieapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.loginmovieapp.CoreApp;
import co.id.loginmovieapp.R;
import co.id.loginmovieapp.di.module.MainActivityModule;
import co.id.loginmovieapp.ui.dashboard.DashboardActivity;
import co.id.loginmovieapp.ui.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private static final String TAG = "RegistrationActivity";

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.email_et)
    EditText mEmail;
    @BindView(R.id.password_et)
    EditText mPassword;
    @BindView(R.id.login_btn)
    Button mLoginButton;
    @BindView(R.id.register_btn)
    Button mRegisterButton;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private LoginContract.UserActionListener mUserActionListener;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setupActivityComponent();
        initializeData();
    }

    private void setupActivityComponent() {
        CoreApp.get()
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);
    }

    private void hideSystemUI() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @OnClick(R.id.login_btn)
    public void userLoginAction(View view) {
        if (mEmail.getText().toString().length() == 0) {
            mEmail.setError(getString(R.string.field_required));
            return;
        }

        if (mPassword.getText().toString().length() == 0) {
            mPassword.setError(getString(R.string.field_required));
            return;
        }

        mUserActionListener.saveData(mEmail.getText().toString(), mPassword.getText().toString());
    }

    @OnClick(R.id.register_btn)
    public void userRegisterAction(View view) {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }

    @Override
    public void initializeData() {
        mUserActionListener = mLoginPresenter;
        mLoginPresenter.setView(this);

        if (mUserActionListener.checkData()) {
            moveToHomeScreen();
        } else {
            hideProgressBar();
        }
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mEmail.setEnabled(false);
        mPassword.setEnabled(false);
        mLoginButton.setEnabled(false);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mEmail.setEnabled(true);
        mPassword.setEnabled(true);
        mLoginButton.setEnabled(true);
    }

    @Override
    public void moveToHomeScreen() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }

    @Override
    public void showError() {
        mEmail.setError(getString(R.string.error_email_password));
        mPassword.setError(getString(R.string.error_email_password));
    }
}

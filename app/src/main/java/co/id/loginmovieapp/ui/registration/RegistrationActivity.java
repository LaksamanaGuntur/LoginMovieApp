package co.id.loginmovieapp.ui.registration;

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

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {
    private static final String TAG = "RegistrationActivity";

    @Inject
    RegistrationPresenter mRegistrationPresenter;

    @BindView(R.id.name_et)
    EditText mName;
    @BindView(R.id.email_et)
    EditText mEmail;
    @BindView(R.id.password_et)
    EditText mPassword;
    @BindView(R.id.register_btn)
    Button mRegisterButton;
    @BindView(R.id.main_progress_bar)
    ProgressBar mProgressBar;

    private RegistrationContract.UserActionListener mUserActionListener;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_registration);
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

    @OnClick(R.id.register_btn)
    public void userRegisterAction(View view) {
        if (mName.getText().toString().length() == 0) {
            mName.setError(getString(R.string.field_required));
            return;
        }

        if (mEmail.getText().toString().length() == 0) {
            mEmail.setError(getString(R.string.field_required));
            return;
        }

        if (mPassword.getText().toString().length() == 0) {
            mPassword.setError(getString(R.string.field_required));
            return;
        }

        mUserActionListener.saveData(mName.getText().toString(), mEmail.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void initializeData() {
        mUserActionListener = mRegistrationPresenter;
        mRegistrationPresenter.setView(this);
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mName.setEnabled(false);
        mEmail.setEnabled(false);
        mPassword.setEnabled(false);
        mRegisterButton.setEnabled(false);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mName.setEnabled(true);
        mEmail.setEnabled(true);
        mPassword.setEnabled(true);
        mRegisterButton.setEnabled(true);
    }

    @Override
    public void moveToHomeScreen() {
//        startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));
        finish();
    }
}

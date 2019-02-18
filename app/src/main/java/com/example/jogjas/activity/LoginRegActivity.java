package com.example.jogjas.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jogjas.R;
import com.example.jogjas.presenter.DataPresenter;
import com.example.jogjas.view.UpdateView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRegActivity extends AppCompatActivity implements View.OnClickListener, UpdateView {

    private EditText etEmail, etEmailReg;
    private EditText etPassword, etPasswordReg, etConfirmPassword;
    private String email, pass, confirmPass;
    private FirebaseAuth auth;
    private LinearLayout linLogin, linSignUp;
    private ProgressBar pgLogin, pgSignUp;
    private DataPresenter dataPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);

        FirebaseApp.initializeApp(getApplicationContext());
        auth = FirebaseAuth.getInstance();

        linLogin = (LinearLayout) findViewById(R.id.linLogin);
        linSignUp = (LinearLayout) findViewById(R.id.linSignUp);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        TextView tvSignUp = (TextView) findViewById(R.id.tvSignup);
        TextView tvSignIn = (TextView) findViewById(R.id.tvlogin);
        Button btnLogin = (Button) findViewById(R.id.btnSignIn);
        pgLogin = (ProgressBar) findViewById(R.id.pdLogin);
        pgSignUp = (ProgressBar) findViewById(R.id.pdSignUp);
        etEmailReg = (EditText) findViewById(R.id.etEmailReg);
        etPasswordReg = (EditText) findViewById(R.id.etPasswordReg);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPasswordReg);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        if (v == findViewById(R.id.btnSignIn)) {
            getData();
            if (TextUtils.isEmpty(email)) {
                etEmail.setError(getString(R.string.wording_email_kosong));
                etEmail.requestFocus();
                return;
            } else if (!(email.contains("@") && email.contains("."))) {
                etEmail.setError(getString(R.string.malformat_email));
                etEmail.requestFocus();
                return;
            } else if (TextUtils.isEmpty(pass)) {
                etPassword.setError(getString(R.string.wording_password_kosong));
                etPassword.requestFocus();
                return;
            } else if (pass.length() < 6) {
                etPassword.setError(getString(R.string.wording_password_kependekan));
                etPassword.requestFocus();
                return;
            }

            dataPresenter = new DataPresenter(this, email, pass, LoginRegActivity.this);
            dataPresenter.doLogin();
//            pgLogin.setVisibility(View.VISIBLE);
//            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull final Task<AuthResult> task) {
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (task.isSuccessful()) {
//                                pgLogin.setVisibility(View.GONE);
//                                startActivity(new Intent(LoginRegActivity.this, MainActivity.class));
//                                finish();
//                                AppPreference.setPreferenceBoolean(LoginRegActivity.this,AppPreference.LOGIN,true);
//                            } else {
//                                pgLogin.setVisibility(View.GONE);
//                                Toast.makeText(LoginRegActivity.this, getString(R.string.wording_email_pwd_missmatch), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    },1500);
//                }
//            });
        } else if (v == findViewById(R.id.btnSignUp)) {
            getData();
            if (TextUtils.isEmpty(email)) {
                etEmailReg.setError(getString(R.string.wording_email_kosong));
                etEmailReg.requestFocus();
            } else if (!(email.contains("@") && email.contains("."))) {
                etEmailReg.setError(getString(R.string.malformat_email));
                etEmailReg.requestFocus();
            } else if (TextUtils.isEmpty(pass)) {
                etPasswordReg.setError(getString(R.string.wording_password_kosong));
                etPasswordReg.requestFocus();
            } else if (TextUtils.isEmpty(confirmPass)) {
                etConfirmPassword.setError(getString(R.string.wording_password_kosong));
                etConfirmPassword.requestFocus();
            } else if (pass.length() < 6) {
                etPasswordReg.setError(getString(R.string.wording_password_kependekan));
                etPasswordReg.requestFocus();
            } else if (confirmPass.length() < 6) {
                etConfirmPassword.setError(getString(R.string.wording_password_kependekan));
                etConfirmPassword.requestFocus();
            } else if (pass.matches(confirmPass)) {
                //do Register
                dataPresenter = new DataPresenter(this, email, pass, LoginRegActivity.this);
                dataPresenter.doRegister();

//                pgSignUp.setVisibility(View.VISIBLE);
//                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull final Task<AuthResult> task) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (task.isSuccessful()) {
//                                    pgSignUp.setVisibility(View.GONE);
//                                    startActivity(new Intent(LoginRegActivity.this, MainActivity.class));
//                                    finish();
//                                } else {
//                                    pgSignUp.setVisibility(View.GONE);
//                                    Toast.makeText(LoginRegActivity.this, getString(R.string.wording_email_pwd_missmatch), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },1500);
//
//                    }
//                });
            } else {
                etConfirmPassword.setError(getString(R.string.pwd_tdak_sama));
                etConfirmPassword.requestFocus();
            }
        } else if (v == findViewById(R.id.tvSignup)) {
            linLogin.setVisibility(View.GONE);
            linSignUp.setVisibility(View.VISIBLE);
            linSignUp.startAnimation(slideUp);
        } else if (v == findViewById(R.id.tvlogin)) {
            linSignUp.setVisibility(View.GONE);
            linLogin.setVisibility(View.VISIBLE);
            linLogin.startAnimation(slideUp);
        }
    }

    private void getData() {
        if (linLogin.getVisibility() == View.VISIBLE) {
            email = etEmail.getText().toString();
            pass = etPassword.getText().toString();
        } else {
            email = etEmailReg.getText().toString();
            pass = etPasswordReg.getText().toString();
            confirmPass = etConfirmPassword.getText().toString();
        }
    }

    @Override
    public void showProgress() {
        pgSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pgSignUp.setVisibility(View.GONE);
    }
}

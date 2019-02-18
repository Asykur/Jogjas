package com.example.jogjas.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.jogjas.R;
import com.example.jogjas.activity.MainActivity;
import com.example.jogjas.utils.AppPreference;
import com.example.jogjas.view.UpdateView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DataPresenter {

    private FirebaseAuth firebaseAuth;

    private UpdateView updateView;
    private String email, password;
    private Context context;

    public DataPresenter(UpdateView updateView, String email, String password, Context context) {
        this.updateView = updateView;
        this.email = email;
        this.password = password;
        this.context = context;
    }

    public void doRegister() {
        updateView.showProgress();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            updateView.hideProgress();
                            context.startActivity(new Intent(context, MainActivity.class));
                            ((Activity) context).finishAffinity();
                            AppPreference.setPreferenceBoolean(context, AppPreference.LOGIN, true);
                        }
                    }, 1500);
                } else {
                    updateView.hideProgress();
                    Toast.makeText(context, context.getString(R.string.wording_gagal_register), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doLogin() {
        updateView.showProgress();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateView.hideProgress();
                    context.startActivity(new Intent(context, MainActivity.class));
                    ((Activity) context).finishAffinity();
                    AppPreference.setPreferenceBoolean(context, AppPreference.LOGIN, true);
                } else {
                    updateView.hideProgress();
                    Toast.makeText(context, context.getString(R.string.wording_gagal_signin), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

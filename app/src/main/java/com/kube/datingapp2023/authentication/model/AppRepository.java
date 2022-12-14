package com.kube.datingapp2023.authentication.model;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kube.datingapp2023.R;

public class AppRepository {
    private Application application;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logOutMutableLiveData;
    private MutableLiveData<Boolean> logInProgressMutableLiveData;
    private MutableLiveData<Boolean> registerInProgressMutableLiveData;
    private FirebaseAuth firebaseAuth;


    public AppRepository() {}

    public AppRepository(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        userMutableLiveData = new MutableLiveData<>();
        logOutMutableLiveData = new MutableLiveData<>();
        logInProgressMutableLiveData = new MutableLiveData<>();
        registerInProgressMutableLiveData = new MutableLiveData<>();

        if(firebaseAuth.getCurrentUser()!=null){
            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
            logOutMutableLiveData.postValue(false);
        }
    }

    public void register(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(application.getMainExecutor(), task -> {
                    if (task.isSuccessful()) {
                        userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                        registerInProgressMutableLiveData.postValue(true);
                    } else {
                        Toast.makeText(application, application.getString(R.string.error_string) + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void login(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(application.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userMutableLiveData.postValue(firebaseAuth.getCurrentUser());
                            logInProgressMutableLiveData.postValue(true);
                        } else {
                            Toast.makeText(application, application.getString(R.string.error_string) + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void logOut(){
        firebaseAuth.signOut();
        logOutMutableLiveData.postValue(true);
    }

    public MutableLiveData<Boolean> getLogInProgressMutableLiveData() {return logInProgressMutableLiveData;}
    public MutableLiveData<Boolean> getRegisterInProgressMutableLiveData() {return registerInProgressMutableLiveData;}
    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {return userMutableLiveData;}
    public MutableLiveData<Boolean> getLogOutMutableLiveData() {return logOutMutableLiveData;}


}

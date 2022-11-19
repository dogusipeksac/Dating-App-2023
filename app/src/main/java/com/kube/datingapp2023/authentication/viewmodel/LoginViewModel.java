package com.kube.datingapp2023.authentication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kube.datingapp2023.authentication.model.AppRepository;

public class LoginViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logInProgressMutableLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        appRepository=new AppRepository(application);
        userMutableLiveData=appRepository.getUserMutableLiveData();
        logInProgressMutableLiveData=appRepository.getLogInProgressMutableLiveData();
    }
    public void login(String email,String password){
        appRepository.login(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLogInProgressMutableLiveData() {return logInProgressMutableLiveData;}
}

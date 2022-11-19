package com.kube.datingapp2023.authentication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kube.datingapp2023.authentication.model.AppRepository;

public class MainViewModel extends AndroidViewModel {
    AppRepository appRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logOutMutableLiveData;
    public MainViewModel(@NonNull Application application) {
        super(application);
        appRepository=new AppRepository(application);
        userMutableLiveData=appRepository.getUserMutableLiveData();
        logOutMutableLiveData=appRepository.getLogOutMutableLiveData();
    }

    public void logOut(){
        appRepository.logOut();
    }
    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {return userMutableLiveData;}
    public MutableLiveData<Boolean> getLogOutMutableLiveData() {return logOutMutableLiveData;}
}

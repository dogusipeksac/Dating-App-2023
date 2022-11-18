package com.kube.datingapp2023.authentication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.kube.datingapp2023.authentication.model.AppRepository;

public class RegisterViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private MutableLiveData<FirebaseUser> userMutableLiveData;

    public RegisterViewModel(Application application) {
        super(application);
        appRepository=new AppRepository(application);
        userMutableLiveData=appRepository.getUserMutableLiveData();
    }
    public void register(String email,String password){
        appRepository.register(email,password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}

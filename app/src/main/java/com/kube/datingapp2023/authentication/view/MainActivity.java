package com.kube.datingapp2023.authentication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;
import com.kube.datingapp2023.BaseActivity;
import com.kube.datingapp2023.authentication.view.LoginActivity;
import com.kube.datingapp2023.authentication.view.RegisterActivity;
import com.kube.datingapp2023.authentication.viewmodel.LoginViewModel;
import com.kube.datingapp2023.authentication.viewmodel.MainViewModel;
import com.kube.datingapp2023.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    //viewbinding ile findviewbyid kullanmadan direk xmle ulaşabiliriz.
    ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        init();
        initObservable();
        initButtons();
    }

    public void  init(){
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }
    public void  initObservable(){
        mainViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser!=null){
                binding.infoTv.setText("İçeridesin:"+firebaseUser.getEmail());
            }
        });
        mainViewModel.getLogOutMutableLiveData().observe(this, logOut -> {
            if (logOut){
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
    public void initButtons() {
        binding.signOutButton.setOnClickListener(view -> mainViewModel.logOut());
    }
}
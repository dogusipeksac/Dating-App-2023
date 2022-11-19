package com.kube.datingapp2023.authentication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kube.datingapp2023.BaseActivity;
import com.kube.datingapp2023.MainActivity;
import com.kube.datingapp2023.authentication.viewmodel.LoginViewModel;
import com.kube.datingapp2023.authentication.viewmodel.RegisterViewModel;
import com.kube.datingapp2023.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
        initObservable();
        initButtons();

    }
    public void  init(){
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        progressdialog.setTitle("Giriş yapılıyor....");
    }
    public void  initObservable(){
        loginViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.getLogInProgressMutableLiveData().observe(this, logInBool -> {
            if (logInBool){
                progressdialog.show();
            }else{
                progressdialog.hide();
            }
        });
    }
    public void initButtons() {
        //buralar düzelticek base acitivty içinde yazılacak sayfa geçişleri
        binding.registerTv.setOnClickListener(view -> {
           startActivity(new Intent(this,RegisterActivity.class));
        });
        binding.loginButton.setOnClickListener(view -> loginViewModel.login(binding.emailEt.getText().toString(), binding.passwordEt.getText().toString()));
    }

}
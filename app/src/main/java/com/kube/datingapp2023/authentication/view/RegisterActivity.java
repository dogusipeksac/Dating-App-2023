package com.kube.datingapp2023.authentication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kube.datingapp2023.BaseActivity;
import com.kube.datingapp2023.MainActivity;
import com.kube.datingapp2023.authentication.viewmodel.RegisterViewModel;
import com.kube.datingapp2023.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding binding;

    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        init();
        initObservable();
        initButtons();
    }

    public void init() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        progressdialog.setTitle("Kayıt olunuyor....");
    }

    public void initObservable() {
        registerViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
            }
        });
        registerViewModel.getRegisterInProgressMutableLiveData().observe(this, registerInBool -> {
            if (registerInBool) {
                progressdialog.show();
            } else {
                progressdialog.hide();

            }
        });
    }

    public void initButtons() {
        binding.registerButton.setOnClickListener(view -> registerViewModel.register(binding.emailEt.getText().toString(), binding.passwordEt.getText().toString()));
    }
}
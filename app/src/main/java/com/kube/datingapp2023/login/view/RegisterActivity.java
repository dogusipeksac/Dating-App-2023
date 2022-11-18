package com.kube.datingapp2023.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.kube.datingapp2023.BaseActivity;
import com.kube.datingapp2023.R;
import com.kube.datingapp2023.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }
}
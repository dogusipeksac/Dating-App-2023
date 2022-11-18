package com.kube.datingapp2023;

import android.os.Bundle;
import android.view.View;

import com.kube.datingapp2023.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {
    //viewbinding ile findviewbyid kullanmadan direk xmle ulaşabiliriz.
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
    }
}
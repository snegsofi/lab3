package com.example.ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignInFragment signInFragment=new SignInFragment();
        FragmentManager fragmentManager =getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.mycontainer,signInFragment)
                .commit();

    }

}
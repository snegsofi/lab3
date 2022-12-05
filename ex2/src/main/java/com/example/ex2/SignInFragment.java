package com.example.ex2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInFragment extends Fragment {


    Button signInBtn;
    Button signUpBtn;
    EditText passwordTxt;
    EditText emailTxt;
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._]+@[a-z0-9]+\\.[a-z]{1,3}$", Pattern.CASE_INSENSITIVE);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.sighin_screen,container,false);


        signInBtn=view.findViewById(R.id.signIn_Button);
        signUpBtn=view.findViewById(R.id.signUp_button);
        emailLayout=view.findViewById(R.id.emailContainer);
        emailTxt=view.findViewById(R.id.emailEditText);
        passwordTxt=view.findViewById(R.id.passwordEditText);
        passwordLayout=view.findViewById(R.id.passwordContainer);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate(emailTxt.getText().toString())){
                    showError(emailLayout,"Invalid mail");
                }
                else if(passwordTxt.getText().toString().isEmpty()){
                    showError(passwordLayout,"Field empty");
                }
                else{
                    Intent intent=new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

       signUpBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SignUpFragment signUpFragment=new SignUpFragment();
               FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager
                       .beginTransaction();
               fragmentTransaction.replace(R.id.mycontainer, signUpFragment);
               fragmentTransaction.commit();

           }
       });

        emailTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!emailTxt.getText().toString().isEmpty() && validate(emailTxt.getText().toString())){
                    hideError(emailLayout);
                }
            }
        });

        passwordTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!passwordTxt.getText().toString().isEmpty()){
                    hideError(passwordLayout);
                }
            }
        });

        return view;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private void showError(TextInputLayout textInputLayout, String string) {
        textInputLayout.setError(string);
    }

    private void hideError(TextInputLayout textInputLayout) {
        textInputLayout.setError(null);
    }

}

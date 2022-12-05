package com.example.ex2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.SignalThresholdInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.BaseProgressIndicator;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    Button signInBtn;
    Button signUpBtn;
    EditText passwordTxt;
    EditText emailTxt;
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[a-z0-9._]+@[a-z0-9]+\\.[a-z]{1,3}$", Pattern.CASE_INSENSITIVE);

    private static final String TAG = "EmailPassword";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sighin_screen);

        signInBtn=findViewById(R.id.signIn_Button);
        signUpBtn=findViewById(R.id.signUp_button);
        emailLayout=findViewById(R.id.emailContainer);
        emailTxt=findViewById(R.id.emailEditText);
        passwordTxt=findViewById(R.id.passwordEditText);
        passwordLayout=findViewById(R.id.passwordContainer);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);





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
                    Intent intent=new Intent(SignInActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);


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
    }

    private static boolean validate(String emailStr) {
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


/*
Учебная практика retrofit

//private static final String BASE_URL="http://185.221.162.233/";

Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    Api json = retrofit.create(Api.class);
                    Log.d("Code",emailTxt.getText().toString()+passwordTxt.getText().toString());
                    Call<Token> call = json.getLogin(emailTxt.getText().toString(),passwordTxt.getText().toString());
                    call.enqueue(new Callback<Token>() {
                        @Override
                        public void onResponse(Call<Token> call, Response<Token> response) {
                            if (!response.isSuccessful()) {
                                if(response.code()==404){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Пользователь не найден", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                Log.e("Code", "ex2 OnResponce: " + response.code());
                                return;
                            }
                            if (response.isSuccessful()) {
                                Toast toast = Toast.makeText(getApplicationContext(), "Вход выполнен", Toast.LENGTH_SHORT);
                                toast.show();
                                Log.i("Code", "ex2 OnResponce: " + response.code());
                                Intent intent=new Intent(SignInActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                return;
                            }
                        }

                        @Override
                        public void onFailure(Call<Token> call, Throwable t) {
                            if(t.getMessage().equals("Failed to connect to /185.221.162.233:80")){
                                Toast toast = Toast.makeText(getApplicationContext(), "Отстутсвует подключение к интернету", Toast.LENGTH_SHORT);
                                toast.show();
                                return;
                            }
                            Log.e("Code", "ex2 OnFailure: " + t.getMessage());
                        }
                    });
 */



/*
Учебная практика. Задача по firebase

// [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]


в методе onCreate{

    // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
}



    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Email sent
                    }
                });
        // [END send_email_verification]
    }

    private void reload() { }

    private void updateUI(FirebaseUser user) {

    }
* * */
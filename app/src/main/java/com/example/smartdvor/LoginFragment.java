package com.example.smartdvor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFragment extends Fragment {

    EditText editTextPhoneNumber;
    EditText editTextPassword;

    Button btnLogin;

    TextInputLayout textInputLayoutPhoneNumber;
    TextInputLayout textInputLayoutPassword;

    com.example.dvor.SmartDvorDatabaseHelper sqliteHelper;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        initViews();

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (validate()) {
//                    String PhoneNumber = editTextPhoneNumber.getText().toString();
//                    String Password = editTextPassword.getText().toString();
//
//                    User currentUser = sqliteHelper.Authenticate(new User(null, PhoneNumber, Password, null, null, null));
//
//                    if (currentUser != null) {
//                        Snackbar.make(btnLogin, "Sucess Logged", Snackbar.LENGTH_LONG).show();
//                        // Sart home activity here
//                    } else {
//                        Snackbar.make(btnLogin, "Unsacess logged. Try again", Snackbar.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onLoginButtonClick(View v) {

    }

//    private void initViews() {
//        editTextPhoneNumber = editTextPhoneNumber.findViewById(R.id.et_phoneNumber);
//        editTextPassword = editTextPassword.findViewById(R.id.et_password);
//        textInputLayoutPhoneNumber = textInputLayoutPhoneNumber.findViewById(R.id.textInputLayoutPhoneNumber);
//        textInputLayoutPassword = textInputLayoutPassword.findViewById(R.id.textInputLayoutPassword);
//        btnLogin = btnLogin.findViewById(R.id.btn_login);
//    }
//
//    public boolean validate() {
//        boolean valid = false;
//
//        String phoneNumber = editTextPhoneNumber.getText().toString();
//        String password = editTextPassword.getText().toString();
//
//        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
//            valid = false;
//            textInputLayoutPhoneNumber.setError("Please enter valid email.");
//        } else {
//            valid = true;
//            textInputLayoutPhoneNumber.setError(null);
//        }
//
//        if (password.isEmpty()) {
//            valid = false;
//            textInputLayoutPassword.setError("Enter valid password");
//        } else {
//            if (password.length() > 5) {
//                valid = true;
//                textInputLayoutPassword.setError(null);
//            } else {
//                valid = false;
//                textInputLayoutPassword.setError("Password should be more then ");
//            }
//        }
//
//        return valid;
//    }

}
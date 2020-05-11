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
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.dvor.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.Objects;


public class LoginFragment extends Fragment {

    EditText editTextPhoneNumber;
    EditText editTextPassword;

    Button btnLogin;

    TextInputLayout textInputLayoutPhoneNumber;
    TextInputLayout textInputLayoutPassword;

    DatabaseHelper databaseHelper;
//
//    com.example.dvor.SmartDvorDatabaseHelper sqliteHelper;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment

//        initViews();

        databaseHelper = new DatabaseHelper(this.requireContext());


        editTextPhoneNumber = rootView.findViewById(R.id.et_signin_phoneNumber);
        editTextPassword = rootView.findViewById(R.id.et_signin_password);

//        textInputLayoutPhoneNumber = rootView.findViewById(R.id.textInputLayoutSignInPassword);
//        textInputLayoutPassword = rootView.findViewById(R.id.textInputLayoutSignInPassword);

//        editTextPassword = Objects.requireNonNull(textInputLayoutPassword.getEditText()).findViewById(R.id.et_signin_password);
//        editTextPhoneNumber = Objects.requireNonNull(textInputLayoutPhoneNumber.getEditText()).findViewById(R.id.et_signin_phoneNumber);

//        editTextPassword = textInputLayoutPassword.getEditText().findViewById(R.id.et_signin_password);
//        editTextPhoneNumber = textInputLayoutPhoneNumber.getEditText().findViewById(R.id.et_signin_phoneNumber);

        btnLogin = rootView.findViewById(R.id.btn_signin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (validate()) {


                    String PhoneNumber = editTextPhoneNumber.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    databaseHelper.insertClientsData(new User(PhoneNumber, Password, null, null, null));

//                    User currentUser = sqliteHelper.Authenticate(new User(null, PhoneNumber, Password, null, null, null));

//                    if (currentUser != null) {
//                        Snackbar.make(btnLogin, "Sucess Logged", Snackbar.LENGTH_LONG).show();
//                        // Sart home activity here
//                    } else {
//                        Snackbar.make(btnLogin, "Unsacess logged. Try again", Snackbar.LENGTH_LONG).show();
//                    }
//                }
            }
        });
        return rootView;
    }



//    private void initViews() {
//        editTextPhoneNumber = editTextPhoneNumber.findViewById(R.id.et_phoneNumber);
//        editTextPassword = editTextPassword.findViewById(R.id.et_password);
//        textInputLayoutPhoneNumber = textInputLayoutPhoneNumber.findViewById(R.id.textInputLayoutPhoneNumber);
//        textInputLayoutPassword = textInputLayoutPassword.findViewById(R.id.textInputLayoutPassword);
//        btnLogin = btnLogin.findViewById(R.id.btn_signin);
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
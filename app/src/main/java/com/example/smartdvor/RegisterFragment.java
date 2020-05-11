package com.example.smartdvor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dvor.SmartDvorDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment {

//    EditText edPhoneNumber;
//    EditText edPassword;
//    EditText edStreet;
//    EditText edHouseNumber;
//    EditText edApartment;
//
//    TextInputLayout textInputLayoutPhoneNumber;
//    TextInputLayout textInputLayoutPassword;
//    TextInputLayout textInputLayoutStreet;
//    TextInputLayout textInputLayoutHouseNumber;
//    TextInputLayout textInputLayoutApartment;
//
//    Button buttonSignUp;
//
//    SmartDvorDatabaseHelper sqlitehelper;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        sqlitehelper = new SmartDvorDatabaseHelper(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onRegisterButtonClick(View v) {

    }

}
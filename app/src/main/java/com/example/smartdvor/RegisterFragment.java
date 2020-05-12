package com.example.smartdvor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dvor.SmartDvorDatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;


public class RegisterFragment extends Fragment {

    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private EditText editTextStreet;
    private EditText editTextHouseNumber;
    private EditText editTextApartment;

    private Button btnRegister;

//    TextInputLayout textInputLayoutPhoneNumber;
//    TextInputLayout textInputLayoutPassword;
//    TextInputLayout textInputLayoutStreet;
//    TextInputLayout textInputLayoutHouseNumber;
//    TextInputLayout textInputLayoutApartment;

    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;
    private SQLiteDatabase db;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

        editTextPhoneNumber = rootView.findViewById(R.id.et_phoneNumber);
        editTextPassword = rootView.findViewById(R.id.et_password);
        editTextStreet = rootView.findViewById(R.id.et_street);
        editTextHouseNumber = rootView.findViewById(R.id.et_house_number);
        editTextApartment = rootView.findViewById(R.id.et_apartment);

        btnRegister = rootView.findViewById(R.id.btn_singup);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String PhoneNumber = editTextPhoneNumber.getText().toString();
                String Password = editTextPassword.getText().toString();
                String Street = editTextStreet.getText().toString();
                String HouseNumber = editTextHouseNumber.getText().toString();
                String Apartment = editTextApartment.getText().toString();

                db = smartDvorDatabaseHelper.getWritableDatabase();

                boolean insertData = smartDvorDatabaseHelper.insertClientsData(db, PhoneNumber, Password, Street, HouseNumber, Apartment);

                if (insertData) {
                    Toast.makeText(RegisterFragment.this.requireContext(), "Succes sign up", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RegisterFragment.this.requireContext(), "Wrong sign up", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
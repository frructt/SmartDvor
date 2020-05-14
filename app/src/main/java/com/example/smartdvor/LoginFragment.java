package com.example.smartdvor;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dvor.SmartDvorDatabaseHelper;

public class LoginFragment extends Fragment {

    private TextView txtv1;
    private TextView txtv2;

    private EditText editTextPhoneNumber;
    private EditText editTextPassword;

    private Button btnLogin;

    private SQLiteDatabase db;
    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

        editTextPhoneNumber = rootView.findViewById(R.id.et_signin_phoneNumber);
        editTextPassword = rootView.findViewById(R.id.et_signin_password);

        btnLogin = rootView.findViewById(R.id.btn_signin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PhoneNumber = editTextPhoneNumber.getText().toString();
                String Password = editTextPassword.getText().toString();

                db = smartDvorDatabaseHelper.getReadableDatabase();

                boolean checkUser = smartDvorDatabaseHelper.checkCorrectUsersAuthentificationData(db, PhoneNumber, Password);

                if (checkUser) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginFragment.this.requireContext(), "You do not have an account yet", Toast.LENGTH_LONG).show();
                }
            }
        });

//        showData(rootView);

        return rootView;
    }

//    private void showData(@NonNull View view) {
//
//        Button btnShow = view.findViewById(R.id.btn_show);
//
//        txtv1 = view.findViewById(R.id.txtPhoneNumber);
//        txtv2 = view.findViewById(R.id.txtPassword);
//
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    //Код чтения данных из базы
//                    SQLiteDatabase db = smartDvorDatabaseHelper.getReadableDatabase();
//                    String product = "";
//                    String product1 = "";
//                    Cursor cursor = db.query("CLIENTS", new String[]{"_id", "phoneNumber", "password", "street", "houseNumber", "apartNumber"}, "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
//                    cursor.moveToFirst();
//                    while (!cursor.isAfterLast()) {
//                        product += cursor.getString(1) + " | ";
//                        product1 += cursor.getString(2) + " | ";
//                        txtv1.setText(product);
//                        txtv2.setText(product1);
//                        cursor.moveToNext();
//                    }
//                    cursor.close();
//                } catch (SQLiteException e) {
//                    Toast.makeText(LoginFragment.this.requireContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


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
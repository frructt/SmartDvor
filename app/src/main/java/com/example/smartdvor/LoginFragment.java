package com.example.smartdvor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.strictmode.SqliteObjectLeakedViolation;
import android.provider.ContactsContract;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dvor.DatabaseHelper;
import com.example.dvor.SQLiteDatabaseHelper;
import com.example.dvor.SmartDvorDatabaseHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class LoginFragment extends Fragment {

    TextView txtv1;
    TextView txtv2;

    EditText editTextPhoneNumber;
    EditText editTextPassword;

    Button btnLogin;
    Button btnShow;

    TextInputLayout textInputLayoutPhoneNumber;
    TextInputLayout textInputLayoutPassword;

//    DatabaseHelper databaseHelper;
//
    SQLiteDatabaseHelper sqLiteDatabaseHelper;
    private SQLiteDatabase db;
    private SmartDvorDatabaseHelper smartDvorDatabaseHelper;
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

//        sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this.requireContext());
        smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this.requireContext());

//        sqLiteDatabaseHelper.insertClientsData("+79991234567", "pswd1", "street", "2a", 10);

        editTextPhoneNumber = rootView.findViewById(R.id.et_signin_phoneNumber);
        editTextPassword = rootView.findViewById(R.id.et_signin_password);

        btnLogin = rootView.findViewById(R.id.btn_signin);
        btnShow = rootView.findViewById(R.id.btn_show);

//        textInputLayoutPhoneNumber = rootView.findViewById(R.id.textInputLayoutSignInPassword);
//        textInputLayoutPassword = rootView.findViewById(R.id.textInputLayoutSignInPassword);

//        editTextPassword = Objects.requireNonNull(textInputLayoutPassword.getEditText()).findViewById(R.id.et_signin_password);
//        editTextPhoneNumber = Objects.requireNonNull(textInputLayoutPhoneNumber.getEditText()).findViewById(R.id.et_signin_phoneNumber);

//        editTextPassword = textInputLayoutPassword.getEditText().findViewById(R.id.et_signin_password);
//        editTextPhoneNumber = textInputLayoutPhoneNumber.getEditText().findViewById(R.id.et_signin_phoneNumber);


//        try {
//            sqLiteDatabaseHelper.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (validate()) {


                String PhoneNumber = editTextPhoneNumber.getText().toString();
                String Password = editTextPassword.getText().toString();



//                sqLiteDatabaseHelper.openDataBase();
               db = smartDvorDatabaseHelper.getWritableDatabase();

                boolean insertData = smartDvorDatabaseHelper.insertClientsData(db, PhoneNumber, Password, "street1", "2", "4");

                if (insertData) {
                    Toast.makeText(LoginFragment.this.requireContext(), "Succes data insert", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginFragment.this.requireContext(), "Wrong data insert", Toast.LENGTH_LONG).show();
                }

//                sqLiteDatabaseHelper.close();

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

//        mDb = sqLiteDatabaseHelper.getWritableDatabase();

        //Найдем компоненты в XML разметке
        txtv1 = rootView.findViewById(R.id.txtPhoneNumber);
        txtv2 = rootView.findViewById(R.id.txtPassword);
//        textView2 = (TextView) findViewById(R.id.textView2);

//        editTextPhoneNumber = editTextPhoneNumber.findViewById(R.id.et_signin_phoneNumber);
//        editTextPassword = editTextPassword.findViewById(R.id.et_signin_password);
//        btnLogin = btnLogin.findViewById(R.id.btn_signin);

        //Пропишем обработчик клика кнопки
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String product = "";
//                String product1 = "";
//                Cursor cursor = mDb.query("CLIENTS", new String[] {"_id", "phoneNumber", "password","street","houseNumber","apartNumber"},"_id = ?", new String[] {Integer.toString(1)}, null, null, null);
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    product += cursor.getString(0) + " | ";
//                    product1 += cursor.getString(1) + " | ";
//                    txtv1.setText(product);
//                    txtv2.setText(product1);
//                    cursor.moveToNext();
//                }
//                cursor.close();
//            }
//        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Код чтения данных из базы
                    SQLiteDatabase db = smartDvorDatabaseHelper.getReadableDatabase();
                    String product = "";
                    String product1 = "";
                    Cursor cursor = db.query("CLIENTS", new String[]{"_id", "phoneNumber", "password", "street", "houseNumber", "apartNumber"}, "_id = ?", new String[]{Integer.toString(1)}, null, null, null);
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        product += cursor.getString(1) + " | ";
                        product1 += cursor.getString(2) + " | ";
                        txtv1.setText(product);
                        txtv2.setText(product1);
                        cursor.moveToNext();
                    }
                    cursor.close();
                } catch (SQLiteException e) {
                    Toast.makeText(LoginFragment.this.requireContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        addData();
        return rootView;
    }

//    public void addData() {
//        sqLiteDatabaseHelper = new SQLiteDatabaseHelper(this.requireContext());
//
//        mDb = sqLiteDatabaseHelper.getWritableDatabase();
//
//        //Найдем компоненты в XML разметке
//        txtv1 = txtv1.findViewById(R.id.txtPhoneNumber);
//        txtv2 = txtv2.findViewById(R.id.txtPassword);
////        textView2 = (TextView) findViewById(R.id.textView2);
//
////        editTextPhoneNumber = editTextPhoneNumber.findViewById(R.id.et_signin_phoneNumber);
////        editTextPassword = editTextPassword.findViewById(R.id.et_signin_password);
//        btnLogin = btnLogin.findViewById(R.id.btn_signin);
//
//        //Пропишем обработчик клика кнопки
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String product = "";
//                String product1 = "";
//                Cursor cursor = mDb.query("CLIENTS", new String[] {"_id", "phoneNumber", "password","street","houseNumber","apartNumber"},"_id = ?", new String[] {Integer.toString(1)}, null, null, null);
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    product += cursor.getString(0) + " | ";
//                    product1 += cursor.getString(1) + " | ";
//                    txtv1.setText(product);
//                    txtv2.setText(product1);
//                    cursor.moveToNext();
//                }
//                cursor.close();
//            }
//        });
//    }


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
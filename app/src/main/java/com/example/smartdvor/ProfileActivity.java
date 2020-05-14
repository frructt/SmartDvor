package com.example.smartdvor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    ArrayList<String> guestCars = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    ArrayList<String> selectedGuestCars = new ArrayList<String>();
    ListView guestCarsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        tableLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        guestCars.add("HG432H");
        guestCars.add("NG189R");

        guestCarsList = (ListView) findViewById(R.id.guestCarsList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, guestCars) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);

                // Generate ListView Item using TextView
                return view;
            }
        };
        guestCarsList.setAdapter(adapter);

        guestCarsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String car = adapter.getItem(position);
                if (guestCarsList.isItemChecked(position)) {
                    selectedGuestCars.add(car);
                }
                else {
                    selectedGuestCars.remove(car);
                }
            }
        });

        Button btn_signout = (Button) findViewById(R.id.btn_signout);

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addCar(View view) {
        TextInputEditText editTextGuestCar = (TextInputEditText) findViewById(R.id.et_guest_car);
        String car = Objects.requireNonNull(editTextGuestCar.getText()).toString();

        if (!car.isEmpty() && !guestCars.contains(car)) {
            adapter.add(car);
//            Toast.makeText(this, ("SUCCESS" + car.contains(car)), Toast.LENGTH_LONG).show();
            editTextGuestCar.setText("");
            adapter.notifyDataSetChanged();
        }
        else {
//            Toast.makeText(this, ("FAILED" + guestCars.contains(car) + "-----" + car.isEmpty()), Toast.LENGTH_LONG).show();
        }

    }

    public void removeCar(View view) {
        for (int i = 0; i < selectedGuestCars.size(); i++) {
            adapter.remove(selectedGuestCars.get(i));
        }

        guestCarsList.clearChoices();
        selectedGuestCars.clear();
        adapter.notifyDataSetChanged();
    }





}

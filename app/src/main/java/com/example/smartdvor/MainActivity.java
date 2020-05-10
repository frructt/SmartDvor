package com.example.smartdvor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);

        AuthenticationPagerAdapter pagerAdapter = new AuthenticationPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragmet(new LoginFragment());
        pagerAdapter.addFragmet(new RegisterFragment());
        viewPager.setAdapter(pagerAdapter);
    }

    class AuthenticationPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentList = new ArrayList<>();

        public AuthenticationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        void addFragmet(Fragment fragment) {
            fragmentList.add(fragment);
        }
    }
}
/*
public class MainActivity extends AppCompatActivity {
    //Объявим переменные компонентов
    Button button;
    TextView textView;
    TextView textView1;
    TextView textView2;

    //Переменная для работы с БД
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDBHelper = new DatabaseHelper(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
            mDb = mDBHelper.getWritableDatabase();

        //Найдем компоненты в XML разметке
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);


        //Пропишем обработчик клика кнопки
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = "";
                String product1 = "";
                Cursor cursor = mDb.query("CLIENTS", new String[] {"_id", "phoneNumber", "password","street","houseNumber","apartNumber"},"_id = ?", new String[] {Integer.toString(1)}, null, null, null);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(0) + " | ";
                    product1 += cursor.getString(1) + " | ";
                    textView.setText(product);
                    textView1.setText(product1);
                    cursor.moveToNext();
                }
                cursor.close();
                           }
        });
    }
}
//
//    SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
//          try{
//        SQLiteDatabaseHelper db = starbuzzDatabaseHelper.getReadableDatabase();
//        Cursor cursos = db.query("DRINK", new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "_id = ?", new String[] {Integer.toString(drinkId)}, null, null, null);
//        }catch(SQLiteException e){
//        Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
//        toast.show();
//        }
* */
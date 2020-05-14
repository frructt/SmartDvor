package com.example.dvor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartdvor.User;

import java.util.HashMap;
import java.util.Map;

//Тестовый класс. Бд создается внутри и здесь же она удаляется после работы.

public class SmartDvorDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dvor"; //Database name
    private static final int DB_VERSION = 1;     //Database version

    public SmartDvorDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Метод onCreate вызывается при первоначальном создании базы данных
    //мы используем его для создания таблицы и вставки данных
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Данный метод выдается, когда возникает необходимость в обновлении базы данных.
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public boolean insertClientsData(SQLiteDatabase db, String phoneNumber, String password, String street, String houseNumber, String apartNumber) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phoneNumber", phoneNumber); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
        contentValues.put("password", password);
        contentValues.put("street", street);
        contentValues.put("houseNumber", houseNumber);
        contentValues.put("apartNumber", apartNumber);
        long result = db.insert("clients", null, contentValues); //кладем в таблицу наши поля.
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE CLIENTS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "PHONENUMBER TEXT, " +
                    "PASSWORD TEXT, " +
                    "STREET TEXT, " +
                    "HOUSENUMBER TEXT, " +
                    "APARTNUMBER TEXT);");
        }

    }

    public boolean checkCorrectUsersAuthentificationData(SQLiteDatabase db, String phoneNumber, String password) {
        //Перед тем, как зайти в метод. Необходимо убедиться, что б.д. имеет SQLiteDatabase db = SmartDvorDatabaseHelper.getWritableDatabase();
        String dbPhone = null;
        String dbPass = null;
        Cursor cursor = db.query("CLIENTS", new String[]{"PHONENUMBER", "PASSWORD"}, "PHONENUMBER=?", new String[]{phoneNumber}, null, null, null);
        if(cursor.moveToFirst()){
            dbPhone = cursor.getString(0);
            dbPass = cursor.getString(1);
        }
        if (phoneNumber.equals(dbPhone) && password.equals(dbPass)) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }

    }

    public User getAllUserDataInformation(SQLiteDatabase db, String phoneNumber){
        //Перед тем, как зайти в метод. Необходимо убедиться, что б.д. имеет SQLiteDatabase db = SmartDvorDatabaseHelper.getReadableDatabase();
        String _id = null;
        String street = null;
        String password = null;
        String houseNumber = null;
        String apartNumber = null;
        Cursor cursor = db.query("CLIENTS", new String[] {"_id","PHONENUMBER","PASSWORD","STREET","HOUSENUMBER","APARTNUMBER"}, "PHONENUMBER=?", new String[]{phoneNumber}, null, null, null);
        if(cursor.moveToFirst()){
            _id = cursor.getString(0);
            phoneNumber = cursor.getString(1);
            password = cursor.getString(2);
            street = cursor.getString(3);
            houseNumber = cursor.getString(4);
            apartNumber = cursor.getString(5);
        }
        return new User(phoneNumber,password,street,houseNumber,apartNumber);
    }
}


/*
* Выборка всех данных из таблицы
* Cursor cursor = db.query("CLIENTS", new String[] {_id,PHONENUMBER,PASSWORD,STREET,HOUSENUMBER,APARTNUMBER}, null, null, null, null, null,)
* Выборка по условию (вернуть номер телефона и пароль по телефону)
* Cursor cursor = db.query("CLIENTS", new String[] {PHONENUMBER,PASSWORD}, "NAME=?", new String[] {user.phoneNumber}, null, null, null,)
* if(cursor.moveToFirst()){
* String phoneNumber = cursor.getString(0);
* String password = cursor.getString(0);
* }
*/
/*
Андрей. Это тебе надо вставить в код, откуда ты будешь брать б.д.
SmartDvorDatabaseHelper smartDvorDatabaseHelper = new SmartDvorDatabaseHelper(this);
//getReadableDatabase() -> только для чтения
//getWritableDatabase() -> для выполнения обновления данных
//SQLiteDatabase db = SmartDvorDatabaseHelper.getReadableDatabase();
//SQLiteDatabase db = SmartDvorDatabaseHelper.getWritableDatabase();
try{
    SQLiteDatabase db = SmartDvorDatabaseHelper.getReadableDatabase();
    //Код чтения данных из базы
    Cursor cursor = db.query("CLIENTS", new String[] {"_id", "phoneNumber", "password","street","houseNumber","apartNumber"},"_id = ?", new String[] {Integer.toString(1)}, null, null, null);
    cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    product += cursor.getString(0) + " | ";
                    product1 += cursor.getString(1) + " | ";
                    textView.setText(product);
                    textView1.setText(product1);
                    cursor.moveToNext();
                }
     cursor.close();
    } catch(SQLiteException){
    Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
    toast.show(); //в этой строке уведомление выводится на экран
}
 */
/*
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
*/
/*
*   Для обновления данных необходимо заново создать ContentValues
*   и положить в него то поле, которое необходимо обновить, а далее применить метод update()
*   update из класса SQLiteDatabase исп. для обновления данных.
 */
/*
* ContentValues drinkValues = new ContentValues();
* drinkValues.put("DESCRIPTION", "Tasty");
* db.update("DRINK", drinkValues, "NAME = ? OR DESCRIPTION = ?", new String[] {"Latte", "Out best drip coffee"});
* В поля "NAME = ? OR DESCRIPTION = ?" подставляются значения "Latte", "Out best drip coffee"
* Если они совпадают, то drinkValues заменяет данные, которые он собрал.
* db.update("DRINK", drinkValues, "_id = ?", new String[] {"Integer.toString(1)"});
* Удаление
* db.delete("DRINK", "NAME = ?", new String[] {"Latte"});
* Удалятеся все поле со всеми данными.
*
 */

//Изменение таблицы
//Добавление нового столбца
/*
* ALTER TABLE DRINK - ИМЯ ТАБЛИЦЫ
* ADD COLUMN FAVORITE NUMERIC - ДОБАВЛЯЕМЫЙ СТОЛБЕЦ
*/
//Изменение имени таблицы
/*
* ALTER TABLE DRINK - ИЗМЕНЕНИЕ ТАБЛИЦЫ
* RENAME TO FOO - НОВОЕ ИМЯ ТАБЛИЦЫ
 */
//Удаление таблицы
/*
* DROP TABLE DRINK - Имя удаляемой таблицы
* 688
*/
//execSQL выполняет команду
/*
*db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
* */
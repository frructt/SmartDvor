package com.example.dvor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
       /*
       //db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, IMAGE_TESOURCE_ID INTEGER);");
       // insertProfilesData(db, "UserName", "Some Info about Me", R.drawable.ic_launcher_background); //Метод, где происходит добавление данных в базу данных.
       */
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Данный метод выдается, когда возникает необходимость в обновлении базы данных.
        updateMyDatabase(db, 0, DB_VERSION);
    }


    /*
     *Метод, в котором будет происходить постоянное добавление данных
     *Суть: пользователь кидает какие то поля данных, мы их принимаем
     * и закидываем в этот класс. Здесь в этом классе будет вызываться база данных и соответственно в нее
     *помещаться данные.
     *Все входные данные являются шаблонными. Необходимо разработать примерное видение и менять параметры
     *Дедлайн на определение базы данных - пятница 1 мая.
     */
//    private static void insertProfilesData(SQLiteDatabase db, String name, String description, int resourceId){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("NAME", name); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
//        contentValues.put("DESCRIPTION", description);
//        contentValues.put("IMAGE_RESOURCE_ID", resourceId);
//        db.insert("DRINK", null, contentValues);
//    }
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
//            insertProfilesData(db, "Latte", "Espresso and steamed milk", R.drawable.background_splash);
//            insertClientsData(db, "+71234567890", "User111", "Pushlinskaya","1", "1");
        }

//        if (oldVersion == 1) {
//            db.execSQL("CREATE TABLE CLIENTS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "PHONENUMBER TEXT, " +
//                    "PASSWORD TEXT, " +
//                    "STREET TEXT, " +
//                    "HOUSENUMBER TEXT, " +
//                    "APARTNUMBER TEXT);");
//        }
/*
        if(oldVersion < 2){
            //
            //                *   Код добавления нового столбца
            //                *   Этот код выполняется, в том случае,
            //                *   если у пользователя уже установлена
            //                *   версия 1 б.д.
            //
            //            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
            db.execSQL("CREATE TABLE CLIENTS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "PHONENUMBER TEXT, " +
                    "PASSWORD TEXT, " +
                    "STREET TEXT, "+
                    "HOUSENUMBER TEXT, "+
                    "APARTNUMBER TEXT);");
            //NUMERIC -> добавление числового столбца
        }
        */

    }

//    public boolean checkCorrectUsersAuthentificationData(SQLiteDatabase db, String phoneNumber, String password) {
//        //Перед тем, как зайти в метод. Необходимо убедиться, что б.д. имеет SQLiteDatabase db = SmartDvorDatabaseHelper.getWritableDatabase();
//        String dbPhone = null;
//        String dbPass = null;
//        Cursor cursor = db.query("CLIENTS", new String[]{"PHONENUMBER", "PASSWORD"}, "PHONENUMBER=?", new String[]{phoneNumber,password}, null, null, null);
//        if(cursor.moveToFirst()){
//            dbPhone = cursor.getString(0);
//            dbPass = cursor.getString(1);
//        }
////        if (phoneNumber.equals(dbPhone) && password.equals(dbPass)) {
////            cursor.close();
////            return true;
////        } else {
////            cursor.close();
////            return false;
////        }
//
//    }
    public Map<String, String> checkCorrectUsersAuthentificationData(SQLiteDatabase db, String phoneNumber, String password) {
        //Перед тем, как зайти в метод. Необходимо убедиться, что б.д. имеет SQLiteDatabase db = SmartDvorDatabaseHelper.getWritableDatabase();
        String dbPhone = null;
        String dbPass = null;
        Cursor cursor = db.query("CLIENTS", new String[]{"PHONENUMBER", "PASSWORD"}, "PHONENUMBER=?", new String[]{phoneNumber,password}, null, null, null);
        if(cursor.moveToFirst()){
            dbPhone = cursor.getString(0);
            dbPass = cursor.getString(1);
        }
//        if (phoneNumber.equals(dbPhone) && password.equals(dbPass)) {
//            cursor.close();
//            return true;
//        } else {
//            cursor.close();
//            return false;
//        }
        Map<String, String> getAllData = new HashMap<String, String>();
        getAllData.put("PHONENUMBER",dbPhone );
        getAllData.put("PASSWORD",dbPass );
        cursor.close();
        //Возвращается map
        return getAllData;

    }

    public Map<String, String> getAllUserDataInformation(SQLiteDatabase db, String phoneNumber, String password){
        //Перед тем, как зайти в метод. Необходимо убедиться, что б.д. имеет SQLiteDatabase db = SmartDvorDatabaseHelper.getReadableDatabase();
        String _id = null;
        String street = null;;
        String houseNumber = null;;
        String apartNumber = null;;
        Cursor cursor = db.query("CLIENTS", new String[] {"_id","PHONENUMBER","PASSWORD","STREET","HOUSENUMBER","APARTNUMBER"}, null, null, null, null, null);
        if(cursor.moveToFirst()){
            _id = cursor.getString(0);
            phoneNumber = cursor.getString(1);
            password = cursor.getString(2);
            street = cursor.getString(3);
            houseNumber = cursor.getString(4);
            apartNumber = cursor.getString(5);
        }
//        HashMap<String, Integer> myHashMap = new HashMap<String, Integer>(){{
//            put("a", 0);
//            put("b", 20);
//            put("d", 30);
//            put("c", 40);
//        }};
        Map<String, String> getAllData = new HashMap<String, String>();
        getAllData.put("_id",_id );
        getAllData.put("PHONENUMBER",phoneNumber);
        getAllData.put("PASSWORD",password);
        getAllData.put("STREET",street);
        getAllData.put("HOUSENUMBER",houseNumber);
        getAllData.put("APARTNUMBER",apartNumber);
        cursor.close();
        //Возвращается map
        return getAllData;
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
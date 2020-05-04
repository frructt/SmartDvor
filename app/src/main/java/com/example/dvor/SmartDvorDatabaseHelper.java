package com.example.dvor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.smartdvor.R;

//Тестовый класс. Бд создается внутри и здесь же она удаляется после работы.
//
public class SmartDvorDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dvor"; //Database name
    private static final int DB_VERSION = 1;     //Database version

    SmartDvorDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Метод onCreate вызывается при первоначальном создании базы данных
    //мы используем его для создания таблицы и вставки данных
    @Override
    public void onCreate(SQLiteDatabase db){
       /*
       //db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT, IMAGE_TESOURCE_ID INTEGER);");
       // insertProfilesData(db, "UserName", "Some Info about Me", R.drawable.ic_launcher_background); //Метод, где происходит добавление данных в базу данных.
       */
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Данный метод выдается, когда возникает необходимость в обновлении базы данных.
        updateMyDatabase(db, 0, DB_VERSION);
    }


    //Метод, в котором будет происходить постоянное добавление данных
    //Суть: пользователь кидает какие то поля данных, мы их принимаем
    // и закидываем в этот класс. Здесь в этом классе будет вызываться база данных и соответственно в нее
    //помещаться данные.
    //Все входные данные являются шаблонными. Необходимо разработать примерное видение и менять параметры
    //Дедлайн на определение базы данных - пятница 1 мая.
    private static void insertProfilesData(SQLiteDatabase db, String name, String description, int resourceId){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
        contentValues.put("DESCRIPTION", description);
        contentValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DRINK", null, contentValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " NAME TEXT, " +
                    "DESCRIPTION TEXT, " +
                    "IMAGE_RESOURCE_ID INTEGER);");
            insertProfilesData(db, "Latte", "Espresso and steamed milk", R.drawable.background_splash);
        }
        if(oldVersion < 2){
            //Код добавления нового столбца
            //Этот код выполняется, в том случае,
            //если у пользователя уже установлена
            //версия 1 б.д.
        }
    }

}

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
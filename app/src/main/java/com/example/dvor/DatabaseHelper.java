package com.example.dvor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import androidx.fragment.app.Fragment;
import com.example.smartdvor.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "info.db"; //Database name
    private static String DB_PATH = ""; //Path to db
    private static final int DB_VERSION = 1; //Database version


    public static SQLiteDatabase mDataBase;//static
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        //Get connection with db
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
        copyDataBase();
        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
//        public void updateDataBase(SQLiteDatabase db, int oldVersion, int newVersion) throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists()) {
                dbFile.delete();
            }
            copyDataBase();
            mNeedUpdate = false;
        }
//
//        if(oldVersion < 1){
//            db.execSQL("CREATE TABLE CLIENTS(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    " PHONENUMBER NVARCHAR, " +
//                    "PASSWORD NVARCHAR, " +
//                    "STREET NVARCHAR,"+
//                    "HOUSENUMBER NVARCHAR,"+
//                    "APARTNUMBER INTEGER);");
//
//            insertClientsData(db, "+79299323716", "User111", "Moskovskaya", "1", 228);
//        }
//        if(oldVersion < 2){
//            /*
//             *   Код добавления нового столбца
//             *   Этот код выполняется, в том случае,
//             *   если у пользователя уже установлена
//             *   версия 1 б.д.
//             */
//            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
//            //NUMERIC -> добавление числового столбца
//        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    //Добавить нового пользователя в б.д.
    public static void insertClientsData(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("PHONENUMBER", user.phoneNumber); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
        contentValues.put("PASSWORD", user.password);
        contentValues.put("STREET", user.street);
        contentValues.put("HOUSENUMBER", user.houseNumber);
        contentValues.put("APARTNUMBER", user.apartNumber);
        mDataBase.insert("CLIENTS", null, contentValues); //кладем в таблицу наши поля.
    }

    //Метод, который возвращает данные в проект
    public void proverka(User user){
        String phoneNumber = user.phoneNumber;
        String password = user.password;
        Cursor cursor = mDataBase.query("CLIENTS", new String[]{"PHONENUMBER","PASSWORD"},"PHONENUMBER=?",new String[]{user.phoneNumber}, null, null, null);
    }
}

//    public static void insertClientsData(SQLiteDatabase db, String phoneNumber, String password, String street, String houseNumber, int apartNumber){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("PHONENUMBER", phoneNumber); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
//        contentValues.put("PASSWORD", password);
//        contentValues.put("STREET", street);
//        contentValues.put("HOUSENUMBER", houseNumber);
//        contentValues.put("APARTNUMBER", apartNumber);
//        db.insert("CLIENTS", null, contentValues); //кладем в таблицу наши поля.
//    }
//    public static void insertClientsData(User user) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("PHONENUMBER", user.phoneNumber); //.put кладет в определенное поле данные. Надо таким образом заполнить все поля строки
//        contentValues.put("PASSWORD", user.password);
//        contentValues.put("STREET", user.street);
//        contentValues.put("HOUSENUMBER", user.houseNumber);
//        contentValues.put("APARTNUMBER", user.apartNumber);
//    //        db.insert("CLIENTS", null, contentValues); //кладем в таблицу наши поля.
//        // mDataBase.insert("CLIENTS", null, contentValues); //кладем в таблицу наши поля.
//    }

package Dbhepper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHepplper extends SQLiteOpenHelper {
    public MyHepplper(Context context){
        super(context,"Danhsachthi",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String tb_danhsach="CREATE TABLE tb_danhsachthi ( id       INTEGER PRIMARY KEY AUTOINCREMENT,   ngaythi  TEXT, ca       INTEGER, phongthi TEXT, tenmon   TEXT);";
            sqLiteDatabase.execSQL(tb_danhsach);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

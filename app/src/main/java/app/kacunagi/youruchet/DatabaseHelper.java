package app.kacunagi.youruchet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, MyConstants.DATABASE_NAME, null, MyConstants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Scores (_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (30), sum  DOUBLE, currency VARCHAR);");
        db.execSQL("CREATE TABLE Transfers (score  INTEGER,score_name VARCHAR(30), date   LONG,sum_transfer  DOUBLE,currency  VARCHAR,type_transfer BOOLEAN,comment  VARCHAR (30));");
        db.execSQL("CREATE TABLE Currencies (currencyName VARCHAR, value DOUBLE);");
        db.execSQL("CREATE TABLE CurentCurrency (current VARCHAR(3), value DOUBLE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        android.util.Log.v("Constants",
                "Upgrading database which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS Scores");
        db.execSQL("DROP TABLE IF EXISTS Transfers");
        db.execSQL("DROP TABLE IF EXISTS Currencies");
        db.execSQL("DROP TABLE IF EXISTS CurentCurrency");
        onCreate(db);
    }
}

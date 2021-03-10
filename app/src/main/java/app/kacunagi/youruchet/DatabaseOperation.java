package app.kacunagi.youruchet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperation {
    /**
     * Получить список валют
     * @param context - Контекст Activity
     */
    public static void getCurrenies(Context context){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        ArrayList<CurrencyElem> currencies = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query("Currencies", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                CurrencyElem currency = new CurrencyElem(cursor.getString(cursor.getColumnIndex("currencyName")),
                        cursor.getDouble(cursor.getColumnIndex("value")));
                currencies.add(currency);
            }while(cursor.moveToNext());
        }

        MyConstants.CurreciesList = currencies;
        dbhelper.close();
    }

    /**
     * Записать список валют в базу данных
     * @param context - Контекст Activity
     * @param currencies - Список валют
     */

    public static void setCurrencies(Context context, List<CurrencyElem> currencies){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        for (CurrencyElem ce : currencies){

            cv.put("currencyName", ce.getName());
            cv.put("value", ce.getCurs());

            Cursor cursor_check = db.rawQuery("SELECT value FROM Currencies WHERE currencyName = ?", new String[]{String.valueOf(ce.getName())}, null);

            if(cursor_check != null && cursor_check.getCount()>0) {
                cursor_check.moveToFirst();
                if (cursor_check.getDouble(cursor_check.getColumnIndex("value")) != ce.getCurs()) {
                    int updCount = db.update("Currencies", cv, "currencyName = ?", new String[]{String.valueOf(ce.getName())});
                }
            }
            else {
                long rowID = db.insert("Currencies", null, cv);
            }
        }

        cv.put("currencyName", "RUB");
        cv.put("value", 1.0000);

        Cursor cursor_check = db.rawQuery("SELECT value FROM Currencies WHERE currencyName = ?", new String[]{cv.get("currencyName").toString()}, null);
        if(cursor_check != null && cursor_check.getCount()>0) {
            cursor_check.moveToFirst();
            if (cursor_check.getDouble(cursor_check.getColumnIndex("value")) != Double.valueOf(cv.get("value").toString())) {
                int updCount = db.update("Currencies", cv, "currencyName = ?", new String[]{cv.get("currencyName").toString()});
            }
        }
        else {
            long rowID = db.insert("Currencies", null, cv);
        }


        dbhelper.close();
    }

    /**
     * Записать текущую валюту по умолчанию в базу данных
     * @param context - Контекст Activity
     * @param newCurent - Наименование текущей валюты по умолчанию
     * @param value - Значение курса текущей валюты
     */
    public static void setCurentCurrency(Context context, String newCurent, double value){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        cv.put("current", newCurent);
        cv.put("value", value);

        Cursor cursor_check = db.rawQuery("SELECT current FROM CurentCurrency", null);
        if(cursor_check != null && cursor_check.getCount()>0) {
            cursor_check.moveToFirst();
            if (cursor_check.getString(cursor_check.getColumnIndex("current")) != newCurent || cursor_check.getDouble(cursor_check.getColumnIndex("value"))!=value) {
                int updCount = db.update("CurentCurrency", cv, "current = ?", new String[]{cursor_check.getString(cursor_check.getColumnIndex("current"))});
            }
        }
        else {
            long rowID = db.insert("CurentCurrency", null, cv);
        }
        dbhelper.close();

    }

    /**
     * Получить текущую валюту по умолчанию и записать в базу данных
     * @param context - Контекст Activity
     */
    public static void getCurentCurrency(Context context){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query("CurentCurrency", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                MyConstants.Currency = cursor.getString(cursor.getColumnIndex("current"));
                MyConstants.ValueCurrency = cursor.getDouble(cursor.getColumnIndex("value"));
            }while (cursor.moveToNext());
        }
        dbhelper.close();
    }

    /**
     * Получить список счетов из базы данных
     * @param context - Контекст Activity
     */
    public static void getScores(Context context){
        ArrayList<Score> scores = new ArrayList<Score>();

        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query("Scores", null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            do{
                Score score = new Score(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getString(cursor.getColumnIndex("name")), cursor.getFloat(cursor.getColumnIndex("sum")), cursor.getString(cursor.getColumnIndex("currency")));
                scores.add(score);
            }while (cursor.moveToNext());
        }

        MyConstants.Scores = scores;

        dbhelper.close();
    }

    /**
     * Удаление счета из базы данных
     * @param context - Контекст Activity
     * @param id - идентификатор удаляемого счета
     */
    public static void deleteScore(Context context, int id){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int delCount = db.delete("Scores", "_id = " + id, null);
        dbhelper.close();
    }

    /**
     * Добавить счет в базу данных
     * @param context - Контекст Activity
     * @param name_score - Наименование счета
     * @param sum_score - Первоначальная сумма счета
     * @param currency - Валюта счета
     */
    public static void addScore(Context context , String name_score, float sum_score, String currency){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name_score);
        cv.put("sum", sum_score);
        cv.put("currency", currency);
        long rowID = db.insert("Scores", null, cv);
        dbhelper.close();
    }

    /**
     * Обновление записи счета в базе данных
     * @param context - Контекст Activity
     * @param id - Идентификатор счета
     * @param name - Наименование счета
     * @param sum - Новая сумма счета
     * @param sum_debit - Сумма перевода
     */
    public static void updateScore(Context context,int id, String name, double sum, float sum_debit){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("sum",  sum);
        int updCount = db.update("Scores", cv, "_id = ?", new String[]{String.valueOf(id)});
        dbhelper.close();
    }

    /**
     * Получение списка переводов из базы данных
     * @param context - Контекст Activity
     * @param score - Идентификатор счета
     * @param score_name -  Наименование счета
     * @param date - Дата перевода
     * @param sum_transfer - Сумма перевода
     * @param currency - Валюта перевода
     * @param type_transfer - Тип перевода
     *                      0 - Списание
     *                      1 - Пополнение
     * @param comment - Комментарий к переводу
     */
    public static void addTransfer(Context context, int score, String score_name, Long date, float sum_transfer, String currency, boolean type_transfer, String comment){
        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("score", score);
        cv.put("score_name", score_name);
        cv.put("date", date);
        cv.put("sum_transfer", sum_transfer);
        cv.put("currency", currency);
        cv.put("type_transfer", type_transfer);
        cv.put("comment", comment);
        long rowID = db.insert("Transfers", null, cv);
        dbhelper.close();
    }

    /**
     * Получение списка переводов
     * @param context - Контекст Activity
     */
    public static void getTransfers(Context context){

        ArrayList<HistoryElem> histories = new ArrayList<HistoryElem>();

        DatabaseHelper dbhelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.query("Transfers", null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            String sum_history;
            int score = cursor.getColumnIndex("score");
            int score_name = cursor.getColumnIndex("score_name");
            int type_transfer = cursor.getColumnIndex("type_transfer");
            int sum = cursor.getColumnIndex("sum_transfer");
            int currency_debit = cursor.getColumnIndex("currency");
            int comment = cursor.getColumnIndex("comment");
            int dateHistory = cursor.getColumnIndex("date");

            do{
                if(cursor.getInt(type_transfer)>0){
                    sum_history = "+"+cursor.getString(sum);
                }
                else{
                    sum_history = "-"+cursor.getString(sum);
                }
                HistoryElem historyElem = new HistoryElem(cursor.getInt(score), cursor.getString(score_name), sum_history,
                        cursor.getString(currency_debit), cursor.getInt(type_transfer) > 0, cursor.getString(comment), cursor.getLong(dateHistory));
                histories.add(historyElem);
            }while(cursor.moveToNext());
        }

        MyConstants.Histories = histories;
        dbhelper.close();
    }
}

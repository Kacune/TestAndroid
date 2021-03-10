package app.kacunagi.youruchet;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Класс содержащий методы заполнения Spinner
 */
public class MySpinnerAdapters {
    public static void setAdapterScore(Context context, Spinner spinner){
        ArrayAdapter<Score> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i=0; i<MyConstants.Scores.size(); i++){
            adapter.add(MyConstants.Scores.get(i));
        }

        spinner.setAdapter(adapter);
    }

    public static void setAdapterCurrencyElem(Context context, Spinner spinner){
        ArrayAdapter<CurrencyElem> adapter_currency = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        adapter_currency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i < MyConstants.CurreciesList.size(); i++){
            adapter_currency.add(MyConstants.CurreciesList.get(i));
        }

        spinner.setAdapter(adapter_currency);
    }
}

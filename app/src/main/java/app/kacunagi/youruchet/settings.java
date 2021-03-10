package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class settings extends AppCompatActivity {
    Spinner spinner_currency;
    TextView currentCurrency;
    Button saveSetings;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        currentCurrency = findViewById(R.id.tv_curent_currency);
        saveSetings = findViewById(R.id.b_save_settings);

        DatabaseOperation.getCurentCurrency(this);

        currentCurrency.setText(MyConstants.Currency);
        saveSetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyElem ce = (CurrencyElem)(((Spinner) findViewById(R.id.s_setting_currency)).getSelectedItem());
                MyConstants.Currency = ce.getName();
                MyConstants.ValueCurrency = ce.getCurs();

                DatabaseOperation.setCurentCurrency(context, MyConstants.Currency, MyConstants.ValueCurrency);

                Context context = settings.this;
                Class destinationActivity = MainActivity.class;
                Intent MainActivity = new Intent(context, destinationActivity);
                startActivity(MainActivity);

                Toast toast = Toast.makeText(getApplicationContext(), MyConstants.Currency, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        spinner_currency = findViewById(R.id.s_setting_currency);
        DatabaseOperation.getCurrenies(this);
        ArrayAdapter<CurrencyElem> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        for (int i=0; i<MyConstants.CurreciesList.size(); i++){
            adapter.add(MyConstants.CurreciesList.get(i));
        }

        spinner_currency.setAdapter(adapter);
    }
}
package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add_score_button, send_money_button, history_button, settings, deleteScore;
    private ScoreAdapter adapter;

    private Thread parsing;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(this);

        DatabaseOperation.getCurentCurrency(this);
        DatabaseOperation.getScores(this);

        RecyclerView rv = findViewById(R.id.rv_main_area);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new ScoreAdapter(MyConstants.Scores);
        rv.setAdapter(adapter);

        add_score_button = findViewById(R.id.add_score);
        deleteScore = findViewById(R.id.b_delete_score);
        send_money_button = findViewById(R.id.send_money);
        history_button = findViewById(R.id.history);
        settings = findViewById(R.id.b_settings);

        add_score_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Context context = MainActivity.this;
                        Class destinationActivity = add_score.class;
                        Intent add_score_activity_intent = new Intent(context, destinationActivity);
                        startActivity(add_score_activity_intent);
            }
        });

        deleteScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destinationActivity = delete_score.class;
                Intent deleteScoreActivity = new Intent(context, destinationActivity);
                startActivity(deleteScoreActivity);
            }
        });

        send_money_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyConstants.Scores.size()>1) {
                    Context context = MainActivity.this;
                    Class destinationActivity = transfer_window.class;
                    Intent transfer_window_activity_intent = new Intent(context, destinationActivity);
                    startActivity(transfer_window_activity_intent);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Переводы недоступны! Необходимо создать минимум 2 счета.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destinationActivity = history.class;
                Intent history_activity_intent = new Intent(context, destinationActivity);
                startActivity(history_activity_intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destinationActivity = settings.class;
                Intent settings_activity = new Intent(context, destinationActivity);
                startActivity(settings_activity);
            }
        });
    }


    private void init(Context context){
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    DatabaseOperation.setCurrencies(context, CurrencyElem.getCurses());
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }

                DatabaseOperation.getCurrenies(context);
            }
        };
        parsing = new Thread(runnable);
        parsing.start();
    }
}
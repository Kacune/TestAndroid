package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    private DatabaseHelper dbhelper;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Context context = this;

        DatabaseOperation.getTransfers(context);

        RecyclerView rv = findViewById(R.id.rv_history);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new HistoryAdapter(MyConstants.Histories);
        rv.setAdapter(adapter);
    }
}
package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class delete_score extends AppCompatActivity {

    Spinner spinnerDelete;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_score);

        Context context = this;

        spinnerDelete = findViewById(R.id.s_delete_score);
        buttonDelete = findViewById(R.id.b_delete);

        MySpinnerAdapters.setAdapterScore(context, spinnerDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Score score = (Score) (spinnerDelete.getSelectedItem());
                int id = score.getId();
                DatabaseOperation.deleteScore(context, id);

                Context context = delete_score.this;
                Class destinationActivity = MainActivity.class;
                Intent MainActivity = new Intent(context, destinationActivity);
                startActivity(MainActivity);
            }
        });
    }
}
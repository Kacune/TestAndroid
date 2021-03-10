package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_score extends AppCompatActivity implements View.OnClickListener {
    /**
     * Элементы Activity
     */
    Button btnAdd;
    EditText et_name, et_sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);

        et_name = findViewById(R.id.et_score_name);
        et_sum = findViewById(R.id.et_score_sum);
        btnAdd = findViewById(R.id.b_add_score);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ContentValues cv = new ContentValues();

        String name_score = et_name.getText().toString();
        String sum_score_String = et_sum.getText().toString();
        /**
         * Проверка длины названия счета
         */
        if(name_score.length() > 30 || name_score.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Некорректное название", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            /**
             * Проверка на пустую строку с суммой
             */
            if (et_sum.getText().toString().length() == 0){
                Toast toast = Toast.makeText(getApplicationContext(), "Введите сумму счета", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                float sum_score = Float.valueOf(sum_score_String);
                /**
                 * Проверка величины значения суммы
                 */
                if(sum_score > Double.MAX_VALUE || sum_score < Double.MIN_VALUE){
                    Toast toast = Toast.makeText(getApplicationContext(), "Недопустимая сумма счета", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    if (v.getId() == R.id.b_add_score){

                        DatabaseOperation.addScore(this, name_score, sum_score, MyConstants.Currency);

                        Context context = add_score.this;
                        Class destinationActivity = MainActivity.class;
                        Intent MainActivity = new Intent(context, destinationActivity);
                        startActivity(MainActivity);
                    }
                }
            }
        }
    }
}
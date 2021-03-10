package app.kacunagi.youruchet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class transfer_window extends AppCompatActivity {
    Button transfer_money;
    EditText sum, comment;
    Spinner spinner_currency,spinner_debit,spinner_enrollment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_window);

        Context context = this;

        sum = findViewById(R.id.et_sum_debit);
        comment = findViewById(R.id.et_comment);

        spinner_debit = findViewById(R.id.s_debit_account);
        spinner_enrollment = findViewById(R.id.s_enrollment_account);
        spinner_currency = findViewById(R.id.s_list_currency);

        MySpinnerAdapters.setAdapterScore(this, spinner_debit);
        MySpinnerAdapters.setAdapterScore(this, spinner_enrollment);
        MySpinnerAdapters.setAdapterCurrencyElem(this, spinner_currency);


        transfer_money = findViewById(R.id.b_transder_money);

        transfer_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Score score = (Score) (((Spinner) findViewById(R.id.s_debit_account)).getSelectedItem());
                Score score_enrollment = (Score) (((Spinner)findViewById(R.id.s_enrollment_account)).getSelectedItem());
                float sum_debit = Float.valueOf(sum.getText().toString());
                CurrencyElem currency_debit = (CurrencyElem)(((Spinner) findViewById(R.id.s_list_currency)).getSelectedItem());
                String comment_transfer = comment.getText().toString();

                Date date = new Date();
                Long dateHistory = date.getTime();
                for (CurrencyElem ce: MyConstants.CurreciesList){
                    if(ce.getName().equals(score.getCurrency())){
                        MyConstants.scoreCurrencyValueDebit = ce.getCurs();
                    }
                    if(ce.getName().equals(score_enrollment.getCurrency())){
                        MyConstants.scoreCurrencyValueEnrollment = ce.getCurs();
                    }
                    if (ce.getName().equals(currency_debit.getName())){
                        MyConstants.currencyValueTransfer = ce.getCurs();
                    }
                }
                if(score.getSum() * MyConstants.scoreCurrencyValueDebit < sum_debit * MyConstants.currencyValueTransfer){
                    Toast toast = Toast.makeText(getApplicationContext(), "Недостаточно средств", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    if (score.getId() == score_enrollment.getId()) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Невозможно списать и зачислить на один и тот же счет.", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (comment_transfer.length() > 30){
                            Toast toast = Toast.makeText(getApplicationContext(), "Слишком большой комментарий", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {

                            double sum = (score.getSum()*MyConstants.scoreCurrencyValueDebit-sum_debit*MyConstants.currencyValueTransfer)/MyConstants.scoreCurrencyValueDebit;

                            DatabaseOperation.updateScore(context, score.getId(), score.getName(), sum, sum_debit);
                            DatabaseOperation.addTransfer(context, score.getId(),score.getName(),dateHistory,sum_debit,currency_debit.getName(),false,comment_transfer);

                            sum = (score_enrollment.getSum()*MyConstants.scoreCurrencyValueEnrollment+sum_debit*MyConstants.currencyValueTransfer)/MyConstants.scoreCurrencyValueEnrollment;

                            DatabaseOperation.updateScore(context, score_enrollment.getId(), score_enrollment.getName(),sum, sum_debit);
                            DatabaseOperation.addTransfer(context, score_enrollment.getId(),score_enrollment.getName(),dateHistory,sum_debit,currency_debit.getName(),true,comment_transfer);


                            Context context = transfer_window.this;
                            Class destinationActivity = MainActivity.class;
                            Intent MainActivity = new Intent(context, destinationActivity);
                            startActivity(MainActivity);
                        }
                    }
                }
            }
        });
    }
}
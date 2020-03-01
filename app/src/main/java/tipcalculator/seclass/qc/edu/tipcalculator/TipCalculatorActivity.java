package tipcalculator.seclass.qc.edu.tipcalculator;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class TipCalculatorActivity extends AppCompatActivity {

    private EditText checkAmountField;
    private EditText partySizeField;

    private Button computeBtn;

    private EditText fifteenPercentTipField;
    private EditText twentyPercentTipField;
    private EditText twentyfivePercentTipField;

    private EditText fifteenPercentTotalField;
    private EditText twentyPercentTotalField;
    private EditText twentyfivePercentTotalField;

    private double checkAmount = 0.0;
    private double partySize = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        checkAmountField = findViewById(R.id.checkAmountValue);
        partySizeField = findViewById(R.id.partySizeValue);

        computeBtn = findViewById(R.id.buttonCompute);

        fifteenPercentTipField = findViewById(R.id.fifteenPercentTipValue);
        twentyPercentTipField = findViewById(R.id.twentyPercentTipValue);
        twentyfivePercentTipField = findViewById(R.id.twentyfivePercentTipValue);

        fifteenPercentTotalField = findViewById(R.id.fifteenPercentTotalValue);
        twentyPercentTotalField = findViewById(R.id.twentyPercentTotalValue);
        twentyfivePercentTotalField = findViewById(R.id.twentyfivePercentTotalValue);
    }


    public void computeTip(View view) {

        String checkAmountValue = checkAmountField.getText().toString();
        String partySizeValue = partySizeField.getText().toString();

        if(checkAmountValue.matches("") || partySizeValue.matches("")){
            Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        } else if(Double.parseDouble(checkAmountValue) < 0 || Double.parseDouble(partySizeValue) < 0){
            Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        } else {

            checkAmount = Double.parseDouble(checkAmountValue);
            partySize = Double.parseDouble(partySizeValue);

            calculateTip();
        }
    }

    private void calculateTip() {

        // 1: Total Tip Amount
        // 2: Tip Per Person of 15%, 20% and 25%

        double totalTipAmountOf15p = (15.0 / 100.0) * checkAmount;
        double tipPerPersonOf15p = totalTipAmountOf15p / partySize;
        tipPerPersonOf15p = Math.round(tipPerPersonOf15p);

        double totalTipAmountOf20p = (20.0 / 100.0) * checkAmount;
        double tipPerPersonOf20p = totalTipAmountOf20p / partySize;
        tipPerPersonOf20p = Math.round(tipPerPersonOf20p);

        double totalTipAmountOf25p = (25.0 / 100.0) * checkAmount;
        double tipPerPersonOf25p = totalTipAmountOf25p / partySize;
        tipPerPersonOf25p = Math.round(tipPerPersonOf25p);

        fifteenPercentTipField.setText(String.valueOf((int)tipPerPersonOf15p));
        twentyPercentTipField.setText(String.valueOf((int)tipPerPersonOf20p));
        twentyfivePercentTipField.setText(String.valueOf((int)tipPerPersonOf25p));

        // 3: Check Total of 15%, 20%, and 25%
        // 4: Total Amount paid by each person.

        double checkTotalOf15p = totalTipAmountOf15p + checkAmount;
        double totalAmountPerPersonOf15p = checkTotalOf15p / partySize;
        totalAmountPerPersonOf15p = Math.round(totalAmountPerPersonOf15p);

        double checkTotalOf20p = totalTipAmountOf20p + checkAmount;
        double totalAmountPerPersonOf20p = checkTotalOf20p / partySize;
        totalAmountPerPersonOf20p = Math.round(totalAmountPerPersonOf20p);

        double checkTotalOf25p = totalTipAmountOf25p + checkAmount;
        double totalAmountPerPersonOf25p = checkTotalOf25p / partySize;
        totalAmountPerPersonOf25p = Math.round(totalAmountPerPersonOf25p);

        fifteenPercentTotalField.setText(String.valueOf((int)totalAmountPerPersonOf15p));
        twentyPercentTotalField.setText(String.valueOf((int)totalAmountPerPersonOf20p));
        twentyfivePercentTotalField.setText(String.valueOf((int)totalAmountPerPersonOf25p));

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}

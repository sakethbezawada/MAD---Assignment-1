package edu.uncc.inclass01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int price;
    int discountPercent;
    RadioGroup radioGroup;
    SeekBar seekBar;
    TextView seekBarPercent;
    TextView discountText;
    TextView finalPriceText;
    EditText priceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceInput = findViewById(R.id.price);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radio10);
        seekBar = findViewById(R.id.seekBar);
        seekBarPercent = findViewById(R.id.seekBarPercent);
        seekBar.setProgress(25);
        seekBar.setEnabled(false);
        discountText = findViewById(R.id.discount);
        finalPriceText = findViewById(R.id.finalPrice);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            seekBar.setEnabled(false);
            if(checkedId == R.id.radio10){
                discountPercent = 10;
            } else if (checkedId == R.id.radio15) {
                discountPercent = 15;
            } else if (checkedId == R.id.radio18) {
                discountPercent = 18;
            } else if (checkedId == R.id.radioCustom) {
                discountPercent = 25;
                seekBar.setEnabled(true);
            }
        });

        // Seekbar Listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarPercent.setText(progress + "%");
                discountPercent = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Button Listeners
        findViewById(R.id.reset_button).setOnClickListener(v -> {
            // Reset All Values
            priceInput.setText("");
            discountText.setText("0.00");
            finalPriceText.setText("0.00");
            discountPercent = 0;
            radioGroup.check(R.id.radio10);
            seekBar.setEnabled(false);
            seekBar.setProgress(25);
            seekBarPercent.setText("25%");
        });

        findViewById(R.id.calculate_button).setOnClickListener(v -> {
            // Show toast if item price input is empty
            if (priceInput.getText().toString().matches("")) {
                Toast.makeText(this, "Enter Item Price", Toast.LENGTH_LONG).show();
            } else {
                // Get the price from the EditText
                price = Integer.parseInt(priceInput.getText().toString());

                // Calculate discount and final price based on the selected discount
                double calculatedDiscountValue = price * discountPercent / 100.0;
                double finalPriceValue = price - calculatedDiscountValue;

                // Display the calculated discount and final price in the TextViews
                discountText.setText(String.valueOf(calculatedDiscountValue));
                finalPriceText.setText(String.valueOf(finalPriceValue));
            }
        });
    }
}
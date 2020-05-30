package com.example.fridgemanagerpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.fridgemanagerpo.EXTRA_NAME";
    public static final String EXTRA_PRIORITY =
            "com.example.fridgemanagerpo.EXTRA_PRIORITY";
    public static final String EXTRA_DAY =
            "com.example.fridgemanagerpo.EXTRA_DAY";
    public static final String EXTRA_MONTH =
            "com.example.fridgemanagerpo.EXTRA_MONTH";
    private EditText  editTextName;
    private NumberPicker numberPickerPriority;
    private NumberPicker dayPicker;
    private NumberPicker monthPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        editTextName = findViewById(R.id.edit_text_name);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        dayPicker = findViewById(R.id.day_picker);
        monthPicker = findViewById(R.id.month_picker);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add product");

    }
    private void saveProduct(){
        String name = editTextName.getText().toString();
        int priority = numberPickerPriority.getValue();
        if (name.trim().isEmpty()){
            Toast.makeText(this,"podaj nazwe",Toast.LENGTH_SHORT).show(); //nie mozna stowrzyc pustego produktu
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_PRIORITY,priority);
        data.putExtra(EXTRA_DAY, dayPicker.getValue());
        data.putExtra(EXTRA_MONTH, monthPicker.getValue());

        setResult(RESULT_OK,data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_product_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_product:
                saveProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}

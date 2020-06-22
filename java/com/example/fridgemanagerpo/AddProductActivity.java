package com.example.fridgemanagerpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;


public class AddProductActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
     "com.example.fridgemanagerpo.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.fridgemanagerpo.EXTRA_NAME";
    public static final String EXTRA_CATEGORY =
            "com.example.fridgemanagerpo.EXTRA_CATEGORY";
    public static final String EXTRA_DESCRIPTION =
            "com.example.fridgemanagerpo.EXTRA_DESCRIPTION";
    //  public static final String EXTRA_PRIORITY =
    //        "com.example.fridgemanagerpo.EXTRA_PRIORITY";
    public static final String EXTRA_DAY =
            "com.example.fridgemanagerpo.EXTRA_DAY";
    public static final String EXTRA_MONTH =
            "com.example.fridgemanagerpo.EXTRA_MONTH";
    public static final String EXTRA_YEAR =
            "com.example.fridgemanagerpo.EXTRA_YEAR";
    private EditText  editTextName;
    private EditText editTextDescription;
    private EditText editTextCategory;
    //   private NumberPicker numberPickerPriority;
    private NumberPicker dayPicker;
    private NumberPicker monthPicker;
    private NumberPicker yearPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        editTextName = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextCategory = findViewById(R.id.edit_text_category);

        // numberPickerPriority = findViewById(R.id.number_picker_priority);
        //numberPickerPriority.setMinValue(1);
        //numberPickerPriority.setMaxValue(10);
        dayPicker = findViewById(R.id.day_picker);
        monthPicker = findViewById(R.id.month_picker);
        yearPicker = findViewById(R.id.year_picker);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        yearPicker.setMinValue(2020);
        yearPicker.setMaxValue(2030);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("dodaj produkt");

    }
    private void saveProduct(){
        String category = editTextCategory.getText().toString();
        String description = editTextDescription.getText().toString();
        String name = editTextName.getText().toString();
        // int priority = numberPickerPriority.getValue();
        if (name.trim().isEmpty()){
            Toast.makeText(this,"podaj nazwe",Toast.LENGTH_SHORT).show(); //nie mozna stowrzyc pustego produktu
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_CATEGORY,category);
        data.putExtra(EXTRA_DESCRIPTION,description);
        // data.putExtra(EXTRA_PRIORITY,priority);
        data.putExtra(EXTRA_DAY, dayPicker.getValue());
        data.putExtra(EXTRA_MONTH, monthPicker.getValue());
        data.putExtra(EXTRA_YEAR, yearPicker.getValue());
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
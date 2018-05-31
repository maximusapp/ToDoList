package com.list.todo.todolist.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.list.todo.todolist.MainActivity;
import com.list.todo.todolist.R;
import com.list.todo.todolist.database.DatabaseHelper;

public class AddStikerActivity extends AppCompatActivity {

    // Handle press back button.
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }

    EditText editText_add_title;
    EditText editText_add_body;
    Button button_add_sticker;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stiker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sec);
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_add_title = (EditText)findViewById(R.id.enter_title_of_stickers);
        editText_add_body = (EditText)findViewById(R.id.enter_describe_of_stickers);

        button_add_sticker = (Button)findViewById(R.id.addStickerButton);
        button_add_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editText_add_title.getText().toString();
                String body = editText_add_body.getText().toString();

                AddData(title, body);

            }
        });


        databaseHelper = new DatabaseHelper(this);


        // set statusBar color
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorMain));
        }

    }

    public void AddData(String title, String body) {
        boolean insertData = databaseHelper.addData(title, body);

        if (insertData) {
            Toast.makeText(this, "Data inserted success", Toast.LENGTH_SHORT).show();
            Intent intentMain = new Intent(AddStikerActivity.this, MainActivity.class);
            startActivity(intentMain);
            finish();
        } else {
            Toast.makeText(this, "Data not inserted", Toast.LENGTH_SHORT).show();
        }

    }
}

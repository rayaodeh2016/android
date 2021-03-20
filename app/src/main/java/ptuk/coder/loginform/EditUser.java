package ptuk.coder.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseHelper;

import Model.Data;
import Views.AdminPages.AllUsers;

public class EditUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, email, password;
    Spinner     roll;

    Button buttonUp;
    DatabaseHelper databaseHelper;
    Data personInfo;
    int position;
    String str_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.name_ed);
        email = findViewById(R.id.email_ed);
        password = findViewById(R.id.password_ed);
        roll = findViewById(R.id.spinners);
        buttonUp = findViewById(R.id.updatebutton_e);

       Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("val");


Log.d("val",str_position);
        roll.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("User");
        categories.add("Admin");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        roll.setAdapter(dataAdapter);

        position = Integer.parseInt(str_position);
        databaseHelper = new DatabaseHelper(this);

        personInfo = databaseHelper.getData(position);

        if (personInfo != null) {

            name.setText(personInfo.getName());
            email.setText(personInfo.getEmail());
            password.setText(personInfo.getPassword());

        }


        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    personInfo.setName(name.getText().toString());
                    personInfo.setEmail(email.getText().toString());
                    personInfo.setPassword(password.getText().toString());
                    personInfo.setRoll( roll.getSelectedItem().toString());

                    databaseHelper.updateUser(personInfo);
                    Toast.makeText(getApplicationContext(),"Update Done",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(EditUser.this, AllUsers.class);
                    startActivity(intent);
                    finish();


            }

        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
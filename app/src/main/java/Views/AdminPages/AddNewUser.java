package Views.AdminPages ;

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
import ptuk.coder.loginform.R;

public class AddNewUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, email, password;
    Spinner     roll;

    Button buttonUp;
    DatabaseHelper databaseHelper;
    Data personInfo;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);

        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.name_ad);
        email = findViewById(R.id.email_ad);
        password = findViewById(R.id.password_ad);
        roll = findViewById(R.id.spinners_ad);
        buttonUp = findViewById(R.id.add_buttona);

        roll.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Choose Roll");
        categories.add("User");
        categories.add("Admin");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        roll.setAdapter(dataAdapter);


        databaseHelper = new DatabaseHelper(this);



        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String roll1 = roll.getSelectedItem().toString();
                boolean checkemail=databaseHelper.checkUser(email1);

                if (name1.equals("") || email1.equals("") || password1.equals("") ||roll1.equals("Choose Roll") ) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Field", Toast.LENGTH_LONG).show();

                } else if(!checkemail) {
                    databaseHelper.addUser1(new Data(name1, email1, password1, roll1));
                    Toast.makeText(getApplicationContext(), "Add Done", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddNewUser.this, AllUsers.class);
                    startActivity(intent);
                    finish();


                }
                else {
                    Toast.makeText(getApplicationContext(), "Username is used Before", Toast.LENGTH_LONG).show();

                }
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
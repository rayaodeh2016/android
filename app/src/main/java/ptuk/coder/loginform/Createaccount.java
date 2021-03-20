package ptuk.coder.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Controller.DatabaseHelper;
import Model.Data;
import Views.AdminPages.AdminPage;

public class Createaccount extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextName;
    EditText mTextemail;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    TextView loginTxt;
    Button mButtonRegister;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secendpage);
        db = new DatabaseHelper(this);
        mTextName = findViewById(R.id.name);
        mTextemail = findViewById(R.id.username);
        mTextPassword = findViewById(R.id.password_c);
        mTextCnfPassword = findViewById(R.id.repassword);
        mButtonRegister = findViewById(R.id.createbutton);
        loginTxt = findViewById(R.id.goLogin);


        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent LoginIntent = new Intent(Createaccount.this, MainActivity.class);
                startActivity(LoginIntent);

            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String name = mTextName.getText().toString();
                String email = mTextemail.getText().toString();
                String pwd = mTextPassword.getText().toString();
                String cnf_pwd = mTextCnfPassword.getText().toString();


                if (!name.equals("") && !email.equals("")
                        && !pwd.equals("") && !cnf_pwd.equals("") ) {
                      if (pwd.equals(cnf_pwd)&& !db.checkUser(email) ) {
                                 long val = db.addUser(new Data(name,email,pwd));

                        if (val > 0  ) {

                            Intent moveToLogin = new Intent(Createaccount.this, MainActivity.class);

                            startActivity(moveToLogin);
                        } else if( val > 0) {
                            Toast.makeText(Createaccount.this, "Registration Done", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(Createaccount.this, MainActivity.class);

                            startActivity(moveToLogin);
                        }else {
                            Toast.makeText(Createaccount.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }

                    } else if(!pwd.equals(cnf_pwd)){
                        Toast.makeText(Createaccount.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }else if(db.checkUser(email)){
                          Toast.makeText(Createaccount.this, "You have account before", Toast.LENGTH_SHORT).show();
                          Intent moveToLogin = new Intent(Createaccount.this, MainActivity.class);
                          startActivity(moveToLogin);
                      }
                } else {
                    Toast.makeText(Createaccount.this, "Please fill all input field", Toast.LENGTH_SHORT).show();
                }

            } });
    }

}
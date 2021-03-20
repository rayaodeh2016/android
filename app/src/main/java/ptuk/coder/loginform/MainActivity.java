package ptuk.coder.loginform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Controller.DatabaseHelper;
import Model.Data;
import Utils.PreferenceUtils;
import Views.AdminPages.AdminPage;

public class MainActivity extends AppCompatActivity {
    private EditText name, password;
    private Button button;
    private TextView create;
    private DatabaseHelper db;

    public static final String PREFS_NAME = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHelper(this);
        name = findViewById(R.id.username);

        password = findViewById(R.id.password);
        button = findViewById(R.id.login_button);
        create = findViewById(R.id.createaccount);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Createaccount.class);
                startActivity(intent);

            }
        });

        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getEmail(this) != null ){
            Intent intent = new Intent(MainActivity.this, AdminPage.class);
            intent.putExtra("HELLO_MESSAGE",utils.getEmail(this));
            startActivity(intent);
        }else{

        }


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(MainActivity.this, Createaccount.class);
            startActivity(intent);
        }

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user = name.getText().toString();
                String pass = password.getText().toString();

                if(!user.equals("")&&!pass.equals("")){

                boolean res = db.checkUser(user, pass);
                    String  roll=db.getRoll(user);



                    Log.d("rol",roll);
                    if (!res) {

                    } else {

                        if(roll.equals("Admin")){
/*
                           PreferenceUtils.saveEmail(user, MainActivity.this);
                            PreferenceUtils.savePassword(pass, MainActivity.this);


 */
                            /*
                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("logged", "logged");
                            editor.commit();

                             */
                            Intent  moveToLogin = new Intent(MainActivity.this, AdminPage.class);
                            moveToLogin.putExtra("HELLO_MESSAGE",user);
                            startActivity( moveToLogin);

                        }else
                        if(roll.equals("User")) {
                         /*   moveToLogin = new Intent(MainActivity.this, AllUsers.class);
                            startActivity(moveToLogin);
                            finish();
    */
                            Toast.makeText(MainActivity.this, "User ", Toast.LENGTH_LONG).show();

                        }

                    }


                }else {
                    Toast.makeText(MainActivity.this, "Please Fill all field", Toast.LENGTH_LONG).show();
                }

    }});

}}
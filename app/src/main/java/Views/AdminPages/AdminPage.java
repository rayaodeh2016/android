package Views.AdminPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import Controller.Foodlist;
import Model.Data;
import Utils.PreferenceUtils;
import ptuk.coder.loginform.MainActivity;
import ptuk.coder.loginform.R;

public class AdminPage extends AppCompatActivity {
TextView name;

    public static final String PREFS_NAME = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        name=findViewById(R.id.dashboard_adminName);
        Intent secondIntent = getIntent();
        String message = secondIntent.getStringExtra("HELLO_MESSAGE");
        name.setText(message);
    }


    public void OrderMangment(View view) {

   Intent intent=new Intent(AdminPage.this, Foodlist.class);
   startActivity(intent);
    }


    public void UserManagment(View view) {
        Intent intent=new Intent(AdminPage.this, Views.AdminPages.AllUsers.class);
        startActivity(intent);
    }

    public void logout(View view) {
      /*  Data data=new Data();

        SharedPreferences preferences =getSharedPreferences("loginPrefs",AdminPage.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        data.setEmail("");
        data.setPassword("");
        editor.clear();
        editor.apply();
        finish();

       */
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();
        finish();
    }

    public void custometrs(View view) {

        Intent intent=new Intent(AdminPage.this, Views.AdminPages.AllCoustemer.class);
        startActivity(intent);
    }

    public void AllAdmin(View view) {
        Intent intent=new Intent(AdminPage.this, AllAdmin.class);
        startActivity(intent);
    }
}

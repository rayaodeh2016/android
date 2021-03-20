package ptuk.coder.loginform;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Controller.DatabaseHelper;

import Model.Data;
import Views.AdminPages.AllUsers;

public class SelectedUser extends AppCompatActivity {
    TextView tittel;
    TextView name, email, password, roll;
    Button buttonUp,buttonDe;
    DatabaseHelper databaseHelper;
    Data personInfo;
    int position;
    String str_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);
        databaseHelper = new DatabaseHelper(this);
        tittel = findViewById(R.id.usersel);
        name = findViewById(R.id.name_sel);
        email = findViewById(R.id.email_sel);
        password = findViewById(R.id.password_sel);
        roll = findViewById(R.id.roll_sel);
        buttonUp = findViewById(R.id.updatebutton);
        buttonDe=findViewById(R.id.Delle);

        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("data");
        Log.d("Position",str_position);
        position = Integer.parseInt(str_position);
        databaseHelper = new DatabaseHelper(this);
        personInfo = databaseHelper.getData(position);


        if (personInfo != null) {
            tittel.setText(personInfo.getName());
            name.setText(personInfo.getName());
            email.setText(personInfo.getEmail());
            password.setText(personInfo.getPassword());
            roll.setText(personInfo.getRoll());

        }


        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(SelectedUser.this, EditUser.class);
                  intent.putExtra("val",str_position);

                    startActivity(intent);
                    finish();


            }

        });
        buttonDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(SelectedUser.this);

                dialogDelete.setTitle("Warning!!");
                dialogDelete.setMessage("Are you sure you want to this delete?");
                dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            databaseHelper.deleteUser(position);
                            Intent intent=new Intent(SelectedUser.this,AllUsers.class) ;
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    "Delete successfully!!!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });

                dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialogDelete.show();
            }


        });

    }

}
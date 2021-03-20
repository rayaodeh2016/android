package Views.AdminPages;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


import Controller.DatabaseHelper;
import Controller.MyListAdapter;
import Controller.UserAdapter;
import Model.Data;
import ptuk.coder.loginform.R;


public class AllAdmin extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allcustomer);
        databaseHelper = new DatabaseHelper(this);

        textView=findViewById(R.id.tittel_User);
        textView.setText("All Admin");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycelView11);
        List<Data> mydata1 = databaseHelper.getAllCustomer("Admin");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        MyListAdapter adapter = new MyListAdapter(mydata1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
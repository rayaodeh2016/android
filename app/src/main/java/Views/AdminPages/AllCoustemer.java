package Views.AdminPages;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


import Controller.DatabaseHelper;
import Controller.MyListAdapter;
import Controller.UserAdapter;
import Model.Data;
import ptuk.coder.loginform.R;


public class AllCoustemer extends AppCompatActivity {
    DatabaseHelper databaseHelper;
TextView textView;
MyListAdapter myListAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allcustomer);
        databaseHelper = new DatabaseHelper(this);
         textView=findViewById(R.id.tittel_User);
         textView.setText("All Customers");

        RecyclerView recyclerView =findViewById(R.id.recycelView11);
        toolbar = findViewById(R.id.toolbar_c);
        this.setSupportActionBar(toolbar);
        List<Data> mydata1 = databaseHelper.getAllCustomer("User");

      myListAdapter = new MyListAdapter(mydata1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myListAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem =menu.findItem(R.id.search_value);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myListAdapter.getFilter().filter(newText);
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.search_value){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
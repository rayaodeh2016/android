package Views.AdminPages;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Controller.DatabaseHelper;
import Controller.UserAdapter;
import Model.Data;
import ptuk.coder.loginform.R;
import ptuk.coder.loginform.SelectedUser;

public class AllUsers extends AppCompatActivity implements UserAdapter.SelectedUser {
    Toolbar toolbar;
    RecyclerView recyclerView;

    UserAdapter userAdapter;
    DatabaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alluser);
        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recycelview);
        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        List<Data> mydata1 = databaseHelper.getAllUser();
        userAdapter = new UserAdapter(mydata1,this);
        recyclerView.setAdapter(userAdapter);


    }

    @Override
    public void SelectedUser(Data data) {
        Intent intent = new Intent(AllUsers.this, SelectedUser.class);
        intent.putExtra("data",String.valueOf(data.getId()));
        startActivity(intent);
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
                userAdapter.getFilter().filter(newText);
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

    public void AddNewUser(View view) {
        Intent intent = new Intent(AllUsers.this, AddNewUser.class);

        startActivity(intent);
    }
}
package com.example.mobileapp.menupb4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{

    private boolean isLoggedIn = false;
    private ListView mListView;
    private ActionMode actionMode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.countryListView);
        populateListViewWithItems();
        //registerForContextMenu(mListView);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(actionMode != null){
                    return false;
                }
                actionMode = startActionMode(callback);
                //v.setSelected(true);
                return true;
            }
        });

    }

    private void populateListViewWithItems() {
        List<String>countries = new ArrayList<>();
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        countries.add("Bangladesh");
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                countries);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.login);
        MenuItem logoutItem = menu.findItem(R.id.logout);
        if(isLoggedIn){
            loginItem.setVisible(false);
            logoutItem.setVisible(true);
        }else{
            loginItem.setVisible(true);
            logoutItem.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.login:
                Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                isLoggedIn = true;
                return true;
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                isLoggedIn = false;
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.edit:
                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                return true;
                default:
                    return super.onContextItemSelected(item);
        }
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.edit:
                    Toast.makeText(MainActivity.this, "edit", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                case R.id.delete:
                    Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}

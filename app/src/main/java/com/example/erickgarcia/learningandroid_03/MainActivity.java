package com.example.erickgarcia.learningandroid_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(names, R.layout.recycle_view, new MyAdapter.OnClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                Toast.makeText(MainActivity.this, "You're delete: "+name+", position: "+position, Toast.LENGTH_SHORT).show();
                deleteName(position);
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    // maneja eventos onClick
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void addName(int position) {
        this.names.add(position, "New name " +(++counter));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void deleteName(int position){
        this.names.remove(position);
        adapter.notifyItemRemoved(position);
    }

    // crea
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){
            {
                add("erick");
                add("monse");
                add("abril");
            }
        };
    }
}

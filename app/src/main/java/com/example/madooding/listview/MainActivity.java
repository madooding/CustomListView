package com.example.madooding.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.madooding.listview.adapter.CustomAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] items = {"alecgator", "goat", "rabbit"};
    final List<Person> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list.add(new Person("Akira", R.drawable.image));
        list.add(new Person("Job", R.drawable.image2));


    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = (ListView) findViewById(R.id.my_list);
        listView.setAdapter(new CustomAdapter(this, R.layout.custom_row, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("list", list.get(i));
                intent.putExtra("position", i);
                startActivityForResult(intent, RequestCode.EDIT_TEXT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == RequestCode.EDIT_TEXT && data != null){
            list.set(data.getIntExtra("position", -1), (Person)data.getSerializableExtra("list"));
//            Toast.makeText(this, ((Person)data.getSerializableExtra("list")).getName(), Toast.LENGTH_SHORT).show();
        }

    }
}

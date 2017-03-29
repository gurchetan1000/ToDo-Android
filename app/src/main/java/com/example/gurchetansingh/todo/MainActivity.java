package com.example.gurchetansingh.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<Todo> datalist;
    ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);
        setTitle("To Do List");
        Todo td = new Todo();
        lv = (ListView) findViewById(R.id.list);
        datalist = td.returnall();


        listAdapter = new ListAdapter(MainActivity.this, datalist);
        lv.setAdapter(listAdapter);


    }

//    public boolean onCLick(final View view) {
//        if (view.getId() == R.id.edit) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Edit");
//            LayoutInflater layoutInflater = getLayoutInflater();
//            final View v = layoutInflater.inflate(R.layout.addedit, null);
//            builder.setView(v);
//            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    List<Todo> list = Todo.returnall();
//                    Todo td = list.get((Integer) view.getTag());
//                    EditText ed = (EditText) v.findViewById(R.id.edittext);
//                    String name1 = ed.getText().toString();
//                    td.setName(name1);
//                    td.save();
//                    listAdapter.notifyDataSetChanged();
//                }
//            });
//            builder.create().show();
//        } else if (view.getId() == R.id.del) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("Delete");
//            LayoutInflater layoutInflater = getLayoutInflater();
//            View v = layoutInflater.inflate(R.layout.del, null);
//            TextView tv = (TextView) v.findViewById(R.id.deltext);
//            final List<Todo> list = Todo.returnall();
//            Todo td = list.get((Integer) view.getTag());
//            tv.setText("Are you sure to want to delete " + td.name);
//            builder.setView(v);
//            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    int pos = (int) view.getTag();
//                    Toast.makeText(MainActivity.this, pos + "", Toast.LENGTH_SHORT).show();
//
//                    Todo.delete(Todo.class, pos);
//
//                    datalist.remove(pos);
//                    listAdapter.notifyDataSetChanged();
//
//
//                }
//            });
//            builder.create().show();
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add");
        LayoutInflater layoutInflater = getLayoutInflater();
        final View v = layoutInflater.inflate(R.layout.addedit, null);
        builder.setView(v);
        AlertDialog.Builder builder1 = builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Todo td = new Todo();
                EditText ed = (EditText) v.findViewById(R.id.edittext);
                String name1 = ed.getText().toString();
                td.name = name1;
                td.save();
                datalist.add(td);
                listAdapter.notifyDataSetChanged();
            }
        });
        builder.create().show();
        return true;
    }
}

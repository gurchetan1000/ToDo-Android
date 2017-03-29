package com.example.gurchetansingh.todo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurchetan singh on 17-Jun-16.
 */
public class ListAdapter extends ArrayAdapter<Todo> {

    Context context;
    List<Todo> datalist;

    public ListAdapter(Context context, List<Todo> objects) {
        super(context, 0, objects);
        this.context = context;
        datalist = objects;
    }

    public static class ViewHolder {
        TextView lefttext;
        Button leftbutton;
        Button rightbutton;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item, null);
            ViewHolder vh = new ViewHolder();
            vh.lefttext = (TextView) convertView.findViewById(R.id.name);
            vh.leftbutton = (Button) convertView.findViewById(R.id.edit);
            vh.rightbutton = (Button) convertView.findViewById(R.id.del);
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.lefttext.setText(datalist.get(position).name);
        vh.leftbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit");
                LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                LayoutInflater layoutInflater = getLayoutInflater();
                final View v1 = li.inflate(R.layout.addedit, null,false);
                builder.setView(v1);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Todo td = datalist.get(position);
                        EditText ed = (EditText) v1.findViewById(R.id.edittext);
                        String name1 = ed.getText().toString();
                        td.setName(name1);
                        td.save();

                    }
                });
                builder.create().show();
            }
        });
        vh.rightbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v1 = li.inflate(R.layout.del, null,false);
                TextView tv = (TextView) v1.findViewById(R.id.deltext);

                Todo td = datalist.get(position);
                tv.setText("Are you sure to want to delete " + td.name);
                builder.setView(v1);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int pos = position;

                        Toast.makeText(context, pos + "", Toast.LENGTH_SHORT).show();

                        Todo.delete(Todo.class, pos);

                        datalist.remove(pos);



                    }
                });
                builder.create().show();
            }
        });
        return convertView;
    }
}

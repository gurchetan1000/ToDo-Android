package com.example.gurchetansingh.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurchetan singh on 17-Jun-16.
 */
@Table(name = "List")
public class Todo extends Model implements Serializable {

    @Column(name = "Name")
    public String name;


    public static List<Todo> returnall(){
        return new Select().from(Todo.class).execute();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

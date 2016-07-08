package zjicm.xmt130706121.bms.entity;


import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 书籍
 * Created by Relish on 2016/7/3.
 */
public class Book implements Serializable{

    private Integer id;
    @NonNull
    private String name;

    private Integer inventory;

    public Book(){

    }

    public Book(@NonNull String name, Integer inventory) {
        this.name = name;
        this.inventory = inventory;
    }

    public Book(Integer id, @NonNull String name, Integer inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;

        return id != null && id.equals(book.id) &&
                name.equals(book.name);
    }

    public boolean isEmpty() {
        return "".equals(name);
    }

    @Override
    public String toString() {
        return "Book named " + name;
    }
}

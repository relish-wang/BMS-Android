package zjicm.xmt130706121.bms.entity;

import java.io.Serializable;

/**
 * 记录
 * Created by Relish on 2016/7/3.
 */
public class Record4Admin implements Serializable{

    private Integer id;
    private String borrowTime;

    private Book book;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

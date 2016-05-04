package com.example.taqtile.apponboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taqtile on 5/4/16.
 */
public class UsersWrapper {

    private int page, per_page, total, total_pages;
    private List<User> data = new ArrayList<User>();

    public UsersWrapper(int page, int per_page, int total, int total_pages, List<User> data) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public int getPer_page() {
        return per_page;
    }

    public int getTotal() {
        return total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<User> getData() {
        return data;
    }
}

package com.example.taqtile.apponboard;

import java.util.List;

/**
 * Created by taqtile on 5/4/16.
 */
public class UsersWrapper {

    private int mPage, mPerPage, mTotal, mTotalPages;
    private List<User> mData;

    public UsersWrapper(int mPage, int mPerPage, int mTotal, int mTotalPages, List<User> mData) {
        this.mPage = mPage;
        this.mPerPage = mPerPage;
        this.mTotal = mTotal;
        this.mTotalPages = mTotalPages;
        this.mData = mData;
    }
}

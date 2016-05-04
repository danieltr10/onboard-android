package com.example.taqtile.apponboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by taqtile on 5/4/16.
 */
public interface UsersService {

    @GET("api/users?page=1")
    Call<UsersWrapper> getUsers();

}

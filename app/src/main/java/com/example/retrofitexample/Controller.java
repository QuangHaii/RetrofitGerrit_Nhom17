package com.example.retrofitexample;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Controller implements Callback<ResponseBody> {
    static final String BASE_URL = "https://git.eclipse.org/r/";
    private RecyclerView recyclerView;
    private LinearLayoutManager itemLayout;

    public Controller(RecyclerView recyclerView, LinearLayoutManager itemLayout) {
        this.recyclerView = recyclerView;
        this.itemLayout = itemLayout;
    }

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);
        Call<ResponseBody> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            try {
                Gson gson = new Gson();
                String jsonString = response.body().string();
                jsonString = jsonString.substring(jsonString.indexOf('\n') + 1);
                List<Change> changes = gson.fromJson(jsonString, new TypeToken<List<Change>>() {
                }.getType());
                ListViewAdapter adapter = new ListViewAdapter(changes);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(itemLayout);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        t.printStackTrace();
    }
}


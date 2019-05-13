package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static AnekdotAPI anekdotapi;
    RecyclerView recyclerview;
    List<AnekdotModel> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anekdotapi =  Controller.getApi();
        posts = new ArrayList<>();

        recyclerview = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        PostAdapter adapter = new PostAdapter(posts);
        recyclerview.setAdapter(adapter);

        anekdotapi.getData("new anekdot",10).enqueue(new Callback<List<AnekdotModel>>() {
            @Override
            public void onResponse(Call<List<AnekdotModel>> call, Response<List<AnekdotModel>> response) {
                posts.addAll(response.body());
                recyclerview.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AnekdotModel>> call, Throwable t) {
                // отсутствует доступ в Сеть

            }
        });

    }
}

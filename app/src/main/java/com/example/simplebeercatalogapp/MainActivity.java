package com.example.simplebeercatalogapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getPosts().enqueue(new Callback<List<PostPoJo>>() {
            @Override
            public void onResponse(Call<List<PostPoJo>> call, Response<List<PostPoJo>> response) {

                if (response.body().size() > 0){
                    List<PostPoJo> pojoList = response.body();
                    shrinkListTo(pojoList, 10);
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, pojoList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "No. of Items : " + pojoList.size() , Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MainActivity.this, "List is Empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<PostPoJo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // Write your code here to call the method.

                                apiInterface.getPosts().enqueue(new Callback<List<PostPoJo>>() {
                                    @Override
                                    public void onResponse(Call<List<PostPoJo>> call, Response<List<PostPoJo>> response) {
                                        if (response.body().size() > 0){
                                            List<PostPoJo> pojoList = response.body();
                                            int randomNum = getRandomNumber(2, 100);
                                            shrinkListTo(pojoList, randomNum);
                                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, pojoList);
                                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                                            recyclerView.setLayoutManager(linearLayoutManager);
                                            recyclerView.setAdapter(adapter);

                                            Toast.makeText(MainActivity.this, "No. of Items : " + pojoList.size() , Toast.LENGTH_SHORT).show();

                                        }else{
                                            Toast.makeText(MainActivity.this, "List is Empty", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<List<PostPoJo>> call, Throwable t) {
                                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                                    }
                                });
//                                fetchData();
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception here
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void shrinkListTo(List<PostPoJo> list, int newSize) {
        if (list.size() > newSize) {
            list.subList(newSize, list.size()).clear();
        }
    }
}
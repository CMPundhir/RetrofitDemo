package com.cmpundhir.cm.retrofitdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cmpundhir.cm.retrofitdemo.api.MyRetro;
import com.cmpundhir.cm.retrofitdemo.model.GithubUserPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAllUSersData();
    }
    private void getAllUSersData(){
        MyRetro.api.getUSersList().enqueue(new Callback<List<GithubUserPojo>>() {
            @Override
            public void onResponse(Call<List<GithubUserPojo>> call, Response<List<GithubUserPojo>> response) {
                List<GithubUserPojo> list = response.body();
                MyAdapter adapter = new MyAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<GithubUserPojo>> call, Throwable t) {

            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        List<GithubUserPojo> list;
        public MyAdapter(List<GithubUserPojo> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.recycler_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            GithubUserPojo pojo = list.get(position);
            holder.txt.setText(pojo.getLogin());
            Glide.with(MainActivity.this).load(pojo.getAvatarUrl()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txt;
            ImageView img;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txt = itemView.findViewById(R.id.txt);
                img = itemView.findViewById(R.id.img);
            }
        }
    }
}

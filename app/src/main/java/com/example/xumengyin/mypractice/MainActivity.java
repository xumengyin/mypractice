package com.example.xumengyin.mypractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<Data> listdata=new ArrayList<>();
    static {
//        listdata.add()
    }
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Myadapter());
    }
    private class MyHolder extends RecyclerView.ViewHolder
    {
        Button button;
        public MyHolder(View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.button);
        }
    }
    private class Myadapter extends RecyclerView.Adapter<MyHolder>
    {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyHolder holder =new MyHolder(View.inflate(getApplicationContext(),R.layout.menu_item,null));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            holder.button.setText(listdata.get(position).title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getBaseContext().startActivity(new Intent(getBaseContext(),listdata.get(position).activity));
                }
            });

        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }
    }

}

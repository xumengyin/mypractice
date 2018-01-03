package com.example.xumengyin.mypractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xumengyin.mypractice.testactivity.BaseActivity;
import com.example.xumengyin.mypractice.testactivity.ContraintLayoutActivity;
import com.example.xumengyin.mypractice.testactivity.LoginActivity;
import com.example.xumengyin.mypractice.testactivity.WebViewTestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity
{

    static List<Data> listdata=new ArrayList<>();
    static {
        listdata.add(new Data("webview js test ", WebViewTestActivity.class));
        listdata.add(new Data("refresh test ", ContraintLayoutActivity.class));
        listdata.add(new Data("TextinputLayout test ", LoginActivity.class));
    }
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Myadapter());
    }

    @Override
    protected int getcontentView()
    {
        return R.layout.activity_main;
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
        LayoutInflater inflater;
        public Myadapter()
        {
            inflater=LayoutInflater.from(getBaseContext());
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyHolder holder =new MyHolder(inflater.inflate(R.layout.menu_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            holder.button.setText(listdata.get(position).title);
            holder.button.setOnClickListener(new View.OnClickListener() {
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

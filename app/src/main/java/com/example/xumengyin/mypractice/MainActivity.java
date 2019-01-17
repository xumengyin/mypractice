package com.example.xumengyin.mypractice;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup; 
import android.widget.Button;

import com.example.xumengyin.mypractice.testactivity.ArgbTestActivity;
import com.example.xumengyin.mypractice.testactivity.AutoTextActivity;
import com.example.xumengyin.mypractice.testactivity.BaseActivity;
import com.example.xumengyin.mypractice.testactivity.BehaviorTest4Activity2;
import com.example.xumengyin.mypractice.testactivity.BehviorActivity;
import com.example.xumengyin.mypractice.testactivity.ContraintLayoutActivity;
import com.example.xumengyin.mypractice.testactivity.ContraintLayoutActivity2;
import com.example.xumengyin.mypractice.testactivity.ContraintLayoutActivity3;
import com.example.xumengyin.mypractice.testactivity.CustomViewAty;
import com.example.xumengyin.mypractice.testactivity.FragmentTestActivity;
import com.example.xumengyin.mypractice.testactivity.HlistviewBugTestActivity;
import com.example.xumengyin.mypractice.testactivity.KotlinActivity;
import com.example.xumengyin.mypractice.testactivity.LoginActivity;
import com.example.xumengyin.mypractice.testactivity.NotificationActivity;
import com.example.xumengyin.mypractice.testactivity.TestScrollingActivity;
import com.example.xumengyin.mypractice.testactivity.TestViewActivity;
import com.example.xumengyin.mypractice.testactivity.WebViewTestActivity;
import com.example.xumengyin.mypractice.view.DividerGridItemDecoration;
import com.example.xumengyin.mypractice.viewmodels.MainViewModel;

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
        listdata.add(new Data("contrainlayout test ", ContraintLayoutActivity2.class));
        listdata.add(new Data("contrainlayout test2 ", ContraintLayoutActivity3.class));
        listdata.add(new Data("autotext ", AutoTextActivity.class));
        listdata.add(new Data("KotlinTest ", KotlinActivity.class));
        listdata.add(new Data("lazyTest ", com.example.xumengyin.mypractice.lazyTest.MainActivity.class));
        listdata.add(new Data("behaviorTest ", BehviorActivity.class));
        listdata.add(new Data("scrollingActivity ", TestScrollingActivity.class));
        listdata.add(new Data("cardBehaviorTest ",BehaviorTest4Activity2.class));
        listdata.add(new Data("ArgbTestActivity ",ArgbTestActivity.class));
        listdata.add(new Data("8.0notificationTest ",NotificationActivity.class));
        listdata.add(new Data("fragment viewpager 生命周期 ",FragmentTestActivity.class));
        listdata.add(new Data("api 24 横向listviewbug测试 ",HlistviewBugTestActivity.class));
        listdata.add(new Data("自定义图片文字居中按钮和权限测试 ",CustomViewAty.class));
        listdata.add(new Data("测试下拉面板",TestViewActivity.class));
    }
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.setAdapter(new Myadapter());
    }

    private void testViewModel()
    {
        ViewModelProviders.of(this).get(MainViewModel.class);
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
        public void onBindViewHolder(final MyHolder holder, final int position) {
            holder.button.setText(listdata.get(position).title);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(),listdata.get(position).activity));
                }
            });

        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }
    }

}

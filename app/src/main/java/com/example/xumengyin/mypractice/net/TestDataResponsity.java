package com.example.xumengyin.mypractice.net;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.xumengyin.mypractice.bean.GutHubUser;
import com.example.xumengyin.mypractice.bean.InitBean;
import com.example.xumengyin.mypractice.bean.Repo;
import com.example.xumengyin.mypractice.db.AppDataBase;
import com.example.xumengyin.mypractice.db.Cmd;
import com.example.xumengyin.mypractice.util.L;
import com.google.gson.Gson;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class TestDataResponsity extends BaseDataResponsity
{
	Context context;

	public TestDataResponsity(Context context)
	{
		this.context = context;
	}

	public LiveData<LiveDataResponse<List<Repo>>> getGitHUb(final String data)
	{
		FetchData<List<Repo>> fetData= new FetchData<List<Repo>>()
		{
			@Override
			protected Observable<ApiResponse<List<Repo>>> createNetCall()
			{

				Observable<ApiResponse<List<Repo>>> observable = Net.getInstance().request(Api.class).listRepos(data);

				return observable;
			}

			@Override
			protected Observable<List<Repo>> LoadFromDb()
			{
				return null;
			}
		};
		return fetData.getLiveData();
	}
	public LiveData<LiveDataResponse<GutHubUser>> getGitHUbUser(final String data)
	{
		FetchData<GutHubUser> fetData= new FetchData<GutHubUser>()
		{
			@Override
			protected Observable<ApiResponse<GutHubUser>> createNetCall()
			{

				Observable<ApiResponse<GutHubUser>> observable = Net.getInstance().request(Api.class).getUser(data);

				return observable;
			}

			@Override
			protected Observable<GutHubUser> LoadFromDb()
			{
				return null;
			}
		};
		return fetData.getLiveData();
	}
	public LiveData<LiveDataResponse<InitBean>> cpsdnaInit(final String data)
	{
		final Gson gson =new Gson();
		FetchData<InitBean> fetData= new FetchData<InitBean>()
		{

			@Override
			protected Observable<ApiResponse<InitBean>> createNetCall()
			{

				Observable<ApiResponse<InitBean>> observable = Net.getInstance().request(Api.class).cpsDnaInit(PackageData.init(data));

				return observable;
			}

			@Override
			protected void saveDb(ApiResponse<InitBean> response)
			{

				AppDataBase.getInstance(context).dao().insertAll(Cmd.create(response.cmd,gson.toJson(response.data)));
			}

			@Override
			protected Observable<InitBean> LoadFromDb()
			{
				return Observable.create(new ObservableOnSubscribe<InitBean>()
				{
					@Override
					public void subscribe(ObservableEmitter<InitBean> e) throws Exception
					{
						List<Cmd>data=AppDataBase.getInstance(context).dao().getCmd("init");
						if(!data.isEmpty())
						{
							InitBean bean=gson.fromJson(data.get(0).value,InitBean.class);
							e.onNext(bean);
						}
					}
				}).compose(NetSchedulers.getInstance().<InitBean>applySchedulers());

//				AppDataBase.getInstance(context).dao().getCmd("init").compose(new FlowableTransformer<List<Cmd>,InitBean >()
//				{
//					@Override
//					public Publisher<InitBean> apply(Flowable<List<Cmd>> upstream)
//					{
//						return null;
//					}
//				})

			}

		};
		return fetData.getLiveData();
	}


}

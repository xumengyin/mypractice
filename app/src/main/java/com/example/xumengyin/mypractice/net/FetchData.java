package com.example.xumengyin.mypractice.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.xumengyin.mypractice.db.AppDataBase;
import com.example.xumengyin.mypractice.util.L;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class FetchData<Result>
{
	private final MediatorLiveData<LiveDataResponse<Result>> result = new MediatorLiveData<>();
	private LiveDataResponse<Result> source =new LiveDataResponse();

	public FetchData()
	{
		result.setValue(source.setLoading());
		dealDataBase();
		fetchNetWork();
	}

	public void dealDataBase()
	{
		Observable<Result> observable=LoadFromDb();
		if(observable!=null)
		observable.subscribe(new Consumer<Result>()
		{
			@Override
			public void accept(Result resultData) throws Exception
			{
				result.setValue(source.setCache(resultData));
			}
		});
	}
	public void fetchNetWork()
	{
		final Observable<ApiResponse<Result>> apiResponse = createNetCall();
		apiResponse.compose(NetSchedulers.getInstance().<ApiResponse<Result>>applySchedulers())
				.doAfterNext(new Consumer<ApiResponse<Result>>()
				{
					@Override
					public void accept(ApiResponse<Result> resultApiResponse) throws Exception
					{
						L.logE("thread","doAfterNext 当前Thread:"+Thread.currentThread().toString());
					}
				})
				.subscribe(new Consumer<ApiResponse<Result>>()
				{
					@Override
					public void accept(final ApiResponse<Result> resultApiResponse) throws Exception
					{
						AppExcuter.getInstance().diskIO().execute(new Runnable()
						{
							@Override
							public void run()
							{
								saveDb(resultApiResponse);
							}
						});
						result.setValue(source.setSuccess(resultApiResponse.data));
						result.setValue(source.setFinish());
						L.logE("thread","subscribe success 当前Thread:"+Thread.currentThread().toString());
					}
				}, new Consumer<Throwable>()
				{
					@Override
					public void accept(Throwable throwable) throws Exception
					{
						L.logE("thread","subscribe error 当前Thread:"+Thread.currentThread().toString());
						result.setValue(source.setError(throwable));
						result.setValue(source.setFinish());
					}
				});

	}


	protected abstract Observable<ApiResponse<Result>>createNetCall();
	protected  void saveDb(ApiResponse<Result>response)
	{

	}
	protected  abstract Observable<Result> LoadFromDb();


	public LiveData<LiveDataResponse<Result>> getLiveData()
	{
		return result;
	}
}

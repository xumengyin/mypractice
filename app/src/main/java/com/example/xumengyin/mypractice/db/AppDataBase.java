package com.example.xumengyin.mypractice.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.xumengyin.mypractice.kotlin.User;

@Database(entities = {Cmd.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase
{
	private static AppDataBase instance;

	public static AppDataBase getInstance(Context context)
	{
		if(instance==null)
		{
			synchronized (AppDataBase.class) {
				instance = Room.databaseBuilder(context.getApplicationContext(),
						AppDataBase.class, "database-name").build();
			}
		}
		return instance;
	}

	public abstract DbDao dao();


}

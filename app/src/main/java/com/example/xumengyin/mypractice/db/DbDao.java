package com.example.xumengyin.mypractice.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface  DbDao
{
	@Query("SELECT * FROM Cmd where cmd like :cmdKey")
	public List<Cmd> getCmd(String cmdKey);

	@Insert(onConflict = REPLACE)
	void insertAll(Cmd... cmds);
	@Update
	void updateCmd(Cmd... cmds);
}

package com.example.xumengyin.mypractice.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = "cmd" ,tableName = "Cmd")
public class Cmd
{
	@NonNull
	@ColumnInfo(name = "cmd")
	public String cmd;
	@ColumnInfo(name = "value")
	public String value;

	public static Cmd create(String cmdv,String value)
	{
		Cmd cmd=new Cmd();
		cmd.cmd=cmdv;
		cmd.value=value;
		return cmd;
	}
}

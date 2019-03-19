package com.example.xumengyin.mypractice.util;

import android.util.Log;

public class L
{
	private static final int MAX_LOG_LENGTH = 4000;

	public static void logE(String tag, String msg)
	{
		log(Log.ERROR,tag,msg);
	}

	public static void logD(String tag, String msg)
	{
		log(Log.DEBUG,tag,msg);
	}

	public static void log(int logLevel, String tag, String message)
	{
		for (int i = 0, length = message.length(); i < length; i++)
		{
			int newline = message.indexOf('\n', i);
			newline = newline != -1 ? newline : length;
			do
			{
				int end = Math.min(newline, i + MAX_LOG_LENGTH);
				Log.println(logLevel, tag, message.substring(i, end));
				i = end;
			} while (i < newline);
		}
	}


}

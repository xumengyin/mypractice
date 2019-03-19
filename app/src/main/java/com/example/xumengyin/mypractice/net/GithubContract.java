package com.example.xumengyin.mypractice.net;

import com.example.xumengyin.mypractice.bean.Repo;

import org.json.JSONObject;

import java.util.List;

public interface GithubContract
{
	interface View extends IBaseView
	{
		void rpnoData(List<Repo>repos);
	}

	interface P extends IBasePrestener
	{
		void getGithubRpno(String name);
		void getGitHUbUser(String name);
		void cpsdnaInit(String data);
	}
}

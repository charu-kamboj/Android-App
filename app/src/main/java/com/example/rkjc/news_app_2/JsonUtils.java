package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

// This class is responsible for parsing the news through JSON Parsing.

        public  static ArrayList<NewsItem> parseNews(String response) {
            ArrayList<NewsItem> list = new ArrayList<NewsItem>();
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("articles");

                for(int i = 0; i < array.length(); i++) {
                    list.add(new NewsItem(array.getJSONObject(i).getString("author"),
                            array.getJSONObject(i).getString("title"),
                            array.getJSONObject(i).getString("description"),
                            array.getJSONObject(i).getString("url"),
                            array.getJSONObject(i).getString("urlToImage"),
                            array.getJSONObject(i).getString("publishedAt")));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }


}



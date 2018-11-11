package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.Uri;
import android.content.Intent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;





    public class MainActivity extends AppCompatActivity
            implements NewsRecyclerViewAdapter.ListItemClickListener{

        private ArrayList<NewsItem> mNewsItems;
        private NewsRecyclerViewAdapter mAdapter;
        private RecyclerView mNumbersList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);

            this.mNewsItems = new ArrayList<NewsItem>();

            mNumbersList = findViewById(R.id.news_recyclerview);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mNumbersList.setLayoutManager(layoutManager);

            mNumbersList.setHasFixedSize(true);

            mAdapter = new NewsRecyclerViewAdapter(this.mNewsItems, this);

            mNumbersList.setAdapter(mAdapter);


            makeNewsSearch();
        }


        private void makeNewsSearch() {
            String search = "the-next-web";
            URL newsSearchUrl = NetworkUtils.SearchArticle(search);
            new NewsQueryTask().execute(newsSearchUrl);
        }


        @Override
        public void onListItemClick(int clickedItemIndex) {

            String url = mNewsItems.get(clickedItemIndex).url;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int itemThatWasClickedId = item.getItemId();
            if (itemThatWasClickedId == R.id.action_search) {
                makeNewsSearch();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


        public class NewsQueryTask extends AsyncTask<URL, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected String doInBackground(URL... params) {
                URL searchUrl = params[0];
                String newsSearchResults = null;
                try {
                    newsSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return newsSearchResults;
            }

            @Override
            protected void onPostExecute(String githubSearchResults) {

                if (githubSearchResults != null && !githubSearchResults.equals("")) {
                    try {
                        mNewsItems.addAll(NetworkUtils.responseToNewsItems(githubSearchResults));
                        mAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                }
            }
        }



    }

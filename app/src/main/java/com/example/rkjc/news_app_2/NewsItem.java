package com.example.rkjc.news_app_2;

public class NewsItem {


         String title;
         String author;
         String description;
         String publishedAt;
         String url;
         String urlToImage;

        public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
            this.title = title;
            this.author = author;
            this.description = description;
            this.publishedAt = publishedAt;
            this.url = url;
            this.urlToImage = urlToImage;
        }

        public String getTitle() {
            return title;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public String getUrl() {
            return url;
        }

        public String getAuthor() {
            return author;
        }

        public String getDescription() {
            return description;
        }

        public String getPublishedAt() {
            return publishedAt;
        }
    }


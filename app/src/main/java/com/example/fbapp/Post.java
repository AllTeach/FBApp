package com.example.fbapp;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Post
{
    private String title;
    private String body;
    private String ownerMail;

    public Post() {
    }

    public Post(String title, String body, String ownerMail) {
        this.title = title;
        this.body = body;
        this.ownerMail = ownerMail;
    }

    public Post(Map<String,Object> map)
    {
        this.title = map.get("title").toString();
        this.body = map.get("body").toString();
        this.ownerMail = map.get("ownerMail").toString();


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public void setOwnerMail(String ownerMail) {
        this.ownerMail = ownerMail;
    }



    public Map<String,Object> postToHasMap()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("title",this.title);
        map.put("body",this.body);
        map.put("ownerMail",this.ownerMail);
        return map;



    }


}

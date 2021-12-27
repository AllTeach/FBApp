package com.example.fbapp;

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


    public static Post hashMapToPost(Map<String,Object> map)
    {
        Post p = new Post();
        p.title = map.get("title").toString();
        p.body = map.get("body").toString();
        p.ownerMail = map.get("ownerMail").toString();

        return p;

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

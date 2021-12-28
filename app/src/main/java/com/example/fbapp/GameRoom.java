package com.example.fbapp;

import java.util.HashMap;
import java.util.Map;

public class GameRoom
{
    private String nameOwner;
    private String nameOther;
    private String status;
    private int row;
    private int col;
    private String currPlayer;

    public GameRoom() {
        this.currPlayer="";
        this.nameOther="";
        this.nameOwner = "";
        this.status="";
    }

    public GameRoom(Map<String,Object> map)
    {
        this.nameOwner = map.get("nameOwner").toString();
        this.nameOther = map.get("nameOther").toString();
        this.status = map.get("status").toString();
        this.row = Integer.valueOf(map.get("row").toString());
        this.col = Integer.valueOf(map.get("col").toString());
        this.currPlayer = map.get("currPlayer").toString();

    }

    public GameRoom(String nameOwner, String nameOther, String status, int row, int col, String currPlayer) {
        this.nameOwner = nameOwner;
        this.nameOther = nameOther;
        this.status = status;
        this.row = row;
        this.col = col;
        this.currPlayer = currPlayer;
    }

    public Map<String,Object> gameToHashMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("nameOwner", this.nameOwner);
        map.put("nameOther", this.nameOther);
        map.put("status", this.status);
        map.put("row", this.row);
        map.put("col", this.col);
        map.put("currPlayer", this.currPlayer);
        return map;
    }



    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNameOther() {
        return nameOther;
    }

    public void setNameOther(String nameOther) {
        this.nameOther = nameOther;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getCurrPlayer() {
        return currPlayer;
    }

    public void setCurrPlayer(String currPlayer) {
        this.currPlayer = currPlayer;
    }

    @Override
    public String toString() {
        return
                "nameOwner=" + nameOwner + "\n" +
                "nameOther='" + nameOther + "\n" +
                "status='" + status + "\n" +
                "row=" + row +
                "col=" + col + "\n" +
                "currPlayer='" + currPlayer;

    }
}

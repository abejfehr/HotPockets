package com.spark.hotpockets;

/**
 * Created by abe on 15-06-17.
 */
public class HotPocket {

    private double lat;
    private double lng;
    private String nickname;

    public HotPocket(String nick, double lt, double ln) {
        lat = lt;
        lng = ln;
        nickname = nick;
    }

    public double getLat() {
        return lat;
    }

    public double getLong() {
        return lng;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString(){ return nickname; }


}

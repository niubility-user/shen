package com.jingdong.common.entity;

import java.util.Date;

/* loaded from: classes5.dex */
public class OneHourArriveSearchHistory {
    private int id;
    private Date searchDate;
    private String tag;
    private int type;
    private String word;

    public OneHourArriveSearchHistory(String str) {
        this.word = str;
        this.searchDate = new Date();
    }

    private void update(int i2, String str, String str2, long j2, int i3) {
        this.word = str;
        this.tag = str2;
        this.id = i2;
        this.type = i3;
        try {
            this.searchDate = new Date(j2);
        } catch (Exception unused) {
        }
    }

    public int getId() {
        return this.id;
    }

    public Date getSearchDate() {
        return this.searchDate;
    }

    public String getTag() {
        return this.tag;
    }

    public int getType() {
        return this.type;
    }

    public String getWord() {
        return this.word;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setSearchDate(Date date) {
        this.searchDate = date;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public String toString() {
        return "OneHourArriveSearchHistory{id=" + this.id + ", word='" + this.word + "', tag='" + this.tag + "', searchDate=" + this.searchDate + ", type=" + this.type + '}';
    }

    public OneHourArriveSearchHistory(int i2, String str, long j2, int i3) {
        this(i2, str, "", j2, i3);
    }

    public OneHourArriveSearchHistory(int i2, String str, String str2, long j2, int i3) {
        update(i2, str, str2, j2, i3);
    }
}

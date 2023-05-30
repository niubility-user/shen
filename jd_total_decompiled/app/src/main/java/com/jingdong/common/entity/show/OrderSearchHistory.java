package com.jingdong.common.entity.show;

import com.jingdong.jdsdk.res.StringUtil;
import java.util.Date;

/* loaded from: classes5.dex */
public class OrderSearchHistory {
    private String cid;
    private int id;
    private Date searchDate;
    private int type;
    private String word;

    public OrderSearchHistory(String str) {
        this.word = str;
        this.searchDate = new Date();
    }

    public String getCid() {
        return this.cid;
    }

    public int getId() {
        return this.id;
    }

    public Date getSearchDate() {
        return this.searchDate;
    }

    public String getShowWord() {
        return isShop() ? String.format(StringUtil.searchShopHistoryTip, this.word) : this.word;
    }

    public int getType() {
        return this.type;
    }

    public String getWord() {
        return this.word;
    }

    public boolean isShop() {
        return this.type - 1 == 0;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setSearchDate(Date date) {
        this.searchDate = date;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public String toString() {
        return "SearchHistory [id=" + this.id + ", word=" + this.word + ", searchDate=" + this.searchDate + ", type=" + this.type + "]";
    }

    public void update(int i2, String str, long j2, int i3, String str2) {
        this.word = str;
        this.id = i2;
        this.type = i3;
        this.cid = str2;
        try {
            this.searchDate = new Date(j2);
        } catch (Exception unused) {
        }
    }

    public OrderSearchHistory(int i2, String str, long j2, int i3, String str2) {
        update(i2, str, j2, i3, str2);
    }
}

package com.jingdong.common.entity;

import com.jingdong.jdsdk.res.StringUtil;
import java.util.Date;

/* loaded from: classes5.dex */
public class SearchHistory {
    private String cid;
    private int id;
    private Date searchDate;
    private String tag;
    private String taglist;
    private int type;
    private String word;

    public SearchHistory(String str) {
        this.word = str;
        this.searchDate = new Date();
    }

    private void update(int i2, String str, String str2, String str3, long j2, int i3, String str4) {
        this.word = str;
        this.tag = str2;
        this.taglist = str3;
        this.id = i2;
        this.type = i3;
        this.cid = str4;
        try {
            this.searchDate = new Date(j2);
        } catch (Exception unused) {
        }
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
        return (isShop() || isTipShop1() || isTipShop2()) ? String.format(StringUtil.searchShopHistoryTip, this.word) : isGlobel() ? String.format(StringUtil.searchGlobalHistoryTip, this.word) : this.word;
    }

    public String getTag() {
        return this.tag;
    }

    public String getTaglist() {
        return this.taglist;
    }

    public int getType() {
        return this.type;
    }

    public String getWord() {
        return this.word;
    }

    public boolean isGlobel() {
        return this.type + (-2) == 0;
    }

    public boolean isShop() {
        return this.type - 1 == 0;
    }

    public boolean isTipShop1() {
        return this.type + (-11) == 0;
    }

    public boolean isTipShop2() {
        return this.type + (-12) == 0;
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

    public void setTag(String str) {
        this.tag = str;
    }

    public void setTaglist() {
        this.taglist = this.taglist;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setWord(String str) {
        this.word = str;
    }

    public String toString() {
        return "SearchHistory{id=" + this.id + ", word='" + this.word + "', tag='" + this.tag + "', taglist='" + this.taglist + "', searchDate=" + this.searchDate + ", cid='" + this.cid + "', type=" + this.type + '}';
    }

    public SearchHistory(int i2, String str, long j2, int i3, String str2) {
        this(i2, str, "", "", j2, i3, str2);
    }

    public SearchHistory(int i2, String str, String str2, String str3, long j2, int i3, String str4) {
        update(i2, str, str2, str3, j2, i3, str4);
    }
}

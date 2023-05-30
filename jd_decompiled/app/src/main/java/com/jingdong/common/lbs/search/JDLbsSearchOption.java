package com.jingdong.common.lbs.search;

import com.jingdong.common.lbs.http.JDLbsHttpOption;

/* loaded from: classes5.dex */
public class JDLbsSearchOption extends JDLbsHttpOption {
    private String city;
    private String keyword;
    private int pageindex;
    private int pagesize;
    private int radius;
    private String title;

    public JDLbsSearchOption() {
        this.pagesize = 10;
        this.pageindex = 1;
    }

    public String getCity() {
        String str = this.city;
        return str == null ? "" : str;
    }

    public String getKeyword() {
        String str = this.keyword;
        return str == null ? "" : str;
    }

    public int getPageIndex() {
        return this.pageindex;
    }

    public int getPageSize() {
        return this.pagesize;
    }

    public int getRadius() {
        return this.radius;
    }

    public String getTitle() {
        String str = this.title;
        return str == null ? "" : str;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public void setPageIndex(int i2) {
        this.pageindex = i2;
    }

    public void setPageSize(int i2) {
        this.pagesize = i2;
    }

    public void setRadius(int i2) {
        this.radius = i2;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public JDLbsSearchOption(String str) {
        super(str);
        this.pagesize = 10;
        this.pageindex = 1;
    }
}

package com.jingdong.common.listui;

/* loaded from: classes5.dex */
public class Page {
    private int currentPage;
    private int pageSize;
    private int totalPage;

    public int getNextPage() {
        return this.currentPage + 1;
    }

    public int getPage() {
        return this.currentPage;
    }

    public int getPageSize() {
        int i2 = this.pageSize;
        if (i2 == 0) {
            return 20;
        }
        return i2;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public boolean isFirstPage(int i2) {
        return i2 == 1;
    }

    public boolean isMaxPage() {
        return this.currentPage >= this.totalPage;
    }

    public void setPage(int i2) {
        this.currentPage = i2;
    }

    public void setPageSize(int i2) {
        this.pageSize = i2;
    }

    public void setTotalPage(int i2) {
        this.totalPage = i2;
    }
}

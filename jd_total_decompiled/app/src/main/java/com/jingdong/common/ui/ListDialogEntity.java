package com.jingdong.common.ui;

/* loaded from: classes6.dex */
public class ListDialogEntity {
    private String content;
    private String title;

    public ListDialogEntity() {
    }

    public String getContent() {
        return this.content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public ListDialogEntity(String str, String str2) {
        this.title = str;
        this.content = str2;
    }
}

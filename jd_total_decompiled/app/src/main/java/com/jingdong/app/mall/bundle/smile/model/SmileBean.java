package com.jingdong.app.mall.bundle.smile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class SmileBean implements Serializable {
    @SerializedName("code")
    @Expose
    public String code;
    public int delIcon;
    @SerializedName("desc")
    @Expose
    public Desc desc;
    public String desclocal;
    public String directoryPath;
    @SerializedName("fileExtName")
    @Expose
    public String fileExtName;
    @SerializedName("filePrefixName")
    @Expose
    public String filePrefixName;
    @SerializedName("flag")
    @Expose
    public int flag;
    @SerializedName("id")
    @Expose
    public int id;
    public String name;
    public String path;
    @SerializedName("sort")
    @Expose
    public int sort;
    @SerializedName("url2x")
    @Expose
    public String url2x;

    /* loaded from: classes3.dex */
    public static class Desc implements Serializable {
        public String zh_CN;
    }
}

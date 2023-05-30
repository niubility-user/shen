package com.jingdong.app.mall.bundle.smile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class SmileJsonEntity implements Serializable {
    @SerializedName("code")
    @Expose
    public int code;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("msg")
    @Expose
    public String msg;

    /* loaded from: classes3.dex */
    public class Data implements Serializable {
        @SerializedName(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)
        @Expose
        public ArrayList<SmileBean> list;

        public Data() {
        }
    }
}

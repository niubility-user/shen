package com.jingdong.sdk.lib.puppetlayout.storage.model;

import java.util.ArrayList;

/* loaded from: classes8.dex */
public class BaseCommonModel {
    public static final int CODE_SUCCESS = 0;
    public int code = -1;
    public String message = "";
    public ArrayList<BaseTemplate> data = null;
    public ArrayList<BaseTemplate> gc = null;

    public boolean isValid() {
        return this.code == 0;
    }
}

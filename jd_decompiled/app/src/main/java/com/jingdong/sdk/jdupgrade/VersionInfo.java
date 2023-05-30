package com.jingdong.sdk.jdupgrade;

import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class VersionInfo implements Serializable {
    public String build;
    public JSONObject customizeFields;
    public String downloadCancel;
    public String downloadConfirm;
    public String downloadText;
    public String downloadTitle;
    public String fileMd5;
    public String installCancel;
    public String installConfirm;
    public String installText;
    public String installTitle;
    public long size;
    public int state;
    public String url;
    public String urlMd5;
    public String version;
}

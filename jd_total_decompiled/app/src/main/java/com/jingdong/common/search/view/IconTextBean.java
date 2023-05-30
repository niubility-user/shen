package com.jingdong.common.search.view;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class IconTextBean implements Serializable {
    public boolean alwaysShow;
    public String describe;
    public String extraName;
    public float radio;
    public String resCode;
    public boolean showArrow;
    public String showName;
    public String textColor;
    public String trackId;
    public String type;
    public String url;
    public int heightDp = 14;
    public int gapDp = 4;
    public TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.END;
}

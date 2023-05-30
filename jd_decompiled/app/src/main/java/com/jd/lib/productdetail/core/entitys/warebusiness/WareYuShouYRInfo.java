package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes15.dex */
public class WareYuShouYRInfo {
    public static final int YURE_STATE_BEFORE_FBH = 10;
    public static final int YURE_STATE_FBH = 20;
    public static final int YURE_STATE_NO_FBH = 11;
    public String bgUrl;
    public long fbhDJTime;
    public String infoContent;
    public ArrayList<YuShouStep> list;
    public String tilteIcon;
    public String title;
    public String webCastUrl;
    public int yuShouStateNew;

    /* loaded from: classes15.dex */
    public static class YuShouStep {
        public boolean select;
        public int titleIndex = -1;
        public String titleInfoContent;
        public String titleTimeContent;

        public boolean isValid() {
            return (TextUtils.isEmpty(this.titleInfoContent) || TextUtils.isEmpty(this.titleTimeContent)) ? false : true;
        }
    }

    public boolean isValid() {
        boolean z = !TextUtils.isEmpty(this.title);
        ArrayList<YuShouStep> arrayList = this.list;
        if (arrayList != null) {
            Iterator<YuShouStep> it = arrayList.iterator();
            while (it.hasNext() && ((z = z & it.next().isValid()))) {
            }
        }
        return z;
    }
}

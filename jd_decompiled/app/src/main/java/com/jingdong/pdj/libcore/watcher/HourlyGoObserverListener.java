package com.jingdong.pdj.libcore.watcher;

import com.jd.framework.json.JDJSONObject;
import java.util.Map;

/* loaded from: classes7.dex */
public interface HourlyGoObserverListener extends HourlyGoGuideBubbleListener {
    public static final int TYPE_NEARBY_BUBBLE_CLICK = 4;
    public static final int TYPE_NEARBY_BUBBLE_DISMISS = 2;
    public static final int TYPE_NEARBY_BUBBLE_FAILED = 3;
    public static final int TYPE_NEARBY_BUBBLE_SHOW = 1;
    public static final int TYPE_NEARBY_BUBBLE_X_CLICK = 5;

    void updateHeadUrl(String str, int i2, int i3);

    void updateTabName(String str);

    void updateTabPicUrl(String str, Map<String, Object> map, JDJSONObject jDJSONObject);
}

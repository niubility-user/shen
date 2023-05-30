package com.jingdong.pdj.libcore.watcher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDYFAddress;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public interface HourlyGoObservableListener {
    void add(HourlyGoObserverListener hourlyGoObserverListener);

    void dismissBackHomeBubble();

    void dismissNearByBubble(boolean z);

    void notifyHeadUrlObserver(String str, int i2, int i3);

    void notifyTabNameObserver(String str);

    void notifyTabUrlObserver(String str, Map<String, Object> map, JDJSONObject jDJSONObject);

    void remove(HourlyGoObserverListener hourlyGoObserverListener);

    void setFoorId(String str);

    void setIntent(Intent intent, String str);

    void setLocationInfo(JDYFAddress jDYFAddress);

    void setLocationInfo(JDLocation jDLocation, JDBusinessAddress jDBusinessAddress);

    void showBackHomeBubble(Activity activity, List<String> list, String str, String str2, int i2, int i3);

    void showNearByBubble(Activity activity, PointF pointF, JDJSONObject jDJSONObject);
}

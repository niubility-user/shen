package com.jingdong.common.widget.custom;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.corelib.utils.Log;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class CustomChannelFollowViewManager {
    private static volatile CustomChannelFollowViewManager mInstance;
    private ConcurrentHashMap<Integer, CustomChannelFollowView> mFollowViewMap = new ConcurrentHashMap<>();

    public static CustomChannelFollowViewManager getInstance() {
        if (mInstance == null) {
            synchronized (CustomChannelFollowViewManager.class) {
                if (mInstance == null) {
                    mInstance = new CustomChannelFollowViewManager();
                }
            }
        }
        return mInstance;
    }

    public void removeContainer(ViewGroup viewGroup) {
        if (viewGroup != null) {
            this.mFollowViewMap.remove(Integer.valueOf(System.identityHashCode(viewGroup)));
        }
    }

    public void showFollowGuidTips(Context context, ViewGroup viewGroup, JSONObject jSONObject) {
        if (context == null || viewGroup == null || jSONObject == null) {
            return;
        }
        String optString = jSONObject.optString("channelId");
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        int optInt = jSONObject.optInt("bottom");
        int optInt2 = jSONObject.optInt("time");
        CustomChannelFollowView customChannelFollowView = this.mFollowViewMap.get(Integer.valueOf(System.identityHashCode(viewGroup)));
        if (customChannelFollowView != null) {
            if (customChannelFollowView.mIsShowing.get()) {
                Log.d("CustomChannelFollowView", ":" + customChannelFollowView.mIsShowing.get());
                return;
            }
            customChannelFollowView.initParams(optInt, optInt2, viewGroup, context);
            customChannelFollowView.setFollowChannelId(optString, false);
            return;
        }
        Log.d("CustomChannelFollowView", ":new");
        CustomChannelFollowView customChannelFollowView2 = new CustomChannelFollowView(context);
        customChannelFollowView2.initParams(optInt, optInt2, viewGroup, context);
        customChannelFollowView2.setFollowChannelId(optString, false);
        this.mFollowViewMap.put(Integer.valueOf(System.identityHashCode(viewGroup)), customChannelFollowView2);
    }
}

package com.jingdong.manto;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import java.io.InputStream;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public interface MantoCore {
    void addPicInPicPage(int i2, boolean z, boolean z2);

    boolean convertNativeBuffer(JSONObject jSONObject, Map map, boolean z);

    void dispatchEevent(String str, JSONObject jSONObject, int i2);

    void dispatchEeventToPage(String str, JSONObject jSONObject, int[] iArr);

    Activity getActivity();

    MantoActivityResult getActivityResultImpl();

    Bitmap getBitmap(String str);

    InputStream getFile(String str);

    ViewGroup getPageInnerContentView();

    boolean isFinishing();

    void removePicInPicPage(int i2);

    void restoreWebViewFocus(boolean z);

    void setAnchorViewVisible(boolean z, Bundle bundle);
}

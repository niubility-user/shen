package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;

/* loaded from: classes5.dex */
public class DeepLinkLocationAddressHelper {
    public static void startLocationAddressActivity(Activity activity) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("location_address_activity").toString(), (Bundle) null, 100);
    }

    public static void startLocationAddressActivity(Activity activity, int i2) {
        DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host("location_address_activity").toString(), (Bundle) null, i2);
    }
}

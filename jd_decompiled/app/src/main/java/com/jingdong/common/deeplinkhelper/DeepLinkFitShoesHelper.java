package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public class DeepLinkFitShoesHelper {
    public static void startFitShoes(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d("DeepLinkFitShoesHelper", "start bundle-fitshoes");
        }
        DeepLinkCommonHelper.startActivityDirect(context, "fitshoesactivity", bundle);
    }
}

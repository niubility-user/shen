package com.jd.lib.flexcube.owidgets.view.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.jd.lib.flexcube.iwidget.entity.material.FlexVideoModel;

/* loaded from: classes15.dex */
public class BabelVideoHandlerV2 {
    private static BabelVideoPlayerViewV2 playerInstance;

    public static BabelVideoPlayerViewV2 createPlayer(Context context, FlexVideoModel flexVideoModel) {
        releasePlayer();
        if (context == null || flexVideoModel == null) {
            return null;
        }
        BabelVideoPlayerViewV2 babelVideoPlayerViewV2 = new BabelVideoPlayerViewV2(context);
        playerInstance = babelVideoPlayerViewV2;
        babelVideoPlayerViewV2.initPlayer(flexVideoModel);
        return playerInstance;
    }

    public static void releaseHandler() {
        playerInstance = null;
    }

    public static void releasePlayer() {
        BabelVideoPlayerViewV2 babelVideoPlayerViewV2 = playerInstance;
        if (babelVideoPlayerViewV2 != null) {
            babelVideoPlayerViewV2.releasePlayer();
            BabelVideoPlayerViewV2 babelVideoPlayerViewV22 = playerInstance;
            if (babelVideoPlayerViewV22 instanceof View) {
                ViewParent parent = babelVideoPlayerViewV22.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(babelVideoPlayerViewV22);
                }
            }
            releaseHandler();
        }
    }
}

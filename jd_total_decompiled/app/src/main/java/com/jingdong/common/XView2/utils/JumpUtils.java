package com.jingdong.common.XView2.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.entity.MtaEntity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class JumpUtils {
    public static final int INTERVAL = 500;
    private static long currentTime;

    public static void clickJump(Context context, String str) {
        try {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent();
            intent.setData(parse);
            intent.setPackage(context.getPackageName());
            if (context instanceof Activity) {
                OpenAppJumpController.dispatchJumpRequest(context, intent);
            } else {
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void execJumpWithMta(Context context, JumpEntity jumpEntity, boolean z, MtaEntity mtaEntity) {
        if (context != null && System.currentTimeMillis() - currentTime >= 500) {
            JumpUtil.execJump(context, jumpEntity, 0);
            if (!z || mtaEntity == null) {
                return;
            }
            String str = mtaEntity.eventId;
            String str2 = mtaEntity.eventParam;
            String str3 = mtaEntity.pageId;
            String str4 = mtaEntity.pageName;
            JDJSONObject jDJSONObject = mtaEntity.jsonObjParam;
            JDMtaUtils.sendClickDataWithExt(context, str, str2, "", str3, str4, "", "", jDJSONObject != null ? jDJSONObject.toString() : "", null);
            return;
        }
        currentTime = System.currentTimeMillis();
    }

    public static void jumpToPage(Context context, String str, Bundle bundle, boolean z, int i2) {
        if (context != null && System.currentTimeMillis() - currentTime >= 500) {
            Intent intent = new Intent();
            intent.setClassName(context, str);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            if (!z) {
                context.startActivity(intent);
                return;
            } else if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, i2);
                return;
            } else {
                return;
            }
        }
        currentTime = System.currentTimeMillis();
    }
}

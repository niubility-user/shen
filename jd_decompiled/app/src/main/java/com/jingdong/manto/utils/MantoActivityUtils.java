package com.jingdong.manto.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;

/* loaded from: classes16.dex */
public class MantoActivityUtils {
    public static boolean startManoPage(Context context, String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str) && str.contains("mini-app-static.jd.com")) {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("appId");
            String queryParameter2 = parse.getQueryParameter("category");
            String queryParameter3 = parse.getQueryParameter("des");
            String queryParameter4 = parse.getQueryParameter("vapptype");
            if (TextUtils.isEmpty(queryParameter) || !"jump".equals(queryParameter2) || !"jdmp".equals(queryParameter3) || TextUtils.isEmpty(queryParameter4)) {
                return false;
            }
            String queryParameter5 = parse.getQueryParameter("param");
            String queryParameter6 = parse.getQueryParameter("path");
            String queryParameter7 = parse.getQueryParameter(BaseEvent.SCENE);
            String queryParameter8 = parse.getQueryParameter("pageAlias");
            String queryParameter9 = parse.getQueryParameter(IMantoBaseModule.ACTION_ID);
            String queryParameter10 = parse.getQueryParameter("mpMode");
            String queryParameter11 = parse.getQueryParameter("logo");
            OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
            builder.des("jdmp");
            builder.map("vapptype", queryParameter4);
            builder.map("appId", queryParameter);
            if (!TextUtils.isEmpty(queryParameter6)) {
                builder.map("path", queryParameter6);
            }
            if (!TextUtils.isEmpty(queryParameter5)) {
                builder.map("param", queryParameter5);
            }
            if (!TextUtils.isEmpty(queryParameter7)) {
                builder.map(BaseEvent.SCENE, queryParameter7);
            }
            if (!TextUtils.isEmpty(queryParameter8)) {
                builder.map("pageAlias", queryParameter8);
            }
            if (!TextUtils.isEmpty(queryParameter9)) {
                builder.map(IMantoBaseModule.ACTION_ID, queryParameter9);
            }
            if (!TextUtils.isEmpty(queryParameter10)) {
                builder.map("mpMode", queryParameter10);
            }
            if (!TextUtils.isEmpty(queryParameter11)) {
                builder.map("logo", queryParameter11);
            }
            builder.build().jump(context);
            return true;
        }
        return false;
    }
}

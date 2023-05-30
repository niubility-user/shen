package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.libs.xconsole.XLog;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = "mTestOnly")
/* loaded from: classes19.dex */
public class JumpToMTest extends a {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        char c2;
        String string = bundle != null ? bundle.getString("url", null) : null;
        if (TextUtils.isEmpty(string)) {
            XLog.e("JumpToMTest", "url\u4e3a\u7a7a");
            c(context);
            return;
        }
        String string2 = bundle != null ? bundle.getString("jumpType", "jump") : "jump";
        int hashCode = string2.hashCode();
        if (hashCode != 3273774) {
            switch (hashCode) {
                case -1330922800:
                    if (string2.equals("dl-1-1")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1330922799:
                    if (string2.equals("dl-1-2")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1330922798:
                    if (string2.equals("dl-1-3")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1330922797:
                    if (string2.equals("dl-1-4")) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    switch (hashCode) {
                        case -1330921839:
                            if (string2.equals("dl-2-1")) {
                                c2 = 4;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case -1330921838:
                            if (string2.equals("dl-2-2")) {
                                c2 = 5;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        default:
                            c2 = '\uffff';
                            break;
                    }
            }
        } else {
            if (string2.equals("jump")) {
                c2 = 6;
            }
            c2 = '\uffff';
        }
        if (c2 == 0) {
            XLog.d("JumpToMTest", "DeepLinkMHelper.startWebActivity(context, url) \u8df3\u8f6c");
            DeepLinkMHelper.startWebActivity(context, string);
            c(context);
        } else if (c2 == 1) {
            XLog.d("JumpToMTest", "DeepLinkMHelper.startWebActivity(context, bundle+url) \u8df3\u8f6c");
            Bundle bundle2 = new Bundle();
            bundle2.putString("url", string);
            DeepLinkMHelper.startWebActivity(context, bundle2);
            c(context);
        } else if (c2 == 2) {
            XLog.d("JumpToMTest", "DeepLinkMHelper.startWebActivity(context, bundle+URLParamMap) \u8df3\u8f6c");
            URLParamMap uRLParamMap = new URLParamMap();
            uRLParamMap.put(RemoteMessageConst.TO, string);
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            Bundle bundle3 = new Bundle();
            bundle3.putSerializable("urlParamMap", serializableContainer);
            bundle3.putString("urlAction", RemoteMessageConst.TO);
            DeepLinkMHelper.startWebActivity(context, bundle3);
            c(context);
        } else if (c2 == 3) {
            XLog.d("JumpToMTest", "DeepLinkMHelper.startWebActivity(context, bundle+url+flag) \u8df3\u8f6c");
            Bundle bundle4 = new Bundle();
            bundle4.putString("url", string);
            DeepLinkMHelper.startWebActivity(context, bundle4, -1);
            c(context);
        } else if (c2 == 4) {
            XLog.d("JumpToMTest", "DeepLinkCommonHelper.startWebActivity(context, bundle+url) \u8df3\u8f6c");
            Bundle bundle5 = new Bundle();
            bundle5.putString("url", string);
            DeepLinkCommonHelper.startWebActivity(context, bundle5, false);
            c(context);
        } else if (c2 != 5) {
            XLog.d("JumpToMTest", "BaseDesJump.forwardM \u8df3\u8f6c");
            f(context, bundle);
        } else {
            XLog.d("JumpToMTest", "DeepLinkCommonHelper.startWebActivity(context, bundle+URLParamMap) \u8df3\u8f6c");
            URLParamMap uRLParamMap2 = new URLParamMap();
            uRLParamMap2.put(RemoteMessageConst.TO, string);
            SerializableContainer serializableContainer2 = new SerializableContainer();
            serializableContainer2.setMap(uRLParamMap2);
            Bundle bundle6 = new Bundle();
            bundle6.putSerializable("urlParamMap", serializableContainer2);
            bundle6.putString("urlAction", RemoteMessageConst.TO);
            DeepLinkCommonHelper.startWebActivity(context, bundle6, false);
            c(context);
        }
    }
}

package com.jingdong.app.mall.basic.deshandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import java.util.Map;
import java.util.Set;

@Des(des = JumpUtil.VALUE_DES_JDREACT_COMMON)
/* loaded from: classes19.dex */
public class JumpToJdreact_common extends a {
    private static long b;

    /* renamed from: c  reason: collision with root package name */
    private static Bundle f8051c;

    private static boolean s(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        if (bundle != null && bundle2 != null) {
            try {
                Set<String> keySet = bundle.keySet();
                Set<String> keySet2 = bundle2.keySet();
                if (keySet.size() != keySet2.size()) {
                    return false;
                }
                for (String str : keySet) {
                    if (!keySet2.contains(str)) {
                        return false;
                    }
                    Object obj = bundle.get(str);
                    Object obj2 = bundle2.get(str);
                    if ((obj instanceof Bundle) && (!(obj2 instanceof Bundle) || !s((Bundle) obj, (Bundle) obj2))) {
                        return false;
                    }
                    if (obj != null || obj2 != null) {
                        if (obj != null && obj2 != null && !obj.equals(obj2)) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private void t(Context context, Bundle bundle) {
        if (bundle != null && bundle.getBoolean("jdreactDebounce")) {
            if (SystemClock.elapsedRealtime() - b < 500 && s(f8051c, bundle)) {
                c(context);
            } else {
                ReactActivityUtils.startJDReactCommonActivity(context, bundle);
                c(context);
                f8051c = bundle;
                b = SystemClock.elapsedRealtime();
            }
            if (context instanceof Activity) {
                ((Activity) context).overridePendingTransition(0, 0);
                return;
            }
            return;
        }
        ReactActivityUtils.startJDReactCommonActivity(context, bundle);
        c(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        boolean z = false;
        if (!TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDReact", "JDReactOpenAppRedirect", "switch", "1"))) {
            t(context, bundle);
            return;
        }
        Map<String, Map<String, Map<String, String>>> allConfig = JDMobileConfig.getInstance().getAllConfig();
        if (allConfig != null && allConfig.get("JDReactOpenAppRedirect") != null) {
            Map<String, Map<String, String>> map = allConfig.get("JDReactOpenAppRedirect");
            String string = bundle.getString(JDReactConstant.IntentConstant.MODULE_NAME);
            if (!TextUtils.isEmpty(string) && map.get(string) != null && !TextUtils.isEmpty(map.get(string).get("redirect"))) {
                Intent intent = new Intent();
                intent.setData(Uri.parse(map.get(string).get("redirect")));
                OpenAppJumpController.Command createCommand = OpenAppJumpController.createCommand(context, intent);
                String string2 = createCommand.getOutBundle().getString(JDReactConstant.IntentConstant.MODULE_NAME);
                if (!TextUtils.equals(createCommand.getDes(), JumpUtil.VALUE_DES_JDREACT_COMMON) || !TextUtils.equals(string2, string)) {
                    Bundle outBundle = createCommand.getOutBundle();
                    if (!TextUtils.equals(map.get(string).get("disableForwardParams"), "1")) {
                        String string3 = bundle.getString("param");
                        String string4 = bundle.getString("h5params");
                        outBundle.putString("param", string3);
                        outBundle.putString("h5params", string4);
                    }
                    JumpUtil.execJumpByDes(createCommand.getDes(), context, outBundle);
                    c(context);
                    z = true;
                }
            }
        }
        if (z) {
            return;
        }
        t(context, bundle);
    }
}

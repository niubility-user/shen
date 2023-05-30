package com.jingdong.manto.o;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.R;
import com.jingdong.manto.network.mantorequests.MantoJDApiRequest;
import com.jingdong.manto.o.m;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.sdk.api.IFeedback;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoTrack;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class d extends m {
    public d() {
        super(6);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Activity activity, com.jingdong.manto.q.n nVar, String str, n nVar2) {
        PkgDetailEntity pkgDetailEntity;
        IFeedback iFeedback;
        if (nVar.h() == null || (pkgDetailEntity = nVar.h().f13016h) == null || (iFeedback = (IFeedback) com.jingdong.a.n(IFeedback.class)) == null) {
            return;
        }
        JSONObject generateRequestParams = MantoJDApiRequest.generateRequestParams(com.jingdong.a.f7578c);
        StringBuilder sb = new StringBuilder();
        if (generateRequestParams != null) {
            try {
                generateRequestParams.put("host_id", com.jingdong.manto.b.e());
                generateRequestParams.put("app_id", pkgDetailEntity.appId);
                generateRequestParams.put("app_name", pkgDetailEntity.name);
                generateRequestParams.put("miniapp_version", pkgDetailEntity.versionName);
                generateRequestParams.put("logo", pkgDetailEntity.logo);
                Iterator<String> keys = generateRequestParams.keys();
                boolean z = false;
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = generateRequestParams.optString(next);
                    if (z) {
                        sb.append(ContainerUtils.FIELD_DELIMITER);
                        sb.append(next);
                        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                        sb.append(optString);
                    } else {
                        sb.append(next);
                        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                        sb.append(optString);
                        z = true;
                    }
                }
            } catch (Throwable th) {
                MantoLog.e(th.getMessage(), new Object[0]);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", sb.toString());
        iFeedback.jumpToFeedback(activity, bundle);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vapp_type", pkgDetailEntity.type);
        } catch (JSONException e2) {
            MantoLog.e(DYConstants.DY_TRACK, e2);
        }
        MantoTrack.sendCommonDataWithExt(activity, "\u95ee\u9898\u53cd\u9988", "applets_capsule_feedback", pkgDetailEntity.appId, "\u80f6\u56ca\u83dc\u5355\u5f39\u7a97", "", jSONObject.toString(), "applets_capsule", null);
    }

    @Override // com.jingdong.manto.o.m
    public void a(Context context, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.j.c cVar, String str, m.a aVar) {
        n nVar2 = nVar.p().get(this.a);
        if (nVar2 == null) {
            return;
        }
        cVar.a(nVar2.f13908c, R.string.manto_page_menu_feedback, R.drawable.manto_menu_feedback).a(true);
    }
}

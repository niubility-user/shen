package com.jd.manto.center.e;

import com.jd.framework.json.JDJSON;
import com.jd.manto.center.model.entity.MantoDiscoveryEntity;
import com.jd.manto.center.model.state.ActivityDataState;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import de.greenrobot.event.EventBus;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends com.jd.manto.center.e.a {
    private ActivityDataState b;

    /* loaded from: classes17.dex */
    class a implements HttpGroup.OnAllListener {
        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            MantoDiscoveryEntity mantoDiscoveryEntity;
            try {
                mantoDiscoveryEntity = (MantoDiscoveryEntity) JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), MantoDiscoveryEntity.class);
            } catch (Exception unused) {
                mantoDiscoveryEntity = null;
            }
            if (mantoDiscoveryEntity != null) {
                b.this.b.setMantoDiscoveryEntity(mantoDiscoveryEntity);
                EventBus.getDefault().post(new com.jd.manto.center.f.a("MANTO_DISCOVERY_SUCCESS"));
                return;
            }
            EventBus.getDefault().post(new com.jd.manto.center.f.a("MANTO_DISCOVERY_FAILURE"));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            EventBus.getDefault().post(new com.jd.manto.center.f.a("MANTO_DISCOVERY_FAILURE"));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    public b(HttpGroupUtil httpGroupUtil) {
        super(httpGroupUtil);
    }

    public void d(IMyActivity iMyActivity, JSONObject jSONObject) {
        if (iMyActivity != null) {
            HttpSetting a2 = c.a(jSONObject);
            a2.setListener(new a());
            a2.setNotifyUser(false);
            b().getHttpGroupWithNPSGroup(iMyActivity, null).add(a2);
        }
    }

    public void e(ActivityDataState activityDataState) {
        this.b = activityDataState;
    }
}

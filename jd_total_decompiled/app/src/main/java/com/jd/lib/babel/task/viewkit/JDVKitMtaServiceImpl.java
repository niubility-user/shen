package com.jd.lib.babel.task.viewkit;

import android.content.Context;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.viewkit.thirdinterface.JDViewKitMtaService;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class JDVKitMtaServiceImpl implements JDViewKitMtaService {
    private MtaManager mMtaManager;

    public JDVKitMtaServiceImpl(MtaManager mtaManager) {
        this.mMtaManager = mtaManager;
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitMtaService
    public void click(JDViewKitMtaModel jDViewKitMtaModel, Context context) {
        if (jDViewKitMtaModel == null || jDViewKitMtaModel.getJumpJsonString() == null || context == null) {
            return;
        }
        try {
            BabelJumpEntity babelJumpEntity = new BabelJumpEntity(new JSONObject(jDViewKitMtaModel.getJumpJsonString()));
            VKEventUtil.onClick(context, MtaData.Builder.from(babelJumpEntity.eventId, babelJumpEntity.srv).page(jDViewKitMtaModel.getParamsModel() != null ? jDViewKitMtaModel.getParamsModel().getActivityId() : "", jDViewKitMtaModel.getParamsModel() != null ? jDViewKitMtaModel.getParamsModel().getPageId() : "").build());
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitMtaService
    public void pageLevel(JDViewKitMtaModel jDViewKitMtaModel, Context context) {
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitMtaService
    public void pv(JDViewKitMtaModel jDViewKitMtaModel, Context context) {
    }

    @Override // com.jd.viewkit.thirdinterface.JDViewKitMtaService
    public void sendExpo(JDViewKitMtaModel jDViewKitMtaModel, Context context) {
        if (this.mMtaManager != null && jDViewKitMtaModel != null && jDViewKitMtaModel.getJumpJsonString() != null) {
            try {
                BabelJumpEntity babelJumpEntity = new BabelJumpEntity(new JSONObject(jDViewKitMtaModel.getJumpJsonString()));
                this.mMtaManager.sendExposureData(babelJumpEntity.eventId, babelJumpEntity.srv, false);
            } catch (Exception unused) {
            }
        }
    }
}

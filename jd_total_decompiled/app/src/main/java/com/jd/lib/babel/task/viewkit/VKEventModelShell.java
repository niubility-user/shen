package com.jd.lib.babel.task.viewkit;

import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;

/* loaded from: classes14.dex */
public class VKEventModelShell {
    private JDViewKitEventModel mEventModel;
    private BabelJumpEntity mJumpEntity;

    public VKEventModelShell(JDViewKitEventModel jDViewKitEventModel) {
        if (jDViewKitEventModel == null) {
            return;
        }
        this.mEventModel = jDViewKitEventModel;
        this.mJumpEntity = VKEventUtil.obtainJump(jDViewKitEventModel);
    }

    public String getDes() {
        BabelJumpEntity babelJumpEntity = this.mJumpEntity;
        if (babelJumpEntity == null) {
            return null;
        }
        return babelJumpEntity.des;
    }

    public String getEventId() {
        BabelJumpEntity babelJumpEntity = this.mJumpEntity;
        return babelJumpEntity == null ? "" : babelJumpEntity.eventId;
    }

    public int getIndex() {
        JDViewKitEventModel jDViewKitEventModel = this.mEventModel;
        if (jDViewKitEventModel == null) {
            return -1;
        }
        return jDViewKitEventModel.getIndex();
    }

    public BabelJumpEntity getJumpEntity() {
        return this.mJumpEntity;
    }

    public String getJumpParams() {
        BabelJumpEntity babelJumpEntity = this.mJumpEntity;
        if (babelJumpEntity == null) {
            return null;
        }
        return babelJumpEntity.params;
    }

    public boolean getNeedLogin() {
        BabelJumpEntity babelJumpEntity = this.mJumpEntity;
        return babelJumpEntity != null && "1".equals(babelJumpEntity.needLogin);
    }

    public Object getParamValue(String str) {
        BabelJumpEntity babelJumpEntity = this.mJumpEntity;
        if (babelJumpEntity == null) {
            return null;
        }
        return babelJumpEntity.getParamValue(str);
    }

    public boolean isOutJump() {
        JDViewKitEventModel jDViewKitEventModel = this.mEventModel;
        return jDViewKitEventModel != null && jDViewKitEventModel.getJumpType() == JDViewKitEventModel.jumpType_Out;
    }

    public boolean isValid() {
        return (this.mEventModel == null || this.mJumpEntity == null) ? false : true;
    }
}

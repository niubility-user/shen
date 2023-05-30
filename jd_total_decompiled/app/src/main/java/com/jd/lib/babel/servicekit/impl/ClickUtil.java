package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import com.jd.lib.babel.servicekit.iservice.IClick;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;

/* loaded from: classes13.dex */
public class ClickUtil implements IClick {
    private JumpEntity createJumpEntity(BabelJumpEntity babelJumpEntity) {
        JumpEntity jumpEntity = new JumpEntity();
        if (babelJumpEntity != null) {
            jumpEntity.des = babelJumpEntity.des;
            jumpEntity.params = babelJumpEntity.params;
            jumpEntity.srv = babelJumpEntity.srv;
            jumpEntity.srvJson = babelJumpEntity.jsonSrv;
            jumpEntity.eventId = babelJumpEntity.eventId;
        }
        return jumpEntity;
    }

    @Override // com.jd.lib.babel.servicekit.iservice.IClick
    public void execJump(Context context, BabelJumpEntity babelJumpEntity) {
        if (babelJumpEntity == null) {
            return;
        }
        JumpUtil.execJump(CommTools.getActivity(context), createJumpEntity(babelJumpEntity), babelJumpEntity.arg);
    }
}

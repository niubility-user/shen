package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;

/* loaded from: classes4.dex */
public abstract class NewcomerBaseItem extends RelativeLayout {
    public NewcomerBaseItem(Context context) {
        super(context);
    }

    public abstract void a(NewcomerFloorEntity.NewcomerBaseModel newcomerBaseModel);
}

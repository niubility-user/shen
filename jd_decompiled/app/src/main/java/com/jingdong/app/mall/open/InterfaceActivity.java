package com.jingdong.app.mall.open;

import android.content.Intent;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;

/* loaded from: classes4.dex */
public class InterfaceActivity extends BaseEntryActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    public void init() {
        C(getIntent());
        super.init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    public void v(Intent intent) {
        OpenLinkTimeManager.getInstance().startOpenApp();
        OpenAppJumpController.dispatchJumpRequest(this, intent);
    }
}

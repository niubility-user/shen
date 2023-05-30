package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VAULE_DES_CARTB)
/* loaded from: classes19.dex */
public class JumpToCartb extends com.jingdong.app.mall.basic.deshandler.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements c {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.app.mall.basic.deshandler.JumpToCartb.c
        public void a() {
            JumpToCartb.this.u(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements c {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        b(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.app.mall.basic.deshandler.JumpToCartb.c
        public void a() {
            JumpToCartb.this.u(this.a, this.b);
        }
    }

    /* loaded from: classes19.dex */
    public interface c {
        void a();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean t(android.content.Context r20, android.os.Bundle r21) {
        /*
            Method dump skipped, instructions count: 459
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.basic.deshandler.JumpToCartb.t(android.content.Context, android.os.Bundle):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            s.n((BaseActivity) context, bundle);
        }
        c(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (t(context, bundle)) {
            u(context, bundle);
        }
    }
}

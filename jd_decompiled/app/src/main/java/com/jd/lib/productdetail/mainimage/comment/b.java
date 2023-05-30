package com.jd.lib.productdetail.mainimage.comment;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.corelib.utils.Log;
import java.lang.reflect.Method;

/* loaded from: classes15.dex */
public class b implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ PdMCommentActivity f4646g;

    public b(PdMCommentActivity pdMCommentActivity) {
        this.f4646g = pdMCommentActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMInfoCommentFragment pdMInfoCommentFragment;
        PdMCommentActivity pdMCommentActivity = this.f4646g;
        if (pdMCommentActivity.f4630h || (pdMInfoCommentFragment = pdMCommentActivity.f4629g) == null) {
            return;
        }
        pdMInfoCommentFragment.getClass();
        if (Log.D) {
            Log.d("PdInfoCommentFragment", "showPage()");
        }
        Bundle bundle = new Bundle();
        bundle.putInt("index", 0);
        bundle.putString("source", "1");
        if (!TextUtils.isEmpty(null)) {
            bundle.putString("firstCommentGuid", null);
        }
        try {
            Method method = pdMInfoCommentFragment.f4643m;
            if (method != null) {
                method.invoke(pdMInfoCommentFragment.f4637g, bundle);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        PdMCommentActivity pdMCommentActivity2 = this.f4646g;
        PdMInfoCommentFragment pdMInfoCommentFragment2 = pdMCommentActivity2.f4629g;
        String str = pdMCommentActivity2.f4634l;
        String str2 = pdMCommentActivity2.f4635m;
        pdMInfoCommentFragment2.getClass();
        if (Log.D) {
            Log.d("PdInfoCommentFragment", "setShadowMainSku()");
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("shadowMainSku", str);
        bundle2.putString("categoryId", str2);
        try {
            Method method2 = pdMInfoCommentFragment2.f4644n;
            if (method2 != null) {
                method2.invoke(pdMInfoCommentFragment2.f4637g, bundle2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}

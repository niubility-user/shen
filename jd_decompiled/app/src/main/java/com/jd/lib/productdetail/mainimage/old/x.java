package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.ui.DialogController;

/* loaded from: classes15.dex */
public class x implements ShoppingBaseController.ShoppingListener {
    public final /* synthetic */ PdMDpgLayerAdapter a;

    /* loaded from: classes15.dex */
    public class a extends DialogController {
        public a(x xVar) {
        }

        @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            if (i2 == -1) {
                dialogInterface.dismiss();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ DialogController f5207g;

        public b(x xVar, DialogController dialogController) {
            this.f5207g = dialogController;
        }

        @Override // java.lang.Runnable
        public void run() {
            DialogController dialogController = this.f5207g;
            if (dialogController != null) {
                dialogController.show();
            }
        }
    }

    public x(PdMDpgLayerAdapter pdMDpgLayerAdapter) {
        this.a = pdMDpgLayerAdapter;
    }

    @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
    public void onEnd(CartResponse cartResponse) {
        Context context = this.a.b;
        if (context == null) {
            return;
        }
        if ((((BaseActivity) context).getLifecycle() != null ? ((BaseActivity) this.a.b).getLifecycle().getCurrentState() : Lifecycle.State.DESTROYED) != Lifecycle.State.RESUMED) {
            return;
        }
        int resultCode = cartResponse != null ? cartResponse.getResultCode() : 2;
        if (resultCode == 0) {
            this.a.f4997h.viewCallBackMutableLiveData.postValue(new PdMImageEventEntity(PdImageEventCode.FRESH_CART_COUNT, "addcart"));
        }
        PDUtils.showToastCenterIcon(this.a.b, (byte) (resultCode != 0 ? 1 : 2), ShoppingBaseController.getResultMsg(cartResponse));
    }

    @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
    public void onError(String str) {
        Context context = this.a.b;
        if (context == null) {
            return;
        }
        if ((((BaseActivity) context).getLifecycle() != null ? ((BaseActivity) this.a.b).getLifecycle().getCurrentState() : Lifecycle.State.DESTROYED) != Lifecycle.State.RESUMED) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            a aVar = new a(this);
            aVar.setMessage(str);
            aVar.setPositiveButton(this.a.b.getString(R.string.lib_pd_image_ok));
            aVar.init(this.a.b);
            new Handler(Looper.getMainLooper()).post(new b(this, aVar));
            return;
        }
        Context context2 = this.a.b;
        if (context2 != null) {
            PDUtils.showToastCenterIcon(context2, (byte) 3, context2.getString(R.string.lib_pd_image_error_addcart));
        }
    }

    @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
    public void onReady() {
    }
}

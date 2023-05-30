package com.jd.lib.productdetail.mainimage.holder.video;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.videoplayer.VideoPlayerUtils;

/* loaded from: classes15.dex */
public class d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMVideoViewHolder f4970g;

    public d(PdMVideoViewHolder pdMVideoViewHolder) {
        this.f4970g = pdMVideoViewHolder;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = this.f4970g;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null) {
            return;
        }
        Context context = pdMVideoViewHolder.f4649i;
        View decorView = context instanceof BaseActivity ? ((BaseActivity) context).getWindow().getDecorView() : null;
        kVar.e(decorView instanceof ViewGroup ? decorView : null);
        VideoPlayerUtils.setActivityFullScreen(this.f4970g.L());
        this.f4970g.f4654n.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.INTERCEPT_KEYBACK, Boolean.TRUE));
    }
}

package com.jd.lib.productdetail.mainimage.holder.video;

import android.content.Context;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMaterVideoInfo;
import com.jd.lib.productdetail.core.utils.VideoPlayUtil;
import com.jingdong.common.deeplinkhelper.VideoPlayerDeepLinkHelper;

/* loaded from: classes15.dex */
public class g implements VideoPlayUtil.OnVideoPlayListener {
    public final /* synthetic */ WareBusinessMaterVideoInfo a;
    public final /* synthetic */ PdMVideoViewHolder b;

    public g(PdMVideoViewHolder pdMVideoViewHolder, WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo) {
        this.b = pdMVideoViewHolder;
        this.a = wareBusinessMaterVideoInfo;
    }

    @Override // com.jd.lib.productdetail.core.utils.VideoPlayUtil.OnVideoPlayListener
    public void onPlay() {
        PdMVideoViewHolder pdMVideoViewHolder = this.b;
        Context context = pdMVideoViewHolder.f4649i;
        String str = pdMVideoViewHolder.v.extMap.skuId;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo = this.a;
        VideoPlayerDeepLinkHelper.startVideoPlayer(context, str, wareBusinessMaterVideoInfo.imageUrl, wareBusinessMaterVideoInfo.playUrl, 0, 3, wareBusinessMaterVideoInfo.videoId);
    }
}

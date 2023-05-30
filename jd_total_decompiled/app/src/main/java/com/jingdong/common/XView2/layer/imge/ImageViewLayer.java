package com.jingdong.common.XView2.layer.imge;

import android.app.Activity;
import android.view.View;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;

/* loaded from: classes5.dex */
public class ImageViewLayer extends BaseLayer {
    XView2ImageView xViewImageView;

    public ImageViewLayer(Activity activity, Object obj, XView2 xView2) {
        super(activity, obj, xView2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void closeLayer(String str, int i2) {
        super.closeLayer(str, i2);
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer
    public View createLayer(XView2Container xView2Container, Object obj, ILayerCallBack iLayerCallBack) {
        if (this.mContext == null || this.layersEntity == null) {
            return null;
        }
        this.mCallBack = iLayerCallBack;
        this.mLayerState = BaseLayer.LAYERESTATE.CREATE;
        XView2ImageView xView2ImageView = new XView2ImageView(this.mContext);
        this.xViewImageView = xView2ImageView;
        xView2ImageView.configLayer(this.layersEntity, this.mCallBack);
        return this.xViewImageView;
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void destroyLayer() {
        super.destroyLayer();
        XView2ImageView xView2ImageView = this.xViewImageView;
        if (xView2ImageView != null) {
            xView2ImageView.destroyLayer();
            this.xViewImageView = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onPause() {
        super.onPause();
    }

    @Override // com.jingdong.common.XView2.layer.BaseLayer, com.jingdong.common.XView2.layer.IBaseLayer
    public void onResume() {
        super.onResume();
    }
}

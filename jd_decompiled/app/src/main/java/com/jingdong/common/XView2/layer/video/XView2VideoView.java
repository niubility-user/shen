package com.jingdong.common.XView2.layer.video;

import android.content.Context;
import android.view.MotionEvent;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.utils.XView2Utils;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

/* loaded from: classes5.dex */
public class XView2VideoView extends JDVideoView {
    XViewConfigEntity.LayersEntity layersEntity;

    public XView2VideoView(Context context) {
        super(context);
    }

    public void configLayer(XViewConfigEntity.LayersEntity layersEntity, ILayerCallBack iLayerCallBack) {
        this.layersEntity = layersEntity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || XView2Utils.isConvertBool(layersEntity.userInteractionEnabled)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }
}

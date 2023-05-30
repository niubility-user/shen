package com.jingdong.common.XView2.layer.imge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes5.dex */
public class XView2ImageView extends FrameLayout {
    XViewConfigEntity.LayersEntity layersEntity;
    public Handler mHandler;
    SimpleDraweeView simpleDraweeView;

    public XView2ImageView(@NonNull Context context) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        initView(context);
    }

    public void configLayer(final XViewConfigEntity.LayersEntity layersEntity, final ILayerCallBack iLayerCallBack) {
        this.layersEntity = layersEntity;
        if (layersEntity == null) {
            return;
        }
        XViewConfigEntity.RenderDataEntity renderDataEntity = layersEntity.renderData;
        if (renderDataEntity != null && renderDataEntity.layout != null) {
            GenericDraweeHierarchyBuilder builder = this.simpleDraweeView.getHierarchy().getBuilder();
            if (builder.getRoundingParams() != null) {
                builder.getRoundingParams().setCornersRadius((float) layersEntity.renderData.layout.radius);
            } else {
                builder.setRoundingParams(RoundingParams.fromCornersRadius((float) layersEntity.renderData.layout.radius));
            }
        }
        XViewConfigEntity.RenderDataEntity renderDataEntity2 = layersEntity.renderData;
        final String str = renderDataEntity2 != null ? renderDataEntity2.url : "";
        if ("1".equals(renderDataEntity2.contentMode)) {
            this.simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else if ("2".equals(layersEntity.renderData.contentMode)) {
            this.simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else {
            this.simpleDraweeView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        XView2Utils.setOptionReferer(XView2Utils.imageNullOptions, layersEntity.layerId);
        JDImageUtils.displayImage(str, this.simpleDraweeView, XView2Utils.imageNullOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.XView2.layer.imge.XView2ImageView.1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                XViewConfigEntity.LayersEntity layersEntity2;
                ILayerCallBack iLayerCallBack2 = iLayerCallBack;
                if (iLayerCallBack2 == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                iLayerCallBack2.onLayerClosed(layersEntity2.layerId, 0);
                XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                XViewConfigEntity.LayersEntity layersEntity3 = layersEntity;
                manager.notifyLayerShowError(layersEntity3.layerId, layersEntity3.logicKey);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                XViewConfigEntity.LayersEntity layersEntity2;
                ILayerCallBack iLayerCallBack2 = iLayerCallBack;
                if (iLayerCallBack2 == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                iLayerCallBack2.onLayerDisplayed(layersEntity2.layerId);
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                if (iLayerCallBack != null && layersEntity != null) {
                    XView2LayerObservableManager manager = XView2LayerObservableManager.getManager();
                    XViewConfigEntity.LayersEntity layersEntity2 = layersEntity;
                    manager.notifyLayerShowError(layersEntity2.layerId, layersEntity2.logicKey);
                    iLayerCallBack.onLayerClosed(layersEntity.layerId, 0);
                }
                if (jDFailReason != null) {
                    XView2Utils.reportXView2Error("configLayer", "NXViewException", str, str + "\u56fe\u7247\u52a0\u8f7d\u5931\u8d25\uff0c\u5931\u8d25\u539f\u56e0\u4e3a" + jDFailReason.getType());
                    return;
                }
                XView2Utils.reportXView2Error("configLayer", "NXViewException", str, str + "\u56fe\u7247\u52a0\u8f7d\u5931\u8d25");
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
                XViewConfigEntity.LayersEntity layersEntity2;
                ILayerCallBack iLayerCallBack2 = iLayerCallBack;
                if (iLayerCallBack2 == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                iLayerCallBack2.onLayerLoading(layersEntity2.layerId);
            }
        });
    }

    public void destroyLayer() {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        XViewConfigEntity.LayersEntity layersEntity = this.layersEntity;
        if (layersEntity == null || XView2Utils.isConvertBool(layersEntity.userInteractionEnabled)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return false;
    }

    public void initView(Context context) {
        this.simpleDraweeView = new SimpleDraweeView((Activity) context);
        addView(this.simpleDraweeView, new FrameLayout.LayoutParams(-1, -1));
    }

    public XView2ImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler(Looper.getMainLooper());
        initView(context);
    }

    public XView2ImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHandler = new Handler(Looper.getMainLooper());
        initView(context);
    }
}

package com.jd.lib.flexcube.layout.floor.linear;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.ifloor.utils.JsonUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.model.BaseEvent;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.flexcube.R;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.layout.entity.FlatConfig;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.ShowMoreClickEvent;
import java.util.Map;

/* loaded from: classes14.dex */
public class ClickMoreView extends LinearLayout {
    private BabelScope babelScopeTemp;
    private ImageView imageView;
    private Context mContext;
    private TextView textView;

    /* loaded from: classes14.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FlexCubeModel f4379g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FloorConfig f4380h;

        a(FlexCubeModel flexCubeModel, FloorConfig floorConfig) {
            this.f4379g = flexCubeModel;
            this.f4380h = floorConfig;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShowMoreClickEvent showMoreClickEvent;
            if (ClickMoreView.this.babelScopeTemp == null || ClickMoreView.this.babelScopeTemp.iFloorUI == null || this.f4379g == null) {
                return;
            }
            Bundle bundle = new Bundle();
            FlatConfig flatConfig = this.f4380h.flatConfig;
            String str = "";
            if (flatConfig != null && (showMoreClickEvent = flatConfig.showMoreClickEvent) != null && !TextUtils.isEmpty(showMoreClickEvent.params)) {
                Map<String, String> jsonToMap = JsonUtil.jsonToMap(this.f4380h.flatConfig.showMoreClickEvent.params);
                if (jsonToMap.get("showMoreQueryParam") != null) {
                    str = jsonToMap.get("showMoreQueryParam");
                }
            }
            bundle.putString("flex_queryParam", str);
            bundle.putInt("flex_clickTimes", this.f4379g.flexClickTimes);
            ClickMoreView.this.babelScopeTemp.iFloorUI.sendEvent(new BaseEvent("clickMore", "\u5e73\u94fa\u70b9\u51fb\u5c55\u793a\u66f4\u591a\u6570\u636e", bundle));
            this.f4379g.flexClickTimes++;
            com.jd.lib.flexcube.widgets.b.a.a(ClickMoreView.this.mContext, view, this.f4380h.flatConfig.showMoreClickEvent, ClickMoreView.this.babelScopeTemp);
        }
    }

    public ClickMoreView(Context context) {
        super(context);
        this.mContext = context;
    }

    public void sendExposureData(BabelScope babelScope, ExposureInfo exposureInfo) {
        if (babelScope != null) {
            try {
                if (babelScope.iFloorUI == null || exposureInfo == null || TextUtils.isEmpty(exposureInfo.eventId)) {
                    return;
                }
                if (TextUtils.isEmpty(exposureInfo.srv)) {
                    return;
                }
                babelScope.iFloorUI.sendExposureData(MtaData.Builder.from(exposureInfo.eventId, exposureInfo.srv).jsonParam(exposureInfo.srvData).split(true).build());
            } catch (Throwable unused) {
            }
        }
    }

    public void updateStyle(@NonNull FlexCubeModel flexCubeModel, FloorConfig floorConfig, BabelScope babelScope) {
        if (floorConfig != null && floorConfig.flatConfig != null) {
            this.babelScopeTemp = babelScope;
            setOrientation(0);
            setGravity(17);
            if ("2".equals(floorConfig.flatConfig.showMoreButtonStyle) && !TextUtils.isEmpty(floorConfig.flatConfig.showMoreButtonUrl)) {
                ImageView imageView = this.imageView;
                if (imageView == null || imageView.getParent() == null) {
                    ImageView newImageView = ImageLoad.newImageView(this.mContext);
                    this.imageView = newImageView;
                    addView(newImageView, new ViewGroup.LayoutParams(-1, -1));
                }
                this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                CommonServiceUtil.displayImage(floorConfig.flatConfig.showMoreButtonUrl, this.imageView);
            } else {
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(floorConfig.flatConfig.showMoreButtonBgColor, Color.argb(255, 255, 81, 81)));
                TextView textView = this.textView;
                if (textView == null || textView.getParent() == null) {
                    TextView textView2 = new TextView(this.mContext);
                    this.textView = textView2;
                    addView(textView2, new LinearLayout.LayoutParams(-2, -2));
                }
                this.textView.setTextSize(0, DPIUtil.getWidthByDesignValue1125(this.mContext, 42));
                this.textView.setText("\u70b9\u51fb\u67e5\u770b\u66f4\u591a");
                this.textView.setGravity(17);
                this.textView.setTextColor(-1);
                Drawable drawable = ContextCompat.getDrawable(this.mContext, R.drawable.flex_key_down);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    this.textView.setCompoundDrawablePadding(DPIUtil.getWidthByDesignValue1125(this.mContext, 12));
                    this.textView.setCompoundDrawables(null, null, drawable, null);
                }
            }
            if (floorConfig.flatConfig.showMoreClickEvent != null) {
                setOnClickListener(new a(flexCubeModel, floorConfig));
                sendExposureData(babelScope, floorConfig.flatConfig.showMoreClickEvent.exposureInfo);
                return;
            }
            setOnClickListener(null);
            setClickable(false);
            return;
        }
        setLayoutParams(new FrameLayout.LayoutParams(-1, 0));
    }
}

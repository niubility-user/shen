package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.yoga.YogaEdge;
import com.jd.dynamic.yoga.YogaNode;
import com.jd.dynamic.yoga.YogaValue;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class q extends o<YogaLayout> {

    /* renamed from: h  reason: collision with root package name */
    private boolean f2463h;

    /* renamed from: i  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.g.b<YogaLayout.LayoutParams> f2464i;

    /* renamed from: j  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<YogaLayout> f2465j;

    public q() {
        this.f2463h = false;
        this.f2464i = new com.jd.dynamic.lib.viewparse.g.d();
        this.f2465j = new com.jd.dynamic.lib.viewparse.c.o();
    }

    public q(boolean z) {
        this.f2463h = false;
        this.f2464i = new com.jd.dynamic.lib.viewparse.g.d();
        this.f2465j = new com.jd.dynamic.lib.viewparse.c.o();
        this.f2463h = z;
    }

    private void n(HashMap<String, String> hashMap, YogaLayout yogaLayout) {
        YogaNode yogaNode;
        YogaEdge yogaEdge;
        YogaNode yogaNode2;
        YogaEdge yogaEdge2;
        YogaNode yogaNode3;
        YogaEdge yogaEdge3;
        if (hashMap.containsKey(DYConstants.DY_SHADOW_OFFSET)) {
            String str = hashMap.get(DYConstants.DY_SHADOW_OFFSET);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String[] split = str.split(DYConstants.DY_REGEX_COMMA);
            try {
                if (split.length < 2) {
                    if (split.length == 1) {
                        int dip2px = DPIUtil.dip2px(Float.parseFloat(split[0]));
                        if (dip2px > 0) {
                            yogaNode = yogaLayout.getYogaNode();
                            yogaEdge = YogaEdge.RIGHT;
                        } else {
                            yogaNode = yogaLayout.getYogaNode();
                            yogaEdge = YogaEdge.LEFT;
                            dip2px = Math.abs(dip2px);
                        }
                        yogaNode.setPadding(yogaEdge, dip2px);
                        return;
                    }
                    return;
                }
                int dip2px2 = DPIUtil.dip2px(Float.parseFloat(split[0]));
                int dip2px3 = DPIUtil.dip2px(Float.parseFloat(split[1]));
                if (dip2px2 > 0) {
                    yogaNode2 = yogaLayout.getYogaNode();
                    yogaEdge2 = YogaEdge.RIGHT;
                } else {
                    yogaNode2 = yogaLayout.getYogaNode();
                    yogaEdge2 = YogaEdge.LEFT;
                    dip2px2 = Math.abs(dip2px2);
                }
                yogaNode2.setPadding(yogaEdge2, dip2px2);
                if (dip2px3 > 0) {
                    yogaNode3 = yogaLayout.getYogaNode();
                    yogaEdge3 = YogaEdge.BOTTOM;
                } else {
                    yogaNode3 = yogaLayout.getYogaNode();
                    yogaEdge3 = YogaEdge.TOP;
                    dip2px3 = Math.abs(dip2px3);
                }
                yogaNode3.setPadding(yogaEdge3, dip2px3);
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "ShadowViewParse parseAttribute shadowOffsetDx error", null, null, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.o
    public ViewGroup.LayoutParams i(Context context, ViewNode viewNode) {
        ViewGroup.LayoutParams i2 = super.i(context, viewNode);
        if (viewNode.getAttributes() == null) {
            return i2;
        }
        return this.f2464i.a(context, com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), i2 instanceof YogaLayout.LayoutParams ? Build.VERSION.SDK_INT >= 19 ? new YogaLayout.LayoutParams((YogaLayout.LayoutParams) i2) : new YogaLayout.LayoutParams((ViewGroup.MarginLayoutParams) i2) : i2 instanceof ViewGroup.MarginLayoutParams ? new YogaLayout.LayoutParams((ViewGroup.MarginLayoutParams) i2) : new YogaLayout.LayoutParams(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public YogaLayout d(Context context) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public YogaLayout e(Context context, ViewNode viewNode) {
        YogaLayout yogaLayout = new YogaLayout(context, (YogaLayout.LayoutParams) this.f2464i.a(context, viewNode.getAttributes(), new YogaLayout.LayoutParams(-2, -2)));
        if (this.f2463h) {
            if (yogaLayout.getYogaNode() != null) {
                YogaValue padding = yogaLayout.getYogaNode().getPadding(YogaEdge.ALL);
                YogaValue padding2 = yogaLayout.getYogaNode().getPadding(YogaEdge.LEFT);
                YogaValue padding3 = yogaLayout.getYogaNode().getPadding(YogaEdge.RIGHT);
                YogaValue padding4 = yogaLayout.getYogaNode().getPadding(YogaEdge.TOP);
                YogaValue padding5 = yogaLayout.getYogaNode().getPadding(YogaEdge.BOTTOM);
                if (Float.isNaN(padding.value) && Float.isNaN(padding2.value) && Float.isNaN(padding3.value) && Float.isNaN(padding4.value) && Float.isNaN(padding5.value)) {
                    n(viewNode.getAttributes(), yogaLayout);
                }
            }
            yogaLayout.initShadow();
        }
        return yogaLayout;
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public YogaLayout a(ViewNode viewNode, Context context) {
        YogaLayout yogaLayout = (YogaLayout) super.j(viewNode, context);
        if (this.f2463h) {
            this.f2465j.a(viewNode.getAttributes(), yogaLayout);
        }
        return yogaLayout;
    }
}

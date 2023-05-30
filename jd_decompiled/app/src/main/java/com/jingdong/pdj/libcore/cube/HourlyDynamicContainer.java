package com.jingdong.pdj.libcore.cube;

import android.content.Context;
import android.view.View;
import com.jd.dynamic.apis.DYContainerConfig;
import com.jd.dynamic.apis.DynamicContainer;
import com.jd.dynamic.apis.IViewBindListener;
import com.jd.dynamic.apis.IViewCreateListener;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.pdj.libcore.cube.DynamicUtils;
import com.jingdong.pdj.libcore.screen.HourlyLayoutSize;
import com.jingdong.sdk.oklog.OKLog;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import point.DJPointVisibleUtil;
import point.view.DJPointFrameLayout;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\"\u001a\u00020\u001c\u0012\u0006\u0010$\u001a\u00020#\u0012\u0006\u0010&\u001a\u00020%\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00008\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0018\u001a\u00020\u00178\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!\u00a8\u0006)"}, d2 = {"Lcom/jingdong/pdj/libcore/cube/HourlyDynamicContainer;", "Lpoint/view/DJPointFrameLayout;", "", "createDynamicViewWithNetwork", "()V", "Lorg/json/JSONObject;", "data", "setData", "(Lorg/json/JSONObject;)V", "Lpoint/DJPointVisibleUtil;", "djPointVisibleUtil", "bindDynamicView", "(Lorg/json/JSONObject;Lpoint/DJPointVisibleUtil;)V", "rootView", "Lcom/jingdong/pdj/libcore/cube/HourlyDynamicContainer;", "mDjPointVisibleUtil", "Lpoint/DJPointVisibleUtil;", "Lcom/jd/dynamic/apis/DynamicContainer;", "container", "Lcom/jd/dynamic/apis/DynamicContainer;", "Lcom/jd/dynamic/apis/DYContainerConfig;", "config", "Lcom/jd/dynamic/apis/DYContainerConfig;", "Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction;", "commFunction", "Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction;", "getCommFunction", "()Lcom/jingdong/pdj/libcore/cube/HourlyCustomFunction;", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", "containerSize", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", AnnoConst.Constructor_Context, "", "businessType", "", "templateId", "<init>", "(Landroid/content/Context;ILjava/lang/String;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyDynamicContainer extends DJPointFrameLayout {
    @NotNull
    private final HourlyCustomFunction commFunction;
    private final DYContainerConfig config;
    private DynamicContainer container;
    private final HourlyLayoutSize containerSize;
    private final Context mContext;
    private DJPointVisibleUtil mDjPointVisibleUtil;
    private final HourlyDynamicContainer rootView;

    public HourlyDynamicContainer(@NotNull Context context, int i2, @NotNull String str) {
        super(context);
        HourlyLayoutSize hourlyLayoutSize = new HourlyLayoutSize(-1, -2);
        this.containerSize = hourlyLayoutSize;
        this.rootView = this;
        this.mContext = context;
        HourlyCustomFunction hourlyCustomFunction = new HourlyCustomFunction(context, i2);
        this.commFunction = hourlyCustomFunction;
        DynamicUtils.Companion companion = DynamicUtils.INSTANCE;
        DYContainerConfig dynamicConfig = companion.getDynamicConfig(context, i2, str, hourlyCustomFunction);
        this.config = dynamicConfig;
        DynamicContainer dynamicView = companion.getDynamicView(dynamicConfig);
        this.container = dynamicView;
        if (dynamicView != null) {
            addView(dynamicView, hourlyLayoutSize.getFLParams(dynamicView));
            dynamicView.createViewAsync(new IViewCreateListener() { // from class: com.jingdong.pdj.libcore.cube.HourlyDynamicContainer$$special$$inlined$let$lambda$1
                @Override // com.jd.dynamic.apis.IViewCreateListener
                public final void onDynamicViewCreate() {
                    OKLog.i("DynamicContainer", "\u4f7f\u7528\u672c\u5730\u6210\u529f");
                }

                @Override // com.jd.dynamic.apis.IViewCreateListener
                public final void onError(@NotNull DynamicException dynamicException) {
                    OKLog.i("DynamicContainer", "\u4f7f\u7528\u672c\u5730\u5931\u8d25");
                    HourlyDynamicContainer.this.createDynamicViewWithNetwork();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createDynamicViewWithNetwork() {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer != null) {
            dynamicContainer.createViewWithNetwork(new IViewCreateListener() { // from class: com.jingdong.pdj.libcore.cube.HourlyDynamicContainer$createDynamicViewWithNetwork$1
                @Override // com.jd.dynamic.apis.IViewCreateListener
                public final void onDynamicViewCreate() {
                    OKLog.i("DynamicContainer", "\u7f51\u7edc\u62c9\u53d6\u6a21\u7248\u6210\u529f");
                }

                @Override // com.jd.dynamic.apis.IViewCreateListener
                public final void onError(@NotNull DynamicException exception) {
                    OKLog.i("DynamicContainer", "\u7f51\u7edc\u62c9\u53d6\u6a21\u7248\u5931\u8d25");
                    HourlyDynamicContainer.this.setVisibility(8);
                }
            });
        }
    }

    private final void setData(JSONObject data) {
        DynamicContainer dynamicContainer = this.container;
        if (dynamicContainer != null) {
            dynamicContainer.setData(data);
        }
        OKLog.i("DynamicContainer", "\u8bbe\u7f6e\u6570\u636e" + data.toString());
        DynamicContainer dynamicContainer2 = this.container;
        if (dynamicContainer2 != null) {
            dynamicContainer2.bindViewAsync(new IViewBindListener() { // from class: com.jingdong.pdj.libcore.cube.HourlyDynamicContainer$setData$1
                /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
                    r0 = r3.this$0.mDjPointVisibleUtil;
                 */
                @Override // com.jd.dynamic.apis.IViewBindListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onDynamicViewBind() {
                    /*
                        r3 = this;
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r0 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        com.jingdong.pdj.libcore.cube.HourlyCustomFunction r0 = r0.getCommFunction()
                        com.jingdong.pdj.libcore.point.PointData r0 = r0.getCubePointData()
                        if (r0 == 0) goto L27
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r0 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        point.DJPointVisibleUtil r0 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.access$getMDjPointVisibleUtil$p(r0)
                        if (r0 == 0) goto L27
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r1 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r1 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.access$getRootView$p(r1)
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r2 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        com.jingdong.pdj.libcore.cube.HourlyCustomFunction r2 = r2.getCommFunction()
                        com.jingdong.pdj.libcore.point.PointData r2 = r2.getCubePointData()
                        r0.setPointViewData(r1, r2)
                    L27:
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r0 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        r0.AgainAttach()
                        java.lang.String r0 = "DynamicContainer"
                        java.lang.String r1 = "\u7ed1\u5b9a\u6570\u636e\u6210\u529f"
                        com.jingdong.sdk.oklog.OKLog.i(r0, r1)
                        com.jingdong.pdj.libcore.cube.HourlyDynamicContainer r0 = com.jingdong.pdj.libcore.cube.HourlyDynamicContainer.this
                        r1 = 0
                        r0.setVisibility(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.pdj.libcore.cube.HourlyDynamicContainer$setData$1.onDynamicViewBind():void");
                }

                @Override // com.jd.dynamic.apis.IViewBindListener
                public final void onError(@NotNull DynamicException exception) {
                    OKLog.i("DynamicContainer", "\u7ed1\u5b9a\u5931\u8d25");
                    HourlyDynamicContainer.this.setVisibility(8);
                }
            });
        }
        HourlyLayoutSize.checkSizeChanged((View) this.container, this.containerSize, true);
    }

    public final void bindDynamicView(@NotNull JSONObject data, @Nullable DJPointVisibleUtil djPointVisibleUtil) {
        this.mDjPointVisibleUtil = djPointVisibleUtil;
        this.commFunction.setData(data);
        setData(data);
    }

    @NotNull
    public final HourlyCustomFunction getCommFunction() {
        return this.commFunction;
    }
}

package com.jd.dynamic.apis;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import com.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.b.a.b;
import com.jd.dynamic.b.i.a;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IRecycleListener;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.error.DynamicException;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import com.jd.dynamic.lib.views.skeleton.shimmer.Shimmer;
import com.jd.dynamic.lib.views.skeleton.shimmer.ShimmerFrameLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00be\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0002xyB\u0011\b\u0016\u0012\u0006\u0010n\u001a\u00020m\u00a2\u0006\u0004\bo\u0010pB\u001b\b\u0016\u0012\u0006\u0010n\u001a\u00020m\u0012\b\u0010r\u001a\u0004\u0018\u00010q\u00a2\u0006\u0004\bo\u0010sB#\b\u0016\u0012\u0006\u0010n\u001a\u00020m\u0012\b\u0010r\u001a\u0004\u0018\u00010q\u0012\u0006\u0010t\u001a\u000201\u00a2\u0006\u0004\bo\u0010uB+\b\u0017\u0012\u0006\u0010n\u001a\u00020m\u0012\b\u0010r\u001a\u0004\u0018\u00010q\u0012\u0006\u0010t\u001a\u000201\u0012\u0006\u0010v\u001a\u000201\u00a2\u0006\u0004\bo\u0010wJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J=\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0018\u0010\u0019J=\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u000e\u00a2\u0006\u0004\b\u001d\u0010\u0012J\u0015\u0010 \u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001e\u00a2\u0006\u0004\b \u0010!J\u0015\u0010$\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\"\u00a2\u0006\u0004\b$\u0010%J\u0019\u0010(\u001a\u00020\u000e2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b(\u0010)J\u000f\u0010*\u001a\u0004\u0018\u00010&\u00a2\u0006\u0004\b*\u0010+J\u0019\u0010.\u001a\u00020\u000e2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\u0002\u00a2\u0006\u0004\b0\u0010\u0004J\u0017\u00103\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u000201\u00a2\u0006\u0004\b3\u00104J\u0015\u00100\u001a\u00020\u000e2\u0006\u00106\u001a\u000205\u00a2\u0006\u0004\b0\u00107J\r\u00108\u001a\u00020\u0002\u00a2\u0006\u0004\b8\u0010\u0004J\u0017\u0010:\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u000109\u00a2\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00020\u000e\u00a2\u0006\u0004\b<\u0010\u0012J\r\u0010=\u001a\u00020\u000e\u00a2\u0006\u0004\b=\u0010\u0012J\r\u0010>\u001a\u00020\u0002\u00a2\u0006\u0004\b>\u0010\u0004J\u0017\u0010@\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010?\u00a2\u0006\u0004\b@\u0010AJ\u0015\u0010C\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u0002\u00a2\u0006\u0004\bC\u0010\u0019J\u0017\u0010D\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010?\u00a2\u0006\u0004\bD\u0010AJ\u0019\u0010F\u001a\u00020\u000e2\b\u0010E\u001a\u0004\u0018\u00010\u001aH\u0016\u00a2\u0006\u0004\bF\u0010GJ\u001d\u0010J\u001a\u00020\u000e2\u0006\u0010H\u001a\u0002012\u0006\u0010I\u001a\u000201\u00a2\u0006\u0004\bJ\u0010KJ#\u0010F\u001a\u00020\u000e2\b\u0010E\u001a\u0004\u0018\u00010\u001a2\b\u0010M\u001a\u0004\u0018\u00010LH\u0016\u00a2\u0006\u0004\bF\u0010NJ#\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Q\u0018\u00010P2\u0006\u0010O\u001a\u00020\u0007\u00a2\u0006\u0004\bR\u0010SJ-\u0010R\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Q\u0018\u00010P2\u0006\u0010O\u001a\u00020\u00072\b\u0010T\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\bR\u0010UJ-\u0010W\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Q\u0018\u00010P2\u0006\u0010V\u001a\u00020\u00072\b\u0010T\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0004\bW\u0010UR\u0018\u0010Y\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010[\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b[\u0010\\R\u0016\u0010]\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010_\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010b\u001a\u0004\u0018\u00010a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bb\u0010cR\u0018\u0010e\u001a\u0004\u0018\u00010d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\be\u0010fR\u0018\u0010g\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bg\u0010hR\u0018\u0010i\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bi\u0010jR\u0018\u0010k\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bk\u0010l\u00a8\u0006z"}, d2 = {"Lcom/jd/dynamic/apis/DynamicContainer;", "Landroid/widget/FrameLayout;", "", "isConfigInvalid", "()Z", "Lcom/jd/dynamic/entity/ViewNode;", "viewNode", "", "mtaId", "Lcom/jd/dynamic/entity/ResultEntity;", "obj", "source", "", "time", "", "showViewNode", "(Lcom/jd/dynamic/entity/ViewNode;Ljava/lang/String;Lcom/jd/dynamic/entity/ResultEntity;Ljava/lang/String;J)V", "prepareContainer", "()V", "Ljava/lang/Runnable;", "runnable", "checkAndPost", "(Ljava/lang/Runnable;)V", "show", "hideOrShowSkeletonView", "(Z)V", "Landroid/view/View;", "innerCreateView", "(Lcom/jd/dynamic/entity/ViewNode;Ljava/lang/String;Lcom/jd/dynamic/entity/ResultEntity;Ljava/lang/String;J)Landroid/view/View;", "onViewRecycled", "Lcom/jd/dynamic/apis/DYContainerConfig;", "config", "attachConfig", "(Lcom/jd/dynamic/apis/DYContainerConfig;)V", "Lcom/jd/dynamic/apis/IDYContainerListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "attachListener", "(Lcom/jd/dynamic/apis/IDYContainerListener;)V", "Lorg/json/JSONObject;", "data", "setData", "(Lorg/json/JSONObject;)V", "getData", "()Lorg/json/JSONObject;", "Landroid/app/Dialog;", XView2Constants.XVIEW2_ACTION_DIALOG, "setDialog", "(Landroid/app/Dialog;)V", TrackLoadSettingsAtom.TYPE, "", CartConstant.KEY_CART_VID, "findChildViewById", "(I)Landroid/view/View;", "Lcom/jd/dynamic/apis/IContainerCallback;", "callback", "(Lcom/jd/dynamic/apis/IContainerCallback;)V", "refresh", "Lcom/jd/dynamic/apis/IViewBindListener;", "bindViewAsync", "(Lcom/jd/dynamic/apis/IViewBindListener;)V", "bindView", "resetData", "createView", "Lcom/jd/dynamic/apis/IViewCreateListener;", "createViewAsync", "(Lcom/jd/dynamic/apis/IViewCreateListener;)V", "isDark", "refreshDarkChange", "createViewWithNetwork", "child", "addView", "(Landroid/view/View;)V", "width", "height", "updateWidthAndHeight", "(II)V", "Landroid/view/ViewGroup$LayoutParams;", "params", "(Landroid/view/View;Landroid/view/ViewGroup$LayoutParams;)V", "customEvent", "", "", "onCustomEvent", "(Ljava/lang/String;)Ljava/util/Map;", "layoutId", "(Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map;", "eventIds", "executeEvents", "Lcom/jd/dynamic/apis/DynamicContainer$OnAddDynamicViewListener;", "onAddDynamicViewListener", "Lcom/jd/dynamic/apis/DynamicContainer$OnAddDynamicViewListener;", "mDialog", "Landroid/app/Dialog;", "isAsyncCreateView", "Z", "skeletonLayout", "Landroid/view/View;", "Lcom/jd/dynamic/lib/views/skeleton/shimmer/ShimmerFrameLayout;", "shimmerFrameLayout", "Lcom/jd/dynamic/lib/views/skeleton/shimmer/ShimmerFrameLayout;", "Lcom/jd/dynamic/base/DynamicTemplateEngine;", "engine", "Lcom/jd/dynamic/base/DynamicTemplateEngine;", "containerData", "Lorg/json/JSONObject;", "containerConfig", "Lcom/jd/dynamic/apis/DYContainerConfig;", "containerListener", "Lcom/jd/dynamic/apis/IDYContainerListener;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "AsyncCreateViewTask", "OnAddDynamicViewListener", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class DynamicContainer extends FrameLayout {
    private DYContainerConfig containerConfig;
    private JSONObject containerData;
    private IDYContainerListener containerListener;
    private DynamicTemplateEngine engine;
    private boolean isAsyncCreateView;
    private Dialog mDialog;
    private OnAddDynamicViewListener onAddDynamicViewListener;
    private ShimmerFrameLayout shimmerFrameLayout;
    private View skeletonLayout;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0001B-\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\b!\u0010\"J%\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0005\"\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0018\u001a\u00020\u00178\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \u00a8\u0006#"}, d2 = {"Lcom/jd/dynamic/apis/DynamicContainer$AsyncCreateViewTask;", "Landroid/os/AsyncTask;", "Lcom/jd/dynamic/apis/DYContainerConfig;", "Ljava/lang/Void;", "Landroid/view/View;", "", "voids", "doInBackground", "([Lcom/jd/dynamic/apis/DYContainerConfig;)Landroid/view/View;", "view", "", "onPostExecute", "(Landroid/view/View;)V", "", JshopConst.JSKEY_COUPON_BEGIN_TIME, "Ljava/lang/Long;", "getBeginTime", "()Ljava/lang/Long;", "Lcom/jd/dynamic/apis/IViewCreateListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "Lcom/jd/dynamic/apis/IViewCreateListener;", "getListener", "()Lcom/jd/dynamic/apis/IViewCreateListener;", "Lcom/jd/dynamic/apis/DynamicContainer;", "container", "Lcom/jd/dynamic/apis/DynamicContainer;", "getContainer", "()Lcom/jd/dynamic/apis/DynamicContainer;", "Lcom/jd/dynamic/entity/ResultEntity;", "entity", "Lcom/jd/dynamic/entity/ResultEntity;", "getEntity", "()Lcom/jd/dynamic/entity/ResultEntity;", "<init>", "(Lcom/jd/dynamic/apis/DynamicContainer;Lcom/jd/dynamic/apis/IViewCreateListener;Lcom/jd/dynamic/entity/ResultEntity;Ljava/lang/Long;)V", "lib_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final class AsyncCreateViewTask extends AsyncTask<DYContainerConfig, Void, View> {
        @NotNull
        private final DynamicContainer a;
        @Nullable
        private final IViewCreateListener b;
        @Nullable

        /* renamed from: c */
        private final ResultEntity f1718c;
        @Nullable
        private final Long d;

        public AsyncCreateViewTask(@NotNull DynamicContainer dynamicContainer, @Nullable IViewCreateListener iViewCreateListener, @Nullable ResultEntity resultEntity, @Nullable Long l2) {
            this.a = dynamicContainer;
            this.b = iViewCreateListener;
            this.f1718c = resultEntity;
            this.d = l2;
        }

        @Override // android.os.AsyncTask
        @Nullable
        public View doInBackground(@NotNull DYContainerConfig... voids) {
            ResultEntity b;
            DynamicContainer dynamicContainer;
            ViewNode viewNode;
            String str;
            ResultEntity resultEntity;
            DYContainerConfig dYContainerConfig = voids[0];
            long nanoTime = System.nanoTime();
            String str2 = dYContainerConfig.getModule() + CartConstant.KEY_YB_INFO_LINK + dYContainerConfig.getTemplateId();
            if (dYContainerConfig.getUseCacheView() && m.J(DynamicSdk.getEngine().templateEngineCache) && m.I(DynamicSdk.getEngine().templateEngineCache.get(str2))) {
                ArrayList<DynamicTemplateEngine> arrayList = DynamicSdk.getEngine().templateEngineCache.get(str2);
                if (m.I(arrayList)) {
                    if (arrayList == null) {
                        Intrinsics.throwNpe();
                    }
                    DynamicTemplateEngine engine = arrayList.remove(0);
                    Intrinsics.checkExpressionValueIsNotNull(engine, "engine");
                    if (engine.getRootContainer() != null) {
                        this.a.engine = engine;
                        DynamicTemplateEngine dynamicTemplateEngine = this.a.engine;
                        if (dynamicTemplateEngine == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine.setActivity((Activity) dYContainerConfig.getContext());
                        DynamicTemplateEngine dynamicTemplateEngine2 = this.a.engine;
                        if (dynamicTemplateEngine2 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine2.setCustomFactory(dYContainerConfig.getFactory());
                        DynamicTemplateEngine dynamicTemplateEngine3 = this.a.engine;
                        if (dynamicTemplateEngine3 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine3.setDialog(this.a.mDialog);
                        DynamicTemplateEngine dynamicTemplateEngine4 = this.a.engine;
                        if (dynamicTemplateEngine4 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine4.bizField = dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null;
                        DynamicTemplateEngine dynamicTemplateEngine5 = this.a.engine;
                        if (dynamicTemplateEngine5 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine5.setSystemCode(dYContainerConfig != null ? dYContainerConfig.getModule() : null);
                        DynamicTemplateEngine dynamicTemplateEngine6 = this.a.engine;
                        if (dynamicTemplateEngine6 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine6.useAsyncItem = dYContainerConfig != null ? Boolean.valueOf(dYContainerConfig.getUseAsyncItem()) : null;
                        DynamicTemplateEngine dynamicTemplateEngine7 = this.a.engine;
                        if (dynamicTemplateEngine7 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine7.useTagViewOptimize = dYContainerConfig != null && dYContainerConfig.getUseTagViewOptimize();
                        DynamicTemplateEngine dynamicTemplateEngine8 = this.a.engine;
                        if (dynamicTemplateEngine8 == null) {
                            Intrinsics.throwNpe();
                        }
                        Boolean valueOf = dYContainerConfig != null ? Boolean.valueOf(dYContainerConfig.getIsAutoListen()) : null;
                        if (valueOf == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine8.shouldAutoListenDarkStatus(valueOf.booleanValue());
                        DynamicTemplateEngine dynamicTemplateEngine9 = this.a.engine;
                        if (dynamicTemplateEngine9 == null) {
                            Intrinsics.throwNpe();
                        }
                        Integer valueOf2 = dYContainerConfig != null ? Integer.valueOf(dYContainerConfig.getContainerWidth()) : null;
                        if (valueOf2 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine9.containerWidth = valueOf2.intValue();
                        DynamicTemplateEngine dynamicTemplateEngine10 = this.a.engine;
                        if (dynamicTemplateEngine10 == null) {
                            Intrinsics.throwNpe();
                        }
                        Integer valueOf3 = dYContainerConfig != null ? Integer.valueOf(dYContainerConfig.getContainerHeight()) : null;
                        if (valueOf3 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine10.containerHeight = valueOf3.intValue();
                        DynamicTemplateEngine dynamicTemplateEngine11 = this.a.engine;
                        if (dynamicTemplateEngine11 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine11.isAttached = true;
                        DynamicTemplateEngine dynamicTemplateEngine12 = this.a.engine;
                        if (dynamicTemplateEngine12 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine12.parseViewListeners();
                        engine.getRootContainer().addLifecycleObserver();
                        t.e("createView", "get cached View time = ", DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
                        return engine.getRootContainer();
                    }
                }
            }
            long nanoTime2 = System.nanoTime();
            DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
            String startMtaStat = DynamicMtaUtil.startMtaStat(engine2.getAppType(), dYContainerConfig != null ? dYContainerConfig.getModule() : null, "", dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, false);
            ResultEntity resultEntity2 = this.f1718c;
            if (resultEntity2 != null) {
                DynamicContainer dynamicContainer2 = this.a;
                ViewNode viewNode2 = resultEntity2.viewNode;
                Intrinsics.checkExpressionValueIsNotNull(viewNode2, "entity.viewNode");
                ResultEntity resultEntity3 = this.f1718c;
                Long l2 = this.d;
                if (l2 == null) {
                    Intrinsics.throwNpe();
                }
                return dynamicContainer2.innerCreateView(viewNode2, startMtaStat, resultEntity3, "network", l2.longValue());
            }
            b o = b.o();
            Intrinsics.checkExpressionValueIsNotNull(o, "DYABConfigUtil.getInstance()");
            if (o.N()) {
                if (com.jd.dynamic.b.e.a.b.w(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null)) {
                    DynamicMtaUtil.startGetTemplate(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, startMtaStat);
                    long nanoTime3 = System.nanoTime();
                    ResultEntity b2 = a.b(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, R2.attr.layout_constraintHeight, startMtaStat, false, 16, null);
                    t.e("createView", "loadLocalTempFile", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime3));
                    if (m.F(b2)) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP, "DynamicContainer.createView \u5f3a\u5236\u4f7f\u7528\u515c\u5e95", dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, dYContainerConfig != null ? dYContainerConfig.getModule() : null, -1000, null, m.m(b2, null));
                        dynamicContainer = this.a;
                        if (b2 == null) {
                            Intrinsics.throwNpe();
                        }
                        viewNode = b2.viewNode;
                        Intrinsics.checkExpressionValueIsNotNull(viewNode, "entity!!.viewNode");
                        resultEntity = b2;
                        str = IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP;
                        return dynamicContainer.innerCreateView(viewNode, startMtaStat, resultEntity, str, nanoTime2);
                    }
                    return null;
                }
            }
            if (com.jd.dynamic.b.e.a.b.t(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null)) {
                Template a = com.jd.dynamic.b.e.a.b.a(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null);
                if (a == null) {
                    return null;
                }
                DynamicMtaUtil.updateTemplate(startMtaStat, a);
                String b3 = p.b(a.getDownloadUrl(), a.getDownloadFileName());
                StringBuilder sb = new StringBuilder();
                sb.append(com.jd.dynamic.b.e.a.b.m(dYContainerConfig != null ? dYContainerConfig.getModule() : null));
                sb.append(File.separator);
                sb.append(a.businessCode);
                File file = new File(sb.toString(), b3);
                if (!file.exists()) {
                    return null;
                }
                DynamicMtaUtil.startGetTemplate(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, startMtaStat);
                b = NewDynamicXParser.parseBinaryToResultEntity(file.getAbsolutePath(), false, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, dYContainerConfig != null ? dYContainerConfig.getModule() : null, startMtaStat);
                if (!m.F(b)) {
                    return null;
                }
                dynamicContainer = this.a;
                if (b == null) {
                    Intrinsics.throwNpe();
                }
                viewNode = b.viewNode;
                Intrinsics.checkExpressionValueIsNotNull(viewNode, "entity!!.viewNode");
                str = "diskCache";
            } else {
                DynamicSdk.getDriver().prepare(dYContainerConfig != null ? dYContainerConfig.getModule() : null, "");
                if (!com.jd.dynamic.b.e.a.b.w(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null)) {
                    return null;
                }
                DynamicMtaUtil.startGetTemplate(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, startMtaStat);
                b = a.b(dYContainerConfig != null ? dYContainerConfig.getModule() : null, dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, R2.attr.layout_constraintHeight, startMtaStat, false, 16, null);
                if (!m.F(b)) {
                    return null;
                }
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP, "DynamicContainer.createView \u4f7f\u7528\u515c\u5e95", dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null, dYContainerConfig != null ? dYContainerConfig.getModule() : null, -1009, null, m.m(b, null));
                dynamicContainer = this.a;
                if (b == null) {
                    Intrinsics.throwNpe();
                }
                viewNode = b.viewNode;
                Intrinsics.checkExpressionValueIsNotNull(viewNode, "entity!!.viewNode");
                str = IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP;
            }
            resultEntity = b;
            return dynamicContainer.innerCreateView(viewNode, startMtaStat, resultEntity, str, nanoTime2);
        }

        @Nullable
        /* renamed from: getBeginTime  reason: from getter */
        public final Long getD() {
            return this.d;
        }

        @NotNull
        /* renamed from: getContainer  reason: from getter */
        public final DynamicContainer getA() {
            return this.a;
        }

        @Nullable
        /* renamed from: getEntity  reason: from getter */
        public final ResultEntity getF1718c() {
            return this.f1718c;
        }

        @Nullable
        /* renamed from: getListener  reason: from getter */
        public final IViewCreateListener getB() {
            return this.b;
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x0099  */
        /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onPostExecute(@org.jetbrains.annotations.Nullable android.view.View r10) {
            /*
                r9 = this;
                if (r10 == 0) goto L9d
                long r0 = java.lang.System.nanoTime()
                int r2 = com.jd.dynamic.R.id.dynamic_root_view
                android.view.View r2 = r10.findViewById(r2)
                com.jd.dynamic.lib.viewparse.BaseFrameLayout r2 = (com.jd.dynamic.lib.viewparse.BaseFrameLayout) r2
                if (r2 == 0) goto L13
                r2.addLifecycleObserver()
            L13:
                r2 = 2
                java.lang.Object[] r3 = new java.lang.Object[r2]
                r4 = 0
                java.lang.String r5 = "createView"
                r3[r4] = r5
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "addLifecycleObserver time = "
                r6.append(r7)
                long r7 = java.lang.System.nanoTime()
                long r7 = r7 - r0
                java.lang.String r0 = com.jd.dynamic.base.DynamicMtaUtil.getCurMicroseconds(r7)
                r6.append(r0)
                java.lang.String r0 = r6.toString()
                r1 = 1
                r3[r1] = r0
                com.jd.dynamic.lib.utils.t.e(r3)
                long r6 = java.lang.System.nanoTime()
                com.jd.dynamic.apis.DynamicContainer r0 = r9.a
                boolean r0 = com.jd.dynamic.apis.DynamicContainer.access$isAsyncCreateView$p(r0)
                if (r0 == 0) goto L4b
                r0 = 4
                r10.setVisibility(r0)
            L4b:
                boolean r0 = r10 instanceof com.jd.dynamic.yoga.android.YogaLayout
                if (r0 == 0) goto L67
                r0 = r10
                com.jd.dynamic.yoga.android.YogaLayout r0 = (com.jd.dynamic.yoga.android.YogaLayout) r0
                com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r3 = r0.getYogaLayoutLayoutParams()
                if (r3 == 0) goto L67
                com.jd.dynamic.apis.DynamicContainer r3 = r9.a
                android.widget.FrameLayout$LayoutParams r8 = new android.widget.FrameLayout$LayoutParams
                com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r0 = r0.getYogaLayoutLayoutParams()
                r8.<init>(r0)
                r3.addView(r10, r8)
                goto L72
            L67:
                com.jd.dynamic.apis.DynamicContainer r0 = r9.a
                android.widget.FrameLayout$LayoutParams r3 = new android.widget.FrameLayout$LayoutParams
                r8 = -1
                r3.<init>(r8, r8)
                r0.addView(r10, r3)
            L72:
                java.lang.Object[] r10 = new java.lang.Object[r2]
                r10[r4] = r5
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "addView time = "
                r0.append(r2)
                long r2 = java.lang.System.nanoTime()
                long r2 = r2 - r6
                java.lang.String r2 = com.jd.dynamic.base.DynamicMtaUtil.getCurMicroseconds(r2)
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                r10[r1] = r0
                com.jd.dynamic.lib.utils.t.e(r10)
                com.jd.dynamic.apis.IViewCreateListener r10 = r9.b
                if (r10 == 0) goto Lab
                r10.onDynamicViewCreate()
                goto Lab
            L9d:
                com.jd.dynamic.apis.IViewCreateListener r10 = r9.b
                if (r10 == 0) goto Lab
                com.jd.dynamic.lib.error.DynamicException r0 = new com.jd.dynamic.lib.error.DynamicException
                java.lang.String r1 = "dynamic view is null"
                r0.<init>(r1)
                r10.onError(r0)
            Lab:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.apis.DynamicContainer.AsyncCreateViewTask.onPostExecute(android.view.View):void");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bb\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/dynamic/apis/DynamicContainer$OnAddDynamicViewListener;", "", "", "onAddView", "()V", "lib_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public interface OnAddDynamicViewListener {
        void onAddView();
    }

    public DynamicContainer(@NotNull Context context) {
        this(context, null);
    }

    public DynamicContainer(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DynamicContainer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @RequiresApi(21)
    public DynamicContainer(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    private final void checkAndPost(Runnable runnable) {
        if (m.D()) {
            runnable.run();
            return;
        }
        DYContainerConfig dYContainerConfig = this.containerConfig;
        Context context = dYContainerConfig != null ? dYContainerConfig.getContext() : null;
        Activity activity = context instanceof Activity ? context : null;
        if (activity != null) {
            activity.runOnUiThread(runnable);
        }
    }

    public final void hideOrShowSkeletonView(boolean show) {
        if (show) {
            View view = this.skeletonLayout;
            if (view != null) {
                view.setVisibility(0);
            }
            ShimmerFrameLayout shimmerFrameLayout = this.shimmerFrameLayout;
            if (shimmerFrameLayout != null) {
                shimmerFrameLayout.showShimmer(true);
                return;
            }
            return;
        }
        View view2 = this.skeletonLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        ShimmerFrameLayout shimmerFrameLayout2 = this.shimmerFrameLayout;
        if (shimmerFrameLayout2 != null) {
            shimmerFrameLayout2.hideShimmer();
        }
    }

    public final View innerCreateView(ViewNode viewNode, String mtaId, ResultEntity obj, String source, long time) {
        String pkgName;
        DynamicTemplateEngine dynamicTemplateEngine;
        long nanoTime = System.nanoTime();
        String curMicroseconds = DynamicMtaUtil.getCurMicroseconds(nanoTime - time);
        DYContainerConfig dYContainerConfig = this.containerConfig;
        String module = dYContainerConfig != null ? dYContainerConfig.getModule() : null;
        DYContainerConfig dYContainerConfig2 = this.containerConfig;
        DynamicMtaUtil.reportAPILoad(module, dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null, source, curMicroseconds);
        DYContainerConfig dYContainerConfig3 = this.containerConfig;
        String pkgName2 = dYContainerConfig3 != null ? dYContainerConfig3.getPkgName() : null;
        if (pkgName2 == null || pkgName2.length() == 0) {
            pkgName = "";
        } else {
            DYContainerConfig dYContainerConfig4 = this.containerConfig;
            pkgName = dYContainerConfig4 != null ? dYContainerConfig4.getPkgName() : null;
        }
        Context context = getContext();
        if (context != null) {
            Activity activity = (Activity) context;
            DYContainerConfig dYContainerConfig5 = this.containerConfig;
            this.engine = new DynamicTemplateEngine(pkgName, activity, this, dYContainerConfig5 != null ? dYContainerConfig5.getFactory() : null);
            t.e("createView", "new engine", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime));
            long nanoTime2 = System.nanoTime();
            if (obj != null && (dynamicTemplateEngine = this.engine) != null) {
                dynamicTemplateEngine.entity = obj;
            }
            DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
            if (dynamicTemplateEngine2 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig6 = this.containerConfig;
            Integer valueOf = dYContainerConfig6 != null ? Integer.valueOf(dYContainerConfig6.getContainerWidth()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine2.containerWidth = valueOf.intValue();
            DynamicTemplateEngine dynamicTemplateEngine3 = this.engine;
            if (dynamicTemplateEngine3 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig7 = this.containerConfig;
            Integer valueOf2 = dYContainerConfig7 != null ? Integer.valueOf(dYContainerConfig7.getContainerHeight()) : null;
            if (valueOf2 == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine3.containerHeight = valueOf2.intValue();
            DynamicTemplateEngine dynamicTemplateEngine4 = this.engine;
            if (dynamicTemplateEngine4 == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine4.setDialog(this.mDialog);
            DynamicTemplateEngine dynamicTemplateEngine5 = this.engine;
            if (dynamicTemplateEngine5 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig8 = this.containerConfig;
            dynamicTemplateEngine5.bizField = dYContainerConfig8 != null ? dYContainerConfig8.getTemplateId() : null;
            DynamicTemplateEngine dynamicTemplateEngine6 = this.engine;
            if (dynamicTemplateEngine6 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig9 = this.containerConfig;
            dynamicTemplateEngine6.setSystemCode(dYContainerConfig9 != null ? dYContainerConfig9.getModule() : null);
            DynamicTemplateEngine dynamicTemplateEngine7 = this.engine;
            if (dynamicTemplateEngine7 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig10 = this.containerConfig;
            Boolean valueOf3 = dYContainerConfig10 != null ? Boolean.valueOf(dYContainerConfig10.getIsAutoListen()) : null;
            if (valueOf3 == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine7.shouldAutoListenDarkStatus(valueOf3.booleanValue());
            DynamicTemplateEngine dynamicTemplateEngine8 = this.engine;
            if (dynamicTemplateEngine8 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig11 = this.containerConfig;
            dynamicTemplateEngine8.useAsyncItem = dYContainerConfig11 != null ? Boolean.valueOf(dYContainerConfig11.getUseAsyncItem()) : null;
            DynamicTemplateEngine dynamicTemplateEngine9 = this.engine;
            if (dynamicTemplateEngine9 == null) {
                Intrinsics.throwNpe();
            }
            DYContainerConfig dYContainerConfig12 = this.containerConfig;
            Boolean valueOf4 = dYContainerConfig12 != null ? Boolean.valueOf(dYContainerConfig12.getUseTagViewOptimize()) : null;
            if (valueOf4 == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine9.useTagViewOptimize = valueOf4.booleanValue();
            t.e("createView", "setting engine", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime2));
            long nanoTime3 = System.nanoTime();
            DynamicTemplateEngine dynamicTemplateEngine10 = this.engine;
            if (dynamicTemplateEngine10 == null) {
                Intrinsics.throwNpe();
            }
            View view = dynamicTemplateEngine10.returnDynamicView(viewNode, mtaId);
            t.e("createView", "engine createView", "time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime3));
            String curMicroseconds2 = DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime);
            DYContainerConfig dYContainerConfig13 = this.containerConfig;
            String module2 = dYContainerConfig13 != null ? dYContainerConfig13.getModule() : null;
            DYContainerConfig dYContainerConfig14 = this.containerConfig;
            DynamicMtaUtil.reportAPIRender(module2, dYContainerConfig14 != null ? dYContainerConfig14.getTemplateId() : null, source, curMicroseconds2);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            return view;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    static /* synthetic */ View innerCreateView$default(DynamicContainer dynamicContainer, ViewNode viewNode, String str, ResultEntity resultEntity, String str2, long j2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            resultEntity = null;
        }
        return dynamicContainer.innerCreateView(viewNode, str, resultEntity, str2, j2);
    }

    private final boolean isConfigInvalid() {
        return this.containerConfig == null;
    }

    public final void prepareContainer() {
        if (getChildCount() > 0) {
            removeAllViews();
        }
    }

    public static /* synthetic */ void setData$default(DynamicContainer dynamicContainer, JSONObject jSONObject, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            jSONObject = null;
        }
        dynamicContainer.setData(jSONObject);
    }

    public static /* synthetic */ void setDialog$default(DynamicContainer dynamicContainer, Dialog dialog, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            dialog = null;
        }
        dynamicContainer.setDialog(dialog);
    }

    public final void showViewNode(ViewNode viewNode, String mtaId, ResultEntity obj, String source, long time) {
        String pkgName;
        long nanoTime = System.nanoTime();
        String curMicroseconds = DynamicMtaUtil.getCurMicroseconds(nanoTime - time);
        DYConstants.DYLog("showViewNode start time :" + nanoTime + " time is: " + time + " load time: " + curMicroseconds + " source: " + source);
        DYContainerConfig dYContainerConfig = this.containerConfig;
        String module = dYContainerConfig != null ? dYContainerConfig.getModule() : null;
        DYContainerConfig dYContainerConfig2 = this.containerConfig;
        DynamicMtaUtil.reportAPILoad(module, dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null, source, curMicroseconds);
        checkAndPost(new Runnable() { // from class: com.jd.dynamic.apis.DynamicContainer$showViewNode$1
            {
                DynamicContainer.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                DynamicContainer.this.setVisibility(0);
            }
        });
        DYContainerConfig dYContainerConfig3 = this.containerConfig;
        String pkgName2 = dYContainerConfig3 != null ? dYContainerConfig3.getPkgName() : null;
        boolean z = false;
        if (pkgName2 == null || pkgName2.length() == 0) {
            pkgName = "";
        } else {
            DYContainerConfig dYContainerConfig4 = this.containerConfig;
            pkgName = dYContainerConfig4 != null ? dYContainerConfig4.getPkgName() : null;
        }
        Context context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
        }
        Activity activity = (Activity) context;
        DYContainerConfig dYContainerConfig5 = this.containerConfig;
        DynamicTemplateEngine dynamicTemplateEngine = new DynamicTemplateEngine(pkgName, activity, this, dYContainerConfig5 != null ? dYContainerConfig5.getFactory() : null);
        this.engine = dynamicTemplateEngine;
        if (obj != null) {
            dynamicTemplateEngine.entity = obj;
        }
        DYContainerConfig dYContainerConfig6 = this.containerConfig;
        Integer valueOf = dYContainerConfig6 != null ? Integer.valueOf(dYContainerConfig6.getContainerWidth()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        dynamicTemplateEngine.containerWidth = valueOf.intValue();
        DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
        if (dynamicTemplateEngine2 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig7 = this.containerConfig;
        Integer valueOf2 = dYContainerConfig7 != null ? Integer.valueOf(dYContainerConfig7.getContainerHeight()) : null;
        if (valueOf2 == null) {
            Intrinsics.throwNpe();
        }
        dynamicTemplateEngine2.containerHeight = valueOf2.intValue();
        DynamicTemplateEngine dynamicTemplateEngine3 = this.engine;
        if (dynamicTemplateEngine3 == null) {
            Intrinsics.throwNpe();
        }
        dynamicTemplateEngine3.setDialog(this.mDialog);
        DynamicTemplateEngine dynamicTemplateEngine4 = this.engine;
        if (dynamicTemplateEngine4 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig8 = this.containerConfig;
        dynamicTemplateEngine4.bizField = dYContainerConfig8 != null ? dYContainerConfig8.getTemplateId() : null;
        DynamicTemplateEngine dynamicTemplateEngine5 = this.engine;
        if (dynamicTemplateEngine5 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig9 = this.containerConfig;
        dynamicTemplateEngine5.setSystemCode(dYContainerConfig9 != null ? dYContainerConfig9.getModule() : null);
        DynamicTemplateEngine dynamicTemplateEngine6 = this.engine;
        if (dynamicTemplateEngine6 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig10 = this.containerConfig;
        dynamicTemplateEngine6.useAsyncItem = dYContainerConfig10 != null ? Boolean.valueOf(dYContainerConfig10.getUseAsyncItem()) : null;
        DynamicTemplateEngine dynamicTemplateEngine7 = this.engine;
        if (dynamicTemplateEngine7 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig11 = this.containerConfig;
        if (dYContainerConfig11 != null && dYContainerConfig11.getUseTagViewOptimize()) {
            z = true;
        }
        dynamicTemplateEngine7.useTagViewOptimize = z;
        DynamicTemplateEngine dynamicTemplateEngine8 = this.engine;
        if (dynamicTemplateEngine8 == null) {
            Intrinsics.throwNpe();
        }
        DYContainerConfig dYContainerConfig12 = this.containerConfig;
        Boolean valueOf3 = dYContainerConfig12 != null ? Boolean.valueOf(dYContainerConfig12.getIsAutoListen()) : null;
        if (valueOf3 == null) {
            Intrinsics.throwNpe();
        }
        dynamicTemplateEngine8.shouldAutoListenDarkStatus(valueOf3.booleanValue());
        DynamicTemplateEngine dynamicTemplateEngine9 = this.engine;
        if (dynamicTemplateEngine9 == null) {
            Intrinsics.throwNpe();
        }
        dynamicTemplateEngine9.initTPFromContainer(viewNode, this.containerData, mtaId);
        IDYContainerListener iDYContainerListener = this.containerListener;
        if (iDYContainerListener != null) {
            iDYContainerListener.onLoad();
        }
        String curMicroseconds2 = DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime);
        DYContainerConfig dYContainerConfig13 = this.containerConfig;
        String module2 = dYContainerConfig13 != null ? dYContainerConfig13.getModule() : null;
        DYContainerConfig dYContainerConfig14 = this.containerConfig;
        DynamicMtaUtil.reportAPIRender(module2, dYContainerConfig14 != null ? dYContainerConfig14.getTemplateId() : null, source, curMicroseconds2);
        DYConstants.DYLog("showViewNode render time : " + curMicroseconds2);
    }

    static /* synthetic */ void showViewNode$default(DynamicContainer dynamicContainer, ViewNode viewNode, String str, ResultEntity resultEntity, String str2, long j2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            resultEntity = null;
        }
        dynamicContainer.showViewNode(viewNode, str, resultEntity, str2, j2);
    }

    @Override // android.view.ViewGroup
    public void addView(@Nullable View child) {
        OnAddDynamicViewListener onAddDynamicViewListener;
        super.addView(child);
        if (this.onAddDynamicViewListener == null || child == null || child.getId() != R.id.dynamic_root_view || (onAddDynamicViewListener = this.onAddDynamicViewListener) == null) {
            return;
        }
        onAddDynamicViewListener.onAddView();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(@Nullable View child, @Nullable ViewGroup.LayoutParams params) {
        OnAddDynamicViewListener onAddDynamicViewListener;
        super.addView(child, params);
        if (this.onAddDynamicViewListener == null || child == null || child.getId() != R.id.dynamic_root_view || (onAddDynamicViewListener = this.onAddDynamicViewListener) == null) {
            return;
        }
        onAddDynamicViewListener.onAddView();
    }

    public final void attachConfig(@NotNull DYContainerConfig config) {
        this.containerConfig = config;
        if (config == null || !config.getUseSkeleton()) {
            checkAndPost(new Runnable() { // from class: com.jd.dynamic.apis.DynamicContainer$attachConfig$1
                {
                    DynamicContainer.this = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    DynamicContainer.this.prepareContainer();
                }
            });
            return;
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dyn_item_skeleton_layout, (ViewGroup) null);
        this.skeletonLayout = inflate;
        if (inflate != null) {
            inflate.setVisibility(8);
        }
        View view = this.skeletonLayout;
        if (view == null) {
            Intrinsics.throwNpe();
        }
        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) view.findViewById(R.id.shimmer_view_container);
        this.shimmerFrameLayout = shimmerFrameLayout;
        if (shimmerFrameLayout != null) {
            shimmerFrameLayout.setShimmer(new Shimmer.AlphaHighlightBuilder().setWidthRatio(1.5f).build());
        }
        if (this.containerConfig == null) {
            Intrinsics.throwNpe();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, DPIUtil.dip2px(r1.getSkeletonHeight()));
        layoutParams.gravity = 17;
        addView(this.skeletonLayout, layoutParams);
    }

    public final void attachListener(@NotNull IDYContainerListener r1) {
        this.containerListener = r1;
    }

    public final void bindView() {
        hideOrShowSkeletonView(false);
        DynamicTemplateEngine dynamicTemplateEngine = this.engine;
        if (dynamicTemplateEngine != null) {
            dynamicTemplateEngine.bindView(this.containerData);
        }
    }

    public final void bindViewAsync(@Nullable final IViewBindListener r8) {
        OnAddDynamicViewListener onAddDynamicViewListener;
        Object[] objArr = new Object[3];
        objArr[0] = "bindViewData";
        StringBuilder sb = new StringBuilder();
        sb.append("engine is null ");
        sb.append(this.engine == null);
        objArr[1] = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("rootView is null ");
        int i2 = R.id.dynamic_root_view;
        sb2.append(findViewById(i2) == null);
        objArr[2] = sb2.toString();
        t.e(objArr);
        hideOrShowSkeletonView(true);
        if (this.engine == null) {
            onAddDynamicViewListener = new OnAddDynamicViewListener() { // from class: com.jd.dynamic.apis.DynamicContainer$bindViewAsync$3
                {
                    DynamicContainer.this = this;
                }

                @Override // com.jd.dynamic.apis.DynamicContainer.OnAddDynamicViewListener
                public void onAddView() {
                    DynamicContainer.this.bindViewAsync(r8);
                }
            };
        } else if (findViewById(i2) != null) {
            DynamicTemplateEngine dynamicTemplateEngine = this.engine;
            if (dynamicTemplateEngine == null) {
                Intrinsics.throwNpe();
            }
            dynamicTemplateEngine.bindViewAsync(this.containerData, new IViewBindListener() { // from class: com.jd.dynamic.apis.DynamicContainer$bindViewAsync$1
                {
                    DynamicContainer.this = this;
                }

                @Override // com.jd.dynamic.apis.IViewBindListener
                public void onDynamicViewBind() {
                    BaseFrameLayout baseFrameLayout;
                    if (DynamicContainer.this.isAsyncCreateView && (baseFrameLayout = (BaseFrameLayout) DynamicContainer.this.findViewById(R.id.dynamic_root_view)) != null) {
                        baseFrameLayout.setVisibility(0);
                    }
                    DynamicContainer.this.hideOrShowSkeletonView(false);
                    IViewBindListener iViewBindListener = r8;
                    if (iViewBindListener != null) {
                        iViewBindListener.onDynamicViewBind();
                    }
                }

                @Override // com.jd.dynamic.apis.IViewBindListener
                public void onError(@NotNull DynamicException r10) {
                    DYContainerConfig dYContainerConfig;
                    DYContainerConfig dYContainerConfig2;
                    DynamicContainer.this.hideOrShowSkeletonView(false);
                    String message = r10.getMessage();
                    dYContainerConfig = DynamicContainer.this.containerConfig;
                    String templateId = dYContainerConfig != null ? dYContainerConfig.getTemplateId() : null;
                    dYContainerConfig2 = DynamicContainer.this.containerConfig;
                    String module = dYContainerConfig2 != null ? dYContainerConfig2.getModule() : null;
                    DynamicTemplateEngine dynamicTemplateEngine2 = DynamicContainer.this.engine;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND_ASYNC, message, templateId, module, R2.attr.layout_constraintHeight_default, r10, m.q(dynamicTemplateEngine2 != null ? dynamicTemplateEngine2.getZipVersion() : null, null));
                    IViewBindListener iViewBindListener = r8;
                    if (iViewBindListener != null) {
                        iViewBindListener.onError(r10);
                    }
                }
            });
            return;
        } else {
            onAddDynamicViewListener = new DynamicContainer$bindViewAsync$2(this, r8);
        }
        this.onAddDynamicViewListener = onAddDynamicViewListener;
    }

    public final boolean createView() {
        this.isAsyncCreateView = false;
        AsyncCreateViewTask asyncCreateViewTask = new AsyncCreateViewTask(this, null, null, 0L);
        DYContainerConfig[] dYContainerConfigArr = new DYContainerConfig[1];
        DYContainerConfig dYContainerConfig = this.containerConfig;
        if (dYContainerConfig == null) {
            Intrinsics.throwNpe();
        }
        dYContainerConfigArr[0] = dYContainerConfig;
        View doInBackground = asyncCreateViewTask.doInBackground(dYContainerConfigArr);
        asyncCreateViewTask.onPostExecute(doInBackground);
        return doInBackground != null;
    }

    public final void createViewAsync(@Nullable IViewCreateListener r6) {
        if (isConfigInvalid() && r6 != null) {
            r6.onError(new DynamicException("config is null"));
        }
        hideOrShowSkeletonView(true);
        this.isAsyncCreateView = true;
        new AsyncCreateViewTask(this, r6, null, 0L).execute(this.containerConfig);
    }

    public final void createViewWithNetwork(@Nullable final IViewCreateListener r8) {
        if (isConfigInvalid()) {
            if (r8 != null) {
                r8.onError(new DynamicException("config is invalid."));
                return;
            }
            return;
        }
        try {
            hideOrShowSkeletonView(true);
            System.currentTimeMillis();
            DYContainerConfig dYContainerConfig = this.containerConfig;
            String module = dYContainerConfig != null ? dYContainerConfig.getModule() : null;
            DYContainerConfig dYContainerConfig2 = this.containerConfig;
            NewDynamicFetcher.requestDynamicConfigByBizField(true, module, dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null, "", new NewDynamicFetcher.Listener2
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0035: INVOKE 
                  true
                  (r3v2 'module' java.lang.String)
                  (wrap: java.lang.String : ?: TERNARY null = ((r5v0 'dYContainerConfig2' com.jd.dynamic.apis.DYContainerConfig) != (null com.jd.dynamic.apis.DYContainerConfig)) ? (wrap: java.lang.String : 0x002a: INVOKE (r5v0 'dYContainerConfig2' com.jd.dynamic.apis.DYContainerConfig) type: VIRTUAL call: com.jd.dynamic.apis.DYContainerConfig.getTemplateId():java.lang.String A[Catch: Exception -> 0x0039, MD:():java.lang.String (m), WRAPPED]) : (null java.lang.String))
                  ("")
                  (wrap: com.jd.dynamic.base.NewDynamicFetcher$Listener2 : 0x0032: CONSTRUCTOR 
                  (r7v0 'this' com.jd.dynamic.apis.DynamicContainer A[IMMUTABLE_TYPE, THIS])
                  (r8v0 'r8' com.jd.dynamic.apis.IViewCreateListener A[DONT_INLINE])
                  (r1 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[Catch: Exception -> 0x0039, MD:(com.jd.dynamic.apis.DynamicContainer, com.jd.dynamic.apis.IViewCreateListener, long):void (m), WRAPPED] call: com.jd.dynamic.apis.DynamicContainer$createViewWithNetwork$1.<init>(com.jd.dynamic.apis.DynamicContainer, com.jd.dynamic.apis.IViewCreateListener, long):void type: CONSTRUCTOR)
                 type: STATIC call: com.jd.dynamic.base.NewDynamicFetcher.requestDynamicConfigByBizField(boolean, java.lang.String, java.lang.String, java.lang.String, com.jd.dynamic.base.NewDynamicFetcher$Listener):void A[Catch: Exception -> 0x0039, MD:(boolean, java.lang.String, java.lang.String, java.lang.String, com.jd.dynamic.base.NewDynamicFetcher$Listener):void (m), TRY_LEAVE] in method: com.jd.dynamic.apis.DynamicContainer.createViewWithNetwork(com.jd.dynamic.apis.IViewCreateListener):void, file: classes13.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                boolean r0 = r7.isConfigInvalid()
                if (r0 == 0) goto L13
                if (r8 == 0) goto L12
                com.jd.dynamic.lib.error.DynamicException r0 = new com.jd.dynamic.lib.error.DynamicException
                java.lang.String r1 = "config is invalid."
                r0.<init>(r1)
                r8.onError(r0)
            L12:
                return
            L13:
                r0 = 1
                r7.hideOrShowSkeletonView(r0)     // Catch: java.lang.Exception -> L39
                long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L39
                com.jd.dynamic.apis.DYContainerConfig r3 = r7.containerConfig     // Catch: java.lang.Exception -> L39
                r4 = 0
                if (r3 == 0) goto L25
                java.lang.String r3 = r3.getModule()     // Catch: java.lang.Exception -> L39
                goto L26
            L25:
                r3 = r4
            L26:
                com.jd.dynamic.apis.DYContainerConfig r5 = r7.containerConfig     // Catch: java.lang.Exception -> L39
                if (r5 == 0) goto L2e
                java.lang.String r4 = r5.getTemplateId()     // Catch: java.lang.Exception -> L39
            L2e:
                java.lang.String r5 = ""
                com.jd.dynamic.apis.DynamicContainer$createViewWithNetwork$1 r6 = new com.jd.dynamic.apis.DynamicContainer$createViewWithNetwork$1     // Catch: java.lang.Exception -> L39
                r6.<init>()     // Catch: java.lang.Exception -> L39
                com.jd.dynamic.base.NewDynamicFetcher.requestDynamicConfigByBizField(r0, r3, r4, r5, r6)     // Catch: java.lang.Exception -> L39
                goto L54
            L39:
                r0 = move-exception
                boolean r1 = r0 instanceof com.jd.dynamic.lib.error.DynamicException
                if (r1 == 0) goto L46
                if (r8 == 0) goto L54
                com.jd.dynamic.lib.error.DynamicException r0 = (com.jd.dynamic.lib.error.DynamicException) r0
                r8.onError(r0)
                goto L54
            L46:
                if (r8 == 0) goto L54
                com.jd.dynamic.lib.error.DynamicException r1 = new com.jd.dynamic.lib.error.DynamicException
                java.lang.String r0 = r0.getMessage()
                r1.<init>(r0)
                r8.onError(r1)
            L54:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.apis.DynamicContainer.createViewWithNetwork(com.jd.dynamic.apis.IViewCreateListener):void");
        }

        @Nullable
        public final Map<String, Object> executeEvents(@NotNull String eventIds, @Nullable String layoutId) {
            DynamicTemplateEngine dynamicTemplateEngine;
            if (!TextUtils.isEmpty(eventIds) && (dynamicTemplateEngine = this.engine) != null) {
                if (dynamicTemplateEngine == null) {
                    Intrinsics.throwNpe();
                }
                if (dynamicTemplateEngine.isAttached) {
                    DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                    if (dynamicTemplateEngine2 == null) {
                        Intrinsics.throwNpe();
                    }
                    return m.l(dynamicTemplateEngine2, eventIds, layoutId);
                }
            }
            return null;
        }

        @Nullable
        public final View findChildViewById(int r3) {
            BaseFrameLayout rootContainer;
            try {
                DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                if (dynamicTemplateEngine == null || (rootContainer = dynamicTemplateEngine.getRootContainer()) == null) {
                    return null;
                }
                return rootContainer.findViewById(r3);
            } catch (Exception unused) {
                return null;
            }
        }

        @Nullable
        /* renamed from: getData  reason: from getter */
        public final JSONObject getContainerData() {
            return this.containerData;
        }

        public final void load(@NotNull final IContainerCallback iContainerCallback) {
            if (isConfigInvalid()) {
                iContainerCallback.onError(new DynamicException("config is invalid."));
                return;
            }
            DYContainerConfig dYContainerConfig = this.containerConfig;
            String module = dYContainerConfig != null ? dYContainerConfig.getModule() : null;
            DYContainerConfig dYContainerConfig2 = this.containerConfig;
            DynamicMtaUtil.reportAPIEnter(module, dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null, "load async");
            if (this.engine != null) {
                DYContainerConfig dYContainerConfig3 = this.containerConfig;
                String templateId = dYContainerConfig3 != null ? dYContainerConfig3.getTemplateId() : null;
                DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                if (dynamicTemplateEngine == null) {
                    Intrinsics.throwNpe();
                }
                if (TextUtils.equals(templateId, dynamicTemplateEngine.bizField)) {
                    checkAndPost(new Runnable() { // from class: com.jd.dynamic.apis.DynamicContainer$load$4
                        {
                            DynamicContainer.this = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            DynamicContainer.this.setVisibility(0);
                        }
                    });
                    DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                    if (dynamicTemplateEngine2 == null) {
                        Intrinsics.throwNpe();
                    }
                    dynamicTemplateEngine2.newRefreshView(this.containerData);
                    IDYContainerListener iDYContainerListener = this.containerListener;
                    if (iDYContainerListener != null) {
                        iDYContainerListener.onRefresh();
                    }
                    iContainerCallback.onSuccess();
                    return;
                }
            }
            try {
                System.currentTimeMillis();
                DYContainerConfig dYContainerConfig4 = this.containerConfig;
                String module2 = dYContainerConfig4 != null ? dYContainerConfig4.getModule() : null;
                DYContainerConfig dYContainerConfig5 = this.containerConfig;
                NewDynamicFetcher.requestDynamicConfigByBizField(true, module2, dYContainerConfig5 != null ? dYContainerConfig5.getTemplateId() : null, "", new NewDynamicFetcher.Listener2
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0085: INVOKE 
                      true
                      (r4v2 'module2' java.lang.String)
                      (wrap: java.lang.String : ?: TERNARY null = ((r5v0 'dYContainerConfig5' com.jd.dynamic.apis.DYContainerConfig) != (null com.jd.dynamic.apis.DYContainerConfig)) ? (wrap: java.lang.String : 0x007a: INVOKE (r5v0 'dYContainerConfig5' com.jd.dynamic.apis.DYContainerConfig) type: VIRTUAL call: com.jd.dynamic.apis.DYContainerConfig.getTemplateId():java.lang.String A[Catch: Exception -> 0x0089, MD:():java.lang.String (m), WRAPPED]) : (null java.lang.String))
                      ("")
                      (wrap: com.jd.dynamic.base.NewDynamicFetcher$Listener2 : 0x0082: CONSTRUCTOR 
                      (r7v0 'this' com.jd.dynamic.apis.DynamicContainer A[IMMUTABLE_TYPE, THIS])
                      (r8v0 'iContainerCallback' com.jd.dynamic.apis.IContainerCallback A[DONT_INLINE])
                      (r2 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[Catch: Exception -> 0x0089, MD:(com.jd.dynamic.apis.DynamicContainer, com.jd.dynamic.apis.IContainerCallback, long):void (m), WRAPPED] call: com.jd.dynamic.apis.DynamicContainer$load$5.<init>(com.jd.dynamic.apis.DynamicContainer, com.jd.dynamic.apis.IContainerCallback, long):void type: CONSTRUCTOR)
                     type: STATIC call: com.jd.dynamic.base.NewDynamicFetcher.requestDynamicConfigByBizField(boolean, java.lang.String, java.lang.String, java.lang.String, com.jd.dynamic.base.NewDynamicFetcher$Listener):void A[Catch: Exception -> 0x0089, MD:(boolean, java.lang.String, java.lang.String, java.lang.String, com.jd.dynamic.base.NewDynamicFetcher$Listener):void (m), TRY_LEAVE] in method: com.jd.dynamic.apis.DynamicContainer.load(com.jd.dynamic.apis.IContainerCallback):void, file: classes13.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    this = this;
                    boolean r0 = r7.isConfigInvalid()
                    if (r0 == 0) goto L11
                    com.jd.dynamic.lib.error.DynamicException r0 = new com.jd.dynamic.lib.error.DynamicException
                    java.lang.String r1 = "config is invalid."
                    r0.<init>(r1)
                    r8.onError(r0)
                    return
                L11:
                    com.jd.dynamic.apis.DYContainerConfig r0 = r7.containerConfig
                    r1 = 0
                    if (r0 == 0) goto L1b
                    java.lang.String r0 = r0.getModule()
                    goto L1c
                L1b:
                    r0 = r1
                L1c:
                    com.jd.dynamic.apis.DYContainerConfig r2 = r7.containerConfig
                    if (r2 == 0) goto L25
                    java.lang.String r2 = r2.getTemplateId()
                    goto L26
                L25:
                    r2 = r1
                L26:
                    java.lang.String r3 = "load async"
                    com.jd.dynamic.base.DynamicMtaUtil.reportAPIEnter(r0, r2, r3)
                    com.jd.dynamic.base.DynamicTemplateEngine r0 = r7.engine
                    if (r0 == 0) goto L67
                    com.jd.dynamic.apis.DYContainerConfig r0 = r7.containerConfig
                    if (r0 == 0) goto L38
                    java.lang.String r0 = r0.getTemplateId()
                    goto L39
                L38:
                    r0 = r1
                L39:
                    com.jd.dynamic.base.DynamicTemplateEngine r2 = r7.engine
                    if (r2 != 0) goto L40
                    kotlin.jvm.internal.Intrinsics.throwNpe()
                L40:
                    java.lang.String r2 = r2.bizField
                    boolean r0 = android.text.TextUtils.equals(r0, r2)
                    if (r0 == 0) goto L67
                    com.jd.dynamic.apis.DynamicContainer$load$4 r0 = new com.jd.dynamic.apis.DynamicContainer$load$4
                    r0.<init>()
                    r7.checkAndPost(r0)
                    com.jd.dynamic.base.DynamicTemplateEngine r0 = r7.engine
                    if (r0 != 0) goto L57
                    kotlin.jvm.internal.Intrinsics.throwNpe()
                L57:
                    org.json.JSONObject r1 = r7.containerData
                    r0.newRefreshView(r1)
                    com.jd.dynamic.apis.IDYContainerListener r0 = r7.containerListener
                    if (r0 == 0) goto L63
                    r0.onRefresh()
                L63:
                    r8.onSuccess()
                    goto La0
                L67:
                    long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L89
                    r0 = 1
                    com.jd.dynamic.apis.DYContainerConfig r4 = r7.containerConfig     // Catch: java.lang.Exception -> L89
                    if (r4 == 0) goto L75
                    java.lang.String r4 = r4.getModule()     // Catch: java.lang.Exception -> L89
                    goto L76
                L75:
                    r4 = r1
                L76:
                    com.jd.dynamic.apis.DYContainerConfig r5 = r7.containerConfig     // Catch: java.lang.Exception -> L89
                    if (r5 == 0) goto L7e
                    java.lang.String r1 = r5.getTemplateId()     // Catch: java.lang.Exception -> L89
                L7e:
                    java.lang.String r5 = ""
                    com.jd.dynamic.apis.DynamicContainer$load$5 r6 = new com.jd.dynamic.apis.DynamicContainer$load$5     // Catch: java.lang.Exception -> L89
                    r6.<init>()     // Catch: java.lang.Exception -> L89
                    com.jd.dynamic.base.NewDynamicFetcher.requestDynamicConfigByBizField(r0, r4, r1, r5, r6)     // Catch: java.lang.Exception -> L89
                    goto La0
                L89:
                    r0 = move-exception
                    boolean r1 = r0 instanceof com.jd.dynamic.lib.error.DynamicException
                    if (r1 == 0) goto L94
                    com.jd.dynamic.lib.error.DynamicException r0 = (com.jd.dynamic.lib.error.DynamicException) r0
                    r8.onError(r0)
                    goto La0
                L94:
                    com.jd.dynamic.lib.error.DynamicException r1 = new com.jd.dynamic.lib.error.DynamicException
                    java.lang.String r0 = r0.getMessage()
                    r1.<init>(r0)
                    r8.onError(r1)
                La0:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.apis.DynamicContainer.load(com.jd.dynamic.apis.IContainerCallback):void");
            }

            /* JADX WARN: Code restructure failed: missing block: B:251:0x0117, code lost:
                if (r8 == null) goto L252;
             */
            /* JADX WARN: Code restructure failed: missing block: B:252:0x0119, code lost:
                kotlin.jvm.internal.Intrinsics.throwNpe();
             */
            /* JADX WARN: Code restructure failed: missing block: B:253:0x011c, code lost:
                r1 = r8.viewNode;
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, "entity!!.viewNode");
                r4 = com.jd.dynamic.base.interfaces.IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP;
                r0 = r17;
                r2 = r3;
                r3 = r8;
             */
            /* JADX WARN: Code restructure failed: missing block: B:343:0x0272, code lost:
                if (r8 == null) goto L252;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final boolean load() {
                /*
                    Method dump skipped, instructions count: 631
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.apis.DynamicContainer.load():boolean");
            }

            @Nullable
            public final Map<String, Object> onCustomEvent(@NotNull String customEvent) {
                DynamicTemplateEngine dynamicTemplateEngine;
                if (!TextUtils.isEmpty(customEvent) && (dynamicTemplateEngine = this.engine) != null) {
                    if (dynamicTemplateEngine == null) {
                        Intrinsics.throwNpe();
                    }
                    if (dynamicTemplateEngine.isAttached) {
                        DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                        if (dynamicTemplateEngine2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return dynamicTemplateEngine2.onCustomEvent(customEvent, null);
                    }
                }
                return null;
            }

            @Nullable
            public final Map<String, Object> onCustomEvent(@NotNull String customEvent, @Nullable String layoutId) {
                DynamicTemplateEngine dynamicTemplateEngine;
                if (!TextUtils.isEmpty(customEvent) && (dynamicTemplateEngine = this.engine) != null) {
                    if (dynamicTemplateEngine == null) {
                        Intrinsics.throwNpe();
                    }
                    if (dynamicTemplateEngine.isAttached) {
                        DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                        if (dynamicTemplateEngine2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return dynamicTemplateEngine2.onCustomEvent(customEvent, layoutId);
                    }
                }
                return null;
            }

            public final void onViewRecycled() {
                DynamicTemplateEngine dynamicTemplateEngine;
                CachePool cachePool;
                ArrayList<IRecycleListener> recycleListeners;
                CachePool cachePool2;
                DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                if (dynamicTemplateEngine2 != null) {
                    dynamicTemplateEngine2.releaseTimers(false);
                }
                DynamicTemplateEngine dynamicTemplateEngine3 = this.engine;
                if (!m.I((dynamicTemplateEngine3 == null || (cachePool2 = dynamicTemplateEngine3.getCachePool()) == null) ? null : cachePool2.getRecycleListeners()) || (dynamicTemplateEngine = this.engine) == null || (cachePool = dynamicTemplateEngine.getCachePool()) == null || (recycleListeners = cachePool.getRecycleListeners()) == null) {
                    return;
                }
                Iterator<T> it = recycleListeners.iterator();
                while (it.hasNext()) {
                    ((IRecycleListener) it.next()).onViewRecycled();
                }
            }

            public final boolean refresh() {
                if (isConfigInvalid()) {
                    return false;
                }
                DYContainerConfig dYContainerConfig = this.containerConfig;
                String module = dYContainerConfig != null ? dYContainerConfig.getModule() : null;
                DYContainerConfig dYContainerConfig2 = this.containerConfig;
                DynamicMtaUtil.reportAPIEnter(module, dYContainerConfig2 != null ? dYContainerConfig2.getTemplateId() : null, "refresh");
                if (this.engine != null) {
                    DYContainerConfig dYContainerConfig3 = this.containerConfig;
                    String templateId = dYContainerConfig3 != null ? dYContainerConfig3.getTemplateId() : null;
                    DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                    if (dynamicTemplateEngine == null) {
                        Intrinsics.throwNpe();
                    }
                    if (TextUtils.equals(templateId, dynamicTemplateEngine.bizField)) {
                        checkAndPost(new Runnable() { // from class: com.jd.dynamic.apis.DynamicContainer$refresh$1
                            {
                                DynamicContainer.this = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                DynamicContainer.this.setVisibility(0);
                            }
                        });
                        DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                        if (dynamicTemplateEngine2 == null) {
                            Intrinsics.throwNpe();
                        }
                        dynamicTemplateEngine2.newRefreshView(this.containerData);
                        IDYContainerListener iDYContainerListener = this.containerListener;
                        if (iDYContainerListener != null) {
                            iDYContainerListener.onRefresh();
                            return true;
                        }
                        return true;
                    }
                }
                return false;
            }

            public final void refreshDarkChange(boolean isDark) {
                DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                if (dynamicTemplateEngine != null) {
                    dynamicTemplateEngine.onDarkChange(isDark);
                }
            }

            public final void resetData() {
                JSONObject jSONObject;
                JSONObject jSONObject2;
                JSONObject jSONObject3;
                DynamicTemplateEngine dynamicTemplateEngine = this.engine;
                if (dynamicTemplateEngine != null && (jSONObject3 = dynamicTemplateEngine.currentData) != null) {
                    jSONObject3.remove("dynamic_private_data");
                }
                DynamicTemplateEngine dynamicTemplateEngine2 = this.engine;
                if (dynamicTemplateEngine2 != null && (jSONObject2 = dynamicTemplateEngine2.currentData) != null) {
                    jSONObject2.remove("dynamic_item_private_data");
                }
                DynamicTemplateEngine dynamicTemplateEngine3 = this.engine;
                if (dynamicTemplateEngine3 != null && (jSONObject = dynamicTemplateEngine3.currentData) != null) {
                    jSONObject.remove("dynamic_private_js_data");
                }
                JSONObject jSONObject4 = this.containerData;
                if (jSONObject4 != null) {
                    jSONObject4.remove("dynamic_private_data");
                }
                JSONObject jSONObject5 = this.containerData;
                if (jSONObject5 != null) {
                    jSONObject5.remove("dynamic_item_private_data");
                }
                JSONObject jSONObject6 = this.containerData;
                if (jSONObject6 != null) {
                    jSONObject6.remove("dynamic_private_js_data");
                }
            }

            public final void setData(@Nullable JSONObject data) {
                this.containerData = data;
            }

            public final void setDialog(@Nullable Dialog r1) {
                this.mDialog = r1;
            }

            public final void updateWidthAndHeight(int width, int height) {
                DynamicTemplateEngine dynamicTemplateEngine;
                DynamicTemplateEngine dynamicTemplateEngine2;
                if (width >= 0 && (dynamicTemplateEngine2 = this.engine) != null) {
                    dynamicTemplateEngine2.containerWidth = width;
                }
                if (height < 0 || (dynamicTemplateEngine = this.engine) == null) {
                    return;
                }
                dynamicTemplateEngine.containerHeight = height;
            }
        }

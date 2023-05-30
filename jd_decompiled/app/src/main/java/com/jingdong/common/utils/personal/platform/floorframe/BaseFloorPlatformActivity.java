package com.jingdong.common.utils.personal.platform.floorframe;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.common.utils.personal.extensions.ViewChildrenSequenceKt;
import com.jingdong.common.utils.personal.platform.floorframe.adapter.SimpleFloorPlatformAdapter;
import com.jingdong.common.utils.personal.platform.floorframe.impl.FloorPlatformFloorManagerProxy;
import com.jingdong.common.utils.personal.platform.floorframe.isv.ISVFloorData;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.JDLifecycleBaseActivity;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005B\u0017\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010%\u001a\u00020$\u00a2\u0006\u0004\b6\u00107J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0015\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH\u0017\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0006H\u0015\u00a2\u0006\u0004\b\u0013\u0010\bJ\u000f\u0010\u0015\u001a\u00020\u0014H&\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H&\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0019\u0010\u001a\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H&\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010\"\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u001c\u0010%\u001a\u00020$8\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0018\u0010*\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010#R\u0016\u0010-\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010#R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010.R$\u00100\u001a\u0004\u0018\u00010/8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105\u00a8\u00068"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/BaseFloorPlatformActivity;", "Lcom/jingdong/sdk/aac/model/BaseViewModel;", "VM", "Lcom/jingdong/sdk/aac/navigator/BaseNavigator;", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "Lcom/jingdong/sdk/aac/ui/JDLifecycleBaseActivity;", "", "downloadDynamicTemplate", "()V", "initFloorPlatform", "initFloorPlatformViewDefaultConfig", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "(Landroid/os/Bundle;)V", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "onDestroy", "", "setStatusBarTransparentEnable", "()Z", "setDownloadDynamicTemplateEnable", "Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;", "floorManagerProxy", "registerFloors", "(Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;)V", "result", "setUsedFloorPlatformDefaultConfig", "(Z)V", "", "moduleName", "Ljava/lang/String;", "mDownloadDynamicTemplateEnable", "Z", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "floorData", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "getFloorData", "()Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "Landroidx/recyclerview/widget/GridLayoutManager;", "mLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "mUsedFloorPlatformDefaultConfig", "mStatusBarTransparentEnable", "Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;", "Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "mFloorAdapter", "Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "getMFloorAdapter", "()Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "setMFloorAdapter", "(Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;)V", "<init>", "(Ljava/lang/String;Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public abstract class BaseFloorPlatformActivity<VM extends BaseViewModel, N extends BaseNavigator> extends JDLifecycleBaseActivity<VM, N> {
    private HashMap _$_findViewCache;
    @NotNull
    private final BaseFloorData floorData;
    private FloorPlatformFloorManagerProxy floorManagerProxy;
    @Nullable
    private SimpleFloorPlatformAdapter mFloorAdapter;
    private GridLayoutManager mLayoutManager;
    private final String moduleName;
    private boolean mUsedFloorPlatformDefaultConfig = true;
    private boolean mStatusBarTransparentEnable = setStatusBarTransparentEnable();
    private boolean mDownloadDynamicTemplateEnable = setDownloadDynamicTemplateEnable();

    public BaseFloorPlatformActivity(@NotNull String str, @NotNull BaseFloorData baseFloorData) {
        this.moduleName = str;
        this.floorData = baseFloorData;
    }

    private final void downloadDynamicTemplate() {
        IDynamicDriver driver = DynamicSdk.getDriver();
        if (driver != null) {
            driver.prepare("myjd", "");
        }
    }

    private final void initFloorPlatform() {
        FloorPlatformFloorManagerProxy floorManager = new FloorPlatformFloorManagerProxy(this.moduleName).getFloorManager();
        this.floorManagerProxy = floorManager;
        registerFloors(floorManager);
        initFloorPlatformViewDefaultConfig();
    }

    private final void initFloorPlatformViewDefaultConfig() {
        if (this.mUsedFloorPlatformDefaultConfig) {
            ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
            View childAt = viewGroup != null ? viewGroup.getChildAt(0) : null;
            if (!(childAt instanceof ViewGroup)) {
                childAt = null;
            }
            ViewGroup viewGroup2 = (ViewGroup) childAt;
            View firstChildOrNull = viewGroup2 != null ? ViewChildrenSequenceKt.firstChildOrNull(viewGroup2, new Function1<View, Boolean>() { // from class: com.jingdong.common.utils.personal.platform.floorframe.BaseFloorPlatformActivity$initFloorPlatformViewDefaultConfig$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(View view) {
                    return Boolean.valueOf(invoke2(view));
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull View view) {
                    return view instanceof RecyclerView;
                }
            }) : null;
            if (!(firstChildOrNull instanceof RecyclerView)) {
                firstChildOrNull = null;
            }
            RecyclerView recyclerView = (RecyclerView) firstChildOrNull;
            if (recyclerView != null) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (!(layoutManager instanceof GridLayoutManagerWrapper)) {
                    layoutManager = null;
                }
                GridLayoutManagerWrapper gridLayoutManagerWrapper = (GridLayoutManagerWrapper) layoutManager;
                if (gridLayoutManagerWrapper == null) {
                    gridLayoutManagerWrapper = new GridLayoutManagerWrapper(getThisActivity(), 2);
                }
                this.mLayoutManager = gridLayoutManagerWrapper;
                Activity thisActivity = getThisActivity();
                Intrinsics.checkExpressionValueIsNotNull(thisActivity, "thisActivity");
                SimpleFloorPlatformAdapter simpleFloorPlatformAdapter = new SimpleFloorPlatformAdapter(thisActivity, this.floorData, recyclerView);
                GridLayoutManager gridLayoutManager = this.mLayoutManager;
                simpleFloorPlatformAdapter.setSpanCount(gridLayoutManager != null ? gridLayoutManager.getSpanCount() : 2);
                this.mFloorAdapter = simpleFloorPlatformAdapter;
                GridLayoutManager gridLayoutManager2 = this.mLayoutManager;
                if (gridLayoutManager2 != null) {
                    gridLayoutManager2.setSpanSizeLookup(simpleFloorPlatformAdapter != null ? simpleFloorPlatformAdapter.getSpanSizeLookup() : null);
                }
                recyclerView.setLayoutManager(this.mLayoutManager);
                recyclerView.setItemAnimator(null);
                recyclerView.setAdapter(this.mFloorAdapter);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.utils.personal.platform.floorframe.BaseFloorPlatformActivity$initFloorPlatformViewDefaultConfig$$inlined$apply$lambda$1
                    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                    public void onScrolled(@NotNull RecyclerView recyclerView2, int dx, int dy) {
                        super.onScrolled(recyclerView2, dx, dy);
                        BaseFloorData floorData = BaseFloorPlatformActivity.this.getFloorData();
                        if (!(floorData instanceof ISVFloorData)) {
                            floorData = null;
                        }
                        ISVFloorData iSVFloorData = (ISVFloorData) floorData;
                        if (iSVFloorData != null) {
                            iSVFloorData.notifyOnScrollChanged(dy);
                        }
                    }
                });
            }
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final BaseFloorData getFloorData() {
        return this.floorData;
    }

    @Nullable
    protected final SimpleFloorPlatformAdapter getMFloorAdapter() {
        return this.mFloorAdapter;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    @CallSuper
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        BaseFloorData baseFloorData = this.floorData;
        if (!(baseFloorData instanceof ISVFloorData)) {
            baseFloorData = null;
        }
        ISVFloorData iSVFloorData = (ISVFloorData) baseFloorData;
        if (iSVFloorData != null) {
            iSVFloorData.notifyOnConfigurationChanged(newConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.aac.ui.JDLifecycleBaseActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        this.statusBarTransparentEnable = this.mStatusBarTransparentEnable;
        super.onCreate(savedInstanceState);
        if (this.mDownloadDynamicTemplateEnable) {
            downloadDynamicTemplate();
        }
        initFloorPlatform();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.aac.ui.JDLifecycleBaseActivity, com.jingdong.sdk.platform.lib.ui.CompactActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        SimpleFloorPlatformAdapter simpleFloorPlatformAdapter = this.mFloorAdapter;
        if (simpleFloorPlatformAdapter != null) {
            simpleFloorPlatformAdapter.onDestroy();
        }
    }

    public abstract void registerFloors(@Nullable FloorPlatformFloorManagerProxy floorManagerProxy);

    public abstract boolean setDownloadDynamicTemplateEnable();

    protected final void setMFloorAdapter(@Nullable SimpleFloorPlatformAdapter simpleFloorPlatformAdapter) {
        this.mFloorAdapter = simpleFloorPlatformAdapter;
    }

    public abstract boolean setStatusBarTransparentEnable();

    public void setUsedFloorPlatformDefaultConfig(boolean result) {
        this.mUsedFloorPlatformDefaultConfig = result;
    }
}

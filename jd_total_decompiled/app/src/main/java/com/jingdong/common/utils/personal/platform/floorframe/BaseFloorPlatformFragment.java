package com.jingdong.common.utils.personal.platform.floorframe;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.apis.IDynamicDriver;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.personal.extensions.ViewChildrenSequenceKt;
import com.jingdong.common.utils.personal.platform.floorframe.adapter.SimpleFloorPlatformAdapter;
import com.jingdong.common.utils.personal.platform.floorframe.impl.FloorPlatformFloorManagerProxy;
import com.jingdong.common.utils.personal.platform.floorframe.isv.ISVFloorData;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.ui.JDLifecycleBaseFragment;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005B\u0017\u0012\u0006\u00100\u001a\u00020/\u0012\u0006\u00105\u001a\u000204\u00a2\u0006\u0004\b:\u0010;J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0017\u00a2\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0011H&\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0016\u0010\bJ\u000f\u0010\u0018\u001a\u00020\u0017H&\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001c\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH&\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u001f\u0010 J\u0017\u0010#\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020!H\u0016\u00a2\u0006\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R$\u0010)\u001a\u0004\u0018\u00010(8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\u001c\u00105\u001a\u0002048\u0004@\u0004X\u0084\u0004\u00a2\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b9\u00103\u00a8\u0006<"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/BaseFloorPlatformFragment;", "Lcom/jingdong/sdk/aac/model/BaseViewModel;", "VM", "Lcom/jingdong/sdk/aac/navigator/BaseNavigator;", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "Lcom/jingdong/sdk/aac/ui/JDLifecycleBaseFragment;", "", "downloadDynamicTemplate", "()V", "initFloorPlatform", "initFloorPlatformViewDefaultConfig", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "(Landroid/os/Bundle;)V", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/View;", "onCreateViews", "(Landroid/view/LayoutInflater;Landroid/os/Bundle;)Landroid/view/View;", "setContentView", "()Landroid/view/View;", "initView", "", "setDownloadDynamicTemplateEnable", "()Z", "Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;", "floorManagerProxy", "registerFloors", "(Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformFloorManagerProxy;)V", "result", "setUsedFloorPlatformDefaultConfig", "(Z)V", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "Landroidx/recyclerview/widget/GridLayoutManager;", "mLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "mFloorAdapter", "Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "getMFloorAdapter", "()Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;", "setMFloorAdapter", "(Lcom/jingdong/common/utils/personal/platform/floorframe/adapter/SimpleFloorPlatformAdapter;)V", "", "moduleName", "Ljava/lang/String;", "mUsedFloorPlatformDefaultConfig", "Z", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "floorData", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "getFloorData", "()Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "mDownloadDynamicTemplateEnable", "<init>", "(Ljava/lang/String;Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public abstract class BaseFloorPlatformFragment<VM extends BaseViewModel, N extends BaseNavigator> extends JDLifecycleBaseFragment<VM, N> {
    private HashMap _$_findViewCache;
    @NotNull
    private final BaseFloorData floorData;
    @Nullable
    private SimpleFloorPlatformAdapter mFloorAdapter;
    private GridLayoutManager mLayoutManager;
    private final String moduleName;
    private boolean mUsedFloorPlatformDefaultConfig = true;
    private boolean mDownloadDynamicTemplateEnable = setDownloadDynamicTemplateEnable();

    public BaseFloorPlatformFragment(@NotNull String str, @NotNull BaseFloorData baseFloorData) {
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
        registerFloors(new FloorPlatformFloorManagerProxy(this.moduleName).getFloorManager());
        initFloorPlatformViewDefaultConfig();
    }

    private final void initFloorPlatformViewDefaultConfig() {
        if (this.mUsedFloorPlatformDefaultConfig) {
            View view = this.rootView;
            if (!(view instanceof ViewGroup)) {
                view = null;
            }
            ViewGroup viewGroup = (ViewGroup) view;
            View firstChildOrNull = viewGroup != null ? ViewChildrenSequenceKt.firstChildOrNull(viewGroup, new Function1<View, Boolean>() { // from class: com.jingdong.common.utils.personal.platform.floorframe.BaseFloorPlatformFragment$initFloorPlatformViewDefaultConfig$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(View view2) {
                    return Boolean.valueOf(invoke2(view2));
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final boolean invoke2(@NotNull View view2) {
                    return view2 instanceof RecyclerView;
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
                    gridLayoutManagerWrapper = new GridLayoutManagerWrapper(this.thisActivity, 2);
                }
                this.mLayoutManager = gridLayoutManagerWrapper;
                BaseActivity thisActivity = this.thisActivity;
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
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            View findViewById = view2.findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    protected final BaseFloorData getFloorData() {
        return this.floorData;
    }

    @Nullable
    protected final SimpleFloorPlatformAdapter getMFloorAdapter() {
        return this.mFloorAdapter;
    }

    public void initView() {
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
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

    @Override // com.jingdong.sdk.aac.ui.JDLifecycleBaseFragment, com.jingdong.sdk.platform.lib.ui.CompactFragment, com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mDownloadDynamicTemplateEnable) {
            downloadDynamicTemplate();
        }
        initFloorPlatform();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    @Nullable
    public View onCreateViews(@Nullable LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        this.rootView = setContentView();
        initView();
        return this.rootView;
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public abstract void registerFloors(@Nullable FloorPlatformFloorManagerProxy floorManagerProxy);

    @Nullable
    public abstract View setContentView();

    public abstract boolean setDownloadDynamicTemplateEnable();

    protected final void setMFloorAdapter(@Nullable SimpleFloorPlatformAdapter simpleFloorPlatformAdapter) {
        this.mFloorAdapter = simpleFloorPlatformAdapter;
    }

    public void setUsedFloorPlatformDefaultConfig(boolean result) {
        this.mUsedFloorPlatformDefaultConfig = result;
    }
}

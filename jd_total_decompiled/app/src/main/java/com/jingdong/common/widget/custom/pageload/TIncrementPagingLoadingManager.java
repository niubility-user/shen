package com.jingdong.common.widget.custom.pageload;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.listui.view.HeadTipStyle;
import com.jingdong.common.listui.view.HeadTipView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.widget.custom.CustomBasePageLoader;
import com.jingdong.common.widget.custom.CustomListFooterView;
import com.jingdong.common.widget.custom.CustomNetFailLayout;
import com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView;
import com.jingdong.common.widget.custom.CustomTopButton;
import com.jingdong.common.widget.custom.pageload.Filter;
import com.jingdong.common.widget.custom.pageload.entity.FooterEntity;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.entity.IncrementBaseTEntity;
import com.jingdong.common.widget.custom.pageload.entity.LastReadEntity;
import com.jingdong.common.widget.custom.pageload.entity.TopEntity;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes12.dex */
public class TIncrementPagingLoadingManager<E extends IncrementBaseTEntity> extends TBasePagingLoadingManagerImpl<E> {
    IShowTip iShowTip;
    TIncrementPagingLoadingManager<E>.TNextPagerLoader nextPagerLoader;

    /* loaded from: classes12.dex */
    public abstract class IData {
        public IData() {
        }

        public abstract RecyclerView.Adapter get(List<IFloorEntity> list, E e2);

        public void refreshSizeCallBack(int i2, int i3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public interface INetResult<E> {
        void error(int i2, String str);

        void start();

        void success(int i2, String str, E e2);
    }

    /* loaded from: classes12.dex */
    public interface IShowTip {
        void show(HeadTipView headTipView, int i2);
    }

    /* loaded from: classes12.dex */
    public static class LoadConfig {
        final int div;
        private final String functionId;
        private ILoadCallBack iLoadCallBack;
        int spanCount;

        /* loaded from: classes12.dex */
        public static abstract class ILoadCallBack {
            public void addExtendParam(HttpSetting httpSetting) {
            }

            public void refresh() {
            }
        }

        public LoadConfig(int i2, String str) {
            this(i2, str, null);
        }

        public LoadConfig setSpanCount(int i2) {
            this.spanCount = i2;
            return this;
        }

        public LoadConfig(int i2, String str, ILoadCallBack iLoadCallBack) {
            this.div = i2;
            this.functionId = str;
            this.iLoadCallBack = iLoadCallBack;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class TNextPagerLoader extends CustomBasePageLoader {
        private final FrameLayout content;
        int downwardNum;
        final Filter filter;
        final FooterEntity footerEntity;
        private final INetResult iNetResult;
        private final JDProgressBar jdProgressBar;
        final LoadConfig loadConfig;
        int upwardTime;
        final List<IFloorEntity> mlist = new ArrayList();
        final String DOWNLOAD = "down";
        final String UPLOAD = "up";
        private final LastReadEntity lastReadEntity = new LastReadEntity();

        public TNextPagerLoader(FrameLayout frameLayout, LoadConfig loadConfig, JDProgressBar jDProgressBar, INetResult iNetResult) {
            this.content = frameLayout;
            this.loadConfig = loadConfig;
            this.iNetResult = iNetResult;
            this.jdProgressBar = jDProgressBar;
            FooterEntity footerEntity = new FooterEntity();
            this.footerEntity = footerEntity;
            footerEntity.retryListener = new CustomListFooterView.RetryListener() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.TNextPagerLoader.1
                @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
                public void emptyRetry() {
                    TNextPagerLoader.this.showPageOne();
                }

                @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
                public void retry() {
                    TNextPagerLoader.this.showNextPage();
                }
            };
            this.filter = new Filter();
        }

        private void getData(final int i2, String str, int i3, int i4, final String str2) {
            JDProgressBar jDProgressBar = this.jdProgressBar;
            if (jDProgressBar != null) {
                jDProgressBar.setVisibility(1 == i2 ? 0 : 8);
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId(this.loadConfig.functionId);
            httpSetting.putJsonParam("page", Integer.valueOf(i2));
            httpSetting.putJsonParam(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, str);
            httpSetting.putJsonParam("upwardTime", Integer.valueOf(i3));
            httpSetting.putJsonParam("downwardNum", Integer.valueOf(i4));
            httpSetting.putJsonParam("loadDirection", str2);
            if (this.loadConfig.iLoadCallBack != null) {
                this.loadConfig.iLoadCallBack.addExtendParam(httpSetting);
            }
            httpSetting.setEffect(0);
            httpSetting.setEffectState(0);
            httpSetting.setPost(true);
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.TNextPagerLoader.2
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    final IncrementBaseTEntity incrementBaseTEntity = (IncrementBaseTEntity) JDJSON.parseObject(httpResponse.getFastJsonObject().toString(), TIncrementPagingLoadingManager.this.eClass);
                    if (TNextPagerLoader.this.content != null) {
                        TNextPagerLoader.this.content.post(new Runnable() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.TNextPagerLoader.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (TNextPagerLoader.this.jdProgressBar != null) {
                                    TNextPagerLoader.this.jdProgressBar.setVisibility(8);
                                }
                                if (TNextPagerLoader.this.iNetResult != null) {
                                    IncrementBaseTEntity incrementBaseTEntity2 = incrementBaseTEntity;
                                    if (incrementBaseTEntity2 == null || !incrementBaseTEntity2.isSuccess()) {
                                        INetResult iNetResult = TNextPagerLoader.this.iNetResult;
                                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                        iNetResult.error(i2, str2);
                                        return;
                                    }
                                    INetResult iNetResult2 = TNextPagerLoader.this.iNetResult;
                                    AnonymousClass2 anonymousClass22 = AnonymousClass2.this;
                                    iNetResult2.success(i2, str2, incrementBaseTEntity);
                                }
                            }
                        });
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    if (TNextPagerLoader.this.content != null) {
                        TNextPagerLoader.this.content.post(new Runnable() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.TNextPagerLoader.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (TNextPagerLoader.this.jdProgressBar != null) {
                                    TNextPagerLoader.this.jdProgressBar.setVisibility(8);
                                }
                                if (TNextPagerLoader.this.iNetResult != null) {
                                    INetResult iNetResult = TNextPagerLoader.this.iNetResult;
                                    AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                    iNetResult.error(i2, str2);
                                }
                            }
                        });
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }

        private void startLoad(int i2, String str, int i3, int i4, String str2) {
            INetResult iNetResult = this.iNetResult;
            if (iNetResult != null) {
                iNetResult.start();
            }
            getData(i2, str, i3, i4, str2);
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void onRefresh() {
            int i2 = this.page;
            startLoad(i2, this.offSet, this.upwardTime, this.downwardNum, i2 == 1 ? "down" : "up");
            LoadConfig loadConfig = this.loadConfig;
            if (loadConfig == null || loadConfig.iLoadCallBack == null) {
                return;
            }
            this.loadConfig.iLoadCallBack.refresh();
        }

        public void resetParams() {
            this.page = 1;
            this.offSet = "";
            this.upwardTime = 0;
            this.downwardNum = 0;
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void showNextPage() {
            startLoad(this.page, this.offSet, this.upwardTime, this.downwardNum, "down");
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void showPageOne() {
            startLoad(1, "", 0, 0, "down");
        }
    }

    public TIncrementPagingLoadingManager(Class<E> cls) {
        super(cls);
    }

    public void addScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView;
        if (onScrollListener == null || (customPageLoaderRecyclerView = this.pageLoaderRecyclerView) == null || customPageLoaderRecyclerView.getRefreshableView() == null) {
            return;
        }
        this.pageLoaderRecyclerView.getRefreshableView().addOnScrollListener(onScrollListener);
    }

    public FrameLayout build(Context context, LoadConfig loadConfig, final TIncrementPagingLoadingManager<E>.IData iData) {
        FrameLayout frameLayout = new FrameLayout(context);
        JDProgressBar jDProgressBar = new JDProgressBar(context);
        jDProgressBar.setVisibility(8);
        final CustomNetFailLayout customNetFailLayout = new CustomNetFailLayout(context);
        this.nextPagerLoader = new TNextPagerLoader(frameLayout, loadConfig, jDProgressBar, new INetResult<E>() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.1
            public RecyclerView.Adapter adapter;
            public TopEntity topEntity;

            private void setAfterDataFooter(List list, int i2) {
                if (list.size() > 0) {
                    if (TIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize() > 0) {
                        setState((short) 5);
                    } else {
                        setState((short) 2);
                    }
                } else if (TIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                    setState((short) 4);
                } else {
                    setState((short) 3);
                }
            }

            private void setState(short s) {
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TIncrementPagingLoadingManager.this.nextPagerLoader;
                if (tNextPagerLoader != null) {
                    FooterEntity footerEntity = tNextPagerLoader.footerEntity;
                    footerEntity.state = s;
                    int indexOf = tNextPagerLoader.mlist.indexOf(footerEntity);
                    RecyclerView.Adapter adapter = this.adapter;
                    if (adapter == null || indexOf <= -1) {
                        return;
                    }
                    adapter.notifyItemChanged(indexOf);
                }
            }

            @Override // com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.INetResult
            public void error(int i2, String str) {
                IShowTip iShowTip;
                CustomNetFailLayout customNetFailLayout2;
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TIncrementPagingLoadingManager.this.nextPagerLoader;
                if (tNextPagerLoader != null) {
                    tNextPagerLoader.getClass();
                    if (TextUtils.equals("down", str)) {
                        if (TIncrementPagingLoadingManager.this.isFirstPage(i2) && (customNetFailLayout2 = customNetFailLayout) != null) {
                            customNetFailLayout2.showFail(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.1.1
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader2 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                                    if (tNextPagerLoader2 != null) {
                                        tNextPagerLoader2.showPageOne();
                                    }
                                }
                            });
                        }
                        setState((short) 2);
                    } else {
                        TIncrementPagingLoadingManager tIncrementPagingLoadingManager = TIncrementPagingLoadingManager.this;
                        HeadTipView headTipView = tIncrementPagingLoadingManager.headTipView;
                        if (headTipView != null && (iShowTip = tIncrementPagingLoadingManager.iShowTip) != null) {
                            iShowTip.show(headTipView, 0);
                        }
                    }
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TIncrementPagingLoadingManager.this.pageLoaderRecyclerView;
                if (customPageLoaderRecyclerView != null) {
                    customPageLoaderRecyclerView.onRefreshComplete();
                }
            }

            @Override // com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.INetResult
            public void start() {
                setState((short) 1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager.INetResult
            public /* bridge */ /* synthetic */ void success(int i2, String str, Object obj) {
                success(i2, str, (String) ((IncrementBaseTEntity) obj));
            }

            public void success(int i2, String str, E e2) {
                IShowTip iShowTip;
                IShowTip iShowTip2;
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TIncrementPagingLoadingManager.this.nextPagerLoader;
                if (tNextPagerLoader != null) {
                    int size = tNextPagerLoader.mlist.size();
                    TIncrementPagingLoadingManager.this.nextPagerLoader.getClass();
                    if (TextUtils.equals("up", str)) {
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader2 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader2.upwardTime++;
                        tNextPagerLoader2.filter.startFilt();
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader3 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                        int indexOf = tNextPagerLoader3.mlist.indexOf(((TNextPagerLoader) tNextPagerLoader3).lastReadEntity);
                        if (indexOf > -1) {
                            TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader4 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                            tNextPagerLoader4.mlist.remove(((TNextPagerLoader) tNextPagerLoader4).lastReadEntity);
                        }
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader5 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader5.mlist.add(0, ((TNextPagerLoader) tNextPagerLoader5).lastReadEntity);
                        for (int i3 = 0; i3 < e2.list.size(); i3++) {
                            if (!TIncrementPagingLoadingManager.this.nextPagerLoader.filter.isFilted(e2.getListItem(i3))) {
                                TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(0, e2.getListItem(i3));
                            }
                        }
                        if (TIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize() <= 0) {
                            TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader6 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                            tNextPagerLoader6.mlist.remove(((TNextPagerLoader) tNextPagerLoader6).lastReadEntity);
                            if (indexOf > -1) {
                                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader7 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                                tNextPagerLoader7.mlist.add(indexOf, ((TNextPagerLoader) tNextPagerLoader7).lastReadEntity);
                            }
                        } else {
                            TopEntity topEntity = this.topEntity;
                            if (topEntity != null && !topEntity.isMove) {
                                TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.remove(topEntity);
                                TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(0, this.topEntity);
                            }
                            RecyclerView.Adapter adapter = this.adapter;
                            if (adapter != null) {
                                adapter.notifyItemRangeChanged(0, size);
                                if (TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.size() - size > 0) {
                                    this.adapter.notifyItemRangeInserted(size, TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.size() - size);
                                }
                            }
                        }
                        setAfterDataFooter(e2.list, i2);
                        TIncrementPagingLoadingManager tIncrementPagingLoadingManager = TIncrementPagingLoadingManager.this;
                        HeadTipView headTipView = tIncrementPagingLoadingManager.headTipView;
                        if (headTipView != null && (iShowTip2 = tIncrementPagingLoadingManager.iShowTip) != null) {
                            iShowTip2.show(headTipView, tIncrementPagingLoadingManager.nextPagerLoader.filter.endFiltGetAddSize());
                        }
                    } else {
                        if (TIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                            TIncrementPagingLoadingManager.this.nextPagerLoader.resetParams();
                            TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.clear();
                            TIncrementPagingLoadingManager.this.nextPagerLoader.filter.clear();
                            TopEntity topEntity2 = e2.getTopEntity();
                            this.topEntity = topEntity2;
                            if (topEntity2 != null) {
                                TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(topEntity2);
                            }
                            CustomNetFailLayout customNetFailLayout2 = customNetFailLayout;
                            if (customNetFailLayout2 != null) {
                                customNetFailLayout2.closeFail();
                            }
                        }
                        TIncrementPagingLoadingManager.this.nextPagerLoader.filter.startFilt();
                        for (int i4 = 0; i4 < e2.list.size(); i4++) {
                            if (!TIncrementPagingLoadingManager.this.nextPagerLoader.filter.isFilted(e2.getListItem(i4))) {
                                TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(e2.getListItem(i4));
                            }
                        }
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader8 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader8.mlist.remove(tNextPagerLoader8.footerEntity);
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader9 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader9.mlist.add(tNextPagerLoader9.footerEntity);
                        if (TIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                            IData iData2 = iData;
                            if (iData2 != null) {
                                RecyclerView.Adapter adapter2 = this.adapter;
                                if (adapter2 == null) {
                                    this.adapter = iData2.get(TIncrementPagingLoadingManager.this.nextPagerLoader.mlist, e2);
                                    TIncrementPagingLoadingManager.this.pageLoaderRecyclerView.getRefreshableView().setAdapter(this.adapter);
                                } else {
                                    adapter2.notifyDataSetChanged();
                                }
                            }
                            TIncrementPagingLoadingManager tIncrementPagingLoadingManager2 = TIncrementPagingLoadingManager.this;
                            HeadTipView headTipView2 = tIncrementPagingLoadingManager2.headTipView;
                            if (headTipView2 != null && (iShowTip = tIncrementPagingLoadingManager2.iShowTip) != null) {
                                iShowTip.show(headTipView2, tIncrementPagingLoadingManager2.nextPagerLoader.filter.endFiltGetAddSize());
                            }
                        } else {
                            RecyclerView.Adapter adapter3 = this.adapter;
                            if (adapter3 != null) {
                                adapter3.notifyItemChanged(size - 1);
                                this.adapter.notifyItemRangeInserted(size, TIncrementPagingLoadingManager.this.nextPagerLoader.mlist.size() - size);
                            }
                        }
                    }
                    setAfterDataFooter(e2.list, i2);
                    IData iData3 = iData;
                    if (iData3 != null) {
                        iData3.refreshSizeCallBack(e2.list.size(), TIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize());
                    }
                    TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader10 = TIncrementPagingLoadingManager.this.nextPagerLoader;
                    tNextPagerLoader10.page++;
                    tNextPagerLoader10.offSet = e2.offset;
                    tNextPagerLoader10.downwardNum += e2.bilistsize;
                    tNextPagerLoader10.checkTooLess(e2.list.size());
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TIncrementPagingLoadingManager.this.pageLoaderRecyclerView;
                if (customPageLoaderRecyclerView != null) {
                    customPageLoaderRecyclerView.onRefreshComplete();
                }
            }
        });
        View customTopButton = new CustomTopButton(context);
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = new CustomPageLoaderRecyclerView((BaseActivity) context, this.nextPagerLoader, loadConfig.div, true, customTopButton);
        this.pageLoaderRecyclerView = customPageLoaderRecyclerView;
        customPageLoaderRecyclerView.setScrollToShowNextPage(6);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f));
        layoutParams.gravity = 17;
        jDProgressBar.setLayoutParams(layoutParams);
        frameLayout.addView(this.pageLoaderRecyclerView);
        frameLayout.addView(customTopButton);
        frameLayout.addView(customNetFailLayout);
        frameLayout.addView(jDProgressBar);
        return frameLayout;
    }

    public void clickRefresh() {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView != null) {
            customPageLoaderRecyclerView.dragPullRefresh();
        }
    }

    @Override // com.jingdong.common.widget.custom.pageload.ITBasePagingLoadingManager
    public CustomBasePageLoader getNextPagerLoader() {
        return this.nextPagerLoader;
    }

    public ViewStub getTipView(Context context, IShowTip iShowTip, HeadTipStyle headTipStyle) {
        this.iShowTip = iShowTip;
        ViewStub viewStub = new ViewStub(context);
        this.headTipView = new HeadTipView(viewStub, headTipStyle);
        return viewStub;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isFirstPage(int i2) {
        return i2 == 1;
    }

    public void setExFilter(Filter.IExFilter iExFilter) {
        Filter filter;
        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = this.nextPagerLoader;
        if (tNextPagerLoader == null || (filter = tNextPagerLoader.filter) == null) {
            return;
        }
        filter.setExFilter(iExFilter);
    }

    public void setFirstItemTop(int i2) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView == null) {
            return;
        }
        customPageLoaderRecyclerView.setFirstItemTop(i2);
    }

    public void setRefreshLayout(@ColorInt int i2) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView == null) {
            return;
        }
        customPageLoaderRecyclerView.setHeaderLayout(i2);
    }
}

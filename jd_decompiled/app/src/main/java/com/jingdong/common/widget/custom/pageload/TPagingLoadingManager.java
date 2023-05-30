package com.jingdong.common.widget.custom.pageload;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.listui.view.HeadTipView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.widget.custom.CustomBasePageLoader;
import com.jingdong.common.widget.custom.CustomListFooterView;
import com.jingdong.common.widget.custom.CustomNetFailLayout;
import com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView;
import com.jingdong.common.widget.custom.CustomTopButton;
import com.jingdong.common.widget.custom.pageload.entity.FooterEntity;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.entity.IncrementBaseTEntity;
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
public class TPagingLoadingManager<E extends IncrementBaseTEntity> extends TBasePagingLoadingManagerImpl<E> {
    private IShowTip iShowTip;
    private TPagingLoadingManager<E>.TNextPagerLoader nextPagerLoader;

    /* loaded from: classes12.dex */
    public abstract class IData {
        public IData() {
        }

        public abstract RecyclerView.Adapter get(List<IFloorEntity> list, E e2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public interface INetResult<E> {
        void error(int i2);

        void start();

        void success(int i2, E e2);
    }

    /* loaded from: classes12.dex */
    public interface IShowTip {
        void show(HeadTipView headTipView, int i2);
    }

    /* loaded from: classes12.dex */
    public static class LoadConfig {
        private final int div;
        private String extendOffset;
        private final String functionId;
        private final ILoadCallBack iLoadCallBack;
        private final PullToRefreshBase.Mode mode;

        /* loaded from: classes12.dex */
        public static abstract class ILoadCallBack {
            public void addExtendParam(HttpSetting httpSetting) {
            }

            public String converJson(JDJSONObject jDJSONObject) {
                return null;
            }

            public boolean isConverJson() {
                return false;
            }

            public void refresh() {
            }
        }

        public LoadConfig(int i2, String str) {
            this(i2, str, PullToRefreshBase.Mode.PULL_FROM_START);
        }

        public LoadConfig setExtendOffset(String str) {
            this.extendOffset = str;
            return this;
        }

        public LoadConfig(int i2, String str, PullToRefreshBase.Mode mode) {
            this(i2, str, PullToRefreshBase.Mode.PULL_FROM_START, null);
        }

        public LoadConfig(int i2, String str, PullToRefreshBase.Mode mode, ILoadCallBack iLoadCallBack) {
            this.div = i2;
            this.functionId = str;
            this.mode = mode;
            this.iLoadCallBack = iLoadCallBack;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class TNextPagerLoader extends CustomBasePageLoader {
        private final FrameLayout content;
        private final FooterEntity footerEntity;
        private final INetResult iNetResult;
        private boolean isRefresh;
        private final JDProgressBar jdProgressBar;
        private final LoadConfig loadConfig;
        private List mlist = new ArrayList();
        private final int firstPage = 1;

        public TNextPagerLoader(FrameLayout frameLayout, LoadConfig loadConfig, JDProgressBar jDProgressBar, INetResult iNetResult) {
            this.content = frameLayout;
            this.loadConfig = loadConfig;
            this.iNetResult = iNetResult;
            this.jdProgressBar = jDProgressBar;
            FooterEntity footerEntity = new FooterEntity();
            this.footerEntity = footerEntity;
            footerEntity.retryListener = new CustomListFooterView.RetryListener() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.TNextPagerLoader.1
                @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
                public void emptyRetry() {
                    TNextPagerLoader.this.showPageOne();
                }

                @Override // com.jingdong.common.widget.custom.CustomListFooterView.RetryListener
                public void retry() {
                    TNextPagerLoader.this.showNextPage();
                }
            };
        }

        private void getData(String str, final int i2) {
            JDProgressBar jDProgressBar = this.jdProgressBar;
            if (jDProgressBar != null) {
                jDProgressBar.setVisibility((!isFirstPage(i2) || this.isRefresh) ? 8 : 0);
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId(this.loadConfig.functionId);
            httpSetting.putJsonParam(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, str);
            httpSetting.putJsonParam("page", Integer.valueOf(i2));
            if (this.loadConfig.iLoadCallBack != null) {
                this.loadConfig.iLoadCallBack.addExtendParam(httpSetting);
            }
            httpSetting.setPost(true);
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.TNextPagerLoader.2
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    String jdjson;
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (TNextPagerLoader.this.loadConfig != null && TNextPagerLoader.this.loadConfig.iLoadCallBack != null && TNextPagerLoader.this.loadConfig.iLoadCallBack.isConverJson()) {
                        jdjson = TNextPagerLoader.this.loadConfig.iLoadCallBack.converJson(fastJsonObject);
                    } else {
                        jdjson = fastJsonObject.toString();
                    }
                    final IncrementBaseTEntity incrementBaseTEntity = (IncrementBaseTEntity) JDJSON.parseObject(jdjson, TPagingLoadingManager.this.eClass);
                    if (TNextPagerLoader.this.content != null) {
                        TNextPagerLoader.this.content.post(new Runnable() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.TNextPagerLoader.2.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (TNextPagerLoader.this.jdProgressBar != null) {
                                    TNextPagerLoader.this.jdProgressBar.setVisibility(8);
                                }
                                if (TNextPagerLoader.this.iNetResult != null) {
                                    IncrementBaseTEntity incrementBaseTEntity2 = incrementBaseTEntity;
                                    if (incrementBaseTEntity2 == null || !incrementBaseTEntity2.isSuccess()) {
                                        TNextPagerLoader.this.iNetResult.error(i2);
                                    } else {
                                        TNextPagerLoader.this.iNetResult.success(i2, incrementBaseTEntity);
                                    }
                                }
                            }
                        });
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    if (TNextPagerLoader.this.content != null) {
                        TNextPagerLoader.this.content.post(new Runnable() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.TNextPagerLoader.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (TNextPagerLoader.this.jdProgressBar != null) {
                                    TNextPagerLoader.this.jdProgressBar.setVisibility(8);
                                }
                                if (TNextPagerLoader.this.iNetResult != null) {
                                    TNextPagerLoader.this.iNetResult.error(i2);
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

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isFirstPage(int i2) {
            return 1 == i2;
        }

        private void refresh(boolean z) {
            this.isRefresh = z;
            INetResult iNetResult = this.iNetResult;
            if (iNetResult != null) {
                iNetResult.start();
            }
            LoadConfig loadConfig = this.loadConfig;
            getData((loadConfig == null || loadConfig.extendOffset == null) ? "" : this.loadConfig.extendOffset, 1);
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void onRefresh() {
            refresh(true);
            LoadConfig loadConfig = this.loadConfig;
            if (loadConfig == null || loadConfig.iLoadCallBack == null) {
                return;
            }
            this.loadConfig.iLoadCallBack.refresh();
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void showNextPage() {
            this.isRefresh = false;
            INetResult iNetResult = this.iNetResult;
            if (iNetResult != null) {
                iNetResult.start();
            }
            getData(this.offSet, this.page);
        }

        @Override // com.jingdong.common.widget.custom.CustomBasePageLoader
        public void showPageOne() {
            refresh(false);
        }
    }

    public TPagingLoadingManager(Class<E> cls) {
        super(cls);
    }

    public FrameLayout build(Context context, LoadConfig loadConfig, final TPagingLoadingManager<E>.IData iData) {
        FrameLayout frameLayout = new FrameLayout(context);
        JDProgressBar jDProgressBar = new JDProgressBar(context);
        jDProgressBar.setVisibility(8);
        final CustomNetFailLayout customNetFailLayout = new CustomNetFailLayout(context);
        this.nextPagerLoader = new TNextPagerLoader(frameLayout, loadConfig, jDProgressBar, new INetResult<E>() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.1
            public RecyclerView.Adapter adapter;

            private void setState(short s) {
                if (TPagingLoadingManager.this.nextPagerLoader != null) {
                    TPagingLoadingManager.this.nextPagerLoader.footerEntity.state = s;
                    int indexOf = TPagingLoadingManager.this.nextPagerLoader.mlist.indexOf(TPagingLoadingManager.this.nextPagerLoader.footerEntity);
                    RecyclerView.Adapter adapter = this.adapter;
                    if (adapter == null || indexOf <= -1) {
                        return;
                    }
                    adapter.notifyItemChanged(indexOf);
                }
            }

            @Override // com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.INetResult
            public void error(int i2) {
                if (TPagingLoadingManager.this.nextPagerLoader != null && TPagingLoadingManager.this.nextPagerLoader.isFirstPage(i2)) {
                    CustomNetFailLayout customNetFailLayout2 = customNetFailLayout;
                    if (customNetFailLayout2 != null) {
                        customNetFailLayout2.showFail(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view) {
                                if (TPagingLoadingManager.this.nextPagerLoader != null) {
                                    TPagingLoadingManager.this.nextPagerLoader.showPageOne();
                                }
                            }
                        });
                    }
                } else {
                    setState((short) 2);
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TPagingLoadingManager.this.pageLoaderRecyclerView;
                if (customPageLoaderRecyclerView != null) {
                    customPageLoaderRecyclerView.onRefreshComplete();
                }
            }

            @Override // com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.INetResult
            public void start() {
                setState((short) 1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.jingdong.common.widget.custom.pageload.TPagingLoadingManager.INetResult
            public /* bridge */ /* synthetic */ void success(int i2, Object obj) {
                success(i2, (int) ((IncrementBaseTEntity) obj));
            }

            public void success(int i2, E e2) {
                if (TPagingLoadingManager.this.nextPagerLoader != null) {
                    int size = TPagingLoadingManager.this.nextPagerLoader.mlist.size();
                    if (TPagingLoadingManager.this.nextPagerLoader.isFirstPage(i2)) {
                        TPagingLoadingManager.this.nextPagerLoader.page = 1;
                        TPagingLoadingManager.this.nextPagerLoader.mlist.clear();
                        CustomNetFailLayout customNetFailLayout2 = customNetFailLayout;
                        if (customNetFailLayout2 != null) {
                            customNetFailLayout2.closeFail();
                        }
                    }
                    TPagingLoadingManager.this.nextPagerLoader.mlist.addAll(e2.list);
                    TPagingLoadingManager.this.nextPagerLoader.mlist.remove(TPagingLoadingManager.this.nextPagerLoader.footerEntity);
                    TPagingLoadingManager.this.nextPagerLoader.mlist.add(TPagingLoadingManager.this.nextPagerLoader.footerEntity);
                    if (e2.list.size() <= 0) {
                        if (TPagingLoadingManager.this.nextPagerLoader.isFirstPage(i2)) {
                            setState((short) 4);
                        } else {
                            setState((short) 3);
                        }
                    } else {
                        setState((short) 5);
                    }
                    if (TPagingLoadingManager.this.nextPagerLoader.isFirstPage(i2)) {
                        IData iData2 = iData;
                        if (iData2 != null) {
                            RecyclerView.Adapter adapter = this.adapter;
                            if (adapter == null) {
                                this.adapter = iData2.get(TPagingLoadingManager.this.nextPagerLoader.mlist, e2);
                                TPagingLoadingManager.this.pageLoaderRecyclerView.getRefreshableView().setAdapter(this.adapter);
                            } else {
                                adapter.notifyDataSetChanged();
                            }
                        }
                        if (TPagingLoadingManager.this.nextPagerLoader.isRefresh) {
                            TPagingLoadingManager tPagingLoadingManager = TPagingLoadingManager.this;
                            if (tPagingLoadingManager.headTipView != null && tPagingLoadingManager.iShowTip != null) {
                                IShowTip iShowTip = TPagingLoadingManager.this.iShowTip;
                                TPagingLoadingManager tPagingLoadingManager2 = TPagingLoadingManager.this;
                                iShowTip.show(tPagingLoadingManager2.headTipView, tPagingLoadingManager2.nextPagerLoader.mlist.size() - size);
                            }
                        }
                    } else {
                        RecyclerView.Adapter adapter2 = this.adapter;
                        if (adapter2 != null) {
                            adapter2.notifyItemChanged(size - 1);
                            this.adapter.notifyItemRangeInserted(size, TPagingLoadingManager.this.nextPagerLoader.mlist.size() - size);
                        }
                    }
                    TPagingLoadingManager.this.nextPagerLoader.page++;
                    TPagingLoadingManager.this.nextPagerLoader.offSet = e2.offset;
                    TPagingLoadingManager.this.nextPagerLoader.checkTooLess(e2.list.size());
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TPagingLoadingManager.this.pageLoaderRecyclerView;
                if (customPageLoaderRecyclerView != null) {
                    customPageLoaderRecyclerView.onRefreshComplete();
                }
            }
        });
        View customTopButton = new CustomTopButton(context);
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = new CustomPageLoaderRecyclerView((BaseActivity) context, this.nextPagerLoader, loadConfig.div, true, customTopButton);
        this.pageLoaderRecyclerView = customPageLoaderRecyclerView;
        customPageLoaderRecyclerView.setMode(loadConfig.mode);
        this.pageLoaderRecyclerView.setScrollToShowNextPage(6);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f));
        layoutParams.gravity = 17;
        jDProgressBar.setLayoutParams(layoutParams);
        frameLayout.addView(this.pageLoaderRecyclerView);
        frameLayout.addView(customTopButton);
        frameLayout.addView(customNetFailLayout);
        frameLayout.addView(jDProgressBar);
        return frameLayout;
    }

    @Override // com.jingdong.common.widget.custom.pageload.ITBasePagingLoadingManager
    public CustomBasePageLoader getNextPagerLoader() {
        return this.nextPagerLoader;
    }

    public ViewStub getTipView(Context context, IShowTip iShowTip) {
        this.iShowTip = iShowTip;
        ViewStub viewStub = new ViewStub(context);
        this.headTipView = new HeadTipView(viewStub);
        return viewStub;
    }

    public void setScrollToShowTopPosition(int i2) {
        CustomPageLoaderRecyclerView customPageLoaderRecyclerView = this.pageLoaderRecyclerView;
        if (customPageLoaderRecyclerView != null) {
            customPageLoaderRecyclerView.setScrollToShowTopPosition(i2);
        }
    }
}

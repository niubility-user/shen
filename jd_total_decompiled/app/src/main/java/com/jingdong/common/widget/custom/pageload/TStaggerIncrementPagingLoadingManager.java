package com.jingdong.common.widget.custom.pageload;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.listui.view.HeadTipView;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.common.widget.custom.CustomNetFailLayout;
import com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView;
import com.jingdong.common.widget.custom.CustomTopButton;
import com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager;
import com.jingdong.common.widget.custom.pageload.entity.FooterEntity;
import com.jingdong.common.widget.custom.pageload.entity.IncrementBaseTEntity;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes12.dex */
public class TStaggerIncrementPagingLoadingManager<E extends IncrementBaseTEntity> extends TIncrementPagingLoadingManager<E> {
    public TStaggerIncrementPagingLoadingManager(Class cls) {
        super(cls);
    }

    @Override // com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager
    public FrameLayout build(Context context, TIncrementPagingLoadingManager.LoadConfig loadConfig, final TIncrementPagingLoadingManager<E>.IData iData) {
        FrameLayout frameLayout = new FrameLayout(context);
        JDProgressBar jDProgressBar = new JDProgressBar(context);
        jDProgressBar.setVisibility(8);
        final CustomNetFailLayout customNetFailLayout = new CustomNetFailLayout(context);
        this.nextPagerLoader = new TIncrementPagingLoadingManager.TNextPagerLoader(frameLayout, loadConfig, jDProgressBar, new TIncrementPagingLoadingManager.INetResult<E>() { // from class: com.jingdong.common.widget.custom.pageload.TStaggerIncrementPagingLoadingManager.1
            public RecyclerView.Adapter adapter;

            private void setAfterDataFooter(List list, int i2) {
                if (list.size() > 0) {
                    if (TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize() > 0) {
                        setState((short) 5);
                    } else {
                        setState((short) 6);
                    }
                } else if (TStaggerIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                    setState((short) 4);
                } else {
                    setState((short) 3);
                }
            }

            private void setState(short s) {
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
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
                CustomNetFailLayout customNetFailLayout2;
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                if (tNextPagerLoader != null) {
                    tNextPagerLoader.getClass();
                    if (TextUtils.equals("down", str)) {
                        if (TStaggerIncrementPagingLoadingManager.this.isFirstPage(i2) && (customNetFailLayout2 = customNetFailLayout) != null) {
                            customNetFailLayout2.showFail(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.pageload.TStaggerIncrementPagingLoadingManager.1.1
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader2 = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                                    if (tNextPagerLoader2 != null) {
                                        tNextPagerLoader2.showPageOne();
                                    }
                                }
                            });
                        }
                        setState((short) 2);
                    } else {
                        TStaggerIncrementPagingLoadingManager tStaggerIncrementPagingLoadingManager = TStaggerIncrementPagingLoadingManager.this;
                        if (tStaggerIncrementPagingLoadingManager.headTipView != null && tStaggerIncrementPagingLoadingManager.iShowTip != null && !tStaggerIncrementPagingLoadingManager.isFirstPage(i2)) {
                            TStaggerIncrementPagingLoadingManager tStaggerIncrementPagingLoadingManager2 = TStaggerIncrementPagingLoadingManager.this;
                            tStaggerIncrementPagingLoadingManager2.iShowTip.show(tStaggerIncrementPagingLoadingManager2.headTipView, 0);
                        }
                    }
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TStaggerIncrementPagingLoadingManager.this.pageLoaderRecyclerView;
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
                TIncrementPagingLoadingManager.IShowTip iShowTip;
                TIncrementPagingLoadingManager.IShowTip iShowTip2;
                RecyclerView.Adapter adapter;
                TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                if (tNextPagerLoader != null) {
                    int size = tNextPagerLoader.mlist.size();
                    TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.getClass();
                    if (TextUtils.equals("up", str)) {
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader2 = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader2.upwardTime++;
                        tNextPagerLoader2.filter.startFilt();
                        for (int i3 = 0; i3 < e2.list.size(); i3++) {
                            if (!TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.isFilted(e2.getListItem(i3))) {
                                TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(0, e2.getListItem(i3));
                            }
                        }
                        if (TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize() > 0 && (adapter = this.adapter) != null) {
                            adapter.notifyItemRangeChanged(0, TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist.size());
                        }
                        setAfterDataFooter(e2.list, i2);
                        TStaggerIncrementPagingLoadingManager tStaggerIncrementPagingLoadingManager = TStaggerIncrementPagingLoadingManager.this;
                        HeadTipView headTipView = tStaggerIncrementPagingLoadingManager.headTipView;
                        if (headTipView != null && (iShowTip2 = tStaggerIncrementPagingLoadingManager.iShowTip) != null) {
                            iShowTip2.show(headTipView, tStaggerIncrementPagingLoadingManager.nextPagerLoader.filter.endFiltGetAddSize());
                        }
                    } else {
                        if (TStaggerIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                            TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.resetParams();
                            TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist.clear();
                            TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.clear();
                            CustomNetFailLayout customNetFailLayout2 = customNetFailLayout;
                            if (customNetFailLayout2 != null) {
                                customNetFailLayout2.closeFail();
                            }
                        }
                        TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.startFilt();
                        for (int i4 = 0; i4 < e2.list.size(); i4++) {
                            if (!TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.isFilted(e2.getListItem(i4))) {
                                TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist.add(e2.getListItem(i4));
                            }
                        }
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader3 = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader3.mlist.remove(tNextPagerLoader3.footerEntity);
                        TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader4 = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                        tNextPagerLoader4.mlist.add(tNextPagerLoader4.footerEntity);
                        if (TStaggerIncrementPagingLoadingManager.this.isFirstPage(i2)) {
                            TIncrementPagingLoadingManager.IData iData2 = iData;
                            if (iData2 != null) {
                                RecyclerView.Adapter adapter2 = this.adapter;
                                if (adapter2 == null) {
                                    this.adapter = iData2.get(TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist, e2);
                                    TStaggerIncrementPagingLoadingManager.this.pageLoaderRecyclerView.getRefreshableView().setAdapter(this.adapter);
                                } else {
                                    adapter2.notifyDataSetChanged();
                                }
                            }
                            TStaggerIncrementPagingLoadingManager tStaggerIncrementPagingLoadingManager2 = TStaggerIncrementPagingLoadingManager.this;
                            HeadTipView headTipView2 = tStaggerIncrementPagingLoadingManager2.headTipView;
                            if (headTipView2 != null && (iShowTip = tStaggerIncrementPagingLoadingManager2.iShowTip) != null) {
                                iShowTip.show(headTipView2, tStaggerIncrementPagingLoadingManager2.nextPagerLoader.filter.endFiltGetAddSize());
                            }
                        } else if (this.adapter != null && TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize() > 0) {
                            this.adapter.notifyItemChanged(size - 1);
                            this.adapter.notifyItemRangeInserted(size, TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.mlist.size() - size);
                        }
                    }
                    setAfterDataFooter(e2.list, i2);
                    TIncrementPagingLoadingManager.IData iData3 = iData;
                    if (iData3 != null) {
                        iData3.refreshSizeCallBack(e2.list.size(), TStaggerIncrementPagingLoadingManager.this.nextPagerLoader.filter.endFiltGetAddSize());
                    }
                    TIncrementPagingLoadingManager<E>.TNextPagerLoader tNextPagerLoader5 = TStaggerIncrementPagingLoadingManager.this.nextPagerLoader;
                    tNextPagerLoader5.page++;
                    tNextPagerLoader5.offSet = e2.offset;
                    tNextPagerLoader5.downwardNum += e2.bilistsize;
                    tNextPagerLoader5.checkTooLess(e2.list.size());
                }
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = TStaggerIncrementPagingLoadingManager.this.pageLoaderRecyclerView;
                if (customPageLoaderRecyclerView != null) {
                    customPageLoaderRecyclerView.onRefreshComplete();
                }
            }
        });
        View customTopButton = new CustomTopButton(context);
        CustomStaggerRecyclerView customStaggerRecyclerView = new CustomStaggerRecyclerView((BaseActivity) context, this.nextPagerLoader, loadConfig, true, customTopButton);
        this.pageLoaderRecyclerView = customStaggerRecyclerView;
        customStaggerRecyclerView.setScrollToShowNextPage(6);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(34.0f), DPIUtil.dip2px(34.0f));
        layoutParams.gravity = 17;
        jDProgressBar.setLayoutParams(layoutParams);
        frameLayout.addView(this.pageLoaderRecyclerView);
        frameLayout.addView(customTopButton);
        frameLayout.addView(customNetFailLayout);
        frameLayout.addView(jDProgressBar);
        return frameLayout;
    }
}

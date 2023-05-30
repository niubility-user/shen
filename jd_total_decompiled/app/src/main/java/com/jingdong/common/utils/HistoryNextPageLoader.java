package com.jingdong.common.utils;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class HistoryNextPageLoader implements HttpGroup.OnAllListener {
    private MySimpleAdapter adapter;
    private String functionId;
    protected HttpGroup httpGroup;
    private AbsListView listView;
    private boolean loadedLastPage;
    private boolean loadedShow;
    private boolean loading;
    private View loadingView;
    private IMyActivity myActivity;
    private ArrayList<?> nextItemList;
    protected Integer pageNum;
    protected String pageNumParamKey;
    protected Integer pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    private ArrayList<Object> showItemList;

    /* loaded from: classes6.dex */
    private abstract class OnScrollLastListener implements AbsListView.OnScrollListener {
        private int firstVisibleItem;
        private int totalItemCount;
        private int visibleItemCount;

        private OnScrollLastListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            this.firstVisibleItem = i2;
            this.visibleItemCount = i3;
            this.totalItemCount = i4;
        }

        public abstract void onScrollLast();

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 0 && this.firstVisibleItem + this.visibleItemCount == this.totalItemCount) {
                onScrollLast();
            }
        }
    }

    public HistoryNextPageLoader(IMyActivity iMyActivity, AbsListView absListView, View view, String str) {
        this.showItemList = new ArrayList<>();
        this.loading = false;
        this.nextItemList = null;
        this.loadedShow = false;
        this.loadedLastPage = false;
        this.params = new JSONObject();
        this.pageNumParamKey = "page";
        this.pageSizeParamKey = "pagesize";
        this.pageNum = 1;
        this.pageSize = 10;
        this.myActivity = iMyActivity;
        this.httpGroup = iMyActivity.getHttpGroupaAsynPool();
        this.listView = absListView;
        this.loadingView = view;
        this.functionId = str;
    }

    private void applyLoadedShow() {
        this.loadedShow = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLoadedLastPage() {
        return this.loadedLastPage;
    }

    private boolean loadedShow() {
        if (this.loadedShow) {
            this.loadedShow = false;
            return true;
        }
        return false;
    }

    private void loading() {
        try {
            this.params.put("wareId", "107225,104089,117884,110277,104087,165369,163395,261281,253234,311451");
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v("NextPageLoader", "JSONException -->> ", e2);
            }
        }
        this.httpGroup.add(this.functionId, this.params, this);
    }

    private synchronized boolean loadingLock() {
        if (this.loading) {
            return false;
        }
        this.loading = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void loadingUnLock() {
        this.loading = false;
    }

    private void showNextPage(ArrayList<?> arrayList) {
        this.showItemList.addAll(arrayList);
        if (this.showItemList.size() < 1 && (this.listView instanceof ListView)) {
            final TextView textView = new TextView(this.myActivity.getThisActivity());
            textView.setGravity(17);
            textView.setText(com.jingdong.jdsdk.res.StringUtil.no_data);
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.utils.HistoryNextPageLoader.1
                @Override // java.lang.Runnable
                public void run() {
                    ((ListView) HistoryNextPageLoader.this.listView).addHeaderView(textView);
                }
            });
        }
        if (arrayList.size() < this.pageSize.intValue()) {
            this.loadedLastPage = true;
            this.listView.setOnScrollListener(null);
        } else {
            this.pageNum = Integer.valueOf(this.pageNum.intValue() + 1);
            loading();
        }
        if (this.adapter == null) {
            this.adapter = createAdapter(this.myActivity, this.showItemList);
            this.listView.setOnScrollListener(new OnScrollLastListener() { // from class: com.jingdong.common.utils.HistoryNextPageLoader.2
                @Override // com.jingdong.common.utils.HistoryNextPageLoader.OnScrollLastListener
                public void onScrollLast() {
                    if (HistoryNextPageLoader.this.isLoadedLastPage()) {
                        return;
                    }
                    HistoryNextPageLoader.this.tryShowNextPage();
                }
            });
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.utils.HistoryNextPageLoader.3
                @Override // java.lang.Runnable
                public void run() {
                    HistoryNextPageLoader.this.listView.setAdapter((ListAdapter) HistoryNextPageLoader.this.adapter);
                    HistoryNextPageLoader.this.loadingUnLock();
                }
            });
        } else {
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.utils.HistoryNextPageLoader.4
                @Override // java.lang.Runnable
                public void run() {
                    HistoryNextPageLoader.this.adapter.notifyDataSetChanged();
                    HistoryNextPageLoader.this.loadingUnLock();
                }
            });
        }
        if (isLoadedLastPage()) {
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.utils.HistoryNextPageLoader.5
                @Override // java.lang.Runnable
                public void run() {
                    if (HistoryNextPageLoader.this.listView instanceof ListView) {
                        if (OKLog.D) {
                            OKLog.d("tag", "+++++++++++++++++++++[removeFooterView");
                        }
                        ((ListView) HistoryNextPageLoader.this.listView).removeFooterView(HistoryNextPageLoader.this.loadingView);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryShowNextPage() {
        ArrayList<?> arrayList = this.nextItemList;
        if (arrayList == null) {
            if (!loadingLock()) {
                applyLoadedShow();
                return;
            } else {
                loading();
                return;
            }
        }
        showNextPage(arrayList);
    }

    protected abstract MySimpleAdapter createAdapter(IMyActivity iMyActivity, ArrayList<?> arrayList);

    public JSONObject getParams() {
        return this.params;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        this.pageNum.intValue();
        ArrayList<?> list = toList(httpResponse);
        if (list == null) {
            showError();
            return;
        }
        this.nextItemList = list;
        if (loadedShow()) {
            showNextPage(list);
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        showError();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
    public void onProgress(int i2, int i3) {
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
    }

    public void setPageNumParamKey(String str) {
        this.pageNumParamKey = str;
    }

    public void setPageSize(int i2) {
        this.pageSize = Integer.valueOf(i2);
    }

    public void setPageSizeParamKey(String str) {
        this.pageSizeParamKey = str;
    }

    protected abstract void showError();

    public void showPageOne() {
        if (this.showItemList.size() < 1) {
            applyLoadedShow();
            tryShowNextPage();
        }
    }

    protected abstract ArrayList<?> toList(HttpResponse httpResponse);

    public HistoryNextPageLoader(IMyActivity iMyActivity, AbsListView absListView, View view, String str, JSONObject jSONObject) {
        this(iMyActivity, absListView, view, str);
        this.params = jSONObject;
    }
}

package com.jingdong.common.utils;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.jingdong.app.util.image.JDPauseOnScrollListener;
import com.jingdong.common.entity.Product;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class FastJsonNextPageLoader4SearchShopList implements HttpGroup.OnAllListener, IDestroyListener {
    private static final String TAG = "NextPageLoader4SearchShopList";
    private final Boolean TRUE;
    private MySimpleAdapter adapter;
    protected AdapterView adapterView;
    private boolean firstLoad;
    protected String functionId;
    private Handler handler;
    private boolean hasLoadFirstData;
    private boolean hasNotify;
    private String host;
    protected HttpGroup httpGroup;
    protected boolean httpNotifyUser;
    private boolean isEffect;
    private boolean isFinishing;
    private boolean isFling;
    private boolean isHolding;
    private boolean isLoading;
    private boolean isNetErrorAndNoData;
    protected boolean isPaging;
    private boolean isPreloading;
    private boolean isSencondDataStrucUsed;
    private boolean isUseCache;
    private boolean isUseSecondDataStrucFlag;
    private boolean isWifi;
    private Map<String, Object> keyParmas;
    private boolean loadedLastPage;
    private HashMap<Integer, Boolean> loadedMap;
    private boolean loadedShow;
    private View loadingView;
    private int mCurrPos;
    private IMyActivity myActivity;
    private ArrayList<?> nextItemList;
    private OnScrollLastListener onScrollLastListener;
    protected Integer pageNum;
    protected String pageNumParamKey;
    protected Integer pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    int prePageNum;
    private MySimpleAdapter secondAdapter;
    protected ArrayList<Object> secondDataStrucShowItemList;
    private ArrayList<?> secondNextItemList;
    protected ArrayList<Object> showItemList;

    /* loaded from: classes6.dex */
    private class BaseListener implements AbsListView.OnScrollListener {
        private BaseListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            FastJsonNextPageLoader4SearchShopList.this.onScroll(absListView, i2, i3, i4);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            FastJsonNextPageLoader4SearchShopList.this.onScrollStateChanged(absListView, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class GalleryListener implements AdapterView.OnItemSelectedListener {
        GalleryListener() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
            if (FastJsonNextPageLoader4SearchShopList.this.showItemList.size() - 1 != i2 || FastJsonNextPageLoader4SearchShopList.this.isFinishing || FastJsonNextPageLoader4SearchShopList.this.isLoadedLastPage()) {
                return;
            }
            FastJsonNextPageLoader4SearchShopList.this.tryShowNextPage();
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* loaded from: classes6.dex */
    public interface ModifyDataRunnable {
        void modifyData(ArrayList<Object> arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public abstract class OnScrollLastListener extends BaseListener {
        private int firstVisibleItem;
        private int totalItemCount;
        private int visibleItemCount;

        private OnScrollLastListener() {
            super();
        }

        public void checkLast() {
            if (this.firstVisibleItem + this.visibleItemCount == this.totalItemCount) {
                onScrollLast();
            }
        }

        @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.BaseListener, android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            this.firstVisibleItem = i2;
            this.visibleItemCount = i3;
            this.totalItemCount = i4;
            FastJsonNextPageLoader4SearchShopList.this.setSelection(i2);
            FastJsonNextPageLoader4SearchShopList.this.mCurrPos = (i3 / 2) + i2;
            if (absListView.getAdapter() != null && ((ListAdapter) absListView.getAdapter()).getCount() > 0) {
                View childAt = absListView.getChildAt(0);
                FastJsonNextPageLoader4SearchShopList.this.onFirstVisible(absListView.getFirstVisiblePosition(), childAt == null ? 0 : childAt.getLeft(), childAt == null ? 0 : childAt.getTop(), childAt == null ? 0 : childAt.getRight(), childAt == null ? 0 : childAt.getBottom());
            }
            super.onScroll(absListView, i2, i3, i4);
        }

        public abstract void onScrollFling();

        public abstract void onScrollIdle();

        public abstract void onScrollLast();

        public abstract void onScrollStart();

        @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.BaseListener, android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 0) {
                onScrollIdle();
            } else if (i2 == 1) {
                onScrollStart();
            } else if (i2 == 2) {
                onScrollFling();
            }
            super.onScrollStateChanged(absListView, i2);
        }
    }

    public FastJsonNextPageLoader4SearchShopList(IMyActivity iMyActivity, HttpGroup httpGroup, AdapterView adapterView, View view, String str) {
        this.hasLoadFirstData = false;
        this.showItemList = new ArrayList<>();
        this.secondDataStrucShowItemList = new ArrayList<>();
        this.isUseSecondDataStrucFlag = false;
        this.isSencondDataStrucUsed = false;
        this.isLoading = false;
        this.nextItemList = null;
        this.secondNextItemList = null;
        this.loadedShow = false;
        this.loadedLastPage = false;
        this.firstLoad = true;
        this.isEffect = true;
        this.isHolding = false;
        this.isFling = false;
        this.isPaging = true;
        this.pageNumParamKey = "pageIdx";
        this.pageSizeParamKey = "pageSize";
        this.pageNum = 0;
        this.isWifi = false;
        this.pageSize = 10;
        this.isWifi = NetUtils.isWifi();
        this.pageSize = 10;
        this.httpNotifyUser = false;
        this.isUseCache = false;
        this.prePageNum = -1;
        this.loadedMap = new HashMap<>();
        this.TRUE = new Boolean(true);
        this.myActivity = iMyActivity;
        this.handler = iMyActivity.getHandler();
        iMyActivity.addDestroyListener(this);
        this.httpGroup = httpGroup;
        this.adapterView = adapterView;
        this.loadingView = view;
        this.functionId = str;
        this.isPreloading = true;
    }

    private void applyLoadedShow() {
        this.loadedShow = true;
    }

    private void doAdapter() {
        if (OKLog.D) {
            OKLog.d(TAG, "doAdapter() -->> ");
        }
        this.onScrollLastListener = new OnScrollLastListener() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.3
            @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.OnScrollLastListener
            public void onScrollFling() {
                FastJsonNextPageLoader4SearchShopList.this.isFling = true;
            }

            @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.OnScrollLastListener
            public void onScrollIdle() {
                FastJsonNextPageLoader4SearchShopList.this.isHolding = false;
                FastJsonNextPageLoader4SearchShopList.this.isFling = false;
                if (FastJsonNextPageLoader4SearchShopList.this.isFinishing) {
                    return;
                }
                if (FastJsonNextPageLoader4SearchShopList.this.hasNotify) {
                    FastJsonNextPageLoader4SearchShopList.this.hasNotify = false;
                    if (FastJsonNextPageLoader4SearchShopList.this.isSencondDataStrucUsed()) {
                        if (FastJsonNextPageLoader4SearchShopList.this.secondAdapter != null) {
                            FastJsonNextPageLoader4SearchShopList.this.secondAdapter.notifyDataSetChanged();
                        }
                    } else if (FastJsonNextPageLoader4SearchShopList.this.adapter != null) {
                        FastJsonNextPageLoader4SearchShopList.this.adapter.notifyDataSetChanged();
                    }
                }
                checkLast();
            }

            @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.OnScrollLastListener
            public void onScrollLast() {
                if (FastJsonNextPageLoader4SearchShopList.this.isFinishing || FastJsonNextPageLoader4SearchShopList.this.isLoadedLastPage()) {
                    return;
                }
                FastJsonNextPageLoader4SearchShopList.this.tryShowNextPage();
            }

            @Override // com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.OnScrollLastListener
            public void onScrollStart() {
                if (FastJsonNextPageLoader4SearchShopList.this.isLoadedLastPage() || FastJsonNextPageLoader4SearchShopList.this.isWifi || FastJsonNextPageLoader4SearchShopList.this.hasLoadFirstData) {
                    return;
                }
                FastJsonNextPageLoader4SearchShopList.this.hasLoadFirstData = true;
                FastJsonNextPageLoader4SearchShopList.this.tryShowNextPage();
            }
        };
        this.adapterView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action != 0) {
                    if (action == 1 && !FastJsonNextPageLoader4SearchShopList.this.isFling) {
                        FastJsonNextPageLoader4SearchShopList.this.onScrollLastListener.onScrollIdle();
                        return false;
                    }
                    return false;
                }
                FastJsonNextPageLoader4SearchShopList.this.isHolding = true;
                return false;
            }
        });
        AdapterView adapterView = this.adapterView;
        if (adapterView instanceof ListView) {
            if (this.adapter != null) {
                if (isSencondDataStrucUsed()) {
                    this.adapterView.setAdapter(this.secondAdapter.getHeaderViewListAdapter());
                } else {
                    this.adapterView.setAdapter(this.adapter.getHeaderViewListAdapter());
                }
            } else if (isSencondDataStrucUsed()) {
                this.adapterView.setAdapter(this.secondAdapter);
            } else {
                this.adapterView.setAdapter(this.adapter);
            }
            ((ListView) this.adapterView).setOnScrollListener(new JDPauseOnScrollListener(true, true, this.onScrollLastListener));
        } else if (adapterView instanceof GridView) {
            adapterView.setAdapter(this.adapter);
            ((GridView) this.adapterView).setOnScrollListener(this.onScrollLastListener);
        } else if (adapterView instanceof Gallery) {
            adapterView.setAdapter(this.adapter);
            ((Gallery) this.adapterView).setOnItemSelectedListener(new GalleryListener());
            this.adapterView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.5
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        } else {
            adapterView.setAdapter(this.adapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean loadedShow() {
        if (this.loadedShow) {
            this.loadedShow = false;
            return true;
        }
        return false;
    }

    private void setLoading(final boolean z) {
        synchronized (this) {
            this.isLoading = z;
        }
        IMyActivity iMyActivity = this.myActivity;
        if (iMyActivity != null) {
            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!z) {
                        FastJsonNextPageLoader4SearchShopList.this.showLoadingView(false);
                        return;
                    }
                    ArrayList<Object> arrayList = FastJsonNextPageLoader4SearchShopList.this.showItemList;
                    if (arrayList == null || arrayList.size() <= 0) {
                        return;
                    }
                    FastJsonNextPageLoader4SearchShopList.this.showLoadingView(true);
                }
            });
        }
    }

    private synchronized void setNetErrorAndNoData(boolean z) {
        this.isNetErrorAndNoData = z;
    }

    private void showCurrentPage(int i2) {
        if (OKLog.D) {
            OKLog.i(TAG, "showItemList.size():" + this.showItemList.size());
        }
        this.adapterView.setSelection(i2);
        this.mCurrPos = i2;
        tryDoAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadingView(boolean z) {
        MySimpleAdapter mySimpleAdapter;
        MySimpleAdapter mySimpleAdapter2;
        if (z) {
            AdapterView adapterView = this.adapterView;
            if (adapterView == null || (mySimpleAdapter2 = this.adapter) == null || !(adapterView instanceof ListView) || this.loadingView == null) {
                return;
            }
            mySimpleAdapter2.removeAllFooterView();
            this.adapter.addFooterView((ListView) this.adapterView, this.loadingView);
            return;
        }
        AdapterView adapterView2 = this.adapterView;
        if (adapterView2 == null || (mySimpleAdapter = this.adapter) == null || !(adapterView2 instanceof ListView) || this.loadingView == null) {
            return;
        }
        mySimpleAdapter.removeAllFooterView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNextPage(ArrayList<?> arrayList) {
        if (OKLog.D) {
            OKLog.d(TAG, "showNextPage() -->> ");
        }
        if (isUseSecondDataStrucFlag() && isSencondDataStrucUsed()) {
            this.secondNextItemList = null;
            if ((this.secondDataStrucShowItemList.size() * 2) / this.pageSize.intValue() <= this.pageNum.intValue()) {
                this.secondDataStrucShowItemList.addAll(arrayList);
            }
        } else {
            this.nextItemList = null;
            if (this.showItemList.size() / this.pageSize.intValue() <= this.pageNum.intValue()) {
                this.showItemList.addAll(arrayList);
            }
        }
        this.pageNum = Integer.valueOf(this.pageNum.intValue() + 1);
        if (OKLog.D) {
            System.out.println("showItemList size = " + this.showItemList.size());
        }
        if (this.showItemList.size() < 1) {
            AdapterView adapterView = this.adapterView;
            if (adapterView instanceof ListView) {
                adapterView.getAdapter();
            }
        }
        if (judgeIsLastPage(arrayList)) {
            this.loadedLastPage = true;
        } else {
            if (OKLog.D) {
                System.err.println("showNextPage() isPreloading " + this.isPreloading);
            }
            if (this.isWifi) {
                loadNextPage();
            }
        }
        tryDoAdapter();
        this.handler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.2
            @Override // java.lang.Runnable
            public void run() {
                FastJsonNextPageLoader4SearchShopList fastJsonNextPageLoader4SearchShopList = FastJsonNextPageLoader4SearchShopList.this;
                if (fastJsonNextPageLoader4SearchShopList.adapterView == null || fastJsonNextPageLoader4SearchShopList.showItemList.size() > FastJsonNextPageLoader4SearchShopList.this.adapterView.getChildCount()) {
                    return;
                }
                FastJsonNextPageLoader4SearchShopList.this.tryShowNextPage();
            }
        }, 500L);
    }

    private void tryDoAdapter() {
        if (OKLog.D) {
            OKLog.d(TAG, "tryDoAdapter() -->> ");
        }
        if (isSencondDataStrucUsed()) {
            MySimpleAdapter mySimpleAdapter = this.secondAdapter;
            if (mySimpleAdapter == null) {
                this.secondAdapter = secondCreateAdapter(this.myActivity, this.httpGroup, this.adapterView, this.secondDataStrucShowItemList);
                doAdapter();
                return;
            }
            mySimpleAdapter.notifyDataSetChanged();
            return;
        }
        MySimpleAdapter mySimpleAdapter2 = this.adapter;
        if (mySimpleAdapter2 == null) {
            this.adapter = createAdapter(this.myActivity, this.httpGroup, this.adapterView, this.showItemList);
            doAdapter();
            return;
        }
        mySimpleAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void tryShowNextPage() {
        this.hasLoadFirstData = true;
        if (OKLog.D) {
            OKLog.d(TAG, "tryShowNextPage() -->> ");
        }
        if (this.loadedLastPage) {
            if (OKLog.D) {
                OKLog.v(TAG, "loadedLast Page " + this.loadedLastPage);
            }
            return;
        }
        if (isSencondDataStrucUsed()) {
            ArrayList<?> arrayList = this.secondNextItemList;
            if (arrayList != null && arrayList.size() != 0) {
                showNextPage(this.secondNextItemList);
            }
            applyLoadedShow();
            if (OKLog.V) {
                OKLog.v(TAG, "isPreloading = " + this.isPreloading);
            }
            if (this.isPreloading) {
                loadNextPage();
            }
        } else {
            ArrayList<?> arrayList2 = this.nextItemList;
            if (arrayList2 != null && arrayList2.size() != 0) {
                if (OKLog.D) {
                    OKLog.d(TAG, "tryShowNextPage() -->> showNextPage(nextItemList)");
                }
                showNextPage(this.nextItemList);
            }
            if (OKLog.D) {
                OKLog.v(TAG, "nextItemList == null isPreloading " + this.isPreloading);
            }
            applyLoadedShow();
            if (OKLog.V) {
                OKLog.v(TAG, "isPreloading = " + this.isPreloading);
            }
            if (this.isPreloading) {
                loadNextPage();
            }
        }
    }

    public void clearData() {
        if (OKLog.D) {
            OKLog.d(TAG, "clearData() -->> ");
        }
        this.loadedMap.clear();
        this.showItemList.clear();
        ArrayList<?> arrayList = this.nextItemList;
        if (arrayList != null && arrayList.size() > 0) {
            this.nextItemList.clear();
        }
        this.secondDataStrucShowItemList.clear();
        ArrayList<?> arrayList2 = this.secondNextItemList;
        if (arrayList2 != null && arrayList2.size() > 0) {
            this.secondNextItemList.clear();
        }
        if (this.loadedLastPage) {
            this.loadedLastPage = false;
        }
        try {
            this.adapter.getAdapterHelper().clean();
            this.secondAdapter.getAdapterHelper().clean();
        } catch (Exception unused) {
        }
        this.adapter = null;
        this.secondAdapter = null;
        this.pageNum = 0;
        setLoading(false);
    }

    protected abstract MySimpleAdapter createAdapter(IMyActivity iMyActivity, HttpGroup httpGroup, AdapterView adapterView, ArrayList<?> arrayList);

    public ArrayList<?> getAllProductList() {
        return this.showItemList;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getHost() {
        return this.host;
    }

    public Map<String, Object> getKeyParmas() {
        if (this.keyParmas == null) {
            this.keyParmas = new HashMap();
        }
        return this.keyParmas;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public JSONObject getParams() {
        JSONObject jSONObject = this.params;
        if (jSONObject == null) {
            JSONObject jSONObject2 = new JSONObject();
            this.params = jSONObject2;
            return jSONObject2;
        }
        return jSONObject;
    }

    public Product getProductItemAt(int i2) {
        return (Product) this.adapter.getItem(i2);
    }

    protected void handleParamsBeforeLoading() {
        try {
            getParams().put(this.pageNumParamKey, this.pageNum);
            getParams().put(this.pageSizeParamKey, this.pageSize);
            if (this.prePageNum >= this.pageNum.intValue()) {
                OKLog.i(TAG, "something wrong...");
            }
            this.prePageNum = this.pageNum.intValue();
        } catch (JSONException e2) {
            if (OKLog.E) {
                OKLog.e("NextPageLoader", "JSONException -->> ", e2);
            }
        }
    }

    public boolean isLoadedLastPage() {
        return this.loadedLastPage;
    }

    public boolean isPaging() {
        return this.isPaging;
    }

    public boolean isSencondDataStrucUsed() {
        return this.isSencondDataStrucUsed && this.isUseSecondDataStrucFlag;
    }

    public boolean isUseCache() {
        return this.isUseCache;
    }

    public boolean isUseSecondDataStrucFlag() {
        return this.isUseSecondDataStrucFlag;
    }

    protected boolean judgeIsLastPage(ArrayList<?> arrayList) {
        int size = arrayList.size();
        if (isSencondDataStrucUsed()) {
            size *= 2;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "judgeIsLastPage() -->> size = " + size);
        }
        boolean z = size < this.pageSize.intValue() || !this.isPaging;
        if (OKLog.D) {
            OKLog.d(TAG, "judgeIsLastPage() -->> flag = " + z);
        }
        return z;
    }

    protected synchronized void loadNextPage() {
        if (OKLog.D) {
            OKLog.d(TAG, "loadNextPage() -->> isLoading = " + this.isLoading);
        }
        if (this.isLoading) {
            return;
        }
        setLoading(true);
        this.pageNum = Integer.valueOf(this.pageNum.intValue() + 1);
        handleParamsBeforeLoading();
        this.pageNum = Integer.valueOf(this.pageNum.intValue() - 1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(this.functionId);
        httpSetting.setJsonParams(getParams());
        httpSetting.setListener(this);
        if (isUseCache()) {
            httpSetting.setLocalFileCache(true);
            httpSetting.setLocalFileCacheTime(10000L);
        }
        if (!TextUtils.isEmpty(getHost())) {
            httpSetting.setHost(getHost());
        }
        Map<String, Object> keyParmas = getKeyParmas();
        keyParmas.put(this.pageNumParamKey, Integer.valueOf(this.pageNum.intValue() + 1));
        httpSetting.setMoreParams(keyParmas);
        httpSetting.setNotifyUser(this.httpNotifyUser);
        httpSetting.setUseFastJsonParser(true);
        if (this.firstLoad && this.isEffect) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        this.firstLoad = false;
        if (this.httpGroup.getHttpGroupSetting() != null) {
            this.httpGroup.getHttpGroupSetting().setMyActivity((Activity) this.myActivity);
        }
        this.httpGroup.add(httpSetting);
    }

    public void modifyData(ModifyDataRunnable modifyDataRunnable) {
        if (this.isHolding) {
            return;
        }
        modifyDataRunnable.modifyData(this.showItemList);
        this.adapter.notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        if (!this.isHolding) {
            if (this.adapter != null) {
                try {
                    if (getAllProductList() != null && getAllProductList().size() < 1) {
                        this.adapter.removeFooterView(this.loadingView);
                    }
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e(TAG, th);
                    }
                }
                this.adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.hasNotify = true;
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        this.isFinishing = true;
        this.myActivity = null;
        this.adapterView = null;
        this.adapter = null;
        this.loadingView = null;
        this.showItemList = null;
        this.nextItemList = null;
        this.secondDataStrucShowItemList = null;
        this.secondNextItemList = null;
        this.httpGroup = null;
        this.myActivity = null;
        this.params = null;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(final HttpResponse httpResponse) {
        setLoading(false);
        setNetErrorAndNoData(false);
        this.handler.post(new Runnable() { // from class: com.jingdong.common.utils.FastJsonNextPageLoader4SearchShopList.6
            private boolean handleReturn(HttpResponse httpResponse2) {
                Map<String, Object> moreParams = httpResponse2.getMoreParams();
                Map<String, Object> keyParmas = FastJsonNextPageLoader4SearchShopList.this.getKeyParmas();
                if (moreParams.size() != keyParmas.size()) {
                    return false;
                }
                for (String str : moreParams.keySet()) {
                    Object obj = moreParams.get(str);
                    Object obj2 = keyParmas.get(str);
                    if (OKLog.D) {
                        OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleReturn() -->> moreKey = " + str);
                        OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleReturn() -->> moreValue = " + obj);
                        OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleReturn() -->> currentValue = " + obj2);
                    }
                    if (obj != obj2 && obj != null && !obj.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            }

            private void handleSecondDataStruc(HttpResponse httpResponse2) {
                if (FastJsonNextPageLoader4SearchShopList.this.isUseSecondDataStrucFlag()) {
                    if (OKLog.D) {
                        OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleSecondDataStruc() -->> \u4f7f\u7528\u4e86\u7b2c\u4e8c\u79cd\u6570\u636e\u7ed3\u6784 ");
                    }
                    ArrayList<?> secondList = FastJsonNextPageLoader4SearchShopList.this.toSecondList(httpResponse2);
                    ArrayList<Object> arrayList = FastJsonNextPageLoader4SearchShopList.this.secondDataStrucShowItemList;
                    if ((arrayList != null && arrayList.size() > 0) || (secondList != null && secondList.size() > 0)) {
                        FastJsonNextPageLoader4SearchShopList.this.showEmpty(false);
                        if (secondList != null && secondList.size() >= 1) {
                            if (secondList.size() > 0) {
                                if (OKLog.D) {
                                    OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleSecondDataStruc() -->> \u4f7f\u7528\u4e86\u7b2c\u4e8c\u79cd\u6570\u636e\u7ed3\u6784\uff0c\u4e14\u8fd8\u4e0d\u4e3a\u7a7a\u54e6");
                                }
                                if (FastJsonNextPageLoader4SearchShopList.this.secondNextItemList != null && FastJsonNextPageLoader4SearchShopList.this.secondNextItemList.size() > 0 && (FastJsonNextPageLoader4SearchShopList.this.secondDataStrucShowItemList.size() * 2) / FastJsonNextPageLoader4SearchShopList.this.pageSize.intValue() < FastJsonNextPageLoader4SearchShopList.this.pageNum.intValue()) {
                                    FastJsonNextPageLoader4SearchShopList fastJsonNextPageLoader4SearchShopList = FastJsonNextPageLoader4SearchShopList.this;
                                    fastJsonNextPageLoader4SearchShopList.secondDataStrucShowItemList.addAll(fastJsonNextPageLoader4SearchShopList.secondNextItemList);
                                }
                                FastJsonNextPageLoader4SearchShopList.this.secondNextItemList = secondList;
                                if (FastJsonNextPageLoader4SearchShopList.this.isSencondDataStrucUsed()) {
                                    if (FastJsonNextPageLoader4SearchShopList.this.loadedShow()) {
                                        if (OKLog.D) {
                                            OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "handleSecondDataStruc()  -->> show now -->> ");
                                        }
                                        FastJsonNextPageLoader4SearchShopList.this.showNextPage(secondList);
                                        return;
                                    }
                                    return;
                                }
                                FastJsonNextPageLoader4SearchShopList.this.secondDataStrucShowItemList.addAll(secondList);
                                return;
                            }
                            return;
                        }
                        OKLog.i(FastJsonNextPageLoader4SearchShopList.TAG, "handleSecondDataStruc() --->showError");
                        FastJsonNextPageLoader4SearchShopList.this.showError();
                        return;
                    }
                    FastJsonNextPageLoader4SearchShopList.this.showEmpty(true);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                Integer num;
                if (FastJsonNextPageLoader4SearchShopList.this.isFinishing) {
                    return;
                }
                if (OKLog.D) {
                    OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "onEnd -->> before handleReturn");
                }
                if (handleReturn(httpResponse)) {
                    if (OKLog.D) {
                        OKLog.d(FastJsonNextPageLoader4SearchShopList.TAG, "onEnd -->> after handleReturn");
                    }
                    try {
                        num = (Integer) httpResponse.getMoreParams().get(FastJsonNextPageLoader4SearchShopList.this.pageNumParamKey);
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(FastJsonNextPageLoader4SearchShopList.TAG, e2);
                        }
                    }
                    synchronized (FastJsonNextPageLoader4SearchShopList.this.loadedMap) {
                        if (FastJsonNextPageLoader4SearchShopList.this.TRUE == FastJsonNextPageLoader4SearchShopList.this.loadedMap.get(num)) {
                            return;
                        }
                        FastJsonNextPageLoader4SearchShopList.this.loadedMap.put(num, FastJsonNextPageLoader4SearchShopList.this.TRUE);
                        ArrayList<?> list = FastJsonNextPageLoader4SearchShopList.this.toList(httpResponse);
                        handleSecondDataStruc(httpResponse);
                        ArrayList<Object> arrayList = FastJsonNextPageLoader4SearchShopList.this.showItemList;
                        if ((arrayList != null && arrayList.size() > 0) || (list != null && list.size() > 0)) {
                            FastJsonNextPageLoader4SearchShopList.this.showEmpty(false);
                            if (list != null) {
                                if (FastJsonNextPageLoader4SearchShopList.this.nextItemList != null && FastJsonNextPageLoader4SearchShopList.this.nextItemList.size() > 0 && FastJsonNextPageLoader4SearchShopList.this.showItemList.size() / FastJsonNextPageLoader4SearchShopList.this.pageSize.intValue() < FastJsonNextPageLoader4SearchShopList.this.pageNum.intValue()) {
                                    FastJsonNextPageLoader4SearchShopList fastJsonNextPageLoader4SearchShopList = FastJsonNextPageLoader4SearchShopList.this;
                                    fastJsonNextPageLoader4SearchShopList.showItemList.addAll(fastJsonNextPageLoader4SearchShopList.nextItemList);
                                }
                                FastJsonNextPageLoader4SearchShopList.this.nextItemList = list;
                                if (!FastJsonNextPageLoader4SearchShopList.this.isSencondDataStrucUsed()) {
                                    if (FastJsonNextPageLoader4SearchShopList.this.loadedShow()) {
                                        FastJsonNextPageLoader4SearchShopList.this.showNextPage(list);
                                        return;
                                    }
                                    return;
                                }
                                FastJsonNextPageLoader4SearchShopList.this.showItemList.addAll(list);
                                return;
                            }
                            OKLog.i(FastJsonNextPageLoader4SearchShopList.TAG, "onEnd--->showError");
                            FastJsonNextPageLoader4SearchShopList.this.showError();
                            return;
                        }
                        FastJsonNextPageLoader4SearchShopList.this.showEmpty(true);
                    }
                }
            }
        });
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        if (this.isFinishing) {
            return;
        }
        ArrayList<Object> arrayList = this.showItemList;
        if (arrayList != null && arrayList.size() > 0) {
            setNetErrorAndNoData(false);
        } else {
            setNetErrorAndNoData(true);
        }
        setLoading(false);
        showEmpty(false);
        showError();
    }

    public void onFirstVisible(int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
    public void onProgress(int i2, int i3) {
    }

    protected void onScroll(AbsListView absListView, int i2, int i3, int i4) {
    }

    protected void onScrollStateChanged(AbsListView absListView, int i2) {
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
    }

    protected MySimpleAdapter secondCreateAdapter(IMyActivity iMyActivity, HttpGroup httpGroup, AdapterView adapterView, ArrayList<?> arrayList) {
        return null;
    }

    public void setAdapterView(AdapterView adapterView) {
        AdapterView adapterView2 = this.adapterView;
        if (adapterView2 == adapterView) {
            return;
        }
        adapterView2.setVisibility(8);
        this.adapterView = null;
        this.adapter = null;
        this.adapterView = adapterView;
        if (adapterView != null) {
            adapterView.setVisibility(0);
            showCurrentPage(this.mCurrPos);
        }
        if (OKLog.D) {
            System.out.println("adapterView is " + this.adapterView);
        }
    }

    public void setEffect(boolean z) {
        this.isEffect = z;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setHttpNotifyUser(boolean z) {
        this.httpNotifyUser = z;
    }

    public void setKeyParmas(Map<String, Object> map) {
        this.keyParmas = map;
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

    public void setPaging(boolean z) {
        this.isPaging = z;
    }

    public void setParams(JSONObject jSONObject) {
        this.params = jSONObject;
    }

    public abstract void setSelection(int i2);

    public void setSencondDataStrucUsed(boolean z) {
        this.isSencondDataStrucUsed = z;
        this.adapter = null;
        this.secondAdapter = null;
    }

    public void setUseCache(boolean z) {
        this.isUseCache = z;
    }

    public void setUseSecondDataStrucFlag(boolean z) {
        this.isUseSecondDataStrucFlag = z;
    }

    protected abstract void showEmpty(boolean z);

    protected abstract void showError();

    public void showPageOne() {
        showPageOne(false);
    }

    protected abstract ArrayList<?> toList(HttpResponse httpResponse);

    protected ArrayList<?> toSecondList(HttpResponse httpResponse) {
        return null;
    }

    public void showPageOne(boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "showPageOne() -->> ");
        }
        this.loadedMap.clear();
        this.firstLoad = true;
        applyLoadedShow();
        tryShowNextPage();
    }

    public FastJsonNextPageLoader4SearchShopList(IMyActivity iMyActivity, HttpGroup httpGroup, AdapterView adapterView, View view, String str, JSONObject jSONObject) {
        this(iMyActivity, httpGroup, adapterView, view, str);
        this.params = jSONObject;
        this.isPreloading = true;
    }
}

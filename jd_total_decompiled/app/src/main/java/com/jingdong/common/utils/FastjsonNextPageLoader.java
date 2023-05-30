package com.jingdong.common.utils;

import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.common.entity.Product;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class FastjsonNextPageLoader implements HttpGroup.OnAllListener, IDestroyListener {
    private static final String TAG = "FastjsonNextPageLoader";
    private final Boolean TRUE;
    private MySimpleAdapter adapter;
    protected AdapterView adapterView;
    protected Integer colSize;
    private boolean firstLoad;
    protected String functionId;
    private Handler handler;
    private boolean hasNotify;
    private String host;
    protected HttpGroup httpGroup;
    protected boolean httpNotifyUser;
    private boolean isEffect;
    private boolean isFinishing;
    private boolean isFling;
    private boolean isHolding;
    private boolean isLoading;
    private boolean isNeedNoDateView;
    protected boolean isPaging;
    private boolean isPreloading;
    private JdOnTouchListener jdOnTouchListener;
    private boolean loadedLastPage;
    private HashMap<Integer, Boolean> loadedMap;
    private boolean loadedShow;
    private boolean loading;
    private View loadingView;
    private long localFileCacheTime;
    private AbsListView.OnScrollListener mCallbackScrollListener;
    private IMyActivity myActivity;
    private boolean needFooterView;
    private ArrayList<?> nextItemList;
    protected String noDataHint;
    private TextView noDataView;
    protected Integer pageNum;
    protected String pageNumParamKey;
    protected Integer pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    HttpRequest request;
    protected ArrayList<Object> showItemList;
    private Long totalCount;
    protected Integer totalPage;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class BaseListener implements AbsListView.OnScrollListener {
        private BaseListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (FastjsonNextPageLoader.this.mCallbackScrollListener != null) {
                FastjsonNextPageLoader.this.mCallbackScrollListener.onScroll(absListView, i2, i3, i4);
            }
            FastjsonNextPageLoader.this.onScroll(absListView, i2, i3, i4);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (FastjsonNextPageLoader.this.mCallbackScrollListener != null) {
                FastjsonNextPageLoader.this.mCallbackScrollListener.onScrollStateChanged(absListView, i2);
            }
            FastjsonNextPageLoader.this.onScrollStateChanged(absListView, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class GalleryListener implements AdapterView.OnItemSelectedListener {
        GalleryListener() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
            if (FastjsonNextPageLoader.this.showItemList == null || r1.size() - 1 != i2 || FastjsonNextPageLoader.this.isFinishing || FastjsonNextPageLoader.this.isLoadedLastPage()) {
                return;
            }
            FastjsonNextPageLoader.this.tryShowNextPage();
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

        @Override // com.jingdong.common.utils.FastjsonNextPageLoader.BaseListener, android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            this.firstVisibleItem = i2;
            this.visibleItemCount = i3;
            this.totalItemCount = i4;
            FastjsonNextPageLoader.this.setSelection(i2);
            super.onScroll(absListView, i2, i3, i4);
        }

        public abstract void onScrollFling();

        public abstract void onScrollIdle();

        public abstract void onScrollLast();

        @Override // com.jingdong.common.utils.FastjsonNextPageLoader.BaseListener, android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 == 0) {
                onScrollIdle();
            } else if (i2 == 2) {
                onScrollFling();
            }
            super.onScrollStateChanged(absListView, i2);
        }
    }

    public FastjsonNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str) {
        this.showItemList = new ArrayList<>();
        this.loading = false;
        this.nextItemList = null;
        this.loadedShow = false;
        this.loadedLastPage = false;
        this.firstLoad = true;
        this.isEffect = true;
        this.isHolding = false;
        this.isFling = false;
        this.isPaging = true;
        this.pageNumParamKey = "page";
        this.pageSizeParamKey = "pagesize";
        this.pageNum = 1;
        this.pageSize = 10;
        this.colSize = 1;
        this.httpNotifyUser = true;
        this.needFooterView = false;
        this.totalCount = null;
        this.mCallbackScrollListener = null;
        this.isNeedNoDateView = true;
        this.loadedMap = new HashMap<>();
        this.TRUE = new Boolean(true);
        this.isLoading = false;
        this.myActivity = iMyActivity;
        this.handler = iMyActivity.getHandler();
        iMyActivity.addDestroyListener(this);
        this.httpGroup = this.myActivity.getHttpGroupaAsynPool();
        this.adapterView = adapterView;
        this.loadingView = view;
        this.functionId = str;
        this.isPreloading = true;
    }

    private void applyLoadedShow() {
        this.loadedShow = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean loadedShow() {
        if (this.loadedShow) {
            this.loadedShow = false;
            return true;
        }
        return false;
    }

    private synchronized boolean loadingLock() {
        if (this.loading) {
            return false;
        }
        this.loading = true;
        return true;
    }

    private synchronized void loadingUnLock() {
        this.loading = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void showNextPage(ArrayList<?> arrayList) {
        MySimpleAdapter mySimpleAdapter;
        MySimpleAdapter mySimpleAdapter2;
        View view;
        View view2;
        this.nextItemList = null;
        this.showItemList.addAll(arrayList);
        if (OKLog.D) {
            System.out.println("showItemList size = " + this.showItemList.size());
        }
        boolean z = this.isNeedNoDateView;
        String str = com.jingdong.jdsdk.res.StringUtil.no_data;
        if (z && this.showItemList.size() < 1) {
            AdapterView adapterView = this.adapterView;
            if (adapterView instanceof ListView) {
                TextView textView = (TextView) adapterView.findViewWithTag(new String("nodataview"));
                this.noDataView = textView;
                if (textView == null) {
                    TextView textView2 = new TextView(this.myActivity.getThisActivity());
                    this.noDataView = textView2;
                    textView2.setGravity(17);
                    String str2 = this.noDataHint;
                    if (str2 != null) {
                        this.noDataView.setText(str2);
                    } else {
                        this.noDataView.setText(com.jingdong.jdsdk.res.StringUtil.no_data);
                    }
                    this.noDataView.setTextSize(17.0f);
                    this.noDataView.setPadding(0, 20, 0, 20);
                    this.noDataView.setTag(new String("nodataview"));
                    try {
                        ((ListView) this.adapterView).addHeaderView(this.noDataView, com.jingdong.jdsdk.res.StringUtil.no_data, false);
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2);
                        }
                    }
                }
                if (!judgeIsLastPage(arrayList)) {
                    this.loadedLastPage = true;
                    AdapterView adapterView2 = this.adapterView;
                    if (adapterView2 instanceof ListView) {
                        ((ListView) adapterView2).setOnScrollListener(new BaseListener());
                    }
                } else {
                    if (OKLog.D) {
                        System.err.println("showNextPage() isPreloading " + this.isPreloading);
                    }
                    this.pageNum = Integer.valueOf(this.pageNum.intValue() + 1);
                    loading();
                }
                mySimpleAdapter = this.adapter;
                if (mySimpleAdapter != null) {
                    MySimpleAdapter createAdapter = createAdapter(this.myActivity, this.adapterView, this.showItemList);
                    this.adapter = createAdapter;
                    createAdapter.setNextPageLoader(this);
                    final OnScrollLastListener onScrollLastListener = new OnScrollLastListener() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.1
                        @Override // com.jingdong.common.utils.FastjsonNextPageLoader.OnScrollLastListener
                        public void onScrollFling() {
                            FastjsonNextPageLoader.this.isFling = true;
                        }

                        @Override // com.jingdong.common.utils.FastjsonNextPageLoader.OnScrollLastListener
                        public void onScrollIdle() {
                            FastjsonNextPageLoader.this.isHolding = false;
                            FastjsonNextPageLoader.this.isFling = false;
                            if (FastjsonNextPageLoader.this.isFinishing) {
                                return;
                            }
                            if (FastjsonNextPageLoader.this.hasNotify) {
                                FastjsonNextPageLoader.this.hasNotify = false;
                                if (FastjsonNextPageLoader.this.adapter != null) {
                                    FastjsonNextPageLoader.this.adapter.notifyDataSetChanged();
                                }
                            }
                            checkLast();
                        }

                        @Override // com.jingdong.common.utils.FastjsonNextPageLoader.OnScrollLastListener
                        public void onScrollLast() {
                            if (FastjsonNextPageLoader.this.isFinishing || FastjsonNextPageLoader.this.isLoadedLastPage()) {
                                return;
                            }
                            FastjsonNextPageLoader.this.tryShowNextPage();
                        }
                    };
                    this.adapterView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.2
                        @Override // android.view.View.OnTouchListener
                        public boolean onTouch(View view3, MotionEvent motionEvent) {
                            if (FastjsonNextPageLoader.this.jdOnTouchListener != null) {
                                FastjsonNextPageLoader.this.jdOnTouchListener.onTouch(view3, motionEvent);
                            }
                            int action = motionEvent.getAction();
                            if (action != 0) {
                                if (action == 1 && !FastjsonNextPageLoader.this.isFling) {
                                    onScrollLastListener.onScrollIdle();
                                    return false;
                                }
                                return false;
                            }
                            FastjsonNextPageLoader.this.isHolding = true;
                            return false;
                        }
                    });
                    if (OKLog.D && this.adapter != null) {
                        OKLog.d(TAG, "setAdapter adpter size = " + this.adapter.getCount());
                    }
                    AdapterView adapterView3 = this.adapterView;
                    if (adapterView3 instanceof ListView) {
                        MySimpleAdapter mySimpleAdapter3 = this.adapter;
                        if (mySimpleAdapter3 != null) {
                            adapterView3.setAdapter(mySimpleAdapter3.getHeaderViewListAdapter());
                            if (this.needFooterView && (view2 = this.loadingView) != null) {
                                this.adapter.addFooterView((ListView) this.adapterView, view2);
                                this.needFooterView = false;
                            }
                        } else {
                            adapterView3.setAdapter(mySimpleAdapter3);
                        }
                        ((ListView) this.adapterView).setOnScrollListener(onScrollLastListener);
                    } else if (adapterView3 instanceof GridView) {
                        adapterView3.setAdapter(this.adapter);
                        ((GridView) this.adapterView).setOnScrollListener(onScrollLastListener);
                    } else if (adapterView3 instanceof Gallery) {
                        adapterView3.setAdapter(this.adapter);
                        ((Gallery) this.adapterView).setOnItemSelectedListener(new GalleryListener());
                        this.adapterView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.3
                            @Override // android.view.View.OnTouchListener
                            public boolean onTouch(View view3, MotionEvent motionEvent) {
                                return false;
                            }
                        });
                    } else {
                        adapterView3.setAdapter(this.adapter);
                    }
                    loadingUnLock();
                } else {
                    mySimpleAdapter.notifyDataSetChanged();
                    loadingUnLock();
                }
                this.handler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ArrayList<Object> arrayList2;
                        FastjsonNextPageLoader fastjsonNextPageLoader = FastjsonNextPageLoader.this;
                        if (fastjsonNextPageLoader.adapterView == null || (arrayList2 = fastjsonNextPageLoader.showItemList) == null || arrayList2.size() > FastjsonNextPageLoader.this.adapterView.getChildCount()) {
                            return;
                        }
                        FastjsonNextPageLoader.this.tryShowNextPage();
                    }
                }, 500L);
                if (isLoadedLastPage() || (mySimpleAdapter2 = this.adapter) == null || (view = this.loadingView) == null || !(this.adapterView instanceof ListView)) {
                    return;
                }
                mySimpleAdapter2.removeFooterView(view);
                return;
            }
        }
        TextView textView3 = (TextView) this.adapterView.findViewWithTag(new String("nodataview"));
        String str3 = this.noDataHint;
        if (str3 != null) {
            str = str3;
        }
        if (textView3 != null && textView3.getText().equals(str)) {
            ((ListView) this.adapterView).removeHeaderView(textView3);
        }
        if (!judgeIsLastPage(arrayList)) {
        }
        mySimpleAdapter = this.adapter;
        if (mySimpleAdapter != null) {
        }
        this.handler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<Object> arrayList2;
                FastjsonNextPageLoader fastjsonNextPageLoader = FastjsonNextPageLoader.this;
                if (fastjsonNextPageLoader.adapterView == null || (arrayList2 = fastjsonNextPageLoader.showItemList) == null || arrayList2.size() > FastjsonNextPageLoader.this.adapterView.getChildCount()) {
                    return;
                }
                FastjsonNextPageLoader.this.tryShowNextPage();
            }
        }, 500L);
        if (isLoadedLastPage()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void tryShowNextPage() {
        if (this.loadedLastPage) {
            if (OKLog.D) {
                OKLog.v(TAG, "loadedLast Page " + this.loadedLastPage);
            }
            return;
        }
        if (this.nextItemList == null) {
            if (OKLog.D) {
                OKLog.v(TAG, "nextItemList == null isPreloading " + this.isPreloading);
            }
            applyLoadedShow();
            if (!loadingLock()) {
                return;
            }
            if (OKLog.V) {
                OKLog.v(TAG, "isPreloading = " + this.isPreloading);
            }
            if (this.isPreloading) {
                loading();
            }
        } else {
            if (OKLog.D) {
                OKLog.d("Temp", "show do -->> ");
                System.err.println("showNextPage(nextItemList)");
            }
            showNextPage(this.nextItemList);
        }
    }

    public boolean checkLast() {
        AdapterView adapterView = this.adapterView;
        if (adapterView != null && adapterView.getLastVisiblePosition() == this.adapterView.getCount() - 1) {
            if (this.isFinishing) {
                return this.loadedLastPage;
            }
            if (!isLoadedLastPage()) {
                tryShowNextPage();
            }
        }
        return this.loadedLastPage;
    }

    protected abstract MySimpleAdapter createAdapter(IMyActivity iMyActivity, AdapterView adapterView, ArrayList<?> arrayList);

    public void forceNotifyDataSetChanged() {
        MySimpleAdapter mySimpleAdapter = this.adapter;
        if (mySimpleAdapter != null) {
            mySimpleAdapter.notifyDataSetChanged();
        }
    }

    public ArrayList<?> getAllProductList() {
        return this.showItemList;
    }

    public String getHost() {
        return this.host;
    }

    public ArrayList<?> getNextItemList() {
        return this.nextItemList;
    }

    public TextView getNoDataView() {
        return this.noDataView;
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

    public Long getTotalCount() {
        return this.totalCount;
    }

    protected void handleHttpSetttingBeforeLoading(HttpSetting httpSetting) {
    }

    protected void handleParamsBeforeLoading() {
        try {
            getParams().put(this.pageNumParamKey, "" + this.pageNum);
            getParams().put(this.pageSizeParamKey, "" + this.pageSize);
        } catch (JSONException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public boolean isLoadedLastPage() {
        return this.loadedLastPage;
    }

    public boolean isNeedFooterView() {
        return this.needFooterView;
    }

    public boolean isPaging() {
        return this.isPaging;
    }

    protected boolean judgeIsLastPage(ArrayList<?> arrayList) {
        if (arrayList.size() < this.pageSize.intValue() / this.colSize.intValue() || !this.isPaging) {
            return true;
        }
        Integer num = this.totalPage;
        return num != null && num == this.pageNum;
    }

    protected synchronized void loading() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        handleParamsBeforeLoading();
        HashMap hashMap = new HashMap();
        hashMap.put(this.pageNumParamKey, this.pageNum);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(this.functionId);
        httpSetting.setJsonParams(getParams());
        httpSetting.setListener(this);
        httpSetting.setMoreParams(hashMap);
        httpSetting.setNotifyUser(this.httpNotifyUser);
        httpSetting.setUseFastJsonParser(true);
        handleHttpSetttingBeforeLoading(httpSetting);
        if (!TextUtils.isEmpty(getHost())) {
            httpSetting.setHost(getHost());
        }
        if (this.firstLoad && this.isEffect) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        if (this.localFileCacheTime > 0) {
            httpSetting.setLocalFileCache(true);
            httpSetting.setLocalFileCacheTime(this.localFileCacheTime);
        }
        this.firstLoad = false;
        this.request = this.httpGroup.add(httpSetting);
    }

    public void modifyData(ModifyDataRunnable modifyDataRunnable) {
        if (this.isHolding || modifyDataRunnable == null) {
            return;
        }
        modifyDataRunnable.modifyData(this.showItemList);
        MySimpleAdapter mySimpleAdapter = this.adapter;
        if (mySimpleAdapter != null) {
            mySimpleAdapter.notifyDataSetChanged();
        }
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
        this.httpGroup = null;
        this.params = null;
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            httpRequest.stop();
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(final HttpResponse httpResponse) {
        this.isLoading = false;
        if (getTotalCount() == null) {
            setTotalCount(Long.valueOf(httpResponse.getFastJsonObject().optLong("totalCount")));
            if (OKLog.D) {
                OKLog.d(TAG, "onEnd() -->> totalCount = " + getTotalCount());
            }
        }
        toList(httpResponse);
        this.handler.post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0043: INVOKE 
              (wrap: android.os.Handler : 0x003c: IGET (r3v0 'this' com.jingdong.common.utils.FastjsonNextPageLoader A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:7) com.jingdong.common.utils.FastjsonNextPageLoader.handler android.os.Handler)
              (wrap: java.lang.Runnable : 0x0040: CONSTRUCTOR 
              (r3v0 'this' com.jingdong.common.utils.FastjsonNextPageLoader A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.ArrayList A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'httpResponse' com.jingdong.jdsdk.network.toolbox.HttpResponse A[DONT_INLINE])
             A[MD:(com.jingdong.common.utils.FastjsonNextPageLoader, java.util.ArrayList, com.jingdong.jdsdk.network.toolbox.HttpResponse):void (m), WRAPPED] call: com.jingdong.common.utils.FastjsonNextPageLoader.5.<init>(com.jingdong.common.utils.FastjsonNextPageLoader, java.util.ArrayList, com.jingdong.jdsdk.network.toolbox.HttpResponse):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:7) in method: com.jingdong.common.utils.FastjsonNextPageLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            r0 = 0
            r3.isLoading = r0
            java.lang.Long r0 = r3.getTotalCount()
            if (r0 != 0) goto L38
            com.jd.framework.json.JDJSONObject r0 = r4.getFastJsonObject()
            java.lang.String r1 = "totalCount"
            long r0 = r0.optLong(r1)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.setTotalCount(r0)
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto L38
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onEnd() -->> totalCount = "
            r0.append(r1)
            java.lang.Long r1 = r3.getTotalCount()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "FastjsonNextPageLoader"
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
        L38:
            java.util.ArrayList r0 = r3.toList(r4)
            android.os.Handler r1 = r3.handler
            com.jingdong.common.utils.FastjsonNextPageLoader$5 r2 = new com.jingdong.common.utils.FastjsonNextPageLoader$5
            r2.<init>()
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.FastjsonNextPageLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        this.isLoading = false;
        loadingUnLock();
        if (this.showItemList == null) {
            return;
        }
        if (getTotalCount() == null || getTotalCount().longValue() != this.showItemList.size()) {
            IMyActivity iMyActivity = this.myActivity;
            if (iMyActivity != null) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.utils.FastjsonNextPageLoader.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (FastjsonNextPageLoader.this.adapter != null) {
                            FastjsonNextPageLoader.this.adapter.removeFooterView(FastjsonNextPageLoader.this.loadingView);
                        }
                        FastjsonNextPageLoader.this.needFooterView = true;
                    }
                });
            }
            showError();
        }
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

    public void pause() {
        MySimpleAdapter mySimpleAdapter = this.adapter;
        if (mySimpleAdapter != null) {
            mySimpleAdapter.onPause();
        }
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
            this.isPreloading = false;
            showPageOne();
        }
        if (OKLog.D) {
            System.out.println("adapterView is null " + this.adapterView);
        }
    }

    public void setColSize(int i2) {
        this.colSize = Integer.valueOf(i2);
    }

    public void setEffect(boolean z) {
        this.isEffect = z;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setHttpNotifyUser(boolean z) {
        this.httpNotifyUser = z;
    }

    public void setJdOntouchListener(JdOnTouchListener jdOnTouchListener) {
        this.jdOnTouchListener = jdOnTouchListener;
    }

    public void setNeedFooterView(boolean z) {
        this.needFooterView = z;
    }

    public void setNeedNoDateView(boolean z) {
        this.isNeedNoDateView = z;
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

    public void setScrollListenerCallback(AbsListView.OnScrollListener onScrollListener) {
        this.mCallbackScrollListener = onScrollListener;
    }

    public abstract void setSelection(int i2);

    public void setTotalCount(Long l2) {
        this.totalCount = l2;
    }

    public void setTotalPage(int i2) {
        this.totalPage = Integer.valueOf(i2);
    }

    protected abstract void showError();

    public void showPageOne() {
        showPageOne(false);
    }

    protected abstract ArrayList<?> toList(HttpResponse httpResponse);

    public void showPageOne(boolean z) {
        this.needFooterView = z;
        this.loadedMap.clear();
        applyLoadedShow();
        tryShowNextPage();
    }

    public FastjsonNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject) {
        this(iMyActivity, adapterView, view, str);
        this.params = jSONObject;
        this.isPreloading = true;
    }

    public FastjsonNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject, String str2) {
        this(iMyActivity, adapterView, view, str, jSONObject);
        this.noDataHint = str2;
        this.isPreloading = true;
    }

    public FastjsonNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject, String str2, long j2) {
        this(iMyActivity, adapterView, view, str, jSONObject, str2);
        this.localFileCacheTime = j2;
    }
}

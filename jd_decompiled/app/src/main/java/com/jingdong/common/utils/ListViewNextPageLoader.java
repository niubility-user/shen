package com.jingdong.common.utils;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.ListView;
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
public abstract class ListViewNextPageLoader implements HttpGroup.OnAllListener, IDestroyListener {
    private static final String TAG = "NextPageLoader";
    protected String functionId;
    private Handler handler;
    private String host;
    protected HttpGroup httpGroup;
    protected boolean httpNotifyUser;
    private boolean isDestoryed;
    private boolean isEffect;
    private boolean isLoading;
    protected boolean isPaging;
    private boolean loadedLastPage;
    private HashMap<Integer, Boolean> loadedMap;
    private long localFileCacheTime;
    private AbsListView.OnScrollListener mCallbackScrollListener;
    private IMyActivity myActivity;
    private ArrayList<?> nextItemList;
    protected Long order;
    protected String orderParamKey;
    protected int pageNo;
    protected String pageNoParamKey;
    protected int pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    HttpRequest request;
    protected ArrayList<Object> showItemList;
    private long totalCount;
    protected int totalPage;
    protected boolean useOtherOrderKey;

    public ListViewNextPageLoader(IMyActivity iMyActivity, ListView listView, String str) {
        this.showItemList = new ArrayList<>();
        this.nextItemList = null;
        this.loadedLastPage = false;
        this.isEffect = true;
        this.isPaging = true;
        this.isLoading = false;
        this.httpNotifyUser = true;
        this.isDestoryed = false;
        this.useOtherOrderKey = false;
        this.orderParamKey = "";
        this.order = 0L;
        this.pageNoParamKey = "page";
        this.pageSizeParamKey = "pagesize";
        this.pageNo = 1;
        this.pageSize = 10;
        this.totalCount = -1L;
        this.mCallbackScrollListener = null;
        this.loadedMap = new HashMap<>();
        this.myActivity = iMyActivity;
        this.handler = iMyActivity.getHandler();
        iMyActivity.addDestroyListener(this);
        this.httpGroup = this.myActivity.getHttpGroupaAsynPool();
        if (listView != null) {
            listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.utils.ListViewNextPageLoader.1
                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                    ArrayList<Object> arrayList;
                    if (!ListViewNextPageLoader.this.loadedLastPage && !ListViewNextPageLoader.this.isLoading && !ListViewNextPageLoader.this.isDestoryed && ListViewNextPageLoader.this.isPaging && absListView.getAdapter() != null && (arrayList = ListViewNextPageLoader.this.showItemList) != null && arrayList.size() > 0 && i4 - i2 <= i3 * 2) {
                        ListViewNextPageLoader.this.tryShowNextPage();
                    }
                    if (ListViewNextPageLoader.this.mCallbackScrollListener != null) {
                        ListViewNextPageLoader.this.mCallbackScrollListener.onScroll(absListView, i2, i3, i4);
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i2) {
                    if (ListViewNextPageLoader.this.mCallbackScrollListener != null) {
                        ListViewNextPageLoader.this.mCallbackScrollListener.onScrollStateChanged(absListView, i2);
                    }
                }
            });
        }
        this.functionId = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadNextPage(ArrayList<?> arrayList, boolean z) {
        judgeIsLastPage(arrayList);
        if (showNextPageData(arrayList)) {
            this.nextItemList = null;
            this.showItemList.addAll(arrayList);
        }
        onOnePageEnd(this.loadedLastPage);
        if (this.loadedLastPage || z) {
            return;
        }
        this.pageNo++;
    }

    private void resetData() {
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            httpRequest.stop();
        }
        this.loadedLastPage = false;
        this.loadedMap.clear();
        this.pageNo = 1;
        this.nextItemList = null;
        this.isPaging = true;
        this.isLoading = false;
        this.showItemList.clear();
    }

    public void bindData(ArrayList<?> arrayList, int i2) {
        resetData();
        if ((arrayList == null || arrayList.size() == 0) && i2 == 0) {
            showPageOne();
            return;
        }
        this.showItemList.addAll(arrayList);
        if (judgeIsLastPage(arrayList)) {
            onOnePageEnd(true);
        }
        this.pageNo += i2;
    }

    public ArrayList<?> getAllDataList() {
        return this.showItemList;
    }

    public String getHost() {
        return this.host;
    }

    public Integer getPageNo() {
        return Integer.valueOf(this.pageNo);
    }

    public int getPageSize() {
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

    public long getTotalCount() {
        return this.totalCount;
    }

    protected void handleParamsBeforeLoading() {
        try {
            if (this.useOtherOrderKey) {
                getParams().put(this.orderParamKey, this.order);
            } else {
                getParams().put(this.pageNoParamKey, "" + this.pageNo);
            }
            getParams().put(this.pageSizeParamKey, "" + this.pageSize);
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(TAG, "JSONException -->> ", e2);
            }
        }
    }

    public boolean hasData() {
        ArrayList<Object> arrayList = this.showItemList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isLoadedLastPage() {
        return this.loadedLastPage;
    }

    protected boolean judgeIsLastPage(ArrayList<?> arrayList) {
        if ((arrayList != null && arrayList.size() < this.pageSize) || (this.totalPage == this.pageNo && !this.useOtherOrderKey)) {
            this.loadedLastPage = true;
        } else {
            this.loadedLastPage = false;
        }
        return this.loadedLastPage;
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        this.isDestoryed = true;
        this.myActivity = null;
        this.showItemList.clear();
        this.nextItemList = null;
        this.httpGroup = null;
        this.params = null;
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            httpRequest.stop();
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
    public void onEnd(HttpResponse httpResponse) {
        if (getTotalCount() <= 0) {
            setTotalCount(httpResponse.getJSONObject().optLong("totalCount"));
            if (OKLog.D) {
                OKLog.d(TAG, "onEnd() -->> totalCount = " + getTotalCount());
            }
        }
        toList(httpResponse);
        httpResponse.getMoreParams();
        this.handler.post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0044: INVOKE 
              (wrap: android.os.Handler : 0x003d: IGET (r5v0 'this' com.jingdong.common.utils.ListViewNextPageLoader A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:7) com.jingdong.common.utils.ListViewNextPageLoader.handler android.os.Handler)
              (wrap: java.lang.Runnable : 0x0041: CONSTRUCTOR 
              (r5v0 'this' com.jingdong.common.utils.ListViewNextPageLoader A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.ArrayList A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r6 I:java.util.Map A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.utils.ListViewNextPageLoader, java.util.ArrayList, java.util.Map):void (m), WRAPPED] call: com.jingdong.common.utils.ListViewNextPageLoader.2.<init>(com.jingdong.common.utils.ListViewNextPageLoader, java.util.ArrayList, java.util.Map):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:7) in method: com.jingdong.common.utils.ListViewNextPageLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes6.dex
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
            */
        /*
            this = this;
            long r0 = r5.getTotalCount()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L35
            com.jingdong.jdsdk.utils.JSONObjectProxy r0 = r6.getJSONObject()
            java.lang.String r1 = "totalCount"
            long r0 = r0.optLong(r1)
            r5.setTotalCount(r0)
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D
            if (r0 == 0) goto L35
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onEnd() -->> totalCount = "
            r0.append(r1)
            long r1 = r5.getTotalCount()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "NextPageLoader"
            com.jingdong.sdk.oklog.OKLog.d(r1, r0)
        L35:
            java.util.ArrayList r0 = r5.toList(r6)
            java.util.Map r6 = r6.getMoreParams()
            android.os.Handler r1 = r5.handler
            com.jingdong.common.utils.ListViewNextPageLoader$2 r2 = new com.jingdong.common.utils.ListViewNextPageLoader$2
            r2.<init>()
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.ListViewNextPageLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        this.isLoading = false;
        if (getTotalCount() == this.showItemList.size()) {
            return;
        }
        this.handler.post(new Runnable() { // from class: com.jingdong.common.utils.ListViewNextPageLoader.3
            @Override // java.lang.Runnable
            public void run() {
                ListViewNextPageLoader.this.onOnePageErr();
            }
        });
        this.isPaging = false;
    }

    protected abstract void onOnePageEnd(boolean z);

    protected abstract void onOnePageErr();

    protected abstract void onOnePageLoading();

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
    public void onProgress(int i2, int i3) {
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
    public void onStart() {
    }

    protected synchronized void requestNextPage() {
        if (!this.isLoading && this.httpGroup != null) {
            this.isLoading = true;
            onOnePageLoading();
            handleParamsBeforeLoading();
            HashMap hashMap = new HashMap();
            if (this.useOtherOrderKey) {
                hashMap.put(this.pageNoParamKey, this.order);
            } else {
                hashMap.put(this.pageNoParamKey, Integer.valueOf(this.pageNo));
            }
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId(this.functionId);
            httpSetting.setJsonParams(getParams());
            httpSetting.setListener(this);
            httpSetting.setMoreParams(hashMap);
            httpSetting.setNotifyUser(this.httpNotifyUser);
            if (!TextUtils.isEmpty(getHost())) {
                httpSetting.setHost(getHost());
            }
            if (this.isEffect && this.pageNo == 1) {
                this.isEffect = false;
                httpSetting.setEffect(1);
            } else {
                httpSetting.setEffect(0);
            }
            if (this.localFileCacheTime > 0 && this.pageNo == 1) {
                httpSetting.setLocalFileCache(true);
                httpSetting.setLocalFileCacheTime(this.localFileCacheTime);
            }
            this.request = this.httpGroup.add(httpSetting);
        }
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

    public void setLocalFileCacheTime(long j2) {
        this.localFileCacheTime = j2;
    }

    public void setOrderParamKey(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.useOtherOrderKey = true;
        this.orderParamKey = str;
    }

    public void setPageNoParamKey(String str) {
        this.pageNoParamKey = str;
    }

    public void setPageSize(int i2) {
        this.pageSize = i2;
    }

    public void setPageSizeParamKey(String str) {
        this.pageSizeParamKey = str;
    }

    public void setParams(JSONObject jSONObject) {
        this.params = jSONObject;
    }

    public void setScrollListenerCallback(AbsListView.OnScrollListener onScrollListener) {
        this.mCallbackScrollListener = onScrollListener;
    }

    public void setTotalCount(long j2) {
        this.totalCount = j2;
    }

    public void setTotalPage(int i2) {
        this.totalPage = i2;
    }

    protected abstract boolean showNextPageData(ArrayList<?> arrayList);

    public void showPageOne() {
        showPageOne(false);
    }

    protected abstract ArrayList<?> toList(HttpResponse httpResponse);

    public synchronized void tryShowNextPage() {
        this.isPaging = true;
        if (this.loadedLastPage) {
            if (OKLog.D) {
                OKLog.v(TAG, "loadedLast Page " + this.loadedLastPage);
            }
            return;
        }
        ArrayList<?> arrayList = this.nextItemList;
        if (arrayList == null) {
            requestNextPage();
        } else {
            loadNextPage(arrayList, true);
        }
    }

    public void showPageOne(boolean z) {
        resetData();
        tryShowNextPage();
    }

    public ListViewNextPageLoader(IMyActivity iMyActivity, ListView listView, String str, JSONObject jSONObject) {
        this(iMyActivity, listView, str);
        this.params = jSONObject;
    }

    public ListViewNextPageLoader(IMyActivity iMyActivity, ListView listView, String str, ArrayList<?> arrayList, int i2) {
        this(iMyActivity, listView, str);
        this.showItemList.addAll(arrayList);
        this.pageNo += i2;
    }

    public ListViewNextPageLoader(IMyActivity iMyActivity, ListView listView, String str, JSONObject jSONObject, ArrayList<?> arrayList, int i2) {
        this(iMyActivity, listView, str, arrayList, i2);
        this.params = jSONObject;
    }
}

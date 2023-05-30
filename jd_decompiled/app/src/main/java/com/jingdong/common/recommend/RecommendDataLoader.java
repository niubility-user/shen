package com.jingdong.common.recommend;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.ListView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.network.JDNetworkHelper;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.frame.IDestroyListener;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.recommend.entity.RecommendCouponEvent;
import com.jingdong.common.recommend.entity.RecommendData;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.entity.VideoPlayUrl;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.b;
import com.jingdong.jdsdk.network.toolbox.CacheResponseChecker;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class RecommendDataLoader implements HttpGroup.OnAllListener, IDestroyListener {
    public static String EXT_RECOMMEND_PARAM = "extRecommendParam";
    public static String EXT_RECOMMEND_POSITION = "curPosOut";
    private static final String FUNCTIONID = "uniformRecommend";
    private static final String TAG = "RecommendDataLoader";
    public static final int USE_ASSET_ONLY = 2;
    public static final int USE_CACHE_BOTH = 3;
    public static final int USE_NET_ONLY = 1;
    public int businessFrom;
    private CommonListener commonListener;
    public int filteredPages;
    private Handler handler;
    public boolean hasInit;
    protected HttpGroup httpGroup;
    public boolean isDestoryed;
    private int isEnableCache;
    private boolean isFromCach;
    public boolean isGene;
    public boolean isInsertHomeData;
    private boolean isLoading;
    public boolean isPaging;
    public boolean isRequestInterActive;
    private boolean loadedLastPage;
    public HashMap<Integer, Boolean> loadedMap;
    private AbsListView.OnScrollListener mCallbackScrollListener;
    public int mGeneTabIndex;
    private RecommendConfig mRecommendConfig;
    private IMyActivity myActivity;
    private ArrayList<?> nextItemList;
    public int nextPage;
    protected int pageNo;
    protected String pageRecommendParam;
    protected int pageSize;
    protected String pageSizeParamKey;
    protected JSONObject params;
    private RecommendOtherData recommendOtherData;
    private HttpRequest request;
    protected ArrayList<Object> showItemList;
    private boolean showLoading;
    String[] tempString;
    private boolean useCacheData;

    /* loaded from: classes6.dex */
    public interface OnRecommendCouponListener {
        void onSucceed(RecommendCouponEvent recommendCouponEvent);
    }

    /* loaded from: classes6.dex */
    public interface OnRecommendGetVideoUrlListener {
        void onError();

        void onSucceed(String str, int i2);

        void onSucceed(String str, String str2);
    }

    public RecommendDataLoader(IMyActivity iMyActivity, ListView listView) {
        this.showItemList = new ArrayList<>();
        this.nextItemList = null;
        this.loadedLastPage = false;
        this.isPaging = true;
        this.isLoading = false;
        this.isDestoryed = false;
        this.pageSizeParamKey = "pageSize";
        this.pageRecommendParam = "recommendParam";
        this.pageNo = 1;
        this.pageSize = 10;
        this.mCallbackScrollListener = null;
        this.loadedMap = new HashMap<>();
        this.showLoading = false;
        this.mGeneTabIndex = 0;
        this.isFromCach = false;
        this.hasInit = false;
        this.isInsertHomeData = false;
        this.isRequestInterActive = false;
        this.useCacheData = true;
        this.isEnableCache = 0;
        this.tempString = new String[]{"recommend_one.json", "recommend_two.json", "recommend_three.json", "recommend_four.json"};
        this.myActivity = iMyActivity;
        this.handler = iMyActivity.getHandler();
        iMyActivity.addDestroyListener(this);
        this.httpGroup = this.myActivity.getHttpGroupaAsynPool();
        if (listView != null) {
            listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.recommend.RecommendDataLoader.1
                {
                    RecommendDataLoader.this = this;
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                    ArrayList<Object> arrayList;
                    if (absListView.getAdapter() != null && (arrayList = RecommendDataLoader.this.showItemList) != null && arrayList.size() > 0 && i4 - i2 <= i3 * 2) {
                        RecommendDataLoader.this.tryShowNextPage();
                    }
                    if (RecommendDataLoader.this.mCallbackScrollListener != null) {
                        RecommendDataLoader.this.mCallbackScrollListener.onScroll(absListView, i2, i3, i4);
                    }
                }

                @Override // android.widget.AbsListView.OnScrollListener
                public void onScrollStateChanged(AbsListView absListView, int i2) {
                    if (RecommendDataLoader.this.mCallbackScrollListener != null) {
                        RecommendDataLoader.this.mCallbackScrollListener.onScrollStateChanged(absListView, i2);
                    }
                }
            });
        }
    }

    static void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (Exception unused) {
        }
    }

    public static String getMockString(String str) {
        BufferedReader bufferedReader;
        InputStream inputStream = null;
        try {
            InputStream open = JdSdk.getInstance().getApplicationContext().getResources().getAssets().open(str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(open));
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine.trim());
                        } else {
                            close(open);
                            close(bufferedReader);
                            String sb2 = sb.toString();
                            close(open);
                            close(bufferedReader);
                            return sb2;
                        }
                    }
                } catch (Throwable unused) {
                    inputStream = open;
                    close(inputStream);
                    close(bufferedReader);
                    return "";
                }
            } catch (Throwable unused2) {
                bufferedReader = null;
            }
        } catch (Throwable unused3) {
            bufferedReader = null;
        }
    }

    public static HttpSetting getVideoPlayUrl(final RecommendVideo recommendVideo, final int i2, final OnRecommendGetVideoUrlListener onRecommendGetVideoUrlListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.putJsonParam(CartConstant.KEY_CART_VID, recommendVideo != null ? recommendVideo.videoId : "");
        httpSetting.putJsonParam("from", "0");
        httpSetting.putJsonParam("type", "1");
        httpSetting.setFunctionId("discVideoPlayAddress");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.recommend.RecommendDataLoader.7
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (OKLog.D) {
                    OKLog.d(RecommendDataLoader.TAG, "response:" + fastJsonObject.toString());
                }
                List<VideoPlayUrl> parseArray = JDJSON.parseArray(fastJsonObject.optString("data"), VideoPlayUrl.class);
                if (parseArray != null) {
                    if (parseArray.isEmpty()) {
                        onRecommendGetVideoUrlListener.onError();
                        return;
                    }
                    for (VideoPlayUrl videoPlayUrl : parseArray) {
                        if (TextUtils.equals(videoPlayUrl.definition, "\u9ad8\u6e05")) {
                            RecommendVideo recommendVideo2 = recommendVideo;
                            if (recommendVideo2 != null) {
                                recommendVideo2.playUrl = videoPlayUrl.main_url;
                            }
                            onRecommendGetVideoUrlListener.onSucceed(videoPlayUrl.main_url, i2);
                            return;
                        }
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.D) {
                    OKLog.d(RecommendDataLoader.TAG, "error:" + httpError.toString());
                }
                onRecommendGetVideoUrlListener.onError();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        return httpSetting;
    }

    private boolean hasCacheData(String str) {
        try {
            return JDNetworkHelper.getGlobalJDRequestQueue().getCache().get(str) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isUseAsset() {
        int i2 = this.isEnableCache;
        return i2 == 2 || i2 == 3;
    }

    private boolean isUseNetCache() {
        int i2 = this.isEnableCache;
        return i2 == 1 || i2 == 3;
    }

    private void loadNextPage(final ArrayList<?> arrayList, final RecommendOtherData recommendOtherData, final boolean z) {
        judgeIsLastPage(arrayList, recommendOtherData);
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendDataLoader.2
            {
                RecommendDataLoader.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                RecommendDataLoader.this.loadNextPageWithoutHandler(arrayList, recommendOtherData, z);
            }
        });
    }

    public void loadNextPageWithoutHandler(ArrayList<?> arrayList, RecommendOtherData recommendOtherData, boolean z) {
        if (this.isDestoryed) {
            return;
        }
        if (showNextPageData(arrayList, recommendOtherData)) {
            this.nextItemList = null;
            if (arrayList != null) {
                this.showItemList.addAll(arrayList);
            }
        }
        onOnePageEnd(this.loadedLastPage);
        if (this.loadedLastPage || z) {
            return;
        }
        int i2 = this.nextPage;
        if (i2 > 1) {
            this.pageNo = i2;
        } else {
            this.pageNo++;
        }
    }

    public void onePageErr() {
        if (this.isFromCach) {
            return;
        }
        this.isLoading = false;
        this.isPaging = true;
        onOnePageErr();
    }

    private synchronized void requestNextPage() {
        if (this.isLoading) {
            return;
        }
        this.isLoading = true;
        onOnePageLoading();
        HashMap hashMap = new HashMap();
        hashMap.put(RecommendConstant.RECOM_pageNoParamKey, Integer.valueOf(this.pageNo));
        try {
            getParams().put(RecommendConstant.RECOM_pageNoParamKey, this.pageNo);
            getParams().put(this.pageSizeParamKey, this.pageSize);
            try {
                if (this.params.optInt("source") == 9) {
                    RecommendConfig recommendConfig = this.mRecommendConfig;
                    if (recommendConfig != null && !TextUtils.isEmpty(recommendConfig.getNetExtentParam())) {
                        getParams().put(this.pageRecommendParam, this.mRecommendConfig.getNetExtentParam());
                    } else {
                        getParams().remove(this.pageRecommendParam);
                    }
                    if (RecommendUtils.getRecommendTestSwitch()) {
                        getParams().put("tryMode", RecommendUtils.getRecommendTestSwitch() ? "1" : "0");
                    }
                }
            } catch (JSONException e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            int i2 = this.pageNo;
            if (i2 == 1) {
                this.filteredPages = 0;
            } else if (this.filteredPages == 0) {
                this.filteredPages = i2 - 1;
            }
            getParams().put("filteredPages", this.filteredPages);
            getParams().put(RecommendSPUtils.SP_VEROLD, RecommendSPUtils.getString(RecommendSPUtils.SP_VEROLD, "2"));
            int currentOverseasArea = JDOverseasUtil.getCurrentOverseasArea();
            JSONObject params = getParams();
            if (currentOverseasArea < 0) {
                currentOverseasArea = 0;
            }
            params.put("areaCode", currentOverseasArea);
            getParams().put("curPos", getParams().has(EXT_RECOMMEND_POSITION) ? getParams().get(EXT_RECOMMEND_POSITION) : RecommendUtils.getCurrentAddress());
        } catch (JSONException e3) {
            if (OKLog.E) {
                OKLog.e(TAG, "JSONException -->> ", e3);
            }
        }
        final HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(RecommendFunctionId.getFunctionId(this.businessFrom, getParams()));
        httpSetting.setEncryptBody(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setJsonParams(getParams());
        httpSetting.setMoreParams(hashMap);
        if (this.isGene) {
            this.mGeneTabIndex = 0;
            try {
                this.mGeneTabIndex = getParams().getInt("geneTabIndex");
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
        }
        httpSetting.setHost(Configuration.getPersonalHost());
        if (this.showLoading && this.pageNo == 1) {
            httpSetting.setEffect(1);
        } else {
            httpSetting.setEffect(0);
        }
        if (this.pageNo == 1 && this.params.optInt("source") == 9) {
            String str = "recommend" + this.params.optInt("tabId", -1000) + this.pageNo;
            if (!RecommendFunctionId.FUNCTIONID.equals(httpSetting.getFunctionId())) {
                str = str + CartConstant.KEY_YB_INFO_LINK + httpSetting.getFunctionId();
            }
            if (isUseNetCache() && "1".equals(JDMobileConfig.getInstance().getConfig("JDUniformRecommend", "uniformRecommendCache", "uniformRecommendCache", "1"))) {
                if (this.useCacheData) {
                    httpSetting.setCacheMode(4);
                } else {
                    httpSetting.setCacheMode(2);
                }
                httpSetting.setForeverCache(true);
                httpSetting.setTopPriority(true);
                httpSetting.setMd5(str);
            }
        }
        if ((this.params.optInt("source") == 9 || this.params.optInt("source", -1) == 0) && this.isEnableCache > 0 && httpSetting.getQueryParam() != null) {
            httpSetting.getQueryParam().put("recommendSource", String.valueOf(this.params.optInt("source")));
        }
        httpSetting.setCacheResponseChecker(new CacheResponseChecker<JDJSONObject>() { // from class: com.jingdong.common.recommend.RecommendDataLoader.3
            {
                RecommendDataLoader.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.CacheResponseChecker
            public boolean shouldCache(JDJSONObject jDJSONObject) {
                JDJSONArray optJSONArray;
                if (jDJSONObject == null) {
                    return false;
                }
                Map<String, Object> moreParams = httpSetting.getMoreParams();
                return ((moreParams.get(RecommendConstant.RECOM_pageNoParamKey) instanceof Integer ? ((Integer) moreParams.get(RecommendConstant.RECOM_pageNoParamKey)).intValue() : 0) != 1 || (optJSONArray = jDJSONObject.optJSONArray("wareInfoList")) == null || optJSONArray.isEmpty()) ? false : true;
            }
        });
        httpSetting.setListener(this);
        if (RecommendUtils.shieldRequest(this.businessFrom)) {
            callEmptyData();
            return;
        }
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            this.request = httpGroup.add(httpSetting);
        }
    }

    private void resetData() {
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            httpRequest.stop();
        }
        this.isInsertHomeData = false;
        this.loadedLastPage = false;
        this.loadedMap.clear();
        this.pageNo = 1;
        this.nextItemList = null;
        this.isPaging = true;
        this.isLoading = false;
        this.showItemList.clear();
    }

    public void addHttpRequest(HttpSetting httpSetting) {
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            this.request = httpGroup.add(httpSetting);
        }
    }

    public void bindData(ArrayList<?> arrayList, int i2) {
        resetData();
        if ((arrayList == null || arrayList.size() == 0) && i2 == 0) {
            showPageOne();
            return;
        }
        this.showItemList.addAll(arrayList);
        if (judgeIsLastPage(arrayList, null)) {
            onOnePageEnd(true);
        }
        this.pageNo += i2;
    }

    public void callEmptyData() {
        OKLog.d("RecommendCache", "-\u8d70\u672c\u5730\u515c\u5e95--");
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(RecommendConstant.RECOM_pageNoParamKey, 1);
            HttpResponse httpResponse = new HttpResponse(hashMap);
            httpResponse.setFastJsonObject(JDJSON.parseObject(getMockString("{\n  \"code\": \"0\",\n  \"waterFallStrategy\": 1,\n  \"isEnableDarkMode\": \"1\",\n  \"wareInfoList\": [],\n  \"isLocalJson\": \"1\"\n}")));
            onEnd(httpResponse);
        } catch (Exception unused) {
        }
    }

    public int getFilteredPages() {
        return this.filteredPages;
    }

    public boolean getFromCache() {
        return this.isFromCach;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getPageNo() {
        return this.pageNo;
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

    public ArrayList<Object> getShowItemList() {
        return this.showItemList;
    }

    public boolean hasData() {
        ArrayList<Object> arrayList = this.showItemList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isLoadedLastPage() {
        return this.loadedLastPage;
    }

    public boolean isReportException() {
        JSONObject jSONObject = this.params;
        return jSONObject != null && jSONObject.optInt("source") == 9 && RecommendConstant.errorReport;
    }

    protected boolean judgeIsLastPage(ArrayList<?> arrayList, RecommendOtherData recommendOtherData) {
        if (this.pageNo == 1 && recommendOtherData != null && recommendOtherData.getWareInfoReasons() != null && recommendOtherData.getWareInfoReasons().size() > 0) {
            this.loadedLastPage = false;
            return false;
        }
        if ((arrayList.size() == 0 && this.showItemList.size() != 0) || (recommendOtherData != null && recommendOtherData.isReachEnd)) {
            this.loadedLastPage = true;
        } else {
            this.loadedLastPage = false;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "judgeIsLastPage:" + this.loadedLastPage);
        }
        return this.loadedLastPage;
    }

    protected void justShowCacheData() {
    }

    @Override // com.jingdong.common.frame.IDestroyListener
    public void onDestroy() {
        this.isDestoryed = true;
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
    public void onEnd(final HttpResponse httpResponse) {
        if (this.isDestoryed) {
            this.isLoading = false;
            return;
        }
        if (httpResponse.isCache()) {
            this.isFromCach = true;
        }
        if (this.myActivity != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("isCache", this.isFromCach);
                jSONObject.put("pageNumber", this.pageNo);
                b.a(this.myActivity.getThisActivity(), RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, RecommendConstant.RECOMMEND_FUNCTION_ID, 0, null, jSONObject.toString());
            } catch (Exception unused) {
            }
        }
        if (httpResponse.getCode() != 0 && isReportException()) {
            RecommendUtil.reportException("uniformRecommend.ComponentsCodeIsNotZero", "\u7f51\u7edc\u8fd4\u56de\u6b63\u5e38\u4f46code!=0,code:" + httpResponse.getCode());
        }
        toList(httpResponse);
        httpResponse.getMoreParams();
        this.handler.post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0072: INVOKE 
              (wrap: android.os.Handler : 0x006b: IGET (r9v0 'this' com.jingdong.common.recommend.RecommendDataLoader A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:15) com.jingdong.common.recommend.RecommendDataLoader.handler android.os.Handler)
              (wrap: java.lang.Runnable : 0x006f: CONSTRUCTOR 
              (r9v0 'this' com.jingdong.common.recommend.RecommendDataLoader A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.util.Map A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r10v0 'httpResponse' com.jingdong.jdsdk.network.toolbox.HttpResponse A[DONT_INLINE])
              (r0 I:com.jingdong.common.recommend.entity.RecommendData A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.recommend.RecommendDataLoader, java.util.Map, com.jingdong.jdsdk.network.toolbox.HttpResponse, com.jingdong.common.recommend.entity.RecommendData):void (m), WRAPPED] call: com.jingdong.common.recommend.RecommendDataLoader.4.<init>(com.jingdong.common.recommend.RecommendDataLoader, java.util.Map, com.jingdong.jdsdk.network.toolbox.HttpResponse, com.jingdong.common.recommend.entity.RecommendData):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:15) in method: com.jingdong.common.recommend.RecommendDataLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void, file: classes6.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            boolean r0 = r9.isDestoryed
            if (r0 == 0) goto L8
            r10 = 0
            r9.isLoading = r10
            return
        L8:
            boolean r0 = r10.isCache()
            if (r0 == 0) goto L11
            r0 = 1
            r9.isFromCach = r0
        L11:
            com.jingdong.common.frame.IMyActivity r0 = r9.myActivity
            if (r0 == 0) goto L3d
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L3c
            r0.<init>()     // Catch: java.lang.Exception -> L3c
            java.lang.String r1 = "isCache"
            boolean r2 = r9.isFromCach     // Catch: java.lang.Exception -> L3c
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L3c
            java.lang.String r1 = "pageNumber"
            int r2 = r9.pageNo     // Catch: java.lang.Exception -> L3c
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L3c
            java.lang.String r8 = r0.toString()     // Catch: java.lang.Exception -> L3c
            com.jingdong.common.frame.IMyActivity r0 = r9.myActivity     // Catch: java.lang.Exception -> L3c
            android.app.Activity r3 = r0.getThisActivity()     // Catch: java.lang.Exception -> L3c
            java.lang.String r4 = com.jingdong.common.recommend.RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE     // Catch: java.lang.Exception -> L3c
            java.lang.String r5 = com.jingdong.common.recommend.RecommendConstant.RECOMMEND_FUNCTION_ID     // Catch: java.lang.Exception -> L3c
            r6 = 0
            r7 = 0
            com.jingdong.jdsdk.mta.b.a(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L3c
            goto L3d
        L3c:
        L3d:
            int r0 = r10.getCode()
            if (r0 == 0) goto L63
            boolean r0 = r9.isReportException()
            if (r0 == 0) goto L63
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "\u7f51\u7edc\u8fd4\u56de\u6b63\u5e38\u4f46code!=0,code:"
            r0.append(r1)
            int r1 = r10.getCode()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "uniformRecommend.ComponentsCodeIsNotZero"
            com.jingdong.common.recommend.forlist.RecommendUtil.reportException(r1, r0)
        L63:
            com.jingdong.common.recommend.entity.RecommendData r0 = r9.toList(r10)
            java.util.Map r1 = r10.getMoreParams()
            android.os.Handler r2 = r9.handler
            com.jingdong.common.recommend.RecommendDataLoader$4 r3 = new com.jingdong.common.recommend.RecommendDataLoader$4
            r3.<init>()
            r2.post(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.RecommendDataLoader.onEnd(com.jingdong.jdsdk.network.toolbox.HttpResponse):void");
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
    public void onError(HttpError httpError) {
        try {
            if (isReportException() && httpError != null && httpError.getHttpResponse() != null && httpError.getJsonCode() != 0) {
                RecommendUtil.reportException("uniformRecommend.ComponentsCodeIsNotZero", httpError.toString());
            }
        } catch (Exception unused) {
        }
        if (this.myActivity != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("isCache", this.isFromCach);
                jSONObject.put("pageNumber", this.pageNo);
                b.a(this.myActivity.getThisActivity(), RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, RecommendConstant.RECOMMEND_FUNCTION_ID, -1, httpError, jSONObject.toString());
            } catch (Exception unused2) {
            }
        }
        if (this.isFromCach) {
            this.isLoading = false;
            this.isPaging = true;
            this.isFromCach = false;
            return;
        }
        this.isLoading = false;
        this.handler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendDataLoader.5
            {
                RecommendDataLoader.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                RecommendDataLoader.this.onOnePageErr();
            }
        });
        this.isPaging = true;
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

    public void recommendClickRequest(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        httpSetting.setPost(false);
        httpSetting.setUrl(str);
        httpSetting.setType(6000);
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            this.request = httpGroup.add(httpSetting);
        }
    }

    public void recommendFeedBack(String str, int i2, int i3, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sku", str);
            jSONObject.put("dataType", i2);
            jSONObject.put("opType", i3);
            jSONObject.put("reasonList", str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "-100";
            }
            jSONObject.put("matrt", str3);
            if (TextUtils.isEmpty(str4)) {
                str4 = "-100";
            }
            jSONObject.put("itemid", str4);
            if (TextUtils.isEmpty(str5)) {
                str5 = "-100";
            }
            jSONObject.put("cvgsku", str5);
            if (TextUtils.isEmpty(str6)) {
                str6 = "-100";
            }
            jSONObject.put("skuSource", str6);
            if (TextUtils.isEmpty(str7)) {
                str7 = "-100";
            }
            jSONObject.put("type", str7);
            jSONObject.put("p", str8);
        } catch (JSONException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("recomFeedback");
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.recommend.RecommendDataLoader.6
            {
                RecommendDataLoader.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (OKLog.D) {
                    OKLog.d(RecommendDataLoader.TAG, "response:" + httpResponse.getJSONObject().toString());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.D) {
                    OKLog.d(RecommendDataLoader.TAG, "error:" + httpError.toString());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i4, int i5) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            this.request = httpGroup.add(httpSetting);
        }
    }

    public void requestInterActive(RecommendProduct recommendProduct, final String[] strArr) {
        if (recommendProduct == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(recommendProduct.wareId);
        try {
            jSONObject.put("source", 38);
            jSONObject.put("seedPage", recommendProduct.seedPage);
            jSONObject.put("seedIndex", recommendProduct.seedIndex);
            jSONObject.put("maxPage", this.pageNo);
            jSONObject.put("skus", jSONArray);
            jSONObject.put("seedSource", recommendProduct.source);
            if (TextUtils.isEmpty(recommendProduct.spu)) {
                jSONObject.put("seedSpu", "0");
            } else {
                jSONObject.put("seedSpu", recommendProduct.spu);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(FUNCTIONID);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setEffect(0);
        httpSetting.setListener(new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.recommend.RecommendDataLoader.8
            {
                RecommendDataLoader.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                ArrayList<?> recommendList;
                RecommendDataLoader.this.isRequestInterActive = true;
                RecommendData parseJsonToList = RecommendUtils.parseJsonToList(httpResponse.getFastJsonObject(), strArr);
                RecommendDataLoader.this.isRequestInterActive = false;
                if (parseJsonToList == null || (recommendList = parseJsonToList.getRecommendList()) == null || recommendList.isEmpty()) {
                    return;
                }
                final RecommendEvent recommendEvent = new RecommendEvent(RecommendEvent.onInterActiveEnd);
                recommendEvent.param.put("interActive", recommendList);
                RecommendDataLoader.this.handler.post(new Runnable() { // from class: com.jingdong.common.recommend.RecommendDataLoader.8.1
                    {
                        AnonymousClass8.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        RecommendDataLoader.this.commonListener.onResult(recommendEvent);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroup httpGroup = this.httpGroup;
        if (httpGroup != null) {
            this.request = httpGroup.add(httpSetting);
        }
    }

    public void setBusinessFrom(int i2) {
        this.businessFrom = i2;
    }

    public void setCommonListener(CommonListener commonListener) {
        this.commonListener = commonListener;
    }

    public void setEnableCache(int i2) {
        this.isEnableCache = i2;
    }

    public void setFilteredPages(int i2) {
        this.filteredPages = i2;
    }

    public void setLoadedLastPage(boolean z) {
        this.loadedLastPage = z;
    }

    public void setNextPage(int i2) {
        this.nextPage = i2;
    }

    public void setPageNo(int i2) {
        this.pageNo = i2;
    }

    public void setPageSize(int i2) {
        this.pageSize = i2;
    }

    public void setParams(JSONObject jSONObject) {
        this.params = jSONObject;
    }

    public void setRecommendConfig(RecommendConfig recommendConfig) {
        this.mRecommendConfig = recommendConfig;
    }

    public void setScrollListenerCallback(AbsListView.OnScrollListener onScrollListener) {
        this.mCallbackScrollListener = onScrollListener;
    }

    public void setShowLoading(boolean z) {
        this.showLoading = z;
    }

    public void setUseCacheData(boolean z) {
        this.useCacheData = z;
    }

    protected abstract boolean showNextPageData(ArrayList<?> arrayList, RecommendOtherData recommendOtherData);

    public void showPageOne() {
        resetData();
        tryShowNextPage();
    }

    public void startRequestRecommend() {
        if (this.myActivity != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("pageNumber", this.pageNo);
                b.c(this.myActivity.getThisActivity(), RecommendConstant.DYNAMIC_RECOMMEND_SYSTEMCODE, RecommendConstant.RECOMMEND_FUNCTION_ID, jSONObject.toString());
            } catch (Exception unused) {
            }
        }
    }

    protected abstract RecommendData toList(HttpResponse httpResponse);

    public synchronized void tryShowNextPage() {
        if (!this.loadedLastPage && !this.isLoading && !this.isDestoryed && this.isPaging) {
            this.isPaging = true;
            startRequestRecommend();
            ArrayList<?> arrayList = this.nextItemList;
            if (arrayList == null) {
                requestNextPage();
            } else {
                loadNextPage(arrayList, this.recommendOtherData, true);
            }
        }
    }

    public void userAssetFile(boolean z) {
        OKLog.d("RecommendCache", "-\u8d70\u672c\u5730\u515c\u5e95--");
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(RecommendConstant.RECOM_pageNoParamKey, 1);
            HttpResponse httpResponse = new HttpResponse(hashMap);
            httpResponse.setFastJsonObject(JDJSON.parseObject(getMockString(this.tempString[new Random().nextInt(4)])));
            httpResponse.setCache(z);
            onEnd(httpResponse);
        } catch (Exception unused) {
        }
    }

    static void close(Reader reader) {
        try {
            reader.close();
        } catch (Exception unused) {
        }
    }

    public RecommendDataLoader(IMyActivity iMyActivity, ListView listView, JSONObject jSONObject) {
        this(iMyActivity, listView);
        this.params = jSONObject;
    }
}

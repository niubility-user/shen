package com.jd.viewkit.templates.container;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitFloorAcrossListener;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.helper.JDViewKitPageRefreshModel;
import com.jd.viewkit.helper.JDViewKitViewListenerParamsModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.JDViewKitStatefulView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.templates.view.helper.eventcallback.JDViewKitEventAbstractCallBack;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitMultiTabView extends JDViewKitBaseLayout<JDViewKitVirtualView> {
    private static String NormalStr = "0";
    private static String SelectStr = "1";
    private static String SubFloorListStr = "subFloorList";
    private static String SubFlootListStatusFail = "fail";
    private static String SubFlootListStatusLoading = "loading";
    private static String SubFlootListStatusStr = "subFlootListStatus";
    private static String SubFlootListStatusSuccess = "success";
    private static final String TAG = "JDViewKitMultiTabView";
    private MyRecyclerAdapter adapter;
    private boolean isScrolled;
    private int loadFloorHeight;
    private JDViewKitFloorModel mFloorModel;
    private boolean mIsLayout;
    private int mSelectNum;
    private RecyclerView mainRecyclerView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class MultiTabViewListenerCallBack extends JDViewKitEventAbstractCallBack {
        public JDViewKitDataSourceModel dataSourceModel;
        public JDViewKitFloorModel floorModel;
        public int selectIndex;
        public JDViewKitVirtualView virtualView;

        public MultiTabViewListenerCallBack(int i2, JDViewKitFloorModel jDViewKitFloorModel, JDViewKitDataSourceModel jDViewKitDataSourceModel, JDViewKitVirtualView jDViewKitVirtualView) {
            super(null, null, jDViewKitVirtualView, jDViewKitDataSourceModel, null, JDViewKitEventAbstractCallBack.CallBackType_Single, null);
            this.selectIndex = -1;
            this.selectIndex = i2;
            this.floorModel = jDViewKitFloorModel;
            this.dataSourceModel = jDViewKitDataSourceModel;
            this.virtualView = jDViewKitVirtualView;
        }

        public void callBackPageRefresh() {
            try {
                JDViewKitFloorModel jDViewKitFloorModel = this.floorModel;
                if (jDViewKitFloorModel == null || jDViewKitFloorModel.getRootVirtualView() == null || this.floorModel.getRootVirtualView().getServiceModel() == null || this.floorModel.getRootVirtualView().getServiceModel().getRefreshListener() == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    if (JDViewKitMultiTabView.this.mFloorModel != null) {
                        jSONObject.put("position", JDViewKitMultiTabView.this.mFloorModel.getFirstPos());
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.floorModel.getRootVirtualView().getServiceModel().getRefreshListener().pageRefresh(new JDViewKitPageRefreshModel(JDViewKitPageRefreshModel.pageRefreshTypeSelectTab, new JDViewKitEventModel(jSONObject.toString())), null);
            } catch (Throwable unused) {
            }
        }

        @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
        public void failCallBack(int i2, Map<String, Object> map, String str) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel;
            if (this.floorModel == null || (jDViewKitDataSourceModel = this.dataSourceModel) == null || this.virtualView == null) {
                return;
            }
            jDViewKitDataSourceModel.getDataMap().put(JDViewKitMultiTabView.SubFlootListStatusStr, JDViewKitMultiTabView.SubFlootListStatusFail);
            callBackPageRefresh();
        }

        @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
        public void successCallBack(int i2, Map<String, Object> map, String str) {
            if (this.floorModel != null && this.dataSourceModel != null && this.virtualView != null) {
                try {
                    JSONObject JSONObject = JSONTool.JSONObject(str);
                    String string = JSONTool.getString(JSONObject, "subCode");
                    if (string == null || !string.equals("0")) {
                        this.dataSourceModel.getDataMap().put(JDViewKitMultiTabView.SubFlootListStatusStr, JDViewKitMultiTabView.SubFlootListStatusFail);
                    } else {
                        JSONArray jSONArray = JSONTool.getJSONArray(JSONObject, "subFloorList");
                        JSONObject jSONObject = JSONTool.getJSONObject(JSONObject, "dslMap");
                        if (jSONArray != null) {
                            this.dataSourceModel.getDataMap().put(JDViewKitMultiTabView.SubFloorListStr, JSONTool.getList(jSONArray));
                            if (jSONObject != null) {
                                this.virtualView.getServiceModel().getVirtualViewManager().dslMap2Virtual(jSONObject.toString(), this.virtualView.getServiceModel());
                            }
                        }
                        this.dataSourceModel.getDataMap().put(JDViewKitMultiTabView.SubFlootListStatusStr, JDViewKitMultiTabView.SubFlootListStatusSuccess);
                    }
                    callBackPageRefresh();
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
        public List<JDViewKitDataSourceModel> modelList;
        private int typeInt = -1;
        private Map<String, String> typeMap = new ArrayMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes18.dex */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public JDViewKitBaseLayout contentLayout;

            public ViewHolder(JDViewKitBaseLayout jDViewKitBaseLayout) {
                super(jDViewKitBaseLayout);
                this.contentLayout = jDViewKitBaseLayout;
            }
        }

        public MyRecyclerAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<JDViewKitDataSourceModel> list = this.modelList;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            String dslId = this.modelList.get(i2).getDslId();
            String str = this.typeMap.get(dslId);
            if (StringTool.isEmpty(str)) {
                this.typeInt++;
                str = "" + this.typeInt;
                this.typeMap.put(dslId, str);
            }
            return Integer.parseInt(str);
        }

        public String getTemplateByViewType(int i2) {
            String str = "" + i2;
            for (String str2 : this.typeMap.keySet()) {
                if (this.typeMap.get(str2).equals(str)) {
                    return str2;
                }
            }
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i2) {
            JDViewKitBaseLayout jDViewKitBaseLayout = viewHolder.contentLayout;
            if (jDViewKitBaseLayout instanceof JDViewKitStatefulView) {
                ((JDViewKitStatefulView) jDViewKitBaseLayout).setDataSourceModel(this.modelList.get(i2), JDViewKitMultiTabView.this.mIsLayout);
                viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jd.viewkit.templates.container.JDViewKitMultiTabView.MyRecyclerAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDViewKitMultiTabView.this.selectTab(i2);
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitMultiTabView.this).dslsMap.get(getTemplateByViewType(i2)), JDViewKitMultiTabView.this.getChannelModel()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class RecycleViewDivider extends RecyclerView.ItemDecoration {
        RecycleViewDivider() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (view.getTag() == null || !(view.getTag() instanceof Rect)) {
                return;
            }
            rect.set((Rect) view.getTag());
        }
    }

    public JDViewKitMultiTabView(@NonNull Context context) {
        super(context);
        this.isScrolled = true;
        this.loadFloorHeight = R2.anim.pop_left_top_out;
        this.mContext = context;
        initView();
    }

    private void asyncSelet(int i2, JDViewKitDataSourceModel jDViewKitDataSourceModel, JDViewKitVirtualView jDViewKitVirtualView) {
        JDViewKitVirtualEvent virtualEventByType;
        if (jDViewKitDataSourceModel == null || jDViewKitVirtualView == null || !isAsync(this.dataSourceMap) || getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals(SubFlootListStatusSuccess) || (virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) == null) {
            return;
        }
        Object obj = jDViewKitDataSourceModel.getDataMap().get("asyncTabParam");
        Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey());
        if (jumpParams == null || obj == null) {
            return;
        }
        String jsonString = JSONTool.getJsonString(jumpParams);
        String jsonString2 = JSONTool.getJsonString((Map) obj);
        JDViewKitEventModel jDViewKitEventModel = new JDViewKitEventModel(jsonString);
        if (StringTool.isNotEmpty(jsonString2)) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("asyncTabParam", jsonString2);
            jDViewKitEventModel.setOtherParams(hashMap);
        }
        JDViewKitViewListenerParamsModel jDViewKitViewListenerParamsModel = new JDViewKitViewListenerParamsModel(JDViewKitViewListenerParamsModel.paramsModelTypeMultiTab, jDViewKitEventModel);
        MultiTabViewListenerCallBack multiTabViewListenerCallBack = new MultiTabViewListenerCallBack(i2, this.floorModel, jDViewKitDataSourceModel, this.virtualView);
        if (this.floorModel.getViewListener() != null) {
            jDViewKitDataSourceModel.getDataMap().put(SubFlootListStatusStr, SubFlootListStatusLoading);
            this.floorModel.getViewListener().floorRefresh(jDViewKitViewListenerParamsModel, multiTabViewListenerCallBack);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getMultiMabLoadStr(com.jd.viewkit.helper.JDViewKitFloorModel r4) {
        /*
            if (r4 == 0) goto L31
            com.jd.viewkit.templates.model.JDViewKitVirtualView r0 = r4.getRootVirtualView()
            if (r0 == 0) goto L31
            com.jd.viewkit.templates.model.JDViewKitVirtualView r0 = r4.getRootVirtualView()
            com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel r0 = r0.getServiceModel()
            if (r0 == 0) goto L31
            com.jd.viewkit.templates.model.JDViewKitVirtualView r0 = r4.getRootVirtualView()
            com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel r0 = r0.getServiceModel()
            com.jd.viewkit.thirdinterface.JDViewKitEventService r0 = r0.getEventService()
            if (r0 == 0) goto L31
            com.jd.viewkit.templates.model.JDViewKitVirtualView r4 = r4.getRootVirtualView()     // Catch: java.lang.Throwable -> L31
            com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel r4 = r4.getServiceModel()     // Catch: java.lang.Throwable -> L31
            com.jd.viewkit.thirdinterface.JDViewKitEventService r4 = r4.getEventService()     // Catch: java.lang.Throwable -> L31
            java.lang.String r4 = r4.getI18nString()     // Catch: java.lang.Throwable -> L31
            goto L32
        L31:
            r4 = 0
        L32:
            boolean r0 = com.jd.viewkit.tool.StringTool.isNotEmpty(r4)
            if (r0 == 0) goto L65
            java.lang.String r0 = "th"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L47
            java.lang.String r4 = "\u0e44\u0e21\u0e48\u0e1e\u0e1a\u0e02\u0e49\u0e2d\u0e21\u0e39\u0e25\u0e43\u0e19\u0e02\u0e13\u0e30\u0e19\u0e35\u0e49"
            java.lang.String r0 = "\u0e01\u0e32\u0e23\u0e42\u0e2b\u0e25\u0e14\u0e25\u0e49\u0e21\u0e40\u0e2b\u0e25\u0e27 \u0e42\u0e1b\u0e23\u0e14\u0e25\u0e2d\u0e07\u0e43\u0e2b\u0e21\u0e48\u0e2d\u0e35\u0e01\u0e04\u0e23\u0e31\u0e49\u0e07"
            java.lang.String r1 = "\u0e01\u0e33\u0e25\u0e31\u0e07\u0e42\u0e2b\u0e25\u0e14"
            goto L6b
        L47:
            java.lang.String r0 = "zh"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L56
            java.lang.String r4 = "\u6682\u65e0\u5185\u5bb9"
            java.lang.String r0 = "\u52a0\u8f7d\u5931\u8d25\uff0c\u70b9\u51fb\u91cd\u8bd5"
            java.lang.String r1 = "\u52a0\u8f7d\u4e2d"
            goto L6b
        L56:
            java.lang.String r0 = "id"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L65
            java.lang.String r4 = "Tidak ada data untuk saat ini."
            java.lang.String r0 = "Mamuat gagal, Silakan klik untuk mengulang."
            java.lang.String r1 = "Memuat\u2026"
            goto L6b
        L65:
            java.lang.String r4 = "There is no data available at the moment."
            java.lang.String r0 = "Loading failed, please try again"
            java.lang.String r1 = "Loading"
        L6b:
            java.lang.String r2 = "{\n  \"dslMap\": {\n    \"multiTabLoadingDsl\": {\n      \"type\": \"view\",\n      \"children\": [\n        {\n          \"type\": \"text\",\n          \"style\": {\n            \"textAlign\":\"center\",\n            \"verticalAlign\":\"center\",\n            \"bold\":0,\n            \"frame\": \"0,0,1125,168\",\n            \"lines\": 0,\n            \"fontSize\": 42,\n            \"color\": \"#9b9b9b\",\n            \"bgColor\": \"#FFFFFF\"\n          },\n          \"valueRefer\": \"$msg\"\n        }\n      ],\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    },\n    \"multiTabFailDsl\": {\n      \"type\": \"view\",\n      \"event\": [\n        {\n          \"type\": \"click\",\n          \"eventKey\": \"666666666\",\n          \"action\": \"MultiTabFail_ActionId\",\n          \"param\": {\n            \"valueRefer\": \"\"\n          }\n        }\n      ],\n      \"children\": [\n        {\n          \"type\": \"text\",\n          \"style\": {\n            \"textAlign\":\"center\",\n            \"verticalAlign\":\"center\",\n            \"bold\":0,\n            \"frame\": \"0,0,1125,168\",\n            \"lines\": 0,\n            \"fontSize\": 42,\n            \"color\": \"#9b9b9b\",\n            \"bgColor\": \"#FFFFFF\"\n          },\n          \"valueRefer\": \"$msg\"\n        }\n      ],\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    },\n    \"multiTabFlatView\":{\n      \"id\": \"flatId\",\n      \"type\": \"flatView\",\n      \"valueRefer\": \"$flatData\",\n      \"dslId\":\"multiTabFlatView\",\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    }\n  },\n  \"loadingFloor\":{\n    \"dslData\": {\n      \"flatData\": [\n        {\n          \"dslId\": \"multiTabLoadingDsl\",\n          \"msg\": \"&loadingMsg&\"\n        }\n      ]\n    },\n    \"dslRoot\": {\n      \"id\": \"flatId\",\n      \"type\": \"flatView\",\n      \"valueRefer\": \"$flatData\",\n      \"dslId\":\"multiTabFlatView\",\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    }\n  },\n  \"noMoreFloor\":{\n    \"dslData\": {\n      \"flatData\": [\n        {\n          \"dslId\": \"multiTabLoadingDsl\",\n          \"msg\": \"&noMoreMsg&\"\n        }\n      ]\n    },\n    \"dslRoot\": {\n      \"id\": \"flatId\",\n      \"type\": \"flatView\",\n      \"valueRefer\": \"$flatData\",\n      \"dslId\":\"multiTabFlatView\",\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    }\n  },\n  \"errorFloor\":{\n    \"dslData\": {\n      \"flatData\": [\n        {\n          \"jumpMap\": {\n            \"666666666\": {\n              \"eventId\": \"MultiTabFail_EventId\"\n            }\n          },\n\n          \"dslId\": \"multiTabFailDsl\",\n          \"msg\": \"&errorMsg&\"\n        }\n      ]\n    },\n    \"dslRoot\": {\n      \"id\": \"flatId\",\n      \"type\": \"flatView\",\n      \"valueRefer\": \"$flatData\",\n      \"dslId\":\"multiTabFlatView\",\n      \"style\": {\n        \"frame\": \"0,0,1125,168\",\n        \"bgColor\": \"#FFFFFF\"\n      }\n    }\n  }\n}"
            java.lang.String r3 = "&loadingMsg&"
            java.lang.String r1 = r2.replace(r3, r1)
            java.lang.String r2 = "&noMoreMsg&"
            java.lang.String r4 = r1.replace(r2, r4)
            java.lang.String r1 = "&errorMsg&"
            java.lang.String r4 = r4.replace(r1, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.viewkit.templates.container.JDViewKitMultiTabView.getMultiMabLoadStr(com.jd.viewkit.helper.JDViewKitFloorModel):java.lang.String");
    }

    private int getScreenHeight() {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
                return displayMetrics.heightPixels;
            }
            return getResources().getDisplayMetrics().heightPixels;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int getSelectSubFloorList(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        String stringValueRef;
        if (list != null && list.size() > 0 && map != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel = list.get(i2);
                JDViewKitVirtualView jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId());
                if (jDViewKitVirtualView != null && (stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) != null && stringValueRef.equals(SelectStr) && jDViewKitDataSourceModel.getDataMap().get("subFloorList") != null && (jDViewKitDataSourceModel.getDataMap().get("subFloorList") instanceof List)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public static List<Object> getSubFloorList(JDViewKitFloorModel jDViewKitFloorModel, Map<String, JDViewKitVirtualView> map) {
        List<JDViewKitDataSourceModel> dataSourceModelList;
        JSONObject JSONObject;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        String stringValueRef;
        List<Object> list = null;
        if (jDViewKitFloorModel != null && jDViewKitFloorModel.getDataSourceModelList() != null && (dataSourceModelList = jDViewKitFloorModel.getDataSourceModelList()) != null) {
            try {
                if (dataSourceModelList.size() > 0 && map != null) {
                    int i2 = 0;
                    JDViewKitDataSourceModel jDViewKitDataSourceModel = null;
                    while (true) {
                        if (i2 >= dataSourceModelList.size()) {
                            break;
                        }
                        JDViewKitDataSourceModel jDViewKitDataSourceModel2 = dataSourceModelList.get(i2);
                        JDViewKitVirtualView jDViewKitVirtualView = map.get(jDViewKitDataSourceModel2.getDslId());
                        if (jDViewKitDataSourceModel2 != null && jDViewKitVirtualView != null && (stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel2.getDataMap())) != null && stringValueRef.equals(SelectStr)) {
                            if (jDViewKitDataSourceModel2.getDataMap().get(SubFloorListStr) != null && (jDViewKitDataSourceModel2.getDataMap().get(SubFloorListStr) instanceof List)) {
                                list = (List) jDViewKitDataSourceModel2.getDataMap().get(SubFloorListStr);
                                jDViewKitDataSourceModel = jDViewKitDataSourceModel2;
                                break;
                            }
                            jDViewKitDataSourceModel = jDViewKitDataSourceModel2;
                        }
                        i2++;
                    }
                    if (isAsync(jDViewKitFloorModel.getDataSourceMap()) && ((list == null || list.size() == 0) && jDViewKitDataSourceModel != null && ((jDViewKitFloorModel.getRootVirtualView().getType().equals(JDViewKitVirtualView.viewTypeMultiTab) || jDViewKitFloorModel.getRootVirtualView().getType().equals(JDViewKitVirtualView.viewTypeMultiPlusTab)) && (JSONObject = JSONTool.JSONObject(getMultiMabLoadStr(jDViewKitFloorModel))) != null))) {
                        JSONObject jSONObject4 = JSONTool.getJSONObject(JSONObject, "dslMap");
                        if (jSONObject4 != null && jDViewKitFloorModel.getRootVirtualView() != null) {
                            jDViewKitFloorModel.getRootVirtualView().getServiceModel().getVirtualViewManager().dslMap2Virtual(jSONObject4.toString(), jDViewKitFloorModel.getRootVirtualView().getServiceModel());
                        }
                        if ((getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals(SubFlootListStatusLoading) || getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals("")) && (jSONObject = JSONTool.getJSONObject(JSONObject, "loadingFloor")) != null) {
                            ArrayList arrayList = new ArrayList();
                            Map<String, Object> map2 = JSONTool.getMap(jSONObject);
                            map2.put("needRefresh", "Y");
                            arrayList.add(map2);
                            return arrayList;
                        } else if (getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals(SubFlootListStatusFail) && (jSONObject3 = JSONTool.getJSONObject(JSONObject, "errorFloor")) != null) {
                            ArrayList arrayList2 = new ArrayList();
                            Map<String, Object> map3 = JSONTool.getMap(jSONObject3);
                            map3.put("needRefresh", "Y");
                            arrayList2.add(map3);
                            return arrayList2;
                        } else if (getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals(SubFlootListStatusSuccess) && (jSONObject2 = JSONTool.getJSONObject(JSONObject, "noMoreFloor")) != null) {
                            ArrayList arrayList3 = new ArrayList();
                            Map<String, Object> map4 = JSONTool.getMap(jSONObject2);
                            map4.put("needRefresh", "Y");
                            arrayList3.add(map4);
                            return arrayList3;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return list;
    }

    public static String getSubFlootListStatus(Map<String, Object> map) {
        return (map == null || map.get(SubFlootListStatusStr) == null) ? "" : (String) map.get(SubFlootListStatusStr);
    }

    public static boolean isAsync(Map<String, Object> map) {
        return (map == null || map.get("supportAsync") == null || !"1".equals(map.get("supportAsync").toString())) ? false : true;
    }

    public static boolean isHideTab(Map<String, Object> map) {
        return (map == null || map.get("hideTab") == null || !"1".equals(map.get("hideTab").toString())) ? false : true;
    }

    public static boolean isLastFloor(Map<String, Object> map) {
        return (map == null || map.get("lastFloor") == null || !"1".equals(map.get("lastFloor").toString())) ? false : true;
    }

    private void updateTabLayout() {
        int i2 = 0;
        for (int i3 = 0; i3 < getDataSourceModels().size(); i3++) {
            JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(getDataSourceModels().get(i3).getDslId());
            if (jDViewKitVirtualView != null) {
                i2 += jDViewKitVirtualView.getWidth();
            }
        }
        if (i2 > 0) {
            double d = i2;
            if (d < NumberTool.defaultWidth) {
                this.mainRecyclerView.getLayoutParams().width = GlobalManage.getInstance().getRealPx(i2, getChannelModel());
                if (this.mainRecyclerView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                    GlobalManage globalManage = GlobalManage.getInstance();
                    double d2 = NumberTool.defaultWidth;
                    Double.isNaN(d);
                    ((ViewGroup.MarginLayoutParams) this.mainRecyclerView.getLayoutParams()).leftMargin = globalManage.getRealPx((int) ((d2 - d) / 2.0d), getChannelModel());
                }
                this.mainRecyclerView.requestLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewLayout(JDViewKitBaseLayout jDViewKitBaseLayout, int i2) {
        if (jDViewKitBaseLayout == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = jDViewKitBaseLayout.getLayoutParams();
        if (i2 == 1) {
            layoutParams.height = GlobalManage.getInstance().getRealPx(this.loadFloorHeight, getChannelModel());
        } else if (i2 == 2) {
            JDViewKitFloorModel jDViewKitFloorModel = this.mFloorModel;
            if (jDViewKitFloorModel != null && isHideTab(jDViewKitFloorModel.getDataSourceMap())) {
                layoutParams.height = 0;
            } else if (jDViewKitBaseLayout.getVirtualView() != null) {
                layoutParams.height = GlobalManage.getInstance().getRealPx(jDViewKitBaseLayout.getVirtualView().getHeigh(), getChannelModel());
            }
        }
        jDViewKitBaseLayout.setLayoutParams(layoutParams);
    }

    public void initView() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        this.mainRecyclerView = recyclerView;
        recyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        this.mainRecyclerView.setLayoutManager(linearLayoutManager);
        this.mainRecyclerView.addItemDecoration(new RecycleViewDivider());
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter();
        this.adapter = myRecyclerAdapter;
        this.mainRecyclerView.setAdapter(myRecyclerAdapter);
        this.mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.viewkit.templates.container.JDViewKitMultiTabView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i2) {
                super.onScrollStateChanged(recyclerView2, i2);
                JDViewKitMultiTabView.this.setScrollOffset();
            }
        });
        addView(this.mainRecyclerView);
    }

    public void mta(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, Object> jumpParams;
        JDViewKitVirtualEvent virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
        if (virtualEventByType == null || (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) == null) {
            return;
        }
        jDViewKitVirtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(JSONTool.getJsonString(jumpParams), jDViewKitDataSourceModel.getParamsModel()), getContext());
    }

    public void pageRefresh() {
        try {
            if (getVirtualView().getServiceModel() == null || getVirtualView().getServiceModel().getRefreshListener() == null) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                JDViewKitFloorModel jDViewKitFloorModel = this.mFloorModel;
                if (jDViewKitFloorModel != null) {
                    jSONObject.put("position", jDViewKitFloorModel.getFirstPos());
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            getVirtualView().getServiceModel().getRefreshListener().pageRefresh(new JDViewKitPageRefreshModel(JDViewKitPageRefreshModel.pageRefreshTypeSelectTab, new JDViewKitEventModel(jSONObject.toString())), null);
        } catch (Throwable unused) {
        }
    }

    public void select(int i2) {
        JDViewKitVirtualView jDViewKitVirtualView;
        for (int i3 = 0; i3 < getDataSourceModels().size(); i3++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i3);
            if (jDViewKitDataSourceModel != null && (jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId())) != null && jDViewKitVirtualView != null) {
                if (i3 == i2) {
                    asyncSelet(i3, jDViewKitDataSourceModel, jDViewKitVirtualView);
                    this.mSelectNum = i3;
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), SelectStr);
                    mta(jDViewKitVirtualView, jDViewKitDataSourceModel);
                } else {
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), NormalStr);
                }
            }
        }
        this.adapter.modelList = getDataSourceModels();
        this.adapter.notifyDataSetChanged();
        updateTabLayout();
        pageRefresh();
    }

    public void selectByfloorAcross() {
        select(this.mSelectNum);
    }

    public void selectTab(int i2) {
        if (i2 == this.mSelectNum) {
            return;
        }
        JDViewKitFloorModel jDViewKitFloorModel = this.mFloorModel;
        int i3 = R2.anim.pop_left_top_out;
        if (jDViewKitFloorModel != null && jDViewKitFloorModel.getDataSourceMap() != null && isLastFloor(this.mFloorModel.getDataSourceMap())) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int px2size = NumberTool.px2size(getScreenHeight() - (iArr[1] + getHeight()), GlobalManage.getInstance().getScreenWidth());
            if (px2size > 168) {
                i3 = px2size;
            }
            this.loadFloorHeight = i3;
        } else {
            this.loadFloorHeight = R2.anim.pop_left_top_out;
        }
        select(i2);
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceMap(Map<String, Object> map, boolean z) {
        super.setDataSourceMap(map, z);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        this.mIsLayout = z;
        if (getDataSourceModels().size() == 0) {
            return;
        }
        this.mSelectNum = 0;
        if (getDataSourceModels() != null && getDataSourceModels().size() > 0 && this.dslsMap != null) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = null;
            JDViewKitVirtualView jDViewKitVirtualView = null;
            boolean z2 = false;
            for (int i2 = 0; i2 < getDataSourceModels().size(); i2++) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel2 = getDataSourceModels().get(i2);
                JDViewKitVirtualView jDViewKitVirtualView2 = this.dslsMap.get(jDViewKitDataSourceModel2.getDslId());
                if (jDViewKitVirtualView2 != null) {
                    String stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView2.getValueRefer(), jDViewKitDataSourceModel2.getDataMap());
                    if (stringValueRef != null && stringValueRef.equals(SelectStr)) {
                        this.mSelectNum = i2;
                        jDViewKitDataSourceModel = jDViewKitDataSourceModel2;
                        jDViewKitVirtualView = jDViewKitVirtualView2;
                        z2 = true;
                    } else if (z2) {
                        ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView2.getValueRefer(), jDViewKitDataSourceModel2.getDataMap(), NormalStr);
                    }
                }
            }
            if (this.mSelectNum == 0 && !z2) {
                jDViewKitDataSourceModel = getDataSourceModels().get(0);
                jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
                if (jDViewKitVirtualView != null && ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap()) != null) {
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), SelectStr);
                }
            }
            if (isAsync(this.floorModel.getDataSourceMap()) && jDViewKitDataSourceModel != null && getSubFlootListStatus(jDViewKitDataSourceModel.getDataMap()).equals("")) {
                if (jDViewKitDataSourceModel.getDataMap().get(SubFloorListStr) != null && (jDViewKitDataSourceModel.getDataMap().get(SubFloorListStr) instanceof List)) {
                    if (((List) jDViewKitDataSourceModel.getDataMap().get(SubFloorListStr)).size() == 0) {
                        asyncSelet(this.mSelectNum, jDViewKitDataSourceModel, jDViewKitVirtualView);
                    }
                } else {
                    asyncSelet(this.mSelectNum, jDViewKitDataSourceModel, jDViewKitVirtualView);
                }
            }
        }
        this.adapter.modelList = getDataSourceModels();
        this.adapter.notifyDataSetChanged();
        updateTabLayout();
        try {
            int[] offset = getOffset();
            if (offset != null) {
                ((LinearLayoutManager) this.mainRecyclerView.getLayoutManager()).scrollToPositionWithOffset(offset[1], offset[0]);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        if (jDViewKitFloorModel != null && (isAsync(jDViewKitFloorModel.getDataSourceMap()) || isHideTab(jDViewKitFloorModel.getDataSourceMap()))) {
            jDViewKitFloorModel.setFloorAcrossListener(new JDViewKitFloorAcrossListener() { // from class: com.jd.viewkit.templates.container.JDViewKitMultiTabView.2
                @Override // com.jd.viewkit.helper.JDViewKitFloorAcrossListener
                public void floorAcross() {
                    JDViewKitMultiTabView.this.selectByfloorAcross();
                }

                @Override // com.jd.viewkit.helper.JDViewKitFloorAcrossListener
                public void updateView(JDViewKitBaseLayout jDViewKitBaseLayout, int i2) {
                    if (jDViewKitBaseLayout != null) {
                        JDViewKitMultiTabView.this.updateViewLayout(jDViewKitBaseLayout, i2);
                    }
                }
            });
        }
        this.mFloorModel = jDViewKitFloorModel;
        super.setFloorModel(jDViewKitFloorModel);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModelByLayout(JDViewKitFloorModel jDViewKitFloorModel) {
        super.setFloorModelByLayout(jDViewKitFloorModel);
        this.mFloorModel = jDViewKitFloorModel;
    }

    public void setScrollOffset() {
        try {
            View childAt = this.mainRecyclerView.getLayoutManager().getChildAt(0);
            setOffset(childAt.getLeft(), this.mainRecyclerView.getLayoutManager().getPosition(childAt));
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualView jDViewKitVirtualView) {
        super.setVirtualView(jDViewKitVirtualView);
    }

    public JDViewKitMultiTabView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }
}

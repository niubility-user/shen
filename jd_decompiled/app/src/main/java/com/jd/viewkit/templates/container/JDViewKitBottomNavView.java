package com.jd.viewkit.templates.container;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.jdviewkitvirtualbottomnavview.JDViewKitVirtualBottomNavView;
import com.jd.viewkit.templates.view.JDViewKitStatefulView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitBottomNavView extends JDViewKitBaseLayout<JDViewKitVirtualBottomNavView> {
    private static String NormalStr = "0";
    private static String SelectStr = "1";
    private MyRecyclerAdapter adapter;
    private JDViewKitFloorModel mFloorModel;
    private boolean mIsLayout;
    private int mSelectNum;
    private RecyclerView mainRecyclerView;

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
                ((JDViewKitStatefulView) jDViewKitBaseLayout).setDataSourceModel(this.modelList.get(i2), JDViewKitBottomNavView.this.mIsLayout);
                viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jd.viewkit.templates.container.JDViewKitBottomNavView.MyRecyclerAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDViewKitBottomNavView.this.selectTab(i2);
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitBottomNavView.this).dslsMap.get(getTemplateByViewType(i2)), JDViewKitBottomNavView.this.getChannelModel()));
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

    public JDViewKitBottomNavView(@NonNull Context context) {
        super(context);
        this.mSelectNum = -1;
        this.mContext = context;
        initView();
    }

    public static List<JDViewKitEventModel> getBottomEventModelList(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        JDViewKitEventModel jDViewKitEventModel;
        String stringValueRef;
        JDViewKitVirtualEvent virtualEventByType;
        Map<String, Object> jumpParams;
        if (list == null || list.size() <= 0 || map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = list.get(i2);
            JDViewKitVirtualView jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId());
            if (jDViewKitVirtualView == null || (stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) == null || !stringValueRef.equals(SelectStr) || (virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) == null || (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) == null) {
                jDViewKitEventModel = null;
            } else {
                int valueOfInt = jumpParams.get("jumpType") != null ? NumberTool.valueOfInt(jumpParams.get("jumpType").toString()) : -1;
                jDViewKitEventModel = new JDViewKitEventModel(valueOfInt == JDViewKitEventModel.jumpTYpe_In ? i2 : -1, valueOfInt, JSONTool.getJsonString(jumpParams));
                arrayList.add(jDViewKitEventModel);
            }
            if (jDViewKitEventModel == null) {
                arrayList.add(new JDViewKitEventModel(null));
            }
        }
        return arrayList;
    }

    public static JDViewKitEventModel getBottomNavSelectEventModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, Map<String, JDViewKitVirtualView> map, int i2, boolean z) {
        JDViewKitVirtualView jDViewKitVirtualView;
        String stringValueRef;
        JDViewKitVirtualEvent virtualEventByType;
        Map<String, Object> jumpParams;
        if (jDViewKitDataSourceModel == null || map == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || (stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) == null || (!(stringValueRef.equals(SelectStr) || z) || (virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) == null || (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) == null)) {
            return null;
        }
        int valueOfInt = jumpParams.get("jumpType") != null ? NumberTool.valueOfInt(jumpParams.get("jumpType").toString()) : -1;
        if (valueOfInt != JDViewKitEventModel.jumpTYpe_In) {
            i2 = -1;
        }
        return new JDViewKitEventModel(i2, valueOfInt, JSONTool.getJsonString(jumpParams));
    }

    public static boolean isJumpIn(JDViewKitDataSourceModel jDViewKitDataSourceModel, Map<String, JDViewKitVirtualView> map) {
        JDViewKitVirtualView jDViewKitVirtualView;
        JDViewKitVirtualEvent virtualEventByType;
        Map<String, Object> jumpParams;
        if (jDViewKitDataSourceModel != null && map != null && (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) != null && (virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) != null && (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) != null) {
            if ((jumpParams.get("jumpType") != null ? NumberTool.valueOfInt(jumpParams.get("jumpType").toString()) : -1) == JDViewKitEventModel.jumpTYpe_In) {
                return true;
            }
        }
        return false;
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
        addView(this.mainRecyclerView);
    }

    public int mtaAndEvent(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, int i2) {
        JDViewKitVirtualEvent virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
        if (virtualEventByType == null) {
            return 0;
        }
        try {
            Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey());
            if (jumpParams != null) {
                String jsonString = JSONTool.getJsonString(jumpParams);
                int valueOfInt = jumpParams.get("jumpType") != null ? NumberTool.valueOfInt(jumpParams.get("jumpType").toString()) : -1;
                if (valueOfInt != JDViewKitEventModel.jumpTYpe_In) {
                    i2 = -1;
                }
                jDViewKitVirtualView.getServiceModel().getEventService().clickEvent(new JDViewKitEventModel(i2, valueOfInt, jsonString), getContext());
                jDViewKitVirtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(jsonString, jDViewKitDataSourceModel.getParamsModel()), getContext());
                return valueOfInt;
            }
        } catch (Throwable unused) {
        }
        return 0;
    }

    public void selectTab(int i2) {
        if (i2 == this.mSelectNum) {
            return;
        }
        for (int i3 = 0; i3 < getDataSourceModels().size(); i3++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i3);
            JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
            if (jDViewKitVirtualView != null) {
                if (i3 == i2) {
                    if (mtaAndEvent(jDViewKitVirtualView, jDViewKitDataSourceModel, i3) != JDViewKitEventModel.jumpTYpe_In) {
                        return;
                    }
                    this.mSelectNum = i3;
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), SelectStr);
                } else {
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), NormalStr);
                }
            }
        }
        this.adapter.modelList = getDataSourceModels();
        this.adapter.notifyDataSetChanged();
        updateTabLayout();
        try {
            if (getVirtualView().getServiceModel() == null || getVirtualView().getServiceModel().getRefreshListener() == null) {
                return;
            }
            getVirtualView().getServiceModel().getRefreshListener().pageRefresh();
        } catch (Throwable unused) {
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        this.mIsLayout = z;
        if (getDataSourceModels().size() == 0) {
            return;
        }
        this.mSelectNum = -1;
        if (getDataSourceModels() != null && getDataSourceModels().size() > 0 && this.dslsMap != null) {
            boolean z2 = false;
            for (int i2 = 0; i2 < getDataSourceModels().size(); i2++) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i2);
                JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
                if (jDViewKitVirtualView != null) {
                    String stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
                    if (stringValueRef != null && stringValueRef.equals(SelectStr)) {
                        this.mSelectNum = i2;
                        z2 = true;
                    } else if (z2) {
                        ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), NormalStr);
                    }
                }
            }
            if (!z2 && isJumpIn(getDataSourceModels().get(0), this.dslsMap)) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel2 = getDataSourceModels().get(0);
                JDViewKitVirtualView jDViewKitVirtualView2 = this.dslsMap.get(jDViewKitDataSourceModel2.getDslId());
                if (jDViewKitVirtualView2 != null && ExpressionParserTool.getStringValueRef(jDViewKitVirtualView2.getValueRefer(), jDViewKitDataSourceModel2.getDataMap()) != null) {
                    ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView2.getValueRefer(), jDViewKitDataSourceModel2.getDataMap(), SelectStr);
                }
            }
        }
        this.adapter.modelList = getDataSourceModels();
        this.adapter.notifyDataSetChanged();
        updateTabLayout();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModel(JDViewKitFloorModel jDViewKitFloorModel) {
        super.setFloorModel(jDViewKitFloorModel);
        this.mFloorModel = jDViewKitFloorModel;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setFloorModelByLayout(JDViewKitFloorModel jDViewKitFloorModel) {
        super.setFloorModelByLayout(jDViewKitFloorModel);
        this.mFloorModel = jDViewKitFloorModel;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualBottomNavView jDViewKitVirtualBottomNavView) {
        super.setVirtualView((JDViewKitBottomNavView) jDViewKitVirtualBottomNavView);
    }

    public JDViewKitBottomNavView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }

    public static JDViewKitEventModel getBottomNavSelectEventModel(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        JDViewKitEventModel jDViewKitEventModel = null;
        if (list == null || list.size() <= 0 || map == null) {
            return null;
        }
        if (isJumpIn(list.get(0), map)) {
            for (int i2 = 0; i2 < list.size() && (jDViewKitEventModel = getBottomNavSelectEventModel(list.get(i2), map, i2, false)) == null; i2++) {
            }
        }
        return jDViewKitEventModel == null ? getBottomNavSelectEventModel(list.get(0), map, 0, true) : jDViewKitEventModel;
    }
}

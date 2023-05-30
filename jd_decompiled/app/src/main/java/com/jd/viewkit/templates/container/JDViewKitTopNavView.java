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
import com.jd.viewkit.templates.model.jdviewkitvirtualtopnavview.JDViewKitVirtualTopNavJumpParams;
import com.jd.viewkit.templates.model.jdviewkitvirtualtopnavview.JDViewKitVirtualTopNavView;
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
public class JDViewKitTopNavView extends JDViewKitBaseLayout<JDViewKitVirtualTopNavView> {
    private static String NormalStr = "0";
    private static String SelectStr = "1";
    private MyRecyclerAdapter adapter;
    private JDViewKitFloorModel mFloorModel;
    private boolean mIsLayout;
    private int mSelectNum;
    private int mSelectWidth;
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
            viewHolder.contentLayout.setTag(new Integer(i2));
            JDViewKitBaseLayout jDViewKitBaseLayout = viewHolder.contentLayout;
            if (jDViewKitBaseLayout instanceof JDViewKitStatefulView) {
                jDViewKitBaseLayout.setTag(new Integer(i2));
                ((JDViewKitStatefulView) viewHolder.contentLayout).setDataSourceModel(this.modelList.get(i2), JDViewKitTopNavView.this.mIsLayout);
                viewHolder.contentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jd.viewkit.templates.container.JDViewKitTopNavView.MyRecyclerAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        JDViewKitTopNavView.this.selectTab(i2, true);
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitTopNavView.this).dslsMap.get(getTemplateByViewType(i2)), JDViewKitTopNavView.this.getChannelModel()));
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

    public JDViewKitTopNavView(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public static int getScelectIndex(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        String stringValueRef;
        if (list == null || list.size() <= 0 || map == null) {
            return -1;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = list.get(i2);
            JDViewKitVirtualView jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId());
            if (jDViewKitVirtualView != null && (stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap())) != null && stringValueRef.equals(SelectStr)) {
                return i2;
            }
        }
        return -1;
    }

    public static List<JDViewKitEventModel> getTopEventModelList(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        JDViewKitEventModel jDViewKitEventModel;
        JDViewKitVirtualEvent virtualEventByType;
        Map<String, Object> jumpParams;
        if (list == null || list.size() <= 0 || map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = list.get(i2);
            JDViewKitVirtualView jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId());
            if (jDViewKitVirtualView == null || (virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) == null || (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) == null) {
                jDViewKitEventModel = null;
            } else {
                jDViewKitEventModel = new JDViewKitEventModel(i2, JSONTool.getJsonString(jumpParams));
                arrayList.add(jDViewKitEventModel);
            }
            if (jDViewKitEventModel == null) {
                arrayList.add(new JDViewKitEventModel(i2, null));
            }
        }
        return arrayList;
    }

    public static List<JDViewKitVirtualTopNavJumpParams> getTopNavJumpParams(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map) {
        Map<String, Object> jumpParams;
        if (list != null && map != null) {
            ArrayList arrayList = new ArrayList();
            for (JDViewKitDataSourceModel jDViewKitDataSourceModel : list) {
                JDViewKitVirtualEvent virtualEventByType = map.get(jDViewKitDataSourceModel.getDslId()).getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
                if (virtualEventByType != null && (jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey())) != null) {
                    arrayList.add(new JDViewKitVirtualTopNavJumpParams(JSONTool.getJsonString(jumpParams)));
                }
            }
            if (arrayList.size() == list.size()) {
                return arrayList;
            }
        }
        return null;
    }

    private void setMainRecyclerViewScroll() {
        try {
            try {
                View childAt = this.mainRecyclerView.getLayoutManager().getChildAt(0);
                int intValue = ((Integer) childAt.getTag()).intValue();
                int left = childAt.getLeft();
                int i2 = this.mSelectNum;
                int i3 = this.mSelectWidth;
                this.mainRecyclerView.smoothScrollBy(GlobalManage.getInstance().getRealPx(((i2 * i3) - ((intValue * i3) - left)) - ((((JDViewKitVirtualTopNavView) this.virtualView).getWidth() - this.mSelectWidth) / 2), getChannelModel()), 0);
            } catch (Exception unused) {
                ((LinearLayoutManager) this.mainRecyclerView.getLayoutManager()).scrollToPositionWithOffset(this.mSelectNum, GlobalManage.getInstance().getRealPx(((JDViewKitVirtualTopNavView) this.virtualView).getWidth() - this.mSelectWidth, getChannelModel()) / 2);
            }
        } catch (Exception unused2) {
        }
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

    public void mtaAndEvent(JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, int i2) {
        JDViewKitVirtualEvent virtualEventByType = jDViewKitVirtualView.getVirtualEventByType(JDViewKitVirtualEvent.typeClick);
        if (virtualEventByType != null && jDViewKitDataSourceModel != null) {
            try {
                Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey());
                if (jumpParams == null) {
                    return;
                }
                String jsonString = JSONTool.getJsonString(jumpParams);
                jDViewKitVirtualView.getServiceModel().getEventService().clickEvent(new JDViewKitEventModel(i2, jsonString), getContext());
                jDViewKitVirtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(jsonString, jDViewKitDataSourceModel.getParamsModel()), getContext());
            } catch (Throwable unused) {
            }
        }
    }

    public void selectTab(int i2, boolean z) {
        if (i2 != this.mSelectNum) {
            for (int i3 = 0; i3 < getDataSourceModels().size(); i3++) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i3);
                JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
                if (jDViewKitVirtualView != null) {
                    if (i3 == i2) {
                        this.mSelectNum = i3;
                        this.mSelectWidth = jDViewKitVirtualView.getWidth();
                        ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), SelectStr);
                        if (z) {
                            mtaAndEvent(jDViewKitVirtualView, jDViewKitDataSourceModel, i3);
                        }
                    } else {
                        ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), NormalStr);
                    }
                }
            }
            this.adapter.modelList = getDataSourceModels();
            this.adapter.notifyDataSetChanged();
            updateTabLayout();
            try {
                if (getVirtualView().getServiceModel() != null && getVirtualView().getServiceModel().getRefreshListener() != null) {
                    getVirtualView().getServiceModel().getRefreshListener().pageRefresh();
                }
            } catch (Throwable unused) {
            }
            setMainRecyclerViewScroll();
        }
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
            boolean z2 = false;
            for (int i2 = 0; i2 < getDataSourceModels().size(); i2++) {
                JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i2);
                JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
                if (jDViewKitVirtualView != null) {
                    String stringValueRef = ExpressionParserTool.getStringValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap());
                    if (!z2 && stringValueRef != null && stringValueRef.equals(SelectStr)) {
                        this.mSelectNum = i2;
                        this.mSelectWidth = jDViewKitVirtualView.getWidth();
                        z2 = true;
                    } else if (z2) {
                        ExpressionParserTool.setObjectValueRef(jDViewKitVirtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap(), NormalStr);
                    }
                }
            }
            if (this.mSelectNum == 0 && !z2) {
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
        setMainRecyclerViewScroll();
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

    public void setTopNavIndex(int i2) {
        selectTab(i2, false);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualTopNavView jDViewKitVirtualTopNavView) {
        super.setVirtualView((JDViewKitTopNavView) jDViewKitVirtualTopNavView);
    }

    public JDViewKitTopNavView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }
}

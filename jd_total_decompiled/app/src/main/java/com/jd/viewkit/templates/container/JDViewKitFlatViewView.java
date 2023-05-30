package com.jd.viewkit.templates.container;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitCountdownInterface;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitProgressView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitFlatViewView extends JDViewKitBaseLayout<JDViewKitVirtualView> implements JDViewKitCountdownInterface {
    private MyRecyclerAdapter adapter;
    private JDViewKitCountdownView countdownView;
    private boolean isSetNewData;
    private boolean mIsLayout;
    private RecyclerView mainRecyclerView;
    private JDViewKitProgressView progressView;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class MyRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
        private JDViewKitCountdownView countdownView;
        public List<JDViewKitDataSourceModel> modelList;
        private int typeInt = -1;
        private Map<String, String> typeMap = new ArrayMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes18.dex */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public JDViewKitAbsoluteLayout absoluteLayout;

            public ViewHolder(JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout) {
                super(jDViewKitAbsoluteLayout);
                this.absoluteLayout = jDViewKitAbsoluteLayout;
            }
        }

        public MyRecyclerAdapter(List<JDViewKitDataSourceModel> list) {
            this.modelList = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<JDViewKitDataSourceModel> list = this.modelList;
            if (list != null) {
                return list.size();
            }
            return 0;
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

        public void setModelList(List<JDViewKitDataSourceModel> list) {
            if (this.modelList == null) {
                this.modelList = new ArrayList();
            }
            this.modelList.clear();
            this.modelList.addAll(list);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i2) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = this.modelList.get(i2);
            viewHolder.absoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, JDViewKitFlatViewView.this.mIsLayout);
            JDViewKitFlatViewView.this.sendExpo(jDViewKitDataSourceModel);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitFlatViewView.this).dslsMap.get(getTemplateByViewType(i2)), JDViewKitFlatViewView.this.getChannelModel()));
        }

        public MyRecyclerAdapter() {
        }
    }

    public JDViewKitFlatViewView(@NonNull Context context) {
        super(context);
        this.isSetNewData = true;
        this.mContext = context;
        initView();
    }

    private void updateCountdownView() {
        if (this.isSetNewData) {
            View childAt = this.mainRecyclerView.getChildAt(0);
            if (childAt instanceof JDViewKitAbsoluteLayout) {
                this.countdownView = JDViewKitCountdownView.getCountdownView((JDViewKitAbsoluteLayout) childAt);
                this.progressView = JDViewKitProgressView.getProgressView((JDViewKitBaseLayout) childAt);
            }
        }
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public int getCountType() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView == null || jDViewKitCountdownView.virtualCountdownView == null) {
            return -1;
        }
        jDViewKitCountdownView.initCountdownParamsByPlay(true);
        return this.countdownView.virtualCountdownView.getCountType();
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public int getTriggerType() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView == null || jDViewKitCountdownView.virtualCountdownView == null) {
            return -1;
        }
        jDViewKitCountdownView.initCountdownParamsByPlay(true);
        return this.countdownView.virtualCountdownView.getTriggerType();
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void handleCountdown(int i2) {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
            this.countdownView.handleCountdown(i2);
        }
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void initCountdownParamsByPlay() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
        }
    }

    public void initView() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        this.mainRecyclerView = recyclerView;
        recyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.mainRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false) { // from class: com.jd.viewkit.templates.container.JDViewKitFlatViewView.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.mainRecyclerView.setNestedScrollingEnabled(false);
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter();
        this.adapter = myRecyclerAdapter;
        this.mainRecyclerView.setAdapter(myRecyclerAdapter);
        addView(this.mainRecyclerView);
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void setCountdownLifeCycle(int i2) {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
            this.countdownView.setCountdownLifeCycle(i2);
        }
        JDViewKitProgressView jDViewKitProgressView = this.progressView;
        if (jDViewKitProgressView != null) {
            jDViewKitProgressView.setCountdownLifeCycle(i2);
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        this.mIsLayout = z;
        if (getDataSourceModels().size() == 0) {
            return;
        }
        this.isSetNewData = true;
        this.adapter.setModelList(getDataSourceModels());
        this.adapter.notifyDataSetChanged();
    }
}

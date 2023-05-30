package com.jd.viewkit.templates.container.jdviewkitscorllview;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
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
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitvirtualscrollview.JDViewKitVirtualScrollView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitScorllView extends JDViewKitBaseLayout<JDViewKitVirtualScrollView> implements JDViewKitMoreRecyclerView.OnItemClickLitener {
    public static String ResetoffsetKey = "JDViewKitScorllViewReset";
    private static final String TAG = "JDViewKitScorllView";
    private static int moreType = Integer.MAX_VALUE;
    public static int scorllCount = 30000;
    private MyRecyclerAdapter adapter;
    private boolean isAutoPlay;
    private boolean isLoop;
    private boolean isMore;
    private Handler mHandler;
    private boolean mIsLayout;
    private JDViewKitMoreRecyclerView mainRecyclerView;
    private JDViewKitAbsoluteLayout moreAbsoluteLayout;
    private JDViewKitDataSourceModel moreDataSourceModel;
    private String moreDslId;
    private JDViewKitVirtualView moreVirtualView;
    private int playDelay;
    private int playItems;
    private final Runnable task;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class RecycleViewDivider extends RecyclerView.ItemDecoration {
        RecycleViewDivider() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (view.getTag() == null || !(view.getTag() instanceof TagParams) || ((TagParams) view.getTag()).getRect() == null || !(((TagParams) view.getTag()).getRect() instanceof Rect)) {
                return;
            }
            rect.set(((TagParams) view.getTag()).getRect());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class TagParams {
        private int position;
        private Rect rect;
        private int width;

        TagParams() {
        }

        public int getPosition() {
            return this.position;
        }

        public Rect getRect() {
            return this.rect;
        }

        public int getWidth() {
            return this.width;
        }

        public void setPosition(int i2) {
            this.position = i2;
        }

        public void setRect(Rect rect) {
            this.rect = rect;
        }

        public void setWidth(int i2) {
            this.width = i2;
        }
    }

    public JDViewKitScorllView(@NonNull Context context) {
        super(context);
        this.isAutoPlay = false;
        this.isLoop = false;
        this.playItems = 1;
        this.playDelay = 3;
        this.isMore = false;
        this.moreDslId = null;
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView.1
            @Override // java.lang.Runnable
            public void run() {
                if (JDViewKitScorllView.this.isAutoPlay) {
                    JDViewKitScorllView.this.setAutoScrollOffset();
                    JDViewKitScorllView.this.mHandler.postDelayed(this, JDViewKitScorllView.this.playDelay * 1000);
                }
            }
        };
        this.mContext = context;
        initView();
    }

    public void initView() {
        JDViewKitMoreRecyclerView jDViewKitMoreRecyclerView = new JDViewKitMoreRecyclerView(getContext());
        this.mainRecyclerView = jDViewKitMoreRecyclerView;
        jDViewKitMoreRecyclerView.setOnLastItemClickLitener(this);
        this.mainRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        this.mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                JDViewKitScorllView.this.setScrollOffset();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        this.mainRecyclerView.setLayoutManager(linearLayoutManager);
        this.mainRecyclerView.addItemDecoration(new RecycleViewDivider());
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter();
        this.adapter = myRecyclerAdapter;
        this.mainRecyclerView.setAdapter(myRecyclerAdapter);
        addView(this.mainRecyclerView);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitMoreRecyclerView.OnItemClickLitener
    public void onItemClick() {
        JDViewKitDataSourceModel jDViewKitDataSourceModel;
        if (this.moreAbsoluteLayout == null || (jDViewKitDataSourceModel = this.moreDataSourceModel) == null) {
            return;
        }
        JDViewKitEventHelper.click(this.moreVirtualView, jDViewKitDataSourceModel, this, getChannelModel());
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    public void setAutoScrollOffset() {
        try {
            View childAt = this.mainRecyclerView.getLayoutManager().getChildAt(0);
            TagParams tagParams = (TagParams) childAt.getTag();
            int position = tagParams.getPosition();
            int width = tagParams.getWidth();
            Rect rect = tagParams.getRect();
            int right = childAt.getRight();
            int i2 = this.playItems;
            if (i2 > 1) {
                right += (width + rect.left) * i2;
            }
            this.mainRecyclerView.smoothScrollBy(right, 0);
            setOffset(0, position);
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        String str;
        super.setDataSourceModels(list, z);
        this.mIsLayout = z;
        if (getDataSourceModels().size() == 0) {
            return;
        }
        if (((JDViewKitVirtualScrollView) this.virtualView).getScrollMoreConfig() != null && !this.isLoop && StringTool.isNotEmpty(((JDViewKitVirtualScrollView) this.virtualView).getScrollMoreConfig().getDslId())) {
            this.moreDslId = ((JDViewKitVirtualScrollView) this.virtualView).getScrollMoreConfig().getDslId();
            this.isMore = true;
        }
        if (this.isMore) {
            JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(this.moreDslId);
            this.moreVirtualView = jDViewKitVirtualView;
            if (jDViewKitVirtualView != null) {
                if (StringTool.isBegin(jDViewKitVirtualView.getValueRefer(), "$")) {
                    String substring = this.moreVirtualView.getValueRefer().substring(1);
                    Map<String, Object> map = null;
                    Map<String, Object> map2 = this.dataSourceMap;
                    if (map2 != null && map2.get(substring) != null) {
                        map = (Map) this.dataSourceMap.get(substring);
                        map.put(JDViewKitDataSourceModel.dslIdKey, this.moreDslId);
                    }
                    JDViewKitDataSourceModel jDViewKitDataSourceModel = new JDViewKitDataSourceModel(map);
                    this.moreDataSourceModel = jDViewKitDataSourceModel;
                    jDViewKitDataSourceModel.setDataMap(map);
                    if (getDataSourceModels().get(0) != null) {
                        this.moreDataSourceModel.setParamsModel(getDataSourceModels().get(0).getParamsModel());
                    }
                    JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(this.mContext, this.moreVirtualView, getChannelModel());
                    this.moreAbsoluteLayout = view;
                    view.setDataSourceModel(this.moreDataSourceModel, false);
                    this.adapter.setView(this.mainRecyclerView);
                } else {
                    this.isMore = false;
                }
            } else {
                this.isMore = false;
            }
        }
        this.adapter.modelList = getDataSourceModels();
        this.adapter.notifyDataSetChanged();
        if (this.isAutoPlay) {
            this.mHandler.removeCallbacks(this.task);
            this.mHandler.postDelayed(this.task, this.playDelay * 1000);
        }
        Map<String, Object> map3 = this.dataSourceMap;
        if (map3 != null && map3.containsKey(ResetoffsetKey) && (this.dataSourceMap.get(ResetoffsetKey) instanceof String) && (str = (String) this.dataSourceMap.get(ResetoffsetKey)) != null && str.contains(getVirtualView().getDslId())) {
            this.mainRecyclerView.scrollToPosition(0);
            this.dataSourceMap.put(ResetoffsetKey, str.replace(getVirtualView().getDslId(), ""));
        }
    }

    public void setScrollOffset() {
        try {
            int i2 = 0;
            View childAt = this.mainRecyclerView.getLayoutManager().getChildAt(0);
            T t = this.virtualView;
            if (t != 0 && ((JDViewKitVirtualScrollView) t).getScrollConfig() != null) {
                i2 = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) this.virtualView).getScrollConfig().getItemSpace(), getChannelModel());
            }
            setOffset(childAt.getLeft() - i2, this.mainRecyclerView.getLayoutManager().getPosition(childAt));
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualScrollView jDViewKitVirtualScrollView) {
        super.setVirtualView((JDViewKitScorllView) jDViewKitVirtualScrollView);
        if (jDViewKitVirtualScrollView == null || jDViewKitVirtualScrollView.getScrollConfig() == null) {
            return;
        }
        this.isAutoPlay = jDViewKitVirtualScrollView.getScrollConfig().isAutoPlay();
        this.isLoop = jDViewKitVirtualScrollView.getScrollConfig().isLoop();
        this.playItems = jDViewKitVirtualScrollView.getScrollConfig().getPlayItems() == 0 ? 1 : jDViewKitVirtualScrollView.getScrollConfig().getPlayItems();
        this.playDelay = jDViewKitVirtualScrollView.getScrollConfig().getPlayDelay() == 0 ? 3 : jDViewKitVirtualScrollView.getScrollConfig().getPlayDelay();
    }

    /* loaded from: classes18.dex */
    public class MyRecyclerAdapter extends RecyclerView.Adapter {
        public List<JDViewKitDataSourceModel> modelList;
        private int typeInt = -1;
        private Map<String, String> typeMap = new ArrayMap();
        private View view;

        /* loaded from: classes18.dex */
        class MoreViewHolder extends RecyclerView.ViewHolder {
            public JDViewKitAbsoluteLayout absoluteLayout;

            public MoreViewHolder(JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout) {
                super(jDViewKitAbsoluteLayout);
                this.absoluteLayout = jDViewKitAbsoluteLayout;
            }
        }

        /* loaded from: classes18.dex */
        class ViewHolder extends RecyclerView.ViewHolder {
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
            if (this.modelList == null) {
                return 0;
            }
            if (!JDViewKitScorllView.this.isLoop) {
                if (JDViewKitScorllView.this.isMore) {
                    return this.modelList.size() + 1;
                }
                return this.modelList.size();
            }
            return this.modelList.size() * JDViewKitScorllView.scorllCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            if (JDViewKitScorllView.this.isLoop) {
                i2 %= this.modelList.size();
            }
            if (JDViewKitScorllView.this.isMore && i2 == this.modelList.size()) {
                return JDViewKitScorllView.moreType;
            }
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

        public View getView() {
            return this.view;
        }

        /* JADX WARN: Removed duplicated region for block: B:40:0x019a  */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel;
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            Rect rect;
            JDViewKitVirtualView jDViewKitVirtualView;
            TagParams tagParams = new TagParams();
            tagParams.setPosition(i2);
            int size = JDViewKitScorllView.this.isLoop ? i2 % this.modelList.size() : i2;
            if (JDViewKitScorllView.this.isMore && size == this.modelList.size()) {
                jDViewKitDataSourceModel = JDViewKitScorllView.this.moreDataSourceModel;
            } else {
                jDViewKitDataSourceModel = this.modelList.get(size);
            }
            if (viewHolder instanceof ViewHolder) {
                if (jDViewKitDataSourceModel == null || ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView == null || ((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig() == null) {
                    i3 = 0;
                    i4 = 0;
                    i5 = 0;
                } else {
                    i3 = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getItemSpace(), JDViewKitScorllView.this.getChannelModel());
                    i4 = ((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getLeftRightMargin() > 0 ? GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getLeftRightMargin(), JDViewKitScorllView.this.getChannelModel()) : i3;
                    i5 = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getPaddingTop(), JDViewKitScorllView.this.getChannelModel());
                    if (((JDViewKitBaseLayout) JDViewKitScorllView.this).dslsMap != null && ((JDViewKitBaseLayout) JDViewKitScorllView.this).dslsMap.get(jDViewKitDataSourceModel.getDslId()) != null && (jDViewKitVirtualView = (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).dslsMap.get(jDViewKitDataSourceModel.getDslId())) != null && ((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig() != null) {
                        i3 = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getItemSpace() - jDViewKitVirtualView.getOrgX(), JDViewKitScorllView.this.getChannelModel());
                        i5 = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getPaddingTop() - jDViewKitVirtualView.getOrgY(), JDViewKitScorllView.this.getChannelModel());
                        i7 = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getWidth(), JDViewKitScorllView.this.getChannelModel());
                        i6 = ((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getLeftRightMargin() > 0 ? GlobalManage.getInstance().getRealPx(((JDViewKitVirtualScrollView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).virtualView).getScrollConfig().getLeftRightMargin() - jDViewKitVirtualView.getOrgX(), JDViewKitScorllView.this.getChannelModel()) : i3;
                        rect = new Rect(i3, i5, 0, 0);
                        if (i2 == 0) {
                            rect = new Rect(i6, i5, 0, 0);
                        }
                        if (size == this.modelList.size() - 1 && !JDViewKitScorllView.this.isLoop) {
                            rect = new Rect(i3, i5, i4, 0);
                        }
                        tagParams.setRect(rect);
                        tagParams.setWidth(i7);
                        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
                        viewHolder2.absoluteLayout.setTag(tagParams);
                        viewHolder2.absoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, JDViewKitScorllView.this.mIsLayout);
                    }
                }
                i6 = 0;
                i7 = 0;
                rect = new Rect(i3, i5, 0, 0);
                if (i2 == 0) {
                }
                if (size == this.modelList.size() - 1) {
                    rect = new Rect(i3, i5, i4, 0);
                }
                tagParams.setRect(rect);
                tagParams.setWidth(i7);
                ViewHolder viewHolder22 = (ViewHolder) viewHolder;
                viewHolder22.absoluteLayout.setTag(tagParams);
                viewHolder22.absoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, JDViewKitScorllView.this.mIsLayout);
            } else {
                boolean z = viewHolder instanceof MoreViewHolder;
            }
            JDViewKitScorllView.this.sendExpo(jDViewKitDataSourceModel);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == JDViewKitScorllView.moreType) {
                return new MoreViewHolder(JDViewKitScorllView.this.moreAbsoluteLayout);
            }
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitScorllView.this).dslsMap.get(getTemplateByViewType(i2)), JDViewKitScorllView.this.getChannelModel()));
        }

        public void setView(View view) {
            this.view = view;
        }

        public MyRecyclerAdapter() {
        }
    }

    public JDViewKitScorllView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }
}

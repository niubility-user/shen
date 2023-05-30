package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.un.address.R;
import com.jd.lib.un.basewidget.widget.flow.FlowLayout;
import com.jd.lib.un.basewidget.widget.flow.TagAdapter;
import com.jd.lib.un.basewidget.widget.flow.TagFlowLayout;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.stickyListView.StickyHeaderAdapter;
import com.jingdong.common.stickyListView.StickyHeaderData;
import com.jingdong.common.stickyListView.StickyHeaderListView;
import com.jingdong.common.ui.JDSlideBar;
import com.jingdong.common.ui.address.entity.AreaBeanVO;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.JDCityDataUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes6.dex */
public class JDAreaCodeSelectView extends RelativeLayout implements JDSlideBar.ISlideBarTouchChangeLisener {
    private static final String TAG = JDAreaCodeSelectView.class.getSimpleName();
    private ArrayList<AreaBeanVO> areaCodeList;
    private JDSlideBar jdSlideBar;
    private TextView jdSlideBarNoteTv;
    private MyAdapter mAdapter;
    private AreaCodeHelper mAreaCodeHelper;
    private AreaBeanVO[] mAreaCodes;
    private long mClickTime;
    private LinearLayout mCommonArea;
    private Context mContext;
    private ImageView mErrorImage;
    private RelativeLayout mErrorLayout;
    private TextView mErrorText;
    private LinearLayout mErrorView;
    private TagFlowLayout mFlowLayout;
    private Handler mHandler;
    private boolean mIsDestroy;
    private StickyHeaderListView mListView;
    private View mLoadingView;
    private OnAreaCodeLoadCompletedListener mOnAreaCodeLoadCompletedListener;
    private View mRootView;
    private StickyHeaderData stickyHeaderData;

    /* loaded from: classes6.dex */
    public interface AreaCodeHelper {
        void close();

        void loadAreaCode(OnAreaCodeLoadCompletedListener onAreaCodeLoadCompletedListener);

        void onAreaCodeSelected(AreaBeanVO areaBeanVO);

        void reloadData();
    }

    /* loaded from: classes6.dex */
    public interface OnAreaCodeLoadCompletedListener {
        void onAreaCodeLoadCompleted(boolean z, ArrayList<AreaBeanVO> arrayList, ArrayList<AreaBeanVO> arrayList2);
    }

    public JDAreaCodeSelectView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFlowView(final ArrayList<AreaBeanVO> arrayList) {
        if (arrayList == null) {
            return;
        }
        if (arrayList.size() > 0) {
            LinearLayout linearLayout = this.mCommonArea;
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            String[] strArr = new String[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (arrayList.get(i2).getName() != null && arrayList.get(i2).getAreaCode() != null) {
                    strArr[i2] = arrayList.get(i2).getName() + MqttTopic.SINGLE_LEVEL_WILDCARD + arrayList.get(i2).getAreaCode();
                }
            }
            TagFlowLayout tagFlowLayout = this.mFlowLayout;
            if (tagFlowLayout == null) {
                return;
            }
            tagFlowLayout.setMaxSelectCount(1);
            this.mFlowLayout.setAdapter(new TagAdapter<String>(strArr) { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.4
                @Override // com.jd.lib.un.basewidget.widget.flow.TagAdapter
                public View getView(FlowLayout flowLayout, int i3, String str) {
                    TextView textView = new TextView(flowLayout.getContext());
                    textView.setTextSize(12.0f);
                    textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
                    textView.setBackgroundResource(R.drawable.flow_item_slecter);
                    textView.setText(str);
                    return textView;
                }
            });
            this.mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.5
                @Override // com.jd.lib.un.basewidget.widget.flow.TagFlowLayout.OnTagClickListener
                public boolean onTagClick(View view, int i3, FlowLayout flowLayout) {
                    if (Log.D) {
                        Log.d(JDAreaCodeSelectView.TAG, "onFlowTagClick " + i3);
                    }
                    if (JDAreaCodeSelectView.this.mAreaCodeHelper != null) {
                        JDAreaCodeSelectView.this.mAreaCodeHelper.onAreaCodeSelected((AreaBeanVO) arrayList.get(i3));
                        return true;
                    }
                    return true;
                }
            });
            return;
        }
        LinearLayout linearLayout2 = this.mCommonArea;
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
    }

    private AreaBeanVO[] getAreaCodeStringArray(ArrayList<AreaBeanVO> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return new AreaBeanVO[0];
        }
        AreaBeanVO[] areaBeanVOArr = new AreaBeanVO[arrayList.size()];
        arrayList.toArray(areaBeanVOArr);
        return areaBeanVOArr;
    }

    private void hiddenSlideBar() {
        JDSlideBar jDSlideBar = this.jdSlideBar;
        if (jDSlideBar != null) {
            jDSlideBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private void initView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.jd_area_code_layout, (ViewGroup) this, true);
        this.mRootView = inflate;
        this.mListView = (StickyHeaderListView) inflate.findViewById(R.id.list_area_code);
        this.mLoadingView = this.mRootView.findViewById(R.id.pd_info_loading_pb_overseas);
        this.mFlowLayout = (TagFlowLayout) this.mRootView.findViewById(R.id.address_flow);
        this.mCommonArea = (LinearLayout) this.mRootView.findViewById(R.id.common_area_ll);
        this.mErrorLayout = (RelativeLayout) this.mRootView.findViewById(R.id.area_code_error_ll);
        LinearLayout linearLayout = (LinearLayout) this.mRootView.findViewById(R.id.area_code_error_view);
        this.mErrorView = linearLayout;
        this.mErrorImage = (ImageView) linearLayout.findViewById(R.id.jd_tip_image);
        this.mErrorText = (TextView) this.mErrorView.findViewById(R.id.jd_tip_tv1);
        this.mErrorImage.setImageResource(R.drawable.y_03);
        this.mErrorText.setText(R.string.area_code_network_error);
        this.jdSlideBar = (JDSlideBar) this.mRootView.findViewById(R.id.slide_bar_o);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.slide_note_tv_o);
        this.jdSlideBarNoteTv = textView;
        this.jdSlideBar.setNotesTextView(textView);
        this.jdSlideBar.setCallback(this);
        this.mRootView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDAreaCodeSelectView.this.mAreaCodeHelper != null) {
                    JDAreaCodeSelectView.this.mAreaCodeHelper.close();
                }
            }
        });
        hideProgress();
        hideErrorView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeader(int i2) {
        MyAdapter myAdapter = this.mAdapter;
        return myAdapter != null && myAdapter.isHeaderView(i2);
    }

    private boolean isNeedShowSlideBar() {
        return true;
    }

    private void showProgress() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void showSelector(int i2, AreaBeanVO[] areaBeanVOArr, int i3) {
        if (this.mAdapter == null) {
            this.mAdapter = new MyAdapter();
            this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j2) {
                    if (Log.D) {
                        Log.d(JDAreaCodeSelectView.TAG, "onListItemClick");
                    }
                    if (JDAreaCodeSelectView.this.mIsDestroy || JDAreaCodeSelectView.this.isHeader(i4)) {
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - JDAreaCodeSelectView.this.mClickTime < 1000) {
                        return;
                    }
                    JDAreaCodeSelectView.this.mClickTime = currentTimeMillis;
                    int changeToDataPosition = JDAreaCodeSelectView.this.mAdapter.changeToDataPosition(i4);
                    if (changeToDataPosition == -1) {
                        return;
                    }
                    JDAreaCodeSelectView.this.mAdapter.setSelected(i4);
                    JDAreaCodeSelectView.this.mListView.postInvalidate();
                    if (JDAreaCodeSelectView.this.mAreaCodeHelper != null) {
                        JDAreaCodeSelectView.this.mAreaCodeHelper.onAreaCodeSelected(JDAreaCodeSelectView.this.mAreaCodes[changeToDataPosition]);
                    }
                }
            });
        }
        updateContent(i3, areaBeanVOArr);
        updateSlideBarStatus();
        updateAddressUi();
        StickyHeaderListView stickyHeaderListView = this.mListView;
        if (stickyHeaderListView != null) {
            stickyHeaderListView.setAdapter((ListAdapter) this.mAdapter);
        }
    }

    private void showSlideBar() {
        JDSlideBar jDSlideBar = this.jdSlideBar;
        if (jDSlideBar != null) {
            jDSlideBar.setVisibility(0);
        }
    }

    private void updateAddressUi() {
        MyAdapter myAdapter = this.mAdapter;
        if (myAdapter != null) {
            myAdapter.changeHeaderStatus(isNeedShowSlideBar());
            this.mAdapter.notifyDataSetChanged();
        }
        StickyHeaderListView stickyHeaderListView = this.mListView;
        if (stickyHeaderListView != null) {
            stickyHeaderListView.setStickyHeaders(isNeedShowSlideBar());
        }
    }

    private void updateContent(int i2, AreaBeanVO[] areaBeanVOArr) {
        if (this.mAdapter != null) {
            if (isNeedShowSlideBar()) {
                StickyHeaderData stickyHeaderData = new StickyHeaderData();
                this.stickyHeaderData = stickyHeaderData;
                AreaBeanVO[] updateDataAreaCode = JDCityDataUtils.updateDataAreaCode(areaBeanVOArr, stickyHeaderData);
                this.jdSlideBar.setDataAndAutoHeight(this.stickyHeaderData.getLetterList());
                this.mAdapter.setContent(i2, updateDataAreaCode, this.stickyHeaderData.getHeaderList(), this.stickyHeaderData.getRealToDataMap(), this.stickyHeaderData.getDataToRealMap());
                return;
            }
            this.mAdapter.setContent(i2, areaBeanVOArr);
        }
    }

    private void updateSlideBarStatus() {
        if (isNeedShowSlideBar()) {
            showSlideBar();
        } else {
            hiddenSlideBar();
        }
    }

    public void destroy() {
        this.mIsDestroy = true;
        this.mListView = null;
        this.mAdapter = null;
        this.mRootView = null;
        this.mErrorLayout = null;
        ArrayList<AreaBeanVO> arrayList = this.areaCodeList;
        if (arrayList != null) {
            arrayList.clear();
        }
        StickyHeaderData stickyHeaderData = this.stickyHeaderData;
        if (stickyHeaderData != null) {
            stickyHeaderData.release();
        }
        JDSlideBar jDSlideBar = this.jdSlideBar;
        if (jDSlideBar != null) {
            jDSlideBar.release();
        }
        JDCityDataUtils.release();
    }

    protected void doAreaCode(ArrayList<AreaBeanVO> arrayList) {
        if (arrayList != null) {
            this.areaCodeList = new ArrayList<>(arrayList);
        }
        AreaBeanVO[] areaCodeStringArray = getAreaCodeStringArray(this.areaCodeList);
        if (areaCodeStringArray.length > 0) {
            showSelector(1, areaCodeStringArray, 0);
            this.mAreaCodes = areaCodeStringArray;
        }
    }

    public void hideErrorView() {
        this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.7
            @Override // java.lang.Runnable
            public void run() {
                if (JDAreaCodeSelectView.this.mErrorLayout != null) {
                    JDAreaCodeSelectView.this.mErrorLayout.setVisibility(8);
                }
            }
        });
    }

    public void initData(ArrayList<AreaBeanVO> arrayList, ArrayList<AreaBeanVO> arrayList2) {
        addFlowView(arrayList2);
        doAreaCode(arrayList);
    }

    public void initDataNew(AreaCodeHelper areaCodeHelper) {
        this.mAreaCodeHelper = areaCodeHelper;
    }

    @Override // com.jingdong.common.ui.JDSlideBar.ISlideBarTouchChangeLisener
    public void onSlideBarSelected(String str) {
        Map<String, Integer> strToPosMap;
        StickyHeaderData stickyHeaderData = this.stickyHeaderData;
        if (stickyHeaderData == null || (strToPosMap = stickyHeaderData.getStrToPosMap()) == null) {
            return;
        }
        Integer num = strToPosMap.get(str);
        int intValue = num != null ? num.intValue() : -1;
        StickyHeaderListView stickyHeaderListView = this.mListView;
        if (stickyHeaderListView == null || intValue == -1) {
            return;
        }
        stickyHeaderListView.smoothScrollToPositionFromTop(intValue, 0, 300);
    }

    public void showAreaCode() {
        if (this.mAreaCodeHelper != null) {
            showProgress();
            this.mAreaCodeHelper.loadAreaCode(this.mOnAreaCodeLoadCompletedListener);
        }
    }

    public void showErrorView() {
        this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.6
            @Override // java.lang.Runnable
            public void run() {
                if (JDAreaCodeSelectView.this.mErrorLayout != null) {
                    JDAreaCodeSelectView.this.mErrorLayout.setVisibility(0);
                }
            }
        });
    }

    public JDAreaCodeSelectView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class MyAdapter extends StickyHeaderAdapter {
        AreaBeanVO[] data;
        int selectedPosition;

        /* loaded from: classes6.dex */
        class HeaderViewHolder {
            public TextView headerTv;
            public View itemView;
            public LinearLayout llRoot;

            public HeaderViewHolder(View view) {
                this.itemView = view;
                this.llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
                this.headerTv = (TextView) view.findViewById(R.id.letter_header_tv);
            }
        }

        /* loaded from: classes6.dex */
        class ViewHolder {
            ImageView img1;
            View layout1;
            TextView txt1;

            ViewHolder() {
            }
        }

        private MyAdapter() {
            this.selectedPosition = 0;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            AreaBeanVO[] areaBeanVOArr = this.data;
            if (areaBeanVOArr == null) {
                return 0;
            }
            return areaBeanVOArr.length;
        }

        @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
        public View getHeaderView(int i2, View view, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(JDAreaCodeSelectView.this.getContext()).inflate(R.layout.jd_address_layout_header, viewGroup, false);
            HeaderViewHolder headerViewHolder = new HeaderViewHolder(inflate);
            headerViewHolder.llRoot.setBackgroundColor(JDAreaCodeSelectView.this.mContext.getResources().getColor(R.color.white));
            headerViewHolder.headerTv.setText(this.data[i2].getName());
            return inflate;
        }

        @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
        public List<Integer> getHeaders() {
            return this.headers;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            AreaBeanVO[] areaBeanVOArr = this.data;
            if (areaBeanVOArr == null) {
                return null;
            }
            return areaBeanVOArr[i2];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // com.jingdong.common.stickyListView.StickyHeaderAdapter
        public View getItemView(int i2, View view, ViewGroup viewGroup) {
            AreaBeanVO areaBeanVO;
            if (view == null) {
                view = LayoutInflater.from(JDAreaCodeSelectView.this.mContext).inflate(R.layout.jd_area_code_layout_item, (ViewGroup) null, true);
                ViewHolder viewHolder = new ViewHolder();
                View findViewById = view.findViewById(R.id.l_layout_1);
                viewHolder.layout1 = findViewById;
                viewHolder.txt1 = (TextView) findViewById.findViewById(R.id.txt_1);
                viewHolder.img1 = (ImageView) viewHolder.layout1.findViewById(R.id.img_1);
                view.setTag(viewHolder);
            }
            if (view.getTag() instanceof ViewHolder) {
                ViewHolder viewHolder2 = (ViewHolder) view.getTag();
                if (i2 > getCount() || (areaBeanVO = this.data[i2]) == null) {
                    return view;
                }
                viewHolder2.layout1.setVisibility(0);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder2.layout1.getLayoutParams();
                layoutParams.bottomMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 12.0f);
                layoutParams.topMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 12.0f);
                int i3 = i2 + 1;
                if (i3 < getCount() && getItemViewType(i3) == 0) {
                    layoutParams.bottomMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 24.0f);
                } else if (i3 == getCount()) {
                    layoutParams.bottomMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 24.0f);
                }
                int i4 = i2 - 1;
                if (i4 > 0 && getItemViewType(i4) == 0) {
                    layoutParams.topMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 24.0f);
                } else if (i2 == 0) {
                    layoutParams.topMargin = DpiUtil.dip2px(JDAreaCodeSelectView.this.mContext, 24.0f);
                }
                viewHolder2.layout1.setLayoutParams(layoutParams);
                viewHolder2.txt1.setText(areaBeanVO.getName() + " +" + areaBeanVO.getAreaCode());
                viewHolder2.txt1.setTextColor(JDAreaCodeSelectView.this.mContext.getResources().getColor(R.color.c_000000));
                if (i2 == this.selectedPosition) {
                    viewHolder2.txt1.setTypeface(Typeface.defaultFromStyle(1));
                    viewHolder2.img1.setVisibility(0);
                    viewHolder2.img1.setImageResource(R.drawable.jd_address_select);
                } else {
                    viewHolder2.txt1.setTypeface(Typeface.defaultFromStyle(0));
                    viewHolder2.img1.setVisibility(8);
                }
            }
            return view;
        }

        void setContent(int i2, AreaBeanVO[] areaBeanVOArr) {
            this.selectedPosition = i2;
            this.data = areaBeanVOArr;
            notifyDataSetChanged();
        }

        void setSelected(int i2) {
            this.selectedPosition = i2;
            notifyDataSetChanged();
        }

        void setContent(int i2, AreaBeanVO[] areaBeanVOArr, List<Integer> list, SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2) {
            this.data = areaBeanVOArr;
            this.headers = list;
            this.realToDataMap = sparseIntArray;
            this.dataTorealMap = sparseIntArray2;
            changeHeaderStatus(true);
            notifyDataSetChanged();
        }
    }

    public JDAreaCodeSelectView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsDestroy = false;
        this.mClickTime = 0L;
        this.mHandler = new Handler();
        this.areaCodeList = null;
        this.mOnAreaCodeLoadCompletedListener = new OnAreaCodeLoadCompletedListener() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.2
            @Override // com.jingdong.common.ui.JDAreaCodeSelectView.OnAreaCodeLoadCompletedListener
            public void onAreaCodeLoadCompleted(final boolean z, final ArrayList<AreaBeanVO> arrayList, final ArrayList<AreaBeanVO> arrayList2) {
                JDAreaCodeSelectView.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.ui.JDAreaCodeSelectView.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JDAreaCodeSelectView.this.mIsDestroy) {
                            return;
                        }
                        ArrayList arrayList3 = arrayList2;
                        if (arrayList3 != null && arrayList != null && z) {
                            JDAreaCodeSelectView.this.addFlowView(arrayList3);
                            JDAreaCodeSelectView.this.doAreaCode(arrayList);
                        }
                        JDAreaCodeSelectView.this.hideProgress();
                        JDAreaCodeSelectView.this.hideErrorView();
                    }
                });
            }
        };
        this.mContext = context;
        initView();
    }
}

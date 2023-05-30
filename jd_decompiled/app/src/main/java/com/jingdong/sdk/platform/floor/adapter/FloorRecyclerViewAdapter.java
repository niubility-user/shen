package com.jingdong.sdk.platform.floor.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.FloorBuilder;
import com.jingdong.sdk.platform.floor.FloorBuilderProxy;
import com.jingdong.sdk.platform.floor.FloorManagerProxy;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.entity.FloorShowData;
import com.jingdong.sdk.platform.floor.floors.CommonFloor;
import com.jingdong.sdk.platform.floor.listener.OnFloorAttachToWindowListener;
import com.jingdong.sdk.platform.floor.listener.OnFloorShowedListener;
import com.jingdong.sdk.platform.floor.listener.OnRecycleViewScrollListener;
import com.jingdong.sdk.platform.floor.utils.DefaultFloorCreator;
import com.jingdong.sdk.platform.floor.utils.FloorCreator;
import com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator;
import com.jingdong.sdk.platform.manager.ViewHolderManagerProxy;
import com.jingdong.sdk.platform.utils.PlatformTools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes10.dex */
public class FloorRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FloorRecycleViewAdapter";
    private int bpConfigPuppetIndex;
    private SyncEventBus.EventBusListener eventBusListener;
    private boolean isDestroy;
    private boolean listenEdgeChange;
    protected BaseFloorData mBaseFloorData;
    private Context mContext;
    private ArrayList<BaseFloor> mCreateFloors;
    private ArrayList<BaseTemplateEntity> mDataList;
    private ViewGroup mDrawViewGroup;
    private HashMap<String, BaseFloor> mExistFloor;
    private FloorBuilder mFloorBuilder;
    private FloorCreator mFloorCreator;
    private OnFloorAttachToWindowListener mOnFloorAttachToWindowListener;
    private OnFloorShowedListener mOnFloorChange;
    private boolean mPreLoadFloor;
    private RecyclerView mRecycleView;
    private final ArrayList<String> puppetIds;

    public FloorRecyclerViewAdapter(Context context, BaseFloorData baseFloorData, FloorCreator floorCreator, RecyclerView recyclerView) {
        this.mDataList = new ArrayList<>();
        this.isDestroy = false;
        this.mCreateFloors = new ArrayList<>();
        this.mExistFloor = new HashMap<>();
        this.mPreLoadFloor = false;
        this.puppetIds = new ArrayList<>();
        this.bpConfigPuppetIndex = -1;
        this.listenEdgeChange = false;
        this.mOnFloorChange = new OnFloorShowedListener() { // from class: com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter.1
            {
                FloorRecyclerViewAdapter.this = this;
            }

            @Override // com.jingdong.sdk.platform.floor.listener.OnFloorShowedListener
            public void onFloorChange(final FloorShowData floorShowData) {
                SyncEventBus.postToMainThread(new Runnable() { // from class: com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        FloorShowData floorShowData2;
                        BaseTemplateEntity baseTemplateEntity;
                        if (FloorRecyclerViewAdapter.this.isDestroy || (floorShowData2 = floorShowData) == null) {
                            return;
                        }
                        try {
                            if (!floorShowData2.isUpdate) {
                                FloorRecyclerViewAdapter.this.showRealFloors(floorShowData2.floors);
                                return;
                            }
                            if (floorShowData2.isAdd && (baseTemplateEntity = floorShowData2.floor) != null) {
                                FloorRecyclerViewAdapter.this.showFloorInd(baseTemplateEntity);
                            }
                            FloorRecyclerViewAdapter.this.updateFloors(floorShowData.floors);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
        this.eventBusListener = new SyncEventBus.EventBusListener() { // from class: com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter.2
            {
                FloorRecyclerViewAdapter.this = this;
            }

            @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
            public String getActionName() {
                return BaseFloorConstant.ACTION_FLOOR_BASE;
            }

            @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
            public Object getData(String str) {
                return null;
            }

            @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
            public boolean isValid() {
                return true;
            }

            @Override // com.jingdong.sdk.aac.util.SyncEventBus.EventBusListener
            public void onEvent(String str, Object obj) {
                BaseTemplateEntity baseTemplateEntity;
                if (!FloorRecyclerViewAdapter.this.isDestroy && TextUtils.equals(BaseFloorConstant.EVENT_FLOOR_SHOW_FLOOR, str)) {
                    FloorShowData floorShowData = null;
                    if (obj != null && (obj instanceof FloorShowData)) {
                        floorShowData = (FloorShowData) obj;
                    }
                    if (floorShowData != null) {
                        try {
                            if (!floorShowData.isUpdate) {
                                FloorRecyclerViewAdapter.this.showRealFloors(floorShowData.floors);
                                return;
                            }
                            if (floorShowData.isAdd && (baseTemplateEntity = floorShowData.floor) != null) {
                                FloorRecyclerViewAdapter.this.showFloorInd(baseTemplateEntity);
                            }
                            FloorRecyclerViewAdapter.this.updateFloors(floorShowData.floors);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        };
        this.mContext = context;
        this.mRecycleView = recyclerView;
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(new OnRecycleViewScrollListener(recyclerView, this, baseFloorData));
        }
        this.mBaseFloorData = baseFloorData;
        this.mFloorBuilder = FloorBuilderProxy.getFloorBuilder(baseFloorData, this.mOnFloorChange);
        this.mFloorCreator = floorCreator;
        SyncEventBus.getInstances(this.mBaseFloorData.mManageKey).register(this.eventBusListener);
    }

    private BaseFloor getExistFloorById(String str) {
        HashMap<String, BaseFloor> hashMap = this.mExistFloor;
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        return this.mExistFloor.get(str);
    }

    private String getPuppetIdByType(int i2) {
        int i3;
        if (i2 < 14000 || i2 >= 20000 || this.puppetIds.size() <= (i3 = i2 - this.bpConfigPuppetIndex)) {
            return null;
        }
        return this.puppetIds.get(i3);
    }

    private void preDrawAllFloors() {
        SyncEventBus.postToMainThread(new Runnable() { // from class: com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter.3
            {
                FloorRecyclerViewAdapter.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseFloor orCreateExitFloor;
                if (FloorRecyclerViewAdapter.this.isDestroy || FloorRecyclerViewAdapter.this.mDataList == null || FloorRecyclerViewAdapter.this.mDataList.isEmpty()) {
                    return;
                }
                FloorRecyclerViewAdapter floorRecyclerViewAdapter = FloorRecyclerViewAdapter.this;
                if (floorRecyclerViewAdapter.mBaseFloorData.mScrollState == 0) {
                    Iterator it = floorRecyclerViewAdapter.mDataList.iterator();
                    while (it.hasNext()) {
                        BaseTemplateEntity baseTemplateEntity = (BaseTemplateEntity) it.next();
                        if (baseTemplateEntity != null && !baseTemplateEntity.isShow && !FloorManagerProxy.getInstances(FloorRecyclerViewAdapter.this.mBaseFloorData.mMoudleName).isFloorReUsed(baseTemplateEntity.mId) && (orCreateExitFloor = FloorRecyclerViewAdapter.this.getOrCreateExitFloor(baseTemplateEntity.mId)) != null) {
                            orCreateExitFloor.onDataShowed(baseTemplateEntity);
                            orCreateExitFloor.preDrawFloor(FloorRecyclerViewAdapter.this.mDrawViewGroup);
                        }
                    }
                }
            }
        }, 0L);
    }

    public void showFloorInd(BaseTemplateEntity baseTemplateEntity) {
        BaseFloor orCreateExitFloor = getOrCreateExitFloor(baseTemplateEntity.mId);
        if (orCreateExitFloor != null) {
            orCreateExitFloor.onDataShowed(baseTemplateEntity);
            orCreateExitFloor.preDrawFloor(this.mDrawViewGroup);
        }
    }

    public void showRealFloors(ArrayList<BaseTemplateEntity> arrayList) {
        disableAllFloor();
        showFloors(arrayList, true);
    }

    public BaseFloor createFloorReal(ViewGroup viewGroup, String str) {
        HashMap<String, BaseFloor> hashMap;
        BaseFloor createFloor = this.mFloorCreator.createFloor(this.mContext, this.mBaseFloorData, str, FloorManagerProxy.getInstances(this.mBaseFloorData.mMoudleName).getClassById(str), viewGroup);
        if (createFloor != null) {
            this.mCreateFloors.add(createFloor);
            if (!FloorManagerProxy.getInstances(this.mBaseFloorData.mMoudleName).isFloorReUsed(str) && (hashMap = this.mExistFloor) != null) {
                hashMap.put(str, createFloor);
            }
        }
        return createFloor;
    }

    public void disableAllFloor() {
        ArrayList<BaseFloor> arrayList = this.mCreateFloors;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (PlatformTools.D) {
            PlatformTools.d(TAG, "onDestroy == mCreateFloors.size = " + this.mCreateFloors.size());
        }
        Iterator<BaseFloor> it = this.mCreateFloors.iterator();
        while (it.hasNext()) {
            BaseFloor next = it.next();
            if (next != null) {
                next.disable();
            }
        }
    }

    public ArrayList<BaseFloor> getAllFloor() {
        return this.mCreateFloors;
    }

    public List<BaseTemplateEntity> getData() {
        ArrayList<BaseTemplateEntity> arrayList = this.mDataList;
        if (arrayList == null) {
            return null;
        }
        return Collections.unmodifiableList(arrayList);
    }

    public BaseTemplateEntity getItem(int i2) {
        ArrayList<BaseTemplateEntity> arrayList = this.mDataList;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return null;
        }
        return this.mDataList.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        ArrayList<BaseTemplateEntity> arrayList = this.mDataList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        BaseTemplateEntity item = getItem(i2);
        if (item != null) {
            if (BaseFloorConstant.PLATFORM_FLOOR_PUPPET.equals(item.mId) && !TextUtils.isEmpty(item.tId)) {
                if (this.bpConfigPuppetIndex == -1) {
                    int floorTypeById = PlatformHelper.getFloorTypeById(this.mBaseFloorData.mMoudleName, BaseFloorConstant.PLATFORM_FLOOR_PUPPET);
                    this.bpConfigPuppetIndex = floorTypeById;
                    if (floorTypeById >= 10000 && floorTypeById < 14000) {
                        this.bpConfigPuppetIndex = floorTypeById + 4000;
                    }
                }
                if (this.bpConfigPuppetIndex < 14000) {
                    return 0;
                }
                if (this.puppetIds.contains(item.tId)) {
                    return this.bpConfigPuppetIndex + this.puppetIds.indexOf(item.tId);
                }
                this.puppetIds.add(item.tId);
                return (this.bpConfigPuppetIndex + this.puppetIds.size()) - 1;
            }
            int i3 = item.itemViewType;
            return i3 > 0 ? i3 : PlatformHelper.getFloorTypeById(this.mBaseFloorData.mMoudleName, item.mId);
        }
        return 0;
    }

    public BaseFloor getOrCreateExitFloor(String str) {
        BaseFloor existFloorById = getExistFloorById(str);
        return existFloorById == null ? createFloorReal(this.mRecycleView, str) : existFloorById;
    }

    public void hideFloor(BaseTemplateEntity baseTemplateEntity) {
        FloorBuilder floorBuilder = this.mFloorBuilder;
        if (floorBuilder != null) {
            floorBuilder.hideFloor(baseTemplateEntity);
        }
    }

    public boolean isListenEdgeChange() {
        return this.listenEdgeChange;
    }

    protected boolean isUesdDiff() {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onAttachedToRecyclerView ");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onBindViewHolder ");
        }
        if (viewHolder == null || !FloorViewHolder.class.isAssignableFrom(viewHolder.getClass())) {
            return;
        }
        ((FloorViewHolder) viewHolder).showData(getItem(i2), null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        String idByType;
        BaseFloor existFloorById;
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onCreateViewHolder " + i2 + " item count == null " + getItemCount());
        }
        String puppetIdByType = getPuppetIdByType(i2);
        if (!TextUtils.isEmpty(puppetIdByType)) {
            idByType = "bpConfig_" + puppetIdByType;
        } else {
            idByType = ViewHolderManagerProxy.getInstance().getIdByType(i2);
        }
        if (!TextUtils.isEmpty(idByType)) {
            existFloorById = new CommonFloor(this.mContext, this.mBaseFloorData, idByType, viewGroup);
        } else {
            String idByType2 = FloorManagerProxy.getInstances(this.mBaseFloorData.mMoudleName).getIdByType(i2);
            if (PlatformTools.D) {
                PlatformTools.d(TAG, " onCreateViewHolder mId = " + idByType2 + " _ " + i2 + " item count == null " + getItemCount());
            }
            existFloorById = getExistFloorById(idByType2);
            if (existFloorById == null || existFloorById.isUsedByRecycleView) {
                existFloorById = createFloorReal(viewGroup, idByType2);
            }
        }
        if (existFloorById != null) {
            existFloorById.isUsedByRecycleView = true;
            View view = existFloorById.getView();
            if (view != null && view.getParent() != null) {
                if (PlatformTools.D) {
                    PlatformTools.d(TAG, " onCreateViewHolder  remove parent");
                }
                if (view.getParent() instanceof ViewGroup) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
            }
            return new FloorViewHolder(existFloorById);
        }
        return new FloorViewHolder(new FrameLayout(this.mContext), viewGroup);
    }

    public void onDestroy() {
        this.isDestroy = true;
        ArrayList<BaseTemplateEntity> arrayList = this.mDataList;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.mContext = null;
        this.mRecycleView = null;
        this.mDrawViewGroup = null;
        HashMap<String, BaseFloor> hashMap = this.mExistFloor;
        if (hashMap != null) {
            hashMap.clear();
        }
        ArrayList<BaseFloor> arrayList2 = this.mCreateFloors;
        if (arrayList2 == null || arrayList2.isEmpty()) {
            return;
        }
        if (PlatformTools.D) {
            PlatformTools.d(TAG, "onDestroy == mCreateFloors.size = " + this.mCreateFloors.size());
        }
        Iterator<BaseFloor> it = this.mCreateFloors.iterator();
        while (it.hasNext()) {
            BaseFloor next = it.next();
            if (next != null) {
                next.onDestroy();
            }
        }
        this.mCreateFloors.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onDetachedFromRecyclerView ");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    public void onShowFloors(ArrayList<BaseTemplateEntity> arrayList, boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        FloorViewHolder floorViewHolder;
        if (viewHolder == null || !FloorViewHolder.class.isAssignableFrom(viewHolder.getClass()) || (floorViewHolder = (FloorViewHolder) viewHolder) == null) {
            return;
        }
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onViewAttachedToWindow " + floorViewHolder.getFloorName());
        }
        floorViewHolder.onAttachToWindow();
        OnFloorAttachToWindowListener onFloorAttachToWindowListener = this.mOnFloorAttachToWindowListener;
        if (onFloorAttachToWindowListener != null) {
            onFloorAttachToWindowListener.onAttachToWindow(floorViewHolder.baseFloor, floorViewHolder.getEntity());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        FloorViewHolder floorViewHolder;
        if (viewHolder == null || !FloorViewHolder.class.isAssignableFrom(viewHolder.getClass()) || (floorViewHolder = (FloorViewHolder) viewHolder) == null) {
            return;
        }
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onViewDetachedFromWindow " + floorViewHolder.getFloorName());
        }
        floorViewHolder.onDetachFromWindow();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null || !PlatformTools.D) {
            return;
        }
        PlatformTools.d(TAG, " holder.isRecyclable() " + viewHolder.isRecyclable());
    }

    public void setFloorTemplateCreator(FloorTemplateCreator floorTemplateCreator) {
        FloorBuilder floorBuilder = this.mFloorBuilder;
        if (floorBuilder != null) {
            floorBuilder.setFloorTemplateCreator(floorTemplateCreator);
        }
    }

    public void setListenEdgeChange(boolean z) {
        this.listenEdgeChange = z;
    }

    public void setOnFloorAttachToWindowListener(OnFloorAttachToWindowListener onFloorAttachToWindowListener) {
        this.mOnFloorAttachToWindowListener = onFloorAttachToWindowListener;
    }

    public void setPreLoadFloor(boolean z) {
        this.mPreLoadFloor = z;
    }

    public void setPredrawViewGroup(ViewGroup viewGroup) {
        this.mDrawViewGroup = viewGroup;
    }

    public void showFloor(BaseTemplateEntity baseTemplateEntity) {
        FloorBuilder floorBuilder = this.mFloorBuilder;
        if (floorBuilder != null) {
            floorBuilder.showFloor(baseTemplateEntity);
        }
    }

    public void showFloors(ArrayList<BaseTemplateEntity> arrayList) {
        FloorBuilder floorBuilder = this.mFloorBuilder;
        if (floorBuilder != null) {
            floorBuilder.showAllFloors(arrayList);
        }
    }

    public void updateFloors() {
        FloorBuilder floorBuilder = this.mFloorBuilder;
        if (floorBuilder != null) {
            floorBuilder.updateFloors();
        }
    }

    private synchronized void showFloors(ArrayList<BaseTemplateEntity> arrayList, boolean z) {
        onShowFloors(arrayList, z);
        if (isUesdDiff()) {
            if (arrayList != null && !arrayList.isEmpty()) {
                if (this.mDataList == null) {
                    this.mDataList = new ArrayList<>();
                }
                DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DiffCallback(this.mDataList, arrayList));
                this.mDataList.clear();
                this.mDataList.addAll(arrayList);
                calculateDiff.dispatchUpdatesTo(this);
            } else {
                this.mDataList = null;
                notifyDataSetChanged();
            }
        } else {
            if (arrayList != null && !arrayList.isEmpty()) {
                this.mDataList = new ArrayList<>(arrayList);
            } else {
                this.mDataList = null;
            }
            notifyDataSetChanged();
            if (this.mPreLoadFloor && z) {
                preDrawAllFloors();
            }
        }
    }

    public void updateFloors(ArrayList<BaseTemplateEntity> arrayList) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, "updateFloors _ dataList count = " + arrayList.size());
        }
        showFloors(arrayList, false);
    }

    /* loaded from: classes10.dex */
    public class FloorViewHolder extends RecyclerView.ViewHolder {
        private BaseFloor baseFloor;
        private BaseTemplateEntity mEntity;
        private ViewGroup mParent;
        private FrameLayout mRootView;
        private int outTopState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FloorViewHolder(BaseFloor baseFloor) {
            super(baseFloor.getView());
            FloorRecyclerViewAdapter.this = r1;
            this.baseFloor = null;
            this.outTopState = -1;
            this.baseFloor = baseFloor;
        }

        public String getFloorName() {
            if (this.baseFloor != null) {
                return "ViewHolder = " + this.baseFloor.getClass().getSimpleName();
            }
            return "null FloorHelper";
        }

        private int getOutTopState(boolean z) {
            return !z ? 1 : 0;
        }

        public void onAttachToWindow() {
            BaseFloor baseFloor = this.baseFloor;
            if (baseFloor != null) {
                baseFloor.onAttachToWindow();
            }
            this.outTopState = -1;
        }

        public void onDetachFromWindow() {
            BaseFloor baseFloor = this.baseFloor;
            if (baseFloor != null) {
                baseFloor.onDetachFromWindow();
            }
        }

        public void showData(BaseTemplateEntity baseTemplateEntity, Object obj) {
            if (baseTemplateEntity == null) {
                return;
            }
            this.mEntity = baseTemplateEntity;
            if (PlatformTools.D) {
                StringBuilder sb = new StringBuilder();
                sb.append(" showData  baseFloor == null ");
                sb.append(this.baseFloor == null);
                PlatformTools.d(FloorRecyclerViewAdapter.TAG, sb.toString());
            }
            BaseFloor baseFloor = this.baseFloor;
            if (baseFloor != null) {
                baseFloor.setFloorPosition(getAdapterPosition());
                this.baseFloor.onDataShowed(baseTemplateEntity, obj);
                return;
            }
            ViewGroup viewGroup = this.mParent;
            if (viewGroup != null) {
                BaseFloor createFloorReal = FloorRecyclerViewAdapter.this.createFloorReal(viewGroup, baseTemplateEntity.mId);
                if (createFloorReal != null) {
                    this.baseFloor = createFloorReal;
                    createFloorReal.setFloorPosition(getAdapterPosition());
                    FrameLayout frameLayout = this.mRootView;
                    if (frameLayout != null) {
                        if (frameLayout.getChildCount() != 0) {
                            this.mRootView.removeAllViews();
                        }
                        this.mRootView.addView(createFloorReal.getView());
                    }
                    createFloorReal.onDataShowed(baseTemplateEntity, obj);
                    this.mParent = null;
                    return;
                }
                FloorRecyclerViewAdapter.this.hideFloor(baseTemplateEntity);
            }
        }

        public void changeEdge(boolean z) {
            if (this.outTopState != getOutTopState(z)) {
                BaseFloor baseFloor = this.baseFloor;
                if (baseFloor != null) {
                    baseFloor.onEdgeChange(z);
                }
                this.outTopState = getOutTopState(z);
            }
        }

        public BaseTemplateEntity getEntity() {
            return this.mEntity;
        }

        public Object getFloorEntity() {
            BaseFloor baseFloor = this.baseFloor;
            if (baseFloor != null) {
                return baseFloor.getBaseEntity();
            }
            return null;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FloorViewHolder(FrameLayout frameLayout, ViewGroup viewGroup) {
            super(frameLayout);
            FloorRecyclerViewAdapter.this = r1;
            this.baseFloor = null;
            this.outTopState = -1;
            this.mRootView = frameLayout;
            this.mParent = viewGroup;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, " onBindViewHolder ");
        }
        if (list != null && list.isEmpty()) {
            onBindViewHolder(viewHolder, i2);
        } else if (viewHolder == null || !FloorViewHolder.class.isAssignableFrom(viewHolder.getClass())) {
        } else {
            ((FloorViewHolder) viewHolder).showData(getItem(i2), list.get(0));
        }
    }

    public FloorRecyclerViewAdapter(Context context, BaseFloorData baseFloorData, RecyclerView recyclerView) {
        this(context, baseFloorData, DefaultFloorCreator.newInstance(), recyclerView);
    }
}

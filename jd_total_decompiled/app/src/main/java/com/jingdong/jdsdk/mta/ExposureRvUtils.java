package com.jingdong.jdsdk.mta;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Keep
/* loaded from: classes14.dex */
public class ExposureRvUtils {
    private static final float BOTTOM = 1.0f;
    public static final String EXPOSURE = "exposure";
    public static final String HIDE = "hide";
    private static final float LEFT = 3.0f;
    public static final int REPEAT_MODE = 1;
    private static final float RIGHT = 4.0f;
    public static final int SINGLE_MODE = 0;
    private static final String TAG = "ExposureRvUtils";
    private static final float TOP = 2.0f;
    private long mEndScrollTime;
    private int mExposureMode;
    private ExposureStateListener mExposureStateListener;
    private final HashMap<Integer, ItemExposureStateBean> mExposureStateMap;
    private final List<ItemExposureHideBean> mInstantList;
    private long mLastListenTime;
    private int mLayoutDirection;
    private RecyclerView mRecyclerView;
    private float mScrollDirection;
    private float mScrollDistance;
    private float mScrollDistanceX;
    private float mScrollDistanceY;
    private ScrollParamListener mScrollParamListener;
    private long mStartScrollTime;
    private final List<Integer> mVisiblePosList;

    @Keep
    /* loaded from: classes14.dex */
    public interface ExposureStateListener {
        void onExposure(int i2, long j2);

        void onHide(int i2, long j2, long j3, long j4);
    }

    @Keep
    /* loaded from: classes14.dex */
    public static class ItemExposureHideBean {
        public long exposureDurationTime;
        public long exposureTime;
        public long hideTime;
        public int pos;
        public String type;
    }

    @Keep
    /* loaded from: classes14.dex */
    public static class ItemScrollBean {
        int distance;
        int pos;
    }

    @Keep
    /* loaded from: classes14.dex */
    public interface ScrollParamListener {
        void onFinish(long j2, long j3, long j4, float f2, float f3, float f4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ExposureRvUtils.this.getHalfExposureHideList();
        }
    }

    /* loaded from: classes14.dex */
    class b implements Comparator<Integer> {
        b(ExposureRvUtils exposureRvUtils) {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(Integer num, Integer num2) {
            return num2.intValue() - num.intValue();
        }
    }

    /* loaded from: classes14.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ExposureRvUtils.this.getHalfExposureHideList();
        }
    }

    public ExposureRvUtils() {
        this.mLastListenTime = 0L;
        this.mExposureStateMap = new HashMap<>();
        this.mStartScrollTime = 0L;
        this.mEndScrollTime = 0L;
        this.mScrollDistance = 0.0f;
        this.mScrollDistanceX = 0.0f;
        this.mScrollDistanceY = 0.0f;
        this.mExposureMode = 1;
        this.mLayoutDirection = 1;
        this.mScrollDirection = 0.0f;
        this.mVisiblePosList = new ArrayList();
        this.mInstantList = new ArrayList();
    }

    private int[] getFirstLastPosArray(RecyclerView.LayoutManager layoutManager) {
        int i2;
        int i3;
        if (this.mRecyclerView == null) {
            return null;
        }
        this.mVisiblePosList.clear();
        int[] iArr = new int[2];
        if (layoutManager == null) {
            return iArr;
        }
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            i3 = linearLayoutManager.findFirstVisibleItemPosition();
            i2 = linearLayoutManager.findLastVisibleItemPosition();
            for (int i4 = i3; i4 <= i2; i4++) {
                if (isVisible(layoutManager, i4)) {
                    this.mVisiblePosList.add(Integer.valueOf(i4));
                }
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            i3 = gridLayoutManager.findFirstVisibleItemPosition();
            i2 = gridLayoutManager.findLastVisibleItemPosition();
            for (int i5 = i3; i5 <= i2; i5++) {
                if (isVisible(layoutManager, i5)) {
                    this.mVisiblePosList.add(Integer.valueOf(i5));
                }
            }
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] findFirstVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
            i3 = Math.min(findFirstVisibleItemPositions[0], findFirstVisibleItemPositions[findFirstVisibleItemPositions.length - 1]);
            int[] findLastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
            i2 = Math.max(findLastVisibleItemPositions[0], findLastVisibleItemPositions[findLastVisibleItemPositions.length - 1]);
            for (int i6 = i3; i6 <= i2; i6++) {
                if (isVisible(layoutManager, i6)) {
                    this.mVisiblePosList.add(Integer.valueOf(i6));
                }
            }
        }
        iArr[0] = i3;
        iArr[1] = i2;
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHalfExposureHideList() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.getLayoutManager() == null) {
            return;
        }
        try {
            getFirstLastPosArray(this.mRecyclerView.getLayoutManager());
            if (this.mExposureStateMap.size() == 0) {
                this.mInstantList.addAll(getInitList());
            } else {
                this.mInstantList.addAll(getScrollList());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Iterator it = new ArrayList(this.mInstantList).iterator();
        while (it.hasNext()) {
            ItemExposureHideBean itemExposureHideBean = (ItemExposureHideBean) it.next();
            try {
                if ("exposure".equals(itemExposureHideBean.type)) {
                    if (isHalfPercentVisible(this.mLayoutDirection, this.mRecyclerView.getLayoutManager().findViewByPosition(itemExposureHideBean.pos))) {
                        this.mInstantList.remove(itemExposureHideBean);
                        ExposureStateListener exposureStateListener = this.mExposureStateListener;
                        if (exposureStateListener != null) {
                            exposureStateListener.onExposure(itemExposureHideBean.pos, itemExposureHideBean.exposureTime);
                        }
                    }
                } else {
                    this.mInstantList.remove(itemExposureHideBean);
                    ExposureStateListener exposureStateListener2 = this.mExposureStateListener;
                    if (exposureStateListener2 != null) {
                        exposureStateListener2.onHide(itemExposureHideBean.pos, itemExposureHideBean.exposureTime, itemExposureHideBean.hideTime, itemExposureHideBean.exposureDurationTime);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private List<ItemExposureHideBean> getInitList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.mVisiblePosList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            long currentTimeMillis = System.currentTimeMillis();
            ItemExposureStateBean itemExposureStateBean = new ItemExposureStateBean(null);
            itemExposureStateBean.pos = intValue;
            itemExposureStateBean.exposureTime = currentTimeMillis;
            this.mExposureStateMap.put(Integer.valueOf(intValue), itemExposureStateBean);
            ItemExposureHideBean itemExposureHideBean = new ItemExposureHideBean();
            itemExposureHideBean.type = "exposure";
            itemExposureHideBean.pos = intValue;
            itemExposureHideBean.exposureTime = currentTimeMillis;
            arrayList.add(itemExposureHideBean);
        }
        return arrayList;
    }

    private List<ItemExposureHideBean> getRepeatModeList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.mVisiblePosList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            try {
                if (!this.mExposureStateMap.containsKey(Integer.valueOf(intValue)) || this.mExposureStateMap.get(Integer.valueOf(intValue)).exposureTime == 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    ItemExposureStateBean itemExposureStateBean = new ItemExposureStateBean(null);
                    itemExposureStateBean.pos = intValue;
                    itemExposureStateBean.exposureTime = currentTimeMillis;
                    this.mExposureStateMap.put(Integer.valueOf(intValue), itemExposureStateBean);
                    ItemExposureHideBean itemExposureHideBean = new ItemExposureHideBean();
                    itemExposureHideBean.type = "exposure";
                    itemExposureHideBean.pos = intValue;
                    itemExposureHideBean.exposureTime = currentTimeMillis;
                    arrayList.add(itemExposureHideBean);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (Map.Entry<Integer, ItemExposureStateBean> entry : this.mExposureStateMap.entrySet()) {
            long currentTimeMillis2 = System.currentTimeMillis();
            int intValue2 = entry.getKey().intValue();
            long j2 = entry.getValue().exposureTime;
            if (!this.mVisiblePosList.contains(Integer.valueOf(intValue2))) {
                try {
                    if (this.mExposureStateMap.get(Integer.valueOf(intValue2)) != null && this.mExposureStateMap.get(Integer.valueOf(intValue2)).exposureTime != 0) {
                        this.mExposureStateMap.get(Integer.valueOf(intValue2)).exposureTime = 0L;
                        this.mExposureStateMap.get(Integer.valueOf(intValue2)).hideTime = currentTimeMillis2;
                        ItemExposureHideBean itemExposureHideBean2 = new ItemExposureHideBean();
                        itemExposureHideBean2.type = HIDE;
                        itemExposureHideBean2.pos = intValue2;
                        itemExposureHideBean2.exposureTime = j2;
                        itemExposureHideBean2.hideTime = currentTimeMillis2;
                        itemExposureHideBean2.exposureDurationTime = currentTimeMillis2 - j2;
                        arrayList.add(itemExposureHideBean2);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    private List<ItemExposureHideBean> getScrollList() {
        if (this.mExposureMode == 0) {
            return getSingleModeList();
        }
        return getRepeatModeList();
    }

    private void getScrollTimeDistanceSpeed(RecyclerView recyclerView, int i2) {
        if (recyclerView == null) {
            return;
        }
        if (i2 == 1) {
            this.mStartScrollTime = System.currentTimeMillis();
        }
        if (i2 == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mEndScrollTime = currentTimeMillis;
            float f2 = this.mScrollDistanceX;
            if (f2 > 2.0f && this.mScrollDistanceY == 0.0f) {
                this.mScrollDirection = 4.0f;
            }
            if (f2 == 0.0f && this.mScrollDistanceY > 2.0f) {
                this.mScrollDirection = 2.0f;
            }
            if (f2 < -2.0f && this.mScrollDistanceY == 0.0f) {
                this.mScrollDirection = 3.0f;
            }
            if (f2 == 0.0f && this.mScrollDistanceY < -2.0f) {
                this.mScrollDirection = 1.0f;
            }
            long j2 = this.mStartScrollTime;
            float abs = currentTimeMillis - j2 != 0 ? Math.abs(this.mScrollDistance / ((float) (currentTimeMillis - j2))) : 0.0f;
            ScrollParamListener scrollParamListener = this.mScrollParamListener;
            if (scrollParamListener != null) {
                long j3 = this.mStartScrollTime;
                long j4 = this.mEndScrollTime;
                scrollParamListener.onFinish(j3, j4, j4 - j3, Math.abs(this.mScrollDistance), abs, this.mScrollDirection);
            }
            this.mScrollDistance = 0.0f;
            this.mScrollDistanceX = 0.0f;
            this.mScrollDistanceY = 0.0f;
            this.mStartScrollTime = 0L;
            this.mEndScrollTime = 0L;
        }
    }

    private List<ItemExposureHideBean> getSingleModeList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.mVisiblePosList.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (!this.mExposureStateMap.containsKey(Integer.valueOf(intValue))) {
                long currentTimeMillis = System.currentTimeMillis();
                ItemExposureStateBean itemExposureStateBean = new ItemExposureStateBean(null);
                itemExposureStateBean.pos = intValue;
                itemExposureStateBean.exposureTime = currentTimeMillis;
                this.mExposureStateMap.put(Integer.valueOf(intValue), itemExposureStateBean);
                ItemExposureHideBean itemExposureHideBean = new ItemExposureHideBean();
                itemExposureHideBean.type = "exposure";
                itemExposureHideBean.pos = intValue;
                itemExposureHideBean.exposureTime = currentTimeMillis;
                arrayList.add(itemExposureHideBean);
            }
        }
        for (Map.Entry<Integer, ItemExposureStateBean> entry : this.mExposureStateMap.entrySet()) {
            long currentTimeMillis2 = System.currentTimeMillis();
            int intValue2 = entry.getKey().intValue();
            long j2 = entry.getValue().exposureTime;
            if (!this.mVisiblePosList.contains(Integer.valueOf(intValue2))) {
                try {
                    if (this.mExposureStateMap.get(Integer.valueOf(intValue2)) != null && this.mExposureStateMap.get(Integer.valueOf(intValue2)).hideTime == 0) {
                        this.mExposureStateMap.get(Integer.valueOf(intValue2)).hideTime = currentTimeMillis2;
                        ItemExposureHideBean itemExposureHideBean2 = new ItemExposureHideBean();
                        itemExposureHideBean2.type = HIDE;
                        itemExposureHideBean2.pos = intValue2;
                        itemExposureHideBean2.exposureTime = j2;
                        itemExposureHideBean2.hideTime = currentTimeMillis2;
                        itemExposureHideBean2.exposureDurationTime = currentTimeMillis2 - j2;
                        arrayList.add(itemExposureHideBean2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    private boolean isVisible(RecyclerView.LayoutManager layoutManager, int i2) {
        View findViewByPosition;
        if (layoutManager == null || (findViewByPosition = layoutManager.findViewByPosition(i2)) == null) {
            return false;
        }
        return findViewByPosition.getGlobalVisibleRect(new Rect());
    }

    private void setScrollDistanceValue(int i2, int i3) {
        if (i2 != 0) {
            float f2 = this.mScrollDistance + i2;
            this.mScrollDistance = f2;
            this.mScrollDistanceX = f2;
        }
        if (i3 != 0) {
            float f3 = this.mScrollDistance + i3;
            this.mScrollDistance = f3;
            this.mScrollDistanceY = f3;
        }
    }

    public void end() {
        ExposureStateListener exposureStateListener;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            long currentTimeMillis = System.currentTimeMillis();
            getFirstLastPosArray(this.mRecyclerView.getLayoutManager());
            Iterator<Integer> it = this.mVisiblePosList.iterator();
            while (it.hasNext()) {
                try {
                    ItemExposureStateBean itemExposureStateBean = this.mExposureStateMap.get(Integer.valueOf(it.next().intValue()));
                    if (itemExposureStateBean != null) {
                        if (this.mExposureMode == 1) {
                            ExposureStateListener exposureStateListener2 = this.mExposureStateListener;
                            if (exposureStateListener2 != null) {
                                int i2 = itemExposureStateBean.pos;
                                long j2 = itemExposureStateBean.exposureTime;
                                exposureStateListener2.onHide(i2, j2, currentTimeMillis, currentTimeMillis - j2);
                            }
                        } else if (itemExposureStateBean.hideTime == 0 && (exposureStateListener = this.mExposureStateListener) != null) {
                            int i3 = itemExposureStateBean.pos;
                            long j3 = itemExposureStateBean.exposureTime;
                            exposureStateListener.onHide(i3, j3, currentTimeMillis, currentTimeMillis - j3);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.mExposureStateMap.clear();
        this.mInstantList.clear();
    }

    public int[] getRvToScreenTopBottomDistance(@NonNull RecyclerView recyclerView) {
        Rect rect = new Rect();
        recyclerView.getGlobalVisibleRect(rect);
        return new int[]{rect.top, rect.bottom};
    }

    public List<ItemScrollBean> getScrollItemDistance(@NonNull RecyclerView.LayoutManager layoutManager) {
        ArrayList arrayList = new ArrayList();
        int[] firstLastPosArray = getFirstLastPosArray(layoutManager);
        if (firstLastPosArray != null && firstLastPosArray.length >= 2) {
            int i2 = firstLastPosArray[1];
            for (int i3 = firstLastPosArray[0]; i3 <= i2; i3++) {
                try {
                    View findViewByPosition = layoutManager.findViewByPosition(i3);
                    if (findViewByPosition != null) {
                        Rect rect = new Rect();
                        if (findViewByPosition.getGlobalVisibleRect(rect)) {
                            ItemScrollBean itemScrollBean = new ItemScrollBean();
                            itemScrollBean.pos = i3;
                            itemScrollBean.distance = rect.top;
                            arrayList.add(itemScrollBean);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public void insert(int i2) {
        HashMap hashMap = new HashMap(this.mExposureStateMap);
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) ((Map.Entry) it.next()).getKey()).intValue();
            if (intValue >= i2) {
                try {
                    ItemExposureStateBean itemExposureStateBean = (ItemExposureStateBean) hashMap.get(Integer.valueOf(intValue));
                    if (hashMap.containsKey(Integer.valueOf(intValue)) && itemExposureStateBean != null) {
                        int i3 = intValue + 1;
                        itemExposureStateBean.pos = i3;
                        this.mExposureStateMap.put(Integer.valueOf(i3), itemExposureStateBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.mExposureStateMap.remove(Integer.valueOf(i2));
        this.mInstantList.clear();
    }

    public boolean isHalfPercentVisible(int i2, View view) {
        Rect rect = new Rect();
        if (view != null && view.getGlobalVisibleRect(rect)) {
            if (i2 == 1) {
                double height = rect.height();
                double measuredHeight = view.getMeasuredHeight();
                Double.isNaN(measuredHeight);
                return height >= measuredHeight * 0.5d;
            }
            double width = rect.width();
            double measuredWidth = view.getMeasuredWidth();
            Double.isNaN(measuredWidth);
            return width >= measuredWidth * 0.5d;
        }
        return false;
    }

    public void listen() {
        if (System.currentTimeMillis() - this.mLastListenTime < 10) {
            return;
        }
        this.mLastListenTime = System.currentTimeMillis();
        getHalfExposureHideList();
    }

    public void listenScrollParam(@NonNull RecyclerView recyclerView, int i2) {
        getScrollTimeDistanceSpeed(recyclerView, i2);
    }

    public void notifyInserted() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.postDelayed(new a(), 500L);
    }

    public void notifyRemoved() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.postDelayed(new c(), 500L);
    }

    public void reStart() {
        this.mExposureStateMap.clear();
        this.mInstantList.clear();
        if (this.mRecyclerView != null) {
            getHalfExposureHideList();
        }
    }

    public void remove(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        Collections.sort(list, new b(this));
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            remove(it.next().intValue());
        }
    }

    @Deprecated
    public void setExposureStateListener(int i2, RecyclerView recyclerView, int i3, ExposureStateListener exposureStateListener) {
        this.mLayoutDirection = i2;
        this.mExposureMode = i3;
        this.mRecyclerView = recyclerView;
        this.mExposureStateListener = exposureStateListener;
        getHalfExposureHideList();
    }

    public void setScrollParamListener(ScrollParamListener scrollParamListener) {
        this.mScrollParamListener = scrollParamListener;
    }

    public void listenScrollParam(int i2, int i3) {
        setScrollDistanceValue(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Keep
    /* loaded from: classes14.dex */
    public static class ItemExposureStateBean {
        long exposureTime;
        long hideTime;
        int pos;

        private ItemExposureStateBean() {
            this.exposureTime = 0L;
            this.hideTime = 0L;
        }

        /* synthetic */ ItemExposureStateBean(a aVar) {
            this();
        }
    }

    public void remove(int i2) {
        HashMap hashMap = new HashMap(this.mExposureStateMap);
        Iterator it = hashMap.entrySet().iterator();
        int i3 = 0;
        while (it.hasNext()) {
            int intValue = ((Integer) ((Map.Entry) it.next()).getKey()).intValue();
            if (intValue > i3) {
                i3 = intValue;
            }
            if (intValue > i2) {
                try {
                    ItemExposureStateBean itemExposureStateBean = (ItemExposureStateBean) hashMap.get(Integer.valueOf(intValue));
                    if (hashMap.containsKey(Integer.valueOf(intValue)) && itemExposureStateBean != null) {
                        int i4 = intValue - 1;
                        itemExposureStateBean.pos = i4;
                        this.mExposureStateMap.put(Integer.valueOf(i4), itemExposureStateBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (this.mInstantList.size() > 0) {
            ArrayList arrayList = new ArrayList(this.mInstantList);
            this.mInstantList.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                r0.pos--;
                this.mInstantList.add((ItemExposureHideBean) it2.next());
            }
        }
        this.mExposureStateMap.remove(Integer.valueOf(i3));
    }

    public ExposureRvUtils(int i2, @NonNull RecyclerView recyclerView, int i3, ExposureStateListener exposureStateListener) {
        this.mLastListenTime = 0L;
        this.mExposureStateMap = new HashMap<>();
        this.mStartScrollTime = 0L;
        this.mEndScrollTime = 0L;
        this.mScrollDistance = 0.0f;
        this.mScrollDistanceX = 0.0f;
        this.mScrollDistanceY = 0.0f;
        this.mExposureMode = 1;
        this.mLayoutDirection = 1;
        this.mScrollDirection = 0.0f;
        this.mVisiblePosList = new ArrayList();
        this.mInstantList = new ArrayList();
        this.mLayoutDirection = i2;
        this.mExposureMode = i3;
        this.mRecyclerView = recyclerView;
        this.mExposureStateListener = exposureStateListener;
    }

    public void insert(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        int i2 = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (i2 < intValue) {
                i2 = intValue;
            }
        }
        HashMap hashMap = new HashMap(this.mExposureStateMap);
        Iterator it2 = hashMap.entrySet().iterator();
        while (it2.hasNext()) {
            int intValue2 = ((Integer) ((Map.Entry) it2.next()).getKey()).intValue();
            if (intValue2 >= i2) {
                try {
                    ItemExposureStateBean itemExposureStateBean = (ItemExposureStateBean) hashMap.get(Integer.valueOf(intValue2));
                    if (hashMap.containsKey(Integer.valueOf(intValue2)) && itemExposureStateBean != null) {
                        itemExposureStateBean.pos = list.size() + intValue2;
                        this.mExposureStateMap.put(Integer.valueOf(intValue2 + list.size()), itemExposureStateBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        Iterator<Integer> it3 = list.iterator();
        while (it3.hasNext()) {
            this.mExposureStateMap.remove(Integer.valueOf(it3.next().intValue()));
        }
        this.mInstantList.clear();
    }
}

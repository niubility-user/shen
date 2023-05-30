package point;

import android.content.Context;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.pdj.libdjbasecore.R;
import com.jingdong.pdj.libdjbasecore.utils.DJCastUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import point.interfaces.DJDefaultRadioWithDelayInitializer;
import point.interfaces.DJDefaultReportInitializer;
import point.interfaces.DJOnScrollViewChangeListener;
import point.interfaces.DJPointData;
import point.interfaces.DJPointListener;
import point.interfaces.DJPointView;
import point.interfaces.DJRectVisibleInterface;
import point.listener.DJPointOnScrollListener;
import point.listener.DJPointOnScrollViewListener;
import point.scrollview.DJPointNestedScrollView;
import point.scrollview.DJPointScrollView;

/* loaded from: classes11.dex */
public class DJPointVisibleUtil implements DJRectVisibleInterface {
    public static final int HORIZONTAL = 0;
    private static final long OVER_LAYOUT_TIME = 50;
    private static final String TAG = "DJPointVisibleUtil";
    public static final int VERTICAL = 1;
    public static DJDefaultRadioWithDelayInitializer mDJDefaultRadioWithDelayInitializer;
    public static DJDefaultReportInitializer mDJDefaultReportInitializer;
    private ConcurrentHashMap<Integer, WeakReference<View>> allPointViewMap = new ConcurrentHashMap<>();
    private boolean disableReport;
    private boolean globalLoopModel;
    private long lastLayoutTime;
    private boolean mEnableRatioWithDelay;
    private long mEpMTADelayDuration;
    private float mEpMTADelayRatio;
    private List<Runnable> notOverLayoutTimeRunnables;
    private List<ViewTreeObserver.OnGlobalLayoutListener> onGlobalLayoutListeners;
    private WeakReference parentViewReference;
    private DJDefaultReportInitializer reportListener;

    /* renamed from: point.DJPointVisibleUtil$1 */
    /* loaded from: classes11.dex */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ List val$filterPointDataList;

        AnonymousClass1(List list) {
            DJPointVisibleUtil.this = r1;
            this.val$filterPointDataList = list;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ViewGroup parentInReference = DJPointVisibleUtil.this.getParentInReference();
            if (parentInReference == null) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            boolean z = elapsedRealtime - DJPointVisibleUtil.this.lastLayoutTime < 50;
            DJPointVisibleUtil.this.lastLayoutTime = elapsedRealtime;
            if (z) {
                DJPointVisibleUtil.this.removeNotOverLayoutTimeRunnables();
                Runnable runnable = new Runnable() { // from class: point.DJPointVisibleUtil.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass1.this.onGlobalLayout();
                    }
                };
                parentInReference.postDelayed(runnable, 50L);
                if (DJPointVisibleUtil.this.notOverLayoutTimeRunnables == null) {
                    DJPointVisibleUtil.this.notOverLayoutTimeRunnables = new ArrayList();
                }
                DJPointVisibleUtil.this.notOverLayoutTimeRunnables.add(runnable);
                return;
            }
            DJPointVisibleUtil.this.calculateRectVisible(parentInReference, this.val$filterPointDataList);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface PointOrientation {
    }

    public DJPointVisibleUtil(boolean z) {
        this.mEnableRatioWithDelay = z;
        DJDefaultRadioWithDelayInitializer dJDefaultRadioWithDelayInitializer = mDJDefaultRadioWithDelayInitializer;
        if (dJDefaultRadioWithDelayInitializer != null) {
            dJDefaultRadioWithDelayInitializer.initialize(this);
        }
    }

    private void calculate(ViewGroup viewGroup, List<String> list) {
        ConcurrentHashMap<Integer, WeakReference<View>> concurrentHashMap = this.allPointViewMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        try {
            Iterator<Map.Entry<Integer, WeakReference<View>>> it = this.allPointViewMap.entrySet().iterator();
            while (it.hasNext()) {
                View view = it.next().getValue().get();
                if (isPointView(view)) {
                    DJPointData dJPointData = (DJPointData) view.getTag(R.id.pointDataKey);
                    if (!this.globalLoopModel && !dJPointData.loopModel) {
                        if (!dJPointData.reported && isVisible(viewGroup, view, dJPointData.epMTADelayRatio)) {
                            dJPointData.reported = true;
                            reportPointFilter(view, list);
                        }
                    }
                    if (isVisible(viewGroup, view, dJPointData.epMTADelayRatio)) {
                        int i2 = R.id.canUpload;
                        if (view.getTag(i2) instanceof Boolean ? ((Boolean) view.getTag(i2)).booleanValue() : false) {
                            reportPointFilter(view, list);
                        }
                        view.setTag(i2, Boolean.FALSE);
                    } else {
                        if (isEnableRatioWithDelay()) {
                            ((DJPointView) view).cancelTimerReport();
                        }
                        view.setTag(R.id.canUpload, Boolean.TRUE);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private List<String> calculateLinearManager(RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (linearLayoutManager.getChildCount() > 0) {
                int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                ArrayList arrayList2 = new ArrayList();
                for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                    View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                    if (findViewByPosition != null) {
                        arrayList2.addAll(getAllChildViews(findViewByPosition));
                    }
                }
                findReportedViews(arrayList, arrayList2, z, z2);
            }
        }
        return arrayList;
    }

    private List<String> calculateStaggerManager(RecyclerView.LayoutManager layoutManager, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            if (staggeredGridLayoutManager.getChildCount() > 0) {
                int[] findFirstVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
                int[] findLastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(null);
                if (findFirstVisibleItemPositions != null && findLastVisibleItemPositions != null && findFirstVisibleItemPositions.length > 0 && findLastVisibleItemPositions.length > 0) {
                    int[] visibleItemIndex = getVisibleItemIndex(findFirstVisibleItemPositions, findLastVisibleItemPositions);
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = visibleItemIndex[0]; i2 <= visibleItemIndex[1]; i2++) {
                        View findViewByPosition = staggeredGridLayoutManager.findViewByPosition(i2);
                        if (findViewByPosition != null) {
                            arrayList2.addAll(getAllChildViews(findViewByPosition));
                        }
                    }
                    findReportedViews(arrayList, arrayList2, z, z2);
                }
            }
        }
        return arrayList;
    }

    private void findReportedViews(List<String> list, List<View> list2, boolean z, boolean z2) {
        for (int i2 = 0; i2 < list2.size(); i2++) {
            View view = list2.get(i2);
            if (isPointView(view)) {
                int i3 = R.id.canUpload;
                if (view.getTag(i3) != null && (view.getTag(i3) instanceof Boolean) && !((Boolean) view.getTag(i3)).booleanValue()) {
                    if (z) {
                        view.setTag(i3, Boolean.valueOf(z2));
                    } else {
                        list.add(((DJPointData) view.getTag(R.id.pointDataKey)).getUniqueKey());
                    }
                }
            }
        }
        list2.clear();
    }

    private HashSet<View> getAllChildViews(View view) {
        HashSet<View> hashSet = new HashSet<>();
        if (isPointView(view)) {
            hashSet.add(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (isPointView(childAt)) {
                    hashSet.add(childAt);
                }
                hashSet.addAll(getAllChildViews(childAt));
            }
        }
        return hashSet;
    }

    public ViewGroup getParentInReference() {
        WeakReference weakReference = this.parentViewReference;
        if (weakReference == null || weakReference.get() == null) {
            return null;
        }
        return (ViewGroup) DJCastUtil.convert(this.parentViewReference.get());
    }

    private int[] getVisibleItemIndex(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (iArr.length > 1) {
            if (iArr[0] > iArr[1]) {
                iArr3[0] = iArr[1];
            } else {
                iArr3[0] = iArr[0];
            }
        } else {
            iArr3[0] = iArr[0];
        }
        if (iArr2.length > 1) {
            if (iArr2[0] > iArr2[1]) {
                iArr3[1] = iArr2[0];
            } else {
                iArr3[1] = iArr2[1];
            }
        } else {
            iArr3[1] = iArr2[0];
        }
        return iArr3;
    }

    public void removeNotOverLayoutTimeRunnables() {
        ViewGroup parentInReference;
        List<Runnable> list = this.notOverLayoutTimeRunnables;
        if (list == null || list.isEmpty() || (parentInReference = getParentInReference()) == null) {
            return;
        }
        Iterator<Runnable> it = this.notOverLayoutTimeRunnables.iterator();
        while (it.hasNext()) {
            parentInReference.removeCallbacks(it.next());
        }
        this.notOverLayoutTimeRunnables.clear();
    }

    private void removeOnGlobalLayoutListeners() {
        ViewGroup parentInReference;
        List<ViewTreeObserver.OnGlobalLayoutListener> list = this.onGlobalLayoutListeners;
        if (list == null || list.isEmpty() || (parentInReference = getParentInReference()) == null) {
            return;
        }
        Iterator<ViewTreeObserver.OnGlobalLayoutListener> it = this.onGlobalLayoutListeners.iterator();
        while (it.hasNext()) {
            parentInReference.getViewTreeObserver().removeOnGlobalLayoutListener(it.next());
        }
        this.onGlobalLayoutListeners.clear();
    }

    private void reportPoint(View view, DJPointData dJPointData) {
        if (isEnableRatioWithDelay() && getEpMTADelayDuration() != 0) {
            ((DJPointView) view).startTimerReport();
        } else {
            report(view.getContext(), "\u7acb\u5373\u66dd\u5149", dJPointData);
        }
    }

    public static void setDJDefaultRadioWithDelayInitializer(@NonNull DJDefaultRadioWithDelayInitializer dJDefaultRadioWithDelayInitializer) {
        mDJDefaultRadioWithDelayInitializer = dJDefaultRadioWithDelayInitializer;
    }

    public static void setDJDefaultReportInitializer(@NonNull DJDefaultReportInitializer dJDefaultReportInitializer) {
        mDJDefaultReportInitializer = dJDefaultReportInitializer;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void calculateRectVisible(ViewGroup viewGroup) {
        calculateRectVisible(viewGroup, null);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public long getEpMTADelayDuration() {
        return this.mEpMTADelayDuration;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public float getEpMTADelayRatio() {
        return this.mEpMTADelayRatio;
    }

    public List<String> getFilterPointDataList(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager != null) {
            try {
                if (layoutManager instanceof LinearLayoutManager) {
                    return calculateLinearManager(layoutManager, false, false);
                }
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    return calculateStaggerManager(layoutManager, false, false);
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isDisableReport() {
        return this.disableReport;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isEnableRatioWithDelay() {
        return this.mEnableRatioWithDelay;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isPointView(View view) {
        return view != null && (view instanceof DJPointView) && (view.getTag(R.id.pointDataKey) instanceof DJPointData);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isVisible(ViewGroup viewGroup, View view) {
        if (view == null || !(view instanceof DJPointView)) {
            return false;
        }
        boolean isVisible = ((DJPointView) view).isVisible();
        boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
        boolean isVisibleForRatio = isVisibleForRatio(viewGroup, view);
        if (isVisible) {
            if (isEnableRatioWithDelay()) {
                if (!localVisibleRect || !isVisibleForRatio) {
                    return false;
                }
            } else if (!localVisibleRect) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isVisibleForRatio(ViewGroup viewGroup, View view) {
        return isVisibleForRatio(viewGroup, view, getEpMTADelayRatio());
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJPointListener registerRootView(ViewGroup viewGroup, int i2) {
        return registerRootView(viewGroup, null, i2);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void registerView(View view) {
        if (view != null) {
            try {
                ConcurrentHashMap<Integer, WeakReference<View>> concurrentHashMap = this.allPointViewMap;
                if (concurrentHashMap != null && !concurrentHashMap.containsKey(Integer.valueOf(view.hashCode())) && isPointView(view)) {
                    DJPointData dJPointData = (DJPointData) view.getTag(R.id.pointDataKey);
                    if (!this.globalLoopModel && !dJPointData.loopModel) {
                        if (!dJPointData.reported) {
                            view.setTag(R.id.canUpload, Boolean.TRUE);
                            this.allPointViewMap.put(Integer.valueOf(view.hashCode()), new WeakReference<>(view));
                        }
                    }
                    view.setTag(R.id.canUpload, Boolean.TRUE);
                    this.allPointViewMap.put(Integer.valueOf(view.hashCode()), new WeakReference<>(view));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void release() {
        ConcurrentHashMap<Integer, WeakReference<View>> concurrentHashMap = this.allPointViewMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        removeNoScrollModelListener();
    }

    public void removeNoScrollModelListener() {
        removeOnGlobalLayoutListeners();
        removeNotOverLayoutTimeRunnables();
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void report(Context context, String str, DJPointData dJPointData) {
        DJDefaultReportInitializer dJDefaultReportInitializer = mDJDefaultReportInitializer;
        if (dJDefaultReportInitializer != null) {
            dJDefaultReportInitializer.report(context, str, dJPointData);
            DJDefaultReportInitializer dJDefaultReportInitializer2 = this.reportListener;
            if (dJDefaultReportInitializer2 != null) {
                dJDefaultReportInitializer2.report(context, str, dJPointData);
            }
        }
    }

    public void reportNoScrollModel(ViewGroup viewGroup) {
        reportNoScrollModel(viewGroup, null);
    }

    public void reportPointFilter(View view, List<String> list) {
        DJPointData dJPointData;
        if (isDisableReport() || (dJPointData = (DJPointData) view.getTag(R.id.pointDataKey)) == null) {
            return;
        }
        if (list != null) {
            if (list.contains(dJPointData.getUniqueKey())) {
                return;
            }
            reportPoint(view, dJPointData);
            return;
        }
        reportPoint(view, dJPointData);
    }

    public void resetPointViewReportTag(RecyclerView.LayoutManager layoutManager, boolean z) {
        if (layoutManager != null) {
            try {
                if (layoutManager instanceof LinearLayoutManager) {
                    calculateLinearManager(layoutManager, true, z);
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    calculateStaggerManager(layoutManager, true, z);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJRectVisibleInterface setDisableReport(boolean z) {
        this.disableReport = z;
        return this;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJRectVisibleInterface setEnableRatioWithDelay(boolean z) {
        this.mEnableRatioWithDelay = z;
        return this;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJRectVisibleInterface setEpMTADelayDuration(@IntRange(from = 0, to = 2147483647L) long j2) {
        this.mEpMTADelayDuration = j2;
        return this;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJRectVisibleInterface setEpMTADelayRatio(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.mEpMTADelayRatio = f2;
        return this;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public DJRectVisibleInterface setGlobalLoopModel(boolean z) {
        this.globalLoopModel = z;
        return this;
    }

    public void setPointViewData(DJPointView dJPointView, DJPointData dJPointData) {
        if (dJPointView == null || !(dJPointView instanceof View)) {
            return;
        }
        ((View) dJPointView).setTag(R.id.pointDataKey, dJPointData);
        dJPointView.setDJPointVisibleUtil(this);
    }

    public void setReportListener(DJDefaultReportInitializer dJDefaultReportInitializer) {
        this.reportListener = dJDefaultReportInitializer;
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void unregisterView(View view) {
        ConcurrentHashMap<Integer, WeakReference<View>> concurrentHashMap;
        if (view == null || (concurrentHashMap = this.allPointViewMap) == null || !concurrentHashMap.containsKey(Integer.valueOf(view.hashCode()))) {
            return;
        }
        this.allPointViewMap.remove(Integer.valueOf(view.hashCode()));
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void calculateRectVisible(ViewGroup viewGroup, List<String> list) {
        calculateRectVisible(viewGroup, list, false);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isVisibleForRatio(ViewGroup viewGroup, View view, float f2) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if (view == null || viewGroup == null) {
            return false;
        }
        if (f2 == 0.0f) {
            f2 = getEpMTADelayRatio();
        }
        Rect rect = new Rect();
        viewGroup.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        int i8 = rect2.top;
        int i9 = rect.top;
        if (i8 < i9 || (i2 = rect2.bottom) > (i3 = rect.bottom) || (i4 = rect2.left) < (i5 = rect.left) || (i6 = rect2.right) > (i7 = rect.right)) {
            return false;
        }
        int i10 = i2 - i8;
        int i11 = i6 - i4;
        int i12 = i3 - i9;
        int i13 = i7 - i5;
        int measuredHeight = view.getMeasuredHeight();
        int measuredWidth = view.getMeasuredWidth();
        if (measuredHeight <= i12) {
            i12 = measuredHeight;
        }
        if (measuredWidth <= i13) {
            i13 = measuredWidth;
        }
        return ((float) i10) >= ((float) i12) * f2 && ((float) i11) >= ((float) i13) * f2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // point.interfaces.DJRectVisibleInterface
    public DJPointListener registerRootView(ViewGroup viewGroup, ViewGroup viewGroup2, int i2) {
        DJPointListener dJPointListener = null;
        if (viewGroup == null) {
            return null;
        }
        try {
            ?? r1 = viewGroup instanceof RecyclerView;
            try {
                if (r1 != 0) {
                    DJPointOnScrollListener dJPointOnScrollListener = new DJPointOnScrollListener(this, i2, viewGroup2);
                    ((RecyclerView) viewGroup).addOnScrollListener((RecyclerView.OnScrollListener) DJCastUtil.convert(dJPointOnScrollListener));
                    r1 = dJPointOnScrollListener;
                } else if (viewGroup instanceof DJPointNestedScrollView) {
                    DJPointOnScrollViewListener dJPointOnScrollViewListener = new DJPointOnScrollViewListener(this, i2, viewGroup2);
                    ((DJPointNestedScrollView) viewGroup).addOnScrollListener((DJOnScrollViewChangeListener) DJCastUtil.convert(dJPointOnScrollViewListener));
                    r1 = dJPointOnScrollViewListener;
                } else if (viewGroup instanceof DJPointScrollView) {
                    DJPointOnScrollViewListener dJPointOnScrollViewListener2 = new DJPointOnScrollViewListener(this, i2, viewGroup2);
                    ((DJPointScrollView) viewGroup).addOnScrollListener((DJOnScrollViewChangeListener) DJCastUtil.convert(dJPointOnScrollViewListener2));
                    r1 = dJPointOnScrollViewListener2;
                } else {
                    throw new IllegalArgumentException("\u5176\u4ed6\u6eda\u52a8view\u66dd\u5149\u9700\u8981\u81ea\u5df1\u5728\u8fd9\u91cc\u5904\u7406\uff01\uff01\uff01");
                }
                return r1;
            } catch (Exception e2) {
                e = e2;
                dJPointListener = r1;
                e.printStackTrace();
                return dJPointListener;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public void reportNoScrollModel(ViewGroup viewGroup, List<String> list) {
        if (viewGroup == null) {
            return;
        }
        this.parentViewReference = new WeakReference(viewGroup);
        removeOnGlobalLayoutListeners();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(list);
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(anonymousClass1);
        if (this.onGlobalLayoutListeners == null) {
            this.onGlobalLayoutListeners = new ArrayList();
        }
        this.onGlobalLayoutListeners.add(anonymousClass1);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public void calculateRectVisible(ViewGroup viewGroup, List<String> list, boolean z) {
        if (z) {
            removeNoScrollModelListener();
        }
        calculate(viewGroup, list);
    }

    @Override // point.interfaces.DJRectVisibleInterface
    public boolean isVisible(ViewGroup viewGroup, View view, float f2) {
        if (view == null || !(view instanceof DJPointView)) {
            return false;
        }
        boolean isVisible = ((DJPointView) view).isVisible();
        boolean localVisibleRect = view.getLocalVisibleRect(new Rect());
        boolean isVisibleForRatio = isVisibleForRatio(viewGroup, view, f2);
        if (isVisible) {
            if (isEnableRatioWithDelay()) {
                if (!localVisibleRect || !isVisibleForRatio) {
                    return false;
                }
            } else if (!localVisibleRect) {
                return false;
            }
            return true;
        }
        return false;
    }
}

package com.jingdong.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.utils.adapter.SimpleImageProcessor;
import com.jingdong.common.utils.adapter.UIRunnable;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes6.dex */
public class SimpleBeanAdapter extends BaseAdapter implements Filterable {
    private static final String TAG = "SimpleBeanAdapter";
    private AdapterHelper adapterHelper;
    private boolean allowNoImage;
    private ArrayList<ListView.FixedViewInfo> footerViewInfos;
    private ArrayList<ListView.FixedViewInfo> headerViewInfos;
    private HeaderViewListAdapter headerViewListAdapter;
    private boolean isAssetsCache;
    private boolean isForeverCacheImage;
    private Context mContext;
    private List<?> mData;
    private JDDisplayImageOptions mDisplayImageOptions;
    private int mDropDownResource;
    private SimpleFilter mFilter;
    private String[] mFrom;
    private LayoutInflater mInflater;
    private int mResource;
    private int[] mTo;
    private List<?> mUnfilteredData;
    private SubViewBinder mViewBinder;
    private Vector uiRunnables;

    /* loaded from: classes6.dex */
    private class SimpleFilter extends Filter {
        private SimpleFilter() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (SimpleBeanAdapter.this.mUnfilteredData == null) {
                SimpleBeanAdapter simpleBeanAdapter = SimpleBeanAdapter.this;
                simpleBeanAdapter.mUnfilteredData = simpleBeanAdapter.mData;
            }
            if (charSequence == null || charSequence.length() == 0) {
                List list = SimpleBeanAdapter.this.mUnfilteredData;
                filterResults.values = list;
                filterResults.count = list.size();
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                List list2 = SimpleBeanAdapter.this.mUnfilteredData;
                int size = list2.size();
                LinkedList linkedList = new LinkedList();
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj = list2.get(i2);
                    if (obj != null) {
                        int length = SimpleBeanAdapter.this.mTo.length;
                        for (int i3 = 0; i3 < length; i3++) {
                            String[] split = ((String) BeanUtil.getValue(obj, SimpleBeanAdapter.this.mFrom[i3])).split(LangUtils.SINGLE_SPACE);
                            int length2 = split.length;
                            int i4 = 0;
                            while (true) {
                                if (i4 >= length2) {
                                    break;
                                } else if (split[i4].toLowerCase().startsWith(lowerCase)) {
                                    linkedList.add(obj);
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                        }
                    }
                }
                filterResults.values = linkedList;
                filterResults.count = linkedList.size();
            }
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            SimpleBeanAdapter.this.mData = (List) filterResults.values;
            if (filterResults.count > 0) {
                SimpleBeanAdapter.this.notifyDataSetChanged();
            } else {
                SimpleBeanAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    public SimpleBeanAdapter(Context context, List<?> list, int i2, String[] strArr, int[] iArr) {
        this.adapterHelper = new AdapterHelper();
        this.allowNoImage = true;
        this.isAssetsCache = false;
        this.isForeverCacheImage = false;
        this.uiRunnables = new Vector();
        this.mData = list;
        this.mDropDownResource = i2;
        this.mResource = i2;
        this.mFrom = strArr;
        this.mTo = iArr;
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    private void bindView(int i2, View view) {
        Object item = getItem(i2);
        if (item == null || view == null) {
            return;
        }
        SubViewBinder viewBinder = getViewBinder();
        String[] strArr = this.mFrom;
        int[] iArr = this.mTo;
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (OKLog.D) {
                OKLog.d(SimpleBeanAdapter.class.getName(), "bindView() for from[i] -->> " + strArr[i3]);
            }
            View subView = this.adapterHelper.getSubView(view, iArr[i3]);
            if (subView != null) {
                Object value = BeanUtil.getValue(item, strArr[i3]);
                if (value != null) {
                    value.toString();
                }
                if (viewBinder != null) {
                    SubViewHolder subViewHolder = new SubViewHolder();
                    subViewHolder.setAdapter(this);
                    subViewHolder.setItemData(item);
                    subViewHolder.setSubData(value);
                    subViewHolder.setItemView(view);
                    subViewHolder.setSubView(subView);
                    subViewHolder.setPosition(i2);
                    subViewHolder.setSubViewId(iArr[i3]);
                    viewBinder.bind(subViewHolder);
                }
            }
        }
    }

    private View createViewFromResource(ViewGroup viewGroup, int i2) {
        View inflateView = inflateView(viewGroup, i2);
        int[] iArr = this.mTo;
        int length = iArr.length;
        HashMap hashMap = new HashMap();
        for (int i3 = 0; i3 < length; i3++) {
            hashMap.put(Integer.valueOf(iArr[i3]), inflateView.findViewById(iArr[i3]));
        }
        this.adapterHelper.putSubViews(inflateView, hashMap);
        return inflateView;
    }

    private View getViewFromResource(int i2, View view, ViewGroup viewGroup, int i3) {
        if (view == null) {
            view = createViewFromResource(viewGroup, i3);
        }
        try {
            bindView(i2, view);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.d(SimpleBeanAdapter.class.getName(), e2);
            }
        }
        return view;
    }

    private void removeFixedViewInfo(View view, ArrayList<ListView.FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (arrayList.get(i2).view == view) {
                arrayList.remove(i2);
                return;
            }
        }
    }

    public synchronized void UIWorkCentralized(UIRunnable uIRunnable) {
        if (this.uiRunnables.size() <= 0) {
            this.uiRunnables.add(uIRunnable);
            BaseApplication.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.utils.SimpleBeanAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (SimpleBeanAdapter.this) {
                        Iterator it = SimpleBeanAdapter.this.uiRunnables.iterator();
                        while (it.hasNext()) {
                            ((UIRunnable) it.next()).run();
                        }
                        SimpleBeanAdapter.this.uiRunnables.clear();
                    }
                }
            }, 600L);
        } else {
            this.uiRunnables.add(uIRunnable);
        }
    }

    public void addAllBean(Collection collection) {
        this.mData.addAll(collection);
    }

    public void addFooterView(ListView listView, View view) {
        if (OKLog.D) {
            OKLog.d(SimpleBeanAdapter.class.getName(), "addFooterView() view -->> " + view);
        }
        if (BaseApplication.getUiThread() == Thread.currentThread()) {
            if (this.footerViewInfos == null) {
                return;
            }
            listView.getClass();
            ListView.FixedViewInfo fixedViewInfo = new ListView.FixedViewInfo(listView);
            fixedViewInfo.view = view;
            fixedViewInfo.data = null;
            fixedViewInfo.isSelectable = true;
            this.footerViewInfos.add(fixedViewInfo);
            notifyDataSetChanged();
            return;
        }
        throw new RuntimeException("must run in ui thread");
    }

    public void addHeaderView(ListView listView, View view) {
        if (OKLog.D) {
            OKLog.d(SimpleBeanAdapter.class.getName(), "addHeaderView() view -->> " + view);
        }
        if (BaseApplication.getUiThread() == Thread.currentThread()) {
            listView.getClass();
            ListView.FixedViewInfo fixedViewInfo = new ListView.FixedViewInfo(listView);
            fixedViewInfo.view = view;
            fixedViewInfo.data = null;
            fixedViewInfo.isSelectable = true;
            this.headerViewInfos.add(fixedViewInfo);
            notifyDataSetChanged();
            return;
        }
        throw new RuntimeException("must run in ui thread");
    }

    public boolean allowNoImageAndIsNoImage() {
        return this.allowNoImage && NoImageUtils.needNoImage();
    }

    public void gc() {
        this.mViewBinder = null;
        this.mData = null;
        this.mUnfilteredData = null;
    }

    public AdapterHelper getAdapterHelper() {
        return this.adapterHelper;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<?> list = this.mData;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return getViewFromResource(i2, view, viewGroup, this.mDropDownResource);
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new SimpleFilter();
        }
        return this.mFilter;
    }

    public HeaderViewListAdapter getHeaderViewListAdapter() {
        if (this.headerViewListAdapter == null) {
            this.headerViewInfos = new ArrayList<>();
            this.footerViewInfos = new ArrayList<>();
            this.headerViewListAdapter = new HeaderViewListAdapter(this.headerViewInfos, this.footerViewInfos, this) { // from class: com.jingdong.common.utils.SimpleBeanAdapter.1
                @Override // android.widget.HeaderViewListAdapter, android.widget.Adapter
                public View getView(int i2, View view, ViewGroup viewGroup) {
                    try {
                        return super.getView(i2, view, viewGroup);
                    } catch (Throwable th) {
                        if (OKLog.E) {
                            OKLog.e(SimpleBeanAdapter.TAG, th);
                        }
                        return new View(JdSdk.getInstance().getApplication().getBaseContext());
                    }
                }
            };
        }
        return this.headerViewListAdapter;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<?> list = this.mData;
        if (list == null || i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return this.mData.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (viewGroup instanceof AdapterView) {
            this.adapterHelper.setAdapterView((AdapterView) viewGroup);
        }
        return getViewFromResource(i2, view, viewGroup, this.mResource);
    }

    public SubViewBinder getViewBinder() {
        if (this.mViewBinder == null) {
            if (this.mDisplayImageOptions == null) {
                this.mViewBinder = new SimpleSubViewBinder(new SimpleImageProcessor());
            } else {
                this.mViewBinder = new SimpleSubViewBinder(new SimpleImageProcessor(), this.mDisplayImageOptions);
            }
        }
        return this.mViewBinder;
    }

    protected View inflateView(ViewGroup viewGroup, int i2) {
        return ImageUtil.inflate(this.mContext, i2, viewGroup, false);
    }

    public boolean isAllowNoImage() {
        return this.allowNoImage;
    }

    public boolean isAssetsCache() {
        return this.isAssetsCache;
    }

    public boolean isForeverCacheImage() {
        return this.isForeverCacheImage;
    }

    public void removeAllFooterView() {
        ArrayList<ListView.FixedViewInfo> arrayList = this.footerViewInfos;
        if (arrayList != null) {
            arrayList.clear();
            notifyDataSetChanged();
        }
    }

    public void removeAllHeaderView() {
        ArrayList<ListView.FixedViewInfo> arrayList = this.headerViewInfos;
        if (arrayList != null) {
            arrayList.clear();
            notifyDataSetChanged();
        }
    }

    public boolean removeFooterView(View view) {
        if (BaseApplication.getUiThread() == Thread.currentThread()) {
            boolean z = false;
            if (this.headerViewListAdapter == null) {
                return false;
            }
            if (this.footerViewInfos.size() > 0) {
                if (this.headerViewListAdapter.removeFooter(view)) {
                    notifyDataSetChanged();
                    z = true;
                }
                removeFixedViewInfo(view, this.footerViewInfos);
            }
            return z;
        }
        throw new RuntimeException("must run in ui thread");
    }

    public boolean removeHeaderView(View view) {
        if (BaseApplication.getUiThread() == Thread.currentThread()) {
            boolean z = false;
            if (this.headerViewListAdapter == null) {
                return false;
            }
            if (this.headerViewInfos.size() > 0) {
                if (this.headerViewListAdapter.removeHeader(view)) {
                    notifyDataSetChanged();
                    z = true;
                }
                removeFixedViewInfo(view, this.headerViewInfos);
            }
            return z;
        }
        throw new RuntimeException("must run in ui thread");
    }

    public void replaceAllBean(Collection collection) {
        int size = this.mData.size();
        addAllBean(collection);
        for (int i2 = 0; i2 < size; i2++) {
            this.mData.remove(0);
        }
    }

    public void setAllowNoImage(boolean z) {
        this.allowNoImage = z;
    }

    public void setAssetsCache(boolean z) {
        this.isAssetsCache = z;
    }

    public void setData(List<?> list) {
        this.mData = list;
    }

    public void setDropDownViewResource(int i2) {
        this.mDropDownResource = i2;
    }

    public void setForeverCacheImage(boolean z) {
        this.isForeverCacheImage = z;
    }

    public void setViewBinder(SubViewBinder subViewBinder) {
        this.mViewBinder = subViewBinder;
    }

    /* loaded from: classes6.dex */
    public static class SubViewHolder {
        public static final String MORE_PARAMETER_LOADED = "loaded";
        public static final String MORE_PARAMETER_LOCAL_LOAD_IMAGE = "localLoadImage";
        public static final String MORE_PARAMETER_MANUAL_GET_IMAGE = "manualGetImage";
        private SimpleBeanAdapter adapter;
        private Object itemData;
        private View itemView;
        private Map<String, Object> moreParameter;
        private Object subData;
        private View subView;
        private int position = 0;
        private int subViewId = 0;

        public SubViewHolder() {
        }

        public SimpleBeanAdapter getAdapter() {
            return this.adapter;
        }

        public Object getItemData() {
            return this.itemData;
        }

        public View getItemView() {
            return this.itemView;
        }

        public Object getMoreParameter(String str) {
            Map<String, Object> map = this.moreParameter;
            if (map == null) {
                return null;
            }
            return map.get(str);
        }

        public int getPosition() {
            return this.position;
        }

        public Object getSubData() {
            return this.subData;
        }

        public View getSubView() {
            return this.subView;
        }

        public int getSubViewId() {
            return this.subViewId;
        }

        public void putMoreParameter(String str, Object obj) {
            if (this.moreParameter == null) {
                this.moreParameter = new HashMap();
            }
            this.moreParameter.put(str, obj);
        }

        public void setAdapter(SimpleBeanAdapter simpleBeanAdapter) {
            this.adapter = simpleBeanAdapter;
        }

        public void setItemData(Object obj) {
            this.itemData = obj;
        }

        public void setItemView(View view) {
            this.itemView = view;
        }

        public void setPosition(int i2) {
            this.position = i2;
        }

        public void setSubData(Object obj) {
            this.subData = obj;
        }

        public void setSubView(View view) {
            this.subView = view;
        }

        public void setSubViewId(int i2) {
            this.subViewId = i2;
        }

        public String toString() {
            return "SubViewHolder [adapter=" + this.adapter + ", itemData=" + this.itemData + ", itemView=" + this.itemView + ", position=" + this.position + ", subData=" + this.subData + ", subView=" + this.subView + ", subViewId=" + this.subViewId + "]";
        }

        public SubViewHolder(SubViewHolder subViewHolder) {
            setAdapter(subViewHolder.getAdapter());
            setPosition(subViewHolder.getPosition());
            setSubViewId(subViewHolder.getSubViewId());
            setItemData(subViewHolder.getItemData());
            setSubData(subViewHolder.getSubData());
            setItemView(subViewHolder.getItemView());
            setSubView(subViewHolder.getSubView());
        }
    }

    public SimpleBeanAdapter(Context context, List<?> list, int i2, String[] strArr, int[] iArr, JDDisplayImageOptions jDDisplayImageOptions) {
        this(context, list, i2, strArr, iArr);
        this.mDisplayImageOptions = jDDisplayImageOptions;
    }
}

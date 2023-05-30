package com.jingdong.common.utils;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class SimpleJsonAdapter extends BaseAdapter implements Filterable {
    private JSONArray mData;
    private int mDropDownResource;
    private SimpleFilter mFilter;
    private String[] mFrom;
    private LayoutInflater mInflater;
    private int mResource;
    private int[] mTo;
    private JSONArray mUnfilteredData;
    private ViewBinder mViewBinder;
    private final WeakHashMap<View, View[]> mHolders = new WeakHashMap<>();
    private ArrayList<View> viewList = new ArrayList<>();

    /* loaded from: classes6.dex */
    private class SimpleFilter extends Filter {
        private SimpleFilter() {
        }

        @Override // android.widget.Filter
        protected Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (SimpleJsonAdapter.this.mUnfilteredData == null) {
                SimpleJsonAdapter simpleJsonAdapter = SimpleJsonAdapter.this;
                simpleJsonAdapter.mUnfilteredData = simpleJsonAdapter.mData;
            }
            if (charSequence == null || charSequence.length() == 0) {
                JSONArray jSONArray = SimpleJsonAdapter.this.mUnfilteredData;
                filterResults.values = jSONArray;
                filterResults.count = jSONArray.length();
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                JSONArray jSONArray2 = SimpleJsonAdapter.this.mUnfilteredData;
                int length = jSONArray2.length();
                JSONArray jSONArray3 = new JSONArray();
                for (int i2 = 0; i2 < length; i2++) {
                    try {
                        JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                        if (jSONObject != null) {
                            int length2 = SimpleJsonAdapter.this.mTo.length;
                            for (int i3 = 0; i3 < length2; i3++) {
                                try {
                                    String[] split = jSONObject.getString(SimpleJsonAdapter.this.mFrom[i3]).split(LangUtils.SINGLE_SPACE);
                                    int length3 = split.length;
                                    int i4 = 0;
                                    while (true) {
                                        if (i4 >= length3) {
                                            break;
                                        } else if (split[i4].toLowerCase().startsWith(lowerCase)) {
                                            jSONArray3.put(jSONObject);
                                            break;
                                        } else {
                                            i4++;
                                        }
                                    }
                                } catch (JSONException e2) {
                                    throw new RuntimeException(e2);
                                }
                            }
                        }
                    } catch (JSONException e3) {
                        throw new RuntimeException(e3);
                    }
                }
                filterResults.values = jSONArray3;
                filterResults.count = jSONArray3.length();
            }
            return filterResults;
        }

        @Override // android.widget.Filter
        protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            SimpleJsonAdapter.this.mData = (JSONArray) filterResults.values;
            if (filterResults.count > 0) {
                SimpleJsonAdapter.this.notifyDataSetChanged();
            } else {
                SimpleJsonAdapter.this.notifyDataSetInvalidated();
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface ViewBinder {
        boolean setViewValue(View view, Object obj, String str);
    }

    public SimpleJsonAdapter(Context context, JSONArray jSONArray, int i2, String[] strArr, int[] iArr) {
        this.mData = jSONArray;
        this.mDropDownResource = i2;
        this.mResource = i2;
        this.mFrom = strArr;
        this.mTo = iArr;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    private void bindView(int i2, View view) {
        try {
            JSONObject jSONObject = this.mData.getJSONObject(i2);
            if (jSONObject == null) {
                return;
            }
            ViewBinder viewBinder = this.mViewBinder;
            View[] viewArr = this.mHolders.get(view);
            String[] strArr = this.mFrom;
            int length = this.mTo.length;
            for (int i3 = 0; i3 < length; i3++) {
                View view2 = viewArr[i3];
                if (view2 != null) {
                    try {
                        Object obj = jSONObject.get(strArr[i3]);
                        String obj2 = obj == null ? "" : obj.toString();
                        if (viewBinder != null ? viewBinder.setViewValue(view2, obj, obj2) : false) {
                            continue;
                        } else if (view2 instanceof Checkable) {
                            if (obj instanceof Boolean) {
                                ((Checkable) view2).setChecked(((Boolean) obj).booleanValue());
                            } else {
                                throw new IllegalStateException(view2.getClass().getName() + " should be bound to a Boolean, not a " + obj.getClass());
                            }
                        } else if (view2 instanceof TextView) {
                            setViewText((TextView) view2, obj2);
                        } else if (view2 instanceof ImageView) {
                            if (obj instanceof Integer) {
                                setViewImage((ImageView) view2, ((Integer) obj).intValue());
                            } else {
                                setViewImage((ImageView) view2, obj2);
                            }
                        } else {
                            throw new IllegalStateException(view2.getClass().getName() + " is not a  view that can be bounds by this SimpleAdapter");
                        }
                    } catch (JSONException e2) {
                        throw new RuntimeException(e2);
                    }
                }
            }
        } catch (JSONException e3) {
            throw new RuntimeException(e3);
        }
    }

    private View createViewFromResource(int i2, View view, ViewGroup viewGroup, int i3) {
        if (view == null) {
            View inflate = ImageUtil.inflate(i3, viewGroup, false);
            int[] iArr = this.mTo;
            int length = iArr.length;
            View[] viewArr = new View[length];
            for (int i4 = 0; i4 < length; i4++) {
                viewArr[i4] = inflate.findViewById(iArr[i4]);
            }
            this.mHolders.put(inflate, viewArr);
            this.viewList.add(inflate);
            view = inflate;
        }
        bindView(i2, view);
        return view;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mData.length();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        return createViewFromResource(i2, view, viewGroup, this.mDropDownResource);
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mFilter == null) {
            this.mFilter = new SimpleFilter();
        }
        return this.mFilter;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        try {
            return this.mData.get(i2);
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        return createViewFromResource(i2, view, viewGroup, this.mResource);
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setDropDownViewResource(int i2) {
        this.mDropDownResource = i2;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        this.mViewBinder = viewBinder;
    }

    public void setViewImage(ImageView imageView, int i2) {
        imageView.setImageResource(i2);
    }

    public void setViewText(TextView textView, String str) {
        textView.setText(str);
    }

    public View getView(int i2) {
        if (OKLog.D) {
            OKLog.d(ApkDownloadTable.FIELD_SIZE, "" + this.viewList.size());
            return null;
        }
        return null;
    }

    public void setViewImage(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }
}

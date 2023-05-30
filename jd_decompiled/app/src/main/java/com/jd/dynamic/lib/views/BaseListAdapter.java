package com.jd.dynamic.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes13.dex */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    /* renamed from: g  reason: collision with root package name */
    protected JSONArray f2466g;

    public BaseListAdapter(Context context, JSONArray jSONArray) {
        this.f2466g = jSONArray;
    }

    public abstract void bindViewHolder(int i2, RecyclerView.ViewHolder viewHolder);

    public abstract void convert(RecyclerView.ViewHolder viewHolder, T t, int i2);

    @Override // android.widget.Adapter
    public int getCount() {
        JSONArray jSONArray = this.f2466g;
        if (jSONArray == null) {
            return 0;
        }
        return jSONArray.length();
    }

    public JSONArray getData() {
        JSONArray jSONArray = this.f2466g;
        if (jSONArray != null) {
            return jSONArray;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public T getItem(int i2) {
        try {
            JSONArray jSONArray = this.f2466g;
            if (jSONArray != null && jSONArray.get(i2) != null && !TextUtils.equals(this.f2466g.get(i2).toString(), DYConstants.DY_NULL_STR)) {
                return (T) this.f2466g.get(i2);
            }
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        RecyclerView.ViewHolder viewHolder = getViewHolder(view, viewGroup, i2);
        if (getItem(i2) == null) {
            return null;
        }
        convert(viewHolder, getItem(i2), i2);
        return viewHolder.itemView;
    }

    public abstract RecyclerView.ViewHolder getViewHolder(View view, ViewGroup viewGroup, int i2);

    public void resetData(JSONArray jSONArray) {
        if (this.f2466g == null || jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        this.f2466g = jSONArray;
        notifyDataSetChanged();
    }

    public void updateData(JSONArray jSONArray) {
        this.f2466g = jSONArray;
    }
}

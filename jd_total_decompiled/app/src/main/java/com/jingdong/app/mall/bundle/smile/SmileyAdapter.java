package com.jingdong.app.mall.bundle.smile;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jingdong.app.mall.bundle.icssdk_smile.R;
import com.jingdong.app.mall.bundle.smile.SmileyPageAdapter;
import com.jingdong.app.mall.bundle.smile.model.SmileBean;
import com.jingdong.app.mall.bundle.smile.utils.BitmapUtil;
import com.jingdong.app.mall.bundle.updownload.utils.FileUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class SmileyAdapter extends SimpleyBaseAdapter<SmileBean> {
    static final String TAG = SmileyAdapter.class.getName();
    private Context mContext;
    private LayoutInflater mInflater;
    private List<SmileBean> mList;
    private SmileyPageAdapter.GridViewItemSelected mListener;
    private Map<String, String> mSmileNameMap;

    /* loaded from: classes3.dex */
    private class GridHolder {
        ImageView icon;

        private GridHolder() {
        }
    }

    public SmileyAdapter(Context context, List<SmileBean> list, SmileyPageAdapter.GridViewItemSelected gridViewItemSelected) {
        super(context, list);
        this.mSmileNameMap = new HashMap();
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
        this.mListener = gridViewItemSelected;
        this.mSmileNameMap = new HashMap();
        String[] stringArray = context.getResources().getStringArray(R.array.icssdk_smile_talkback_emoji_small_key);
        String[] stringArray2 = context.getResources().getStringArray(R.array.icssdk_smile_talkback_emoji_small_name);
        if (stringArray == null || stringArray.length <= 0 || stringArray2 == null || stringArray2.length <= 0 || stringArray.length != stringArray2.length) {
            return;
        }
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            this.mSmileNameMap.put(stringArray[i2], stringArray2[i2]);
        }
    }

    @Override // com.jingdong.app.mall.bundle.smile.SimpleyBaseAdapter, android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // com.jingdong.app.mall.bundle.smile.SimpleyBaseAdapter, android.widget.Adapter
    public Object getItem(int i2) {
        return this.mList.get(i2);
    }

    @Override // com.jingdong.app.mall.bundle.smile.SimpleyBaseAdapter, android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // com.jingdong.app.mall.bundle.smile.SimpleyBaseAdapter, android.widget.Adapter
    public View getView(final int i2, final View view, ViewGroup viewGroup) {
        GridHolder gridHolder;
        SmileBean smileBean;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.icssdk_smile_chat_smiley_item, (ViewGroup) null);
            gridHolder = new GridHolder();
            gridHolder.icon = (ImageView) view.findViewById(R.id.jd_ics_sdk_iv_smiley_icon);
            view.setTag(gridHolder);
        } else {
            gridHolder = (GridHolder) view.getTag();
        }
        List<SmileBean> list = this.mList;
        if (list != null && (smileBean = list.get(i2)) != null) {
            if (gridHolder.icon == null) {
                OKLog.d(TAG, "holder.icon is null");
            }
            if (!TextUtils.isEmpty(smileBean.desclocal)) {
                gridHolder.icon.setContentDescription(smileBean.desclocal);
            }
            if (!TextUtils.isEmpty(smileBean.path) && FileUtils.isFileExist(smileBean.path)) {
                gridHolder.icon.setImageDrawable(new BitmapDrawable(this.mContext.getResources(), BitmapUtil.parseBitmap(smileBean.path)));
            } else if (!TextUtils.isEmpty(smileBean.url2x)) {
                Glide.with(this.mContext).load(smileBean.url2x).into(gridHolder.icon);
            } else {
                gridHolder.icon.setImageResource(smileBean.delIcon);
            }
            gridHolder.icon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.smile.SmileyAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (SmileyAdapter.this.mListener != null) {
                        SmileyAdapter.this.mListener.onGridViewItemSelected(view, (SmileBean) SmileyAdapter.this.mList.get(i2));
                    }
                }
            });
        }
        return view;
    }

    @Override // com.jingdong.app.mall.bundle.smile.SimpleyBaseAdapter
    public void notifyDataSetChanged(List<SmileBean> list) {
        super.notifyDataSetChanged();
    }
}

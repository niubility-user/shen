package com.jingdong.common.unification.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.R;
import com.jingdong.common.unification.filter.FilterTools;
import com.jingdong.common.unification.filter.filter.CommonFilter;
import com.jingdong.common.unification.filter.video.ExtractDecodeEditEncodeMux;
import com.jingdong.common.unification.video.VideoInfoUtil;
import com.jingdong.common.unification.video.VideoUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class FilterView extends LinearLayout {
    private FilterAdapter adapter;
    private ExtractDecodeEditEncodeMux extractDecodeEdit;
    private FilterChangerListener filterChangerListener;
    private Context mContext;
    private FilterTools.FilterAdjuster mFilterAdjuster;
    private MainHandler mUIHandler;
    private FilterProgressChangerListener progressChangerListener;
    private RecyclerView recyclerView;
    private SeekBar seekBar;
    private LinearLayout seekBarLayot;
    private FilterInfo selectImageFilter;

    /* loaded from: classes6.dex */
    public interface FilterChangerListener {
        void onFilterChanged(FilterTools.FilterType filterType, CommonFilter commonFilter);
    }

    /* loaded from: classes6.dex */
    public interface FilterProgressChangerListener {
        void onFilterProgressChanged();
    }

    /* loaded from: classes6.dex */
    private class MainHandler extends Handler {
        private MainHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 200 || FilterView.this.adapter == null) {
                return;
            }
            FilterView.this.adapter.notifyItemChanged(message.arg1);
        }
    }

    public FilterView(Context context) {
        this(context, null);
    }

    public void addFilter(String str, int i2, int i3, int i4, FilterTools.FilterType filterType, boolean z, final ExtractDecodeEditEncodeMux.ResultListener resultListener) {
        if (filterType == FilterTools.FilterType.NO_FILTER && resultListener != null) {
            resultListener.onResult(true, str, "");
            return;
        }
        String createOutVideoPath = VideoUtil.createOutVideoPath(str);
        String createFilterVideoName = VideoUtil.createFilterVideoName(str, filterType, z);
        ExtractDecodeEditEncodeMux extractDecodeEditEncodeMux = new ExtractDecodeEditEncodeMux(this.mContext);
        this.extractDecodeEdit = extractDecodeEditEncodeMux;
        extractDecodeEditEncodeMux.setSource(str);
        this.extractDecodeEdit.setOutputFile(createOutVideoPath + createFilterVideoName);
        this.extractDecodeEdit.setVideoOrientation(i4);
        this.extractDecodeEdit.setFilterType(filterType);
        this.extractDecodeEdit.setFilterProgress(getSeekBarProgress());
        this.extractDecodeEdit.testExtractDecodeEditEncodeMuxAudioVideo(new ExtractDecodeEditEncodeMux.ResultListener() { // from class: com.jingdong.common.unification.filter.FilterView.3
            @Override // com.jingdong.common.unification.filter.video.ExtractDecodeEditEncodeMux.ResultListener
            public void onResult(boolean z2, String str2, String str3) {
                ExtractDecodeEditEncodeMux.ResultListener resultListener2 = resultListener;
                if (resultListener2 != null) {
                    resultListener2.onResult(z2, str2, str3);
                    FilterView.this.extractDecodeEdit.setHandlerThread(null);
                }
            }
        }, i2, i3);
    }

    public void destory() {
        this.mUIHandler.removeCallbacksAndMessages(null);
        if (this.adapter != null) {
            this.adapter = null;
        }
    }

    public int getSeekBarProgress() {
        return this.seekBar.getProgress();
    }

    public void initData(Bitmap bitmap, List<FilterTools.FilterType> list) {
        List<FilterInfo> initFilter;
        Bitmap scaleBitmap = VideoInfoUtil.scaleBitmap(bitmap, DpiUtil.dip2px(this.mContext, 70.0f), DpiUtil.dip2px(this.mContext, 70.0f));
        if (list != null && list.size() != 0) {
            initFilter = FilterTools.initFilter(this.mContext, scaleBitmap, list);
        } else {
            initFilter = FilterTools.initFilter(this.mContext, scaleBitmap);
        }
        if (initFilter.size() > 0) {
            this.selectImageFilter = initFilter.get(0);
        }
        FilterTools.getFilterBitmap(this.mContext, initFilter, scaleBitmap, this.mUIHandler);
        FilterAdapter filterAdapter = new FilterAdapter(this.mContext, initFilter);
        this.adapter = filterAdapter;
        this.recyclerView.setAdapter(filterAdapter);
        this.adapter.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.filter.FilterView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FilterView.this.selectImageFilter != null) {
                    FilterView.this.selectImageFilter.isSelect = false;
                }
                FilterView.this.selectImageFilter = (FilterInfo) view.getTag();
                FilterView.this.selectImageFilter.isSelect = true;
                FilterView.this.adapter.notifyDataSetChanged();
                CommonFilter createFilterForType = FilterTools.createFilterForType(FilterView.this.selectImageFilter.type);
                if (createFilterForType == null || FilterView.this.filterChangerListener == null) {
                    return;
                }
                FilterView.this.mFilterAdjuster = new FilterTools.FilterAdjuster(createFilterForType);
                if (FilterView.this.mFilterAdjuster.canAdjust()) {
                    FilterView.this.seekBarLayot.setVisibility(0);
                    FilterView.this.seekBar.setProgress(FilterView.this.seekBar.getMax());
                } else {
                    FilterView.this.seekBarLayot.setVisibility(8);
                }
                FilterView.this.filterChangerListener.onFilterChanged(FilterView.this.selectImageFilter.type, createFilterForType);
            }
        });
    }

    public boolean isNeedFilter(FilterTools.FilterType filterType) {
        return filterType != FilterTools.FilterType.NO_FILTER;
    }

    public void setFilterChangerListener(FilterChangerListener filterChangerListener) {
        this.filterChangerListener = filterChangerListener;
    }

    public void setProgressChangerListener(FilterProgressChangerListener filterProgressChangerListener) {
        this.progressChangerListener = filterProgressChangerListener;
    }

    public FilterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FilterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        this.mUIHandler = new MainHandler();
        LayoutInflater.from(context).inflate(R.layout.common_filter_view, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
        this.seekBarLayot = (LinearLayout) findViewById(R.id.seekBarLayot);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.seekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.jingdong.common.unification.filter.FilterView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar2, int i3, boolean z) {
                if (FilterView.this.mFilterAdjuster != null) {
                    FilterView.this.mFilterAdjuster.adjust(i3);
                    if (FilterView.this.progressChangerListener != null) {
                        FilterView.this.progressChangerListener.onFilterProgressChanged();
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar2) {
            }
        });
    }
}

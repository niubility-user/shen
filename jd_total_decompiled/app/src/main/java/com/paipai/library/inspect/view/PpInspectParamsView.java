package com.paipai.library.inspect.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.DpiUtil;
import com.paipai.library.inspect.adapter.PpInspectParamsAdapter;
import com.paipai.library.inspect.dataclass.PpBaseOutlines;
import com.paipai.library.inspect.dataclass.PpInspectParamsCode;
import com.paipai.library.inspect.dataclass.PpInspectParamsDesc;
import com.paipai.library.inspect.dataclass.PpInspectParamsItems;
import com.paipai.library.inspect.dataclass.PpInspectParamsQuailty;
import com.paipai.library.inspect.dataclass.PpReportOutlines;
import com.paipai.library.inspect.dataclass.PpSpecificationInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class PpInspectParamsView extends LinearLayout {
    private RecyclerView mInspectParamsView;

    public PpInspectParamsView(Context context) {
        super(context);
        setup();
    }

    private void setup() {
        setOrientation(1);
        int dip2px = DpiUtil.dip2px(getContext(), 10.0f);
        setPadding(dip2px, 0, dip2px, dip2px);
        RecyclerView recyclerView = new RecyclerView(getContext());
        addView(recyclerView, new LinearLayout.LayoutParams(-1, -1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.paipai.library.inspect.view.PpInspectParamsView.1
            final int offset;

            {
                this.offset = DpiUtil.dip2px(PpInspectParamsView.this.getContext(), 8.0f);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView2, @NonNull RecyclerView.State state) {
                rect.bottom = this.offset;
            }
        });
        this.mInspectParamsView = recyclerView;
    }

    public void reload(String str, boolean z) {
        if (z) {
            setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_141212));
        } else {
            setBackgroundColor(Color.parseColor("#F2F2F2"));
        }
        RecyclerView recyclerView = this.mInspectParamsView;
        if (recyclerView == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            PpSpecificationInfo ppSpecificationInfo = (PpSpecificationInfo) new GsonBuilder().create().fromJson(str, PpSpecificationInfo.class);
            if (ppSpecificationInfo == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PpInspectParamsQuailty(ppSpecificationInfo.getQualityId(), ppSpecificationInfo.getInspectDesc()));
            List<PpBaseOutlines> baseOutlines = ppSpecificationInfo.getBaseOutlines();
            if (baseOutlines != null && !baseOutlines.isEmpty()) {
                arrayList.add(new PpInspectParamsItems(baseOutlines));
            }
            List<PpReportOutlines> reportOutlines = ppSpecificationInfo.getReportOutlines();
            if (reportOutlines != null && !reportOutlines.isEmpty()) {
                arrayList.add(new PpInspectParamsDesc(reportOutlines));
            }
            arrayList.add(new PpInspectParamsCode(ppSpecificationInfo.getCode(), ppSpecificationInfo.getCodeName()));
            recyclerView.setAdapter(new PpInspectParamsAdapter(arrayList, z));
        } catch (JsonSyntaxException unused) {
        }
    }

    public PpInspectParamsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setup();
    }
}

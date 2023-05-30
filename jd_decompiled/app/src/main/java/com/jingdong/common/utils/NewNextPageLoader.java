package com.jingdong.common.utils;

import android.view.View;
import android.widget.AdapterView;
import com.jingdong.common.frame.IMyActivity;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class NewNextPageLoader extends NextPageLoader {
    private int totalPageCount;

    public NewNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str) {
        super(iMyActivity, adapterView, view, str);
        this.totalPageCount = 0;
    }

    public int getTotalPageCount() {
        return this.totalPageCount;
    }

    @Override // com.jingdong.common.utils.NextPageLoader
    protected boolean judgeIsLastPage(ArrayList<?> arrayList) {
        if (arrayList.size() < this.pageSize.intValue() || !this.isPaging) {
            return this.totalPageCount == 0 || this.pageNum.intValue() == this.totalPageCount;
        }
        return false;
    }

    public void setTotalPageCount(int i2) {
        this.totalPageCount = i2;
    }

    public NewNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject) {
        super(iMyActivity, adapterView, view, str, jSONObject);
        this.totalPageCount = 0;
    }

    public NewNextPageLoader(IMyActivity iMyActivity, AdapterView adapterView, View view, String str, JSONObject jSONObject, String str2) {
        super(iMyActivity, adapterView, view, str, jSONObject, str2);
        this.totalPageCount = 0;
    }
}

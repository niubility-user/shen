package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import com.jingdong.common.jdreactFramework.track.RenderDataReport;
import com.jingdong.jdreactframewok.R;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes5.dex */
public class DebugReportDataActivity extends Activity implements View.OnClickListener {
    private ArrayList<Map> mDataList;
    private LayoutInflater mInflater;
    private ItemAdapter mItemAdapter;
    private TextView mTab1;
    private TextView mTab2;
    int mType = 1;

    /* loaded from: classes5.dex */
    private class ItemAdapter extends BaseAdapter {
        private ItemAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return DebugReportDataActivity.this.mDataList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return DebugReportDataActivity.this.mDataList.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            String str;
            View inflate = view == null ? DebugReportDataActivity.this.mInflater.inflate(R.layout.debug_item_report_data, (ViewGroup) null) : view;
            TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
            TextView textView2 = (TextView) inflate.findViewById(R.id.f12872tv);
            Map map = (Map) DebugReportDataActivity.this.mDataList.get(i2);
            View view2 = inflate;
            if (DebugReportDataActivity.this.mType == 1) {
                str = "session: " + map.get("session") + "\nloadresult: " + map.get("loadresult") + "\nloadtime: " + map.get("loadtime") + "ms\nloadtotaltime: " + map.get("loadtotaltime") + "ms\nloadfailcode: " + map.get("loadfailcode") + "\nloadfailreason: " + map.get("loadfailreason") + "\nbundletype: " + map.get("bundletype") + "\nbundlesource: " + map.get("bundlesource") + "\nisfirstload: " + map.get("isfirstload") + "\nmodulename: " + map.get(JDReactConstant.IntentConstant.MODULE_NAME) + "\nmoduleversion: " + map.get("moduleversion") + "\nloadstarttime: " + map.get("loadstarttime") + "\nloadendtime: " + map.get("loadendtime") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            } else {
                str = "session: " + map.get("session") + "\ndownloadresult: " + map.get("downloadresult") + "\ndownloadtime: " + map.get("downloadtime") + "ms\ndownloadfailcode: " + map.get("downloadfailcode") + "\ndownloadfailreason: " + map.get("downloadfailreason") + "\nbundletype: " + map.get("bundletype") + "\nbundlesource: " + map.get("bundlesource") + "\nmodulename: " + map.get(JDReactConstant.IntentConstant.MODULE_NAME) + "\nmoduleversion: " + map.get("moduleversion") + "\ndownloadstarttime: " + map.get("downloadstarttime") + "\ndownloadendtime: " + map.get("downloadendtime") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
                textView2.setText(str);
            }
            textView.setText("\u3010" + map.get(JDReactConstant.IntentConstant.MODULE_NAME) + "\u3011" + map.get("title"));
            textView2.setText(str);
            return view2;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.tab1) {
            this.mTab1.setTextColor(Color.parseColor("#2196F3"));
            this.mTab2.setTextColor(Color.parseColor("#555555"));
            this.mType = 1;
            this.mDataList = RenderDataReport.sRenderReportList;
        } else if (view.getId() == R.id.tab2) {
            this.mTab2.setTextColor(Color.parseColor("#2196F3"));
            this.mTab1.setTextColor(Color.parseColor("#555555"));
            this.mType = 2;
            this.mDataList = PluginDownloadInfo.sDownReportList;
        }
        this.mItemAdapter.notifyDataSetChanged();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_debug_report_data);
        this.mType = getIntent().getIntExtra("type", 1);
        this.mInflater = LayoutInflater.from(this);
        this.mTab1 = (TextView) findViewById(R.id.tab1);
        this.mTab2 = (TextView) findViewById(R.id.tab2);
        ListView listView = (ListView) findViewById(R.id.list);
        this.mDataList = this.mType == 1 ? RenderDataReport.sRenderReportList : PluginDownloadInfo.sDownReportList;
        ItemAdapter itemAdapter = new ItemAdapter();
        this.mItemAdapter = itemAdapter;
        listView.setAdapter((ListAdapter) itemAdapter);
        this.mTab1.setOnClickListener(this);
        this.mTab2.setOnClickListener(this);
    }
}

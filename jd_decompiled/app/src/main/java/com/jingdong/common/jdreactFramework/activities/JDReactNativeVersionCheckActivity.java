package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateRequest;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.jdreactframewok.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes5.dex */
public class JDReactNativeVersionCheckActivity extends Activity {
    private VersionItemAdapter adapter;
    private ArrayList<String> array;
    private LayoutInflater inflater;
    private boolean isFirstLoad = true;
    private ListView listView;
    private Map<String, String> map;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class VersionItemAdapter extends BaseAdapter {
        private VersionItemAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return JDReactNativeVersionCheckActivity.this.array.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return JDReactNativeVersionCheckActivity.this.array.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = JDReactNativeVersionCheckActivity.this.inflater.inflate(R.layout.jdreact_version_item, (ViewGroup) null);
            }
            String str = (String) JDReactNativeVersionCheckActivity.this.array.get(i2);
            ((TextView) view.findViewById(R.id.tv_name)).setText(str);
            ((TextView) view.findViewById(R.id.tv_version_inner)).setText("\u6700\u65b0\u7248\u672c\uff1a" + ((String) JDReactNativeVersionCheckActivity.this.map.get(str)));
            return view;
        }
    }

    private TextView createTextView(String str, View.OnClickListener onClickListener) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView.setGravity(17);
        textView.setTextSize(1, 16.0f);
        textView.setPadding(20, 25, 20, 25);
        textView.setText(str);
        textView.setOnClickListener(onClickListener);
        return textView;
    }

    private void delete(File file) {
        try {
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        for (File file2 : listFiles) {
                            delete(file2);
                        }
                    }
                    file.delete();
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteDownloadFile() {
        File file = JDReactConstant.ReactDownloadPathOld;
        if (file == null || !file.exists()) {
            return;
        }
        delete(file);
    }

    private void makeData() {
        this.array.clear();
        Map<String, String> mergedPluginVersionLists = ReactVersionUtils.getMergedPluginVersionLists();
        this.map = mergedPluginVersionLists;
        this.array.addAll(mergedPluginVersionLists.keySet());
        Collections.sort(this.array);
    }

    private View newDividerView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.jdreact_common_textview_bg_color));
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 3));
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshData() {
        makeData();
        VersionItemAdapter versionItemAdapter = this.adapter;
        if (versionItemAdapter != null) {
            versionItemAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("\u63d0\u793a\uff1a");
        builder.setMessage("\u662f\u5426\u5220\u9664" + str + "\u6a21\u5757\uff1f(\u5916\u7f6e\u8d44\u6e90)");
        builder.setCancelable(true);
        builder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(JDReactConstant.ReactDownloadPath.getAbsolutePath());
                    String str2 = File.separator;
                    sb.append(str2);
                    sb.append(str);
                    sb.append(str2);
                    FileUtil.emptyDir(new File(sb.toString()));
                    Toast.makeText(JDReactNativeVersionCheckActivity.this, "\u5220\u9664\u6210\u529f", 0).show();
                    JDReactNativeVersionCheckActivity.this.refreshData();
                } catch (Exception e2) {
                    Toast.makeText(JDReactNativeVersionCheckActivity.this, "\u5220\u9664\u5931\u8d25", 0).show();
                    e2.printStackTrace();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("\u53d6\u6d88", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.inflater = LayoutInflater.from(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.addView(createTextView("\u8bf7\u6c42\u66f4\u65b0\u63a5\u53e3", new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Map<String, String> mergedPluginVersionLists = ReactVersionUtils.getMergedPluginVersionLists();
                ReactSharedPreferenceUtils.putLastCheckCompleteFlag(true);
                ReactNativeUpdateRequest.getInstance(JDReactNativeVersionCheckActivity.this.getApplicationContext()).sendReactUpdateRequest(mergedPluginVersionLists);
                ReactSharedPreferenceUtils.putLastCheckTime();
                Toast.makeText(JDReactNativeVersionCheckActivity.this, "\u8bf7\u6c42\u5df2\u53d1\u9001", 0).show();
            }
        }));
        linearLayout.addView(newDividerView());
        linearLayout.addView(createTextView("\u5237\u65b0\u6570\u636e", new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDReactNativeVersionCheckActivity.this.refreshData();
            }
        }));
        linearLayout.addView(newDividerView());
        linearLayout.addView(createTextView("\u4e00\u952e\u5220\u9664\u672c\u5730\u5305", new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDReactNativeVersionCheckActivity.this.deleteDownloadFile();
                JDReactNativeVersionCheckActivity.this.refreshData();
            }
        }));
        linearLayout.addView(newDividerView());
        this.listView = new ListView(this);
        this.array = new ArrayList<>();
        makeData();
        this.adapter = new VersionItemAdapter();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                Intent intent = new Intent();
                intent.setClass(JDReactNativeVersionCheckActivity.this, JDReactNativeSubVersionCheckActivity.class);
                intent.putExtra("moduleName", (String) adapterView.getItemAtPosition(i2));
                JDReactNativeVersionCheckActivity.this.startActivity(intent);
            }
        });
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheckActivity.5
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                JDReactNativeVersionCheckActivity.this.showDialog((String) adapterView.getItemAtPosition(i2));
                return true;
            }
        });
        linearLayout.addView(this.listView, new LinearLayout.LayoutParams(-1, -1));
        setContentView(linearLayout);
        this.listView.setAdapter((ListAdapter) this.adapter);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!this.isFirstLoad && this.adapter != null) {
            refreshData();
        } else {
            this.isFirstLoad = false;
        }
    }
}

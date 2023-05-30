package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.utils.FileUtil;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import com.jingdong.common.jdreactFramework.utils.VersionUtils;
import com.jingdong.common.jdreactFramework.utils.XmlUtils;
import com.jingdong.jdreactframewok.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes5.dex */
public class JDReactNativeSubVersionCheckActivity extends Activity {
    private List<PluginVersion> array;
    private LayoutInflater inflater;
    private ListView listView;
    private String moduleName;
    private VersionItemAdapter versionItemAdapter;

    /* loaded from: classes5.dex */
    private class VersionItemAdapter extends BaseAdapter {
        private VersionItemAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return JDReactNativeSubVersionCheckActivity.this.array.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return JDReactNativeSubVersionCheckActivity.this.array.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = JDReactNativeSubVersionCheckActivity.this.inflater.inflate(R.layout.jdreact_version_item_detail, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.tv_name);
            TextView textView2 = (TextView) view.findViewById(R.id.tv_detail_version);
            TextView textView3 = (TextView) view.findViewById(R.id.tv_detail_path);
            PluginVersion pluginVersion = (PluginVersion) JDReactNativeSubVersionCheckActivity.this.array.get(i2);
            StringBuilder sb = new StringBuilder();
            sb.append(pluginVersion.pluginName);
            sb.append(pluginVersion.full ? "(\u5168\u5305)" : "(\u62c6\u5206\u5305)");
            textView.setText(sb.toString());
            String str = "\u7248\u672c\uff1a" + pluginVersion.pluginVersion;
            if (!pluginVersion.full && !TextUtils.isEmpty(pluginVersion.pluginCommonVersion)) {
                str = str + ", \u4f9d\u8d56common\u5305\u7248\u672c\uff1a" + pluginVersion.pluginCommonVersion;
            }
            textView2.setText(str);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(pluginVersion.isPreset ? "(\u5185\u7f6e)" : "(\u5916\u7f6e)");
            sb2.append(" \u8def\u5f84\u4e3a\uff1a");
            sb2.append(pluginVersion.pluginDir);
            textView3.setText(sb2.toString());
            return view;
        }
    }

    private void addPlugin(ArrayList<PluginVersion> arrayList, PluginVersion pluginVersion, String str, boolean z) {
        if (pluginVersion != null) {
            pluginVersion.pluginDir = str;
            pluginVersion.full = z;
            arrayList.add(pluginVersion);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PluginVersion> getData(String str) {
        ArrayList<PluginVersion> arrayList = new ArrayList<>();
        PluginVersion pluginPreloadDataPath = ReactVersionUtils.getPluginPreloadDataPath(getApplicationContext(), str);
        if (pluginPreloadDataPath != null) {
            arrayList.add(pluginPreloadDataPath);
        }
        StringBuilder sb = new StringBuilder();
        File file = JDReactConstant.ReactDownloadPath;
        sb.append(file.getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        sb.append(JDReactConstant.BUFF_DIR_FULL);
        sb.append(str2);
        String sb2 = sb.toString();
        addPlugin(arrayList, XmlUtils.getPluginVersion(sb2 + JDReactConstant.BUFF_DIR_ONE + str2 + str + ".version"), sb2 + JDReactConstant.BUFF_DIR_ONE, true);
        addPlugin(arrayList, XmlUtils.getPluginVersion(sb2 + JDReactConstant.BUFF_DIR_TWO + str2 + str + ".version"), sb2 + JDReactConstant.BUFF_DIR_TWO, true);
        String str3 = file.getAbsolutePath() + str2 + str + str2 + JDReactConstant.BUFF_DIR_SPLIT + str2;
        PluginVersion pluginVersion = XmlUtils.getPluginVersion(str3 + JDReactConstant.BUFF_DIR_ONE + str2 + str + ".version");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str3);
        sb3.append(JDReactConstant.BUFF_DIR_ONE);
        addPlugin(arrayList, pluginVersion, sb3.toString(), false);
        PluginVersion pluginVersion2 = XmlUtils.getPluginVersion(str3 + JDReactConstant.BUFF_DIR_TWO + str2 + str + ".version");
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str3);
        sb4.append(JDReactConstant.BUFF_DIR_TWO);
        addPlugin(arrayList, pluginVersion2, sb4.toString(), false);
        Collections.sort(arrayList, new Comparator<PluginVersion>() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSubVersionCheckActivity.3
            @Override // java.util.Comparator
            public int compare(PluginVersion pluginVersion3, PluginVersion pluginVersion4) {
                return VersionUtils.compareVersion(pluginVersion4.pluginVersion, pluginVersion3.pluginVersion);
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final PluginVersion pluginVersion) {
        if (pluginVersion.isPreset) {
            Toast.makeText(this, "\u5185\u7f6e\u5305\u65e0\u6cd5\u5220\u9664", 0).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("\u63d0\u793a\uff1a");
        builder.setMessage("\u662f\u5426\u5220\u9664\uff1f");
        builder.setCancelable(true);
        builder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSubVersionCheckActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                try {
                    FileUtil.emptyDir(new File(pluginVersion.pluginDir));
                    PluginVersion pluginVersion2 = pluginVersion;
                    if (pluginVersion.pluginDir.contains(ReactSharedPreferenceUtils.getCurPath(pluginVersion2.full, pluginVersion2.pluginName))) {
                        PluginVersion pluginVersion3 = pluginVersion;
                        ReactSharedPreferenceUtils.reverseCurBakSP(pluginVersion3.full, pluginVersion3.pluginName);
                    }
                    Toast.makeText(JDReactNativeSubVersionCheckActivity.this, "\u5220\u9664\u6210\u529f", 0).show();
                    JDReactNativeSubVersionCheckActivity jDReactNativeSubVersionCheckActivity = JDReactNativeSubVersionCheckActivity.this;
                    jDReactNativeSubVersionCheckActivity.array = jDReactNativeSubVersionCheckActivity.getData(jDReactNativeSubVersionCheckActivity.moduleName);
                    if (!JDReactNativeSubVersionCheckActivity.this.array.isEmpty()) {
                        JDReactNativeSubVersionCheckActivity.this.versionItemAdapter.notifyDataSetChanged();
                    } else {
                        JDReactNativeSubVersionCheckActivity.this.finish();
                    }
                } catch (Exception e2) {
                    Toast.makeText(JDReactNativeSubVersionCheckActivity.this, "\u5220\u9664\u5931\u8d25", 0).show();
                    e2.printStackTrace();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("\u53d6\u6d88", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSubVersionCheckActivity.5
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
        this.moduleName = getIntent().getStringExtra("moduleName");
        this.inflater = LayoutInflater.from(this);
        this.listView = new ListView(this);
        this.array = getData(this.moduleName);
        VersionItemAdapter versionItemAdapter = new VersionItemAdapter();
        this.versionItemAdapter = versionItemAdapter;
        this.listView.setAdapter((ListAdapter) versionItemAdapter);
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSubVersionCheckActivity.1
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                JDReactNativeSubVersionCheckActivity.this.showDialog((PluginVersion) adapterView.getItemAtPosition(i2));
                return true;
            }
        });
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSubVersionCheckActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                PluginVersion pluginVersion = (PluginVersion) adapterView.getItemAtPosition(i2);
                if (JDReactHelper.newInstance().isIndependent()) {
                    Intent intent = new Intent();
                    intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, pluginVersion.pluginName);
                    if (!pluginVersion.isPreset) {
                        intent.putExtra("bundlePath", pluginVersion.pluginDir);
                    }
                    intent.setClass(JDReactNativeSubVersionCheckActivity.this, JDReactNativeDebugActivity.class);
                    JDReactNativeSubVersionCheckActivity.this.startActivity(intent);
                    return;
                }
                ReactActivityUtilsHelperBase.startJDReactActivityForDebug(JDReactNativeSubVersionCheckActivity.this, pluginVersion.pluginName, null, pluginVersion.pluginDir);
            }
        });
        setContentView(this.listView);
    }
}

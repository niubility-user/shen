package com.jingdong.common.jdreactFramework.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdreactFramework.utils.ReactVersionUtils;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class JDReactNativeVersionCheck extends BaseActivity {
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ListView listView = new ListView(this);
        final Map<String, String> dataPluginVersionLists = ReactVersionUtils.getDataPluginVersionLists();
        final Map<String, String> preloadPluginVersionLists = ReactVersionUtils.getPreloadPluginVersionLists();
        final Map<String, String> mergedPluginVersionLists = ReactVersionUtils.getMergedPluginVersionLists(preloadPluginVersionLists, dataPluginVersionLists);
        Set<String> keySet = mergedPluginVersionLists.keySet();
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(keySet);
        listView.setAdapter((ListAdapter) new ArrayAdapter(this, 17367043, arrayList));
        listView.setItemsCanFocus(false);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheck.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                String str = (String) arrayList.get(i2);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str2 = (String) preloadPluginVersionLists.get(str);
                String str3 = (String) dataPluginVersionLists.get(str);
                String str4 = (String) mergedPluginVersionLists.get(str);
                AlertDialog.Builder builder = new AlertDialog.Builder(JDReactNativeVersionCheck.this);
                builder.setTitle("\u7248\u672c\u4fe1\u606f");
                View inflate = JDReactNativeVersionCheck.this.getLayoutInflater().inflate(R.layout.cw, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.aao);
                TextView textView2 = (TextView) inflate.findViewById(R.id.aam);
                TextView textView3 = (TextView) inflate.findViewById(R.id.aaq);
                ((TextView) inflate.findViewById(R.id.aal)).setText(str);
                if (str2 != null) {
                    textView.setText(str2);
                }
                if (str4 != null) {
                    textView2.setText(str4);
                }
                if (str3 != null) {
                    textView3.setText(str3);
                }
                builder.setView(inflate);
                builder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeVersionCheck.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i3) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
        setContentView(listView);
    }
}

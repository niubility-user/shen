package com.jingdong.common.jdreactFramework.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import java.util.Set;

/* loaded from: classes5.dex */
public class PluginListFragment extends Fragment {
    private VersionItemAdapter adapter;
    private ArrayList<String> array;
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
            return PluginListFragment.this.array.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return PluginListFragment.this.array.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(PluginListFragment.this.getContext(), R.layout.jdreact_version_item, null);
            }
            String str = (String) PluginListFragment.this.array.get(i2);
            ((TextView) view.findViewById(R.id.tv_name)).setText(str);
            ((TextView) view.findViewById(R.id.tv_version_inner)).setText("\u6700\u65b0\u7248\u672c\uff1a" + ((String) PluginListFragment.this.map.get(str)));
            return view;
        }
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
        File file = JDReactConstant.ReactDownloadPath;
        if (file == null || !file.exists()) {
            return;
        }
        delete(file);
    }

    private void makeData(String str) {
        this.array.clear();
        Map<String, String> mergedPluginVersionLists = ReactVersionUtils.getMergedPluginVersionLists();
        this.map = mergedPluginVersionLists;
        Set<String> keySet = mergedPluginVersionLists.keySet();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : keySet) {
                if (!TextUtils.isEmpty(str2) && str2.toLowerCase().contains(str.toLowerCase())) {
                    this.array.add(str2);
                }
            }
        } else {
            this.array.addAll(keySet);
        }
        Collections.sort(this.array);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshData(String str) {
        makeData(str);
        VersionItemAdapter versionItemAdapter = this.adapter;
        if (versionItemAdapter != null) {
            versionItemAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("\u63d0\u793a\uff1a");
        builder.setMessage("\u662f\u5426\u5220\u9664" + str + "\u6a21\u5757\uff1f(\u5916\u7f6e\u8d44\u6e90)");
        builder.setCancelable(true);
        builder.setPositiveButton("\u786e\u5b9a", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.9
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
                    Toast.makeText(PluginListFragment.this.getContext(), "\u5220\u9664\u6210\u529f", 0).show();
                    PluginListFragment.this.refreshData(null);
                } catch (Exception e2) {
                    Toast.makeText(PluginListFragment.this.getContext(), "\u5220\u9664\u5931\u8d25", 0).show();
                    e2.printStackTrace();
                }
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("\u53d6\u6d88", new DialogInterface.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = View.inflate(getContext(), R.layout.fragment_rn_plugin_list, null);
        ((TextView) inflate.findViewById(R.id.tv_update)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Map<String, String> mergedPluginVersionLists = ReactVersionUtils.getMergedPluginVersionLists();
                ReactSharedPreferenceUtils.putLastCheckCompleteFlag(true);
                ReactNativeUpdateRequest.getInstance(PluginListFragment.this.getContext()).sendReactUpdateRequest(mergedPluginVersionLists);
                ReactSharedPreferenceUtils.putLastCheckTime();
                Toast.makeText(PluginListFragment.this.getContext(), "\u8bf7\u6c42\u5df2\u53d1\u9001", 0).show();
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_refresh)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PluginListFragment.this.refreshData(null);
                Toast.makeText(PluginListFragment.this.getContext(), "\u5237\u65b0\u5b8c\u6210", 0).show();
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_delete)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PluginListFragment.this.deleteDownloadFile();
                PluginListFragment.this.refreshData(null);
                Toast.makeText(PluginListFragment.this.getContext(), "\u5220\u9664\u5b8c\u6210", 0).show();
            }
        });
        final EditText editText = (EditText) inflate.findViewById(R.id.et_plugin_list_search_content);
        editText.setOnKeyListener(new View.OnKeyListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.4
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 0 && i2 == 66) {
                    PluginListFragment.this.refreshData(editText.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_plugin_list_search)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PluginListFragment.this.refreshData(editText.getText().toString().trim());
            }
        });
        ((TextView) inflate.findViewById(R.id.tv_plugin_list_search_clear)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                editText.setText("");
                PluginListFragment.this.refreshData(null);
            }
        });
        this.listView = (ListView) inflate.findViewById(R.id.ll_plugin_list);
        this.array = new ArrayList<>();
        makeData(null);
        this.adapter = new VersionItemAdapter();
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.7
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                Intent intent = new Intent();
                intent.setClass(PluginListFragment.this.getActivity(), JDReactNativeSubVersionCheckActivity.class);
                intent.putExtra("moduleName", (String) adapterView.getItemAtPosition(i2));
                PluginListFragment.this.startActivity(intent);
            }
        });
        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.PluginListFragment.8
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                PluginListFragment.this.showDialog((String) adapterView.getItemAtPosition(i2));
                return true;
            }
        });
        this.listView.setAdapter((ListAdapter) this.adapter);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (!this.isFirstLoad && this.adapter != null) {
            refreshData(null);
        } else {
            this.isFirstLoad = false;
        }
    }
}

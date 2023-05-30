package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.download.PluginDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdate;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtilsHelperBase;
import com.jingdong.jdreactframewok.R;
import java.io.File;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class DebugToolsFragment extends Fragment {
    public static final Pattern IPV4_PAT = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");

    /* JADX INFO: Access modifiers changed from: private */
    public void createFileDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    private String getDebugModuleName() {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getString("rn_debug_moduleName", JDReactConstant.AVAILABILITY_APIDEMOS);
    }

    private String getDebugParams() {
        return PreferenceManager.getDefaultSharedPreferences(getContext()).getString("rn_debug_params", "{\"index\":1}");
    }

    private Pair<String, String> getHostAndPort(String str) {
        int parseInt;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String[] split = str.split(":");
            if (split.length == 2 && (parseInt = Integer.parseInt(split[1])) >= 0 && parseInt <= 65535 && IPV4_PAT.matcher(split[0]).matches()) {
                return new Pair<>(split[0], split[1]);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidAddress(String str) {
        return getHostAndPort(str) != null;
    }

    private Pair<String, String> readDebugServerAddress() {
        return getHostAndPort(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("debug_http_host", ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveDebugInfo(String str, String str2) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        defaultSharedPreferences.edit().putString("rn_debug_moduleName", str).apply();
        defaultSharedPreferences.edit().putString("rn_debug_params", str2).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDebugServerAddress(String str) {
        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putString("debug_http_host", str).apply();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = View.inflate(getContext(), R.layout.fragment_rn_debug_tools, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_debug_update);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_debug_ip);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.et_debug_port);
        final EditText editText3 = (EditText) inflate.findViewById(R.id.et_download);
        editText3.setText("https://storage.jd.com/bucket-1/JDReactAPIDemos1666267681android.so?Expires=3813751329&AccessKey=nxgRi4Ob6sX9G8IT&Signature=LlAp7IfHrU7vJhvocM%2BKre7jikU%3D&jdreactkey=JDReactAPIDemos&jdreactmodule=JDReactAPIDemos&jdreactapp=JDReactAPIDemos");
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_debug_jump);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.cb_rn_debug_switch);
        CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.cb_rn_tools_switch);
        final EditText editText4 = (EditText) inflate.findViewById(R.id.et_debug_params);
        editText4.setText(getDebugParams());
        final EditText editText5 = (EditText) inflate.findViewById(R.id.et_debug_module_name);
        editText5.setText(getDebugModuleName());
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_debug_open);
        final EditText editText6 = (EditText) inflate.findViewById(R.id.et_file);
        inflate.findViewById(R.id.tv_create).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String obj = editText6.getText().toString();
                if (TextUtils.isEmpty(obj)) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                File file = JDReactConstant.ReactDownloadPath;
                sb.append(file.getAbsolutePath());
                String str = File.separator;
                sb.append(str);
                sb.append(obj);
                sb.append(str);
                sb.append(JDReactConstant.BUFF_DIR_FULL);
                sb.append(str);
                String sb2 = sb.toString();
                DebugToolsFragment.this.createFileDir(sb2 + JDReactConstant.BUFF_DIR_ONE + str);
                DebugToolsFragment.this.createFileDir(sb2 + JDReactConstant.BUFF_DIR_TWO + str);
                String str2 = file.getAbsolutePath() + str + obj + str + JDReactConstant.BUFF_DIR_SPLIT + str;
                DebugToolsFragment.this.createFileDir(str2 + JDReactConstant.BUFF_DIR_ONE + str);
                DebugToolsFragment.this.createFileDir(str2 + JDReactConstant.BUFF_DIR_TWO + str);
                Toast.makeText(DebugToolsFragment.this.getContext(), "\u521b\u5efa\u6210\u529f", 0).show();
            }
        });
        Pair<String, String> readDebugServerAddress = readDebugServerAddress();
        if (readDebugServerAddress != null) {
            editText.setText((CharSequence) readDebugServerAddress.first);
            editText2.setText((CharSequence) readDebugServerAddress.second);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                String trim2 = editText2.getText().toString().trim();
                if (TextUtils.isEmpty(trim2)) {
                    trim2 = editText2.getHint().toString().trim();
                }
                if (TextUtils.isEmpty(trim) || TextUtils.equals(trim, AndroidInfoHelpers.DEVICE_LOCALHOST)) {
                    DebugToolsFragment.this.setDebugServerAddress("");
                    Toast.makeText(DebugToolsFragment.this.getContext(), "\u5df2\u8bbe\u7f6elocalhost", 0).show();
                    return;
                }
                if (DebugToolsFragment.this.isValidAddress(trim + ":" + trim2)) {
                    editText.setText(trim2);
                    DebugToolsFragment.this.setDebugServerAddress(trim + ":" + trim2);
                    Toast.makeText(DebugToolsFragment.this.getContext(), "\u66f4\u65b0ip=" + trim + ":" + trim2, 0).show();
                    return;
                }
                Toast.makeText(DebugToolsFragment.this.getContext(), "\u65e0\u6548\u7684ip\u5730\u5740\u6216\u7aef\u53e3", 0).show();
            }
        });
        checkBox.setChecked(JDReactHelper.newInstance().isDebug());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                JDReactHelper.newInstance().changeDebug(z);
            }
        });
        checkBox2.setChecked(JDReactHelper.newInstance().isUserRNTools());
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                JDReactHelper.newInstance().changeUserRNTools(z);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String trim = editText3.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(DebugToolsFragment.this.getContext(), "\u8bf7\u8f93\u5165\u5730\u5740", 0).show();
                } else if (JDReactHelper.newInstance().isIndependent()) {
                    ReactActivityUtilsHelperBase.startJDReactCommonPage(DebugToolsFragment.this.getActivity(), trim, new Bundle());
                } else {
                    Uri parse = Uri.parse(trim);
                    String queryParameter = parse.getQueryParameter("jdreactkey");
                    String queryParameter2 = parse.getQueryParameter("jdreactapp");
                    if (TextUtils.equals("storage.jd.com", parse.getHost())) {
                        Intent intent = new Intent();
                        intent.setClass(DebugToolsFragment.this.getActivity(), JDReactNativeDebugActivity.class);
                        if (queryParameter != null) {
                            trim = trim.replaceAll("jdreactkey=" + queryParameter, "");
                        }
                        if (queryParameter2 != null) {
                            trim = trim.replaceAll("app_name=" + queryParameter2, "");
                        }
                        intent.putExtra("downloadpath", trim);
                        intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, queryParameter);
                        if (TextUtils.isEmpty(trim)) {
                            return;
                        }
                        File file = new File(JDReactConstant.ReactDownloadPath.getAbsolutePath() + File.separator + queryParameter);
                        if (file.exists()) {
                            ReactNativeUpdate.delete(file);
                        }
                        PluginDownloadInfo pluginDownloadInfo = new PluginDownloadInfo();
                        PluginUpdateInfo pluginUpdateInfo = new PluginUpdateInfo();
                        pluginUpdateInfo.pluginUpdateName = queryParameter;
                        pluginUpdateInfo.pluginUpdateVersion = "0.0";
                        pluginUpdateInfo.pluginDownloadUrl = trim;
                        pluginUpdateInfo.realDownloadUrl = trim;
                        pluginUpdateInfo.isItForceUpdate = DYConstants.DY_TRUE;
                        pluginUpdateInfo.downType = 0;
                        pluginDownloadInfo.setPluginResult(pluginUpdateInfo);
                        ReactNativeUpdateManager.getInstance().addForceDownloadTask(pluginDownloadInfo);
                        intent.putExtra("force_download_mode", true);
                        DebugToolsFragment.this.startActivity(intent);
                        return;
                    }
                    Toast.makeText(DebugToolsFragment.this.getContext(), "\u5730\u5740\u683c\u5f0f\u9519\u8bef", 0).show();
                }
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.DebugToolsFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                checkBox.setChecked(true);
                JDReactHelper.newInstance().changeDebug(true);
                String trim = editText4.getText().toString().trim();
                String trim2 = editText5.getText().toString().trim();
                DebugToolsFragment.this.saveDebugInfo(trim2, trim);
                if (JDReactHelper.newInstance().isIndependent()) {
                    Intent intent = new Intent();
                    intent.setClass(DebugToolsFragment.this.getActivity(), JDReactNativeDebugActivity.class);
                    intent.putExtra("param", trim);
                    intent.putExtra(JDReactConstant.IntentConstant.MODULE_NAME, trim2);
                    intent.putExtra("localDebug", true);
                    DebugToolsFragment.this.startActivity(intent);
                    return;
                }
                ReactActivityUtilsHelperBase.startJDReactActivityForDebug(DebugToolsFragment.this.getActivity(), trim2, trim, null);
            }
        });
        return inflate;
    }
}

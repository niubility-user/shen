package com.jingdong.common.XView2.utils.log.floatview;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.entity.Switch;
import com.jingdong.common.entity.SwitchEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.SwitchQueryFetcherUtil;
import com.jingdong.common.utils.ToastUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XViewFloatView extends FloatBase {
    private EditText edInput;
    private ImageView imgLogo;
    private String localDataVersion;
    private Map<String, String> localMap;
    private ClipboardManager mClipboard;
    private JDDialog mDialog;
    private int mSelectedPosition;
    private ArrayAdapter<CharSequence> mSpinnerAdapter;
    private Spinner mSpinnerType;
    private StringBuffer mStringBuffer;
    private NestedScrollView nsContent;
    private RelativeLayout rlMenu;
    private RelativeLayout rlRoot;
    private String serverDataVersion;
    private Map<String, String> serverMap;
    private List<SwitchEntity> serverSwitchList;
    private TextView tvClearLog;
    private TextView tvContent;
    private TextView tvCopyLog;
    private TextView tvRequest;
    private TextView tvRetract;
    private TextView tvSwitchDecrypt;
    private TextView tvSwitchQuery;

    public XViewFloatView(Context context) {
        super(context);
        this.localMap = new HashMap();
        this.serverMap = new HashMap();
        this.serverSwitchList = new ArrayList();
        setGravity(51);
        setLayoutParam(3);
        this.mAddX = DPIUtil.getWidth(context) - DPIUtil.dip2px(context, 60.0f);
        this.mAddY = DPIUtil.getHeight(context) - DPIUtil.dip2px(context, 80.0f);
        this.mStringBuffer = new StringBuffer();
        this.mClipboard = (ClipboardManager) this.mContext.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
    }

    /* renamed from: a */
    public /* synthetic */ void b(View view) {
        this.mDialog.dismiss();
        show();
    }

    /* renamed from: c */
    public /* synthetic */ void d(View view, View view2) {
        JSONArray popAbleLayersByBuzID;
        String obj = this.edInput.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtils.showToast(this.mContext, "\u8bf7\u8f93\u5165\u6b63\u786e\u7684" + ((Object) this.mSpinnerAdapter.getItem(this.mSelectedPosition)));
            return;
        }
        ((InputMethodManager) this.mContext.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        this.mDialog.dismiss();
        show();
        this.mStringBuffer.setLength(0);
        JSONObject jSONObject = new JSONObject();
        try {
            int i2 = this.mSelectedPosition;
            if (i2 == 0) {
                jSONObject.put(XView2Constants.LAYER_ID, obj);
            } else if (i2 == 1) {
                jSONObject.put(XView2Constants.LOGIC_KEY, obj);
            } else if (i2 == 2) {
                jSONObject.put(XView2Constants.BUZID, obj);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (obj.contains(";")) {
            for (String str : obj.split(";")) {
                startXView2(this.mContext, "showLayer", str, this.mSelectedPosition);
            }
            return;
        }
        if (this.mSelectedPosition == 2 && (popAbleLayersByBuzID = XView2Manager.getInstance().getPopAbleLayersByBuzID(jSONObject, this.mContext)) != null) {
            try {
                ToastUtil.showToast(" obj.optString(\"layerID\")" + ((JSONObject) popAbleLayersByBuzID.get(0)).optString("layerID"));
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        JSONObject layerBasicInfoByLayerId = XView2Manager.getInstance().getLayerBasicInfoByLayerId(obj);
        if (layerBasicInfoByLayerId != null) {
            ToastUtil.showToast("jsonObject size" + layerBasicInfoByLayerId.length());
        }
        if (XView2Manager.getInstance().getXViewCanPopStatus(jSONObject)) {
            startXView2(this.mContext, "showLayer", obj, this.mSelectedPosition);
        }
    }

    /* renamed from: e */
    public /* synthetic */ void f(View view) {
        this.mDialog.show();
        hide();
    }

    /* renamed from: g */
    public /* synthetic */ void h(View view) {
        showBall();
    }

    /* renamed from: i */
    public /* synthetic */ void j(View view) {
        this.mStringBuffer.setLength(0);
        this.tvContent.setText("");
    }

    /* renamed from: k */
    public /* synthetic */ void l(View view) {
        String charSequence = this.tvContent.getText().toString();
        if (StringUtil.isEmpty(charSequence)) {
            return;
        }
        this.mClipboard.setPrimaryClip(ClipData.newPlainText("", charSequence));
        ToastUtil.showToast("\u65e5\u5fd7\u5df2\u590d\u5236");
    }

    public void parseData(JDJSONObject jDJSONObject, Map<String, String> map, boolean z) {
        JDJSONArray optJSONArray;
        SwitchEntity switchEntity;
        Map<String, String> a;
        List<Switch> parseArray;
        if (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray("data")) == null || optJSONArray.size() < 1) {
            return;
        }
        String optString = jDJSONObject.optString(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
        if (z) {
            this.serverDataVersion = optString;
            String str = "serverDataVersion: " + this.serverDataVersion;
        } else {
            this.localDataVersion = optString;
            String str2 = "localDataVersion: " + this.localDataVersion;
        }
        for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
            JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject != null && (switchEntity = (SwitchEntity) JDJSON.parseObject(optJSONObject.toString(), SwitchEntity.class)) != null) {
                if (z) {
                    this.serverSwitchList.add(switchEntity);
                }
                ArrayList<Switch> arrayList = switchEntity.switches;
                if (arrayList != null && arrayList.size() > 0) {
                    sortSwitch(switchEntity.switches, map);
                } else if (TextUtils.equals(switchEntity.type, SwitchQueryFetcher.SWITCH_TYPE_ENCODE) && !TextUtils.isEmpty(switchEntity.data) && (a = com.jd.phc.e.c(JdSdk.getInstance().getApplicationContext()).a(switchEntity.data)) != null && a.containsKey("data")) {
                    String str3 = a.get("data");
                    if (!TextUtils.isEmpty(str3) && (parseArray = JDJSON.parseArray(str3, Switch.class)) != null && !parseArray.isEmpty()) {
                        sortSwitch(parseArray, map);
                    }
                }
            }
        }
    }

    public void showBall() {
        RelativeLayout relativeLayout = this.rlMenu;
        relativeLayout.setVisibility(relativeLayout.getVisibility() == 0 ? 8 : 0);
        NestedScrollView nestedScrollView = this.nsContent;
        nestedScrollView.setVisibility(nestedScrollView.getVisibility() == 0 ? 8 : 0);
        this.rlRoot.setBackground(this.rlMenu.getVisibility() == 0 ? this.mContext.getResources().getDrawable(R.drawable.x_view_bg) : null);
        this.imgLogo.setVisibility(this.rlMenu.getVisibility() == 0 ? 8 : 0);
        TextView textView = this.tvClearLog;
        textView.setVisibility(textView.getVisibility() == 0 ? 8 : 0);
        if (this.rlMenu.getVisibility() == 8) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            layoutParams.x = this.mAddX;
            layoutParams.y = this.mAddY;
            this.mWindowManager.updateViewLayout(this.mContentView, layoutParams);
        }
    }

    private void sortSwitch(List<Switch> list, Map<String, String> map) {
        String versionName = PackageInfoUtil.getVersionName();
        for (Switch r1 : list) {
            if (!TextUtils.isEmpty(r1.name)) {
                String str = r1.appVersionUp;
                String str2 = r1.appVersionDown;
                if ((!TextUtils.isEmpty(str2) && SwitchQueryFetcherUtil.leftLargerOrEqual(str2, versionName)) || (!TextUtils.isEmpty(str) && SwitchQueryFetcherUtil.leftLargerOrEqual(versionName, str))) {
                    OKLog.e("switchQueryTest", "switchObj.name" + r1.name + "\u4e0d\u5728\u7248\u672c\u8303\u56f4\u5185");
                } else {
                    map.put(r1.name, TextUtils.isEmpty(r1.value) ? "" : r1.value);
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void startTest() {
        char c2;
        Map<String, String> a;
        List parseArray;
        String string = CommonBase.getJdSharedPreferences().getString("switch_query", "");
        if (!TextUtils.isEmpty(string)) {
            parseData(JDJSON.parseObject(string), this.localMap, false);
        }
        for (String str : this.serverMap.keySet()) {
            if (TextUtils.equals(this.localMap.get(str), this.serverMap.get(str))) {
                String str2 = "localCacheTest Key Success \uff1a" + str + " \uff1a" + this.serverMap.get(str);
            } else {
                String str3 = "localCacheTest Failed \uff1a" + str + " \uff1a" + this.serverMap.get(str);
            }
        }
        for (int i2 = 0; i2 < this.serverSwitchList.size(); i2++) {
            SwitchEntity switchEntity = this.serverSwitchList.get(i2);
            if (switchEntity != null) {
                ArrayList<Switch> arrayList = switchEntity.switches;
                String str4 = switchEntity.type;
                str4.hashCode();
                switch (str4.hashCode()) {
                    case -7975933:
                        if (str4.equals(SwitchQueryFetcher.SWITCH_TYPE_INT)) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 83358373:
                        if (str4.equals(SwitchQueryFetcher.SWITCH_TYPE_STRING)) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 1827950972:
                        if (str4.equals(SwitchQueryFetcher.SWITCH_TYPE_BOOLEAN)) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        for (int i3 = 0; i3 < arrayList.size(); i3++) {
                            Switch r6 = arrayList.get(i3);
                            if (SwitchQueryFetcher.getSwitchIntValue(r6.name, -1) != Integer.parseInt(r6.value)) {
                                String str5 = "MemoryTest intSwitch Failed \uff1a" + r6.name + " \uff1a" + r6.value;
                            } else {
                                String str6 = "MemoryTest intSwitch Success \uff1a" + r6.name + " \uff1a" + r6.value;
                            }
                        }
                        continue;
                    case 1:
                        for (int i4 = 0; i4 < arrayList.size(); i4++) {
                            Switch r62 = arrayList.get(i4);
                            if (TextUtils.equals(SwitchQueryFetcher.getSwitchStringValue(r62.name, ""), r62.value)) {
                                String str7 = "MemoryTest stringSwitch Success \uff1a" + r62.name + " \uff1a" + r62.value;
                            } else {
                                String str8 = "MemoryTest stringSwitch Failed \uff1a" + r62.name + " \uff1a" + r62.value;
                            }
                        }
                        continue;
                    case 2:
                        for (int i5 = 0; i5 < arrayList.size(); i5++) {
                            Switch r63 = arrayList.get(i5);
                            if (SwitchQueryFetcher.getSwitchBooleanValue(r63.name, false) != TextUtils.equals(r63.value, "yes")) {
                                String str9 = "MemoryTest booleanSwitch Failed \uff1a" + r63.name + " \uff1a" + r63.value;
                            } else {
                                String str10 = "MemoryTest booleanSwitch Success \uff1a" + r63.name + " \uff1a" + r63.value;
                            }
                        }
                        continue;
                    default:
                        if (TextUtils.equals(switchEntity.type, SwitchQueryFetcher.SWITCH_TYPE_ENCODE)) {
                            if (!TextUtils.isEmpty(switchEntity.data) && (a = com.jd.phc.e.c(JdSdk.getInstance().getApplicationContext()).a(switchEntity.data)) != null && a.containsKey("data")) {
                                String str11 = a.get("data");
                                if (!TextUtils.isEmpty(str11) && (parseArray = JDJSON.parseArray(str11, Switch.class)) != null && !parseArray.isEmpty()) {
                                    for (int i6 = 0; i6 < parseArray.size(); i6++) {
                                        Switch r64 = (Switch) parseArray.get(i6);
                                        if (TextUtils.equals(SwitchQueryFetcher.getSwitchStringValue(r64.name, ""), r64.value)) {
                                            String str12 = "MemoryTest stringSwitch Success \uff1a" + r64.name + " \uff1a" + r64.value;
                                        } else {
                                            String str13 = "MemoryTest stringSwitch Failed \uff1a" + r64.name + " \uff1a" + r64.value;
                                        }
                                    }
                                    break;
                                }
                            }
                        } else {
                            continue;
                        }
                        break;
                }
            }
        }
    }

    private Bundle startXView2(Context context, String str, String str2, int i2) {
        Bundle bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("moduleName", XView2Constants.XVIEW2_POP_EVENT_NAME);
            jSONObject.put("methodName", str);
            JSONObject jSONObject2 = new JSONObject();
            if (i2 == 0) {
                jSONObject.put(XView2Constants.LAYER_ID, str2);
                jSONObject2.put(XView2Constants.LAYER_ID, str2);
                jSONObject2.put("name", "xView2Param");
            } else if (i2 == 1) {
                jSONObject.put(XView2Constants.LOGIC_KEY, str2);
                jSONObject2.put(XView2Constants.LOGIC_KEY, str2);
            } else if (i2 == 2) {
                jSONObject.put(XView2Constants.BUZID, str2);
                jSONObject2.put(XView2Constants.BUZID, str2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("xviewtext", "haha");
            jSONObject3.put("xviewimg", "https://m.360buyimg.com/img/jfs/t1/179815/1/28272/39321/631858a3Edd2bbf3e/17629282acf2e225.png");
            jSONObject2.put(XView2Constants.STATE, jSONObject3);
            jSONObject.put("params", jSONObject2);
            bundle.putString("params", jSONObject.toString());
            JumpUtil.execJumpByDes(JumpUtil.XVIEW2_NXVIEW, context, bundle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return bundle;
    }

    public void switchQueryFetch() {
        HttpGroup.CustomOnAllListener customOnAllListener = new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatView.5
            {
                XViewFloatView.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse != null && httpResponse.getFastJsonObject() != null) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    XViewFloatView.this.serverMap.clear();
                    XViewFloatView.this.serverSwitchList.clear();
                    XViewFloatView xViewFloatView = XViewFloatView.this;
                    xViewFloatView.parseData(fastJsonObject, xViewFloatView.serverMap, true);
                }
                XViewFloatView.this.startTest();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("switchQuery");
        httpSetting.putJsonParam("coldOrNot", "1");
        httpSetting.putJsonParam(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, "0");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setListener(customOnAllListener);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    @Override // com.jingdong.common.XView2.utils.log.floatview.FloatBase
    public int getLayoutId() {
        return R.layout.layout_float_xview;
    }

    public void hideView() {
        hide();
        JDDialog jDDialog = this.mDialog;
        if (jDDialog == null || !jDDialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    @Override // com.jingdong.common.XView2.utils.log.floatview.FloatBase
    public void initView() {
        final View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_xview_layer, (ViewGroup) null);
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Context context = this.mContext;
        this.mDialog = jDDialogFactory.createJdDialogWithStyle10(context, context.getString(R.string.xView2_layerId), "", inflate, "\u53d6\u6d88", "\u786e\u8ba4");
        this.edInput = (EditText) inflate.findViewById(R.id.ed_input);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner_type);
        this.mSpinnerType = spinner;
        spinner.setDropDownVerticalOffset(DPIUtil.dip2px(this.mContext, 20.0f));
        ArrayAdapter<CharSequence> createFromResource = ArrayAdapter.createFromResource(this.mContext, R.array.spinner_types, R.layout.layout_spinner_item);
        this.mSpinnerAdapter = createFromResource;
        createFromResource.setDropDownViewResource(17367049);
        this.mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatView.1
            {
                XViewFloatView.this = this;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j2) {
                XViewFloatView.this.mSelectedPosition = i2;
                XViewFloatView.this.edInput.setHint("\u8bf7\u8f93\u5165" + XViewFloatView.this.mSpinnerAdapter.getItem(XViewFloatView.this.mSelectedPosition));
                XViewFloatView.this.edInput.setText("");
                XViewFloatView.this.mDialog.setTitle("\u8bf7\u8f93\u5165" + XViewFloatView.this.mSpinnerAdapter.getItem(XViewFloatView.this.mSelectedPosition));
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.mSpinnerType.setAdapter((SpinnerAdapter) this.mSpinnerAdapter);
        this.mDialog.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.b
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.b(view);
            }
        });
        this.mDialog.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.d
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.d(inflate, view);
            }
        });
        TextView textView = (TextView) this.mContentView.findViewById(R.id.tv_request);
        this.tvRequest = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.c
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.f(view);
            }
        });
        this.rlRoot = (RelativeLayout) this.mContentView.findViewById(R.id.rl_root);
        this.tvRetract = (TextView) this.mContentView.findViewById(R.id.tv_retract);
        this.tvContent = (TextView) this.mContentView.findViewById(R.id.tv_content);
        this.nsContent = (NestedScrollView) this.mContentView.findViewById(R.id.ns_content);
        this.rlMenu = (RelativeLayout) this.mContentView.findViewById(R.id.rl_menu);
        this.tvRetract.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.e
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.h(view);
            }
        });
        TextView textView2 = (TextView) this.mContentView.findViewById(R.id.tv_clear_log);
        this.tvClearLog = textView2;
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.f
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.j(view);
            }
        });
        TextView textView3 = (TextView) this.mContentView.findViewById(R.id.tv_copy_log);
        this.tvCopyLog = textView3;
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.a
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                XViewFloatView.this.l(view);
            }
        });
        this.imgLogo = (ImageView) this.mContentView.findViewById(R.id.img_logo);
        this.mContentView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatView.2
            public boolean isMoved;
            private float mLastX;
            private float mLastY;

            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.mLastX = rawX;
                    this.mLastY = rawY;
                    this.isMoved = false;
                } else if (action != 1) {
                    if (action == 2) {
                        float f2 = rawX - this.mLastX;
                        float f3 = rawY - this.mLastY;
                        if (!this.isMoved && (Math.abs(f2) > 5.0f || Math.abs(f3) > 5.0f)) {
                            this.isMoved = true;
                        }
                        XViewFloatView xViewFloatView = XViewFloatView.this;
                        WindowManager.LayoutParams layoutParams = xViewFloatView.mLayoutParams;
                        layoutParams.x = (int) (layoutParams.x + f2);
                        layoutParams.y = (int) (layoutParams.y + f3);
                        xViewFloatView.mWindowManager.updateViewLayout(xViewFloatView.mContentView, layoutParams);
                        this.mLastX = rawX;
                        this.mLastY = rawY;
                    }
                } else if (!this.isMoved) {
                    XViewFloatView.this.showBall();
                }
                return true;
            }
        });
        TextView textView4 = (TextView) this.mContentView.findViewById(R.id.tv_switch_query);
        this.tvSwitchQuery = textView4;
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatView.3
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XViewFloatView.this.switchQueryFetch();
            }
        });
        TextView textView5 = (TextView) this.mContentView.findViewById(R.id.tv_switch_decrypt);
        this.tvSwitchDecrypt = textView5;
        textView5.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.XView2.utils.log.floatview.XViewFloatView.4
            {
                XViewFloatView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Map<String, String> a;
                List parseArray;
                for (int i2 = 0; i2 < XViewFloatView.this.serverSwitchList.size(); i2++) {
                    SwitchEntity switchEntity = (SwitchEntity) XViewFloatView.this.serverSwitchList.get(i2);
                    if (switchEntity != null && TextUtils.equals(switchEntity.type, SwitchQueryFetcher.SWITCH_TYPE_ENCODE) && !TextUtils.isEmpty(switchEntity.data) && (a = com.jd.phc.e.c(JdSdk.getInstance().getApplicationContext()).a(switchEntity.data)) != null && a.containsKey("data")) {
                        String str = a.get("data");
                        if (!TextUtils.isEmpty(str) && (parseArray = JDJSON.parseArray(str, Switch.class)) != null && !parseArray.isEmpty()) {
                            String str2 = "SwitchDecrypt \uff1a" + JDJSON.toJSONString(parseArray);
                        }
                    }
                }
            }
        });
    }

    public void showLog(String str) {
        this.mStringBuffer.append(str);
        this.mStringBuffer.append("\n\n");
        this.tvContent.setText(this.mStringBuffer.toString());
    }
}

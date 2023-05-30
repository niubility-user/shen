package com.jingdong.app.mall.more;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.app.mall.R;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class HostSelectActivity extends FragmentActivity {

    /* renamed from: g  reason: collision with root package name */
    private SparseIntArray f11265g;

    /* renamed from: h  reason: collision with root package name */
    private RelativeLayout f11266h;

    /* renamed from: i  reason: collision with root package name */
    private CheckBox f11267i;

    /* renamed from: j  reason: collision with root package name */
    private EditText f11268j;

    /* renamed from: k  reason: collision with root package name */
    private ListView f11269k;

    /* renamed from: l  reason: collision with root package name */
    private d f11270l;

    /* renamed from: m  reason: collision with root package name */
    private List<HostConfig.HostModel> f11271m;

    /* renamed from: n  reason: collision with root package name */
    private Map<String, HostConfig.HostModel> f11272n;
    private int o = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Iterator it = HostSelectActivity.this.f11272n.keySet().iterator();
            while (it.hasNext()) {
                HostConfig.HostModel hostModel = (HostConfig.HostModel) HostSelectActivity.this.f11272n.get((String) it.next());
                if (hostModel != null && !"\u767b\u5f55\u670d\u52a1\u5668\u9009\u62e9".equals(hostModel.name) && hostModel.list != null) {
                    if (z) {
                        hostModel.selectIndex = 0;
                    } else {
                        hostModel.selectIndex = 1;
                    }
                }
            }
            SharedPreferencesUtil.getSharedPreferences().edit().putBoolean("host_select_checkbox", z).apply();
            HostSelectActivity.this.f11270l.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HostSelectActivity.this.f11268j.setFocusable(true);
            HostSelectActivity.this.f11268j.setFocusableInTouchMode(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements TextView.OnEditorActionListener {
        c() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            HostSelectActivity.this.D();
            HostSelectActivity.this.f11266h.setFocusableInTouchMode(true);
            HostSelectActivity.this.f11266h.setFocusable(true);
            InputMethodManager inputMethodManager = (InputMethodManager) HostSelectActivity.this.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(textView.getWindowToken(), 0);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends BaseAdapter {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes4.dex */
        public class a implements CompoundButton.OnCheckedChangeListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ HostConfig.HostModel f11276g;

            a(HostConfig.HostModel hostModel) {
                this.f11276g = hostModel;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    this.f11276g.selectIndex = HostSelectActivity.this.f11265g.indexOfValue(compoundButton.getId());
                    if ("\u767b\u5f55\u670d\u52a1\u5668\u9009\u62e9".equals(this.f11276g.name)) {
                        String charSequence = compoundButton.getText().toString();
                        if (TextUtils.equals("\u7ebf\u4e0a", charSequence)) {
                            UserUtil.getWJLoginHelper().setDevelop(0);
                            HostConfig.getInstance().setDevelopType(0);
                        } else if (TextUtils.equals("\u9884\u53d1\u5e03", charSequence)) {
                            UserUtil.getWJLoginHelper().setDevelop(2);
                            HostConfig.getInstance().setDevelopType(1);
                        } else if (TextUtils.equals("\u6d4b\u8bd5", charSequence)) {
                            UserUtil.getWJLoginHelper().setDevelop(1);
                            HostConfig.getInstance().setDevelopType(2);
                        }
                    }
                }
            }
        }

        d() {
        }

        private void a(e eVar, int i2) {
            HostConfig.HostModel hostModel = (HostConfig.HostModel) HostSelectActivity.this.f11271m.get(i2);
            eVar.a.setText(hostModel.name);
            int size = hostModel.list.size();
            eVar.b.removeAllViews();
            int i3 = 0;
            while (i3 < size) {
                RadioButton b = b(i3, hostModel.selectIndex == i3, hostModel);
                b.setOnCheckedChangeListener(new a(hostModel));
                eVar.b.addView(b);
                i3++;
            }
        }

        private RadioButton b(int i2, boolean z, HostConfig.HostModel hostModel) {
            RadioButton radioButton = new RadioButton(HostSelectActivity.this);
            radioButton.setId(HostSelectActivity.this.f11265g.get(i2));
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(-1, -2));
            radioButton.setTextSize(DPIUtil.dip2px(6.0f));
            radioButton.setChecked(z);
            radioButton.setText(hostModel.list.get(i2));
            return radioButton;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return HostSelectActivity.this.f11271m.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return HostSelectActivity.this.f11271m.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            e eVar;
            if (view == null) {
                view = LayoutInflater.from(HostSelectActivity.this).inflate(R.layout.b_, (ViewGroup) null);
                eVar = new e();
                eVar.a = (TextView) view.findViewById(R.id.a5u);
                eVar.b = (RadioGroup) view.findViewById(R.id.a5v);
                view.setTag(eVar);
            } else {
                eVar = (e) view.getTag();
            }
            a(eVar, i2);
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }
    }

    /* loaded from: classes4.dex */
    public static class e {
        public TextView a;
        public RadioGroup b;
    }

    private List<String> B(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(str);
        }
        if (this.o < arrayList.size()) {
            this.o = arrayList.size();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        String obj = this.f11268j.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            return;
        }
        SharedPreferencesUtil.getSharedPreferences().edit().putString("host_mocker_id_name", obj).apply();
    }

    private void E() {
        this.f11265g = new SparseIntArray();
        int i2 = 0;
        if (Build.VERSION.SDK_INT >= 17) {
            while (i2 < this.o) {
                this.f11265g.put(i2, View.generateViewId());
                i2++;
            }
            return;
        }
        while (i2 < this.o) {
            this.f11265g.put(i2, ViewCompat.MEASURED_SIZE_MASK - i2);
            i2++;
        }
    }

    private void F(HostConfig.HostModel hostModel) {
        List<String> list;
        int size;
        if (hostModel == null || (list = hostModel.list) == null || this.o >= (size = list.size())) {
            return;
        }
        this.o = size;
    }

    private void initData() {
        List<String> list;
        this.f11271m = new ArrayList();
        HostConfig.getInstance().restoreSvaedConfigFromSp();
        this.f11272n = HostConfig.getInstance().getHostModelArrayMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, HostConfig.HostModel> entry : this.f11272n.entrySet()) {
            String key = entry.getKey();
            HostConfig.HostModel value = entry.getValue();
            F(value);
            if (value != null && (list = value.list) != null) {
                int size = list.size();
                if (size == 2) {
                    this.f11271m.add(value);
                } else if (size > 2) {
                    linkedHashMap.put(key, value);
                }
            }
        }
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            this.f11271m.add((HostConfig.HostModel) ((Map.Entry) it.next()).getValue());
        }
        HostConfig.HostModel hostModel = new HostConfig.HostModel("\u767b\u5f55\u670d\u52a1\u5668\u9009\u62e9");
        hostModel.list = B("\u7ebf\u4e0a", "\u9884\u53d1\u5e03", "\u6d4b\u8bd5");
        hostModel.selectIndex = HostConfig.getInstance().getDevelopType();
        this.f11271m.add(hostModel);
    }

    private void initView() {
        this.f11267i = (CheckBox) findViewById(R.id.a5s);
        this.f11268j = (EditText) findViewById(R.id.a5r);
        this.f11269k = (ListView) findViewById(R.id.a5t);
        this.f11266h = (RelativeLayout) findViewById(R.id.a5q);
        d dVar = new d();
        this.f11270l = dVar;
        this.f11269k.setAdapter((ListAdapter) dVar);
        this.f11267i.setChecked(SharedPreferencesUtil.getSharedPreferences().getBoolean("host_select_checkbox", true));
        this.f11267i.setOnCheckedChangeListener(new a());
        this.f11266h.setFocusableInTouchMode(true);
        this.f11266h.setFocusable(true);
        String string = SharedPreferencesUtil.getSharedPreferences().getString("host_mocker_id_name", "");
        if (!TextUtils.isEmpty(string)) {
            this.f11268j.setText(string);
        }
        this.f11268j.setOnClickListener(new b());
        this.f11268j.setOnEditorActionListener(new c());
    }

    public boolean C(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int height = view.getHeight() + i3;
        int width = view.getWidth() + i2;
        if (motionEvent.getX() <= i2 || motionEvent.getX() >= width || motionEvent.getY() <= i3 || motionEvent.getY() >= height) {
            view.setFocusable(false);
            view.setFocusableInTouchMode(true);
            return true;
        }
        return false;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        InputMethodManager inputMethodManager;
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (C(currentFocus, motionEvent) && (inputMethodManager = (InputMethodManager) getSystemService("input_method")) != null) {
                inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
            D();
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!Configuration.isBeta()) {
            finish();
        }
        setContentView(R.layout.b8);
        initData();
        E();
        initView();
        Toast.makeText(this, "listView\u662f\u5426\u5f00\u542f\u786c\u4ef6\u52a0\u901fTest" + this.f11269k.isHardwareAccelerated(), 1).show();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            Iterator<Map.Entry<String, HostConfig.HostModel>> it = this.f11272n.entrySet().iterator();
            while (it.hasNext()) {
                HostConfig.HostModel value = it.next().getValue();
                if (value.selectIndex == 0) {
                    value.setUseBetaHost(true);
                } else {
                    value.setUseBetaHost(false);
                }
            }
            HostConfig.getInstance().saveHostConfigToSP();
        }
        return super.onKeyDown(i2, keyEvent);
    }
}

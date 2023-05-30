package com.jd.lib.un.business.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.business.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.StatusBarHintTitle;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.unification.uniconfig.UnNewIconTable;
import com.jingdong.common.widget.button.UnButton;
import com.jingdong.common.widget.button.UnCheckBox;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class UnTestBusinessActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    private StatusBarHintTitle f5853g;

    /* renamed from: h  reason: collision with root package name */
    private EditText f5854h;

    /* renamed from: i  reason: collision with root package name */
    private UnButton f5855i;

    /* renamed from: j  reason: collision with root package name */
    private UnCheckBox f5856j;

    /* renamed from: k  reason: collision with root package name */
    private RecyclerView f5857k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f5858l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f5859m;

    /* renamed from: n  reason: collision with root package name */
    private UnCheckBox f5860n;
    private TextView o;
    private UnCheckBox p;
    private boolean q;

    /* loaded from: classes16.dex */
    class a implements CompoundButton.OnCheckedChangeListener {
        a() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            UnTestBusinessActivity.this.f5859m = z;
        }
    }

    /* loaded from: classes16.dex */
    class b implements CompoundButton.OnCheckedChangeListener {
        b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            UnTestBusinessActivity.this.q = z;
        }
    }

    /* loaded from: classes16.dex */
    class c implements CompoundButton.OnCheckedChangeListener {
        c(UnTestBusinessActivity unTestBusinessActivity) {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                UnIconConfigController.getController().abSwitch = Boolean.FALSE;
                return;
            }
            UnIconConfigController.getController().abSwitch = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d extends RecyclerView.Adapter<e> {
        private String[] a;
        private Context b;

        public d(String[] strArr, Context context) {
            this.a = strArr;
            this.b = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull e eVar, int i2) {
            String str = this.a[i2];
            int i3 = UnTestBusinessActivity.this.q ? 2 : 0;
            if (str.contains(UnNewIconTable.FIELD_IS_VAR)) {
                String[] split = str.split(DYConstants.DY_REGEX_AT);
                if (split != null) {
                    eVar.a.setText(split[0]);
                    if (split.length == 1) {
                        eVar.b.setImageBitmap(UnIconConfigHelper.getTextBitmap(split[0], split[0], UnTestBusinessActivity.this.f5859m, i3));
                        return;
                    } else {
                        eVar.b.setImageBitmap(UnIconConfigHelper.getTextBitmap(split[0], split[1], UnTestBusinessActivity.this.f5859m, i3));
                        return;
                    }
                }
                return;
            }
            eVar.a.setText(str);
            eVar.b.setImageBitmap(UnIconConfigHelper.getBitmap(str, UnTestBusinessActivity.this.f5859m, i3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.a.length;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public e onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return new e(UnTestBusinessActivity.this, LayoutInflater.from(this.b).inflate(R.layout.un_test_icon_item, viewGroup, false));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e extends RecyclerView.ViewHolder {
        public TextView a;
        public ImageView b;

        public e(@NonNull UnTestBusinessActivity unTestBusinessActivity, View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.iconIdTv);
            this.b = (ImageView) view.findViewById(R.id.icon);
        }
    }

    private void initData() {
        String[] split;
        String obj = this.f5854h.getText().toString();
        if (TextUtils.isEmpty(obj) || (split = obj.split(DYConstants.DY_REGEX_COMMA)) == null || split.length == 0) {
            return;
        }
        this.f5857k.setAdapter(new d(split, this));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.showIcon) {
            initData();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"MissingInflatedId"})
    public void onCreate(Bundle bundle) {
        this.statusBarTransparentEnable = true;
        super.onCreate(bundle);
        setContentView(R.layout.un_test_business_activity);
        this.f5853g = (StatusBarHintTitle) findViewById(R.id.title);
        this.f5854h = (EditText) findViewById(R.id.iconId);
        this.f5855i = (UnButton) findViewById(R.id.showIcon);
        this.f5856j = (UnCheckBox) findViewById(R.id.elderCheckbox);
        this.f5857k = (RecyclerView) findViewById(R.id.recycler);
        this.f5858l = (TextView) findViewById(R.id.versionInfo);
        this.f5860n = (UnCheckBox) findViewById(R.id.smallCheckbox);
        this.p = (UnCheckBox) findViewById(R.id.bsCheckbox);
        this.o = (TextView) findViewById(R.id.testSnap);
        this.f5857k.setLayoutManager(new LinearLayoutManager(this));
        this.f5853g.setTitleText("\u7edf\u4e00\u6253\u6807\u6d4b\u8bd5");
        ArrayList arrayList = new ArrayList();
        arrayList.add("tab_156");
        TextView textView = this.o;
        textView.setText(UnIconConfigHelper.getSpanableString(arrayList, "\u6807\u7b7e\u6d4b\u8bd5\u6570\u636e", textView));
        this.f5855i.setOnClickListener(this);
        String currentMode = JDBModeUtils.getCurrentMode();
        if (TextUtils.equals(currentMode, "2")) {
            currentMode = "B";
        } else if (TextUtils.equals(currentMode, "0")) {
            currentMode = "A";
        } else if (TextUtils.equals(currentMode, "1")) {
            currentMode = "\u8001\u5e74";
        }
        this.f5858l.setText("\u5f53\u524d\u7248\u672c\uff1a" + currentMode + "; \u8bd5\u91d1\u77f3id\uff1a" + UnIconConfigController.getController().getExpId() + " \n \u8bd5\u91d1\u77f3\u6570\u636e\uff1a" + UnIconConfigController.getController().getExpEntityFromSp());
        this.f5856j.setOnCheckedChangeListener(new a());
        this.f5860n.setOnCheckedChangeListener(new b());
        if (!UnIconConfigController.getController().abSwitch.booleanValue()) {
            this.p.setChecked(true);
        }
        this.p.setOnCheckedChangeListener(new c(this));
    }
}

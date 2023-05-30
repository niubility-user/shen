package com.jingdong.app.mall.safemode;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class SafeModeFixActivity extends Activity implements View.OnClickListener {

    /* renamed from: g */
    private TextView f11602g;

    /* renamed from: h */
    private TextView f11603h;

    /* renamed from: i */
    private TextView f11604i;

    /* renamed from: j */
    private Button f11605j;

    /* renamed from: k */
    private ImageView f11606k;

    /* renamed from: l */
    private ImageView f11607l;

    /* renamed from: m */
    private String f11608m;

    /* renamed from: n */
    private d f11609n;
    private boolean o = true;

    /* loaded from: classes4.dex */
    class a implements Runnable {
        a() {
            SafeModeFixActivity.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            SafeModeFixActivity.this.f11608m = i.g().e();
            if (!TextUtils.isEmpty(SafeModeFixActivity.this.f11608m)) {
                SafeModeFixActivity safeModeFixActivity = SafeModeFixActivity.this;
                safeModeFixActivity.f11609n = new e(safeModeFixActivity, safeModeFixActivity.f11602g, SafeModeFixActivity.this.f11603h, SafeModeFixActivity.this.f11604i, SafeModeFixActivity.this.f11605j);
                g.a("safeModeStart_1");
                return;
            }
            SafeModeFixActivity safeModeFixActivity2 = SafeModeFixActivity.this;
            safeModeFixActivity2.f11609n = new f(safeModeFixActivity2, safeModeFixActivity2.f11602g, SafeModeFixActivity.this.f11603h, SafeModeFixActivity.this.f11604i, SafeModeFixActivity.this.f11605j, SafeModeFixActivity.this.f11606k, SafeModeFixActivity.this.f11607l);
            g.a("safeModeStart_0");
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        d dVar = this.f11609n;
        if (dVar != null && dVar.f11614f) {
            Toast.makeText(this, getString(R.string.safemode_state_fixing), 0).show();
            return;
        }
        super.onBackPressed();
        h.a();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        d dVar;
        int id = view.getId();
        if (id != R.id.btnFix) {
            if (id == R.id.tvExit && (dVar = this.f11609n) != null) {
                dVar.a();
                return;
            }
            return;
        }
        d dVar2 = this.f11609n;
        if (dVar2 != null) {
            dVar2.b(this.f11608m);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.safemode_fix_activity);
        this.f11602g = (TextView) findViewById(R.id.tvState);
        this.f11603h = (TextView) findViewById(R.id.b5n);
        this.f11604i = (TextView) findViewById(R.id.tvExit);
        this.f11605j = (Button) findViewById(R.id.btnFix);
        this.f11606k = (ImageView) findViewById(R.id.ivExitArrow);
        this.f11607l = (ImageView) findViewById(R.id.ivJoy);
        this.f11605j.setOnClickListener(this);
        this.f11604i.setOnClickListener(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.o) {
            new Thread(new a()).start();
        }
        this.o = false;
    }
}

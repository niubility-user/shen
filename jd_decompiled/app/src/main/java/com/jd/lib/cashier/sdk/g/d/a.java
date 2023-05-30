package com.jd.lib.cashier.sdk.g.d;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.b0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.freindpaydialog.bean.ShareInfo;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.g.g.c;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private final String a = a.class.getSimpleName();
    private ViewGroup b;

    /* renamed from: c  reason: collision with root package name */
    private TextView f3470c;
    private FriendPayDialogActivity d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.g.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class ViewOnClickListenerC0121a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ShareInfo f3471g;

        ViewOnClickListenerC0121a(ShareInfo shareInfo) {
            this.f3471g = shareInfo;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b0.a aVar = new b0.a();
            aVar.a("Wxfriends");
            aVar.b(this.f3471g.imageUrl);
            aVar.e(this.f3471g.shareUrl);
            aVar.d(this.f3471g.title);
            aVar.c(this.f3471g.description);
            Map<String, String> f2 = aVar.f();
            if (a.this.d instanceof FragmentActivity) {
                b0.a(a.this.d, f2);
            }
            com.jd.lib.cashier.sdk.g.e.a.d(a.this.d);
        }
    }

    public a(FriendPayDialogActivity friendPayDialogActivity) {
        this.d = friendPayDialogActivity;
        b();
    }

    private void c() {
        try {
            if (this.b != null) {
                if (JDDarkUtil.isDarkMode()) {
                    this.b.setBackgroundColor(-16777216);
                } else {
                    this.b.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_F9F9F9));
                }
            }
        } catch (Exception e2) {
            r.d(this.a, e2.getMessage());
        }
    }

    private void e(ShareInfo shareInfo) {
        TextView textView = this.f3470c;
        if (textView == null) {
            return;
        }
        if (shareInfo != null && this.d != null) {
            textView.setClickable(true);
            this.f3470c.setOnClickListener(new ViewOnClickListenerC0121a(shareInfo));
            return;
        }
        textView.setClickable(false);
    }

    public void b() {
        this.b = (ViewGroup) this.d.findViewById(R.id.lib_cashier_friend_pay_dialog_share_button_root);
        this.f3470c = (TextView) this.d.findViewById(R.id.lib_cashier_friend_pay_share_button);
    }

    public void d(c cVar) {
        if (cVar != null) {
            e(cVar.a);
            c();
        }
    }
}

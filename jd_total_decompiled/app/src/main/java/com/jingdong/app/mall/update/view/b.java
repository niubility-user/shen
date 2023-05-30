package com.jingdong.app.mall.update.view;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.ui.JDDialog;

/* loaded from: classes4.dex */
public class b extends JDDialog {

    /* renamed from: g  reason: collision with root package name */
    private TextView f11720g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f11721h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f11722i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f11723j;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f11724g;

        a(b bVar, View.OnClickListener onClickListener) {
            this.f11724g = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f11724g;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.update.view.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class ViewOnClickListenerC0380b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f11725g;

        ViewOnClickListenerC0380b(b bVar, View.OnClickListener onClickListener) {
            this.f11725g = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            View.OnClickListener onClickListener = this.f11725g;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public b(Context context) {
        this(context, R.style.f30cp);
    }

    private void a() {
        setContentView(R.layout.dialog_gray_upgrade);
        this.f11720g = (TextView) findViewById(R.id.dialog_gray_upgrade_tv_title);
        this.f11721h = (TextView) findViewById(R.id.dialog_gray_upgrade_tv_content);
        this.f11722i = (TextView) findViewById(R.id.dialog_gray_upgrade_tv_one);
        this.f11723j = (TextView) findViewById(R.id.dialog_gray_upgrade_tv_two);
        this.f11721h.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void b(String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        this.f11720g.setText(str);
        this.f11721h.setText(str2);
        this.f11722i.setText(str3);
        this.f11722i.setOnClickListener(new a(this, onClickListener));
        this.f11723j.setText(str4);
        this.f11723j.setOnClickListener(new ViewOnClickListenerC0380b(this, onClickListener2));
    }

    public b(Context context, int i2) {
        super(context, i2);
        a();
    }
}

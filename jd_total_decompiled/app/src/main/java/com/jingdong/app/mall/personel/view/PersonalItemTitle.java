package com.jingdong.app.mall.personel.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class PersonalItemTitle extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private ImageView f11544g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f11545h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f11546i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f11547j;

    /* renamed from: k  reason: collision with root package name */
    private View f11548k;

    /* renamed from: l  reason: collision with root package name */
    private View f11549l;

    /* renamed from: m  reason: collision with root package name */
    int f11550m;

    /* renamed from: n  reason: collision with root package name */
    int f11551n;
    int o;
    int p;
    boolean q;
    boolean r;
    boolean s;

    public PersonalItemTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.personal_view_item_title, (ViewGroup) this, true);
        if (isInEditMode()) {
            return;
        }
        this.f11544g = (ImageView) findViewById(R.id.personal_item_icon);
        this.f11545h = (TextView) findViewById(R.id.personal_item_title);
        this.f11546i = (TextView) findViewById(R.id.personal_item_more_msg);
        ImageView imageView = (ImageView) findViewById(R.id.personal_item_new_icon);
        this.f11547j = (ImageView) findViewById(R.id.personal_item_arrow);
        this.f11548k = findViewById(R.id.personal_item_top_line);
        this.f11549l = findViewById(R.id.personal_item_bottom_line);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PersonalItemTitle);
        this.f11550m = obtainStyledAttributes.getResourceId(6, -1);
        this.f11551n = obtainStyledAttributes.getResourceId(5, -1);
        this.o = obtainStyledAttributes.getResourceId(2, -1);
        this.q = obtainStyledAttributes.getBoolean(0, true);
        this.p = obtainStyledAttributes.getResourceId(3, -1);
        this.r = obtainStyledAttributes.getBoolean(7, false);
        this.s = obtainStyledAttributes.getBoolean(1, false);
        int i2 = this.f11550m;
        if (i2 != -1) {
            this.f11544g.setImageResource(i2);
        } else {
            this.f11544g.setVisibility(8);
        }
        int i3 = this.f11551n;
        if (i3 != -1) {
            this.f11545h.setText(i3);
        }
        if (this.o != -1) {
            this.f11546i.setVisibility(0);
            this.f11546i.setText(this.o);
        }
        if (this.p != -1) {
            this.f11546i.setTextColor(getResources().getColor(this.p));
        }
        if (this.q) {
            this.f11547j.setVisibility(0);
        } else {
            this.f11547j.setVisibility(4);
        }
        if (this.r) {
            this.f11548k.setVisibility(0);
        } else {
            this.f11548k.setVisibility(8);
        }
        if (this.s) {
            this.f11549l.setVisibility(0);
        } else {
            this.f11549l.setVisibility(8);
        }
        obtainStyledAttributes.recycle();
    }
}

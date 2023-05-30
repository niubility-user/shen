package com.jingdong.app.mall.home.widget;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.widget.JsonViewLayout;
import com.jingdong.common.utils.JDSharedCommandUtils;
import com.jingdong.common.utils.ToastUtil;

/* loaded from: classes4.dex */
public class JsonView extends LinearLayout {
    private TextView commandTV;
    private View contentView;
    private ImageView imageview;
    private TextView keyTV;
    private Context mContext;
    private JsonViewLayout.a mJsonClick;
    private TextView valueTV;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CharSequence f11050g;

        a(CharSequence charSequence) {
            this.f11050g = charSequence;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDSharedCommandUtils.getInstance().saveShareText(JsonView.this.mJsonClick == null ? this.f11050g.toString() : JsonView.this.mJsonClick.a().toString());
            ToastUtil.showToast("\u590d\u5236\u6210\u529f");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnLongClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CharSequence f11052g;

        b(CharSequence charSequence) {
            this.f11052g = charSequence;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            try {
                String charSequence = JsonView.this.mJsonClick == null ? this.f11052g.toString() : JsonView.this.mJsonClick.a().toString();
                Intent intent = new Intent();
                Context context = view.getContext();
                intent.setClassName(context, "com.jingdong.demo.ShowImageActivity");
                intent.putExtra("imgUrl", charSequence);
                context.startActivity(intent);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ CharSequence f11054g;

        c(CharSequence charSequence) {
            this.f11054g = charSequence;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JDSharedCommandUtils.getInstance().saveShareText(JsonView.this.mJsonClick == null ? this.f11054g.toString() : JsonView.this.mJsonClick.a().toString());
            ToastUtil.showToast("\u590d\u5236\u6210\u529f");
        }
    }

    public JsonView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        setOrientation(1);
        LayoutInflater.from(this.mContext).inflate(R.layout.item_view_jsonview_layout, (ViewGroup) this, true);
        this.imageview = (ImageView) findViewById(R.id.icon);
        this.keyTV = (TextView) findViewById(R.id.key);
        this.valueTV = (TextView) findViewById(R.id.value);
        this.commandTV = (TextView) findViewById(R.id.command);
        View findViewById = findViewById(R.id.content);
        this.contentView = findViewById;
        findViewById.setBackgroundColor(0);
        this.imageview.setVisibility(8);
        this.keyTV.setVisibility(8);
        this.valueTV.setVisibility(8);
        setTextSize(JsonViewLayout.s);
    }

    public void addViewNoInvalidate(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null && (layoutParams = generateDefaultLayoutParams()) == null) {
            throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
        }
        addViewInLayout(view, -1, layoutParams);
    }

    public void collapse() {
        this.imageview.setTag(Boolean.TRUE);
        this.imageview.callOnClick();
    }

    public void expand() {
        this.imageview.setTag(Boolean.FALSE);
        this.imageview.callOnClick();
    }

    public void hideIcon() {
        this.imageview.setVisibility(8);
    }

    public void hideValue() {
        this.valueTV.setVisibility(8);
    }

    public void setCommand(CharSequence charSequence) {
        this.commandTV.setText(charSequence);
    }

    public void setIconClickListener(JsonViewLayout.a aVar) {
        this.mJsonClick = aVar;
        this.imageview.setOnClickListener(aVar);
    }

    public void setTextSize(float f2) {
        float f3 = (int) f2;
        JsonViewLayout.s = f3;
        this.keyTV.setTextSize(f3);
        this.valueTV.setTextSize(JsonViewLayout.s);
        this.commandTV.setTextSize(JsonViewLayout.s);
        int d = d.d(24);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.imageview.getLayoutParams();
        layoutParams.height = d;
        layoutParams.width = d;
        layoutParams.rightMargin = d.d(6);
        layoutParams.gravity = 16;
        this.imageview.setLayoutParams(layoutParams);
    }

    public void showArrayLength(int i2) {
        this.valueTV.setBackgroundColor(i2);
    }

    public void showIcon(boolean z) {
        this.imageview.setVisibility(0);
        this.imageview.setImageResource(z ? R.drawable.jsonview_item_expand : R.drawable.jsonview_item_collapse);
    }

    public void showKey(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.keyTV.setOnClickListener(new c(charSequence));
        }
        this.keyTV.setVisibility(0);
        this.keyTV.setText(charSequence);
    }

    public void showValue(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.valueTV.setOnClickListener(new a(charSequence));
            this.valueTV.setOnLongClickListener(new b(charSequence));
        }
        this.valueTV.setVisibility(0);
        this.valueTV.setText(charSequence);
        this.valueTV.setBackgroundColor(0);
    }

    public JsonView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    public JsonView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        initView();
    }
}

package com.jingdong.manto.ui.auth;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.c;
import com.jingdong.manto.jsapi.auth.tools.AuthInfo;
import com.jingdong.manto.k.a;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.ui.auth.MantoAuthDialog;
import com.jingdong.manto.ui.base.AuthorizeItemListView;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.LinkedList;

/* loaded from: classes16.dex */
public class MantoUserInfoAuthDialog extends MantoAuthDialog implements View.OnClickListener, a.b {
    private String appName;
    private AuthorizeItemListView authorizeItemListView;
    private Button btAccept;
    private Button btReject;
    private MantoAuthDialog.Callback callback;
    private View contentView;
    private Context context;
    private ImageView ivAppIcon;
    private LinkedList<AuthInfo> linkedList;
    private String logo;
    private TextView tvAppName;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public class Adapter extends BaseAdapter {
        private LayoutInflater mInflater;

        /* loaded from: classes16.dex */
        class ViewHolder {
            TextView textView;

            ViewHolder() {
            }
        }

        public Adapter() {
            this.mInflater = LayoutInflater.from(MantoUserInfoAuthDialog.this.context);
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MantoUserInfoAuthDialog.this.linkedList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return MantoUserInfoAuthDialog.this.linkedList.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            TextView textView;
            Resources resources;
            int i3;
            if (view == null) {
                view = this.mInflater.inflate(R.layout.manto_authorize_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) view.findViewById(R.id.tv_scope);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            AuthInfo authInfo = (AuthInfo) MantoUserInfoAuthDialog.this.linkedList.get(i2);
            if (TextUtils.equals("scope.1", authInfo.scope)) {
                String format = String.format("\u83b7\u53d6\u60a8\u7684\u624b\u673a\u53f7\u7801\n%s   \u4eac\u4e1c\u7ed1\u5b9a\u624b\u673a\u53f7\u7801", authInfo.description);
                SpannableString spannableString = new SpannableString(format);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#909399")), format.length() - 8, format.length(), 33);
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), format.length() - 8, format.length(), 33);
                viewHolder.textView.setText(spannableString);
            } else {
                viewHolder.textView.setText(authInfo.description);
            }
            if (a.b().a() == 0) {
                textView = viewHolder.textView;
                resources = c.a().getResources();
                i3 = R.color.manto_auth_item_day;
            } else {
                textView = viewHolder.textView;
                resources = c.a().getResources();
                i3 = R.color.manto_dark_text_weight;
            }
            textView.setTextColor(resources.getColor(i3));
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int i2) {
            return false;
        }
    }

    public MantoUserInfoAuthDialog(@NonNull Context context, LinkedList<AuthInfo> linkedList, String str, String str2, @NonNull MantoAuthDialog.Callback callback) {
        super(context);
        this.context = context;
        this.linkedList = linkedList;
        this.appName = str;
        this.logo = str2;
        this.callback = callback;
    }

    private void setDarkMode(int i2) {
        Button button;
        Resources resources;
        int i3;
        if (i2 == 0) {
            int color = getContext().getResources().getColor(R.color.manto_day_text_weight);
            this.contentView.setBackgroundResource(R.drawable.manto_dialog_auth_background);
            this.tvAppName.setTextColor(color);
            this.btReject.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_reject_btn));
            this.btReject.setTextColor(getContext().getResources().getColor(R.color.manto_auth_reject_btn_text_day));
            this.btAccept.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_ok_btn));
            button = this.btAccept;
            resources = getContext().getResources();
            i3 = R.color.manto_auth_ok_btn_text_day;
        } else {
            int color2 = getContext().getResources().getColor(R.color.manto_dark_text_weight);
            this.contentView.setBackgroundResource(R.drawable.manto_dialog_auth_background_dark);
            this.tvAppName.setTextColor(color2);
            this.btReject.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_reject_btn_dark));
            this.btReject.setTextColor(getContext().getResources().getColor(R.color.manto_auth_reject_btn_text_dark));
            this.btAccept.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.manto_dialog_auth_jd_style_ok_btn_dark));
            button = this.btAccept;
            resources = getContext().getResources();
            i3 = R.color.manto_auth_ok_btn_text_dark;
        }
        button.setTextColor(resources.getColor(i3));
    }

    @Override // com.jingdong.manto.ui.auth.MantoAuthDialog, android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        MantoAuthDialog.Callback callback = this.callback;
        if (callback != null) {
            callback.onCancel();
        }
        a.b().b(this);
    }

    void initView() {
        IImageLoader iImageLoader;
        ImageView imageView = (ImageView) this.contentView.findViewById(R.id.iv_app_icon);
        this.ivAppIcon = imageView;
        if (imageView != null && !MantoStringUtils.isEmpty(this.logo) && (iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class)) != null) {
            iImageLoader.loadImage(this.ivAppIcon, this.logo);
        }
        TextView textView = (TextView) this.contentView.findViewById(R.id.tv_app_name);
        this.tvAppName = textView;
        textView.setText(this.appName);
        AuthorizeItemListView authorizeItemListView = (AuthorizeItemListView) this.contentView.findViewById(R.id.lv_request_permission);
        this.authorizeItemListView = authorizeItemListView;
        authorizeItemListView.setAdapter((ListAdapter) new Adapter());
        if (this.linkedList.size() > 5) {
            this.authorizeItemListView.a = this.linkedList.size();
            ViewGroup.LayoutParams layoutParams = this.authorizeItemListView.getLayoutParams();
            layoutParams.height = MantoDensityUtils.dip2pixel(getContext(), 100);
            this.authorizeItemListView.setLayoutParams(layoutParams);
        }
        Button button = (Button) this.contentView.findViewById(R.id.bt_reject);
        this.btReject = button;
        button.setOnClickListener(this);
        Button button2 = (Button) this.contentView.findViewById(R.id.bt_accept);
        this.btAccept = button2;
        button2.setOnClickListener(this);
        a.b().a(this);
    }

    @Override // com.jingdong.manto.ui.auth.MantoAuthDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_accept) {
            this.callback.onAccept();
        } else if (id == R.id.bt_reject) {
            this.callback.onReject();
        } else {
            this.callback.onCancel();
        }
        a.b().b(this);
        dismiss();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        this.contentView = View.inflate(getContext(), R.layout.manto_auth_user_info_layout, null);
        if (window != null) {
            window.requestFeature(1);
        }
        setContentView(this.contentView);
        if (window != null) {
            window.setLayout(-1, -2);
            window.setGravity(80);
            window.setBackgroundDrawableResource(17170445);
        }
        setCanceledOnTouchOutside(false);
        initView();
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        setDarkMode(i2);
    }
}

package com.jingdong.common.messagepop;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.messagepop.JDMessageNoticeGuideDialog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes5.dex */
public class JDMessageNoticeGuideDialog {
    private AlertDialog alertDialog;
    private BackForegroundWatcher.BackForegroundListener mBackForegroundListener = null;
    protected boolean hasBtnClicked = false;
    private String pageName = "";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static abstract class BaseStyleView {
        private BaseStyleView() {
        }

        protected abstract void bindData(JDMessageNoticeModel jDMessageNoticeModel);

        protected abstract View init(Context context);
    }

    /* loaded from: classes5.dex */
    public class StyleView0 extends BaseStyleView {
        private ImageView btnClose;
        private Button btnOpen;
        private SimpleDraweeView imgPushGuide;
        private View rootView;
        private TextView tvContent;
        private TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private StyleView0() {
            super();
            JDMessageNoticeGuideDialog.this = r1;
        }

        /* renamed from: a */
        public /* synthetic */ void b(JDMessageNoticeModel jDMessageNoticeModel, View view) {
            JDMessageNoticeGuideDialog.this.clickCloseBtn(jDMessageNoticeModel);
        }

        /* renamed from: c */
        public /* synthetic */ void d(JDMessageNoticeModel jDMessageNoticeModel, View view) {
            JDMessageNoticeGuideDialog.this.clickOpenBtn(jDMessageNoticeModel);
        }

        @Override // com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.BaseStyleView
        protected void bindData(final JDMessageNoticeModel jDMessageNoticeModel) {
            this.tvTitle.setText(jDMessageNoticeModel.pushTitle);
            this.tvContent.setText(jDMessageNoticeModel.pushSubTitle);
            if (TextUtils.isEmpty(jDMessageNoticeModel.pushImage)) {
                this.imgPushGuide.setImageResource(R.drawable.message_push_guide_dialog_bg);
            } else {
                JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                jDDisplayImageOptions.setPlaceholder(R.drawable.message_push_guide_dialog_bg);
                JDImageUtils.displayImage(jDMessageNoticeModel.pushImage, this.imgPushGuide, jDDisplayImageOptions);
            }
            this.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.b
                {
                    JDMessageNoticeGuideDialog.StyleView0.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDMessageNoticeGuideDialog.StyleView0.this.b(jDMessageNoticeModel, view);
                }
            });
            this.btnOpen.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.a
                {
                    JDMessageNoticeGuideDialog.StyleView0.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDMessageNoticeGuideDialog.StyleView0.this.d(jDMessageNoticeModel, view);
                }
            });
        }

        @Override // com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.BaseStyleView
        protected View init(Context context) {
            View inflate = View.inflate(context, R.layout.message_push_guide_dialog, null);
            this.rootView = inflate;
            this.tvTitle = (TextView) inflate.findViewById(R.id.tv_title);
            this.tvContent = (TextView) this.rootView.findViewById(R.id.tv_content);
            this.imgPushGuide = (SimpleDraweeView) this.rootView.findViewById(R.id.img_push_guide);
            this.btnOpen = (Button) this.rootView.findViewById(R.id.btn_open);
            this.btnClose = (ImageView) this.rootView.findViewById(R.id.btn_close);
            return this.rootView;
        }
    }

    /* loaded from: classes5.dex */
    public class StyleView1 extends BaseStyleView {
        private View bottomBtn;
        private TextView bottomTTv;
        private TextView bottombTv;
        private ImageView btnClose;
        private Button topBtn;
        private SimpleDraweeView topIv;
        private TextView tvSubTitle;
        private TextView tvTitle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private StyleView1() {
            super();
            JDMessageNoticeGuideDialog.this = r1;
        }

        /* renamed from: a */
        public /* synthetic */ void b(JDMessageNoticeModel jDMessageNoticeModel, View view) {
            JDMessageNoticeGuideDialog.this.clickCloseBtn(jDMessageNoticeModel);
        }

        /* renamed from: c */
        public /* synthetic */ void d(JDMessageNoticeModel jDMessageNoticeModel, View view) {
            jDMessageNoticeModel.chooseIndex = 1;
            JDMessageNoticeGuideDialog.this.clickOpenBtn(jDMessageNoticeModel);
        }

        /* renamed from: e */
        public /* synthetic */ void f(JDMessageNoticeModel jDMessageNoticeModel, View view) {
            jDMessageNoticeModel.chooseIndex = 3;
            JDMessageNoticeGuideDialog.this.clickOpenBtn(jDMessageNoticeModel);
        }

        @Override // com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.BaseStyleView
        protected void bindData(final JDMessageNoticeModel jDMessageNoticeModel) {
            this.tvTitle.setText(jDMessageNoticeModel.pushTitle);
            this.tvSubTitle.setText(jDMessageNoticeModel.pushSubTitle);
            if (TextUtils.isEmpty(jDMessageNoticeModel.pushImage)) {
                this.topIv.setImageResource(R.drawable.message_push_guide_dialog_bg);
            } else {
                JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                jDDisplayImageOptions.setPlaceholder(R.drawable.message_push_guide_dialog_bg);
                JDImageUtils.displayImage(jDMessageNoticeModel.pushImage, this.topIv, jDDisplayImageOptions);
            }
            if (jDMessageNoticeModel.defaultPushBtn == 1) {
                this.topBtn.setBackgroundResource(R.drawable.message_push_guide_open_btn);
                this.topBtn.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                this.bottomBtn.setBackgroundResource(R.drawable.message_notice_no_check_btn);
                this.bottomTTv.setTextColor(Color.parseColor("#1a1a1a"));
                this.bottombTv.setTextColor(Color.parseColor(JDDarkUtil.COLOR_808080));
            } else {
                this.topBtn.setBackgroundResource(R.drawable.message_notice_no_check_btn);
                this.topBtn.setTextColor(Color.parseColor("#1a1a1a"));
                this.bottomBtn.setBackgroundResource(R.drawable.message_push_guide_open_btn);
                this.bottomTTv.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                this.bottombTv.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            this.btnClose.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.e
                {
                    JDMessageNoticeGuideDialog.StyleView1.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDMessageNoticeGuideDialog.StyleView1.this.b(jDMessageNoticeModel, view);
                }
            });
            this.topBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.c
                {
                    JDMessageNoticeGuideDialog.StyleView1.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDMessageNoticeGuideDialog.StyleView1.this.d(jDMessageNoticeModel, view);
                }
            });
            this.bottomBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.messagepop.d
                {
                    JDMessageNoticeGuideDialog.StyleView1.this = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JDMessageNoticeGuideDialog.StyleView1.this.f(jDMessageNoticeModel, view);
                }
            });
        }

        @Override // com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.BaseStyleView
        protected View init(Context context) {
            View inflate = View.inflate(context, R.layout.message_push_guide_dialog_1, null);
            this.tvTitle = (TextView) inflate.findViewById(R.id.tv_title);
            this.tvSubTitle = (TextView) inflate.findViewById(R.id.tv_sub_title);
            this.topIv = (SimpleDraweeView) inflate.findViewById(R.id.topIv);
            this.topBtn = (Button) inflate.findViewById(R.id.topBtn);
            this.bottomBtn = inflate.findViewById(R.id.bottomBtn);
            this.bottomTTv = (TextView) inflate.findViewById(R.id.bottomTTv);
            this.bottombTv = (TextView) inflate.findViewById(R.id.bottombTv);
            this.btnClose = (ImageView) inflate.findViewById(R.id.btn_close);
            return inflate;
        }
    }

    private void addWatcherListener(final JDMessageNoticeModel jDMessageNoticeModel) {
        if (this.mBackForegroundListener == null) {
            this.mBackForegroundListener = new BackForegroundWatcher.BackForegroundListener() { // from class: com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.1
                {
                    JDMessageNoticeGuideDialog.this = this;
                }

                @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
                public void onBackToForeground() {
                    JDMessageNoticeGuideDialog jDMessageNoticeGuideDialog = JDMessageNoticeGuideDialog.this;
                    if (jDMessageNoticeGuideDialog.hasBtnClicked) {
                        JDJSONObject parseObject = JDJSON.parseObject(jDMessageNoticeGuideDialog.getMatJsonParam(jDMessageNoticeModel));
                        parseObject.put("result", (Object) (JDMessageNoticeManager.getNotificationStatus() ? "1" : "0"));
                        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication(), "MessageAuthority_PushOpenResultAuto", "", "MessageCenter_Authority", JDMessageNoticeGuideDialog.this.pageName, "", parseObject.toJSONString(), null);
                        JDMessageNoticeGuideDialog.this.hasBtnClicked = false;
                        BackForegroundWatcher.getInstance().unRegisterListener(JDMessageNoticeGuideDialog.this.mBackForegroundListener);
                        JDMessageNoticeGuideDialog.this.mBackForegroundListener = null;
                    }
                }

                @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
                public void onForeToBackground() {
                }
            };
            BackForegroundWatcher.getInstance().registerListener(this.mBackForegroundListener);
        }
    }

    public void clickCloseBtn(JDMessageNoticeModel jDMessageNoticeModel) {
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), "MessageAuthority_PushOpenPopupX", "", "", "MessageCenter_Authority", this.pageName, "", "", getMatJsonParam(jDMessageNoticeModel), null);
        this.alertDialog.dismiss();
        this.alertDialog = null;
    }

    public void clickOpenBtn(JDMessageNoticeModel jDMessageNoticeModel) {
        JDMessageNoticeManager jDMessageNoticeManager = JDMessageNoticeManager.getInstance();
        if (jDMessageNoticeManager.getListener() != null) {
            jDMessageNoticeManager.getListener().pushOpenClicked();
        }
        if (this.alertDialog == null) {
            return;
        }
        requestPushGuideBtn(jDMessageNoticeModel);
        addWatcherListener(jDMessageNoticeModel);
        this.hasBtnClicked = true;
        this.alertDialog.dismiss();
        jDMessageNoticeManager.openNoticeSettings(this.alertDialog.getContext());
        this.alertDialog = null;
    }

    public String getMatJsonParam(JDMessageNoticeModel jDMessageNoticeModel) {
        String str = jDMessageNoticeModel.pageId;
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("businessid", (Object) (TextUtils.isEmpty(jDMessageNoticeModel.bizId) ? "" : jDMessageNoticeModel.bizId));
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        jDJSONObject.put("pageid", (Object) str);
        jDJSONObject.put("styleid", (Object) ("" + jDMessageNoticeModel.pushContentId));
        jDJSONObject.put("pushstyleid", (Object) ("" + jDMessageNoticeModel.pushStyleId));
        jDJSONObject.put("defaultbtn", (Object) ("" + jDMessageNoticeModel.chooseIndex));
        return jDJSONObject.toJSONString();
    }

    private void notifyNoticeCallback(JDMessageNoticeCallback jDMessageNoticeCallback, boolean z) {
        if (jDMessageNoticeCallback != null) {
            jDMessageNoticeCallback.onJDMessageNotice(z);
        }
    }

    private void requestPushGuideBtn(final JDMessageNoticeModel jDMessageNoticeModel) {
        HttpSetting setting = MessageHttpUtils.getSetting();
        setting.setFunctionId("undisturbed");
        setting.putJsonParam("pushGuideBtn", jDMessageNoticeModel.chooseIndex + "");
        setting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.3
            {
                JDMessageNoticeGuideDialog.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getCode() != 0) {
                    return;
                }
                JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication(), "MessageAuthority_PushOpenPopupOpen", "", "", "MessageCenter_Authority", JDMessageNoticeGuideDialog.this.pageName, "", "", JDMessageNoticeGuideDialog.this.getMatJsonParam(jDMessageNoticeModel), null);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(setting);
    }

    private void requestPushGuideCallback(final JDMessageNoticeModel jDMessageNoticeModel) {
        HttpSetting setting = MessageHttpUtils.getSetting();
        setting.setFunctionId("pushGuideCallback");
        setting.putJsonParam("bizId", jDMessageNoticeModel.bizId);
        setting.putJsonParam("pageId", jDMessageNoticeModel.pageId);
        setting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.2
            {
                JDMessageNoticeGuideDialog.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse.getCode() != 0) {
                    return;
                }
                JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication(), "MessageAuthority_PushOpenPopupExpo", "", "MessageCenter_Authority", JDMessageNoticeGuideDialog.this.pageName, "", JDMessageNoticeGuideDialog.this.getMatJsonParam(jDMessageNoticeModel), null);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(setting);
    }

    public boolean isShown() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null) {
            return false;
        }
        return alertDialog.isShowing();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0071  */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$1] */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$BaseStyleView] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void show(android.app.Activity r4, java.lang.String r5, com.jingdong.common.messagepop.JDMessageNoticeModel r6, com.jingdong.common.messagepop.JDMessageNoticeCallback r7) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L75
            boolean r1 = r4.isFinishing()
            if (r1 != 0) goto L75
            boolean r1 = r3.isShown()
            if (r1 != 0) goto L75
            if (r6 != 0) goto L12
            goto L75
        L12:
            r3.pageName = r5
            android.app.AlertDialog$Builder r5 = new android.app.AlertDialog$Builder
            r5.<init>(r4)
            android.app.AlertDialog r4 = r5.create()
            r3.alertDialog = r4
            android.view.Window r4 = r4.getWindow()
            if (r4 != 0) goto L29
            r3.notifyNoticeCallback(r7, r0)
            return
        L29:
            int r5 = r6.pushStyleId
            r1 = 1
            r2 = 0
            if (r5 == 0) goto L38
            if (r5 == r1) goto L32
            goto L3e
        L32:
            com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$StyleView1 r5 = new com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$StyleView1
            r5.<init>()
            goto L3d
        L38:
            com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$StyleView0 r5 = new com.jingdong.common.messagepop.JDMessageNoticeGuideDialog$StyleView0
            r5.<init>()
        L3d:
            r2 = r5
        L3e:
            if (r2 == 0) goto L71
            android.app.AlertDialog r5 = r3.alertDialog
            r5.show()
            android.app.AlertDialog r5 = r3.alertDialog
            r5.setCancelable(r0)
            android.app.AlertDialog r5 = r3.alertDialog
            r5.setCanceledOnTouchOutside(r0)
            android.content.Context r5 = r4.getContext()
            android.view.View r5 = r2.init(r5)
            r4.setContentView(r5)
            r2.bindData(r6)
            java.lang.String r4 = r6.bizId
            java.lang.String r5 = "10000"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L6a
            com.jingdong.common.utils.MSGWithDDUtil.homePushGuideDialogShow()
        L6a:
            r3.requestPushGuideCallback(r6)
            r3.notifyNoticeCallback(r7, r1)
            return
        L71:
            r3.notifyNoticeCallback(r7, r0)
            return
        L75:
            r3.notifyNoticeCallback(r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.messagepop.JDMessageNoticeGuideDialog.show(android.app.Activity, java.lang.String, com.jingdong.common.messagepop.JDMessageNoticeModel, com.jingdong.common.messagepop.JDMessageNoticeCallback):void");
    }
}

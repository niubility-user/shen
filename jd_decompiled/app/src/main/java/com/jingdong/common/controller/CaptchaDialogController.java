package com.jingdong.common.controller;

import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.DialogController;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import java.util.Random;

/* loaded from: classes5.dex */
public abstract class CaptchaDialogController extends DialogController {
    private ImageView captchaImage;
    private EditText captchaInput;
    private String captchaKey;
    private final String captchaUrl;
    private IMyActivity currentMyActivity = BaseFrameUtil.getInstance().getCurrentMyActivity();
    private ExceptionDrawable loadingDrawable = new ExceptionDrawable(JdSdk.getInstance().getApplication(), getString(R.string.settlement_captcha_dialog_loading));
    private ExceptionDrawable noDrawable = new ExceptionDrawable(JdSdk.getInstance().getApplication(), getString(R.string.settlement_captcha_dialog_loaded_fail));
    private Button switchButton;
    private View view;

    public CaptchaDialogController(String str, String str2) {
        this.captchaUrl = str;
        findView();
        bindImage();
        setTitle(getString(R.string.settlement_captcha_dialog_nead_code));
        setMessage(TextUtils.isEmpty(str2) ? getString(R.string.settlement_captcha_dialog_input_pic_code) : str2);
        setPositiveButton(getString(R.string.settlement_captcha_dialog_submit));
        setNeutralButton(getString(R.string.settlement_captcha_dialog_cancel));
        setNegativeButton(getString(R.string.settlement_captcha_dialog_change));
        setCanBack(true);
        init(this.currentMyActivity.getThisActivity());
        this.currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.CaptchaDialogController.1
            @Override // java.lang.Runnable
            public void run() {
                CaptchaDialogController.this.show();
                CaptchaDialogController captchaDialogController = CaptchaDialogController.this;
                captchaDialogController.switchButton = captchaDialogController.getButton(-2);
                if (CaptchaDialogController.this.switchButton != null) {
                    CaptchaDialogController.this.switchButton.setClickable(false);
                }
            }
        });
    }

    private void bindImage() {
        this.currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.CaptchaDialogController.5
            @Override // java.lang.Runnable
            public void run() {
                CaptchaDialogController.this.captchaImage.setImageDrawable(CaptchaDialogController.this.loadingDrawable);
            }
        });
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setType(5000);
        httpSetting.setPriority(5000);
        httpSetting.setUrl(this.captchaUrl);
        new ExceptionReporter(httpSetting);
        httpSetting.setListener(new HttpGroup.OnCommonListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0026: INVOKE 
              (r0v1 'httpSetting' com.jingdong.jdsdk.network.toolbox.HttpSetting)
              (wrap: com.jingdong.jdsdk.network.toolbox.HttpGroup$OnCommonListener : 0x0023: CONSTRUCTOR 
              (r3v0 'this' com.jingdong.common.controller.CaptchaDialogController A[IMMUTABLE_TYPE, THIS])
              (r1 I:com.jingdong.jdsdk.network.toolbox.ExceptionReporter A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.controller.CaptchaDialogController, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void (m), WRAPPED] (LINE:7) call: com.jingdong.common.controller.CaptchaDialogController.6.<init>(com.jingdong.common.controller.CaptchaDialogController, com.jingdong.jdsdk.network.toolbox.ExceptionReporter):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdsdk.network.toolbox.HttpSetting.setListener(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void A[MD:(com.jingdong.jdsdk.network.toolbox.HttpGroup$HttpTaskListener):void (m)] (LINE:7) in method: com.jingdong.common.controller.CaptchaDialogController.bindImage():void, file: classes5.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            com.jingdong.common.frame.IMyActivity r0 = r3.currentMyActivity
            com.jingdong.common.controller.CaptchaDialogController$5 r1 = new com.jingdong.common.controller.CaptchaDialogController$5
            r1.<init>()
            r0.post(r1)
            com.jingdong.jdsdk.network.toolbox.HttpSetting r0 = new com.jingdong.jdsdk.network.toolbox.HttpSetting
            r0.<init>()
            r1 = 5000(0x1388, float:7.006E-42)
            r0.setType(r1)
            r0.setPriority(r1)
            java.lang.String r1 = r3.captchaUrl
            r0.setUrl(r1)
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter r1 = new com.jingdong.jdsdk.network.toolbox.ExceptionReporter
            r1.<init>(r0)
            com.jingdong.common.controller.CaptchaDialogController$6 r2 = new com.jingdong.common.controller.CaptchaDialogController$6
            r2.<init>()
            r0.setListener(r2)
            com.jingdong.common.frame.IMyActivity r1 = r3.currentMyActivity
            com.jingdong.jdsdk.network.toolbox.HttpGroup r1 = r1.getHttpGroupaAsynPool()
            r1.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.controller.CaptchaDialogController.bindImage():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String randomText(int i2) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(cArr[random.nextInt(62)]);
        }
        return sb.toString();
    }

    public void findView() {
        this.currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.CaptchaDialogController.2
            @Override // java.lang.Runnable
            public void run() {
                RelativeLayout relativeLayout = new RelativeLayout(CaptchaDialogController.this.currentMyActivity.getThisActivity());
                CaptchaDialogController.this.setView(relativeLayout);
                CaptchaDialogController.this.view = ImageUtil.inflate(R.layout.captcha, relativeLayout);
                CaptchaDialogController captchaDialogController = CaptchaDialogController.this;
                captchaDialogController.captchaImage = (ImageView) captchaDialogController.view.findViewById(R.id.captcha_image);
                CaptchaDialogController captchaDialogController2 = CaptchaDialogController.this;
                captchaDialogController2.captchaInput = (EditText) captchaDialogController2.view.findViewById(R.id.captcha_input);
            }
        });
    }

    public String getString(int i2) {
        return JdSdk.getInstance().getApplication().getResources().getString(i2);
    }

    @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        if (i2 == -2) {
            Button button = this.switchButton;
            if (button != null) {
                button.setClickable(false);
            }
            bindImage();
            this.currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.CaptchaDialogController.4
                @Override // java.lang.Runnable
                public void run() {
                    if (((DialogController) CaptchaDialogController.this).alertDialog.isShowing()) {
                        return;
                    }
                    ((DialogController) CaptchaDialogController.this).alertDialog.show();
                }
            });
        } else if (i2 != -1) {
        } else {
            String obj = this.captchaInput.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                this.currentMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.CaptchaDialogController.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(JdSdk.getInstance().getApplication(), CaptchaDialogController.this.getString(R.string.settlement_captcha_dialog_can_not_null), 1).show();
                        if (((DialogController) CaptchaDialogController.this).alertDialog.isShowing()) {
                            return;
                        }
                        ((DialogController) CaptchaDialogController.this).alertDialog.show();
                    }
                });
            } else {
                submit(obj, this.captchaKey);
            }
        }
    }

    protected void submit(String str, String str2) {
    }
}

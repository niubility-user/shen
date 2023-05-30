package com.jingdong.common.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;

/* loaded from: classes6.dex */
public class JshopNoPayPinActivity extends MyActivity {
    private static final String TAG = "JshopNoPayPinActivity";
    private Button submit;
    private TextView title;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.jshop_no_pay_pin_activity);
        TextView textView = (TextView) findViewById(R.id.jshop_title_title_text);
        this.title = textView;
        textView.setText("\u9886\u53d6\u4f18\u60e0\u5238");
        setTitleBack((ImageView) findViewById(R.id.jshop_title_left_img));
        getIntent().getStringExtra("url");
        getIntent().getStringExtra("action");
        Button button = (Button) findViewById(R.id.jshop_no_pay_pin_btn);
        this.submit = button;
        button.setOnClickListener(new View.OnClickListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004d: INVOKE 
              (r1v3 'button' android.widget.Button)
              (wrap: android.view.View$OnClickListener : 0x004a: CONSTRUCTOR 
              (r3v0 'this' com.jingdong.common.sample.JshopNoPayPinActivity A[IMMUTABLE_TYPE, THIS])
              (r4 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.sample.JshopNoPayPinActivity, java.lang.String, java.lang.String):void (m), WRAPPED] (LINE:10) call: com.jingdong.common.sample.JshopNoPayPinActivity.1.<init>(com.jingdong.common.sample.JshopNoPayPinActivity, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: android.widget.Button.setOnClickListener(android.view.View$OnClickListener):void A[MD:(android.view.View$OnClickListener):void (s)] (LINE:10) in method: com.jingdong.common.sample.JshopNoPayPinActivity.onCreate(android.os.Bundle):void, file: classes6.dex
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
            r0 = 1
            r3.requestWindowFeature(r0)
            super.onCreate(r4)
            r4 = 2130968972(0x7f04018c, float:1.7546613E38)
            r3.setContentView(r4)
            r4 = 2131694017(0x7f0f11c1, float:1.9017179E38)
            android.view.View r4 = r3.findViewById(r4)
            android.widget.TextView r4 = (android.widget.TextView) r4
            r3.title = r4
            java.lang.String r0 = "\u9886\u53d6\u4f18\u60e0\u5238"
            r4.setText(r0)
            r4 = 2131694009(0x7f0f11b9, float:1.9017162E38)
            android.view.View r4 = r3.findViewById(r4)
            android.widget.ImageView r4 = (android.widget.ImageView) r4
            r3.setTitleBack(r4)
            android.content.Intent r4 = r3.getIntent()
            java.lang.String r0 = "url"
            java.lang.String r4 = r4.getStringExtra(r0)
            android.content.Intent r0 = r3.getIntent()
            java.lang.String r1 = "action"
            java.lang.String r0 = r0.getStringExtra(r1)
            r1 = 2131693951(0x7f0f117f, float:1.9017045E38)
            android.view.View r1 = r3.findViewById(r1)
            android.widget.Button r1 = (android.widget.Button) r1
            r3.submit = r1
            com.jingdong.common.sample.JshopNoPayPinActivity$1 r2 = new com.jingdong.common.sample.JshopNoPayPinActivity$1
            r2.<init>()
            r1.setOnClickListener(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.sample.JshopNoPayPinActivity.onCreate(android.os.Bundle):void");
    }
}

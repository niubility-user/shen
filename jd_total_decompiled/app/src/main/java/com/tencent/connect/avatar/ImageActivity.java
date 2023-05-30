package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.b.e;
import com.tencent.open.b.h;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.g;
import com.tencent.open.utils.m;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ImageActivity extends Activity {
    RelativeLayout a;
    private QQToken b;

    /* renamed from: c  reason: collision with root package name */
    private String f16127c;
    private Handler d;

    /* renamed from: e  reason: collision with root package name */
    private c f16128e;

    /* renamed from: f  reason: collision with root package name */
    private Button f16129f;

    /* renamed from: g  reason: collision with root package name */
    private Button f16130g;

    /* renamed from: h  reason: collision with root package name */
    private b f16131h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f16132i;

    /* renamed from: j  reason: collision with root package name */
    private ProgressBar f16133j;
    private String r;
    private Bitmap s;

    /* renamed from: k  reason: collision with root package name */
    private int f16134k = 0;

    /* renamed from: l  reason: collision with root package name */
    private boolean f16135l = false;

    /* renamed from: m  reason: collision with root package name */
    private long f16136m = 0;

    /* renamed from: n  reason: collision with root package name */
    private int f16137n = 0;
    private final int o = 640;
    private final int p = 640;
    private Rect q = new Rect();
    private final View.OnClickListener t = new View.OnClickListener() { // from class: com.tencent.connect.avatar.ImageActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageActivity.this.f16133j.setVisibility(0);
            ImageActivity.this.f16130g.setEnabled(false);
            ImageActivity.this.f16130g.setTextColor(Color.rgb(21, 21, 21));
            ImageActivity.this.f16129f.setEnabled(false);
            ImageActivity.this.f16129f.setTextColor(Color.rgb(36, 94, 134));
            new Thread(new Runnable() { // from class: com.tencent.connect.avatar.ImageActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageActivity.this.c();
                }
            }).start();
            if (ImageActivity.this.f16135l) {
                ImageActivity.this.a("10657", 0L);
                return;
            }
            ImageActivity.this.a("10655", System.currentTimeMillis() - ImageActivity.this.f16136m);
            if (ImageActivity.this.f16128e.b) {
                ImageActivity.this.a("10654", 0L);
            }
        }
    };
    private final View.OnClickListener u = new View.OnClickListener() { // from class: com.tencent.connect.avatar.ImageActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageActivity.this.a("10656", System.currentTimeMillis() - ImageActivity.this.f16136m);
            ImageActivity.this.setResult(0);
            ImageActivity.this.d();
        }
    };
    private final IUiListener v = new DefaultUiListener() { // from class: com.tencent.connect.avatar.ImageActivity.5
        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            ImageActivity.this.f16130g.setEnabled(true);
            int i2 = -1;
            ImageActivity.this.f16130g.setTextColor(-1);
            ImageActivity.this.f16129f.setEnabled(true);
            ImageActivity.this.f16129f.setTextColor(-1);
            ImageActivity.this.f16133j.setVisibility(8);
            JSONObject jSONObject = (JSONObject) obj;
            try {
                i2 = jSONObject.getInt("ret");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (i2 == 0) {
                ImageActivity.this.a("\u8bbe\u7f6e\u6210\u529f", 0);
                ImageActivity.this.a("10658", 0L);
                e.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", "3", "0");
                ImageActivity imageActivity = ImageActivity.this;
                if (imageActivity.f16127c != null && !"".equals(ImageActivity.this.f16127c)) {
                    Intent intent = new Intent();
                    intent.setClassName(imageActivity, ImageActivity.this.f16127c);
                    if (imageActivity.getPackageManager().resolveActivity(intent, 0) != null) {
                        imageActivity.startActivity(intent);
                    }
                }
                ImageActivity.this.a(0, jSONObject.toString(), null, null);
                ImageActivity.this.d();
                return;
            }
            ImageActivity.this.a("\u8bbe\u7f6e\u51fa\u9519\u4e86\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u518d\u5c1d\u8bd5\u4e0b\u5462\uff1a\uff09", 1);
            e.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", Constants.VIA_ACT_TYPE_NINETEEN, "1");
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            ImageActivity.this.f16130g.setEnabled(true);
            ImageActivity.this.f16130g.setTextColor(-1);
            ImageActivity.this.f16129f.setEnabled(true);
            ImageActivity.this.f16129f.setTextColor(-1);
            ImageActivity.this.f16129f.setText("\u91cd\u8bd5");
            ImageActivity.this.f16133j.setVisibility(8);
            ImageActivity.this.f16135l = true;
            ImageActivity.this.a(uiError.errorMessage, 1);
            ImageActivity.this.a("10660", 0L);
        }
    };
    private final IUiListener w = new DefaultUiListener() { // from class: com.tencent.connect.avatar.ImageActivity.6
        private void a(int i2) {
            if (ImageActivity.this.f16134k < 2) {
                ImageActivity.this.e();
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            int i2 = -1;
            try {
                i2 = jSONObject.getInt("ret");
                if (i2 == 0) {
                    jSONObject.getString("nickname");
                    ImageActivity.this.d.post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001e: INVOKE 
                          (wrap: android.os.Handler : 0x0015: IGET 
                          (wrap: com.tencent.connect.avatar.ImageActivity : 0x0013: IGET (r5v0 'this' com.tencent.connect.avatar.ImageActivity$6 A[IMMUTABLE_TYPE, THIS]) A[Catch: JSONException -> 0x0031, WRAPPED] (LINE:4) com.tencent.connect.avatar.ImageActivity.6.a com.tencent.connect.avatar.ImageActivity)
                         A[Catch: JSONException -> 0x0031, MD:(com.tencent.connect.avatar.ImageActivity):android.os.Handler (m), WRAPPED] (LINE:1) com.tencent.connect.avatar.ImageActivity.d android.os.Handler)
                          (wrap: java.lang.Runnable : 0x001b: CONSTRUCTOR 
                          (r5v0 'this' com.tencent.connect.avatar.ImageActivity$6 A[IMMUTABLE_TYPE, THIS])
                          (r6 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: JSONException -> 0x0031, MD:(com.tencent.connect.avatar.ImageActivity$6, java.lang.String):void (m), WRAPPED] call: com.tencent.connect.avatar.ImageActivity.6.1.<init>(com.tencent.connect.avatar.ImageActivity$6, java.lang.String):void type: CONSTRUCTOR)
                         type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[Catch: JSONException -> 0x0031, MD:(java.lang.Runnable):boolean (c)] (LINE:1) in method: com.tencent.connect.avatar.ImageActivity.6.onComplete(java.lang.Object):void, file: classes9.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                        	... 27 more
                        */
                    /*
                        this = this;
                        org.json.JSONObject r6 = (org.json.JSONObject) r6
                        r0 = -1
                        java.lang.String r1 = "ret"
                        int r0 = r6.getInt(r1)     // Catch: org.json.JSONException -> L31
                        r1 = 0
                        if (r0 != 0) goto L29
                        java.lang.String r3 = "nickname"
                        java.lang.String r6 = r6.getString(r3)     // Catch: org.json.JSONException -> L31
                        com.tencent.connect.avatar.ImageActivity r3 = com.tencent.connect.avatar.ImageActivity.this     // Catch: org.json.JSONException -> L31
                        android.os.Handler r3 = com.tencent.connect.avatar.ImageActivity.m(r3)     // Catch: org.json.JSONException -> L31
                        com.tencent.connect.avatar.ImageActivity$6$1 r4 = new com.tencent.connect.avatar.ImageActivity$6$1     // Catch: org.json.JSONException -> L31
                        r4.<init>()     // Catch: org.json.JSONException -> L31
                        r3.post(r4)     // Catch: org.json.JSONException -> L31
                        com.tencent.connect.avatar.ImageActivity r6 = com.tencent.connect.avatar.ImageActivity.this     // Catch: org.json.JSONException -> L31
                        java.lang.String r3 = "10659"
                        r6.a(r3, r1)     // Catch: org.json.JSONException -> L31
                        goto L35
                    L29:
                        com.tencent.connect.avatar.ImageActivity r6 = com.tencent.connect.avatar.ImageActivity.this     // Catch: org.json.JSONException -> L31
                        java.lang.String r3 = "10661"
                        r6.a(r3, r1)     // Catch: org.json.JSONException -> L31
                        goto L35
                    L31:
                        r6 = move-exception
                        r6.printStackTrace()
                    L35:
                        if (r0 == 0) goto L3a
                        r5.a(r0)
                    L3a:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.avatar.ImageActivity.AnonymousClass6.onComplete(java.lang.Object):void");
                }

                @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
                public void onError(UiError uiError) {
                    a(0);
                }
            };

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: classes9.dex */
            public class QQAvatarImp extends BaseApi {
                public QQAvatarImp(QQToken qQToken) {
                    super(qQToken);
                }

                public void setAvator(Bitmap bitmap, IUiListener iUiListener) {
                    Bundle a = a();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    bitmap.recycle();
                    BaseApi.TempRequestListener tempRequestListener = new BaseApi.TempRequestListener(iUiListener);
                    a.putByteArray("picture", byteArray);
                    HttpUtils.requestAsync(this.f16153c, g.a(), "user/set_user_face", a, "POST", tempRequestListener);
                    e.a().a(this.f16153c.getOpenId(), this.f16153c.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", Constants.VIA_ACT_TYPE_NINETEEN, "0");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* loaded from: classes9.dex */
            public class a extends View {
                public a(Context context) {
                    super(context);
                }

                public void a(Button button) {
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    Drawable b = ImageActivity.this.b("com.tencent.plus.blue_normal.png");
                    Drawable b2 = ImageActivity.this.b("com.tencent.plus.blue_down.png");
                    Drawable b3 = ImageActivity.this.b("com.tencent.plus.blue_disable.png");
                    stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, b2);
                    stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, b);
                    stateListDrawable.addState(View.ENABLED_STATE_SET, b);
                    stateListDrawable.addState(View.FOCUSED_STATE_SET, b);
                    stateListDrawable.addState(View.EMPTY_STATE_SET, b3);
                    button.setBackgroundDrawable(stateListDrawable);
                }

                public void b(Button button) {
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    Drawable b = ImageActivity.this.b("com.tencent.plus.gray_normal.png");
                    Drawable b2 = ImageActivity.this.b("com.tencent.plus.gray_down.png");
                    Drawable b3 = ImageActivity.this.b("com.tencent.plus.gray_disable.png");
                    stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, b2);
                    stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, b);
                    stateListDrawable.addState(View.ENABLED_STATE_SET, b);
                    stateListDrawable.addState(View.FOCUSED_STATE_SET, b);
                    stateListDrawable.addState(View.EMPTY_STATE_SET, b3);
                    button.setBackgroundDrawable(stateListDrawable);
                }
            }

            @Override // android.app.Activity
            public void onBackPressed() {
                setResult(0);
                d();
            }

            @Override // android.app.Activity
            public void onCreate(Bundle bundle) {
                requestWindowFeature(1);
                super.onCreate(bundle);
                setRequestedOrientation(1);
                setContentView(a());
                this.d = new Handler();
                Bundle bundleExtra = getIntent().getBundleExtra(Constants.KEY_PARAMS);
                this.r = bundleExtra.getString("picture");
                this.f16127c = bundleExtra.getString("return_activity");
                String string = bundleExtra.getString("appid");
                String string2 = bundleExtra.getString(Constants.PARAM_ACCESS_TOKEN);
                long j2 = bundleExtra.getLong(Constants.PARAM_EXPIRES_IN);
                String string3 = bundleExtra.getString("openid");
                this.f16137n = bundleExtra.getInt("exitAnim");
                QQToken qQToken = new QQToken(string);
                this.b = qQToken;
                qQToken.setAccessToken(string2, ((j2 - System.currentTimeMillis()) / 1000) + "");
                this.b.setOpenId(string3);
                b();
                e();
                this.f16136m = System.currentTimeMillis();
                a("10653", 0L);
            }

            @Override // android.app.Activity
            protected void onDestroy() {
                super.onDestroy();
                this.f16128e.setImageBitmap(null);
                Bitmap bitmap = this.s;
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                this.s.recycle();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void c() {
                Matrix imageMatrix = this.f16128e.getImageMatrix();
                float[] fArr = new float[9];
                imageMatrix.getValues(fArr);
                float f2 = fArr[2];
                float f3 = fArr[5];
                float f4 = fArr[0];
                float width = 640.0f / this.q.width();
                Rect rect = this.q;
                int i2 = (int) ((rect.left - f2) / f4);
                int i3 = i2 < 0 ? 0 : i2;
                int i4 = (int) ((rect.top - f3) / f4);
                int i5 = i4 < 0 ? 0 : i4;
                Matrix matrix = new Matrix();
                matrix.set(imageMatrix);
                matrix.postScale(width, width);
                int i6 = (int) (650.0f / f4);
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(this.s, i3, i5, Math.min(this.s.getWidth() - i3, i6), Math.min(this.s.getHeight() - i5, i6), matrix, true);
                    Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, 640, 640);
                    createBitmap.recycle();
                    a(createBitmap2);
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                    a(Constants.MSG_IMAGE_ERROR, 1);
                    a(-5, null, Constants.MSG_IMAGE_ERROR, e2.getMessage());
                    d();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void d() {
                finish();
                int i2 = this.f16137n;
                if (i2 != 0) {
                    overridePendingTransition(0, i2);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void e() {
                this.f16134k++;
                new UserInfo(this, this.b).getUserInfo(this.w);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public Drawable b(String str) {
                return m.a(str, this);
            }

            private void b() {
                Bitmap a2;
                try {
                    a2 = a(this.r);
                    this.s = a2;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    a(Constants.MSG_IMAGE_ERROR, 1);
                    a(-5, null, Constants.MSG_IMAGE_ERROR, e2.getMessage());
                    d();
                }
                if (a2 != null) {
                    this.f16128e.setImageBitmap(a2);
                    this.f16129f.setOnClickListener(this.t);
                    this.f16130g.setOnClickListener(this.u);
                    this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.tencent.connect.avatar.ImageActivity.1
                        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                        public void onGlobalLayout() {
                            ImageActivity.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            ImageActivity imageActivity = ImageActivity.this;
                            imageActivity.q = imageActivity.f16131h.a();
                            ImageActivity.this.f16128e.a(ImageActivity.this.q);
                        }
                    });
                    return;
                }
                throw new IOException("cannot read picture: '" + this.r + "'!");
            }

            private String d(String str) {
                return str.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", ContainerUtils.FIELD_DELIMITER);
            }

            private Bitmap a(String str) throws IOException {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i2 = 1;
                options.inJustDecodeBounds = true;
                Uri parse = Uri.parse(str);
                InputStream openInputStream = getContentResolver().openInputStream(parse);
                if (openInputStream == null) {
                    return null;
                }
                try {
                    BitmapFactory.decodeStream(openInputStream, null, options);
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                }
                openInputStream.close();
                int i3 = options.outWidth;
                int i4 = options.outHeight;
                while (i3 * i4 > 4194304) {
                    i3 /= 2;
                    i4 /= 2;
                    i2 *= 2;
                }
                options.inJustDecodeBounds = false;
                options.inSampleSize = i2;
                try {
                    return BitmapFactory.decodeStream(getContentResolver().openInputStream(parse), null, options);
                } catch (OutOfMemoryError e3) {
                    e3.printStackTrace();
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void b(String str, int i2) {
                Toast makeText = Toast.makeText(this, str, 1);
                LinearLayout linearLayout = (LinearLayout) makeText.getView();
                ((TextView) linearLayout.getChildAt(0)).setPadding(8, 0, 0, 0);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(com.tencent.connect.avatar.a.a(this, 16.0f), com.tencent.connect.avatar.a.a(this, 16.0f)));
                if (i2 == 0) {
                    imageView.setImageDrawable(b("com.tencent.plus.ic_success.png"));
                } else {
                    imageView.setImageDrawable(b("com.tencent.plus.ic_error.png"));
                }
                linearLayout.addView(imageView, 0);
                linearLayout.setOrientation(0);
                linearLayout.setGravity(17);
                makeText.setView(linearLayout);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }

            private View a() {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
                ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -1);
                ViewGroup.LayoutParams layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
                RelativeLayout relativeLayout = new RelativeLayout(this);
                this.a = relativeLayout;
                relativeLayout.setLayoutParams(layoutParams);
                this.a.setBackgroundColor(-16777216);
                RelativeLayout relativeLayout2 = new RelativeLayout(this);
                relativeLayout2.setLayoutParams(layoutParams3);
                this.a.addView(relativeLayout2);
                c cVar = new c(this);
                this.f16128e = cVar;
                cVar.setLayoutParams(layoutParams2);
                this.f16128e.setScaleType(ImageView.ScaleType.MATRIX);
                relativeLayout2.addView(this.f16128e);
                this.f16131h = new b(this);
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(layoutParams2);
                layoutParams4.addRule(14, -1);
                layoutParams4.addRule(15, -1);
                this.f16131h.setLayoutParams(layoutParams4);
                relativeLayout2.addView(this.f16131h);
                LinearLayout linearLayout = new LinearLayout(this);
                RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, com.tencent.connect.avatar.a.a(this, 80.0f));
                layoutParams5.addRule(14, -1);
                linearLayout.setLayoutParams(layoutParams5);
                linearLayout.setOrientation(0);
                linearLayout.setGravity(17);
                this.a.addView(linearLayout);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(com.tencent.connect.avatar.a.a(this, 24.0f), com.tencent.connect.avatar.a.a(this, 24.0f)));
                imageView.setImageDrawable(b("com.tencent.plus.logo.png"));
                linearLayout.addView(imageView);
                this.f16132i = new TextView(this);
                LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(layoutParams3);
                layoutParams6.leftMargin = com.tencent.connect.avatar.a.a(this, 7.0f);
                this.f16132i.setLayoutParams(layoutParams6);
                this.f16132i.setEllipsize(TextUtils.TruncateAt.END);
                this.f16132i.setSingleLine();
                this.f16132i.setTextColor(-1);
                this.f16132i.setTextSize(24.0f);
                this.f16132i.setVisibility(8);
                linearLayout.addView(this.f16132i);
                RelativeLayout relativeLayout3 = new RelativeLayout(this);
                RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, com.tencent.connect.avatar.a.a(this, 60.0f));
                layoutParams7.addRule(12, -1);
                layoutParams7.addRule(9, -1);
                relativeLayout3.setLayoutParams(layoutParams7);
                relativeLayout3.setBackgroundDrawable(b("com.tencent.plus.bar.png"));
                int a2 = com.tencent.connect.avatar.a.a(this, 10.0f);
                relativeLayout3.setPadding(a2, a2, a2, 0);
                this.a.addView(relativeLayout3);
                a aVar = new a(this);
                int a3 = com.tencent.connect.avatar.a.a(this, 14.0f);
                int a4 = com.tencent.connect.avatar.a.a(this, 7.0f);
                this.f16130g = new Button(this);
                this.f16130g.setLayoutParams(new RelativeLayout.LayoutParams(com.tencent.connect.avatar.a.a(this, 78.0f), com.tencent.connect.avatar.a.a(this, 45.0f)));
                this.f16130g.setText("\u53d6\u6d88");
                this.f16130g.setTextColor(-1);
                this.f16130g.setTextSize(18.0f);
                this.f16130g.setPadding(a3, a4, a3, a4);
                aVar.b(this.f16130g);
                relativeLayout3.addView(this.f16130g);
                this.f16129f = new Button(this);
                RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(com.tencent.connect.avatar.a.a(this, 78.0f), com.tencent.connect.avatar.a.a(this, 45.0f));
                layoutParams8.addRule(11, -1);
                this.f16129f.setLayoutParams(layoutParams8);
                this.f16129f.setTextColor(-1);
                this.f16129f.setTextSize(18.0f);
                this.f16129f.setPadding(a3, a4, a3, a4);
                this.f16129f.setText("\u9009\u53d6");
                aVar.a(this.f16129f);
                relativeLayout3.addView(this.f16129f);
                TextView textView = new TextView(this);
                RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(layoutParams3);
                layoutParams9.addRule(13, -1);
                textView.setLayoutParams(layoutParams9);
                textView.setText("\u79fb\u52a8\u548c\u7f29\u653e");
                textView.setPadding(0, com.tencent.connect.avatar.a.a(this, 3.0f), 0, 0);
                textView.setTextSize(18.0f);
                textView.setTextColor(-1);
                relativeLayout3.addView(textView);
                this.f16133j = new ProgressBar(this);
                RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(layoutParams3);
                layoutParams10.addRule(14, -1);
                layoutParams10.addRule(15, -1);
                this.f16133j.setLayoutParams(layoutParams10);
                this.f16133j.setVisibility(8);
                this.a.addView(this.f16133j);
                return this.a;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void c(String str) {
                String d = d(str);
                if ("".equals(d)) {
                    return;
                }
                this.f16132i.setText(d);
                this.f16132i.setVisibility(0);
            }

            private void a(Bitmap bitmap) {
                new QQAvatarImp(this.b).setAvator(bitmap, this.v);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(final String str, final int i2) {
                this.d.post(new Runnable() { // from class: com.tencent.connect.avatar.ImageActivity.4
                    @Override // java.lang.Runnable
                    public void run() {
                        ImageActivity.this.b(str, i2);
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void a(int i2, String str, String str2, String str3) {
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_ERROR_CODE, i2);
                intent.putExtra(Constants.KEY_ERROR_MSG, str2);
                intent.putExtra(Constants.KEY_ERROR_DETAIL, str3);
                intent.putExtra(Constants.KEY_RESPONSE, str);
                setResult(-1, intent);
            }

            public void a(String str, long j2) {
                a(str, j2, this.b.getAppId());
            }

            public static void a(String str, long j2, String str2) {
                HashMap hashMap = new HashMap();
                hashMap.put("strValue", str2);
                hashMap.put("nValue", str);
                hashMap.put("qver", Constants.SDK_VERSION);
                if (j2 != 0) {
                    hashMap.put("elt", String.valueOf(j2));
                }
                h.a().a("https://cgi.qplus.com/report/report", hashMap);
            }
        }

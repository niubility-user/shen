package com.jingdong.app.mall.bundle.smile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.bundle.icssdk_smile.R;
import com.jingdong.app.mall.bundle.smile.SmileyPageAdapter;
import com.jingdong.app.mall.bundle.smile.model.SmileBean;
import com.jingdong.app.mall.bundle.smile.utils.ActivityUtil;
import com.jingdong.app.mall.bundle.smile.utils.Const;
import com.jingdong.app.mall.bundle.smile.utils.RouterUtil;
import com.jingdong.app.mall.bundle.smile.utils.ZipUtils;
import com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener;
import com.jingdong.app.mall.bundle.updownload.download.DownloadManager;
import com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask;
import com.jingdong.app.mall.bundle.updownload.utils.FileUtils;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"ValidFragment"})
/* loaded from: classes3.dex */
public class FragmentJoySmall extends Fragment implements View.OnClickListener {
    private static final String TAG = "FragmentJoySmall";
    private TextView mDownJoyBtn;
    private View mDownJoyLl;
    private ImageView mDownJoyLoading;
    private ProgressBar mDownJoyProBar;
    private LinearLayout mDownJoyProBarLl;
    private TextView mDownJoyProTv;
    private TextView mDownJoyTv;
    private LinearLayout mIndicatorLayout;
    private SmileClickListener mSmileClickListener;
    private List<SmileBean> mSmileyList;
    private SmileyPageAdapter mSmileyPageAdapter;
    private ViewPager mSmileyPager;
    private ArrayList<ImageView> mSmileyTagList;
    private View rootView;
    private List<SmileBean> smileBeans;
    private String url;
    private boolean visitor;
    private String name = "";
    private BaseDownloadProgressListener mDownLoadListener = new BaseDownloadProgressListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.6
        @Override // com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener, com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
        public void onCancel(Object obj, Bundle bundle) {
            OKLog.d(FragmentJoySmall.TAG, "onCancel>>>tag: " + obj);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.6.3
                @Override // java.lang.Runnable
                public void run() {
                    FragmentJoySmall.this.mDownJoyBtn.setVisibility(0);
                    FragmentJoySmall.this.mDownJoyBtn.setOnClickListener(FragmentJoySmall.this);
                    FragmentJoySmall.this.mDownJoyProBarLl.setVisibility(8);
                }
            });
            super.onCancel(obj, bundle);
        }

        @Override // com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener, com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
        public void onComplete(Object obj, String str, Bundle bundle) {
            OKLog.d(FragmentJoySmall.TAG, "onComplete>>>tag: " + obj + " filePath: " + str);
            super.onComplete(obj, str, bundle);
            try {
                String substring = str.substring(0, str.lastIndexOf("/"));
                OKLog.d(FragmentJoySmall.TAG, "directoryPath:" + substring);
                Pair<String, String> upZipSmile = ZipUtils.upZipSmile(str, substring);
                OKLog.d(FragmentJoySmall.TAG, "jsonPath:" + ((String) upZipSmile.first));
                if (!TextUtils.isEmpty((CharSequence) upZipSmile.first) && ((String) upZipSmile.first).endsWith(FileService.CACHE_EXT_NAME_JSON)) {
                    if (ZipUtils.fromSmile(FragmentJoySmall.this.getContext(), substring, (String) upZipSmile.second, (String) upZipSmile.first, FragmentJoySmall.this.visitor)) {
                        RouterUtil.findSmileys(FragmentJoySmall.this.getContext(), new CallBackWithReturnListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.6.1
                            @Override // com.jingdong.common.unification.router.CallBackListener
                            public void onComplete() {
                            }

                            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
                            public void onComplete(Object obj2) {
                                FragmentJoySmall.this.smileBeans = (List) obj2;
                            }

                            @Override // com.jingdong.common.unification.router.CallBackListener
                            public void onError(int i2) {
                            }
                        }, FragmentJoySmall.this.visitor);
                        if (FragmentJoySmall.this.smileBeans == null || FragmentJoySmall.this.smileBeans.isEmpty()) {
                            return;
                        }
                        FragmentJoySmall fragmentJoySmall = FragmentJoySmall.this;
                        fragmentJoySmall.mSmileyList = fragmentJoySmall.smileBeans;
                        Iterator it = FragmentJoySmall.this.smileBeans.iterator();
                        if (it.hasNext()) {
                            SmileBean smileBean = (SmileBean) it.next();
                            OKLog.d(FragmentJoySmall.TAG, "smileBean.directoryPath:" + smileBean.directoryPath);
                            FileUtils.delete(smileBean.directoryPath);
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.6.2
                            @Override // java.lang.Runnable
                            public void run() {
                                FragmentJoySmall.this.setSmileyVisibe();
                                FragmentJoySmall.this.initSmileyData();
                            }
                        });
                        return;
                    }
                    FileUtils.delete(substring);
                    return;
                }
                FileUtils.delete(substring);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener, com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
        public void onFailure(Object obj, int i2, String str, Bundle bundle) {
            OKLog.d(FragmentJoySmall.TAG, "onFailure>>>tag: " + obj + " code: " + i2 + " err: " + str);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.6.4
                @Override // java.lang.Runnable
                public void run() {
                    FragmentJoySmall.this.mDownJoyBtn.setVisibility(0);
                    FragmentJoySmall.this.mDownJoyBtn.setOnClickListener(FragmentJoySmall.this);
                    FragmentJoySmall.this.mDownJoyProBarLl.setVisibility(8);
                    Toast.makeText(FragmentJoySmall.this.getActivity(), R.string.icssdk_smile_system_error, 0).show();
                }
            });
            super.onFailure(obj, i2, str, bundle);
        }

        @Override // com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener
        public void onUIResponseProgress(long j2, long j3, boolean z) {
            super.onUIResponseProgress(j2, j3, z);
            int i2 = (int) (((((float) j2) * 1.0f) / ((float) j3)) * 100.0f);
            OKLog.d(FragmentJoySmall.TAG, "onUIResponseProgress>>>progress: " + i2 + " done: " + z);
            if (FragmentJoySmall.this.mDownJoyProBar != null) {
                FragmentJoySmall.this.mDownJoyProBar.setProgress(i2);
            }
            if (FragmentJoySmall.this.mDownJoyProTv != null) {
                FragmentJoySmall.this.mDownJoyProTv.setText(i2 + "%");
            }
        }
    };

    /* loaded from: classes3.dex */
    public interface SmileClickListener {
        void onClick(SmileBean smileBean, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(View view, SmileBean smileBean) {
        OKLog.d(TAG, "------ onGridViewItemSelected() ------>");
        SmileClickListener smileClickListener = this.mSmileClickListener;
        if (smileClickListener != null) {
            smileClickListener.onClick(smileBean, smileBean.delIcon > 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSmileyData() {
        OKLog.d(TAG, "------ initSmileyData() ------>");
        if (getActivity() != null && !ActivityUtil.checkDestroyed(getActivity())) {
            this.mSmileyTagList = new ArrayList<>();
            SmileyPageAdapter smileyPageAdapter = new SmileyPageAdapter(getActivity(), this.mSmileyList, this.mIndicatorLayout, this.mSmileyTagList);
            this.mSmileyPageAdapter = smileyPageAdapter;
            this.mSmileyPager.setAdapter(smileyPageAdapter);
            this.mSmileyPageAdapter.setOnGridViewItemSelectedListener(new SmileyPageAdapter.GridViewItemSelected() { // from class: com.jingdong.app.mall.bundle.smile.a
                @Override // com.jingdong.app.mall.bundle.smile.SmileyPageAdapter.GridViewItemSelected
                public final void onGridViewItemSelected(View view, SmileBean smileBean) {
                    FragmentJoySmall.this.b(view, smileBean);
                }
            });
            this.mSmileyPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.3
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                    OKLog.d(FragmentJoySmall.TAG, "------ onPageScrollStateChanged() ------>");
                    OKLog.d(FragmentJoySmall.TAG, "<------ onPageScrollStateChanged() ------");
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                    OKLog.d(FragmentJoySmall.TAG, "------ onPageScrolled() ------>");
                    OKLog.d(FragmentJoySmall.TAG, "<------ onPageScrolled() ------");
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    OKLog.d(FragmentJoySmall.TAG, "------ onPageSelected() ------>");
                    for (int i3 = 0; i3 < FragmentJoySmall.this.mSmileyTagList.size(); i3++) {
                        if (i2 != i3) {
                            ((ImageView) FragmentJoySmall.this.mSmileyTagList.get(i3)).setImageResource(R.drawable.icssdk_smile_page_tag_normol);
                        } else {
                            ((ImageView) FragmentJoySmall.this.mSmileyTagList.get(i3)).setImageResource(R.drawable.icssdk_smile_page_tag_pressed);
                        }
                    }
                    OKLog.d(FragmentJoySmall.TAG, "<------ onPageSelected() ------");
                }
            });
            return;
        }
        OKLog.e(TAG, "initSmileyData <<< activity is null !");
    }

    private void initView(View view) {
        this.mSmileyPager = (ViewPager) view.findViewById(R.id.joyPager);
        this.mIndicatorLayout = (LinearLayout) view.findViewById(R.id.indicatorLayout);
        this.mDownJoyLl = view.findViewById(R.id.down_joy_ll);
        this.mDownJoyTv = (TextView) view.findViewById(R.id.down_joy_tv);
        this.mDownJoyProBarLl = (LinearLayout) view.findViewById(R.id.down_joy_progressbar_ll);
        this.mDownJoyBtn = (TextView) view.findViewById(R.id.down_joy_button);
        this.mDownJoyProBar = (ProgressBar) view.findViewById(R.id.down_joy_progressbar);
        this.mDownJoyProTv = (TextView) view.findViewById(R.id.down_joy_progress_tv);
        this.mDownJoyLoading = (ImageView) view.findViewById(R.id.down_joy_loading_iv);
        List<SmileBean> list = this.mSmileyList;
        if (list != null && !list.isEmpty()) {
            setSmileyVisibe();
            initSmileyData();
            return;
        }
        setDownVisibe();
        this.mDownJoyTv.setText(R.string.icssdk_smile_default_emoji_download_tips);
        RouterUtil.getEmojiUrl(getContext(), new CallBackWithReturnListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.2
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
            }

            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
            public void onComplete(Object obj) {
                FragmentJoySmall.this.url = (String) obj;
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
            }
        });
        DownloadTask task = DownloadManager.getInstance().getTask(DownloadManager.TYPE_EMOJI, this.url);
        if (task != null) {
            this.mDownJoyBtn.setVisibility(8);
            this.mDownJoyProBarLl.setVisibility(0);
            dissLoading();
            this.mDownJoyProBar.setProgress(task.getCurrentProgress());
            this.mDownJoyProTv.setText(task.getCurrentProgress() + "%");
            task.setListener(this.mDownLoadListener);
            return;
        }
        this.mDownJoyBtn.setVisibility(0);
        this.mDownJoyProBarLl.setVisibility(8);
        dissLoading();
        this.mDownJoyBtn.setOnClickListener(this);
    }

    public void applyUIMode() {
        SmileyPageAdapter smileyPageAdapter = this.mSmileyPageAdapter;
        if (smileyPageAdapter != null) {
            smileyPageAdapter.notifyDataSetChanged();
        }
    }

    public void dissLoading() {
        ImageView imageView = this.mDownJoyLoading;
        if (imageView != null) {
            imageView.setVisibility(8);
            this.mDownJoyLoading.clearAnimation();
        }
    }

    public void init(boolean z) {
        this.visitor = z;
    }

    public void initData() {
        RouterUtil.findSmileysWithFlag(getContext(), new CallBackWithReturnListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.1
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
            }

            @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
            public void onComplete(Object obj) {
                FragmentJoySmall.this.mSmileyList = (List) obj;
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
            }
        }, this.visitor);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.down_joy_button) {
            if (NetUtils.isNetworkAvailable()) {
                RouterUtil.getEmojiUrl(getContext(), new CallBackWithReturnListener() { // from class: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.4
                    @Override // com.jingdong.common.unification.router.CallBackListener
                    public void onComplete() {
                    }

                    @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
                    public void onComplete(Object obj) {
                        FragmentJoySmall.this.url = (String) obj;
                    }

                    @Override // com.jingdong.common.unification.router.CallBackListener
                    public void onError(int i2) {
                    }
                });
                if (TextUtils.isEmpty(this.url)) {
                    this.url = Const.DEFAULT_EMOJI_URL;
                }
                OKLog.d(TAG, "downLoadSmile url = " + this.url);
                this.mDownJoyBtn.setVisibility(8);
                this.mDownJoyProBarLl.setVisibility(0);
                showLoading();
                int lastIndexOf = this.url.lastIndexOf("/");
                if (lastIndexOf > 0) {
                    this.name = this.url.substring(lastIndexOf + 1);
                } else {
                    this.name = String.valueOf(this.url.hashCode());
                }
                new StringBuilder();
                RouterUtil.getPin(getContext(), new CallBackWithReturnListener
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x007f: INVOKE 
                      (wrap: android.content.Context : 0x0074: INVOKE (r2v0 'this' com.jingdong.app.mall.bundle.smile.FragmentJoySmall A[IMMUTABLE_TYPE, THIS]) type: VIRTUAL call: androidx.fragment.app.Fragment.getContext():android.content.Context A[MD:():android.content.Context (m), WRAPPED] (LINE:15))
                      (wrap: com.jingdong.common.unification.router.CallBackWithReturnListener : 0x007a: CONSTRUCTOR 
                      (r2v0 'this' com.jingdong.app.mall.bundle.smile.FragmentJoySmall A[IMMUTABLE_TYPE, THIS])
                      (r3 I:java.lang.StringBuilder A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(com.jingdong.app.mall.bundle.smile.FragmentJoySmall, java.lang.StringBuilder):void (m), WRAPPED] call: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.5.<init>(com.jingdong.app.mall.bundle.smile.FragmentJoySmall, java.lang.StringBuilder):void type: CONSTRUCTOR)
                      (wrap: boolean : 0x007d: IGET (r2v0 'this' com.jingdong.app.mall.bundle.smile.FragmentJoySmall A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] com.jingdong.app.mall.bundle.smile.FragmentJoySmall.visitor boolean)
                     type: STATIC call: com.jingdong.app.mall.bundle.smile.utils.RouterUtil.getPin(android.content.Context, com.jingdong.common.unification.router.CallBackWithReturnListener, boolean):void A[MD:(android.content.Context, com.jingdong.common.unification.router.CallBackWithReturnListener, boolean):void (m)] (LINE:15) in method: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.onClick(android.view.View):void, file: classes3.dex
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
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    	... 31 more
                    */
                /*
                    this = this;
                    int r3 = r3.getId()
                    int r0 = com.jingdong.app.mall.bundle.icssdk_smile.R.id.down_joy_button
                    if (r3 != r0) goto L90
                    boolean r3 = com.jingdong.jdsdk.utils.NetUtils.isNetworkAvailable()
                    r0 = 0
                    if (r3 == 0) goto L83
                    android.content.Context r3 = r2.getContext()
                    com.jingdong.app.mall.bundle.smile.FragmentJoySmall$4 r1 = new com.jingdong.app.mall.bundle.smile.FragmentJoySmall$4
                    r1.<init>()
                    com.jingdong.app.mall.bundle.smile.utils.RouterUtil.getEmojiUrl(r3, r1)
                    java.lang.String r3 = r2.url
                    boolean r3 = android.text.TextUtils.isEmpty(r3)
                    if (r3 == 0) goto L27
                    java.lang.String r3 = "https://storage.360buyimg.com/sticker-zip/ddemojis/v0/emoji.zip"
                    r2.url = r3
                L27:
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r1 = "downLoadSmile url = "
                    r3.append(r1)
                    java.lang.String r1 = r2.url
                    r3.append(r1)
                    java.lang.String r3 = r3.toString()
                    java.lang.String r1 = "FragmentJoySmall"
                    com.jingdong.sdk.oklog.OKLog.d(r1, r3)
                    android.widget.TextView r3 = r2.mDownJoyBtn
                    r1 = 8
                    r3.setVisibility(r1)
                    android.widget.LinearLayout r3 = r2.mDownJoyProBarLl
                    r3.setVisibility(r0)
                    r2.showLoading()
                    java.lang.String r3 = r2.url
                    java.lang.String r0 = "/"
                    int r3 = r3.lastIndexOf(r0)
                    if (r3 <= 0) goto L63
                    java.lang.String r0 = r2.url
                    int r3 = r3 + 1
                    java.lang.String r3 = r0.substring(r3)
                    r2.name = r3
                    goto L6f
                L63:
                    java.lang.String r3 = r2.url
                    int r3 = r3.hashCode()
                    java.lang.String r3 = java.lang.String.valueOf(r3)
                    r2.name = r3
                L6f:
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    android.content.Context r0 = r2.getContext()
                    com.jingdong.app.mall.bundle.smile.FragmentJoySmall$5 r1 = new com.jingdong.app.mall.bundle.smile.FragmentJoySmall$5
                    r1.<init>()
                    boolean r3 = r2.visitor
                    com.jingdong.app.mall.bundle.smile.utils.RouterUtil.getPin(r0, r1, r3)
                    goto L90
                L83:
                    androidx.fragment.app.FragmentActivity r3 = r2.getActivity()
                    int r1 = com.jingdong.app.mall.bundle.icssdk_smile.R.string.icssdk_smile_network_error
                    android.widget.Toast r3 = android.widget.Toast.makeText(r3, r1, r0)
                    r3.show()
                L90:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.smile.FragmentJoySmall.onClick(android.view.View):void");
            }

            @Override // androidx.fragment.app.Fragment
            public void onCreate(@Nullable Bundle bundle) {
                super.onCreate(bundle);
            }

            @Override // androidx.fragment.app.Fragment
            @Nullable
            public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
                if (this.rootView == null) {
                    this.rootView = layoutInflater.inflate(R.layout.icssdk_smile_chat_fragment_joy_small, viewGroup, false);
                }
                initData();
                initView(this.rootView);
                return this.rootView;
            }

            public void setDownVisibe() {
                this.mDownJoyLl.setVisibility(0);
                this.mSmileyPager.setVisibility(8);
                this.mIndicatorLayout.setVisibility(8);
            }

            public void setSmileClickListener(SmileClickListener smileClickListener) {
                this.mSmileClickListener = smileClickListener;
            }

            public void setSmileyVisibe() {
                this.mDownJoyLl.setVisibility(8);
                this.mSmileyPager.setVisibility(0);
                this.mIndicatorLayout.setVisibility(0);
            }

            public void showLoading() {
                ImageView imageView = this.mDownJoyLoading;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360000.0f, 1, 0.5f, 1, 0.5f);
                    rotateAnimation.setDuration(1000000L);
                    rotateAnimation.setRepeatCount(-1);
                    rotateAnimation.setInterpolator(new LinearInterpolator());
                    this.mDownJoyLoading.startAnimation(rotateAnimation);
                }
            }
        }

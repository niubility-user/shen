package com.jdjr.generalKeyboard.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jdjr.degrade.SDKRequestDegradeManager;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.BasicKeyboardCallback;
import com.jdjr.generalKeyboard.common.JDJRResultMessage;
import com.jdjr.generalKeyboard.common.KeyboardError;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.ViewsUtils;
import com.jdjr.tools.CommonTools;
import com.jdjr.tools.EncryptUtils;
import com.jdjr.tools.EnvConfig;
import com.jdjr.tools.JDJRLog;
import com.jdjr.tools.JDJRSecureUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import com.wangyin.platform.CryptoUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes18.dex */
public class GeneralKeyboard extends LinearLayout {
    protected static final String FUNC_LIST_DEFAULT_VALUE = "00001010";
    static final String TAG = "GeneralKeyboard";
    private static int originalUIMode = -1;
    protected BasicKeyboardCallback basicKeyboardCallback;
    protected String cryptoAlg;
    protected FunctionalKeyboardCallback functionalKeyboardCallback;
    protected String isDegrade;
    private int mBottom;
    private String mCertificate;
    protected Context mContext;
    protected long mCppHandler;
    protected int mIsCipherMode;
    public boolean mIsKeyboardShown;
    protected boolean mIsPlainText;
    protected FrameLayout mRootView;
    protected StringBuilder mSB;
    private String mSMCertificate;
    private long mServerTime;
    protected CryptoUtils mUtils;
    protected int maxInputLen;
    protected String md5Attach;

    /* loaded from: classes18.dex */
    public enum FunctionalActionType {
        BACK,
        HIDE,
        CLOSE,
        FINISH,
        DELETE_ALL,
        GET_VERIFY_CODE,
        ACTION_LEFT,
        ACTION_MIDDLE,
        ACTION_RIGHT
    }

    /* loaded from: classes18.dex */
    public interface FunctionalKeyboardCallback {
        void onActionClick(FunctionalActionType functionalActionType, String str);
    }

    /* loaded from: classes18.dex */
    public enum KeyboardModeBasic {
        BASE_TOTAL,
        BASE_NUMBER_PURE_CAN_FINISH,
        BASE_NUMBER_PURE_NO_FINISH,
        BASE_NUMBER_X_CAN_FINISH,
        BASE_NUMBER_X_NO_FINISH,
        BASE_NUMBER_POINT_CAN_FINISH,
        BASE_NUMBER_POINT_NO_FINISH
    }

    /* loaded from: classes18.dex */
    public enum KeyboardModeFunctional {
        FUNCTION_PAYMENT,
        FUNCTION_SIX_UNDERLINE,
        FUNCTION_SIX_SQUARE,
        FUNCTION_VERIFY_CODE,
        FUNCTION_COMMON_PWD
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class TimeAsyncTask extends AsyncTask<Void, Void, Boolean> {
        String mTimeAddress;

        private TimeAsyncTask() {
            GeneralKeyboard.this = r1;
            this.mTimeAddress = null;
        }

        private void setTimeAddress() {
            this.mTimeAddress = "https://aks.jdpay.com/timeMillis";
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            setTimeAddress();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:128:0x00b7 A[Catch: Exception -> 0x00b3, TryCatch #11 {Exception -> 0x00b3, blocks: (B:124:0x00af, B:128:0x00b7, B:130:0x00bc), top: B:144:0x00af }] */
        /* JADX WARN: Removed duplicated region for block: B:130:0x00bc A[Catch: Exception -> 0x00b3, TRY_LEAVE, TryCatch #11 {Exception -> 0x00b3, blocks: (B:124:0x00af, B:128:0x00b7, B:130:0x00bc), top: B:144:0x00af }] */
        /* JADX WARN: Removed duplicated region for block: B:144:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v14, types: [java.io.Reader, java.io.InputStreamReader] */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v3 */
        /* JADX WARN: Type inference failed for: r2v4, types: [java.io.InputStreamReader] */
        /* JADX WARN: Type inference failed for: r2v5, types: [java.io.InputStreamReader] */
        /* JADX WARN: Type inference failed for: r2v7 */
        /* JADX WARN: Type inference failed for: r2v8 */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Boolean doInBackground(java.lang.Void... r10) {
            /*
                r9 = this;
                java.lang.String r10 = "UTF-8"
                r0 = 0
                java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8e
                java.lang.String r2 = r9.mTimeAddress     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8e
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8e
                java.net.URLConnection r1 = r1.openConnection()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8e
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8e
                java.lang.String r2 = "POST"
                r1.setRequestMethod(r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                r2 = 1
                r1.setDoInput(r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                java.lang.String r2 = "Accept-Charset"
                r1.setRequestProperty(r2, r10)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                java.lang.String r2 = "connection"
                java.lang.String r3 = "close"
                r1.setRequestProperty(r2, r3)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                r1.connect()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                int r2 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                r3 = 200(0xc8, float:2.8E-43)
                if (r2 != r3) goto L76
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                java.io.InputStream r3 = r1.getInputStream()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                r2.<init>(r3, r10)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L86
                java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> La9
                r10.<init>(r2)     // Catch: java.lang.Exception -> L90 java.lang.Throwable -> La9
                com.jdjr.generalKeyboard.views.GeneralKeyboard r0 = com.jdjr.generalKeyboard.views.GeneralKeyboard.this     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                java.lang.String r3 = r10.readLine()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                long r3 = r3.longValue()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                r5 = 1000(0x3e8, double:4.94E-321)
                long r3 = r3 / r5
                com.jdjr.generalKeyboard.views.GeneralKeyboard.access$102(r0, r3)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                com.jdjr.generalKeyboard.views.GeneralKeyboard r0 = com.jdjr.generalKeyboard.views.GeneralKeyboard.this     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                com.wangyin.platform.CryptoUtils r3 = r0.mUtils     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                long r4 = r0.mCppHandler     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                long r6 = com.jdjr.generalKeyboard.views.GeneralKeyboard.access$100(r0)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                r3.setServerTime(r4, r6)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L74
                r10.close()     // Catch: java.lang.Exception -> L6d
                r2.close()     // Catch: java.lang.Exception -> L6d
                if (r1 == 0) goto L71
                r1.disconnect()     // Catch: java.lang.Exception -> L6d
                goto L71
            L6d:
                r10 = move-exception
                r10.printStackTrace()
            L71:
                return r0
            L72:
                r0 = move-exception
                goto Lad
            L74:
                r0 = r10
                goto L90
            L76:
                if (r1 == 0) goto L80
                r1.disconnect()     // Catch: java.lang.Exception -> L7c
                goto L80
            L7c:
                r10 = move-exception
                r10.printStackTrace()
            L80:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE
                return r10
            L83:
                r10 = move-exception
                r2 = r0
                goto L8b
            L86:
                r2 = r0
                goto L90
            L88:
                r10 = move-exception
                r1 = r0
                r2 = r1
            L8b:
                r0 = r10
                r10 = r2
                goto Lad
            L8e:
                r1 = r0
                r2 = r1
            L90:
                java.lang.Boolean r10 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> La9
                if (r0 == 0) goto L9a
                r0.close()     // Catch: java.lang.Exception -> L98
                goto L9a
            L98:
                r0 = move-exception
                goto La5
            L9a:
                if (r2 == 0) goto L9f
                r2.close()     // Catch: java.lang.Exception -> L98
            L9f:
                if (r1 == 0) goto La8
                r1.disconnect()     // Catch: java.lang.Exception -> L98
                goto La8
            La5:
                r0.printStackTrace()
            La8:
                return r10
            La9:
                r10 = move-exception
                r8 = r0
                r0 = r10
                r10 = r8
            Lad:
                if (r10 == 0) goto Lb5
                r10.close()     // Catch: java.lang.Exception -> Lb3
                goto Lb5
            Lb3:
                r10 = move-exception
                goto Lc0
            Lb5:
                if (r2 == 0) goto Lba
                r2.close()     // Catch: java.lang.Exception -> Lb3
            Lba:
                if (r1 == 0) goto Lc3
                r1.disconnect()     // Catch: java.lang.Exception -> Lb3
                goto Lc3
            Lc0:
                r10.printStackTrace()
            Lc3:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jdjr.generalKeyboard.views.GeneralKeyboard.TimeAsyncTask.doInBackground(java.lang.Void[]):java.lang.Boolean");
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (bool.booleanValue()) {
                GeneralKeyboard generalKeyboard = GeneralKeyboard.this;
                if (generalKeyboard.mCppHandler <= 0 || generalKeyboard.mServerTime <= 0) {
                    return;
                }
                GeneralKeyboard generalKeyboard2 = GeneralKeyboard.this;
                generalKeyboard2.mUtils.setServerTime(generalKeyboard2.mCppHandler, generalKeyboard2.mServerTime);
            }
        }
    }

    public GeneralKeyboard(Context context, boolean z) {
        super(context);
        this.maxInputLen = 50;
        this.mIsCipherMode = 0;
        this.cryptoAlg = "0";
        this.md5Attach = "1";
        this.isDegrade = "1";
        this.mCertificate = null;
        this.mServerTime = 0L;
        this.mSMCertificate = null;
        this.mIsKeyboardShown = false;
        this.mSB = new StringBuilder();
        this.mContext = context;
        KeyboardUiMode.setDark(z);
        CryptoUtils newInstance = CryptoUtils.newInstance(this.mContext.getApplicationContext());
        this.mUtils = newInstance;
        this.mCppHandler = newInstance.initializeKeyBoardCrypto();
        if (!SDKRequestDegradeManager.mUpdatedFunclist) {
            new SDKRequestDegradeManager(context.getApplicationContext(), "deviceID", CommonTools.getStringSharePreference(context, CommonTools.KEY_FUNCLIST, "11111010")).requestSDKDegradeResult();
        }
        if (this.mServerTime == 0) {
            new TimeAsyncTask().execute(new Void[0]);
            JDJRLog.i(TAG, "TimeAsyncTask");
        }
        ((Activity) getContext()).getWindow().setSoftInputMode(3);
        initCryptoArgument();
    }

    private void appendTextOnCipher(String str, boolean z, int i2, boolean z2) {
        for (int i3 = 0; i3 < str.length(); i3++) {
            try {
                if (this.mUtils.getInputDataLen(this.mCppHandler) < getMaxInputLen()) {
                    if (i2 == -1) {
                        this.mUtils.appenChar(this.mCppHandler, 0, str.substring(i3, i3 + 1), getInputLength());
                    } else {
                        this.mUtils.appenChar(this.mCppHandler, 0, str.substring(i3, i3 + 1), i2);
                        i2++;
                    }
                    handleBasicAppendCallback(str.substring(i3, i3 + 1), z, z2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    private void appendTextOnPlain(String str, boolean z, int i2, boolean z2) {
        for (int i3 = 0; i3 < str.length(); i3++) {
            try {
                if (this.mSB.length() < getMaxInputLen()) {
                    if (i2 == -1) {
                        this.mSB.append(str.substring(i3, i3 + 1));
                    } else {
                        this.mSB.insert(i2, str.substring(i3, i3 + 1));
                        i2++;
                    }
                    handleBasicAppendCallback(str.substring(i3, i3 + 1), z, z2);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    private void deleteCipherText(int i2) {
        try {
            if (this.mUtils.getInputDataLen(this.mCppHandler) > 0) {
                if (i2 == -1) {
                    this.mUtils.deleteChar(this.mCppHandler, 1, getInputLength());
                } else {
                    this.mUtils.deleteChar(this.mCppHandler, 1, i2);
                }
                handleBasicDeleteCallback();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void deletePlainText(int i2) {
        try {
            if (this.mSB.length() > 0) {
                if (i2 == -1) {
                    this.mSB.deleteCharAt(r2.length() - 1);
                } else {
                    StringBuilder sb = this.mSB;
                    int i3 = i2 - 1;
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    sb.deleteCharAt(i3);
                }
                handleBasicDeleteCallback();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String getAppendTextByCipher(String str, boolean z) {
        if (this.mUtils.getInputDataLen(this.mCppHandler) < getMaxInputLen()) {
            this.mUtils.appenChar(this.mCppHandler, 0, str, getInputLength());
            return getCurrentTextByCipher(z);
        }
        return null;
    }

    private String getAppendTextByPlain(String str, boolean z) {
        StringBuilder sb = this.mSB;
        if (sb == null || sb.length() >= getMaxInputLen()) {
            return null;
        }
        this.mSB.append(str);
        return getCurrentTextByPlain(z);
    }

    private String getCurrentTextByCipher(boolean z) {
        if (z) {
            byte[] tempInputData = this.mUtils.getTempInputData(this.mCppHandler);
            if (new String(JDJRSecureUtils.getErrorCode(tempInputData)).equals("00000")) {
                return new String(JDJRSecureUtils.getRetData(tempInputData));
            }
            return null;
        }
        return getSymbolText(this.mUtils.getInputDataLen(this.mCppHandler));
    }

    private String getCurrentTextByPlain(boolean z) {
        if (z) {
            return this.mSB.toString();
        }
        return getSymbolText(this.mSB.length());
    }

    private String getDeleteTextByCipher(boolean z) {
        try {
            if (this.mUtils.getInputDataLen(this.mCppHandler) > 0) {
                this.mUtils.deleteChar(this.mCppHandler, 1, getInputLength());
                return getCurrentTextByCipher(z);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String getDeleteTextByPlain(boolean z) {
        try {
            if (this.mSB.length() > 0) {
                this.mSB.deleteCharAt(r1.length() - 1);
                return getCurrentTextByPlain(z);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Matcher getMatcher(String str) {
        byte[] tempInputData = this.mUtils.getTempInputData(this.mCppHandler);
        return Pattern.compile(str).matcher(new String(JDJRSecureUtils.getErrorCode(tempInputData)).equals("00000") ? new String(JDJRSecureUtils.getRetData(tempInputData)) : "");
    }

    private int getVirtualHeight() {
        try {
            WindowManager windowManager = (WindowManager) this.mContext.getSystemService("window");
            Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Class.forName("android.view.Display").getMethod("getMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            return displayMetrics.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return BaseInfo.getScreenHeight();
        }
    }

    public void calculateButtonDimen() {
        this.mBottom = getVirtualHeight() - BaseInfo.getScreenHeight();
    }

    public boolean checkEqual(GeneralKeyboard generalKeyboard) {
        if (generalKeyboard == null) {
            return false;
        }
        return getCheckResult(this, generalKeyboard);
    }

    public boolean checkRegexFind(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return getMatcher(str).find();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean checkRegexMatch(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return getMatcher(str).matches();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void clearShownInput() {
        try {
            this.mUtils.deleteAllChar(this.mCppHandler);
            StringBuilder sb = this.mSB;
            if (sb != null) {
                sb.delete(0, sb.length());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            JDJRLog.i(TAG, "back key press");
            if (this.mIsKeyboardShown) {
                hide();
                BasicKeyboardCallback basicKeyboardCallback = this.basicKeyboardCallback;
                if (basicKeyboardCallback != null) {
                    basicKeyboardCallback.onHide();
                    return true;
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean getCheckResult(GeneralKeyboard generalKeyboard, GeneralKeyboard generalKeyboard2) {
        byte[] CheckPwdEqual = this.mUtils.CheckPwdEqual(generalKeyboard.mCppHandler, generalKeyboard2.mCppHandler);
        if (CheckPwdEqual == null || !new String(JDJRSecureUtils.getErrorCode(CheckPwdEqual)).equals("00000")) {
            return false;
        }
        return "1".equals(new String(JDJRSecureUtils.getRetData(CheckPwdEqual)));
    }

    public JDJRResultMessage getCryptoData() {
        byte[] retData;
        byte[] bArr;
        byte[] retData2;
        if (this.mIsCipherMode == 1 && this.isDegrade.equals("1")) {
            this.mUtils.setMD5Attach(this.mCppHandler, Integer.parseInt(this.md5Attach));
            if (this.cryptoAlg.equals("0")) {
                String str = this.mCertificate;
                if (str == null) {
                    bArr = this.mUtils.getCryptoInputData(this.mCppHandler, Base64.decode(EnvConfig.getKeyboardCert(this.mContext).getBytes(), 2));
                } else {
                    bArr = this.mUtils.getCryptoInputData(this.mCppHandler, str.getBytes());
                }
            } else if (this.cryptoAlg.equals("1")) {
                String str2 = this.mSMCertificate;
                if (str2 == null) {
                    bArr = this.mUtils.getCryptoInputData(this.mCppHandler, EnvConfig.getKeyboardCertGm(this.mContext).getBytes());
                } else {
                    bArr = this.mUtils.getCryptoInputData(this.mCppHandler, str2.getBytes());
                }
            } else {
                bArr = null;
            }
            if (JDJRSecureUtils.getErrorCode(bArr) != null && JDJRSecureUtils.getErrorCode(bArr).length > 0 && new String(JDJRSecureUtils.getErrorCode(bArr)).equals("00000") && (retData2 = JDJRSecureUtils.getRetData(bArr)) != null && retData2.length > 0) {
                return new JDJRResultMessage(retData2, "00000");
            }
        } else if (this.mIsCipherMode == 1 && this.isDegrade.equals("0")) {
            this.mUtils.setMD5Attach(this.mCppHandler, Integer.parseInt(this.md5Attach));
            byte[] cryptoInputDataDegrade = this.mUtils.getCryptoInputDataDegrade(this.mCppHandler, this.mSB.toString().getBytes());
            if (JDJRSecureUtils.getErrorCode(cryptoInputDataDegrade) != null && JDJRSecureUtils.getErrorCode(cryptoInputDataDegrade).length > 0 && new String(JDJRSecureUtils.getErrorCode(cryptoInputDataDegrade)).equals("00000") && (retData = JDJRSecureUtils.getRetData(cryptoInputDataDegrade)) != null && retData.length > 0) {
                return new JDJRResultMessage(retData, "00000");
            }
        } else {
            return new JDJRResultMessage(this.mSB.toString().getBytes(), "00000");
        }
        return new JDJRResultMessage("".getBytes(), KeyboardError.GET_CRYPTODATA_ERROR);
    }

    public String getCurrentAppendData(String str) {
        if (this.mIsCipherMode == 0) {
            return getAppendTextByPlain(str, true);
        }
        if (this.mIsPlainText) {
            if ("0".equals(this.isDegrade)) {
                return getAppendTextByPlain(str, true);
            }
            return getAppendTextByCipher(str, true);
        } else if ("0".equals(this.isDegrade)) {
            return getAppendTextByPlain(str, false);
        } else {
            return getAppendTextByCipher(str, false);
        }
    }

    public String getCurrentData() {
        if (this.mIsCipherMode == 0) {
            return getCurrentTextByPlain(true);
        }
        if (this.mIsPlainText) {
            if ("0".equals(this.isDegrade)) {
                return getCurrentTextByPlain(true);
            }
            return getCurrentTextByCipher(true);
        } else if ("0".equals(this.isDegrade)) {
            return getCurrentTextByPlain(false);
        } else {
            return getCurrentTextByCipher(false);
        }
    }

    public String getCurrentDeleteData() {
        if (this.mIsCipherMode == 0) {
            return getDeleteTextByPlain(true);
        }
        if (this.mIsPlainText) {
            if ("0".equals(this.isDegrade)) {
                return getDeleteTextByPlain(true);
            }
            return getDeleteTextByCipher(true);
        } else if ("0".equals(this.isDegrade)) {
            return getDeleteTextByPlain(false);
        } else {
            return getDeleteTextByCipher(false);
        }
    }

    public Rect getCurrentRect(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return rect;
    }

    public View getCurrentView() {
        return this.mRootView;
    }

    public int getInputLength() {
        if (this.mIsCipherMode != 0 && !"0".equals(this.isDegrade)) {
            return this.mUtils.getInputDataLen(this.mCppHandler);
        }
        StringBuilder sb = this.mSB;
        if (sb != null) {
            return sb.length();
        }
        return 0;
    }

    public int getMaxInputLen() {
        return this.maxInputLen;
    }

    public byte[] getSourceData() {
        return getResources().getString(R.string.security_test_tip).getBytes();
    }

    protected String getSymbolText(int i2) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append(ProxyConfig.MATCH_ALL_SCHEMES);
        }
        return stringBuffer.toString();
    }

    public String getTempCipherText() {
        byte[] tempInputData = this.mUtils.getTempInputData(this.mCppHandler);
        if (new String(JDJRSecureUtils.getErrorCode(tempInputData)).equals("00000")) {
            return EncryptUtils.encryptBySha256(new String(JDJRSecureUtils.getRetData(tempInputData)));
        }
        return null;
    }

    public void handleBaseKeyboardAppend(String str, int i2, boolean z) {
        if (this.mIsCipherMode == 0) {
            appendTextOnPlain(str, this.mIsPlainText, i2, z);
        } else if (this.mIsPlainText) {
            if ("0".equals(this.isDegrade)) {
                appendTextOnPlain(str, true, i2, z);
            } else {
                appendTextOnCipher(str, true, i2, z);
            }
        } else if ("0".equals(this.isDegrade)) {
            appendTextOnPlain(str, false, i2, z);
        } else {
            appendTextOnCipher(str, false, i2, z);
        }
    }

    public void handleBaseKeyboardDelete(int i2) {
        if (this.mIsCipherMode != 0 && !"0".equals(this.isDegrade)) {
            deleteCipherText(i2);
        } else {
            deletePlainText(i2);
        }
    }

    public void handleBasicAppendCallback(String str, boolean z, boolean z2) {
        BasicKeyboardCallback basicKeyboardCallback = this.basicKeyboardCallback;
        if (basicKeyboardCallback == null || !z2) {
            return;
        }
        if (z) {
            basicKeyboardCallback.onInputAppend(str);
            return;
        }
        if (str.length() <= 1) {
            str = ProxyConfig.MATCH_ALL_SCHEMES;
        }
        basicKeyboardCallback.onInputAppend(str);
    }

    public void handleBasicDeleteCallback() {
        BasicKeyboardCallback basicKeyboardCallback = this.basicKeyboardCallback;
        if (basicKeyboardCallback != null) {
            basicKeyboardCallback.onInputDelete();
        }
    }

    public void hide() {
        this.mIsKeyboardShown = false;
    }

    public void hideSystemKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
        if (inputMethodManager == null || activity.getCurrentFocus() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void initCryptoArgument() {
        String stringSharePreference = CommonTools.getStringSharePreference(getContext(), CommonTools.KEY_FUNCLIST, FUNC_LIST_DEFAULT_VALUE);
        if (stringSharePreference.length() >= 8) {
            this.isDegrade = stringSharePreference.substring(4, 5);
            this.md5Attach = stringSharePreference.substring(6, 7);
        }
    }

    public void releaseCppKeyboard() {
        this.mUtils.uninitializeKeyBoardcrypto(this.mCppHandler);
    }

    public void setBasicKeyboardCallback(BasicKeyboardCallback basicKeyboardCallback) {
        this.basicKeyboardCallback = basicKeyboardCallback;
    }

    public void setCertificate(String str) {
        this.mCertificate = str;
    }

    public void setCryptoAlg(String str) {
        this.mUtils.setCryptoAlgorithm(this.mCppHandler, Integer.parseInt(str));
    }

    public void setFunctionalKeyboardCallback(FunctionalKeyboardCallback functionalKeyboardCallback) {
        this.functionalKeyboardCallback = functionalKeyboardCallback;
    }

    public void setIsCipherMode(int i2) {
        this.mIsCipherMode = i2;
    }

    public void setIsShownPlain(boolean z) {
        this.mIsPlainText = z;
    }

    public void setMaxInputLen(int i2) {
        this.maxInputLen = i2;
    }

    public void setOKButtonEnabled(boolean z) {
    }

    public void setOkButtonText(CharSequence charSequence) {
    }

    public void setSMCertificate(String str) {
        this.mSMCertificate = str;
    }

    public void show(Activity activity) {
        if (activity == null) {
            return;
        }
        hideSystemKeyboard(activity);
        if (this.mIsKeyboardShown) {
            return;
        }
        View findViewById = activity.getWindow().findViewById(16908290);
        ViewsUtils.removeFromParent(this.mRootView);
        ViewsUtils.addToParent(findViewById, this.mRootView);
    }
}

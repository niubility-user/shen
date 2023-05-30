package com.jingdong.common.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.AesCbcCrypto;
import com.jd.lib.babel.task.viewkit.EventModelKey;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.appshare.R;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.ClipBoardPermissionHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdshare.cell.c;
import com.jingdong.sdk.jdshare.utils.g;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import de.greenrobot.event.EventBus;
import java.lang.ref.SoftReference;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDSharedCommandUtils {
    private static final String CLIPBOARD_MODULE_NAME = "\u4eac\u53e3\u4ee4";
    private static final String CLIPBOARD_TEXT = "\u4eac\u4e1c\u7533\u8bf7\u83b7\u53d6\u60a8\u7684\u526a\u5207\u677f\u5185\u5bb9\uff0c\u4ee5\u8bfb\u53d6\u4eac\u53e3\u4ee4\u5185\u5bb9\uff0c\u62d2\u7edd\u4e0d\u5f71\u54cd\u4f7f\u7528\u5176\u4ed6\u670d\u52a1\u3002";
    private static final String CLIPBOARD_TITLE = "\u526a\u5207\u677f\u6743\u9650\u7533\u8bf7";
    private static final int COMMAND_TYPE_CN = 3;
    private static final int COMMAND_TYPE_EN = 0;
    private static final String JD_SHARED_FROM_LOCAL = "jd_shared_from_local";
    private static final String JD_SHARED_MYSHARE_DATA = "jd_shared_myshare_data";
    private static final String JD_SHARED_MYSHARE_LAST_SCAN_PIC = "jd_shared_myshare_last_scan_pic";
    private static final String JD_SHARED_PICTORIAL_LAST_GEN_PIC = "jd_shared_pictorial_last_gen_pic";
    private static final String JD_SHARE_JCOMM_SPOT_CH = "$,%,\uffe5,@,\uff01,(-),%-@,#-@,#-%,\uffe5-%,%-!";
    private static JDSharedCommandUtils mInstance;
    private String cnRegex;
    private int commandType;
    private String engRegex;
    private String eventParamJson;
    private boolean isJKLDegrade;
    private boolean isWhiteActivity;
    private c.b mBuilder;
    private String mCmdMaxLengthCn;
    private c mDialog;
    private boolean mJComRecogniseSwitch;
    private JDCommandInfo mJDCommandInfo;
    private String mLastRequestCommand;
    private String mLastRequestInValidCommand;
    private char[] mOuterCharCnAft;
    private char[] mOuterCharCnPre;
    private String preCnRegex;
    private String preEngRegex;
    private String preSwitchRule;
    private ShareInfo shareInfo;
    private static final Pattern PATTERN_PURE_DIGIT = Pattern.compile("^[0-9]*$");
    private static long sCurrentClipDataTimeStamp = 0;
    private String key = "@L^f*R%z#t!E(";
    private String sKey = "5yKhoqodQjuHGlKZ";
    private String ivParameter = "7WwXmH2TKSCIEJQ3";
    private AtomicBoolean isJdShareWaitOutside = new AtomicBoolean(false);
    private AtomicBoolean isJdShareWaitContext = new AtomicBoolean(true);
    private SoftReference<BaseActivity> mContextSoftReference = new SoftReference<>(null);
    private AtomicBoolean mIsRequest = new AtomicBoolean(false);
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String regexRules = "$()*+.[?\\^{}|";
    private AtomicBoolean isDecoding = new AtomicBoolean(false);

    /* loaded from: classes6.dex */
    public static class ImgInfo {
        public int height;
        public String imgPath;
        public String timeStamp;
        public int width;
    }

    /* loaded from: classes6.dex */
    public static class JDCommandInfo {
        public String eventParamJson;
        public String headImg;
        public String img;
        public String jumpUrl;
        public String requestText;
        public String response;
        public String source;
        public String srv;
        public String title;
        public String userName;
    }

    /* loaded from: classes6.dex */
    public class RegexInfo {
        int engLength;
        int startLength;
        String regexStart = "";
        String regexEnd = "";

        RegexInfo() {
            JDSharedCommandUtils.this = r1;
        }
    }

    private JDSharedCommandUtils() {
    }

    private String buildPattern() {
        StringBuilder sb = new StringBuilder(128);
        sb.append('[');
        sb.append(this.mOuterCharCnPre);
        sb.append(']');
        sb.append("[\\u4e00-\\u9fa5]{8,");
        sb.append(this.mCmdMaxLengthCn);
        sb.append('}');
        sb.append('[');
        sb.append(this.mOuterCharCnAft);
        sb.append(']');
        return sb.toString();
    }

    private boolean checkValue(String str) {
        String config;
        try {
            getMobileConfigValue();
            if (!this.isJKLDegrade) {
                if (str.equals(this.mLastRequestInValidCommand) && TextUtils.equals(this.cnRegex, this.preCnRegex) && TextUtils.equals(this.engRegex, this.preEngRegex)) {
                    OKLog.d("JDSharedCommandUtils", "\u5f53\u524d\u53e3\u4ee4\u4e3a\u4e0a\u6b21\u5931\u6548\u53e3\u4ee4\uff0c\u4e0d\u8bf7\u6c42");
                    return false;
                }
                String str2 = this.cnRegex;
                this.preCnRegex = str2;
                this.preEngRegex = this.engRegex;
                if (Pattern.compile(str2).matcher(str).find()) {
                    this.commandType = 3;
                    return true;
                }
                Matcher matcher = Pattern.compile(this.engRegex).matcher(str);
                boolean z = false;
                while (matcher.find()) {
                    String group = matcher.group();
                    if (!SwitchQueryFetcher.getSwitchBooleanValue("isNumberJKLValid", false) && !TextUtils.isEmpty(group) && group.length() > 2) {
                        if (PATTERN_PURE_DIGIT.matcher(group.substring(1, group.length() - 1)).find()) {
                            return false;
                        }
                    }
                    z = true;
                }
                if (z) {
                    this.commandType = 0;
                    return true;
                }
            } else {
                if (ShareUtil.isUseSwitchQuery()) {
                    config = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.SWITCH_SHARE_JCOMM_SPOT_CH, "");
                } else {
                    config = JDMobileConfig.getInstance().getConfig("JDShare", "jcommSpotCh", SwitchQueryFetcher.SWITCH_SHARE_JCOMM_SPOT_CH);
                }
                if (config != null && config.length() < 1) {
                    return filterByRule("", str);
                }
                String[] split = config.split(DYConstants.DY_REGEX_COMMA);
                if (split.length < 1) {
                    return filterByRule("", str);
                }
                if (str.equals(this.mLastRequestInValidCommand) && TextUtils.equals(config, this.preSwitchRule)) {
                    OKLog.d("JDSharedCommandUtils", "\u5f53\u524d\u53e3\u4ee4\u4e3a\u4e0a\u6b21\u5931\u6548\u53e3\u4ee4\uff0c\u4e0d\u8bf7\u6c42");
                    return false;
                }
                this.preSwitchRule = config;
                for (int i2 = 0; i2 < split.length; i2++) {
                    if (!TextUtils.isEmpty(split[i2]) && filterByRule(split[i2], str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.d("JDSharedCommandUtils", "\u672c\u5730\u89e3\u6790\u53e3\u4ee4\u5931\u8d25" + e2.getMessage());
            ExceptionReporter.reportKeyShareException("customTryCatch", "", e2.getLocalizedMessage(), "");
        }
        OKLog.d("JDSharedCommandUtils", "\u672c\u5730\u8fc7\u6ee4\u5931\u8d25\uff0c\u672c\u6b21\u4e0d\u8bf7\u6c42\u7f51\u7edc");
        processInvalidData(str);
        return false;
    }

    public void clearClipboard() {
        copyValueToClipboard("");
    }

    private String convertSpecialChar(char c2) {
        StringBuilder sb = new StringBuilder();
        if (this.regexRules.contains(String.valueOf(c2))) {
            sb.append("\\");
        }
        sb.append(String.valueOf(c2));
        return sb.toString();
    }

    private void copyValueToClipboard(String str) {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) JdSdk.getInstance().getApplicationContext().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
            if (clipboardManager == null) {
                return;
            }
            clipboardManager.setPrimaryClip(ClipData.newPlainText("OcrText", str));
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.d("JDSharedCommandUtils", "\u83b7\u53d6\u526a\u8d34\u677f\u5931\u8d25");
        }
    }

    private RegexInfo createRegexRules(String str) {
        RegexInfo regexInfo = new RegexInfo();
        try {
            char[] charArray = str.toCharArray();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            if (str.contains("-")) {
                String[] split = str.split("-");
                if (split.length < 2) {
                    return regexInfo;
                }
                char[] charArray2 = split[0].toCharArray();
                char[] charArray3 = split[1].toCharArray();
                regexInfo.startLength = charArray2.length;
                for (int i3 = 0; i3 < regexInfo.startLength; i3++) {
                    sb.append(convertSpecialChar(charArray2[i3]));
                }
                regexInfo.engLength = charArray3.length;
                while (i2 < regexInfo.engLength) {
                    sb2.append(convertSpecialChar(charArray3[i2]));
                    i2++;
                }
            } else {
                regexInfo.startLength = charArray.length;
                regexInfo.engLength = charArray.length;
                while (i2 < charArray.length) {
                    sb.append(convertSpecialChar(charArray[i2]));
                    sb2.append(convertSpecialChar(charArray[i2]));
                    i2++;
                }
            }
            regexInfo.regexStart = sb.toString();
            regexInfo.regexEnd = sb2.toString();
        } catch (Exception unused) {
        }
        return regexInfo;
    }

    private Result decodeQR(String str) {
        Result result = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put(DecodeHintType.CHARACTER_SET, "utf-8");
        hashtable.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hashtable.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth / R2.attr.internalMinHeight;
        options.inSampleSize = i2;
        if (i2 <= 0) {
            options.inSampleSize = 1;
        }
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile.getWidth() < 1080) {
            decodeFile = scale(decodeFile, R2.attr.internalMinHeight);
        }
        int[] iArr = new int[decodeFile.getWidth() * decodeFile.getHeight()];
        decodeFile.getPixels(iArr, 0, decodeFile.getWidth(), 0, 0, decodeFile.getWidth(), decodeFile.getHeight());
        try {
            result = new QRCodeReader().decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(decodeFile.getWidth(), decodeFile.getHeight(), iArr))), hashtable);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!decodeFile.isRecycled()) {
            decodeFile.recycle();
        }
        return result;
    }

    private String encodeCn(char c2, boolean z) {
        char[] cArr = z ? this.mOuterCharCnPre : this.mOuterCharCnAft;
        int binarySearch = Arrays.binarySearch(com.jingdong.sdk.jdshare.utils.c.a, c2);
        if (binarySearch != -1) {
            return String.valueOf(cArr[binarySearch]);
        }
        throw new RuntimeException("Failed to generate cn codes");
    }

    private boolean filterByRule(String str, String str2) {
        String str3;
        String str4;
        int i2 = 5;
        int i3 = 1;
        if (this.mJComRecogniseSwitch) {
            Matcher matcher = Pattern.compile(buildPattern()).matcher(str2);
            while (matcher.find()) {
                String group = matcher.group();
                String valueOf = String.valueOf(group.charAt(0));
                String valueOf2 = String.valueOf(group.charAt(group.length() - 1));
                StringBuilder sb = new StringBuilder(group);
                sb.replace(0, 1, "");
                sb.replace(group.length() - 2, group.length() - 1, "");
                String b = com.jingdong.sdk.jdshare.utils.c.b(((Object) sb) + this.key);
                try {
                    str3 = encodeCn(b.charAt(1), true);
                    try {
                        str4 = encodeCn(b.charAt(5), false);
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        str4 = "";
                        if (!valueOf.equalsIgnoreCase(str3)) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    str3 = "";
                }
                if (!valueOf.equalsIgnoreCase(str3) && valueOf2.equalsIgnoreCase(str4)) {
                    this.commandType = 3;
                    return true;
                }
            }
        }
        long currentTimeMillis = System.currentTimeMillis();
        RegexInfo createRegexRules = createRegexRules(str);
        String str5 = createRegexRules.regexStart;
        String str6 = createRegexRules.regexEnd;
        OKLog.d("JDSharedCommandUtils", "\u53e3\u4ee4 : \u89c4\u5219 " + str5 + "---" + str6);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str5);
        sb2.append("[a-zA-Z0-9]{6,20}");
        sb2.append(str6);
        Matcher matcher2 = Pattern.compile(sb2.toString()).matcher(str2);
        while (matcher2.find()) {
            String group2 = matcher2.group();
            String substring = group2.substring(createRegexRules.startLength, group2.length() - createRegexRules.engLength);
            OKLog.d("JDSharedCommandUtils", "\u8bc6\u522b\u5230\u7684\u4eac\u4e1c\u53e3\u4ee4\uff1a" + substring);
            String valueOf3 = String.valueOf(substring.charAt(i3));
            String valueOf4 = String.valueOf(substring.charAt(i2));
            StringBuilder sb3 = new StringBuilder(substring);
            Matcher matcher3 = matcher2;
            sb3.replace(i3, 2, "");
            sb3.replace(4, 5, "");
            String b2 = com.jingdong.sdk.jdshare.utils.c.b(((Object) sb3) + this.key);
            String valueOf5 = String.valueOf(b2.charAt(1));
            String valueOf6 = String.valueOf(b2.charAt(5));
            if (valueOf3.toUpperCase().equals(valueOf5.toUpperCase()) && valueOf4.toUpperCase().equals(valueOf6.toUpperCase())) {
                OKLog.d("JDSharedCommandUtils", "\u53e3\u4ee4\uff1a" + substring + "    \u5224\u65ad\u6210\u529f\uff0c\u4e3a\u4eac\u53e3\u4ee4----\u4eac\u53e3\u4ee4\u89c4\u5219" + str5 + "---" + str6);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("\u89e3\u6790\u5355\u4e2a\u8017\u65f6==");
                sb4.append(System.currentTimeMillis() - currentTimeMillis);
                OKLog.d("JDSharedCommandUtils", sb4.toString());
                this.commandType = 0;
                return true;
            }
            OKLog.d("JDSharedCommandUtils", "\u53e3\u4ee4\uff1a" + substring + "    \u5224\u65ad\u5931\u8d25\uff0c\u4e0d\u4e3a\u4eac\u53e3\u4ee4");
            matcher2 = matcher3;
            i2 = 5;
            i3 = 1;
        }
        OKLog.d("JDSharedCommandUtils", "\u89e3\u6790\u5355\u4e2a\u8017\u65f6==" + (System.currentTimeMillis() - currentTimeMillis));
        return false;
    }

    private ImgInfo findLastImg(Context context) {
        ContentResolver contentResolver;
        int i2;
        int i3;
        ImgInfo imgInfo = new ImgInfo();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            int i4 = Build.VERSION.SDK_INT;
            int i5 = 0;
            Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, i4 >= 16 ? new String[]{"_data", "datetaken", "width", "height"} : new String[]{"_data", "datetaken"}, null, null, "date_added desc limit 1");
            if (query == null) {
                OKLog.d("JDSharedCommandUtils", "cursor = null");
                return imgInfo;
            }
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("_data");
                int columnIndex2 = query.getColumnIndex("datetaken");
                int i6 = -1;
                if (i4 >= 16) {
                    i6 = query.getColumnIndex("width");
                    i2 = query.getColumnIndex("height");
                } else {
                    i2 = -1;
                }
                String string = query.getString(columnIndex);
                long j2 = query.getLong(columnIndex2);
                if (i6 < 0 || i2 < 0) {
                    i3 = 0;
                } else {
                    i5 = query.getInt(i6);
                    i3 = query.getInt(i2);
                }
                OKLog.d("JDSharedCommandUtils", "data: " + string + " ,dateTaken: " + j2 + " ,width: " + i5 + " ,height: " + i3);
                imgInfo.imgPath = string;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(j2);
                imgInfo.timeStamp = sb.toString();
                imgInfo.width = i5;
                imgInfo.height = i3;
            }
            query.close();
        }
        return imgInfo;
    }

    private String getClipboardValue() {
        ClipData primaryClip;
        ClipDescription description;
        try {
            sCurrentClipDataTimeStamp = 0L;
            ClipboardManager clipboardManager = (ClipboardManager) JdSdk.getInstance().getApplicationContext().getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD);
            if (clipboardManager == null || (primaryClip = clipboardManager.getPrimaryClip()) == null) {
                return "";
            }
            if (Build.VERSION.SDK_INT >= 26 && (description = primaryClip.getDescription()) != null) {
                sCurrentClipDataTimeStamp = description.getTimestamp();
            }
            ClipData.Item itemAt = primaryClip.getItemAt(0);
            return (itemAt == null || itemAt.getText() == null) ? "" : itemAt.getText().toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.d("JDSharedCommandUtils", "\u83b7\u53d6\u526a\u8d34\u677f\u5931\u8d25");
            return "";
        }
    }

    public static JDSharedCommandUtils getInstance() {
        if (mInstance == null) {
            mInstance = new JDSharedCommandUtils();
        }
        return mInstance;
    }

    private String getLocalClipDate(String str) {
        return JdSdk.getInstance().getApplication().getSharedPreferences(JD_SHARED_FROM_LOCAL, 0).getString(str, "");
    }

    private void getMobileConfigValue() {
        String config;
        String config2;
        if (ShareUtil.isUseSwitchQuery()) {
            config = SwitchQueryFetcher.getSwitchStringValue("switchJComRecognise", "");
        } else {
            config = JDMobileConfig.getInstance().getConfig("JDShare", "switchJComRecognise", "switchType");
        }
        this.mJComRecogniseSwitch = TextUtils.equals("1", config);
        if (ShareUtil.isUseSwitchQuery()) {
            if (this.mJComRecogniseSwitch) {
                this.mCmdMaxLengthCn = SwitchQueryFetcher.getSwitchStringValue("cmdMaxLengthCn", "");
                this.mOuterCharCnPre = toSortedCharArray(SwitchQueryFetcher.getSwitchStringValue("outerCharCnPre", ""));
                this.mOuterCharCnAft = toSortedCharArray(SwitchQueryFetcher.getSwitchStringValue("outerCharCnAft", ""));
            }
            config2 = SwitchQueryFetcher.getSwitchStringValue("isJKLDegrade", "");
        } else {
            if (this.mJComRecogniseSwitch) {
                this.mCmdMaxLengthCn = JDMobileConfig.getInstance().getConfig("JDShare", "jComRecognise", "cmdMaxLengthCn");
                this.mOuterCharCnPre = toSortedCharArray(JDMobileConfig.getInstance().getConfig("JDShare", "jComRecognise", "outerCharCnPre"));
                this.mOuterCharCnAft = toSortedCharArray(JDMobileConfig.getInstance().getConfig("JDShare", "jComRecognise", "outerCharCnAft"));
            }
            config2 = JDMobileConfig.getInstance().getConfig("JDShare", "isJKLDegrade", "isDegraded");
        }
        boolean equals = TextUtils.equals("1", config2);
        this.isJKLDegrade = equals;
        if (equals) {
            return;
        }
        if (ShareUtil.isUseSwitchQuery()) {
            this.engRegex = SwitchQueryFetcher.getSwitchStringValue("engRegex", "");
            this.cnRegex = SwitchQueryFetcher.getSwitchStringValue("cnRegex", "");
        } else {
            this.engRegex = JDMobileConfig.getInstance().getConfig("JDShare", "JKLRegex", "engRegex");
            this.cnRegex = JDMobileConfig.getInstance().getConfig("JDShare", "JKLRegex", "cnRegex");
        }
        if (TextUtils.isEmpty(this.engRegex) || TextUtils.equals(this.engRegex, DYConstants.DY_NULL_STR)) {
            this.engRegex = "[$#%@(\uffe5\uff01][0-9A-Za-z]{6,20}[$%@!)\uffe5\uff01\"]";
        }
        if (TextUtils.isEmpty(this.cnRegex) || TextUtils.equals(this.cnRegex, DYConstants.DY_NULL_STR)) {
            this.cnRegex = "(?:(?:[2-9]{2}[\u65a4\u5305\u888b\u7bb1]){1}[\\u4e00-\\u9fa5]{2}[\u2602-\u27be\u2801-\u28ff]{1}){3}|(?:[\\u4e00-\\u9fa5]{4}[\u2602-\u27be\u2801-\u28ff]{1}){3}|(?:[\\u4e00-\\u9fa5]{4}[\u2602-\u27be\u2801-\u28ff]{1}){3}|[\\u4e00-\\u9fa5]{16}[\u2602-\u27be\u2801-\u28ff]{1}|[\u2602-\u27be\u2801-\u28ff]{1}[\\u4e00-\\u9fa5]{14}[\u2602-\u27be\u2801-\u28ff]{1}|(?:[\u2602-\u27be\u2801-\u28ff]{1}[\\u4e00-\\u9fa5]{6}){2}[\u2602-\u27be\u2801-\u28ff]{1}|(?:[0-9A-Za-z\u03b1-\u03c9\u0410-\u042f\u0430-\u044f\u00c0-\u017e]{3}[\\u4e00-\\u9fa5]{2}){2}[\u2602-\u27be\u2801-\u28ff]{1}|(?:[\u2602-\u27be\u2801-\u28ff]{1}[0-9A-Za-z\u03b1-\u03c9\u0410-\u042f\u0430-\u044f\u00c0-\u017e]{2}[\\u4e00-\\u9fa5]{2}){2}[\u2602-\u27be\u2801-\u28ff]{1}";
        }
    }

    private String getNewText(String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(this.cnRegex).matcher(str);
        while (matcher.find()) {
            sb.append(matcher.group());
            sb.append(LangUtils.SINGLE_SPACE);
        }
        Matcher matcher2 = Pattern.compile(this.engRegex).matcher(str);
        while (matcher2.find()) {
            sb.append(matcher2.group());
            sb.append(LangUtils.SINGLE_SPACE);
        }
        return sb.toString();
    }

    public void processError() {
        BaseActivity baseActivity = this.mContextSoftReference.get();
        if (baseActivity != null && processSpecialPage(baseActivity)) {
            OKLog.d("JDSharedCommandUtils", "\u7f51\u7edc\u9519\u8bef code===-2");
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, baseActivity.getString(R.string.jd_share_command_newwork_error), 1);
            clearClipboard();
        }
    }

    public void processInvalidData(String str) {
        this.mLastRequestInValidCommand = str;
    }

    private boolean processJDCommand(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getClipboardValue();
        }
        if (!TextUtils.isEmpty(str) && str.length() <= 500 && str.length() >= 5) {
            if (this.mIsRequest.get() && this.mLastRequestCommand.equals(str)) {
                return true;
            }
            if (str.equals(getLocalClipDate(JD_SHARED_MYSHARE_DATA))) {
                this.mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.3
                    {
                        JDSharedCommandUtils.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (JDSharedCommandUtils.this.isWhiteActivity) {
                            OKLog.d("JDSharedCommandUtils", "\u5f53\u524d\u53e3\u4ee4\u4e3a\u81ea\u5df1\u5206\u4eab\u751f\u6210\u7684\uff0c\u4e0d\u54cd\u5e94");
                            JDSharedCommandUtils.this.saveSpToLocal("", JDSharedCommandUtils.JD_SHARED_MYSHARE_DATA);
                            JDSharedCommandUtils.this.clearClipboard();
                        }
                    }
                }, 1000L);
                return false;
            } else if (checkValue(str)) {
                EventBus.getDefault().post(new BaseEvent("keyShareEvent", "1"));
                requestServerForCommand(str, true, "");
                return true;
            }
        }
        return false;
    }

    public boolean processJDShare() {
        return processJDCommand("");
    }

    public void processNullUrl() {
        BaseActivity baseActivity = this.mContextSoftReference.get();
        if (baseActivity != null && processSpecialPage(baseActivity)) {
            OKLog.d("JDSharedCommandUtils", "url\u4e3a\u7a7a,\u5f39toast");
            ExceptionReporter.reportKeyShareException("jumpUrlNull", "codeException", "jumpUrl\u4e0b\u53d1\u4e3a\u7a7a", "");
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, baseActivity.getString(R.string.jd_share_command_emptyurl_error), 1);
            clearClipboard();
        }
    }

    public void processOverdue(String str, String str2) {
        BaseActivity baseActivity = this.mContextSoftReference.get();
        if (baseActivity != null && processSpecialPage(baseActivity)) {
            OKLog.d("JDSharedCommandUtils", "\u53e3\u4ee4\u8fc7\u671f");
            if (TextUtils.isEmpty(str2)) {
                str2 = baseActivity.getString(R.string.jd_share_command_overdue);
            }
            ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, str2, 1);
            ShareInfo shareInfo = this.shareInfo;
            g.n("ShareJingwords_Expired_Expo", str, "", shareInfo != null ? shareInfo.getShareSource() : "");
            clearClipboard();
        }
    }

    public boolean processSpecialPage(BaseActivity baseActivity) {
        if (baseActivity != null && !baseActivity.isFinishing()) {
            String name = baseActivity.getClass().getName();
            TextUtils.isEmpty("");
            if (!TextUtils.isEmpty("com.jingdong.app.mall.main.MainActivity,com.jingdong.app.mall.ad.ADActivity,com.jingdong.app.mall.basic.ShareActivity,com.jingdong.app.mall.open.InterfaceActivity")) {
                return !"com.jingdong.app.mall.main.MainActivity,com.jingdong.app.mall.ad.ADActivity,com.jingdong.app.mall.basic.ShareActivity,com.jingdong.app.mall.open.InterfaceActivity".contains(name);
            }
        }
        return false;
    }

    public void processValidDialog() {
        try {
            safeShowDialog();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void requestServerForCommand(final String str, final boolean z, final String str2) {
        String config;
        String str3;
        this.mLastRequestCommand = str;
        if (ShareUtil.isUseSwitchQuery()) {
            config = SwitchQueryFetcher.getSwitchStringValue("substrSwitchType", "");
        } else {
            config = JDMobileConfig.getInstance().getConfig("JDShare", "substrSwitchType", "subSwitchType");
        }
        if (TextUtils.equals("1", config)) {
            str3 = getNewText(str);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            OKLog.d("substrSwitchType", config);
        } else {
            str3 = "";
        }
        JSONObject jSONObject = new JSONObject();
        final long j2 = 0;
        try {
            if (TextUtils.isEmpty(str3)) {
                str3 = str;
            }
            String encode = URLEncoder.encode(AesCbcCrypto.encrypt(str3, this.sKey, this.ivParameter.getBytes()), "utf-8");
            jSONObject.put("appCode", "jApp");
            jSONObject.put("text", encode);
            if (z && sCurrentClipDataTimeStamp > 0) {
                j2 = (System.currentTimeMillis() - sCurrentClipDataTimeStamp) / 1000;
                jSONObject.put("aliveMin", j2);
            }
            if (this.mJComRecogniseSwitch) {
                jSONObject.put("commandType", this.commandType);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportKeyShareException("applyErrorResolve", "codeException", "\u89e3\u5bc6\u5931\u8d25" + e2.getMessage() + " \u53e3\u4ee4\uff1a" + str, "");
            e2.printStackTrace();
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("jComExchange");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setEffect(0);
        httpSetting.setConnectTimeout(5000);
        httpSetting.setCacheMode(2);
        if (!TextUtils.isEmpty(jSONObject.toString())) {
            httpSetting.setJsonParams(JsonParser.parseParamsJsonFromString(jSONObject.toString()));
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.4
            {
                JDSharedCommandUtils.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(final HttpResponse httpResponse) {
                final JDJSONObject fastJsonObject;
                JDSharedCommandUtils.this.mIsRequest.set(false);
                if (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                    return;
                }
                JDSharedCommandUtils.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.4.1
                    {
                        AnonymousClass4.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String config2;
                        String optString = fastJsonObject.optString("code");
                        if ("0".equals(optString)) {
                            JDJSONObject jSONObject2 = fastJsonObject.getJSONObject("data");
                            if (jSONObject2 == null || jSONObject2.isEmpty()) {
                                return;
                            }
                            JDSharedCommandUtils.this.mJDCommandInfo = new JDCommandInfo();
                            JDSharedCommandUtils.this.mJDCommandInfo.jumpUrl = jSONObject2.optString(CartConstant.KEY_JUMPURL);
                            if (!TextUtils.isEmpty(JDSharedCommandUtils.this.mJDCommandInfo.jumpUrl)) {
                                JDSharedCommandUtils.this.mJDCommandInfo.img = jSONObject2.optString("img");
                                JDSharedCommandUtils.this.mJDCommandInfo.title = jSONObject2.optString("title");
                                JDSharedCommandUtils.this.mJDCommandInfo.userName = jSONObject2.optString("userName");
                                JDSharedCommandUtils.this.mJDCommandInfo.headImg = jSONObject2.optString("headImg");
                                JDSharedCommandUtils.this.mJDCommandInfo.eventParamJson = fastJsonObject.optString("eventParamJson");
                                JDSharedCommandUtils.this.eventParamJson = fastJsonObject.optString("eventParamJson");
                                JDCommandInfo jDCommandInfo = JDSharedCommandUtils.this.mJDCommandInfo;
                                StringBuilder sb = new StringBuilder();
                                sb.append(fastJsonObject.optString(EventModelKey.SRV));
                                sb.append(z ? "_0" : "_1");
                                jDCommandInfo.srv = sb.toString();
                                JDCommandInfo jDCommandInfo2 = JDSharedCommandUtils.this.mJDCommandInfo;
                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                jDCommandInfo2.requestText = str;
                                JDSharedCommandUtils.this.mJDCommandInfo.response = httpResponse.toString();
                                JDJSONObject parseObject = JDJSON.parseObject(JDSharedCommandUtils.this.eventParamJson);
                                if (parseObject != null) {
                                    JDSharedCommandUtils.this.mJDCommandInfo.source = parseObject.optString("source");
                                    parseObject.put("timeInterval", (Object) Long.valueOf(j2));
                                    JDSharedCommandUtils.this.eventParamJson = parseObject.toJSONString();
                                    JDSharedCommandUtils.this.mJDCommandInfo.eventParamJson = parseObject.toJSONString();
                                }
                                if (ShareUtil.isUseSwitchQuery()) {
                                    config2 = SwitchQueryFetcher.getSwitchStringValue("dialogTimeSwitch", "");
                                } else {
                                    config2 = JDMobileConfig.getInstance().getConfig("JDShare", "dialogTimeSwitch", "effectTime");
                                }
                                String str4 = "time:" + j2 + "  effectTime\uff1a" + config2;
                                try {
                                    if (!TextUtils.isEmpty(config2) && j2 > Integer.parseInt(config2)) {
                                        g.l("ShareJingwords_Intercepted_Expo", "", "", JDSharedCommandUtils.this.eventParamJson);
                                        return;
                                    }
                                } catch (Exception e3) {
                                    String str5 = "Exception\uff1a" + e3.toString();
                                }
                                EventBus.getDefault().post(new BaseEvent("keyShareEvent", "2"));
                                JDSharedCommandUtils.this.processValidDialog();
                            } else {
                                AnonymousClass4 anonymousClass42 = AnonymousClass4.this;
                                if (z) {
                                    JDSharedCommandUtils.this.processNullUrl();
                                    return;
                                }
                                return;
                            }
                        } else {
                            AnonymousClass4 anonymousClass43 = AnonymousClass4.this;
                            JDSharedCommandUtils.this.processInvalidData(str);
                            ExceptionReporter.reportKeyShareException("applyErrorApiData", "codeException", "\u53e3\u4ee4\u65e0\u6548 Code:" + optString, "");
                            OKLog.d("JDSharedCommandUtils", "\u53e3\u4ee4\u65e0\u6548 \u5ba2\u6237\u7aef\u4e0d\u505a\u5904\u7406");
                        }
                        AnonymousClass4 anonymousClass44 = AnonymousClass4.this;
                        if (z) {
                            return;
                        }
                        JDSharedCommandUtils.this.saveSpToLocal(str2, JDSharedCommandUtils.JD_SHARED_MYSHARE_LAST_SCAN_PIC);
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(final HttpError httpError) {
                final JDJSONObject fastJsonObject;
                JDSharedCommandUtils.this.mIsRequest.set(false);
                HttpResponse httpResponse = httpError.getHttpResponse();
                if (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                    return;
                }
                JDSharedCommandUtils.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.4.2
                    {
                        AnonymousClass4.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        int errorCode = httpError.getErrorCode();
                        int jsonCode = httpError.getJsonCode();
                        String optString = fastJsonObject.optString("message");
                        if (jsonCode == 3) {
                            Context context = (Context) JDSharedCommandUtils.this.mContextSoftReference.get();
                            if (TextUtils.isEmpty(optString)) {
                                optString = "\u767b\u5f55\u540e\u624d\u53ef\u6253\u5f00\u53e3\u4ee4\u54e6~";
                            }
                            ToastUtils.showToastInCenter(context, optString, 1);
                            JDSharedCommandUtils.this.clearClipboard();
                        } else if (errorCode == 3) {
                            String str4 = httpError.getJsonCode() + "";
                            if ("-2".equals(str4)) {
                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                if (z) {
                                    JDSharedCommandUtils.this.processError();
                                }
                                ExceptionReporter.reportKeyShareException("resolveKey", "codeException", str4, str);
                            } else if ("1003".equals(str4)) {
                                ExceptionReporter.reportKeyShareException("applyErrorExpired", "codeException", "\u53e3\u4ee4\u8fc7\u671f Code:" + str4, str);
                                StringBuilder sb = new StringBuilder();
                                sb.append(fastJsonObject.optString(EventModelKey.SRV));
                                sb.append(z ? "_0" : "_1");
                                String sb2 = sb.toString();
                                AnonymousClass4 anonymousClass42 = AnonymousClass4.this;
                                if (z) {
                                    JDSharedCommandUtils.this.processOverdue(sb2, optString);
                                } else {
                                    JDSharedCommandUtils.this.saveSpToLocal(str2, JDSharedCommandUtils.JD_SHARED_MYSHARE_LAST_SCAN_PIC);
                                }
                            } else {
                                if (!TextUtils.isEmpty(optString)) {
                                    ToastUtils.showToastInCenter((Context) JDSharedCommandUtils.this.mContextSoftReference.get(), optString, 1);
                                }
                                ExceptionReporter.reportKeyShareException("resolveKey", "codeException", str4, str);
                            }
                        } else {
                            if (!TextUtils.isEmpty(optString)) {
                                ToastUtils.showToastInCenter((Context) JDSharedCommandUtils.this.mContextSoftReference.get(), optString, 1);
                            }
                            ExceptionReporter.reportKeyShareException("resolveKey", "netException", errorCode + "", str);
                        }
                        OKLog.d("JDSharedCommandUtils", "\u7f51\u7edc\u9519\u8bef " + httpError.getErrorCode());
                    }
                });
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                JDSharedCommandUtils.this.mIsRequest.set(true);
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void safeShowDialog() {
        BaseActivity baseActivity = this.mContextSoftReference.get();
        if (baseActivity != null && !baseActivity.isFinishing()) {
            if (!this.isJdShareWaitOutside.get() && processSpecialPage(baseActivity)) {
                c.b bVar = this.mBuilder;
                if (bVar != null) {
                    bVar.m();
                }
                if (this.mJDCommandInfo == null || baseActivity.interceptJDShareCommand(null)) {
                    return;
                }
                c.b bVar2 = new c.b(baseActivity);
                bVar2.s(this.mJDCommandInfo);
                bVar2.t(this.shareInfo);
                this.mBuilder = bVar2;
                c k2 = bVar2.k();
                this.mDialog = k2;
                Window window = k2.getWindow();
                if (window == null) {
                    return;
                }
                window.setGravity(17);
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                window.setAttributes(attributes);
                this.mDialog.show();
                JDJSONObject parseObject = JDJSON.parseObject(this.eventParamJson);
                if (parseObject != null) {
                    parseObject.put("keyid", (Object) parseObject.optString("skuId"));
                    g.n("ShareJingwords_OpenPanel_Expo", this.mJDCommandInfo.srv, "", g.h(this.shareInfo, parseObject.toJSONString()));
                }
                this.mJDCommandInfo = null;
                clearClipboard();
                this.mContextSoftReference.clear();
                return;
            }
            return;
        }
        setJdShareCommandWaitContext(true);
    }

    public void saveSpToLocal(String str, String str2) {
        SharedPreferences.Editor edit = JdSdk.getInstance().getApplication().getSharedPreferences(JD_SHARED_FROM_LOCAL, 0).edit();
        edit.putString(str2, str);
        edit.apply();
    }

    private Bitmap scale(Bitmap bitmap, int i2) {
        float width = (i2 * 1.0f) / bitmap.getWidth();
        Matrix matrix = new Matrix();
        matrix.postScale(width, width);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void setJdShareCommandWaitContext(boolean z) {
        this.isJdShareWaitContext.set(z);
    }

    public static char[] toSortedCharArray(String str) {
        if (str == null || str.isEmpty()) {
            return new char[0];
        }
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return charArray;
    }

    public void notifyJDShareOpen() {
        if (this.isJdShareWaitOutside.get()) {
            this.isJdShareWaitOutside.set(false);
            if (Looper.myLooper() == Looper.getMainLooper()) {
                processValidDialog();
            } else {
                this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.1
                    {
                        JDSharedCommandUtils.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JDSharedCommandUtils.this.processValidDialog();
                    }
                });
            }
        }
    }

    public void resumeForJDCommand(final BaseActivity baseActivity) {
        String config;
        if (JDPrivacyHelper.isAcceptPrivacy(baseActivity)) {
            if (ShareUtil.isUseSwitchQuery()) {
                config = SwitchQueryFetcher.getSwitchStringValue("jcommExchangeSwitch", DYConstants.DY_TRUE);
            } else {
                config = JDMobileConfig.getInstance().getConfig("JDShare", "jComRecognise", SwitchQueryFetcher.SWITCH_SHARE_JCOMM_EXCHANGE_SWITCH);
            }
            if (!DYConstants.DY_TRUE.equals(config)) {
                ExceptionReporter.reportKeyShareException("applyErrorSwitch", "codeException", "\u5f00\u5173\u5173\u95ed", "");
            } else if (!BackForegroundWatcher.getInstance().isAppForeground()) {
                OKLog.d("JDSharedCommandUtils", "isAppForeground = false");
            } else {
                ClipBoardPermissionHelper.handleClipBoardPermissionDialog(baseActivity, CLIPBOARD_MODULE_NAME, CLIPBOARD_TITLE, CLIPBOARD_TEXT, new ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.2
                    {
                        JDSharedCommandUtils.this = this;
                    }

                    @Override // com.jingdong.common.permission.ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack
                    public void onDenied() {
                        super.onDenied();
                        OKLog.d("JDSharedCommandUtils", "onDenied");
                    }

                    @Override // com.jingdong.common.permission.ClipBoardPermissionHelper.ClipBoardPermissionResultCallBack
                    public void onGranted() {
                        super.onGranted();
                        OKLog.d("JDSharedCommandUtils", "onGranted");
                        if (JDSharedCommandUtils.this.processSpecialPage(baseActivity)) {
                            if (Build.VERSION.SDK_INT > 28) {
                                JDSharedCommandUtils.this.mHandler.post(new Runnable() { // from class: com.jingdong.common.utils.JDSharedCommandUtils.2.1
                                    {
                                        AnonymousClass2.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        JDSharedCommandUtils.this.processJDShare();
                                    }
                                });
                            } else {
                                JDSharedCommandUtils.this.processJDShare();
                            }
                        }
                    }
                });
            }
        }
    }

    public void saveShareText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        copyValueToClipboard(str);
        saveSpToLocal(str, JD_SHARED_MYSHARE_DATA);
    }

    public boolean searchForJDCommand(BaseActivity baseActivity, String str, String str2) {
        if (JDPrivacyHelper.isAcceptPrivacy(baseActivity) && PasteStateRouterImpl.getClipPasteStatusValue() && processSpecialPage(baseActivity)) {
            return processJDCommand(str);
        }
        return false;
    }

    public void setJdShareCommandWaitOutside() {
        this.isJdShareWaitOutside.set(true);
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public void upDateLastGenPictorialImg(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        saveSpToLocal(str, JD_SHARED_PICTORIAL_LAST_GEN_PIC);
    }

    public void updateJDCommandContext(BaseActivity baseActivity) {
        SoftReference<BaseActivity> softReference = this.mContextSoftReference;
        if (softReference != null) {
            softReference.clear();
        }
        boolean processSpecialPage = processSpecialPage(baseActivity);
        this.isWhiteActivity = processSpecialPage;
        if (processSpecialPage) {
            this.mContextSoftReference = new SoftReference<>(baseActivity);
            if (!this.isJdShareWaitContext.get() || this.isJdShareWaitOutside.get()) {
                return;
            }
            notifyJDShareOpen();
        }
    }
}

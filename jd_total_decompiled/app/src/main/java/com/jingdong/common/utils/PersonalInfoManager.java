package com.jingdong.common.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.R;
import com.jingdong.common.database.table.CommentEditTable;
import com.jingdong.common.deeplinkhelper.DeepLinkIntelligentAssistantHelper;
import com.jingdong.common.entity.personal.EmployeeSetting;
import com.jingdong.common.entity.personal.FlashInfo;
import com.jingdong.common.entity.personal.GongyiDataInfo;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.entity.personal.SchoolDataInfo;
import com.jingdong.common.entity.personal.UserPlusEntity;
import com.jingdong.common.entity.personal.UserPlusProduct;
import com.jingdong.common.entity.personal.XbCreditInfo;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.i18n.UnI18NUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JsonEncryptUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PersonalInfoManager {
    public static final String DES_KEY = "%#Z&P!)9";
    public static final int FROM_SOURCE_MY_JD = 1;
    public static final int FROM_SOURCE_NO_MY_JD = 0;
    private static String TAG = "userInfoSnsManager";
    private static PersonalInfoManager instance;
    private BirthdayActivity birthdayActivity;
    @Nullable
    private EmployeeSetting employeeSetting;
    private int enc;
    private FirstBuyGiude firstBuyGiude;
    private GongyiDataInfo gongyiDataInfo;
    private String headPicEditText;
    private HomePage homePage;
    private boolean interview;
    private boolean isSupportCredit;
    @Nullable
    private FlashInfo jingXiangZhiFlashInfo;
    private boolean jingXiangZhiFlashSwitch;
    public CustomerService mCustomerService;
    private String nicknameEditText;
    public String noModifyText;
    public boolean plusBannerShow;
    public String plusImagAbtest;
    public String plusImagUrl;
    public String plusSkipUrl;
    private String plusTestId;
    private String plusText;
    public String plusWareABtest;
    public List<UserPlusProduct> plusWareInfoList;
    private SchoolDataInfo schoolDataInfo;
    private int score;
    private String selectedTabIcon;
    private String unSelectedTabIcon;
    private String userData;
    private UserInfoSns userInfoSns;
    private String userLevelClass;
    private String userLevelIcon;
    public UserPlusEntity userPlusEntity;
    public String userPlusIcon;
    private boolean userPlusStatus;
    private XbCreditInfo xiaoBaiCreditInfo;
    private String xiaoBaiMessage;
    private final Object lock = new Object();
    private int bannerType = 0;
    private final BroadcastReceiver loginAndExitReceiver = new BroadcastReceiver() { // from class: com.jingdong.common.utils.PersonalInfoManager.1
        {
            PersonalInfoManager.this = this;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.jingdong.action.user.login.in".equals(action)) {
                if (LoginUserBase.hasLogin()) {
                    PersonalInfoManager.requestPersonalInfo(HttpGroupUtils.getHttpGroupaAsynPool(), null, 1, 1, false);
                }
            } else if ("com.jingdong.action.user.login.out".equals(action)) {
                PersonalInfoManager.this.resetData();
            }
        }
    };
    private boolean available = false;
    private List<Lable> lableList = new ArrayList();

    /* loaded from: classes6.dex */
    public static class BirthdayActivity {
        public String backImage;
        public boolean isBirthday;
        public String jumplink;
        public String rightIcon;
        public String text;
        public String textColor;
    }

    /* loaded from: classes6.dex */
    public static class CustomerService {
        public boolean isExclusive;
        public String url;

        public CustomerService(JDJSONObject jDJSONObject) {
            this.isExclusive = jDJSONObject.optBoolean("isExclusive");
            this.url = jDJSONObject.optString("url");
        }
    }

    /* loaded from: classes6.dex */
    public static class FirstBuyGiude {
        public String giftH5Url;
        public String jumpUrl;
        public String type;
    }

    /* loaded from: classes6.dex */
    public static class HomePage {
        public int uclass;
    }

    /* loaded from: classes6.dex */
    public static class Lable {
        public double amount;
        public boolean displayIcon;
        public String key;
        public String name;
    }

    /* loaded from: classes6.dex */
    public interface PersonalInfoRequestListener {
        void onEnd();

        void onError();
    }

    /* loaded from: classes6.dex */
    public static class UserInfoSns {
        public String birthday;
        public boolean familyNum;
        public String homePage;
        public HomePage homePageObject;
        public String imgUrl;
        public String petName;
        public String petNameTips;
        public int sex = 2;
        public String uclass;
        public String unickName;
        public String userName;
    }

    private PersonalInfoManager() {
    }

    public static synchronized PersonalInfoManager getInstance() {
        PersonalInfoManager personalInfoManager;
        synchronized (PersonalInfoManager.class) {
            if (instance == null) {
                instance = new PersonalInfoManager();
            }
            personalInfoManager = instance;
        }
        return personalInfoManager;
    }

    private Lable getLable(String str) {
        if (this.available) {
            synchronized (this.lock) {
                List<Lable> list = this.lableList;
                if (list != null) {
                    for (Lable lable : list) {
                        if (lable != null && lable.key.equals(str)) {
                            return lable;
                        }
                    }
                }
                return null;
            }
        }
        throw new IllegalStateException("personal info uninitialized");
    }

    private int getLableValue(String str) {
        if (this.available) {
            synchronized (this.lock) {
                List<Lable> list = this.lableList;
                if (list != null) {
                    for (Lable lable : list) {
                        if (lable != null && lable.key.equals(str)) {
                            return (int) lable.amount;
                        }
                    }
                }
                return 0;
            }
        }
        throw new IllegalStateException("personal info uninitialized");
    }

    private void mainAppParse(JDJSONObject jDJSONObject, int i2) {
        this.score = jDJSONObject.optInt(CommentEditTable.TB_COLUMN_SCORE, -1);
        this.interview = jDJSONObject.optBoolean("interview", false);
        this.isSupportCredit = jDJSONObject.optBoolean("isSupportCredit", false);
        this.userPlusStatus = jDJSONObject.optBoolean("userPlusStatus", false);
        this.userLevelIcon = jDJSONObject.optString("userLevelIcon");
        this.plusText = jDJSONObject.optString("plusText");
        PersonalUtsManager.getInstance().setUts(jDJSONObject.optString("uts"));
        this.userPlusIcon = jDJSONObject.optString("userPlusIcon");
        this.userLevelClass = jDJSONObject.optString("userLevelClass");
        this.selectedTabIcon = jDJSONObject.optString("selectedTabIcon");
        this.unSelectedTabIcon = jDJSONObject.optString("unSelectedTabIcon");
        JDJSONObject jSONObject = jDJSONObject.getJSONObject("birthdayActivity");
        if (jSONObject != null) {
            if (this.birthdayActivity == null) {
                this.birthdayActivity = new BirthdayActivity();
            }
            this.birthdayActivity.isBirthday = jSONObject.optBoolean("isBirthday");
            this.birthdayActivity.backImage = jSONObject.optString("backImage");
            this.birthdayActivity.jumplink = jSONObject.optString("jumplink");
            this.birthdayActivity.rightIcon = jSONObject.optString("rightIcon");
            this.birthdayActivity.text = jSONObject.optString("text");
            this.birthdayActivity.textColor = jSONObject.optString(DYConstants.DY_TEXT_COLOR);
        } else if (this.birthdayActivity != null) {
            this.birthdayActivity = null;
        }
        JDJSONObject jSONObject2 = jDJSONObject.getJSONObject("firstBuyGiude");
        if (jSONObject2 != null) {
            FirstBuyGiude firstBuyGiude = new FirstBuyGiude();
            this.firstBuyGiude = firstBuyGiude;
            firstBuyGiude.giftH5Url = jSONObject2.optString("giftH5Url");
            this.firstBuyGiude.type = jSONObject2.optString("type");
            this.firstBuyGiude.jumpUrl = jSONObject2.optString(CartConstant.KEY_JUMPURL);
        } else {
            this.firstBuyGiude = null;
        }
        if (i2 == 1) {
            this.plusSkipUrl = jDJSONObject.optString("plusSkipUrl");
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("plusWareInfoList");
            if (optJSONArray != null) {
                this.plusWareInfoList = JDJSON.parseArray(optJSONArray.toString(), UserPlusProduct.class);
            } else {
                this.plusWareInfoList = null;
            }
        }
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject(PersonalConstants.FUNCTION_ID_CUSTOMERSERVICE);
        if (optJSONObject != null) {
            this.mCustomerService = new CustomerService(optJSONObject);
        }
        this.plusBannerShow = jDJSONObject.optBoolean("plusBannerShow");
        this.bannerType = jDJSONObject.optInt("bannerType", 0);
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("plusSetting");
        if (optJSONObject2 != null) {
            this.userPlusEntity = (UserPlusEntity) JDJSON.parseObject(optJSONObject2.toString(), UserPlusEntity.class);
        }
        this.plusImagAbtest = jDJSONObject.optString("plusImagAbtest");
        this.plusImagUrl = jDJSONObject.optString("plusImagUrl");
        this.plusWareABtest = jDJSONObject.optString("plusWareABtest");
        this.plusTestId = jDJSONObject.optString("plusTestId");
        JDJSONObject optJSONObject3 = jDJSONObject.optJSONObject("employeeSetting");
        if (optJSONObject3 != null) {
            this.employeeSetting = (EmployeeSetting) JDJSON.parseObject(optJSONObject3.toString(), EmployeeSetting.class);
        } else {
            this.employeeSetting = null;
        }
        this.jingXiangZhiFlashSwitch = jDJSONObject.optBoolean("jingXiangZhiFlashSwitch");
        JDJSONObject optJSONObject4 = jDJSONObject.optJSONObject("jingXiangZhiFlashInfo");
        if (optJSONObject4 != null) {
            this.jingXiangZhiFlashInfo = (FlashInfo) JDJSON.parseObject(optJSONObject4.toString(), FlashInfo.class);
        }
        parsePersonaJson(jDJSONObject);
        this.userData = jDJSONObject.optString("userData");
    }

    private JDJSONArray parseArrayProxy(String str, JDJSONObject jDJSONObject) {
        try {
            return JDJSON.parseArray(PersonalDesCommonUtils.commonDecrypt(jDJSONObject.optString(str), this.enc));
        } catch (Exception unused) {
            return null;
        }
    }

    private void parsePersonaJson(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.xiaoBaiMessage = jDJSONObject.optString("xiaoBaiMessage");
        JDJSONObject optJSONObject = jDJSONObject.optJSONObject("gongyiDataInfo");
        if (optJSONObject != null) {
            this.gongyiDataInfo = (GongyiDataInfo) JDJSON.parseObject(optJSONObject.toString(), GongyiDataInfo.class);
        } else {
            this.gongyiDataInfo = null;
        }
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("schoolDataInfo");
        if (optJSONObject2 != null) {
            this.schoolDataInfo = (SchoolDataInfo) JDJSON.parseObject(optJSONObject2.toString(), SchoolDataInfo.class);
        } else {
            this.schoolDataInfo = null;
        }
        JDJSONObject optJSONObject3 = jDJSONObject.optJSONObject("xbCredit");
        if (optJSONObject3 != null) {
            this.xiaoBaiCreditInfo = (XbCreditInfo) JDJSON.parseObject(optJSONObject3.toString(), XbCreditInfo.class);
        } else {
            this.xiaoBaiCreditInfo = null;
        }
        this.headPicEditText = jDJSONObject.optString("headPicEditText");
        this.nicknameEditText = jDJSONObject.optString("nicknameEditText");
    }

    private JDJSONObject parseProxy(String str, JDJSONObject jDJSONObject) {
        try {
            Object parse = JDJSON.parse(PersonalDesCommonUtils.commonDecrypt(jDJSONObject.optString(str), this.enc));
            if (parse instanceof JDJSONObject) {
                return (JDJSONObject) parse;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void requestPersonalInfo(HttpGroup httpGroup, PersonalInfoRequestListener personalInfoRequestListener) {
        requestPersonalInfo(httpGroup, personalInfoRequestListener, 2);
    }

    private void updateLabelList(JDJSONArray jDJSONArray) {
        synchronized (this.lock) {
            List<Lable> list = this.lableList;
            if (list != null) {
                list.clear();
            }
        }
        if (jDJSONArray != null) {
            int size = jDJSONArray.size();
            synchronized (this.lock) {
                for (int i2 = 0; i2 < size; i2++) {
                    Object obj = jDJSONArray.get(i2);
                    if (obj instanceof JDJSONObject) {
                        JDJSONObject jDJSONObject = (JDJSONObject) obj;
                        Lable lable = new Lable();
                        lable.amount = jDJSONObject.optDouble("amount");
                        lable.key = jDJSONObject.optString("key");
                        lable.name = jDJSONObject.optString("name");
                        lable.displayIcon = jDJSONObject.optBoolean("displayIcon");
                        List<Lable> list2 = this.lableList;
                        if (list2 != null) {
                            list2.add(lable);
                        }
                    }
                }
            }
        }
    }

    public String getAvatarUrl() {
        UserInfoSns userInfoSns = this.userInfoSns;
        return userInfoSns == null ? "" : userInfoSns.imgUrl;
    }

    public int getBannerType() {
        return this.bannerType;
    }

    public BirthdayActivity getBirthdayActivity() {
        return this.birthdayActivity;
    }

    public String getEmployeeImageUrl() {
        EmployeeSetting employeeSetting = this.employeeSetting;
        if (employeeSetting != null) {
            return employeeSetting.employeeImageUrl;
        }
        return null;
    }

    public String getEmployeeMUrl() {
        EmployeeSetting employeeSetting = this.employeeSetting;
        if (employeeSetting != null) {
            return employeeSetting.employeeMUrl;
        }
        return null;
    }

    public String getEmployeeText() {
        EmployeeSetting employeeSetting = this.employeeSetting;
        if (employeeSetting != null) {
            return employeeSetting.employeeText;
        }
        return null;
    }

    public int getEnc() {
        return this.enc;
    }

    public FirstBuyGiude getFirstBuyGiude() {
        return this.firstBuyGiude;
    }

    public int getGender() {
        UserInfoSns userInfoSns = this.userInfoSns;
        if (userInfoSns == null) {
            return 2;
        }
        return userInfoSns.sex;
    }

    public int getGiftCardNumber() {
        return getLableValue("giftCard");
    }

    public int getGiftECardNumber() {
        return getLableValue("giftECard");
    }

    public GongyiDataInfo getGongyiDataInfo() {
        return this.gongyiDataInfo;
    }

    public String getHeadPicEditText() {
        return this.headPicEditText;
    }

    @Nullable
    public FlashInfo getJingXiangZhiFlashInfo() {
        return this.jingXiangZhiFlashInfo;
    }

    public List<Lable> getLablesList() {
        if (this.available) {
            return this.lableList;
        }
        throw new IllegalStateException("personal info uninitialized");
    }

    public String getNickName() {
        UserInfoSns userInfoSns = this.userInfoSns;
        if (userInfoSns == null) {
            return "";
        }
        if (TextUtils.isEmpty(userInfoSns.petName)) {
            return !TextUtils.isEmpty(this.userInfoSns.userName) ? this.userInfoSns.userName : "";
        }
        return this.userInfoSns.petName;
    }

    public String getNicknameEditText() {
        return this.nicknameEditText;
    }

    public String getPlusTestId() {
        return this.plusTestId;
    }

    public String getPlusText() {
        return TextUtils.isEmpty(this.plusText) ? "" : this.plusText;
    }

    public String getPlusWareABtest() {
        return this.plusWareABtest;
    }

    public String getQiXiangZhiText() {
        EmployeeSetting employeeSetting = this.employeeSetting;
        if (employeeSetting != null) {
            return employeeSetting.qiXiangZhiText;
        }
        return null;
    }

    public SchoolDataInfo getSchoolDataInfo() {
        return this.schoolDataInfo;
    }

    public int getScore() {
        return this.score;
    }

    public String getSelectedTabIcon() {
        return TextUtils.isEmpty(this.selectedTabIcon) ? "" : this.selectedTabIcon;
    }

    public String getSmallUserPlusUrl() {
        UserPlusEntity userPlusEntity = this.userPlusEntity;
        return userPlusEntity != null ? userPlusEntity.plusHeadSmallBkImg : "";
    }

    public String getUclass() {
        String string = JdSdk.getInstance().getApplication().getString(R.string.personal_visitor);
        UserInfoSns userInfoSns = this.userInfoSns;
        return (userInfoSns == null || TextUtils.isEmpty(userInfoSns.uclass)) ? string : this.userInfoSns.uclass;
    }

    public String getUnSelectedTabIcon() {
        return TextUtils.isEmpty(this.unSelectedTabIcon) ? "" : this.unSelectedTabIcon;
    }

    public String getUserData() {
        return this.userData;
    }

    public UserInfoSns getUserInfoSns() {
        UserInfoSns userInfoSns = this.userInfoSns;
        return userInfoSns == null ? new UserInfoSns() : userInfoSns;
    }

    public String getUserLevelClass() {
        return TextUtils.isEmpty(this.userLevelClass) ? "" : this.userLevelClass;
    }

    public String getUserLevelIcon() {
        return TextUtils.isEmpty(this.userLevelIcon) ? "" : this.userLevelIcon;
    }

    public UserPlusEntity getUserPlusEntity() {
        return this.userPlusEntity;
    }

    public String getUts() {
        return PersonalUtsManager.getInstance().getUts();
    }

    public XbCreditInfo getXiaoBaiCreditInfo() {
        return this.xiaoBaiCreditInfo;
    }

    public String getXiaoBaiMessage() {
        return TextUtils.isEmpty(this.xiaoBaiMessage) ? "" : this.xiaoBaiMessage;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isBirthdayActivity() {
        BirthdayActivity birthdayActivity = this.birthdayActivity;
        if (birthdayActivity == null) {
            return false;
        }
        return birthdayActivity.isBirthday;
    }

    public boolean isEmployee() {
        EmployeeSetting employeeSetting = this.employeeSetting;
        return employeeSetting != null && employeeSetting.isEmployee;
    }

    public boolean isFamilyNum() {
        UserInfoSns userInfoSns = this.userInfoSns;
        return userInfoSns != null && userInfoSns.familyNum;
    }

    public boolean isInterview() {
        return this.interview;
    }

    public boolean isJingXiangZhiFlashSwitch() {
        return this.jingXiangZhiFlashSwitch;
    }

    public boolean isPlusBannerShow() {
        int i2 = this.bannerType;
        return i2 == 1 || i2 == 2;
    }

    public boolean isSupportCredit() {
        return this.isSupportCredit;
    }

    public boolean isUserPlusStatus() {
        return this.userPlusStatus;
    }

    public void parsePersonalInfo(JDJSONObject jDJSONObject, int i2) {
        JDJSONObject jSONObject;
        JDJSONArray jSONArray;
        int optInt = jDJSONObject.optInt(JsonEncryptUtil.ENC_KEY, -1);
        this.enc = optInt;
        if (optInt != 0) {
            jSONObject = parseProxy("userInfoSns", jDJSONObject);
            jSONArray = parseArrayProxy("labels", jDJSONObject);
        } else {
            jSONObject = jDJSONObject.getJSONObject("userInfoSns");
            jSONArray = jDJSONObject.getJSONArray("labels");
        }
        if (jSONObject != null) {
            UserInfoSns userInfoSns = new UserInfoSns();
            this.userInfoSns = userInfoSns;
            userInfoSns.unickName = jSONObject.optString("unickName");
            this.userInfoSns.petName = jSONObject.optString("petName");
            this.userInfoSns.petNameTips = jSONObject.optString("petNameTips");
            this.userInfoSns.userName = jSONObject.optString("userName");
            this.userInfoSns.uclass = jSONObject.optString("uclass");
            this.userInfoSns.sex = jSONObject.optInt("sex", 2);
            this.userInfoSns.birthday = jSONObject.optString("birthday");
            this.userInfoSns.imgUrl = jSONObject.optString("imgUrl");
            this.userInfoSns.homePage = jSONObject.optString(DeepLinkIntelligentAssistantHelper.FROM_KEY_HOME_PAGE);
            this.userInfoSns.familyNum = jSONObject.optBoolean("familyNum");
            if (!TextUtils.isEmpty(this.userInfoSns.homePage)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(this.userInfoSns.homePage);
                    HomePage homePage = new HomePage();
                    this.homePage = homePage;
                    homePage.uclass = jSONObject2.optInt("uclass");
                    this.userInfoSns.homePageObject = this.homePage;
                } catch (JSONException e2) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        this.noModifyText = jDJSONObject.optString("noModifyText");
        updateLabelList(jSONArray);
        if (UnI18NUtils.isMainApp()) {
            mainAppParse(jDJSONObject, i2);
        }
        this.available = true;
        if (LoginUserBase.hasLogin()) {
            return;
        }
        resetData();
    }

    public boolean plusWareInfoListIsAvailable() {
        List<UserPlusProduct> list = this.plusWareInfoList;
        return list != null && list.size() > 0;
    }

    public void registerLoginAndExitReceiver(Context context) {
        if (context == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        intentFilter.addAction("com.jingdong.action.user.login.out");
        try {
            context.registerReceiver(this.loginAndExitReceiver, intentFilter);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "registerLoginAndExitReceiver: " + e2);
            }
        }
    }

    public void reset() {
        this.available = false;
    }

    public void resetData() {
        this.plusBannerShow = false;
        this.plusWareInfoList = null;
        this.schoolDataInfo = null;
        this.gongyiDataInfo = null;
        this.userInfoSns = null;
        this.employeeSetting = null;
        this.userData = null;
        PersonalUtsManager.getInstance().removeUts();
        reset();
    }

    public void setBannerType(int i2) {
        this.bannerType = i2;
    }

    public void setFirstBuyGiude(FirstBuyGiude firstBuyGiude) {
        this.firstBuyGiude = firstBuyGiude;
    }

    public void setNickName(String str) {
        UserInfoSns userInfoSns = this.userInfoSns;
        if (userInfoSns != null) {
            userInfoSns.petName = str;
        }
    }

    public void unregisterLoginAndExitReceiver(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.unregisterReceiver(this.loginAndExitReceiver);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "unregisterLoginAndExitReceiver: " + e2);
            }
        }
    }

    public static void requestPersonalInfo(HttpGroup httpGroup, PersonalInfoRequestListener personalInfoRequestListener, int i2) {
        requestPersonalInfo(httpGroup, personalInfoRequestListener, i2, 0);
    }

    public static void requestPersonalInfo(HttpGroup httpGroup, int i2, int i3) {
        requestPersonalInfo(httpGroup, null, i2, i3);
    }

    public static void requestPersonalInfo(HttpGroup httpGroup, PersonalInfoRequestListener personalInfoRequestListener, int i2, int i3) {
        requestPersonalInfo(httpGroup, personalInfoRequestListener, i2, i3, false);
    }

    public static void requestPersonalInfo(HttpGroup httpGroup, final PersonalInfoRequestListener personalInfoRequestListener, final int i2, final int i3, boolean z) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setFunctionId(PersonalConstants.MYJD_SERVER_NERUSERINFO);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.PersonalInfoManager.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getCode() != 0) {
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (OKLog.D) {
                    OKLog.d(PersonalInfoManager.TAG, " queryNewUserInfo -->> onEnd");
                }
                try {
                    PersonalInfoManager.getInstance().parsePersonalInfo(fastJsonObject, i2);
                    if (PersonalInfoManager.getInstance().isAvailable()) {
                        PersonalChangeSkinUtils.getInstance().changeIcon(PersonalInfoManager.getInstance().getSelectedTabIcon(), PersonalInfoManager.getInstance().getUnSelectedTabIcon());
                    }
                    PersonalInfoRequestListener personalInfoRequestListener2 = personalInfoRequestListener;
                    if (personalInfoRequestListener2 != null) {
                        personalInfoRequestListener2.onEnd();
                    }
                } catch (Exception unused) {
                    PersonalInfoRequestListener personalInfoRequestListener3 = personalInfoRequestListener;
                    if (personalInfoRequestListener3 != null) {
                        personalInfoRequestListener3.onError();
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (OKLog.D) {
                    OKLog.d(PersonalInfoManager.TAG, " queryCouponAndGift -->> onError");
                }
                PersonalInfoRequestListener personalInfoRequestListener2 = personalInfoRequestListener;
                if (personalInfoRequestListener2 != null) {
                    personalInfoRequestListener2.onError();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                if (UnI18NUtils.isMainApp()) {
                    httpSettingParams.putJsonParam("flag", "nickname");
                    httpSettingParams.putJsonParam("sourceLevel", Integer.valueOf(i2));
                    httpSettingParams.putJsonParam("fromSource", Integer.valueOf(i3));
                }
            }
        });
        httpSetting.setEffect(z ? 1 : 0);
        httpSetting.setEnableGatewayQueue(false);
        httpGroup.add(httpSetting);
    }
}

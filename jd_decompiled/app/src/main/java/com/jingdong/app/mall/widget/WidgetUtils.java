package com.jingdong.app.mall.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.widget.model.CardConfigVO;
import com.jingdong.app.mall.widget.model.RecommentGoods;
import com.jingdong.app.mall.widget.model.SecondKillVO;
import com.jingdong.app.mall.widget.model.WidgetData;
import com.jingdong.app.mall.widget.model.WidgetPromotionInfo;
import com.jingdong.app.mall.widget.vivo.VIVOMultiFunctionWidget;
import com.jingdong.app.mall.widget.vivo.VIVOWidget;
import com.jingdong.app.mall.widget.vivo.VIVOWidgetRemoteService;
import com.jingdong.app.mall.widget.xiaomi.MIUIMultiFunctionWidget;
import com.jingdong.app.mall.widget.xiaomi.MIUIWidget;
import com.jingdong.app.mall.widget.xiaomi.MIUIWidgetRemoteService;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class WidgetUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RemoteViews f12024g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12025h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Context f12026i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ AppWidgetManager f12027j;

        a(RemoteViews remoteViews, String str, Context context, AppWidgetManager appWidgetManager) {
            this.f12024g = remoteViews;
            this.f12025h = str;
            this.f12026i = context;
            this.f12027j = appWidgetManager;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObject optJSONObject;
            CardConfigVO cardConfigVO;
            SharedPreferences.Editor edit;
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return;
            }
            WidgetData widgetData = (WidgetData) JDJSON.parseObject(optJSONObject.toString(), WidgetData.class);
            if (widgetData != null && (cardConfigVO = widgetData.cardConfigVO) != null) {
                CardConfigVO.CardConfig cardConfig = cardConfigVO.cardConfig;
                if (cardConfig != null) {
                    WidgetUtils.o(this.f12024g, cardConfig);
                }
                this.f12024g.setViewVisibility(R.id.widget_no_data_title, 8);
                this.f12024g.setViewVisibility(R.id.widget_no_data_goods_list, 8);
                this.f12024g.setViewVisibility(R.id.widget_title_rl, 0);
                this.f12024g.setViewVisibility(R.id.widget_gridview, 0);
                List<RecommentGoods> list = widgetData.cardContent;
                if (list != null && list.size() > 0) {
                    SharedPreferences sharedPreferences = null;
                    if (TextUtils.equals(this.f12025h, "xiaomi")) {
                        sharedPreferences = this.f12026i.getSharedPreferences("widgetDateXIAOMI", 0);
                    } else if (TextUtils.equals(this.f12025h, "vivo")) {
                        sharedPreferences = this.f12026i.getSharedPreferences("widgetDateVIVO", 0);
                    }
                    if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                        edit.putString("goods", new Gson().toJson(list));
                        if (!TextUtils.isEmpty(cardConfigVO.burriedExpLabel)) {
                            edit.putString("expLabel", cardConfigVO.burriedExpLabel);
                        }
                        edit.apply();
                    }
                }
            }
            if (TextUtils.equals("xiaomi", this.f12025h)) {
                ComponentName componentName = new ComponentName(this.f12026i, MIUIWidget.class);
                AppWidgetManager appWidgetManager = this.f12027j;
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.widget_gridview);
                this.f12027j.updateAppWidget(componentName, this.f12024g);
            } else if (TextUtils.equals("vivo", this.f12025h)) {
                ComponentName componentName2 = new ComponentName(this.f12026i, VIVOWidget.class);
                AppWidgetManager appWidgetManager2 = this.f12027j;
                appWidgetManager2.notifyAppWidgetViewDataChanged(appWidgetManager2.getAppWidgetIds(componentName2), R.id.widget_gridview);
                this.f12027j.updateAppWidget(componentName2, this.f12024g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str;
            this.f12024g.setViewVisibility(R.id.widget_gridview, 8);
            this.f12024g.setViewVisibility(R.id.widget_title_rl, 8);
            Intent intent = new Intent();
            if (TextUtils.equals(this.f12025h, "xiaomi")) {
                str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"m\",\"url\":\"https://jingfen.jd.com/item?u_act_p=union-radar&cu=true&utm_source=kong&utm_medium=tuiguang&utm_campaign=t_2024785911_4&utm_term=itemrec_groupa_1\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_4|tuiguang|itemrec_groupa_1\"}}";
            } else {
                str = TextUtils.equals(this.f12025h, "vivo") ? "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"m\",\"url\":\"https://jingfen.jd.com/item?u_act_p=union-radar&cu=true&utm_source=kong&utm_medium=tuiguang&utm_campaign=t_2024785911_4&utm_term=itemrec_groupa_1\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_2|tuiguang|itemrec_groupa_1\"}}" : "";
            }
            SharedPreferences sharedPreferences = null;
            if (TextUtils.equals(this.f12025h, "xiaomi")) {
                sharedPreferences = this.f12026i.getSharedPreferences("widgetDateXIAOMI", 0);
            } else if (TextUtils.equals(this.f12025h, "vivo")) {
                sharedPreferences = this.f12026i.getSharedPreferences("widgetDateVIVO", 0);
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt("i", 0);
            edit.putString("goods", "");
            edit.apply();
            if (!TextUtils.isEmpty(str)) {
                intent.setData(Uri.parse(str));
                PendingIntent activity = PendingIntent.getActivity(this.f12026i, 8, intent, WidgetUtils.f("IMMUTABLE"));
                this.f12024g.setOnClickPendingIntent(R.id.widget_no_data_title, activity);
                this.f12024g.setOnClickPendingIntent(R.id.widget_no_data_goods_list, activity);
            }
            if (TextUtils.equals("xiaomi", this.f12025h)) {
                this.f12027j.updateAppWidget(new ComponentName(this.f12026i, MIUIWidget.class), this.f12024g);
            } else if (TextUtils.equals("vivo", this.f12025h)) {
                this.f12027j.updateAppWidget(new ComponentName(this.f12026i, VIVOWidget.class), this.f12024g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RemoteViews f12028g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12029h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Context f12030i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ AppWidgetManager f12031j;

        b(RemoteViews remoteViews, String str, Context context, AppWidgetManager appWidgetManager) {
            this.f12028g = remoteViews;
            this.f12029h = str;
            this.f12030i = context;
            this.f12031j = appWidgetManager;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObject optJSONObject;
            WidgetPromotionInfo.TemplateSkuInfo templateSkuInfo;
            SharedPreferences sharedPreferences;
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("floorInfo")) == null) {
                return;
            }
            WidgetPromotionInfo widgetPromotionInfo = (WidgetPromotionInfo) JDJSON.parseObject(optJSONObject.toString(), WidgetPromotionInfo.class);
            if (widgetPromotionInfo != null) {
                List<WidgetPromotionInfo.TemplateSkuInfo> list = widgetPromotionInfo.templateList;
                if (list != null && !list.isEmpty() && widgetPromotionInfo.templateList.size() > 0 && (templateSkuInfo = widgetPromotionInfo.templateList.get(0)) != null) {
                    if (!TextUtils.isEmpty(templateSkuInfo.image)) {
                        this.f12028g.setImageViewBitmap(R.id.seckill_sku_img, WidgetUtils.c(templateSkuInfo.image));
                    }
                    if (!TextUtils.isEmpty(templateSkuInfo.pprice)) {
                        this.f12028g.setTextViewText(R.id.seckill_sku_price, templateSkuInfo.pprice);
                    }
                    if (!TextUtils.isEmpty(templateSkuInfo.jumpUrl)) {
                        Intent intent = new Intent();
                        String str = templateSkuInfo.jumpUrl;
                        if (TextUtils.equals(this.f12029h, "xiaomi")) {
                            sharedPreferences = this.f12030i.getSharedPreferences("widgetDateXIAOMI", 0);
                        } else {
                            sharedPreferences = TextUtils.equals(this.f12029h, "vivo") ? this.f12030i.getSharedPreferences("widgetDateVIVO", 0) : null;
                        }
                        if (sharedPreferences != null) {
                            String string = sharedPreferences.getString("expLabel", null);
                            if (!TextUtils.isEmpty(string)) {
                                str = WidgetUtils.j(str, string);
                            }
                        }
                        intent.setData(Uri.parse(str));
                        this.f12028g.setOnClickPendingIntent(R.id.widget_multi_seckill_layout, PendingIntent.getActivity(this.f12030i, 12, intent, WidgetUtils.f("IMMUTABLE")));
                    }
                }
                this.f12028g.setViewVisibility(R.id.widget_multi_no_data, 8);
                this.f12028g.setViewVisibility(R.id.widget_multi_layout, 0);
            }
            if (TextUtils.equals("xiaomi", this.f12029h)) {
                this.f12031j.updateAppWidget(new ComponentName(this.f12030i, MIUIMultiFunctionWidget.class), this.f12028g);
            } else if (TextUtils.equals("vivo", this.f12029h)) {
                this.f12031j.updateAppWidget(new ComponentName(this.f12030i, VIVOMultiFunctionWidget.class), this.f12028g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str;
            this.f12028g.setViewVisibility(R.id.widget_multi_no_data, 0);
            this.f12028g.setViewVisibility(R.id.widget_multi_layout, 8);
            Intent intent = new Intent();
            if (TextUtils.equals(this.f12029h, "xiaomi")) {
                str = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"sourceValue\":\"sourceValue_test\",\"sourceType\":\"sourceType_test\",\"des\":\"seckill\",\"secKillKeyTabType\":\"seckill\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_4|tuiguang|multifunc_groupa_miaosha\"}}";
            } else {
                str = TextUtils.equals(this.f12029h, "vivo") ? "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"sourceValue\":\"sourceValue_test\",\"sourceType\":\"sourceType_test\",\"des\":\"seckill\",\"secKillKeyTabType\":\"seckill\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_2|tuiguang|multifunc_groupa_miaosha\"}}" : "";
            }
            if (!TextUtils.isEmpty(str)) {
                intent.setData(Uri.parse(str));
                PendingIntent activity = PendingIntent.getActivity(this.f12030i, 11, intent, WidgetUtils.f("IMMUTABLE"));
                this.f12028g.setOnClickPendingIntent(R.id.no_data_seckill_layout, activity);
                this.f12028g.setOnClickPendingIntent(R.id.no_data_card_layout, activity);
            }
            if (TextUtils.equals("xiaomi", this.f12029h)) {
                this.f12031j.updateAppWidget(new ComponentName(this.f12030i, MIUIMultiFunctionWidget.class), this.f12028g);
            } else if (TextUtils.equals("vivo", this.f12029h)) {
                this.f12031j.updateAppWidget(new ComponentName(this.f12030i, VIVOMultiFunctionWidget.class), this.f12028g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ RemoteViews f12032g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12033h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Context f12034i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ AppWidgetManager f12035j;

        c(RemoteViews remoteViews, String str, Context context, AppWidgetManager appWidgetManager) {
            this.f12032g = remoteViews;
            this.f12033h = str;
            this.f12034i = context;
            this.f12035j = appWidgetManager;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObject optJSONObject;
            SharedPreferences.Editor edit;
            int i2;
            int i3;
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject("data")) == null) {
                return;
            }
            WidgetData widgetData = (WidgetData) JDJSON.parseObject(optJSONObject.toString(), WidgetData.class);
            if (widgetData != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("cardContent");
                if (optJSONObject2 == null) {
                    return;
                }
                SecondKillVO secondKillVO = (SecondKillVO) JDJSON.parseObject(optJSONObject2.toString(), SecondKillVO.class);
                if (secondKillVO != null) {
                    this.f12032g.setTextViewText(R.id.seckill_time, secondKillVO.screenings + "\u70b9\u573a");
                }
                CardConfigVO cardConfigVO = widgetData.cardConfigVO;
                if (cardConfigVO != null) {
                    String str = cardConfigVO.burriedExpLabel;
                    CardConfigVO.CardConfig cardConfig = cardConfigVO.cardConfig;
                    if (cardConfig != null) {
                        if (!TextUtils.isEmpty(cardConfig.jdIcon)) {
                            this.f12032g.setImageViewBitmap(R.id.jd_icon, WidgetUtils.c(cardConfig.jdIcon));
                        }
                        if (!TextUtils.isEmpty(cardConfig.backgroundPicture)) {
                            this.f12032g.setImageViewBitmap(R.id.widget_multi_bg, WidgetUtils.c(cardConfig.backgroundPicture));
                        }
                        List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> list = cardConfig.channelList;
                        if (list != null && !list.isEmpty() && list.size() > 2) {
                            String json = new Gson().toJson(list);
                            SharedPreferences sharedPreferences = null;
                            if (TextUtils.equals(this.f12033h, "xiaomi")) {
                                sharedPreferences = this.f12034i.getSharedPreferences("widgetDateXIAOMI", 0);
                            } else if (TextUtils.equals(this.f12033h, "vivo")) {
                                sharedPreferences = this.f12034i.getSharedPreferences("widgetDateVIVO", 0);
                            }
                            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                                edit.putString("functionList", json);
                                int i4 = sharedPreferences.getInt("select1", -1);
                                int i5 = sharedPreferences.getInt("select2", -1);
                                if (i4 == -1 || i5 == -1) {
                                    edit.putInt("select1", 0);
                                    edit.putInt("select2", 1);
                                    i2 = 0;
                                    i3 = 1;
                                } else {
                                    i3 = i5;
                                    i2 = i4;
                                }
                                if (!TextUtils.isEmpty(str)) {
                                    edit.putString("expLabel", str);
                                }
                                edit.apply();
                                WidgetUtils.n(this.f12034i, this.f12032g, list, i2, i3, this.f12033h);
                            }
                        }
                    }
                }
                this.f12032g.setViewVisibility(R.id.widget_multi_no_data, 8);
                this.f12032g.setViewVisibility(R.id.widget_multi_layout, 0);
            }
            if (TextUtils.equals("xiaomi", this.f12033h)) {
                this.f12035j.updateAppWidget(new ComponentName(this.f12034i, MIUIMultiFunctionWidget.class), this.f12032g);
            } else if (TextUtils.equals("vivo", this.f12033h)) {
                this.f12035j.updateAppWidget(new ComponentName(this.f12034i, VIVOMultiFunctionWidget.class), this.f12032g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            this.f12032g.setViewVisibility(R.id.widget_multi_no_data, 0);
            this.f12032g.setViewVisibility(R.id.widget_multi_layout, 8);
            if (TextUtils.equals("xiaomi", this.f12033h)) {
                this.f12035j.updateAppWidget(new ComponentName(this.f12034i, MIUIMultiFunctionWidget.class), this.f12032g);
            } else if (TextUtils.equals("vivo", this.f12033h)) {
                this.f12035j.updateAppWidget(new ComponentName(this.f12034i, VIVOMultiFunctionWidget.class), this.f12032g);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public static Bitmap c(String str) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
            URLConnection openConnection = new URL(str).openConnection();
            openConnection.connect();
            InputStream inputStream = openConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            bufferedInputStream.close();
            inputStream.close();
            return bitmap;
        } catch (IOException e2) {
            Log.e("widget", "Error getting bitmap", e2);
            return bitmap;
        }
    }

    public static void d(Context context, String str, RemoteViews remoteViews, int i2, AppWidgetManager appWidgetManager) {
        if (context == null || remoteViews == null || appWidgetManager == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("mobileCode", str);
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("negativeOneGoodsRecomment");
        httpSetting.setListener(new a(remoteViews, str, context, appWidgetManager));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void e(String str, RemoteViews remoteViews, int i2, AppWidgetManager appWidgetManager, Context context) {
        if (context == null || appWidgetManager == null || remoteViews == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("mobileCode", str);
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("negativeOneMultiFunction");
        httpSetting.setListener(new c(remoteViews, str, context, appWidgetManager));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static int f(String str) {
        if (Build.VERSION.SDK_INT >= 31) {
            if (TextUtils.equals("IMMUTABLE", str)) {
                return 201326592;
            }
            if (TextUtils.equals("MUTABLE", str)) {
                return 167772160;
            }
        }
        return 134217728;
    }

    public static void g(RemoteViews remoteViews, int i2, AppWidgetManager appWidgetManager, Context context, String str) {
        if (remoteViews == null || appWidgetManager == null || context == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setNotifyUser(false);
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("desktopComponents");
        httpSetting.setListener(new b(remoteViews, str, context, appWidgetManager));
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void h(Intent intent, String str, AppWidgetManager appWidgetManager, Context context) {
        if (intent == null || appWidgetManager == null || context == null) {
            return;
        }
        int intExtra = intent.getIntExtra("appWidgetId", -1);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_goodsrecommend);
        Intent intent2 = new Intent();
        if (TextUtils.equals("xiaomi", str)) {
            intent2.setComponent(new ComponentName(context, MIUIWidget.class));
        } else if (TextUtils.equals("vivo", str)) {
            intent2.setComponent(new ComponentName(context, VIVOWidget.class));
        }
        intent2.setAction("com.jingdong.app.mall.widget.WIDGETGOODS_REFRESH");
        remoteViews.setOnClickPendingIntent(R.id.widget_change, PendingIntent.getBroadcast(context, 6, intent2, f("IMMUTABLE")));
        Intent intent3 = null;
        if (TextUtils.equals("xiaomi", str)) {
            intent3 = new Intent(context, MIUIWidgetRemoteService.class);
        } else if (TextUtils.equals("vivo", str)) {
            intent3 = new Intent(context, VIVOWidgetRemoteService.class);
        }
        d(context, str, remoteViews, intExtra, appWidgetManager);
        if (intent3 == null) {
            return;
        }
        intent3.putExtra("appWidgetId", intExtra);
        intent3.setData(Uri.parse(intent3.toUri(1)));
        remoteViews.setRemoteAdapter(R.id.widget_gridview, intent3);
        remoteViews.setPendingIntentTemplate(R.id.widget_gridview, PendingIntent.getActivity(context, 7, new Intent(), f("MUTABLE")));
        d(context, str, remoteViews, intExtra, appWidgetManager);
    }

    public static void i(Context context, AppWidgetManager appWidgetManager, int i2, String str) {
        if (context == null || appWidgetManager == null) {
            return;
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_goodsrecommend);
        if (!JDPrivacyHelper.isAcceptPrivacy(context)) {
            remoteViews.setViewVisibility(R.id.widget_noPrivacy_layout, 0);
            remoteViews.setImageViewResource(R.id.widget_title_bg, R.drawable.widget_top_bg);
            remoteViews.setViewVisibility(R.id.widget_no_data_title, 8);
            remoteViews.setViewVisibility(R.id.widget_title_rl, 8);
            remoteViews.setViewVisibility(R.id.widget_no_data_goods_list, 8);
            remoteViews.setViewVisibility(R.id.widget_gridview, 8);
            Bundle bundle = new Bundle();
            bundle.putString(LoginConstans.JUMP_DES, JumpUtil.VALUE_DES_APPHOME);
            remoteViews.setOnClickPendingIntent(R.id.widget_noPrivacy_layout, PendingIntent.getActivity(context, 5, com.jingdong.app.mall.basic.deshandler.a.i(context, bundle), f("IMMUTABLE")));
        } else {
            remoteViews.setViewVisibility(R.id.widget_noPrivacy_layout, 8);
            remoteViews.setViewVisibility(R.id.widget_no_data_title, 0);
            remoteViews.setViewVisibility(R.id.widget_no_data_goods_list, 0);
            Intent intent = new Intent();
            intent.putExtra("appWidgetId", i2);
            h(intent, str, appWidgetManager, context);
        }
        if (TextUtils.equals("xiaomi", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, MIUIWidget.class), remoteViews);
        } else if (TextUtils.equals("vivo", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, VIVOWidget.class), remoteViews);
        }
    }

    public static String j(String str, String str2) {
        Uri parse = Uri.parse(str);
        if (parse != null) {
            String queryParameter = parse.getQueryParameter("params");
            if (TextUtils.isEmpty(queryParameter)) {
                return str;
            }
            if (queryParameter.startsWith("\"")) {
                queryParameter = queryParameter.substring(1);
            }
            if (queryParameter.endsWith("\"")) {
                queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
            }
            JDJSONObject string2JDJsonObject = JumpUtil.string2JDJsonObject(queryParameter, parse);
            if (string2JDJsonObject == null || string2JDJsonObject.isEmpty()) {
                return str;
            }
            if (string2JDJsonObject.containsKey(JshopConst.JSHOP_M_PARAM)) {
                JDJSONObject optJSONObject = string2JDJsonObject.optJSONObject(JshopConst.JSHOP_M_PARAM);
                if (optJSONObject != null && !optJSONObject.isEmpty()) {
                    optJSONObject.put("touchstone_expids", (Object) str2);
                    string2JDJsonObject.put(JshopConst.JSHOP_M_PARAM, (Object) optJSONObject);
                }
            } else {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("touchstone_expids", (Object) str2);
                string2JDJsonObject.put(JshopConst.JSHOP_M_PARAM, (Object) jDJSONObject);
            }
            return "openapp.jdmobile://virtual?params=" + string2JDJsonObject.toJSONString();
        }
        return str;
    }

    public static boolean k(String str) {
        if (str == null) {
            return false;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1159866970:
                if (str.equals("jipiao")) {
                    c2 = 0;
                    break;
                }
                break;
            case -906336856:
                if (str.equals("search")) {
                    c2 = 1;
                    break;
                }
                break;
            case -806191449:
                if (str.equals(JumpUtil.VALUE_DES_CHONGZHI)) {
                    c2 = 2;
                    break;
                }
                break;
            case -316854026:
                if (str.equals(JumpUtil.VAULE_DES_AIRLINE)) {
                    c2 = 3;
                    break;
                }
                break;
            case 1833245752:
                if (str.equals("chongzhi")) {
                    c2 = 4;
                    break;
                }
                break;
            case 1968810330:
                if (str.equals(JumpUtil.VALUE_DES_WAITING_GOODS)) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }
    }

    public static void l(Intent intent, String str, AppWidgetManager appWidgetManager, Context context) {
        if (intent == null || context == null || appWidgetManager == null) {
            return;
        }
        int intExtra = intent.getIntExtra("appWidgetIds", -1);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_multifunction);
        Intent intent2 = new Intent();
        if (TextUtils.equals(str, "xiaomi")) {
            intent2.setData(Uri.parse("openapp.jdmobile://virtual?params={\"des\":\"search\",\"category\":\"jump\",\"keyword\":\"\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_4|tuiguang|multifunc_groupa_search\",\"overwrite_jdv\":true}}"));
        } else if (TextUtils.equals(str, "vivo")) {
            intent2.setData(Uri.parse("openapp.jdmobile://virtual?params={\"des\":\"search\",\"category\":\"jump\",\"keyword\":\"\",\"m_param\":{\"jdv\":\"76161171|kong|t_2024785911_3|tuiguang|multifunc_groupa_search\",\"overwrite_jdv\":true}}"));
        }
        PendingIntent activity = PendingIntent.getActivity(context, 10, intent2, f("IMMUTABLE"));
        remoteViews.setOnClickPendingIntent(R.id.widget_multi_top, activity);
        remoteViews.setOnClickPendingIntent(R.id.no_data_search_layout, activity);
        e(str, remoteViews, intExtra, appWidgetManager, context);
        g(remoteViews, intExtra, appWidgetManager, context, str);
    }

    public static void m(Context context, AppWidgetManager appWidgetManager, int i2, String str) {
        if (context == null || appWidgetManager == null) {
            return;
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_multifunction);
        if (!JDPrivacyHelper.isAcceptPrivacy(context)) {
            remoteViews.setViewVisibility(R.id.widget_multi_noPrivacy_layout, 0);
            remoteViews.setViewVisibility(R.id.widget_multi_no_data, 8);
            remoteViews.setViewVisibility(R.id.widget_multi_layout, 8);
            remoteViews.setImageViewResource(R.id.widget_multi_bg, R.drawable.widget_multifunction_bg);
            Bundle bundle = new Bundle();
            bundle.putString(LoginConstans.JUMP_DES, JumpUtil.VALUE_DES_APPHOME);
            remoteViews.setOnClickPendingIntent(R.id.widget_multi_noPrivacy_layout, PendingIntent.getActivity(context, 9, com.jingdong.app.mall.basic.deshandler.a.i(context, bundle), f("IMMUTABLE")));
        } else {
            remoteViews.setViewVisibility(R.id.widget_multi_noPrivacy_layout, 8);
            Intent intent = new Intent();
            intent.putExtra("appWidgetId", i2);
            l(intent, str, appWidgetManager, context);
        }
        if (TextUtils.equals("xiaomi", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, MIUIMultiFunctionWidget.class), remoteViews);
        } else if (TextUtils.equals("vivo", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, VIVOMultiFunctionWidget.class), remoteViews);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void n(Context context, RemoteViews remoteViews, List<CardConfigVO.CardConfig.NegativeOneCardChannelVO> list, int i2, int i3, String str) {
        if (context == null || remoteViews == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(LoginConstans.JUMP_DES, JumpUtil.VALUE_DES_WIDGET_EDIT_SETTING);
        bundle.putString("functionList", new Gson().toJson(list));
        bundle.putInt("select1", i2);
        bundle.putInt("select2", i3);
        bundle.putString("fromBrand", str);
        Intent i4 = com.jingdong.app.mall.basic.deshandler.a.i(context, bundle);
        if (TextUtils.equals(str, "xiaomi")) {
            remoteViews.setOnClickPendingIntent(R.id.widget_multi_more, PendingIntent.getActivity(context, 1, i4, f("IMMUTABLE")));
        } else if (TextUtils.equals(str, "vivo")) {
            remoteViews.setOnClickPendingIntent(R.id.widget_multi_more, PendingIntent.getActivity(context, 2, i4, f("IMMUTABLE")));
        }
        if (!TextUtils.isEmpty(list.get(i2).buttonIcon)) {
            remoteViews.setImageViewBitmap(R.id.multi_card_1_img, c(list.get(i2).buttonIcon));
        }
        if (!TextUtils.isEmpty(list.get(i2).title)) {
            remoteViews.setTextViewText(R.id.multi_card_1_tv, list.get(i2).title);
        }
        if (!TextUtils.isEmpty(list.get(i3).buttonIcon)) {
            remoteViews.setImageViewBitmap(R.id.multi_card_2_img, c(list.get(i3).buttonIcon));
        }
        if (!TextUtils.isEmpty(list.get(i3).title)) {
            remoteViews.setTextViewText(R.id.multi_card_2_tv, list.get(i3).title);
        }
        if (!TextUtils.isEmpty(list.get(i2).jumpProtocol)) {
            Intent intent = new Intent();
            intent.setData(Uri.parse(list.get(i2).jumpProtocol));
            remoteViews.setOnClickPendingIntent(R.id.multi_card_1, PendingIntent.getActivity(context, 3, intent, f("IMMUTABLE")));
        }
        if (TextUtils.isEmpty(list.get(i3).jumpProtocol)) {
            return;
        }
        Intent intent2 = new Intent();
        intent2.setData(Uri.parse(list.get(i3).jumpProtocol));
        remoteViews.setOnClickPendingIntent(R.id.multi_card_2, PendingIntent.getActivity(context, 4, intent2, f("IMMUTABLE")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void o(RemoteViews remoteViews, CardConfigVO.CardConfig cardConfig) {
        if (remoteViews == null || cardConfig == null) {
            return;
        }
        if (!TextUtils.isEmpty(cardConfig.backgroundPicture)) {
            remoteViews.setImageViewBitmap(R.id.widget_title_bg, c(cardConfig.backgroundPicture));
        }
        if (!TextUtils.isEmpty(cardConfig.jdIcon)) {
            remoteViews.setImageViewBitmap(R.id.widget_jdlogo, c(cardConfig.jdIcon));
        }
        if (!TextUtils.isEmpty(cardConfig.title)) {
            remoteViews.setTextViewText(R.id.widget_title, cardConfig.title);
        }
        if (!TextUtils.isEmpty(cardConfig.titleColour)) {
            remoteViews.setTextColor(R.id.widget_title, Color.parseColor(cardConfig.titleColour));
        }
        if (!TextUtils.isEmpty(cardConfig.deputyTitle)) {
            remoteViews.setTextViewText(R.id.widget_subtitle, cardConfig.deputyTitle);
        }
        if (!TextUtils.isEmpty(cardConfig.deputyTitleColour)) {
            remoteViews.setTextColor(R.id.widget_subtitle, Color.parseColor(cardConfig.deputyTitleColour));
        }
        if (!TextUtils.isEmpty(cardConfig.changeIcon)) {
            remoteViews.setImageViewBitmap(R.id.widget_goods_change_icon, c(cardConfig.changeIcon));
        }
        if (TextUtils.isEmpty(cardConfig.changeColour)) {
            return;
        }
        remoteViews.setTextColor(R.id.widget_goods_change_tv, Color.parseColor(cardConfig.changeColour));
    }

    public static void p(Context context, int i2, int i3, int i4, String str) {
        SharedPreferences sharedPreferences;
        if (context == null) {
            return;
        }
        if (TextUtils.equals(str, "xiaomi")) {
            sharedPreferences = context.getSharedPreferences("widgetDateXIAOMI", 0);
        } else {
            sharedPreferences = TextUtils.equals(str, "vivo") ? context.getSharedPreferences("widgetDateVIVO", 0) : null;
        }
        if (sharedPreferences == null) {
            return;
        }
        String string = sharedPreferences.getString("functionList", null);
        List arrayList = new ArrayList();
        if (!TextUtils.isEmpty(string)) {
            arrayList = (List) new Gson().fromJson(string, new TypeToken<List<CardConfigVO.CardConfig.NegativeOneCardChannelVO>>() { // from class: com.jingdong.app.mall.widget.WidgetUtils.1
            }.getType());
        }
        List list = arrayList;
        if (i2 >= list.size() || i3 >= list.size()) {
            return;
        }
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_multifunction);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (edit != null) {
            edit.putInt("select1", i2);
            edit.putInt("select2", i3);
            edit.apply();
        }
        n(context, remoteViews, list, i2, i3, str);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (appWidgetManager == null) {
            return;
        }
        if (TextUtils.equals("xiaomi", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, MIUIMultiFunctionWidget.class), remoteViews);
        } else if (TextUtils.equals("vivo", str)) {
            appWidgetManager.updateAppWidget(new ComponentName(context, VIVOMultiFunctionWidget.class), remoteViews);
        }
    }

    public static void q() {
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        if (applicationContext == null || AppWidgetManager.getInstance(applicationContext) == null) {
            return;
        }
        ComponentName componentName = new ComponentName(applicationContext, VIVOMultiFunctionWidget.class);
        if (AppWidgetManager.getInstance(applicationContext).getAppWidgetIds(componentName).length > 0) {
            Intent intent = new Intent();
            intent.setAction("com.jingdong.app.mall.widget.WIDGET_VIVO_UPDATE");
            intent.setComponent(componentName);
            applicationContext.sendBroadcast(intent);
        }
        ComponentName componentName2 = new ComponentName(applicationContext, MIUIMultiFunctionWidget.class);
        if (AppWidgetManager.getInstance(applicationContext).getAppWidgetIds(componentName2).length > 0) {
            Intent intent2 = new Intent();
            intent2.setAction("com.jingdong.app.mall.widget.WIDGET_XIAOMI_UPDATE");
            intent2.setComponent(componentName2);
            applicationContext.sendBroadcast(intent2);
        }
    }
}

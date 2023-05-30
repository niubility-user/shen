package com.jingdong.app.mall.widget.vivo;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.widget.WidgetUtils;
import com.jingdong.app.mall.widget.model.RecommentGoods;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class VIVOWidgetRemoteService extends RemoteViewsService {

    /* loaded from: classes4.dex */
    public static class WidgetRemoteFactory implements RemoteViewsService.RemoteViewsFactory {

        /* renamed from: e */
        private static List<RecommentGoods> f12056e = new ArrayList();
        private final Context a;
        private List<RecommentGoods> b = new ArrayList();

        /* renamed from: c */
        private final int f12057c;
        public String d;

        public WidgetRemoteFactory(Context context, int i2) {
            this.a = context;
            this.f12057c = i2;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getCount() {
            return this.b.size();
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getLoadingView() {
            return new RemoteViews(this.a.getPackageName(), (int) R.layout.widget_loading_background);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public RemoteViews getViewAt(int i2) {
            RecommentGoods recommentGoods;
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), (int) R.layout.widget_grid_item);
            List<RecommentGoods> list = this.b;
            if (list != null && !list.isEmpty() && this.b.size() > 0 && (recommentGoods = this.b.get(i2)) != null) {
                remoteViews.setTextViewText(R.id.widget_item_price, "\u00a5" + recommentGoods.discountPrice);
                remoteViews.setTextViewText(R.id.widget_item_discount, "\u76f4\u964d" + recommentGoods.discountAmount + "\u5143");
                String str = recommentGoods.imageUrl;
                if (!TextUtils.isEmpty(str)) {
                    if (!recommentGoods.imageUrl.contains("http")) {
                        str = "https://m.360buyimg.com/mobilecms/s98x76_" + str;
                    }
                    remoteViews.setImageViewBitmap(R.id.widget_item_img, WidgetUtils.c(str));
                }
                if (!TextUtils.isEmpty(recommentGoods.openUrl)) {
                    String j2 = WidgetUtils.j(recommentGoods.openUrl, this.d);
                    Intent intent = new Intent();
                    intent.setData(Uri.parse(j2));
                    remoteViews.setOnClickFillInIntent(R.id.widget_item, intent);
                }
            }
            return remoteViews;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public boolean hasStableIds() {
            return false;
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onCreate() {
            int i2;
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("widgetDateVIVO", 0);
            if (sharedPreferences == null) {
                return;
            }
            String string = sharedPreferences.getString("goods", "");
            int i3 = sharedPreferences.getInt("i", 0);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            List<RecommentGoods> list = (List) new Gson().fromJson(string, new TypeToken<List<RecommentGoods>>(this) { // from class: com.jingdong.app.mall.widget.vivo.VIVOWidgetRemoteService.WidgetRemoteFactory.1
            }.getType());
            f12056e = list;
            if (list != null && list.size() >= (i2 = (i3 + 1) * 4)) {
                this.b = f12056e.subList(i3 * 4, i2);
            }
            this.d = sharedPreferences.getString("expLabel", null);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onDataSetChanged() {
            int i2;
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("widgetDateVIVO", 0);
            if (sharedPreferences == null) {
                return;
            }
            String string = sharedPreferences.getString("goods", "");
            int i3 = sharedPreferences.getInt("i", 0);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            f12056e = (List) new Gson().fromJson(string, new TypeToken<List<RecommentGoods>>(this) { // from class: com.jingdong.app.mall.widget.vivo.VIVOWidgetRemoteService.WidgetRemoteFactory.2
            }.getType());
            this.d = sharedPreferences.getString("expLabel", null);
            List<RecommentGoods> list = f12056e;
            if (list != null && list.size() >= (i2 = (i3 + 1) * 4)) {
                this.b = f12056e.subList(i3 * 4, i2);
            } else {
                List<RecommentGoods> list2 = f12056e;
                if (list2 != null && list2.size() >= 4) {
                    this.b = f12056e.subList(0, 4);
                }
            }
            RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), (int) R.layout.widget_goodsrecommend);
            if (this.b.isEmpty()) {
                remoteViews.setViewVisibility(R.id.widget_no_data_title, 0);
                remoteViews.setViewVisibility(R.id.widget_title_rl, 8);
            } else {
                remoteViews.setViewVisibility(R.id.widget_no_data_title, 8);
                remoteViews.setViewVisibility(R.id.widget_title_rl, 0);
            }
            AppWidgetManager.getInstance(this.a).partiallyUpdateAppWidget(this.f12057c, remoteViews);
        }

        @Override // android.widget.RemoteViewsService.RemoteViewsFactory
        public void onDestroy() {
            this.b.clear();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteFactory(JdSdk.getInstance().getApplicationContext(), intent.getIntExtra("appWidgetId", 0));
    }
}

package com.jd.viewkit.templates.container.jdviewkitbannerview;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerConfig;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDBannerPagerAdapter extends PagerAdapter {
    private static final int INITIAL_POSITION = 30;
    private static final int MAX_ITEMS_COUNT = 10000;
    private JDViewKitChannelModel channelModel;
    private boolean circle;
    private JDViewKitVirtualBannerConfig config;
    private int currentItem;
    private View currentView;
    private Map<String, JDViewKitVirtualView> dslsMap;
    ViewGroup.LayoutParams layoutParams;
    private List<JDViewKitDataSourceModel> mDatas;
    private final List<MyContentView> mViewCache;

    /* loaded from: classes18.dex */
    public class MyContentView extends FrameLayout {
        private JDViewKitAbsoluteLayout absoluteLayout;
        private JDViewKitAbsoluteLayout absoluteLayoutT;
        MyAsyncTask asyncTask;
        JDViewKitChannelModel channelModel;
        private JDViewKitDataSourceModel dataSourceModel;
        Context mContext;

        /* loaded from: classes18.dex */
        public class MyAsyncTask extends AsyncTask {
            public MyAsyncTask() {
            }

            @Override // android.os.AsyncTask
            protected Object doInBackground(Object[] objArr) {
                MyContentView myContentView = MyContentView.this;
                myContentView.absoluteLayoutT = JDBannerPagerAdapter.this.getView(myContentView.mContext, myContentView.dataSourceModel, 0, MyContentView.this.channelModel);
                return null;
            }

            @Override // android.os.AsyncTask
            protected void onCancelled(Object obj) {
                super.onCancelled(obj);
            }

            @Override // android.os.AsyncTask
            protected void onPostExecute(Object obj) {
                super.onPostExecute(obj);
                MyContentView.this.updateView();
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override // android.os.AsyncTask
            protected void onProgressUpdate(Object[] objArr) {
                super.onProgressUpdate(objArr);
            }

            @Override // android.os.AsyncTask
            protected void onCancelled() {
                super.onCancelled();
            }
        }

        public MyContentView(@NonNull Context context, JDViewKitDataSourceModel jDViewKitDataSourceModel, JDViewKitChannelModel jDViewKitChannelModel) {
            super(context);
            this.dataSourceModel = jDViewKitDataSourceModel;
            this.mContext = context;
            this.channelModel = jDViewKitChannelModel;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateView() {
            View view = this.absoluteLayout;
            if (view != null) {
                removeView(view);
            }
            JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = this.absoluteLayoutT;
            if (jDViewKitAbsoluteLayout != null) {
                this.absoluteLayout = jDViewKitAbsoluteLayout;
                addView(jDViewKitAbsoluteLayout, JDBannerPagerAdapter.this.layoutParams);
            }
        }

        public JDViewKitDataSourceModel getDataSourceModel() {
            return this.dataSourceModel;
        }

        public void getL() {
            MyAsyncTask myAsyncTask = this.asyncTask;
            if (myAsyncTask != null) {
                myAsyncTask.onCancelled();
                this.asyncTask = null;
            }
            MyAsyncTask myAsyncTask2 = new MyAsyncTask();
            this.asyncTask = myAsyncTask2;
            myAsyncTask2.execute(new Object[0]);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
        }

        public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
            this.dataSourceModel = jDViewKitDataSourceModel;
        }
    }

    public JDBannerPagerAdapter(JDViewKitChannelModel jDViewKitChannelModel) {
        this.mViewCache = new LinkedList();
        this.circle = true;
        this.currentItem = 0;
        this.channelModel = jDViewKitChannelModel;
    }

    private int getRealCount() {
        List<JDViewKitDataSourceModel> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    private int getStartSelectItem() {
        if (getRealCount() == 0) {
            return 0;
        }
        int realCount = (getRealCount() * 500) / 2;
        if (realCount % getRealCount() == 0) {
            return realCount;
        }
        while (realCount % getRealCount() != 0) {
            realCount++;
        }
        return realCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JDViewKitAbsoluteLayout getView(Context context, JDViewKitDataSourceModel jDViewKitDataSourceModel, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
        JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(context, jDViewKitDataSourceModel, jDViewKitVirtualView, jDViewKitChannelModel);
        if (jDViewKitVirtualView != null && jDViewKitVirtualView.getBorderRadius() != 0) {
            view.setBackgroundColor(0);
            View view2 = new View(context);
            view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            view.setBgColorAndBorder(view2);
            view.addView(view2, 0);
        }
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
        if (obj == null || !(obj instanceof MyContentView)) {
            return;
        }
        this.mViewCache.add((MyContentView) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        if (this.circle && getRealCount() > 1) {
            return getRealCount() * 10000;
        }
        return getRealCount();
    }

    public JDViewKitDataSourceModel getJDViewKitDataSourceModel(int i2) {
        int realCount;
        if (i2 < 0) {
            realCount = getRealCount() - 1;
        } else {
            realCount = i2 % getRealCount();
        }
        if (realCount < 0 || realCount >= this.mDatas.size()) {
            return null;
        }
        return this.mDatas.get(realCount);
    }

    public int getStartItem() {
        if (!this.circle || getCount() <= 1) {
            return 0;
        }
        return (10000 / (getRealCount() * 2)) * getRealCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        MyContentView remove;
        int realCount = i2 % getRealCount();
        if (this.mViewCache.size() == 0) {
            remove = new MyContentView(viewGroup.getContext(), this.mDatas.get(realCount), this.channelModel);
        } else {
            remove = this.mViewCache.remove(0);
            remove.setDataSourceModel(this.mDatas.get(realCount));
        }
        viewGroup.addView(remove, this.layoutParams);
        remove.getL();
        return remove;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public void setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
        this.channelModel = jDViewKitChannelModel;
    }

    public void setCircle(boolean z) {
        this.circle = z;
    }

    public void setConfig(JDViewKitVirtualBannerConfig jDViewKitVirtualBannerConfig) {
        this.config = jDViewKitVirtualBannerConfig;
        this.layoutParams = new ViewGroup.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualBannerConfig.getCenterSizeWidth(), this.channelModel), GlobalManage.getInstance().getRealPx(jDViewKitVirtualBannerConfig.getCenterSizeHeigh(), this.channelModel));
    }

    public void setDatas(List<JDViewKitDataSourceModel> list) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList();
        }
        this.mDatas.clear();
        this.mDatas.addAll(list);
    }

    public void setDslsMap(Map<String, JDViewKitVirtualView> map) {
        this.dslsMap = map;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        this.currentView = (View) obj;
    }

    public JDBannerPagerAdapter(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map, JDViewKitVirtualBannerConfig jDViewKitVirtualBannerConfig, boolean z, JDViewKitChannelModel jDViewKitChannelModel) {
        this.mViewCache = new LinkedList();
        this.circle = true;
        this.currentItem = 0;
        this.mDatas = list;
        this.dslsMap = map;
        this.config = jDViewKitVirtualBannerConfig;
        this.circle = z;
        this.channelModel = jDViewKitChannelModel;
        this.layoutParams = new ViewGroup.LayoutParams(GlobalManage.getInstance().getRealPx(jDViewKitVirtualBannerConfig.getCenterSizeWidth(), jDViewKitChannelModel), GlobalManage.getInstance().getRealPx(jDViewKitVirtualBannerConfig.getCenterSizeHeigh(), jDViewKitChannelModel));
    }
}

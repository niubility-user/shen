package com.jingdong.common.unification.unipage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.UnConstants;
import com.jingdong.common.unification.uniutil.UnFileUtils;
import com.jingdong.common.utils.CooTouchImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.photo.PhotoUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class CooImageActivity extends BaseActivity {
    private static final String TAG = "CooImageActivity";
    private CooImageAdapter adapter;
    private ImageButton backButton;
    private ImageButton deleteButton;
    JDDisplayImageOptions displayImageOptions;
    private TextView indicatorTextView;
    private ViewPager viewPager;
    private ArrayList<String> mImgUriList = new ArrayList<>();
    private ArrayList<String> mDeleteImgUriList = new ArrayList<>();
    private int mPosition = 0;
    private JDDialog deleteDialog = null;

    /* loaded from: classes6.dex */
    private class CooImageAdapter extends PagerAdapter {
        ArrayList<String> imgUriList;
        private ArrayList<ImageView> views = new ArrayList<>();

        public CooImageAdapter(Context context, ArrayList<String> arrayList) {
            this.imgUriList = arrayList;
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next();
                CooTouchImageView cooTouchImageView = new CooTouchImageView(context);
                cooTouchImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.CooImageAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CooImageActivity.this.finishWithResultOk();
                    }
                });
                this.views.add(cooTouchImageView);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView(this.views.get(i2));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.views.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            int indexOf = this.views.indexOf(obj);
            if (indexOf == -1) {
                return -2;
            }
            return indexOf;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            ImageView imageView = this.views.get(i2);
            viewGroup.addView(imageView);
            JDImageUtils.displayImage(this.imgUriList.get(i2), imageView, CooImageActivity.this.displayImageOptions);
            return imageView;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public int removeView(ViewPager viewPager, int i2) {
            viewPager.setAdapter(null);
            this.views.remove(i2);
            viewPager.setAdapter(this);
            return i2;
        }
    }

    public static void launchForReuslt(Activity activity, int i2, ArrayList<String> arrayList, int i3) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(UnConstants.EXTRA_IMG_URI_LIST, arrayList);
        intent.putExtra(UnConstants.EXTRA_IMG_URI_LIST_POSITION, i3);
        intent.setClass(activity, CooImageActivity.class);
        activity.startActivityForResult(intent, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIndicator(int i2) {
        int size = this.mImgUriList.size();
        if (size != 0) {
            this.indicatorTextView.setText((i2 + 1) + "/" + size);
        }
    }

    public void finishWithResultOk() {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(UnConstants.EXTRA_IMG_URI_LIST_DELETE, this.mDeleteImgUriList);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setUseBasePV(false);
        JDMtaUtils.sendPagePv(this, this, "", "Evaluate_CooImage", "");
        setContentView(R.layout.un_coo_image_activity);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(UnConstants.EXTRA_IMG_URI_LIST);
        this.mImgUriList = stringArrayListExtra;
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            this.mPosition = intent.getIntExtra(UnConstants.EXTRA_IMG_URI_LIST_POSITION, 0);
            this.displayImageOptions = JDDisplayImageOptions.createSimple().considerExifParams(true).isUseThumbnail(false);
            this.backButton = (ImageButton) findViewById(R.id.coo_comment_img_viewer_back);
            this.deleteButton = (ImageButton) findViewById(R.id.coo_comment_img_viewer_delete);
            this.indicatorTextView = (TextView) findViewById(R.id.coo_comment_img_viewer_indicator);
            this.viewPager = (ViewPager) findViewById(R.id.coo_comment_edit_img_pager);
            CooImageAdapter cooImageAdapter = new CooImageAdapter(this, this.mImgUriList);
            this.adapter = cooImageAdapter;
            this.viewPager.setAdapter(cooImageAdapter);
            this.viewPager.setCurrentItem(this.mPosition);
            this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.1
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    File file;
                    CooImageActivity.this.mPosition = i2;
                    String str = (String) CooImageActivity.this.mImgUriList.get(CooImageActivity.this.mPosition);
                    CooImageActivity cooImageActivity = CooImageActivity.this;
                    cooImageActivity.updateIndicator(cooImageActivity.mPosition);
                    if (TextUtils.isEmpty(str) || (file = UnFileUtils.getFile(CooImageActivity.this, Uri.parse(str))) == null || file.exists()) {
                        return;
                    }
                    try {
                        Thread.sleep(300L);
                    } catch (Exception e2) {
                        OKLog.e(CooImageActivity.TAG, e2);
                    }
                    ToastUtils.shortToast(R.string.uni_coo_image_no_pic_local);
                    CooImageActivity.this.mDeleteImgUriList.add(str);
                    CooImageActivity.this.mImgUriList.remove(CooImageActivity.this.mPosition);
                    int removeView = CooImageActivity.this.adapter.removeView(CooImageActivity.this.viewPager, CooImageActivity.this.mPosition);
                    if (removeView == CooImageActivity.this.adapter.getCount()) {
                        removeView--;
                    }
                    CooImageActivity.this.mPosition = removeView;
                    CooImageActivity.this.viewPager.setCurrentItem(removeView);
                    if (CooImageActivity.this.mImgUriList.size() == 0) {
                        CooImageActivity.this.finishWithResultOk();
                    }
                    CooImageActivity cooImageActivity2 = CooImageActivity.this;
                    cooImageActivity2.updateIndicator(cooImageActivity2.mPosition);
                }
            });
            updateIndicator(this.mPosition);
            this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CooImageActivity.this.finishWithResultOk();
                }
            });
            this.deleteButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PhotoUtils.sendComonData(CooImageActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_TOP_RIGHT, 3);
                    CooImageActivity cooImageActivity = CooImageActivity.this;
                    JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
                    CooImageActivity cooImageActivity2 = CooImageActivity.this;
                    cooImageActivity.deleteDialog = jDDialogFactory.createJdDialogWithStyle6(cooImageActivity2, cooImageActivity2.getString(R.string.uni_coo_image_delete), "", CooImageActivity.this.getString(R.string.uni_coo_image_delete_cancel), CooImageActivity.this.getString(R.string.uni_coo_image_delete_ok));
                    CooImageActivity.this.deleteDialog.setCanceledOnTouchOutside(true);
                    CooImageActivity.this.deleteDialog.negButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            CooImageActivity.this.mDeleteImgUriList.add((String) CooImageActivity.this.mImgUriList.get(CooImageActivity.this.mPosition));
                            CooImageActivity.this.mImgUriList.remove(CooImageActivity.this.mPosition);
                            int removeView = CooImageActivity.this.adapter.removeView(CooImageActivity.this.viewPager, CooImageActivity.this.mPosition);
                            if (removeView == CooImageActivity.this.adapter.getCount()) {
                                removeView--;
                            }
                            CooImageActivity.this.mPosition = removeView;
                            CooImageActivity.this.viewPager.setCurrentItem(removeView);
                            if (CooImageActivity.this.mImgUriList.size() == 0) {
                                CooImageActivity.this.finishWithResultOk();
                            }
                            CooImageActivity cooImageActivity3 = CooImageActivity.this;
                            cooImageActivity3.updateIndicator(cooImageActivity3.mPosition);
                            PhotoUtils.sendComonData(CooImageActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_DIALOG_YES, 3);
                            CooImageActivity.this.deleteDialog.dismiss();
                        }
                    });
                    CooImageActivity.this.deleteDialog.posButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.unification.unipage.CooImageActivity.3.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            PhotoUtils.sendComonData(CooImageActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_DIALOG_NO, 3);
                            CooImageActivity.this.deleteDialog.dismiss();
                        }
                    });
                    CooImageActivity.this.deleteDialog.show();
                }
            });
            return;
        }
        ToastUtils.shortToast(R.string.uni_coo_image_no_pic);
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            finishWithResultOk();
            return true;
        }
        return false;
    }

    public static void launchForReuslt(Activity activity, int i2, ArrayList<String> arrayList, int i3, String str) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(UnConstants.EXTRA_IMG_URI_LIST, arrayList);
        intent.putExtra(UnConstants.EXTRA_IMG_URI_LIST_POSITION, i3);
        intent.putExtra(UnConstants.EXTRA_SOURCE_FROM, str);
        intent.setClass(activity, CooImageActivity.class);
        activity.startActivityForResult(intent, i2);
    }
}

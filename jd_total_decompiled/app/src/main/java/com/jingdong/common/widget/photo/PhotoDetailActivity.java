package com.jingdong.common.widget.photo;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.ui.HorizontalListView;
import com.jingdong.common.utils.CooTouchImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class PhotoDetailActivity extends BaseActivity {
    private static final int H_LISTVIEW_SCROLLER_RIGHT = 9999;
    private static final String TAG = "PhotoDetailActivity";
    private String bucketId;
    private Button mConfirmBtn;
    private HorizontalListView mHorizontalListView;
    private ArrayList<String> mPhotoList;
    private PickedPhotoAdapter mPickedAdapter;
    private ArrayList<String> mSelectedList;
    private ImageView mTitleRight;
    private ViewPager mViewPager;
    private View titleRightContainer;
    private int maxCount = -1;
    private int mPosition = 0;
    private int source = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class PhotoAdapter extends FragmentStatePagerAdapter {
        public PhotoAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            if (PhotoDetailActivity.this.mPhotoList == null) {
                return 0;
            }
            return PhotoDetailActivity.this.mPhotoList.size();
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i2) {
            OKLog.d(PhotoDetailActivity.TAG, "PhotoAdapter:getItem:" + i2);
            return PhotoFragment.newInstance(i2, PhotoDetailActivity.this.mPhotoList != null ? (String) PhotoDetailActivity.this.mPhotoList.get(i2) : null);
        }
    }

    /* loaded from: classes12.dex */
    public static class PhotoFragment extends Fragment {
        JDDisplayImageOptions options = JDDisplayImageOptions.createSimple().cacheInMemory(false);
        private String path = "";
        private int postion = -1;

        static PhotoFragment newInstance(int i2, String str) {
            PhotoFragment photoFragment = new PhotoFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("postion", i2);
            if (str == null) {
                str = "";
            }
            bundle.putString("path", str);
            photoFragment.setArguments(bundle);
            return photoFragment;
        }

        @Override // androidx.fragment.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Bundle arguments = getArguments();
            if (arguments != null) {
                this.postion = arguments.getInt("position");
                this.path = arguments.getString("path");
            }
        }

        @Override // androidx.fragment.app.Fragment
        @Nullable
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            if (OKLog.D) {
                OKLog.i(PhotoDetailActivity.TAG, " onCreateView-->> ");
            }
            if (getActivity() == null) {
                return super.onCreateView(layoutInflater, viewGroup, bundle);
            }
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            CooTouchImageView cooTouchImageView = new CooTouchImageView(getActivity());
            linearLayout.addView(cooTouchImageView, new LinearLayout.LayoutParams(-1, -1));
            cooTouchImageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.PhotoFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OKLog.d(PhotoDetailActivity.TAG, "touchImageView onCLick");
                    if (PhotoFragment.this.getActivity() == null || !(PhotoFragment.this.getActivity() instanceof PhotoDetailActivity)) {
                        return;
                    }
                    ((PhotoDetailActivity) PhotoFragment.this.getActivity()).onBackPressed();
                }
            });
            cooTouchImageView.setContentDescription(PhotoUtils.getImageContentDescription(this.path, false));
            JDImageUtils.displayImage("file://" + this.path, cooTouchImageView, this.options);
            return linearLayout;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class PickedPhotoAdapter extends BaseAdapter {
        private ArrayList<String> pickedList;

        public PickedPhotoAdapter(ArrayList<String> arrayList) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            this.pickedList = arrayList2;
            if (arrayList != null) {
                arrayList2.clear();
                this.pickedList.addAll(arrayList);
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            ArrayList<String> arrayList = this.pickedList;
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            ArrayList<String> arrayList = this.pickedList;
            if (arrayList == null) {
                return null;
            }
            return arrayList.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            PickedPhotoHolder pickedPhotoHolder;
            if (view == null) {
                view = PhotoDetailActivity.this.getLayoutInflater().inflate(R.layout.lib_layout_photo_picked_item, (ViewGroup) null);
                pickedPhotoHolder = new PickedPhotoHolder();
                pickedPhotoHolder.pickedPhoto = (SimpleDraweeView) view.findViewById(R.id.lib_picked_photo);
                pickedPhotoHolder.deleteIcon = view.findViewById(R.id.lib_photo_delete_icon_container);
                view.setTag(pickedPhotoHolder);
            } else {
                pickedPhotoHolder = (PickedPhotoHolder) view.getTag();
            }
            final String str = this.pickedList.get(i2);
            pickedPhotoHolder.pickedPhoto.setContentDescription("\u56fe" + (i2 + 1));
            JDImageUtils.displayImage("file://" + str, pickedPhotoHolder.pickedPhoto);
            pickedPhotoHolder.deleteIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.PickedPhotoAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    PhotoDetailActivity.this.mSelectedList.remove(str);
                    if (PhotoDetailActivity.this.mSelectedList.size() <= 0) {
                        PhotoDetailActivity.this.mConfirmBtn.setText(R.string.uni_photo_select_pic_over);
                    } else {
                        Button button = PhotoDetailActivity.this.mConfirmBtn;
                        PhotoDetailActivity photoDetailActivity = PhotoDetailActivity.this;
                        button.setText(photoDetailActivity.format(photoDetailActivity.mSelectedList.size(), PhotoDetailActivity.this.maxCount));
                        PhotoDetailActivity.this.mConfirmBtn.setEnabled(true);
                    }
                    PhotoDetailActivity.this.mPickedAdapter.setPhotoList(PhotoDetailActivity.this.mSelectedList);
                    PhotoDetailActivity.this.mPickedAdapter.notifyDataSetChanged();
                    if (PhotoDetailActivity.this.mPosition >= 0 && PhotoDetailActivity.this.mPosition < PhotoDetailActivity.this.mPhotoList.size() && ((String) PhotoDetailActivity.this.mPhotoList.get(PhotoDetailActivity.this.mPosition)).equals(str)) {
                        PhotoDetailActivity.this.titleRightContainer.setContentDescription("\u672a\u9009\u4e2d");
                        PhotoDetailActivity.this.mTitleRight.setImageResource(R.drawable.lib_photo_not_selected_icon);
                    }
                    PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_DELETE_PIC, PhotoDetailActivity.this.source);
                }
            });
            return view;
        }

        public void setPhotoList(ArrayList<String> arrayList) {
            if (arrayList != null) {
                this.pickedList.clear();
                this.pickedList.addAll(arrayList);
            }
        }
    }

    /* loaded from: classes12.dex */
    static class PickedPhotoHolder {
        View deleteIcon;
        SimpleDraweeView pickedPhoto;

        PickedPhotoHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String format(int i2, int i3) {
        if (i2 == 0) {
            return getString(R.string.uni_photo_select_pic_over);
        }
        return getString(R.string.uni_photo_select_pic_over) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + i2 + "/" + i3;
    }

    private void initView() {
        View findViewById = findViewById(R.id.lib_photo_detail_back);
        this.titleRightContainer = findViewById(R.id.lib_photo_detail_selected);
        ImageView imageView = (ImageView) findViewById(R.id.lib_photo_detail_selected_icon);
        this.mTitleRight = imageView;
        int i2 = R.drawable.lib_photo_not_selected_icon;
        imageView.setImageResource(i2);
        this.titleRightContainer.setContentDescription("\u672a\u9009\u4e2d");
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhotoDetailActivity.this.mSelectedList == null) {
                    PhotoDetailActivity.this.mSelectedList = new ArrayList();
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, PhotoDetailActivity.this.mSelectedList);
                PhotoDetailActivity.this.setResult(-1, intent);
                PhotoDetailActivity.this.finish();
            }
        });
        this.titleRightContainer.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhotoDetailActivity.this.mPhotoList == null || PhotoDetailActivity.this.mSelectedList == null || PhotoDetailActivity.this.mConfirmBtn == null || PhotoDetailActivity.this.mPosition < 0 || PhotoDetailActivity.this.mPosition >= PhotoDetailActivity.this.mPhotoList.size()) {
                    return;
                }
                String str = (String) PhotoDetailActivity.this.mPhotoList.get(PhotoDetailActivity.this.mPosition);
                if (PhotoDetailActivity.this.mSelectedList.contains(str)) {
                    PhotoDetailActivity.this.titleRightContainer.setContentDescription("\u672a\u9009\u4e2d");
                    PhotoDetailActivity.this.mTitleRight.setImageResource(R.drawable.lib_photo_not_selected_icon);
                    PhotoDetailActivity.this.mSelectedList.remove(str);
                    if (PhotoDetailActivity.this.mSelectedList.size() <= 0) {
                        PhotoDetailActivity.this.mConfirmBtn.setText(R.string.uni_photo_select_pic_over);
                    } else {
                        Button button = PhotoDetailActivity.this.mConfirmBtn;
                        PhotoDetailActivity photoDetailActivity = PhotoDetailActivity.this;
                        button.setText(photoDetailActivity.format(photoDetailActivity.mSelectedList.size(), PhotoDetailActivity.this.maxCount));
                        PhotoDetailActivity.this.mConfirmBtn.setEnabled(true);
                    }
                    PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_DROP_PIC, PhotoDetailActivity.this.source);
                } else {
                    if (PhotoDetailActivity.this.source == 2) {
                        PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_SELECTED_PIC, PhotoDetailActivity.this.source);
                    }
                    if (PhotoDetailActivity.this.maxCount > 0 && PhotoDetailActivity.this.mSelectedList.size() >= PhotoDetailActivity.this.maxCount) {
                        PhotoDetailActivity photoDetailActivity2 = PhotoDetailActivity.this;
                        ToastUtils.shortToast(photoDetailActivity2, photoDetailActivity2.getString(R.string.uni_photo_select_pic_max, new Object[]{Integer.valueOf(photoDetailActivity2.maxCount)}));
                        return;
                    } else if (PhotoUtils.checkLimit(PhotoDetailActivity.this, str)) {
                        return;
                    } else {
                        PhotoDetailActivity.this.titleRightContainer.setContentDescription("\u5df2\u9009\u4e2d");
                        PhotoDetailActivity.this.mTitleRight.setImageResource(R.drawable.lib_photo_selected_icon);
                        PhotoDetailActivity.this.mSelectedList.add(str);
                        Button button2 = PhotoDetailActivity.this.mConfirmBtn;
                        PhotoDetailActivity photoDetailActivity3 = PhotoDetailActivity.this;
                        button2.setText(photoDetailActivity3.format(photoDetailActivity3.mSelectedList.size(), PhotoDetailActivity.this.maxCount));
                        PhotoDetailActivity.this.mConfirmBtn.setEnabled(true);
                        if (PhotoDetailActivity.this.source == 1) {
                            PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_SELECTED_PIC, PhotoDetailActivity.this.source);
                        }
                    }
                }
                if (PhotoDetailActivity.this.mPickedAdapter != null) {
                    PhotoDetailActivity.this.mPickedAdapter.setPhotoList(PhotoDetailActivity.this.mSelectedList);
                    PhotoDetailActivity.this.mPickedAdapter.notifyDataSetChanged();
                    PhotoDetailActivity.this.mHorizontalListView.scrollTo(9999);
                }
            }
        });
        ArrayList<String> photos = PhotoUtils.getPhotos(this, this.bucketId);
        this.mPhotoList = photos;
        if (this.mPosition == 0 && photos.size() > 0 && this.mSelectedList.contains(this.mPhotoList.get(0))) {
            this.titleRightContainer.setContentDescription("\u5df2\u9009\u4e2d");
            this.mTitleRight.setImageResource(R.drawable.lib_photo_selected_icon);
        }
        this.mViewPager = (ViewPager) findViewById(R.id.lib_photo_detail_viewpager);
        this.mViewPager.setAdapter(new PhotoAdapter(getSupportFragmentManager()));
        int i3 = this.mPosition;
        if (i3 >= 0 && i3 < this.mPhotoList.size()) {
            this.mViewPager.setCurrentItem(this.mPosition);
            if (this.mSelectedList.contains(this.mPhotoList.get(this.mPosition))) {
                this.titleRightContainer.setContentDescription("\u5df2\u9009\u4e2d");
                this.mTitleRight.setImageResource(R.drawable.lib_photo_selected_icon);
            } else {
                this.titleRightContainer.setContentDescription("\u672a\u9009\u4e2d");
                this.mTitleRight.setImageResource(i2);
            }
        }
        this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i4) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i4, float f2, int i5) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i4) {
                if (OKLog.D) {
                    OKLog.d(PhotoDetailActivity.TAG, "onPageSelected:" + i4);
                }
                PhotoDetailActivity.this.mPosition = i4;
                if (i4 >= 0 && i4 < PhotoDetailActivity.this.mPhotoList.size()) {
                    if (PhotoDetailActivity.this.mSelectedList.contains(PhotoDetailActivity.this.mPhotoList.get(i4))) {
                        PhotoDetailActivity.this.titleRightContainer.setContentDescription("\u5df2\u9009\u4e2d");
                        PhotoDetailActivity.this.mTitleRight.setImageResource(R.drawable.lib_photo_selected_icon);
                    } else {
                        PhotoDetailActivity.this.titleRightContainer.setContentDescription("\u672a\u9009\u4e2d");
                        PhotoDetailActivity.this.mTitleRight.setImageResource(R.drawable.lib_photo_not_selected_icon);
                    }
                }
                PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_PAGE_SELECTED, PhotoDetailActivity.this.source);
            }
        });
        this.mViewPager.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OKLog.d(PhotoDetailActivity.TAG, "mViewPager onCLick");
            }
        });
        this.mConfirmBtn = (Button) findViewById(R.id.lib_photo_detail_confirm);
        if (this.mSelectedList.size() > 0) {
            this.mConfirmBtn.setEnabled(true);
        }
        this.mConfirmBtn.setText(format(this.mSelectedList.size(), this.maxCount));
        FontsUtil.changeTextFont(this.mConfirmBtn);
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoDetailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PhotoDetailActivity.this.mSelectedList == null) {
                    return;
                }
                Intent intent = new Intent();
                intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, PhotoDetailActivity.this.mSelectedList);
                intent.putExtra(PhotoListActivity.BACK_FINISH, true);
                PhotoDetailActivity.this.setResult(-1, intent);
                PhotoDetailActivity.this.finish();
                PhotoUtils.sendComonData(PhotoDetailActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_DETAIL_CONFIRM, PhotoDetailActivity.this.source);
            }
        });
        this.mHorizontalListView = (HorizontalListView) findViewById(R.id.lib_photo_detail_horizontal_view);
        PickedPhotoAdapter pickedPhotoAdapter = new PickedPhotoAdapter(this.mSelectedList);
        this.mPickedAdapter = pickedPhotoAdapter;
        this.mHorizontalListView.setAdapter((ListAdapter) pickedPhotoAdapter);
        this.mHorizontalListView.scrollTo(9999);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0057, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
        com.jingdong.sdk.oklog.OKLog.e(com.jingdong.common.widget.photo.PhotoDetailActivity.TAG, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ArrayList<String> getPhotos() {
        String[] strArr = {"_data", "datetaken", "_id"};
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                ContentResolver contentResolver = getContentResolver();
                Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                String str = this.bucketId;
                cursor = contentResolver.query(uri, strArr, str == null ? null : "bucket_id=?", str == null ? null : new String[]{str}, "_id DESC");
                if (cursor != null) {
                    int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
                    while (cursor.moveToNext()) {
                        arrayList.add(cursor.getString(columnIndexOrThrow));
                    }
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Exception e3) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e3);
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e4) {
            if (OKLog.E) {
                OKLog.e(TAG, e4);
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                    e = e5;
                }
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mSelectedList == null) {
            this.mSelectedList = new ArrayList<>();
        }
        Intent intent = new Intent();
        intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, this.mSelectedList);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.lib_layout_photo_detail);
        Intent intent = getIntent();
        if (intent != null) {
            this.bucketId = intent.getStringExtra(AlbumListActivity.KEY_BUCKET_ID);
            this.maxCount = intent.getIntExtra(AlbumListActivity.KEY_MAX_COUNT, -1);
            this.mSelectedList = intent.getStringArrayListExtra(AlbumListActivity.KEY_SELECTED_PHOTOS);
            this.mPosition = intent.getIntExtra("clickPosition", 0);
            this.source = intent.getIntExtra(AlbumListActivity.SOURCE_TO_ALBUM, -1);
        }
        if (this.mSelectedList == null) {
            this.mSelectedList = new ArrayList<>();
        }
        initView();
        setUseBasePV(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        PhotoUtils.sendPagePV(this, this.source, PhotoUtils.PV_PHOTO_DETAIL);
    }
}

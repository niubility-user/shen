package com.jingdong.common.widget.photo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.UnLog;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.ui.HorizontalListView;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: classes12.dex */
public class PhotoListActivity extends BaseActivity {
    public static final String BACK_FINISH = "back_finish";
    private static final int GRIDVIEW_COLUMN_COUNT = 4;
    private static final int H_LISTVIEW_SCROLLER_RIGHT = 9999;
    private static final int RESULT_CODE_PHOTO_DETAIL = 5;
    private static final String TAG = PhotoListActivity.class.getSimpleName();
    private static final int TAKE_PICTURE = 7788;
    private ImageAdapter adapter;
    private Button btConfirm;
    private String bucketId;
    GridView mGridView;
    HorizontalListView mHorizontalListView;
    private String name;
    public Uri originalUri;
    private String photoLimit;
    private List<PickedImage> photos;
    private PickedPhotoAdapter pickedPhotoAdapter;
    private ArrayList<String> selectedPhotos;
    private int maxCount = -1;
    private int source = -1;
    private JDDisplayImageOptions options = JDDisplayImageOptions.createSimple().considerExifParams(true);

    /* loaded from: classes12.dex */
    public class ImageAdapter extends BaseAdapter {
        LayoutInflater inflater;

        ImageAdapter() {
            PhotoListActivity.this = r1;
            this.inflater = LayoutInflater.from(r1);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (PhotoListActivity.this.photos == null || PhotoListActivity.this.photos.size() == 0) {
                return 1;
            }
            return PhotoListActivity.this.photos.size() + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (i2 == 0) {
                return 0;
            }
            if (PhotoListActivity.this.photos == null || PhotoListActivity.this.photos.size() == 0) {
                return null;
            }
            return PhotoListActivity.this.photos.get(i2 - 1);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            return i2 == 0 ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(final int i2, View view, ViewGroup viewGroup) {
            if (i2 == 0) {
                return view == null ? this.inflater.inflate(R.layout.lib_item_camera_list, (ViewGroup) null) : view;
            }
            if (view == null) {
                view = this.inflater.inflate(R.layout.lib_item_photo_list, (ViewGroup) null);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.image = (ImageView) view.findViewById(R.id.image);
                viewHolder.floatView = view.findViewById(R.id.float_view);
                viewHolder.checked = (ImageView) view.findViewById(R.id.checked);
                viewHolder.checkedLayout = (RelativeLayout) view.findViewById(R.id.checked_layout);
                int width = (DPIUtil.getWidth() - (DPIUtil.dip2px(4.0f) * 5)) / 4;
                if (width > 0) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewHolder.image.getLayoutParams();
                    layoutParams.width = width;
                    layoutParams.height = width;
                    viewHolder.image.setLayoutParams(layoutParams);
                }
                view.setTag(viewHolder);
            }
            final ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            PickedImage pickedImage = (PickedImage) PhotoListActivity.this.photos.get(i2 - 1);
            viewHolder2.image.setContentDescription(PhotoUtils.getImageContentDescription(pickedImage.getPath(), false));
            JDImageUtils.displayImage("file://" + pickedImage.getPath(), viewHolder2.image, PhotoListActivity.this.options);
            if (PhotoListActivity.this.selectedPhotos != null) {
                if (PhotoListActivity.this.selectedPhotos.contains(pickedImage.getPath())) {
                    pickedImage.setPicked(true);
                } else {
                    pickedImage.setPicked(false);
                }
            }
            if (pickedImage.isPicked()) {
                viewHolder2.checked.setContentDescription("\u5df2\u9009\u4e2d");
                viewHolder2.floatView.setVisibility(0);
                viewHolder2.checked.setImageResource(R.drawable.lib_photo_selected_icon);
            } else {
                viewHolder2.checked.setContentDescription("\u672a\u9009\u4e2d");
                viewHolder2.floatView.setVisibility(8);
                viewHolder2.checked.setImageResource(R.drawable.lib_photolist_not_selected);
            }
            viewHolder2.checkedLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.ImageAdapter.1
                {
                    ImageAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OKLog.d(PhotoListActivity.TAG, "onClick:checked:position:" + i2);
                    PhotoListActivity.this.onCheckViewClick(i2 + (-1), viewHolder2);
                }
            });
            viewHolder2.image.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.ImageAdapter.2
                {
                    ImageAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OKLog.d(PhotoListActivity.TAG, "onClick:image");
                    PhotoUtils.sendComonData(PhotoListActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_PREVIEW_PIC, PhotoListActivity.this.source);
                    Intent intent = new Intent();
                    intent.setClass(PhotoListActivity.this, PhotoDetailActivity.class);
                    intent.putStringArrayListExtra(AlbumListActivity.KEY_SELECTED_PHOTOS, PhotoListActivity.this.selectedPhotos);
                    intent.putExtra(AlbumListActivity.KEY_MAX_COUNT, PhotoListActivity.this.maxCount);
                    intent.putExtra("clickPosition", i2 - 1);
                    intent.putExtra(AlbumListActivity.SOURCE_TO_ALBUM, PhotoListActivity.this.source);
                    intent.putExtra(AlbumListActivity.KEY_BUCKET_ID, PhotoListActivity.this.bucketId);
                    PhotoListActivity.this.startActivityForResult(intent, 5);
                }
            });
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }
    }

    /* loaded from: classes12.dex */
    public class PickedImage {
        private boolean isPicked;
        private String path;

        public PickedImage(String str, boolean z) {
            PhotoListActivity.this = r1;
            this.path = str;
            this.isPicked = z;
        }

        public String getPath() {
            return this.path;
        }

        public boolean isPicked() {
            return this.isPicked;
        }

        public void setPath(String str) {
            this.path = str;
        }

        public void setPicked(boolean z) {
            this.isPicked = z;
        }
    }

    /* loaded from: classes12.dex */
    public class PickedPhotoAdapter extends BaseAdapter {
        private ArrayList<String> pickedList;

        public PickedPhotoAdapter(ArrayList<String> arrayList) {
            PhotoListActivity.this = r1;
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
                view = PhotoListActivity.this.getLayoutInflater().inflate(R.layout.lib_layout_photo_picked_item, (ViewGroup) null);
                pickedPhotoHolder = new PickedPhotoHolder();
                pickedPhotoHolder.pickedPhoto = (SimpleDraweeView) view.findViewById(R.id.lib_picked_photo);
                pickedPhotoHolder.deleteIcon = (ImageView) view.findViewById(R.id.lib_photo_delete_icon);
                pickedPhotoHolder.deleteIconContainer = view.findViewById(R.id.lib_photo_delete_icon_container);
                view.setTag(pickedPhotoHolder);
            } else {
                pickedPhotoHolder = (PickedPhotoHolder) view.getTag();
            }
            final String str = this.pickedList.get(i2);
            pickedPhotoHolder.pickedPhoto.setContentDescription("\u56fe" + (i2 + 1));
            JDImageUtils.displayImage("file://" + str, pickedPhotoHolder.pickedPhoto, PhotoListActivity.this.options);
            pickedPhotoHolder.deleteIconContainer.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.PickedPhotoAdapter.1
                {
                    PickedPhotoAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    PhotoListActivity.this.selectedPhotos.remove(str);
                    PhotoListActivity.this.pickedPhotoAdapter.setPhotoList(PhotoListActivity.this.selectedPhotos);
                    PhotoListActivity.this.pickedPhotoAdapter.notifyDataSetChanged();
                    PhotoListActivity.this.adapter.notifyDataSetChanged();
                    if (PhotoListActivity.this.selectedPhotos.size() <= 0) {
                        PhotoListActivity.this.btConfirm.setText(R.string.uni_photo_select_pic_over);
                    } else {
                        Button button = PhotoListActivity.this.btConfirm;
                        PhotoListActivity photoListActivity = PhotoListActivity.this;
                        button.setText(photoListActivity.format(photoListActivity.selectedPhotos.size(), PhotoListActivity.this.maxCount));
                        PhotoListActivity.this.btConfirm.setEnabled(true);
                    }
                    PhotoUtils.sendComonData(PhotoListActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_DELETE, PhotoListActivity.this.source);
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
        ImageView deleteIcon;
        View deleteIconContainer;
        SimpleDraweeView pickedPhoto;

        PickedPhotoHolder() {
        }
    }

    /* loaded from: classes12.dex */
    public static class ViewHolder {
        ImageView checked;
        RelativeLayout checkedLayout;
        View floatView;
        ImageView image;

        ViewHolder() {
        }
    }

    private void dropPhoto(String str) {
        ArrayList<String> arrayList = this.selectedPhotos;
        if (arrayList == null || arrayList.size() == 0) {
            return;
        }
        PhotoUtils.sendComonData(getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_DROP_PIC, this.source);
        this.selectedPhotos.remove(str);
        PickedPhotoAdapter pickedPhotoAdapter = this.pickedPhotoAdapter;
        if (pickedPhotoAdapter != null) {
            pickedPhotoAdapter.setPhotoList(this.selectedPhotos);
            this.pickedPhotoAdapter.notifyDataSetChanged();
        }
        if (this.selectedPhotos.size() == 0) {
            this.btConfirm.setText(R.string.uni_photo_select_pic_over);
        } else {
            this.btConfirm.setText(format(this.selectedPhotos.size(), this.maxCount));
        }
    }

    public String format(int i2, int i3) {
        if (i2 == 0) {
            return getString(R.string.uni_photo_select_pic_over);
        }
        return getString(R.string.uni_photo_select_pic_over) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + i2 + "/" + i3;
    }

    private void galleryAddPic(String str) {
        if (str == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        sendBroadcast(intent);
    }

    public void getFromCamera() {
        if (!PhotoUtils.isCameraCanUse()) {
            ToastUtils.shortToast(R.string.uni_photo_camera_permission);
        } else if (CommonBase.checkSDcard()) {
            PhotoUtils.sendComonData(getBaseContext(), PhotoUtils.CLICK_PHOTO_TAKE_PIC, this.source);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/jd/userPhoto");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, System.currentTimeMillis() + ".jpg");
            this.originalUri = Uri.fromFile(file2);
            intent.putExtra("output", getPictureUri(file2, intent));
            try {
                startActivityForResult(intent, 7788);
            } catch (Exception e2) {
                ToastUtils.shortToast(R.string.uni_photo_camera_permission);
                OKLog.e(TAG, e2);
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.camera_hint_title);
            builder.setMessage(R.string.camera_hint_message);
            builder.setPositiveButton(R.string.alert_comment_discuss_ok, new DialogInterface.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.5
                {
                    PhotoListActivity.this = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    private String getPicturePath(String str) {
        return (str == null || !str.contains("camera_photos")) ? str : str.replace("camera_photos", Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private Uri getPictureUri(File file, Intent intent) {
        if (Build.VERSION.SDK_INT > 23) {
            Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileProvider", file);
            if (intent != null) {
                intent.addFlags(3);
                return uriForFile;
            }
            return uriForFile;
        }
        return Uri.fromFile(file);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.bucketId = intent.getStringExtra(AlbumListActivity.KEY_BUCKET_ID);
            this.maxCount = intent.getIntExtra(AlbumListActivity.KEY_MAX_COUNT, -1);
            this.name = intent.getStringExtra("name");
            this.selectedPhotos = intent.getStringArrayListExtra(AlbumListActivity.KEY_SELECTED_PHOTOS);
            this.source = intent.getIntExtra(AlbumListActivity.SOURCE_TO_ALBUM, -1);
            this.photoLimit = intent.getStringExtra(AlbumListActivity.KEY_PHOTO_LIMIT);
        }
        if (this.selectedPhotos == null) {
            this.selectedPhotos = new ArrayList<>();
        }
        this.options.cacheInMemory(true);
        this.options.cacheOnDisk(false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 16;
        this.options.decodingOptions(options);
        PhotoUtils.initPhotoLimitParams(this.photoLimit);
        this.photos = getPhotos(this);
        ImageAdapter imageAdapter = this.adapter;
        if (imageAdapter != null) {
            imageAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.titleText);
        if (textView != null) {
            textView.setText(this.name);
        }
        TextView textView2 = (TextView) findViewById(R.id.common_title_tight_textView);
        if (textView2 != null) {
            textView2.setText(R.string.alert_comment_discuss_cancel);
            textView2.setTextColor(getResources().getColor(R.color.c_252525));
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.1
                {
                    PhotoListActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PhotoUtils.sendComonData(PhotoListActivity.this.getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_CANCEL, PhotoListActivity.this.source);
                    PhotoListActivity.this.setResult(2);
                    PhotoListActivity.this.finish();
                }
            });
            textView2.setVisibility(0);
        }
        ImageView imageView = (ImageView) findViewById(R.id.title_back);
        if (imageView != null) {
            imageView.setImageResource(R.drawable.common_title_back_selector);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.2
                {
                    PhotoListActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PhotoListActivity.this.onBackPressed();
                }
            });
            imageView.setVisibility(0);
        }
        this.mHorizontalListView = (HorizontalListView) findViewById(R.id.lib_photo_horizontal_view);
        PickedPhotoAdapter pickedPhotoAdapter = new PickedPhotoAdapter(this.selectedPhotos);
        this.pickedPhotoAdapter = pickedPhotoAdapter;
        this.mHorizontalListView.setAdapter((ListAdapter) pickedPhotoAdapter);
        this.btConfirm = (Button) findViewById(R.id.confirm);
        if (this.selectedPhotos.size() > 0) {
            this.btConfirm.setEnabled(true);
        }
        FontsUtil.changeTextFont(this.btConfirm);
        this.btConfirm.setText(format(this.selectedPhotos.size(), this.maxCount));
        this.btConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.3
            {
                PhotoListActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhotoListActivity.this.onConfirm();
            }
        });
        this.mGridView = (GridView) findViewById(R.id.gridview);
        if (this.adapter == null) {
            this.adapter = new ImageAdapter();
        }
        this.mGridView.setAdapter((ListAdapter) this.adapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.4
            {
                PhotoListActivity.this = this;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                if (UnLog.D) {
                    UnLog.d(PhotoListActivity.TAG, "position " + i2);
                }
                if (i2 == 0) {
                    if (PhotoListActivity.this.maxCount > 0 && PhotoListActivity.this.selectedPhotos.size() >= PhotoListActivity.this.maxCount) {
                        PhotoListActivity photoListActivity = PhotoListActivity.this;
                        ToastUtils.longToast(photoListActivity, photoListActivity.getString(R.string.uni_photo_select_pic_max, new Object[]{Integer.valueOf(photoListActivity.maxCount)}));
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("android.permission.CAMERA");
                    if (PermissionHelper.hasPermission(PhotoListActivity.this, arrayList)) {
                        PhotoListActivity.this.getFromCamera();
                        return;
                    }
                    Bundle generateBundle = PermissionHelper.generateBundle("PhotoList", "PhotoListActivity", "onItemClick");
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add("\u6444\u50cf\u5934");
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add("\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u6444\u50cf\u5934\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u6b63\u5e38\u4f7f\u7528\u62cd\u6444\u7167\u7247\u6216\u89c6\u9891\u4ee5\u53ca\u9884\u89c8\u529f\u80fd");
                    PermissionHelper.requestPermission(PhotoListActivity.this, generateBundle, arrayList, new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.widget.photo.PhotoListActivity.4.1
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onCanceled() {
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onDenied() {
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onGranted() {
                            PhotoListActivity.this.getFromCamera();
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onIgnored() {
                        }

                        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                        public void onOpenSetting() {
                        }
                    }, arrayList2, arrayList3);
                }
            }
        });
    }

    public void onCheckViewClick(int i2, ViewHolder viewHolder) {
        List<PickedImage> list = this.photos;
        if (list != null && i2 >= 0 && i2 < list.size()) {
            PickedImage pickedImage = this.photos.get(i2);
            boolean z = !pickedImage.isPicked();
            if (z && this.maxCount > 0 && this.selectedPhotos.size() >= this.maxCount) {
                if (this.source == 2) {
                    PhotoUtils.sendComonData(getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_SELECTED_PIC, this.source);
                }
                ToastUtils.longToast(this, getString(R.string.uni_photo_select_pic_max, new Object[]{Integer.valueOf(this.maxCount)}));
            } else if (z && PhotoUtils.checkLimit(this, pickedImage.getPath())) {
            } else {
                pickedImage.setPicked(z);
                if (z) {
                    PhotoUtils.sendComonData(getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_SELECTED_PIC, this.source);
                    viewHolder.floatView.setVisibility(0);
                    viewHolder.checked.setContentDescription("\u5df2\u9009\u4e2d");
                    viewHolder.checked.setImageResource(R.drawable.lib_photo_selected_icon);
                    pickPhoto(pickedImage.getPath());
                    if (OKLog.D) {
                        OKLog.d(TAG, "checked:" + pickedImage.getPath());
                        return;
                    }
                    return;
                }
                viewHolder.checked.setContentDescription("\u672a\u9009\u4e2d");
                viewHolder.floatView.setVisibility(8);
                viewHolder.checked.setImageResource(R.drawable.lib_photolist_not_selected);
                dropPhoto(pickedImage.getPath());
            }
        }
    }

    public void onConfirm() {
        if (this.selectedPhotos == null) {
            return;
        }
        PhotoUtils.sendComonData(getBaseContext(), PhotoUtils.CLICK_PHOTO_LIST_CONFIRM, this.source);
        Intent intent = new Intent();
        intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, this.selectedPhotos);
        intent.putExtra(BACK_FINISH, true);
        setResult(-1, intent);
        finish();
    }

    private void pickPhoto(String str) {
        if (this.maxCount <= 0 || this.selectedPhotos.size() < this.maxCount) {
            if (this.selectedPhotos == null) {
                this.selectedPhotos = new ArrayList<>();
            }
            this.selectedPhotos.add(str);
            PickedPhotoAdapter pickedPhotoAdapter = this.pickedPhotoAdapter;
            if (pickedPhotoAdapter != null) {
                pickedPhotoAdapter.setPhotoList(this.selectedPhotos);
                this.pickedPhotoAdapter.notifyDataSetChanged();
                this.mHorizontalListView.scrollTo(9999);
            }
            if (this.selectedPhotos.size() > 0 && !this.btConfirm.isEnabled()) {
                this.btConfirm.setEnabled(true);
            }
            this.btConfirm.setText(format(this.selectedPhotos.size(), this.maxCount));
        }
    }

    public List<PickedImage> getPhotos(Context context) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = PhotoUtils.getPhotos(context, this.bucketId).iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(new PickedImage(next, this.selectedPhotos.contains(next)));
        }
        return arrayList;
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        String str;
        if (i3 != -1) {
            return;
        }
        if (i2 != 5) {
            if (i2 != 7788) {
                return;
            }
            if (intent == null) {
                str = DYConstants.DY_TRUE;
            } else {
                str = intent.getData() + "";
            }
            OKLog.e(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP, str);
            Uri uri = this.originalUri;
            if (uri != null) {
                String path = uri.getPath();
                galleryAddPic(path);
                List<PickedImage> list = this.photos;
                if (list != null) {
                    list.add(0, new PickedImage(path, true));
                }
                pickPhoto(path);
                this.adapter.notifyDataSetChanged();
            }
        } else if (intent == null || this.pickedPhotoAdapter == null || this.photos == null || this.adapter == null) {
        } else {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS);
            if (intent.getBooleanExtra(BACK_FINISH, false)) {
                if (stringArrayListExtra == null) {
                    stringArrayListExtra = new ArrayList<>();
                }
                Intent intent2 = new Intent();
                intent2.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, stringArrayListExtra);
                intent2.putExtra(BACK_FINISH, true);
                setResult(-1, intent2);
                finish();
            } else if (stringArrayListExtra != null) {
                this.selectedPhotos = stringArrayListExtra;
                this.pickedPhotoAdapter.setPhotoList(stringArrayListExtra);
                this.pickedPhotoAdapter.notifyDataSetChanged();
                this.mHorizontalListView.scrollTo(9999);
                this.adapter.notifyDataSetChanged();
                if (this.selectedPhotos.size() <= 0) {
                    this.btConfirm.setText(R.string.uni_photo_select_pic_over);
                    return;
                }
                this.btConfirm.setText(format(this.selectedPhotos.size(), this.maxCount));
                this.btConfirm.setEnabled(true);
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        ArrayList<String> arrayList = this.selectedPhotos;
        if (arrayList != null) {
            intent.putStringArrayListExtra(AlbumListActivity.KEY_RESULT_PHOTOS, arrayList);
        }
        setResult(-1, intent);
        finish();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.lib_layout_photo_list);
        initData();
        initView();
        setUseBasePV(false);
        JDMtaUtils.sendPagePv(this, this, "", "Evaluate_PhotoAlbum", "");
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        PhotoUtils.sendPagePV(this, this.source, PhotoUtils.PV_PHOTO_LIST);
    }
}

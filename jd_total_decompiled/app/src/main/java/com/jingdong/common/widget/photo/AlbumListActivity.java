package com.jingdong.common.widget.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.com.union.fido.common.MIMEType;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeepLinkAlbumHelper;
import com.jingdong.common.unification.album.AlbumConstant;
import com.jingdong.common.unification.album.AlbumParam;
import com.jingdong.common.unification.album.LocalMedia;
import com.jingdong.common.unification.album.UnImageTools;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.widget.ExceptionDrawable;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class AlbumListActivity extends BaseActivity {
    public static final String KEY_BUCKET_ID = "bucketId";
    public static final String KEY_MAX_COUNT = "maxCount";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHOTO_LIMIT = "photo_limit";
    public static final String KEY_RESULT_PHOTOS = "photos";
    public static final String KEY_SELECTED_PHOTOS = "selected_photos";
    public static final int REQUEST_CODE_GET_PHOTOS = 0;
    public static final int RESULT_CODE_EXIT = 2;
    public static final int SOURCE_EVALUATE_EDIT = 2;
    public static final int SOURCE_FEEDBACK = 3;
    public static final int SOURCE_STORY = 1;
    public static final String SOURCE_TO_ALBUM = "source_to_album";
    private static final String TAG = AlbumListActivity.class.getSimpleName();
    private List<Album> albums;
    private String photoLimit;
    private ArrayList<String> selectedPhotos;
    private JDDisplayImageOptions options = JDDisplayImageOptions.createSimple();
    private int maxCount = -1;
    private int source = -1;

    /* loaded from: classes12.dex */
    public class Album {
        private String cover;
        private String id;
        private String name;

        public Album(String str, String str2, String str3) {
            this.id = str;
            this.name = str2;
            this.cover = str3;
        }

        public String getCover() {
            return this.cover;
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public void setCover(String str) {
            this.cover = str;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes12.dex */
    class AlbumAdapter extends BaseAdapter {
        LayoutInflater inflater;

        AlbumAdapter() {
            this.inflater = LayoutInflater.from(AlbumListActivity.this);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (AlbumListActivity.this.albums == null || AlbumListActivity.this.albums.size() == 0) {
                return 0;
            }
            return AlbumListActivity.this.albums.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            if (AlbumListActivity.this.albums == null || AlbumListActivity.this.albums.size() == 0) {
                return null;
            }
            return AlbumListActivity.this.albums.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.inflater.inflate(R.layout.lib_item_album_list, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.cover = (ImageView) view.findViewById(R.id.cover);
                viewHolder.name = (TextView) view.findViewById(R.id.name);
                view.setTag(viewHolder);
            }
            ViewHolder viewHolder2 = (ViewHolder) view.getTag();
            Album album = (Album) AlbumListActivity.this.albums.get(i2);
            if (album.getCover() != null) {
                UnImageTools.loadImageWithAlbum(album.cover, viewHolder2.cover);
            } else {
                viewHolder2.cover.setImageDrawable(new ExceptionDrawable(JdSdk.getInstance().getApplication().getApplicationContext(), StringUtil.app_name));
            }
            if (album.getName() != null) {
                viewHolder2.name.setText(album.getName());
            }
            return view;
        }
    }

    /* loaded from: classes12.dex */
    static class ViewHolder {
        ImageView cover;
        TextView name;

        ViewHolder() {
        }
    }

    private AlbumParam createParam() {
        AlbumParam albumParam = new AlbumParam();
        if (this.maxCount <= 0) {
            this.maxCount = 1;
        }
        albumParam.canSelectMediaCount = this.maxCount;
        albumParam.loadCameraOrVideo = 1;
        albumParam.source = String.valueOf(this.source);
        albumParam.isUsePreviewPage = true;
        ArrayList<String> arrayList = this.selectedPhotos;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<String> it = this.selectedPhotos.iterator();
            while (it.hasNext()) {
                LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(it.next());
                localMedia.setPictureType(MIMEType.MIME_TYPE_JPEG);
                arrayList2.add(localMedia);
            }
            albumParam.selectedMedia = arrayList2;
        }
        return albumParam;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToPhotoList(int i2) {
        if (i2 >= this.albums.size()) {
            return;
        }
        Album album = this.albums.get(i2);
        Intent intent = new Intent();
        intent.setClass(this, getPhoneListActivityClass());
        intent.putExtra(KEY_BUCKET_ID, album.getId());
        intent.putExtra(KEY_MAX_COUNT, this.maxCount);
        intent.putExtra("name", album.getName());
        ArrayList<String> arrayList = this.selectedPhotos;
        if (arrayList != null) {
            intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, arrayList);
        }
        intent.putExtra(SOURCE_TO_ALBUM, this.source);
        if (!TextUtils.isEmpty(this.photoLimit)) {
            intent.putExtra(KEY_PHOTO_LIMIT, this.photoLimit);
        }
        startActivityForResult(intent, 0);
    }

    private void initData() {
        this.albums = getAlbums(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.maxCount = intent.getIntExtra(KEY_MAX_COUNT, -1);
            this.selectedPhotos = intent.getStringArrayListExtra(KEY_RESULT_PHOTOS);
            this.source = intent.getIntExtra(SOURCE_TO_ALBUM, -1);
            this.photoLimit = intent.getStringExtra(KEY_PHOTO_LIMIT);
        }
        this.options.cacheInMemory(true);
        this.options.cacheOnDisk(false);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 16;
        this.options.decodingOptions(options);
    }

    private void initView() {
        setContentView(R.layout.lib_layout_album_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        if (listView != null) {
            listView.setAdapter((ListAdapter) new AlbumAdapter());
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.jingdong.common.widget.photo.AlbumListActivity.1
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
                    AlbumListActivity.this.goToPhotoList(i2);
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.titleText);
        if (textView != null) {
            textView.setText(R.string.uni_album_list);
        }
        TextView textView2 = (TextView) findViewById(R.id.common_title_tight_textView);
        if (textView2 != null) {
            textView2.setText(R.string.alert_comment_discuss_cancel);
            textView2.setTextColor(getResources().getColor(R.color.c_252525));
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.AlbumListActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AlbumListActivity.this.setResult(2);
                    AlbumListActivity.this.finish();
                }
            });
            textView2.setVisibility(0);
        }
        ImageView imageView = (ImageView) findViewById(R.id.title_back);
        if (imageView != null) {
            imageView.setImageResource(R.drawable.common_title_back_selector);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.photo.AlbumListActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AlbumListActivity.this.finish();
                }
            });
            imageView.setVisibility(0);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c2, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00c4, code lost:
        com.jingdong.sdk.oklog.OKLog.e(com.jingdong.common.widget.photo.AlbumListActivity.TAG, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00df, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E == false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected List<Album> getAlbums(Context context) {
        String[] strArr = {"bucket_display_name", "bucket_id", "_data", "datetaken"};
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        arrayList.add(new Album(null, getString(R.string.uni_album_list_recent), null));
        try {
            try {
                cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, null, null, "datetaken DESC");
                if (cursor != null) {
                    HashSet hashSet = new HashSet();
                    if (cursor.moveToFirst()) {
                        int columnIndexOrThrow = cursor.getColumnIndexOrThrow("bucket_id");
                        int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("bucket_display_name");
                        int columnIndexOrThrow3 = cursor.getColumnIndexOrThrow("_data");
                        ((Album) arrayList.get(0)).setCover("file://" + cursor.getString(columnIndexOrThrow3));
                        do {
                            String string = cursor.getString(columnIndexOrThrow);
                            String string2 = cursor.getString(columnIndexOrThrow2);
                            String string3 = cursor.getString(columnIndexOrThrow3);
                            if (string != null && string3 != null && !string3.endsWith(".gif") && new File(string3).exists() && !hashSet.contains(string)) {
                                hashSet.add(string);
                                arrayList.add(new Album(string, string2, "file://" + string3));
                            }
                        } while (cursor.moveToNext());
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

    protected Class<? extends Activity> getPhoneListActivityClass() {
        return PhotoListActivity.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 != -1 || intent == null) {
            if (i3 == 2) {
                setResult(2);
                finish();
                return;
            }
            super.onActivityResult(i2, i3, intent);
        } else if (i2 == 0) {
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(AlbumConstant.SELECT_MEDIAS);
            ArrayList<String> arrayList = this.selectedPhotos;
            if (arrayList == null) {
                this.selectedPhotos = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            if (parcelableArrayListExtra != null) {
                Iterator it = parcelableArrayListExtra.iterator();
                while (it.hasNext()) {
                    this.selectedPhotos.add(((LocalMedia) it.next()).getPath());
                }
            }
            Intent intent2 = new Intent();
            intent2.putStringArrayListExtra(KEY_RESULT_PHOTOS, this.selectedPhotos);
            setResult(-1, intent2);
            finish();
        } else {
            super.onActivityResult(i2, i3, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        DeepLinkAlbumHelper.startAlbumActivityForResult(this, createParam(), 0);
    }
}

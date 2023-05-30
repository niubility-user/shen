package com.jd.manto.lbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.manto.map.R;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.refact.lbs.MapAddress;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoCommonHttpHandler;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoMapSearchActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, View.OnTouchListener {
    private MantoSearchPOISuggestAdapter adapter;
    private EditText editTextKeyword;
    private View footer;
    private boolean isLoadingMore;
    private double lat;
    private ListView listView;
    private double lng;
    private View loading;
    private int pageIndex = 1;
    private boolean loadMoreEnable = true;

    static /* synthetic */ int access$308(MantoMapSearchActivity mantoMapSearchActivity) {
        int i2 = mantoMapSearchActivity.pageIndex;
        mantoMapSearchActivity.pageIndex = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search() {
        final String trim = this.editTextKeyword.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return;
        }
        final int i2 = this.pageIndex;
        if (1 == i2 && this.loading.getVisibility() != 0) {
            this.loading.setVisibility(0);
        }
        MantoCommonHttpHandler.getInstance().commit(new MantoPoiSuggestRequest(this.lat, this.lng, i2, MantoMapHelper.getQqMapApiKey(this), trim), new IMantoHttpListener() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.4
            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                super.onError(jSONObject, th);
                MantoMapSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MantoMapSearchActivity.this.isLoadingMore = false;
                        Toast.makeText(MantoMapSearchActivity.this.getApplicationContext(), "\u83b7\u53d6\u6570\u636e\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", 0).show();
                        if (MantoMapSearchActivity.this.loading.getVisibility() == 0) {
                            MantoMapSearchActivity.this.loading.setVisibility(8);
                        }
                    }
                });
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(final JSONObject jSONObject) {
                MantoLog.d("loc", "onSuccess: suggestion:" + jSONObject.toString());
                if (jSONObject.optInt("status") == 0) {
                    final boolean z = false;
                    boolean z2 = jSONObject.optInt("count") > 20;
                    JSONArray optJSONArray = jSONObject.optJSONArray("data");
                    final ArrayList arrayList = new ArrayList();
                    if (optJSONArray.length() > 0) {
                        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                            if (optJSONObject != null) {
                                MapAddress mapAddress = new MapAddress();
                                JSONObject optJSONObject2 = optJSONObject.optJSONObject(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                                if (optJSONObject2 != null) {
                                    mapAddress.latitude = optJSONObject2.optDouble("lat");
                                    mapAddress.longitude = optJSONObject2.optDouble(HybridSDK.LNG);
                                    mapAddress.name = optJSONObject.optString("title");
                                    mapAddress.address = optJSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
                                    arrayList.add(mapAddress);
                                }
                            }
                        }
                        z = z2;
                    } else if (1 == i2) {
                        MantoMapSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MantoMapSearchActivity.this.loadMoreEnable = false;
                                if (MantoMapSearchActivity.this.listView.getFooterViewsCount() > 0) {
                                    MantoMapSearchActivity.this.listView.removeFooterView(MantoMapSearchActivity.this.footer);
                                }
                                MantoMapSearchActivity.this.isLoadingMore = false;
                                Toast.makeText(MantoMapSearchActivity.this.getApplicationContext(), "\u83b7\u53d6\u6570\u636e\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", 0).show();
                                if (MantoMapSearchActivity.this.loading.getVisibility() == 0) {
                                    MantoMapSearchActivity.this.loading.setVisibility(8);
                                }
                            }
                        });
                        return;
                    }
                    MantoMapSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (MantoMapSearchActivity.this.listView.getVisibility() != 0) {
                                MantoMapSearchActivity.this.listView.setVisibility(0);
                            }
                            if (arrayList.size() > 0) {
                                if (MantoMapSearchActivity.this.adapter != null) {
                                    MantoMapSearchActivity.this.adapter.setKeyword(trim);
                                    AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                    if (1 == i2) {
                                        MantoMapSearchActivity.this.adapter.setData(arrayList);
                                        MantoMapSearchActivity.this.adapter.notifyDataSetChanged();
                                        MantoMapSearchActivity.this.listView.smoothScrollToPosition(0);
                                    } else {
                                        MantoMapSearchActivity.this.adapter.appendData(arrayList);
                                        MantoMapSearchActivity.this.adapter.notifyDataSetChanged();
                                    }
                                } else {
                                    MantoMapSearchActivity.this.adapter = new MantoSearchPOISuggestAdapter(MantoMapSearchActivity.this.getApplicationContext());
                                    MantoMapSearchActivity.this.adapter.setData(arrayList);
                                    MantoMapSearchActivity.this.adapter.setKeyword(trim);
                                    MantoMapSearchActivity.this.listView.setAdapter((ListAdapter) MantoMapSearchActivity.this.adapter);
                                }
                            }
                            MantoMapSearchActivity.this.isLoadingMore = false;
                            if (!z || MantoMapSearchActivity.this.adapter.getCount() % 20 != 0) {
                                MantoMapSearchActivity.this.loadMoreEnable = false;
                                if (MantoMapSearchActivity.this.listView.getFooterViewsCount() > 0) {
                                    MantoMapSearchActivity.this.listView.removeFooterView(MantoMapSearchActivity.this.footer);
                                }
                            } else if (MantoMapSearchActivity.this.listView.getFooterViewsCount() == 0) {
                                MantoMapSearchActivity.this.listView.addFooterView(MantoMapSearchActivity.this.footer);
                            }
                            if (MantoMapSearchActivity.this.loading.getVisibility() == 0) {
                                MantoMapSearchActivity.this.loading.setVisibility(8);
                            }
                        }
                    });
                    return;
                }
                MantoMapSearchActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.4.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MantoMapSearchActivity.this.isLoadingMore = false;
                        if (MantoMapSearchActivity.this.loading.getVisibility() == 0) {
                            MantoMapSearchActivity.this.loading.setVisibility(8);
                        }
                        Context applicationContext = MantoMapSearchActivity.this.getApplicationContext();
                        JSONObject jSONObject2 = jSONObject;
                        Toast.makeText(applicationContext, jSONObject2.optString("message", jSONObject2.toString()), 0).show();
                    }
                });
            }
        });
    }

    public int getLayoutId() {
        return R.layout.map_search_activity;
    }

    public final boolean hideKeyboard() {
        View currentFocus;
        IBinder windowToken;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager == null || (currentFocus = getCurrentFocus()) == null || (windowToken = currentFocus.getWindowToken()) == null) {
            return false;
        }
        try {
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (R.id.map_search_back == id) {
            setResult(0);
            finish();
        } else if (R.id.map_search_btn == id) {
            search();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        findViewById(R.id.map_search_back).setOnClickListener(this);
        findViewById(R.id.map_search_btn).setOnClickListener(this);
        this.loading = findViewById(R.id.map_search_loading_ll);
        this.editTextKeyword = (EditText) findViewById(R.id.map_search_et);
        ListView listView = (ListView) findViewById(R.id.map_search_listview);
        this.listView = listView;
        listView.setOnItemClickListener(this);
        this.listView.setOnTouchListener(this);
        this.footer = LayoutInflater.from(this).inflate(R.layout.map_poi_list_footer, (ViewGroup) null);
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                if (MantoMapSearchActivity.this.loadMoreEnable && !MantoMapSearchActivity.this.isLoadingMore && MantoMapSearchActivity.this.adapter != null && MantoMapSearchActivity.this.adapter.getCount() % 20 == 0 && i2 + i3 >= i4) {
                    MantoMapSearchActivity.this.isLoadingMore = true;
                    MantoMapSearchActivity.access$308(MantoMapSearchActivity.this);
                    MantoMapSearchActivity.this.search();
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
        this.editTextKeyword.setImeOptions(3);
        this.editTextKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                if (i2 == 3) {
                    MantoMapSearchActivity.this.hideKeyboard();
                    MantoMapSearchActivity.this.search();
                    return true;
                }
                return false;
            }
        });
        this.editTextKeyword.addTextChangedListener(new TextWatcher() { // from class: com.jd.manto.lbs.MantoMapSearchActivity.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MantoMapSearchActivity.this.loadMoreEnable = true;
                if (TextUtils.isEmpty(editable.toString())) {
                    return;
                }
                MantoMapSearchActivity.this.pageIndex = 1;
                MantoMapSearchActivity.this.search();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
        this.editTextKeyword.requestFocus();
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.editTextKeyword, 0);
        getWindow().setSoftInputMode(5);
        Intent intent = getIntent();
        if (intent != null) {
            this.lat = intent.getDoubleExtra("lat", Double.MIN_VALUE);
            this.lng = intent.getDoubleExtra(HybridSDK.LNG, Double.MIN_VALUE);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        hideKeyboard();
        super.onDestroy();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        Intent intent = new Intent();
        intent.putExtra("key_search_choosed_addr", (MapAddress) this.adapter.getItem(i2));
        setResult(-1, intent);
        finish();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.editTextKeyword.hasFocus()) {
            this.editTextKeyword.clearFocus();
            hideKeyboard();
            return false;
        }
        return false;
    }
}

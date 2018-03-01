package com.qiankun.qkun.login;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kun.qian.baselibrary.api.ApiService;
import com.kun.qian.baselibrary.base.BaseActivity;
import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.bean.HomeBean;
import com.kun.qian.baselibrary.bean.LoginBean;
import com.kun.qian.baselibrary.core.response.BaseResponse;
import com.kun.qian.baselibrary.core.retrofit.RetrofitHelper;
import com.kun.qian.baselibrary.core.rxjava.BaseObserver;
import com.kun.qian.baselibrary.core.rxjava.RxSchedulers;
import com.kun.qian.baselibrary.utils.ActivityUtils;
import com.kun.qian.baselibrary.utils.CacheUtils;
import com.kun.qian.baselibrary.utils.KeyboardUtils;
import com.kun.qian.baselibrary.utils.SPUtils;
import com.kun.qian.baselibrary.utils.ToastUtils;
import com.kun.qian.baselibrary.wiget.dialog.IProgressDialog;
import com.qiankun.qkun.R;
import com.qiankun.qkun.main.MainActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/2/24.
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.tvLoginTitle)
    TextView mTvLoginTitle;
    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.editUser)
    EditText mEditUser;
    @BindView(R.id.iv_account_delete)
    AppCompatImageView mIvAccountDelete;
    @BindView(R.id.editPass)
    EditText mEditPass;
    @BindView(R.id.iv_password_delete)
    AppCompatImageView mIvPasswordDelete;
    @BindView(R.id.cBoxPass)
    CheckBox mCBoxPass;
    @BindView(R.id.tvLogin)
    TextView mTvLogin;
    @BindView(R.id.imgMotion)
    AppCompatImageView mImgMotion;
    private String mUserName;
    private String mPassWord;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }



    @Override
    protected void init() {


        Spannable sp = new SpannableString(getString(R.string.loginTitel));
        sp.setSpan(new AbsoluteSizeSpan(20, true), 0, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSuperColor)),
                0, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sp.setSpan(new AbsoluteSizeSpan(14, true), 15, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                15, sp.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvLoginTitle.setText(sp);

        Spannable sp1 = new SpannableString(getString(R.string.loginTitel2));
        sp1.setSpan(new AbsoluteSizeSpan(20, true), 0, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSuperColor)),
                0, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        sp1.setSpan(new AbsoluteSizeSpan(14, true), 10, sp1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                10, sp1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mLoginTitle.setText(sp1);

        //设置缓存的登录 账号 密码
        mEditUser.setText(SPUtils.getInstance().getString("username"));
        if (!TextUtils.isEmpty(SPUtils.getInstance().getString("'password'"))) {
            mEditPass.setText(SPUtils.getInstance().getString("'password'"));
            mEditUser.setSelection(mEditUser.getText().length());
            mCBoxPass.setChecked(true);
        }

        mEditUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mIvAccountDelete.setVisibility(View.VISIBLE);
                } else {
                    mIvAccountDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEditPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mIvPasswordDelete.setVisibility(View.VISIBLE);
                } else {
                    mIvPasswordDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mEditUser.getText().toString().trim().length() > 0) {
            mIvAccountDelete.setVisibility(View.VISIBLE);
        } else {
            mIvAccountDelete.setVisibility(View.GONE);
        }
        if (mEditPass.getText().toString().trim().length() > 0) {
            mIvPasswordDelete.setVisibility(View.VISIBLE);
        } else {
            mIvPasswordDelete.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tvLogin, R.id.iv_account_delete, R.id.iv_password_delete})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvLogin:
                login();
                break;
            case R.id.iv_account_delete:
                mEditUser.setText("");
                KeyboardUtils.showSoftInput(LoginActivity.this);
                break;
            case R.id.iv_password_delete:
                mEditPass.setText("");
                KeyboardUtils.hideSoftInput(LoginActivity.this);
                break;
            default:
                break;
        }
    }


    private void login() {
        mUserName = mEditUser.getText().toString().trim();
        mPassWord = mEditPass.getText().toString().trim();

        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassWord)) {
            ToastUtils.showLong(R.string.numPass);
            return;
        }

        final Map<String, Object> param = new HashMap<>();
        param.put("username", mUserName);
        param.put("password", mPassWord);

        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.user_login);
        RetrofitHelper.createApi(ApiService.class).loginToApp(param)
                .compose(RxSchedulers.<BaseResponse<LoginBean>>compose())
                .doOnNext(new Consumer<BaseResponse<LoginBean>>() {
                    @Override
                    public void accept(BaseResponse<LoginBean> loginBeanBaseResponse) throws Exception {
                        //做一些缓存等处理
                        Constant.token = loginBeanBaseResponse.getRows().getToken();
                        LoginBean.StudentBean studentBean = loginBeanBaseResponse.getRows().getStudent();
                        CacheUtils.getInstance().put("studentBean", studentBean);
                        Constant.studentID = loginBeanBaseResponse.getRows().getStudent().getStudentid();
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse<LoginBean>, ObservableSource<BaseResponse<HomeBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<HomeBean>> apply(BaseResponse<LoginBean> loginBeanBaseResponse) throws Exception {
                        Map<String, Object> parm = new HashMap<>();
                        param.put("token", loginBeanBaseResponse.getRows().getToken());
                        param.put("studentid", loginBeanBaseResponse.getRows().getStudent().getStudentid());

                        return RetrofitHelper.createApi(ApiService.class).getHome(param);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new BaseObserver<HomeBean>(progressDialog) {
                    @Override
                    protected void onSuccess(HomeBean homeBean) {
                        //缓存 登录账号
                        SPUtils.getInstance().put("username", mUserName);
                        if (mCBoxPass.isChecked()) {
                            SPUtils.getInstance().put("password", mPassWord);
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        ActivityUtils.startActivity(intent);
                        ActivityUtils.finishActivity(LoginActivity.class);

                    }
                });
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


}

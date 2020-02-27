package com.android.core.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.library.dialog.LoadingDialogFragment;
import com.android.library.utils.LoadingUtil;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class SimpleBaseDialog extends DialogFragment {

    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = getLayoutResId();
        if (layoutResId <= 0) throw new UnsupportedOperationException("请指定fragment的布局文件!");
        View inflate = inflater.inflate(layoutResId, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    protected abstract int getLayoutResId();

    public void showLoading() {
        LoadingUtil.showLoading(getAppCompatActivity().getClass().getName(), getAppCompatActivity().getSupportFragmentManager());
    }

    /**
     * 可点击返回键取消disposable的任务
     */
    public void showLoading(Disposable disposable, LoadingDialogFragment.OnRequestCancelledListener listener) {
        LoadingUtil.showLoading(getAppCompatActivity().getClass().getName(), getAppCompatActivity().getSupportFragmentManager(), disposable, listener);
    }

    public void hideLoading() {
        LoadingUtil.hideLoading(getAppCompatActivity().getClass().getName());
    }

    protected abstract AppCompatActivity getAppCompatActivity();

    @Override
    public void onDestroyView() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable.clear();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }
}

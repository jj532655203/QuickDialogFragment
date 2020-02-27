package com.android.core.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * Jay
 * 全屏dialog
 */
public abstract class BaseFullScreenDialog extends SimpleBaseDialog {

    @Override
    public void onStart() {
        super.onStart();

        int windowWidth = ScreenUtils.getScreenWidth();
        int windowHeight = ScreenUtils.getScreenHeight();

        int height = Math.max(windowWidth, windowHeight);
        int width = Math.min(windowWidth, windowHeight);
        Dialog dialog = getDialog();
        if (dialog == null) return;

        //设置窗体尺寸
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable());
            window.setLayout(width, height);
        }

    }
}

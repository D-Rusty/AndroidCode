package com.project.onepice.basicproject.androidBasic.widget.spinner.custom.OverrideSpinner;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Created by onepice2015 on 2016/12/18.
 * 重写 Spinner
 * */
public class OverrideSpinner extends Spinner {

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    public Context getPopupContext() {
        return super.getPopupContext();
    }

    @Override
    public void setPopupBackgroundDrawable(Drawable background) {
        super.setPopupBackgroundDrawable(background);
    }

    @Override
    public void setPopupBackgroundResource(int resId) {
        super.setPopupBackgroundResource(resId);
    }

    @Override
    public Drawable getPopupBackground() {
        return super.getPopupBackground();
    }

    @Override
    public void setDropDownVerticalOffset(int pixels) {
        super.setDropDownVerticalOffset(pixels);
    }

    @Override
    public int getDropDownVerticalOffset() {
        return super.getDropDownVerticalOffset();
    }

    @Override
    public void setDropDownHorizontalOffset(int pixels) {
        super.setDropDownHorizontalOffset(pixels);
    }

    @Override
    public int getDropDownHorizontalOffset() {
        return super.getDropDownHorizontalOffset();
    }

    @Override
    public void setDropDownWidth(int pixels) {
        super.setDropDownWidth(pixels);
    }

    @Override
    public int getDropDownWidth() {
        return super.getDropDownWidth();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
    }

    @Override
    public int getGravity() {
        return super.getGravity();
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public int getBaseline() {
        return super.getBaseline();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener l) {
        super.setOnItemClickListener(l);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override
    public void setPrompt(CharSequence prompt) {
        super.setPrompt(prompt);
    }

    @Override
    public void setPromptId(int promptId) {
        super.setPromptId(promptId);
    }

    @Override
    public CharSequence getPrompt() {
        return super.getPrompt();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public OverrideSpinner(Context context) {
        super(context);
    }
}

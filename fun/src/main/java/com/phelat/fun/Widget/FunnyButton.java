package com.phelat.fun.Widget;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phelat.R;

public class FunnyButton extends CardView{

    private String
            mText = "",
            mFont = "",
            mColor = "#000000";

    private float
            mSize = 15;

    private TypedArray typedArray;

    private TextView textView;

    private StateListAnimator stateListAnimator;

    public FunnyButton(Context context) {
        super(context);
        setPreventCornerOverlap(false);
        setUseCompatPadding(true);
        setClickable(true);
        if (Build.VERSION.SDK_INT >= 21){
            stateListAnimator = AnimatorInflater
                    .loadStateListAnimator(context, R.anim.card_ripple_touch);
            setStateListAnimator(stateListAnimator);
        }
        setForeground(ContextCompat.getDrawable(context, R.drawable.card_ripple));
        textView = new TextView(context);
    }

    public FunnyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPreventCornerOverlap(false);
        setUseCompatPadding(true);
        setClickable(true);
        if (Build.VERSION.SDK_INT >= 21){
            stateListAnimator = AnimatorInflater
                    .loadStateListAnimator(context, R.anim.card_ripple_touch);
            setStateListAnimator(stateListAnimator);
        }
        setForeground(ContextCompat.getDrawable(context, R.drawable.card_ripple));
        textView = new TextView(context);
        initButton(context, attrs);
    }

    private void initButton(Context context, AttributeSet attributeSet){
        typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.FunnyButton);

        if (typedArray.getString(R.styleable.FunnyButton_text) != null){
            mText = typedArray.getString(R.styleable.FunnyButton_text);
        }
        if (typedArray.getString(R.styleable.FunnyButton_textColor) != null){
            mColor = typedArray.getString(R.styleable.FunnyButton_textColor);
        }
        if (typedArray.getString(R.styleable.FunnyButton_textSize) != null){
            mSize = typedArray.getDimension(R.styleable.FunnyButton_textSize, 15);
        }

        initTextView();

        typedArray.recycle();
    }

    private void initTextView() {

        textView.setText(mText);
        textView.setTextColor(Color.parseColor(mColor));
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mSize);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        addView(textView, 0);

    }

    public void hideTextView(){
        textView.setVisibility(GONE);
    }

    public void showTextView(){
        textView.setVisibility(VISIBLE);
    }

    public void setTypeface(Typeface typeface){
        textView.setTypeface(typeface);
    }

    public void setTypeface(Context context, String fontName){
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
    }

    public void setTextColor(int color){
        textView.setTextColor(color);
    }

    public void setTextColor(String color) {
        if (color.length() == 7 && color.charAt(0) == '#') {
            textView.setTextColor(Color.parseColor(color));
        }else{
            throw new IllegalArgumentException("Color code should start with '#' and have 6 characters(HEX)");
        }
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setTextSize(float textSize){
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

}

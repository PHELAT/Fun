package com.phelat.fun.Layouts;

import android.animation.LayoutTransition;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class Funny extends FrameLayout {

    public Funny(Context context) {
        super(context);
        setLayoutTransition(new LayoutTransition());
    }

    public Funny(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutTransition(new LayoutTransition());
    }

}

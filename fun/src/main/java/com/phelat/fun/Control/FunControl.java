package com.phelat.fun.Control;

import android.animation.LayoutTransition;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.phelat.fun.Layouts.Funny;
import com.phelat.fun.Widget.FunnyButton;

public class FunControl {

    private Funny funny;

    private FunnyButton funnyButton;

    private ViewGroup funnyContainer;

    private boolean isExpanded = false;

    private int
            width,
            gravity,
            gravityToExpand,
            widthToExpand;

    public FunControl(Builder builder){
        this.funny = builder.funny;
        this.funnyButton = builder.funnyButton;
        this.funnyContainer = builder.viewGroup;
        this.gravityToExpand = builder.gravityToExpand;
        this.widthToExpand = builder.width;

        LayoutTransition layoutTransition = funny.getLayoutTransition();
        layoutTransition.setDuration(builder.animationDuration);
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);
    }

    /**
     * Builder class for FunControl for setting attributes
     */
    public static class Builder {

        private Funny funny = null;

        private FunnyButton funnyButton = null;

        private ViewGroup viewGroup = null;

        private int gravityToExpand = Gravity.CENTER;

        private long animationDuration = 400;

        private int width = FrameLayout.LayoutParams.MATCH_PARENT;

        public Builder() {}

        /**
         * sets the FunnyLayout to run animations on it
         *
         * @param funnyLayout rootView of the layout
         * @return Builder
         */
        public Builder setFunnyLayout(Funny funnyLayout){
            this.funny = funnyLayout;
            return this;
        }

        /**
         * sets the FunnyButton for expanding and collapsing
         *
         * @param funnyButton the button you want to fun with
         * @return Builder
         */
        public Builder setFunnyButton(FunnyButton funnyButton){
            this.funnyButton = funnyButton;
            return this;
        }

        /**
         * sets the FunnyContainer to visible child's on expand and gone on collapse
         *
         * @param container parent of things inside button
         * @return Builder
         */
        public Builder setContainer(ViewGroup container){
            this.viewGroup = container;
            return this;
        }

        /**
         * sets the animationDuration so we know when to finish animation <br>
         * default : 400 ms
         *
         * @param animationDuration millis you want to fun
         * @return Builder
         */
        public Builder setAnimationDuration(long animationDuration){
            this.animationDuration = animationDuration;
            return this;
        }

        /**
         * sets the gravityToExpand so we know where to expand the FunnyButton <br>
         * default : CENTER
         *
         * @param gravityToExpand Gravity you want to expand FunnyButton
         * @return Builder
         */
        public Builder setGravityToExpand(int gravityToExpand){
            this.gravityToExpand = gravityToExpand;
            return this;
        }

        /**
         * sets the widthToExpand so we know how big we should expand the FunnyButton <br>
         * default : MATCH_PARENT
         *
         * @param width you want to have on expanding process
         * @return Builder
         */
        public Builder setWidthToExpand(int width){
            this.width = width;
            return this;
        }

        public FunControl build() {
            if (funny == null){
                throw new RuntimeException("set funny layout");
            }
            if (funnyButton == null){
                throw new RuntimeException("set funny button");
            }
            if (viewGroup == null){
                throw new RuntimeException("set fun container");
            }
            return new FunControl(this);
        }

    }

    public void expand(){

        if (funnyButton.getParent().getClass().getName().equals("com.phelat.fun.Layouts.Funny")) {

            if (!isExpanded) {
                funnyButton.hideTextView();

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) funnyButton.getLayoutParams();

                width = layoutParams.width;
                gravity = layoutParams.gravity;

                layoutParams.gravity = gravityToExpand;
                layoutParams.width = widthToExpand;
                funnyButton.setLayoutParams(layoutParams);

                for (int i = 0; i < funnyContainer.getChildCount(); i++) {
                    funnyContainer.getChildAt(i).setVisibility(View.VISIBLE);
                }

                funnyButton.setClickable(false);

                isExpanded = true;
            }

        }else{
            throw new RuntimeException("parent of FunnyButton should be Funny!");
        }

    }

    /**
     * collapse FunnyButton back to normal mode
     */
    public void collapse(){

        if (isExpanded) {
            funnyButton.showTextView();
            for (int i = 0; i < funnyContainer.getChildCount(); i++) {
                funnyContainer.getChildAt(i).setVisibility(View.GONE);
            }

            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) funnyButton.getLayoutParams();
            layoutParams.gravity = gravity;
            layoutParams.width = width;
            funnyButton.setLayoutParams(layoutParams);

            funnyButton.setClickable(true);

            isExpanded = false;
        }

    }

    /**
     * returns true if FunnyButton is expanded, false if not
     *
     * @return boolean
     */
    public boolean isExpanded(){return isExpanded;}

}

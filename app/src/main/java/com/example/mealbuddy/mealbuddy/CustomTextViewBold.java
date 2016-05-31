package com.example.mealbuddy.mealbuddy;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * For a custom font
 */
public class CustomTextViewBold extends TextView {

    public CustomTextViewBold(Context context, AttributeSet attributes) {
        super(context,attributes);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Ubuntu-B.ttf"));
    }

}
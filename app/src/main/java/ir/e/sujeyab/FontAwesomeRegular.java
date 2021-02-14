package ir.e.sujeyab;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class FontAwesomeRegular extends TextView {


    public FontAwesomeRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontAwesomeRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontAwesomeRegular(Context context) {
        super(context);
        init();
    }

    private void init() {

        //Font name should not contain "/".
        Typeface tf = ResourcesCompat.getFont(getContext(), R.font.fa_regular_400);
        setTypeface(tf);
    }

}
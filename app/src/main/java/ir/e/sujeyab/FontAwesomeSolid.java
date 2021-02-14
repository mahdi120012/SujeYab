package ir.e.sujeyab;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

public class FontAwesomeSolid extends TextView {


    public FontAwesomeSolid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public FontAwesomeSolid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontAwesomeSolid(Context context) {
        super(context);
        init();
    }

    private void init() {

        //Font name should not contain "/".
        Typeface tf = ResourcesCompat.getFont(getContext(), R.font.fa_solid_900);
        setTypeface(tf);
    }

}
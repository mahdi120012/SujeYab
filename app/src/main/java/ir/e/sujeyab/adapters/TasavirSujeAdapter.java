package ir.e.sujeyab.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.models.SliderModel;
import ir.e.sujeyab.models.TasavirSujeModel;

public class TasavirSujeAdapter extends PagerAdapter {

    private ArrayList<TasavirSujeModel> images;
    private LayoutInflater inflater;
    private Context context;
    String method;


    public TasavirSujeAdapter(Context context, ArrayList<TasavirSujeModel> images, String method){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
        this.method = method;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position){
        View myImageLayout = null;
        if (method.matches("slider")){
            myImageLayout  = inflater.inflate(R.layout.slider_ba_hashiye, view, false);
        }

        if (method.matches("slider")) {
            final ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);

            if (images.get(position).getP1().isEmpty()) {
                Picasso.get()
                        .load(R.drawable.adamak_icon)
                        .fit()
                        .error(R.drawable.adamak_icon)
                        .into(myImage);
            } else {

                Picasso.get()
                        .load(images.get(position).getP1())
                        .error(R.drawable.adamak_icon)
                        .into(myImage);
            }

            view.addView(myImageLayout, 0);
            return myImageLayout;
        }

        return null;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
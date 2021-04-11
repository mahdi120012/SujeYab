package ir.e.sujeyab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ir.e.sujeyab.models.SliderModel;

public class ViewPagerAdapterForSlider extends PagerAdapter {

    private ArrayList<SliderModel> images;
    private LayoutInflater inflater;
    private Context context;
    String method;
    ViewPager viewPager;

    public ViewPagerAdapterForSlider(Context context, ArrayList<SliderModel> images, String method, ViewPager viewPager){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
        this.method = method;
        this.viewPager = viewPager;
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
        }else if (method.matches("slider_suje_haye_vijeh")){
            myImageLayout  = inflater.inflate(R.layout.slider_suje_haye_vijeh, view, false);

        }else if (method.matches("slider_khadamat_vijeh")){
            myImageLayout  = inflater.inflate(R.layout.slider_khadamat_vijeh, view, false);

        }

        if (method.matches("slider")){
            final ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
            final ImageView imgLeft = (ImageView) myImageLayout.findViewById(R.id.imgLeft);
            final ImageView imgRight = (ImageView) myImageLayout.findViewById(R.id.imgRight);

            if(images.get(position).getPicture().isEmpty()){
                Picasso.get()
                        .load(R.drawable.adamak_icon)
                        .fit()
                        .error(R.drawable.adamak_icon)
                        .into(myImage);
            }else {

                Picasso.get()
                        .load(images.get(position).getPicture())
                        .error(R.drawable.adamak_icon)
                        .into(myImage);
            }


            myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(images.get(position).getLink().isEmpty()){

                    }else {
                        String data = images.get(position).getLink();
                        Intent defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER);
                        defaultBrowser.setData(Uri.parse(data));
                        context.startActivity(defaultBrowser);
                    }
                }
            });

            imgRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                }
            });

            imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            });


            view.addView(myImageLayout, 0);
            return myImageLayout;

        }else if (method.matches("slider_suje_haye_vijeh")){

            final TextView txOnvan = (TextView) myImageLayout.findViewById(R.id.txOnvan);
            final TextView txNameFerestande = (TextView) myImageLayout.findViewById(R.id.txFerestande);
            final TextView txSemat = (TextView) myImageLayout.findViewById(R.id.txSemat);
            final TextView txMatnKholase = (TextView) myImageLayout.findViewById(R.id.txMatnKholase);

            txOnvan.setText(images.get(position).getPicture());
            txNameFerestande.setText(images.get(position).getLink());
            txSemat.setText(images.get(position).getDescription());
            txMatnKholase.setText(images.get(position).getMatn_kholase());
            view.addView(myImageLayout, 0);
            return myImageLayout;

        }else if (method.matches("slider_khadamat_vijeh")){

            final TextView txOnvan = (TextView) myImageLayout.findViewById(R.id.txOnvan);
            final TextView txMozo = (TextView) myImageLayout.findViewById(R.id.txMozo);
            final TextView txMatnKholase = (TextView) myImageLayout.findViewById(R.id.txMatnKholase);

            txOnvan.setText(images.get(position).getPicture());
            txMozo.setText(images.get(position).getLink());
            txMatnKholase.setText(images.get(position).getDescription());

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
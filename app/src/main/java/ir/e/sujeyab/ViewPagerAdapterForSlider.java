package ir.e.sujeyab;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.SujeClick.MainActFarakhan;
import ir.e.sujeyab.SujeClick.MainActSuje;
import ir.e.sujeyab.models.FarakhanVijehModel;
import ir.e.sujeyab.models.SliderModel;

public class ViewPagerAdapterForSlider extends PagerAdapter {
    private ArrayList<FarakhanVijehModel> sujeModel;
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

    public ViewPagerAdapterForSlider(Context context, ArrayList<SliderModel> images, String method, ViewPager viewPager,
                                     ArrayList<FarakhanVijehModel> sujeModel){
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
        this.method = method;
        this.viewPager = viewPager;
        this.sujeModel = sujeModel;
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

        }else if (method.matches("slider_pishkhan")){
            myImageLayout  = inflater.inflate(R.layout.slider_ba_hashiye_pishkhan, view, false);
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

                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    }


                }
            });

            imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager.getCurrentItem() == 0 ){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);


                    }else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);


                    }

                   // viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            });


            view.addView(myImageLayout, 0);
            return myImageLayout;

        }else if (method.matches("slider_suje_haye_vijeh")){

            final TextView txOnvan = (TextView) myImageLayout.findViewById(R.id.txOnvan);
            final TextView txNameFerestande = (TextView) myImageLayout.findViewById(R.id.txFerestande);
            final TextView txSemat = (TextView) myImageLayout.findViewById(R.id.txSemat);
            final TextView txMatnKholase = (TextView) myImageLayout.findViewById(R.id.txMatnKholase);
            final TextView txRateAvg = (TextView) myImageLayout.findViewById(R.id.txRateAvg);

            txOnvan.setText(images.get(position).getPicture());
            txNameFerestande.setText(images.get(position).getLink());
            txSemat.setText(images.get(position).getDescription());
            txMatnKholase.setText(images.get(position).getMatn_kholase());
            txRateAvg.setText(new EnglishNumberToPersian().convert(sujeModel.get(position).getMiyangin_rate()));

            view.addView(myImageLayout, 0);
            myImageLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent =new Intent(context, MainActSuje.class);
                            intent.putExtra("id",sujeModel.get(position).getId());
                            intent.putExtra("onvan",sujeModel.get(position).getOnvan());
                            intent.putExtra("matn",sujeModel.get(position).getMatn_kholase());
                            intent.putExtra("picture",sujeModel.get(position).getPicture());
                            intent.putExtra("motavali",sujeModel.get(position).getMotavali());
                            intent.putExtra("modat_baghi_mande",sujeModel.get(position).getModat_baghimande());
                            intent.putExtra("date_create",sujeModel.get(position).getDate_create());
                            intent.putExtra("name",sujeModel.get(position).getName_family());
                            intent.putExtra("semat_shoghli",sujeModel.get(position).getSemat_shoghli());
                            intent.putExtra("tedad_like",sujeModel.get(position).getTedad_like());
                            intent.putExtra("tedad_comment",sujeModel.get(position).getTedad_comment());
                            intent.putExtra("vaziyat_like",sujeModel.get(position).getVaziyat_like());
                            context.startActivity(intent);

                    //Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                }
            });
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

        }else if (method.matches("slider_pishkhan")){
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

                    if (viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    }else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    }


                }
            });

            imgLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewPager.getCurrentItem() == 0 ){
                        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);


                    }else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem()-1);


                    }

                    // viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            });


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
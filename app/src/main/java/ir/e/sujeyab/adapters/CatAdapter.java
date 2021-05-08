package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.util.ErrorDialogManager;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.CustomClasses.Recyclerview;
import ir.e.sujeyab.LoadData;
import ir.e.sujeyab.R;
import ir.e.sujeyab.bank_suje.Main_Cat_1;
import ir.e.sujeyab.login.TakmilEtelaat;
import ir.e.sujeyab.models.CatModel;
import ir.e.sujeyab.models.CitysModel;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.MyViewHolder> {

    private ArrayList<CatModel> rModels;
    private ArrayList<CatModel> rModels1;
    Context c;
    String rowLayoutType;
    CatAdapter rAdapter;
    CatAdapter rAdapter1;
    ConstraintLayout clWifiState;
    RecyclerView rv1;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    public CatAdapter(String rowLayoutType, Context c, ArrayList<CatModel> rModels,
                      CatAdapter rAdapter) {
        this.rModels = rModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = rAdapter;

    }

    public CatAdapter(String rowLayoutType, Context c, ArrayList<CatModel> rModels,
                      CatAdapter rAdapter, RecyclerView rv1, ConstraintLayout clWifiState, ProgressBar progressBar,
                      NestedScrollView nestedScrollView) {
        this.rModels = rModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = rAdapter;
        this.rv1 = rv1;
        this.clWifiState = clWifiState;
        this.progressBar = progressBar;
        this.nestedScrollView = nestedScrollView;
    }


    @Override
    public CatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("cat_0")) {
             return new CatAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat, parent, false));

         }else if(rowLayoutType.matches("cat_1")) {
             return new CatAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cat, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final CatAdapter.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("cat_0")){


            holder.txName.setText(rModels.get(position).getCat());
            holder.txCount.setText(new EnglishNumberToPersian().convert(rModels.get(position).getTedad_mataleb()));
            String picture = rModels.get(position).getPicture();

            //Toast.makeText(c, picture, Toast.LENGTH_SHORT).show();
            if (picture.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);

            }else {
                Picasso.get()
                        .load(picture)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);
            }


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("cat",rModels.get(position).getCat());
                    AppCompatActivity activity = (AppCompatActivity) c;
                    Fragment myFragment = new Main_Cat_1();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();



                }
            });

        }else if (rowLayoutType.matches("cat_1")){



            holder.txName.setText(rModels.get(position).getCat());
            holder.txCount.setText(new EnglishNumberToPersian().convert(rModels.get(position).getTedad_mataleb()));
            holder.imgPicture.setVisibility(View.GONE);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putString("cat",rModels.get(position).getCat());
                    AppCompatActivity activity = (AppCompatActivity) c;
                    Fragment myFragment = new Main_Cat_1();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();



                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }


    public void onItemRemoved(ArrayList<CatModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txName,txCount;
        ImageView imgPicture;
        MyViewHolder(View view) {
            super(view);
            txName = itemView.findViewById(R.id.txName);
            txCount = itemView.findViewById(R.id.txCount);
            imgPicture = itemView.findViewById(R.id.imgPicture);
        }
    }

}
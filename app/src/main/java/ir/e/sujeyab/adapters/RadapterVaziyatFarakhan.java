package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.CustomClasses.Recyclerview;
import ir.e.sujeyab.LoadData;
import ir.e.sujeyab.R;
import ir.e.sujeyab.RecyclerAdapter;
import ir.e.sujeyab.SujeClick.MainActFarakhan;
import ir.e.sujeyab.SujeClick.MainActSuje;
import ir.e.sujeyab.models.FarakhanVijehModel;
import ir.e.sujeyab.models.RecyclerModel;

public class RadapterVaziyatFarakhan extends RecyclerView.Adapter<RadapterVaziyatFarakhan.MyViewHolder> {
    private static int lastClickedPosition = -1;
    private ArrayList<RecyclerModel> rModels;
    private ArrayList<RecyclerModel> rModels2;
    RecyclerAdapter rAdapter;


    Context c;
    String rowLayoutType;
    RadapterVaziyatFarakhan rAdapterV;
    RecyclerView rv2;
    ConstraintLayout clwifi;
    public RadapterVaziyatFarakhan(String rowLayoutType,Context c, ArrayList<RecyclerModel> rModels,
                                   RadapterVaziyatFarakhan rAdapterV,
                                   ArrayList<RecyclerModel> rModels2,RecyclerAdapter rAdapter,RecyclerView rv2,ConstraintLayout clwifi) {
        this.c = c;
        this.rowLayoutType = rowLayoutType;
        this.rModels = rModels;
        this.rAdapterV = rAdapterV;
        this.rModels2 = rModels2;
        this.rAdapter = rAdapter;
        this.rModels2 = rModels2;
        this.rv2 = rv2;
        this.clwifi = clwifi;
    }

    @Override
    public RadapterVaziyatFarakhan.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate your custom row layout here
        if(rowLayoutType.matches("vaziyat_farakhan")){
            return new RadapterVaziyatFarakhan.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vaziyat_farakhan, parent, false));
        }
        return new RadapterVaziyatFarakhan.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_vijeh, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RadapterVaziyatFarakhan.MyViewHolder holder, final int position) {


        if (rowLayoutType.matches("vaziyat_farakhan")){

            holder.txOnvan.setText(rModels.get(position).getPicture());

            holder.clbg.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.bg_orange_ba_hashiye) );
            holder.txOnvan.setTypeface(ResourcesCompat.getFont(c, R.font.iran_sans));


            holder.txOnvan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(c, rModels.get(position).getPicture(), Toast.LENGTH_SHORT).show();

                    holder.txOnvan.setTypeface(ResourcesCompat.getFont(c, R.font.iran_sans_bold));

                    holder.clbg.setBackgroundDrawable(ContextCompat.getDrawable(c, R.drawable.bg_orange_ba_hashiye_selected) );
                    if (lastClickedPosition != -1)
                        notifyItemChanged(lastClickedPosition);
                    lastClickedPosition = position;


                    rModels2 = new ArrayList();
                    rAdapter = new RecyclerAdapter(
                            "farakhan_ha",
                            c,
                            rModels2,
                            rAdapter);
                    Recyclerview.defineRecyclerViewVertical(c, rv2, rAdapter, rModels2);
                    LoadData.loadFarakhanHaBarAsasVaziyatBaVolley(c, clwifi, rModels2, rAdapter, rModels.get(position).getPicture());


/*                    rModels = new ArrayList();
                    recyclerAdapterFarakhanHa = new RadapterVaziyatFarakhan(
                            "farakhan_ha",
                            c,
                            rModels,
                            recyclerAdapterFarakhanHa,rModels,recyclerAdapterFarakhanHa, rv1,clwifi,"",""
        );
                    //Recyclerview.defineRecyclerViewVertical(c, rv2, rAdapter, recyclerModelsFarakhanHa);
                    LoadData.loadFarakhanHaBarAsasVaziyat(
                            c,
                            clwifi,
                            rModels,
                            recyclerAdapterFarakhanHa,rModels.get(position).getPicture());*/
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }



    public void onItemRemoved(ArrayList<RecyclerModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txOnvan;
        ConstraintLayout clbg;
        MyViewHolder(View view) {
            super(view);
            clbg = itemView.findViewById(R.id.clbg);
            txOnvan = itemView.findViewById(R.id.txOnvan);
        }
    }

}
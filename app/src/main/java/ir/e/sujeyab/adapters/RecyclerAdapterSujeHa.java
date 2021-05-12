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

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.R;
import ir.e.sujeyab.SujeClick.MainActFarakhan;
import ir.e.sujeyab.SujeClick.MainActSuje;
import ir.e.sujeyab.models.FarakhanVijehModel;

public class RecyclerAdapterSujeHa extends RecyclerView.Adapter<RecyclerAdapterSujeHa.MyViewHolder> {

    private ArrayList<FarakhanVijehModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapterSujeHa rAdapter;

    public RecyclerAdapterSujeHa(String rowLayoutType, Context c, ArrayList<FarakhanVijehModel> recyclerModels,
                                 RecyclerAdapterSujeHa recyclerAdapterSujeHa) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterSujeHa;
    }


    @Override
    public RecyclerAdapterSujeHa.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("suje_ha")) {
             return new RecyclerAdapterSujeHa.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_suje_ha, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapterSujeHa.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("suje_ha")){
            holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kholase());
            holder.txVaziyat.setText(rModels.get(position).getMotavali());
            holder.txTedadLike.setText(new EnglishNumberToPersian().convert(rModels.get(position).getTedad_like()));
            holder.txTedadComment.setText(new EnglishNumberToPersian().convert("0"));
            holder.txFerestande.setText(rModels.get(position).getName_family());
            holder.txSematShoghli.setText("(" + rModels.get(position).getSemat_shoghli() +")" );
            holder.txOnvanFarakhan.setText(rModels.get(position).getOnvan_farakhan());

            if (rModels.get(position).getVaziyat_like().matches("0")){
                holder.imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like));
            }else {
                holder.imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like_red));
            }


            String ax = rModels.get(position).getPicture();
/*            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);
            }*/
            Glide.with(c).load("empty")
                    .thumbnail(Glide.with(c).load(rModels.get(position).getPicture()))
                    .into(holder.imgPicture);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(c, MainActSuje.class);
                    intent.putExtra("id",rModels.get(position).getId());
                    intent.putExtra("onvan",rModels.get(position).getOnvan());
                    intent.putExtra("matn",rModels.get(position).getMatn_kholase());
                    intent.putExtra("picture",rModels.get(position).getPicture());
                    intent.putExtra("motavali",rModels.get(position).getMotavali());
                    intent.putExtra("modat_baghi_mande",rModels.get(position).getModat_baghimande());
                    intent.putExtra("date_create",rModels.get(position).getDate_create());
                    intent.putExtra("name",rModels.get(position).getName_family());
                    intent.putExtra("semat_shoghli",rModels.get(position).getSemat_shoghli());
                    intent.putExtra("tedad_like",rModels.get(position).getTedad_like());
                    intent.putExtra("vaziyat_like",rModels.get(position).getVaziyat_like());
                    c.startActivity(intent);

                    //Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }



    public void onItemRemoved(ArrayList<FarakhanVijehModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txOnvan,txMatnKholase,txTedadComment,txTedadLike,txFerestande,txSematShoghli,txVaziyat,txOnvanFarakhan;
        ImageView imgPicture,imgLike;

        MyViewHolder(View view) {
            super(view);
            imgLike = itemView.findViewById(R.id.imgLike);

            txOnvan = itemView.findViewById(R.id.txOnvan);

            txFerestande = itemView.findViewById(R.id.txFerestande);
            txSematShoghli = itemView.findViewById(R.id.txSemat2);


            txVaziyat = itemView.findViewById(R.id.txVaziyat);

            txTedadLike = itemView.findViewById(R.id.txTedadLike);
            txTedadComment = itemView.findViewById(R.id.txTedadComment);

            txMatnKholase = itemView.findViewById(R.id.txMatnKholase);
            txOnvanFarakhan = itemView.findViewById(R.id.txOnvanFarakhan);
            imgPicture = itemView.findViewById(R.id.imgPicture);



        }
    }

}
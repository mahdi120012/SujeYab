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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.CustomClasses.TimeKononi;
import ir.e.sujeyab.R;
import ir.e.sujeyab.SujeClick.SujeClick2;
import ir.e.sujeyab.models.CommentsModel;
import ir.e.sujeyab.models.FarakhanVijehModel;

public class RecyclerAdapterComments extends RecyclerView.Adapter<RecyclerAdapterComments.MyViewHolder> {

    private ArrayList<CommentsModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapterComments rAdapter;

    public RecyclerAdapterComments(String rowLayoutType, Context c, ArrayList<CommentsModel> recyclerModels,
                                   RecyclerAdapterComments recyclerAdapterSujeHa) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterSujeHa;
    }


    @Override
    public RecyclerAdapterComments.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("comments")) {
             return new RecyclerAdapterComments.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapterComments.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("comments")){
            holder.txName.setText(rModels.get(position).getName());
            holder.txMatn.setText(rModels.get(position).getComment());
            holder.txDate.setText(new TimeKononi().changeGregorianToPersian(rModels.get(position).getDate_create()));
            //holder.txRateCount.setText(new EnglishNumberToPersian().convert(rModels.get(position).get()));

           String picture = rModels.get(position).getProfile_picture();
           if (picture.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);

            }else{
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

                /*    Intent intent =new Intent(c, SujeClick2.class);
                    intent.putExtra("id",rModels.get(position).getId());
                    intent.putExtra("onvan",rModels.get(position).getOnvan());
                    intent.putExtra("matn",rModels.get(position).getMatn_kholase());
                    intent.putExtra("picture",rModels.get(position).getPicture());
                    intent.putExtra("motavali",rModels.get(position).getMotavali());
                    intent.putExtra("modat_baghi_mande",rModels.get(position).getModat_baghimande());
                    c.startActivity(intent);*/

                    //Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }



    public void onItemRemoved(ArrayList<CommentsModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txName,txMatn,txDate,txRateCount;
        ImageView imgPicture;

        MyViewHolder(View view) {
            super(view);

            txName = itemView.findViewById(R.id.txName);
            txMatn = itemView.findViewById(R.id.txMatn);
            txDate = itemView.findViewById(R.id.txDate);
            txRateCount = itemView.findViewById(R.id.txRateCount);
            imgPicture = itemView.findViewById(R.id.imgPicture);

        }
    }

}
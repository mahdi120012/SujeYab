package ir.e.sujeyab;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.models.RecyclerModel;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<RecyclerModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapter rAdapter;

    public RecyclerAdapter( String rowLayoutType, Context c, ArrayList<RecyclerModel> recyclerModels,
                           RecyclerAdapter recyclerAdapterYouHaveKnow) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterYouHaveKnow;
    }


    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate your custom row layout here
        if (rowLayoutType.matches("farakhan_vijeh")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_vijeh, parent, false));
        }else if(rowLayoutType.matches("vaziyat_farakhan")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vaziyat_farakhan, parent, false));
        }else if(rowLayoutType.matches("farakhan_ha")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_ha, parent, false));

        }
        return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_vijeh, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {



        if (rowLayoutType.matches("farakhan_vije")){

            holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txModatBaghiMande.setText(rModels.get(position).getModat_baghimande());
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            //holder.txTedadComment.setText(rModels.get(position).getMatn_kolase());
            //holder.txTedadLike.setText(rModels.get(position).getMatn_kolase());
            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.alaamt_soal_icon)
                        .centerInside()
                        .fit()
                        .error(R.drawable.alaamt_soal_icon)
                        .placeholder(R.drawable.alaamt_soal_icon)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.alaamt_soal_icon)
                        .placeholder(R.drawable.alaamt_soal_icon)
                        .into(holder.imgAxFarakhan);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
                }
            });

        }else if (rowLayoutType.matches("vaziyat_farakhan")){
            holder.txOnvan2.setText(rModels.get(position).getPicture());
            holder.txOnvan2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, position, Toast.LENGTH_SHORT).show();
                }
            });

        }else if (rowLayoutType.matches("farakhan_ha")){
            holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txModatBaghiMande.setText(rModels.get(position).getModat_baghimande());
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            holder.txMotavali.setText(rModels.get(position).getMotavali());

            holder.txTedadSuje.setText( position+1 + " سوژه" );


            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.alaamt_soal_icon)
                        .centerInside()
                        .fit()
                        .error(R.drawable.alaamt_soal_icon)
                        .placeholder(R.drawable.alaamt_soal_icon)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.alaamt_soal_icon)
                        .placeholder(R.drawable.alaamt_soal_icon)
                        .into(holder.imgAxFarakhan);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
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
        TextView txOnvan,txModatBaghiMande,txMatnKholase,txTedadComment,txTedadLike,txOnvan2,txMotavali,txTedadSuje;
        ImageView imgAxFarakhan;

        MyViewHolder(View view) {
            super(view);

            txTedadSuje = itemView.findViewById(R.id.txTedadSuje);
            txMotavali = itemView.findViewById(R.id.txMotavali);

            txOnvan2 = itemView.findViewById(R.id.txOnvan2);

            txOnvan = itemView.findViewById(R.id.txOnvan);
            txModatBaghiMande = itemView.findViewById(R.id.txTimeRemain);
            txMatnKholase = itemView.findViewById(R.id.txMatnKholase);
            txTedadComment = itemView.findViewById(R.id.txTedadComment);
            txTedadLike = itemView.findViewById(R.id.txTedadLike);

            imgAxFarakhan = itemView.findViewById(R.id.imgAxFarakhan);



        }
    }

}
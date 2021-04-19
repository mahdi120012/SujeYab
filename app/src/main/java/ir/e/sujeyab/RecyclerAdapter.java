package ir.e.sujeyab;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.SujeClick.SujeClick2;
import ir.e.sujeyab.models.RecyclerModel;
import ir.e.sujeyab.SujeClick.SujeClick;

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
        }else if(rowLayoutType.matches("farakhan_ha")) {
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_ha, parent, false));

        }else if(rowLayoutType.matches("entekhab_farakhan_suje")){
                return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_ha, parent, false));

        }else if(rowLayoutType.matches("suje_ha")){
            return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_suje_ha, parent, false));

        }
        return new RecyclerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_farakhan_vijeh, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, final int position) {



        if (rowLayoutType.matches("farakhan_vije")){

            holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txModatBaghiMande.setText(new EnglishNumberToPersian().convert(String.valueOf(rModels.get(position).getModat_baghimande())));
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                holder.txMatnKholase.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            }*/
            holder.txTedadComment.setText(new EnglishNumberToPersian().convert(String.valueOf(position+1)));
            holder.txTedadLike.setText(new EnglishNumberToPersian().convert(String.valueOf(position+1)));
            if (Integer.parseInt((String) holder.txTedadLike.getText()) > 0){

            }else {
                holder.imgLike.setBackgroundResource(R.drawable.like_red);
            }
            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
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
            holder.txModatBaghiMande.setText(new EnglishNumberToPersian().convert(String.valueOf(rModels.get(position).getModat_baghimande())));
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            holder.txMotavali.setText(rModels.get(position).getMotavali());

            holder.txTedadSuje.setText(new EnglishNumberToPersian().convert(String.valueOf(position+1)) + " سوژه" );


            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
                }
            });

        }else if (rowLayoutType.matches("entekhab_farakhan_suje")){

            holder.txMoshahede.setText("انتخاب");
            holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txModatBaghiMande.setText(new EnglishNumberToPersian().convert(String.valueOf(rModels.get(position).getModat_baghimande())));
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            holder.txMotavali.setText(rModels.get(position).getMotavali());

            holder.txTedadSuje.setText(new EnglishNumberToPersian().convert(String.valueOf(position+1)) + " سوژه" );


            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, "انتخاب شد", Toast.LENGTH_SHORT).show();
                }
            });

        }else if (rowLayoutType.matches("suje_ha")){
            holder.txOnvan.setText(rModels.get(position).getOnvan());
            //holder.txModatBaghiMande.setText(new EnglishNumberToPersian().convert(String.valueOf(rModels.get(position).getModat_baghimande())));
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kolase());
            holder.txVaziyat.setText(rModels.get(position).getMotavali());
            holder.txTedadLike.setText(new EnglishNumberToPersian().convert("0"));
            holder.txTedadComment.setText(new EnglishNumberToPersian().convert("0"));

            holder.txFerestande.setText(rModels.get(position).getName_family());
            holder.txSematShoghli.setText(rModels.get(position).getSemat_shoghli());

            //holder.txTedadSuje.setText(new EnglishNumberToPersian().convert(String.valueOf(position+1)) + " سوژه" );


            String ax = rModels.get(position).getPicture();
            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.logo)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgAxFarakhan);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(c, SujeClick2.class);
                    intent.putExtra("onvan",rModels.get(position).getOnvan());
                    intent.putExtra("matn",rModels.get(position).getMatn_kolase());
                    intent.putExtra("picture",rModels.get(position).getPicture());
                    intent.putExtra("motavali",rModels.get(position).getMotavali());
                    intent.putExtra("modat_baghi_mande",rModels.get(position).getModat_baghimande());
                    intent.putExtra("date_create",rModels.get(position).getDate_create());
                    intent.putExtra("name",rModels.get(position).getName_family());
                    intent.putExtra("semat_shoghli",rModels.get(position).getSemat_shoghli());
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



    public void onItemRemoved(ArrayList<RecyclerModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txOnvan,txModatBaghiMande,txMatnKholase,txTedadComment,txTedadLike,txOnvan2,txMotavali,txTedadSuje,txFerestande,txSematShoghli
                ,txVaziyat,txMoshahede;
        ImageView imgAxFarakhan,imgLike;

        MyViewHolder(View view) {
            super(view);
            txMoshahede = itemView.findViewById(R.id.txMoshahede);

            txFerestande = itemView.findViewById(R.id.txFerestande);
            txVaziyat = itemView.findViewById(R.id.txVaziyat);
            txSematShoghli = itemView.findViewById(R.id.txSemat2);

            imgLike = itemView.findViewById(R.id.imgLike);
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
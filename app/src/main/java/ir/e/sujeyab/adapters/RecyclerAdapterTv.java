package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.SujeClick.MainActFarakhan;
import ir.e.sujeyab.SujeClick.MainActSuje;
import ir.e.sujeyab.models.FarakhanVijehModel;

public class RecyclerAdapterTv extends RecyclerView.Adapter<RecyclerAdapterTv.MyViewHolder> {

    private ArrayList<FarakhanVijehModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapterTv rAdapter;

    public RecyclerAdapterTv(String rowLayoutType, Context c, ArrayList<FarakhanVijehModel> recyclerModels,
                             RecyclerAdapterTv recyclerAdapterSujeHa) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterSujeHa;
    }


    @Override
    public RecyclerAdapterTv.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("tv")) {
             return new RecyclerAdapterTv.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tv, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapterTv.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("tv")){
            /*holder.txOnvan.setText(rModels.get(position).getOnvan());
            holder.txMatnKholase.setText(rModels.get(position).getMatn_kholase());
            holder.txVaziyat.setText(rModels.get(position).getMotavali());
            holder.txTedadLike.setText(new EnglishNumberToPersian().convert("0"));
            holder.txTedadComment.setText(new EnglishNumberToPersian().convert("0"));
            holder.txFerestande.setText(rModels.get(position).getName_family());
            holder.txSematShoghli.setText("( " + rModels.get(position).getSemat_shoghli() + " )" );*/


            String ax = rModels.get(position).getPicture();
            Glide.with(c).load("empty")
                    .thumbnail(Glide.with(c).load(rModels.get(position).getPicture()))
                    .into(holder.imgPicture);


/*            if (ax.isEmpty()) {

                Picasso.get()
                        .load(R.drawable.bg_tv_item)
                        .centerInside()
                        .fit()
                        .error(R.drawable.bg_tv_item)
                        .placeholder(R.drawable.bg_tv_item)
                        .into(holder.imgPicture);

            }else{
                Picasso.get()
                        .load(ax)
                        .centerInside()
                        .fit()
                        .error(R.drawable.bg_tv_item)
                        .placeholder(R.drawable.bg_tv_item)
                        .into(holder.imgPicture);
            }*/

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
                    intent.putExtra("vaziyat_like",rModels.get(position).getVaziyat_like());
                    intent.putExtra("tedad_like",rModels.get(position).getTedad_like());
                    intent.putExtra("tedad_comment",rModels.get(position).getTedad_comment());
                    intent.putExtra("username_ferestande",rModels.get(position).getUsername_ferestande());
                    intent.putExtra("vaziyat_suje",rModels.get(position).getVaziyat_suje());
                    intent.putExtra("profile_picture_ferestande",rModels.get(position).getProfile_picture_ferestande());



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
        //TextView txOnvan,txMatnKholase,txTedadComment,txTedadLike,txFerestande,txSematShoghli,txVaziyat;
        ImageView imgPicture;

        MyViewHolder(View view) {
            super(view);
            /*txOnvan = itemView.findViewById(R.id.txOnvan);

            txFerestande = itemView.findViewById(R.id.txFerestande);
            txSematShoghli = itemView.findViewById(R.id.txSemat2);


            txVaziyat = itemView.findViewById(R.id.txVaziyat);

            txTedadLike = itemView.findViewById(R.id.txTedadLike);
            txTedadComment = itemView.findViewById(R.id.txTedadComment);

            txMatnKholase = itemView.findViewById(R.id.txMatnKholase);
*/
            imgPicture = itemView.findViewById(R.id.imgPicture);



        }
    }

}
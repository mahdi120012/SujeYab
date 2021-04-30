package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.SujeClick.SujeClick2;
import ir.e.sujeyab.models.VaziyatSujehaModel;

public class RecyclerAdapterVaziyatSujeha extends RecyclerView.Adapter<RecyclerAdapterVaziyatSujeha.MyViewHolder> {

    private ArrayList<VaziyatSujehaModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapterVaziyatSujeha rAdapter;

    public RecyclerAdapterVaziyatSujeha(String rowLayoutType, Context c, ArrayList<VaziyatSujehaModel> recyclerModels,
                                        RecyclerAdapterVaziyatSujeha recyclerAdapterVaziyatSujeha) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterVaziyatSujeha;
    }


    @Override
    public RecyclerAdapterVaziyatSujeha.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(rowLayoutType.matches("vaziyat_suje_ha")) {
            return new RecyclerAdapterVaziyatSujeha.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vaziyat_farakhan, parent, false));
        }
        return new RecyclerAdapterVaziyatSujeha.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vaziyat_farakhan, parent, false));

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    public void onBindViewHolder(final RecyclerAdapterVaziyatSujeha.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("vaziyat_suje_ha")){

            holder.txOnvan.setText(rModels.get(position).getName_vaziyat_suje_ha());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(c, SujeClick2.class);
                    intent.putExtra("id",rModels.get(position).getId());
                    intent.putExtra("onvan",rModels.get(position).getName_vaziyat_suje_ha());

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }



    public void onItemRemoved(ArrayList<VaziyatSujehaModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txOnvan;

        MyViewHolder(View view) {
            super(view);
            txOnvan = itemView.findViewById(R.id.txOnvan);
        }
    }

}
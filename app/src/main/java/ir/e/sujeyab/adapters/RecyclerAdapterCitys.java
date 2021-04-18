package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.SujeClick.SujeClick2;
import ir.e.sujeyab.models.CitysModel;
import ir.e.sujeyab.models.FarakhanVijehModel;

public class RecyclerAdapterCitys extends RecyclerView.Adapter<RecyclerAdapterCitys.MyViewHolder> {

    private ArrayList<CitysModel> rModels;
    Context c;
    String rowLayoutType;
    RecyclerAdapterCitys rAdapter;
    EditText etSelect,etOstan,etShahrestan,etShar,etRosta;
    Dialog dialog;
    public RecyclerAdapterCitys(String rowLayoutType, Context c, ArrayList<CitysModel> recyclerModels,
                                RecyclerAdapterCitys recyclerAdapterSujeHa, EditText etSelect, Dialog dialog,EditText etOstan,EditText etShahrestan,EditText etShar,EditText etRosta) {
        this.rModels = recyclerModels;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = recyclerAdapterSujeHa;
        this.etSelect = etSelect;
        this.dialog = dialog;
        this.etOstan = etOstan;
        this.etShahrestan = etShahrestan;
        this.etShar = etShar;
        this.etRosta = etRosta;

    }


    @Override
    public RecyclerAdapterCitys.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("citys")) {
             return new RecyclerAdapterCitys.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_citys, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final RecyclerAdapterCitys.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("citys")){


            holder.txName.setText(rModels.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etSelect.getId() == R.id.etOstan){
                        etShahrestan.setText("");
                        etShahrestan.setHint("");

                        etShar.setText("");
                        etShar.setHint("");

                        etRosta.setText("");
                        etRosta.setHint("");
                    }else if (etSelect.getId() == R.id.etShahrestan){
                        etShar.setText("");
                        etShar.setHint("");

                        etRosta.setText("");
                        etRosta.setHint("");
                    }else if (etSelect.getId() == R.id.etShahr){
                        etRosta.setText("");
                        etRosta.setHint("");
                    }else if (etSelect.getId() == R.id.etRosta){

                    }


                    etSelect.setHint(rModels.get(position).getId());
                    etSelect.setText(rModels.get(position).getName());
                    //Toast.makeText(c, rModels.get(position).getName(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    //Toast.makeText(c, rModels.get(position).getOnvan(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return rModels.size();
    }


    public void onItemRemoved(ArrayList<CitysModel> arrObjects){
        rModels = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txName;

        MyViewHolder(View view) {
            super(view);
            txName = itemView.findViewById(R.id.txName);

        }
    }

}
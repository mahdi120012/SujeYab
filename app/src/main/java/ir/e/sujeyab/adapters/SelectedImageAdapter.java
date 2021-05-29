package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;

public class SelectedImageAdapter extends RecyclerView.Adapter<SelectedImageAdapter.MyViewHolder> {
    private ArrayList<Uri> arrayList;
    private ArrayList<Uri> arrayListTozihat;
    Context c;
    String rowLayoutType;
    SelectedImageAdapter rAdapter;

    public SelectedImageAdapter(String rowLayoutType, Context c, ArrayList<Uri> arrayList,ArrayList<Uri> arrayListTozihat,
                                SelectedImageAdapter selectedImageAdapter) {
        this.arrayList = arrayList;
        this.arrayListTozihat = arrayListTozihat;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = selectedImageAdapter;
    }


    @Override
    public SelectedImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("selected_image")) {
             return new SelectedImageAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_selected_image, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final SelectedImageAdapter.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("selected_image")){
            //holder.txOnvan.setText(rModels.get(position).getOnvan());

//            if (arrayList.get(position).isEmpty()) {
//
//                Picasso.get()
//                        .load(R.drawable.logo)
//                        .centerInside()
//                        .fit()
//                        .error(R.drawable.logo)
//                        .placeholder(R.drawable.logo)
//                        .into(holder.imgPicture);
//
//            }else{

            if (arrayList.get(position).toString().contains("jpg") || arrayList.get(position).toString().contains("png")) {
                Picasso.get()
                        .load(arrayList.get(position))
                        .centerCrop()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);
            }else {
                Glide.with(c).load("empty")
                        .thumbnail(Glide.with(c).load(arrayList.get(position)))
                        .into(holder.imgPicture);
            }

            holder.txRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();
                }
            });

            holder.txAfzodanTozih.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayListTozihat.add(Uri.parse(holder.etTozih.getText().toString()));
                }
            });




        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public void onItemRemoved(ArrayList<Uri> arrObjects){
        arrayList = arrObjects;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txRemove,txAfzodanTozih;
        ImageView imgPicture;
        EditText etTozih;
        MyViewHolder(View view) {
            super(view);

            imgPicture = itemView.findViewById(R.id.imgPicture);
            txRemove = itemView.findViewById(R.id.txRemove);
            txAfzodanTozih = itemView.findViewById(R.id.txAfzodanTozih);
            etTozih = itemView.findViewById(R.id.etTozih);
        }
    }

}
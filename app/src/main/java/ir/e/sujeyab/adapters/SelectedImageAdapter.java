package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
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

public class SelectedImageAdapter extends RecyclerView.Adapter<SelectedImageAdapter.MyViewHolder> {
    private ArrayList<Uri> arrayList;
    Context c;
    String rowLayoutType;
    SelectedImageAdapter rAdapter;

    public SelectedImageAdapter(String rowLayoutType, Context c, ArrayList<Uri> arrayList,
                                SelectedImageAdapter selectedImageAdapter) {
        this.arrayList = arrayList;
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
        //TextView txOnvan;
        ImageView imgPicture;

        MyViewHolder(View view) {
            super(view);

            imgPicture = itemView.findViewById(R.id.imgPicture);



        }
    }

}
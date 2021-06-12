package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.models.RecyclerModel;

public class SelectedImageAdapter extends RecyclerView.Adapter<SelectedImageAdapter.MyViewHolder> {
    private ArrayList<Uri> arrayList;
    private ArrayList<Uri> arrayListTozihat;
    Context c;
    String rowLayoutType;
    SelectedImageAdapter rAdapter;
    ConstraintLayout clTasvirSuje;

    public SelectedImageAdapter(String rowLayoutType, Context c, ArrayList<Uri> arrayList,ArrayList<Uri> arrayListTozihat,
                                SelectedImageAdapter selectedImageAdapter,ConstraintLayout clTasvirSuje) {
        this.arrayList = arrayList;
        this.arrayListTozihat = arrayListTozihat;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = selectedImageAdapter;
        this.clTasvirSuje = clTasvirSuje;
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

/*            holder.txRemove.setOnClickListener(new View.OnClickListener() {
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
            });*/

            holder.imgPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allDialogButton(c,arrayList,rAdapter,position);

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
        //TextView txAfzodanTozih;
        ImageView imgPicture;
        //EditText etTozih;
        MyViewHolder(View view) {
            super(view);

            imgPicture = itemView.findViewById(R.id.imgPicture);
            //txRemove = itemView.findViewById(R.id.txRemove);
            //txAfzodanTozih = itemView.findViewById(R.id.txAfzodanTozih);
            //etTozih = itemView.findViewById(R.id.etTozih);
        }
    }

    public void allDialogButton(final Context context, ArrayList<Uri> arrayList,SelectedImageAdapter rAdapter, int position) {

        final Dialog dialog = new Dialog(context, R.style.customDialogKar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_dialog_suje_media, null, false);

        ConstraintLayout clSabtTozihat = view.findViewById(R.id.clSabtTozihat);
        ConstraintLayout clRemove = view.findViewById(R.id.clRemove);

        clSabtTozihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "بزودی راه اندازی می شود", Toast.LENGTH_SHORT).show();

               /* dialogGozareshKarRoyeKar(c,position,"gozaresh_kar",
                        null,null,
                        null,list_family,list_id,recyclerModels,recyclerAdapterYouHaveKnow,id);
                dialog.dismiss();*/
            }
        });


        clRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //clickDialogItems(context,position,"delete",null,noeGozaresh,recyclerModels,adapter);
                arrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "حذف شد", Toast.LENGTH_SHORT).show();
                if (arrayList.size() == 0){
                    clTasvirSuje.setVisibility(View.GONE);
                }
                dialog.dismiss();
            }
        });

        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        //line zir baraye transparent kardan hashiye haye cardview ee:
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }


}
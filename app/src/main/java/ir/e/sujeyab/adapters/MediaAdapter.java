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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.R;
import ir.e.sujeyab.models.MediaModel;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MyViewHolder> {
    private ArrayList<MediaModel> arrayList;
    Context c;
    String rowLayoutType;
    MediaAdapter rAdapter;
    ConstraintLayout clTasvirSuje;

    public MediaAdapter(String rowLayoutType, Context c, ArrayList<MediaModel> arrayList,
                        MediaAdapter selectedImageAdapter) {
        this.arrayList = arrayList;
        this.rowLayoutType = rowLayoutType;
        this.c = c;
        this.rAdapter = selectedImageAdapter;
    }


    @Override
    public MediaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(rowLayoutType.matches("post_media")) {
             return new MediaAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_suje_media, parent, false));
         }
         return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint({"RecyclerView", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(final MediaAdapter.MyViewHolder holder, final int position) {

        if (rowLayoutType.matches("post_media")){
            //holder.txOnvan.setText(rModels.get(position).getOnvan());


            if (arrayList.get(position).getMediaUrl().toString().contains("jpg") || arrayList.get(position).getMediaUrl().toString().contains("png")) {
                Picasso.get()
                        .load(arrayList.get(position).getMediaUrl())
                        .centerCrop()
                        .fit()
                        .error(R.drawable.logo)
                        .placeholder(R.drawable.logo)
                        .into(holder.imgPicture);
            }else {
                Glide.with(c).load("empty")
                        .thumbnail(Glide.with(c).load(arrayList.get(position).getMediaUrl()))
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



    public void onItemRemoved(ArrayList<MediaModel> arrObjects){
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

    public void allDialogButton(final Context context, ArrayList<MediaModel> arrayList, MediaAdapter rAdapter, int position) {

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
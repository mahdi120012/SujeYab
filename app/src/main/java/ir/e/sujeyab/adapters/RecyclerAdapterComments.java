package ir.e.sujeyab.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ir.e.sujeyab.CustomClasses.EnglishNumberToPersian;
import ir.e.sujeyab.CustomClasses.Recyclerview;
import ir.e.sujeyab.CustomClasses.SharedPrefClass;
import ir.e.sujeyab.CustomClasses.TimeKononi;
import ir.e.sujeyab.LoadData;
import ir.e.sujeyab.R;
import ir.e.sujeyab.models.CommentsModel;

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
         }else if(rowLayoutType.matches("pasokh_comments")) {
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
            holder.txTedadPasokh.setText(new EnglishNumberToPersian().convert(rModels.get(position).getTedad_pasokh()));

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

            holder.clPasokh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialogGozareshKarRoyeKar(c,position,"gozaresh_kar",
                            null,null,
                            null,null ,null,rModels,rAdapter,rModels.get(position).getId());
                }
            });

            holder.txGozareshTakhalof.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        allDialogButton(c, rModels.get(position).getPostId());
                }
            });

        }else if (rowLayoutType.matches("pasokh_comments")){
            holder.txName.setText(rModels.get(position).getName());
            holder.txMatn.setText(rModels.get(position).getComment());
            holder.txDate.setText(new TimeKononi().changeGregorianToPersian(rModels.get(position).getDate_create()));
            holder.txTedadPasokh.setText(new EnglishNumberToPersian().convert(rModels.get(position).getTedad_pasokh()));

            if (position == 0){

            }


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

            holder.clPasokh.setVisibility(View.GONE);
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
        TextView txName,txMatn,txDate,txRateCount,txTedadPasokh, txGozareshTakhalof;
        ImageView imgPicture;
        ConstraintLayout clPasokh;

        MyViewHolder(View view) {
            super(view);

            txGozareshTakhalof = itemView.findViewById(R.id.gozareshTakhalof);
            clPasokh = itemView.findViewById(R.id.clPasokh);
            txTedadPasokh = itemView.findViewById(R.id.txTedadPasokh);
            txName = itemView.findViewById(R.id.txName);
            txMatn = itemView.findViewById(R.id.txMatn);
            txDate = itemView.findViewById(R.id.txDate);
            txRateCount = itemView.findViewById(R.id.txRateCount);
            imgPicture = itemView.findViewById(R.id.imgPicture);

        }
    }


    public void allDialogButton(Context c, String post_id) {
        final Dialog dialog = new Dialog(c, R.style.customDialogKar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_sabt_takhalof, null, false);

        final ConstraintLayout clSend = view.findViewById(R.id.clSend);
        final EditText etOnvan = view.findViewById(R.id.etOnvan);
        final EditText etMatn = view.findViewById(R.id.etMatn);


        clSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etOnvan.getText().toString().length() < 3 || etMatn.getText().toString().length() < 3){
                    Toast.makeText(c, "طول عنوان و شرح تخلف خیلی کوتاه است.", Toast.LENGTH_SHORT).show();

                    //clcl.snackbar("طول عنوان و شرح تخلف خیلی کوتاه است.")
                }else{

                    Toast.makeText(c, "گزارش شما با موفقیت ارسال شد.", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                }
            }
        });


        ((Activity) c).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        dialog.setContentView(view);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        //line zir baraye transparent kardan hashiye haye cardview ee:
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();

    }


    public void dialogGozareshKarRoyeKar(final Context context, final int position, final String method,
                                         final ImageView imgVaziyatTaeid, final TextView txOnvanMessageInRecivedMessage,
                                         final String noeGozaresh, final ArrayList<String> list_family, final ArrayList<String> list_id,
                                         final ArrayList<CommentsModel> recyclerModels, final RecyclerAdapterComments adapter,
                                         final String idKar) {
        final Dialog dialog = new Dialog(context, R.style.customDialogKar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gozaresh_roye_kar, null, false);
        final RecyclerView rvInInboxMessageTeacher = view.findViewById(R.id.rvInInboxMessageTeacher);
        final ConstraintLayout clWifiState = view.findViewById(R.id.clWifiState);
        ImageView imgBack = view.findViewById(R.id.imgBack);
        final EditText etMatnChat = view.findViewById(R.id.etMatnComment);
        final ImageView imgSend = view.findViewById(R.id.imgSend);
        TextView txTedadPasokh = view.findViewById(R.id.txTedadPasokh);
        txTedadPasokh.setText(new EnglishNumberToPersian().convert(recyclerModels.get(position).getTedad_pasokh()));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        etMatnChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    imgSend.setVisibility(View.VISIBLE);
                }else{
                    imgSend.setVisibility(View.GONE);
                }
            }
        });

        String username = SharedPrefClass.getUserId(c,"user");

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (username == "" || username == null){
                    Toast.makeText(c, "برای ارسال نظر ابتدا وارد شوید", Toast.LENGTH_SHORT).show();
                }else{

                    if (etMatnChat.getText().toString() == ""){
                        Toast.makeText(c, "طول نظر خیلی کوتاه است", Toast.LENGTH_SHORT).show();
                    }else{
                        LoadData.sendPasokhCommentsBaRetrofit(c,clWifiState,rModels,rAdapter, username, rModels.get(position).getPostId() ,idKar, etMatnChat, rvInInboxMessageTeacher);
                    }
                }

            }
        });


        ArrayList<CommentsModel> rModelsYouHaveKnow = null;
        RecyclerAdapterComments rAdapterYouHaveKnow = null;

        //LoadData.lastId2 = "0";
        rModelsYouHaveKnow = new ArrayList();

        rAdapterYouHaveKnow = new RecyclerAdapterComments("pasokh_comments", c, rModelsYouHaveKnow, rAdapterYouHaveKnow);
        Recyclerview.defineRecyclerViewVerticalComment(c, rvInInboxMessageTeacher, rAdapterYouHaveKnow, rModelsYouHaveKnow);

        //rAdapterYouHaveKnow = new  RecyclerAdapterYouHaveKnow(rModelsYouHaveKnow, "recived_message_dakhel_sepordan_kar", c, rAdapterYouHaveKnow, "",null,clShowErsal,null,"");
       /* Recyclerview.define_recyclerviewAddStudent(c, rvInInboxMessageTeacher, rAdapterYouHaveKnow,
                rModelsYouHaveKnow,null);*/

        LoadData.loadPasokhHayeCommentsBaRetrofit(c,clWifiState,rModelsYouHaveKnow,rAdapterYouHaveKnow, idKar);

/*

        LoadData.loadGozareshatUnderSepordanKar(c, rAdapterYouHaveKnow, rModelsYouHaveKnow,
                rvInInboxMessageTeacher, username,className,"sepordan_kar",noe,clWifiState,idKar);
*/


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
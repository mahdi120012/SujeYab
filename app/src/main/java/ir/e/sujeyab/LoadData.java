 package ir.e.sujeyab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ir.e.sujeyab.Controller.Api;
import ir.e.sujeyab.Controller.RetrofitProvider;
import ir.e.sujeyab.CustomClasses.MySingleton;
import ir.e.sujeyab.CustomClasses.ProgressDialogClass;
import ir.e.sujeyab.CustomClasses.Recyclerview;
import ir.e.sujeyab.CustomClasses.SharedPrefClass;
import ir.e.sujeyab.CustomClasses.TimeKononi;
import ir.e.sujeyab.CustomClasses.UrlEncoderClass;
import ir.e.sujeyab.SabtSuje.UploadResponse;
import ir.e.sujeyab.adapters.CatAdapter;
import ir.e.sujeyab.adapters.RadapterVaziyatFarakhan;
import ir.e.sujeyab.adapters.RecyclerAdapterCitys;
import ir.e.sujeyab.adapters.RecyclerAdapterComments;
import ir.e.sujeyab.adapters.RecyclerAdapterSujeHa;
import ir.e.sujeyab.adapters.RecyclerAdapterTv;
import ir.e.sujeyab.adapters.RecyclerAdapterVaziyatSujeha;
import ir.e.sujeyab.adapters.TasavirSujeAdapter;
import ir.e.sujeyab.login.Login;
import ir.e.sujeyab.login.TakmilEtelaat;
import ir.e.sujeyab.models.CatModel;
import ir.e.sujeyab.models.CitysModel;
import ir.e.sujeyab.models.CommentsModel;
import ir.e.sujeyab.models.FarakhanVijehModel;
import ir.e.sujeyab.models.RatesModel;
import ir.e.sujeyab.models.RecyclerModel;
import ir.e.sujeyab.models.RegisterModel;
import ir.e.sujeyab.models.SliderModel;
import ir.e.sujeyab.models.TakmilEtelaatModel;
import ir.e.sujeyab.models.TasavirSujeModel;
import ir.e.sujeyab.models.VaziyatModel;
import ir.e.sujeyab.models.VaziyatSujehaModel;
import ir.e.sujeyab.upload.MyResponse;
import me.relex.circleindicator.CircleIndicator;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadData {


    public static final int LOAD_LIMIT = 60;
    public static String lastId = "0";
    public static boolean itShouldLoadMore = true;





    public static void LikePost(final Context c, final ConstraintLayout clWifi, String username,
                                 final String sujeId, ImageView imgLike, TextView txTedadLike) {

        String usernameEncode = UrlEncoderClass.urlEncoder(username);
        String sujeIdEncode = UrlEncoderClass.urlEncoder(sujeId);
/*
                    "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/like_post?username1=124124&post_id=242"
*/
        String url= "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/like_post?username1=" + usernameEncode + "&post_id=" + sujeIdEncode;
        //itShouldLoadMore = false;
        /*final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);*/

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                //clWifi.setVisibility(View.GONE);
                //progressDialog.dismissProgress();
                //itShouldLoadMore = true;

                String message = null;
                if (response.length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else {

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            message = jsonObject.getString("message");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (message.matches("like shod")){
                    //Toast.makeText(c, "انجام شد", Toast.LENGTH_LONG).show();
                    imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like_red));
                    int tedad_like = Integer.parseInt(txTedadLike.getText().toString());
                    txTedadLike.setText(String.valueOf(tedad_like+1));
                }else {
                    //Toast.makeText(c, "انجام شد", Toast.LENGTH_LONG).show();
                    imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like));
                    int tedad_like = Integer.parseInt(txTedadLike.getText().toString());
                    txTedadLike.setText(String.valueOf(tedad_like-1));
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(c, (error.networkResponse.statusCode), Toast.LENGTH_LONG).show();

                //itShouldLoadMore = true;
                //progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }



    public static void LikePost3(final Context c, final ConstraintLayout clWifi, String username,
                                final String sujeId, ImageView imgLike, TextView txTedadLike) {



        Call<JsonObject> call = new RetrofitProvider().getApi().likePost(username,sujeId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {

                String message = null;
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        message = jsonObject.getString("message");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (message.matches("like shod")){
                    Toast.makeText(c, "انجام شد", Toast.LENGTH_LONG).show();
                    imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like_red));
                    int tedad_like = Integer.parseInt(txTedadLike.getText().toString());
                    txTedadLike.setText(String.valueOf(tedad_like+1));
                }else {
                    Toast.makeText(c, "انجام شد", Toast.LENGTH_LONG).show();
                    imgLike.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.like));
                    int tedad_like = Integer.parseInt(txTedadLike.getText().toString());
                    txTedadLike.setText(String.valueOf(tedad_like-1));
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void LoadTasavirSujeBaVolley(final Context c, final ConstraintLayout clWifi,
                                               final String sujeId, final ViewPager mPager, final CircleIndicator indicator, final ArrayList<TasavirSujeModel> ImgArray) {
        String sujeIdEncode = UrlEncoderClass.urlEncoder(sujeId);
        String url= "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/load_tasavir_suje?suje_id=" + sujeIdEncode;
     /*   itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
*/
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                /*clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;*/


                if (response.length() <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String p1 = jsonObject.getString("p1");
                        ImgArray.add(new TasavirSujeModel(p1));
                        mPager.setAdapter(new TasavirSujeAdapter(c,ImgArray,"slider",mPager));
                        indicator.setViewPager(mPager);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();

               /* itShouldLoadMore = true;
                progressDialog.dismissProgress();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*//*
                    }
                });*/

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }




    public static void LoadTasavirSujeBaRetrofit(final Context c, final ConstraintLayout clWifi,
                                                 final String sujeId, final ViewPager mPager, final CircleIndicator indicator, final ArrayList<TasavirSujeModel> ImgArray) {

        Call<List<TasavirSujeModel>> call = new RetrofitProvider().getApi().getTasavirSuje(sujeId);
        call.enqueue(new Callback<List<TasavirSujeModel>>() {
            @Override
            public void onResponse(Call<List<TasavirSujeModel>> call, retrofit2.Response<List<TasavirSujeModel>> response) {

                //if (response.isSuccessful()){}
                List<TasavirSujeModel> tasavirSujeModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (TasavirSujeModel tasavirSujeModel:tasavirSujeModels){
                    ImgArray.add(new TasavirSujeModel(tasavirSujeModel.getP1()));
                    mPager.setAdapter(new TasavirSujeAdapter(c,ImgArray,"slider",mPager));
                    indicator.setViewPager(mPager);
                }
            }

            @Override
            public void onFailure(Call<List<TasavirSujeModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void LoadPishkhanSliderByRetrofit(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                                    CircleIndicator indicator, ArrayList<SliderModel> ImgArray) {

        //String usernameEncode = UrlEncoderClass.urlEncoder(etUsername.getText().toString());
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
          Api api = retrofit.create(Api.class);*/

        //Api api = new RetrofitProvider().getApi();

        Call<List<SliderModel>> call = new RetrofitProvider().getApi().getSlider("main_slider_pishkhan");
        call.enqueue(new Callback<List<SliderModel>>() {
            @Override
            public void onResponse(Call<List<SliderModel>> call, retrofit2.Response<List<SliderModel>> response) {

                //if (response.isSuccessful()){}
                List<SliderModel> sliderModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (SliderModel sliderModel:sliderModels){
                    lastId = sliderModel.getId();
                    ImgArray.add(new SliderModel(lastId, sliderModel.getPicture(),sliderModel.getLink(),sliderModel.getDescription(),""));
                    mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider", mPager));
                    indicator.setViewPager(mPager);
                }
            }

            @Override
            public void onFailure(Call<List<SliderModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void checUsernameExsist(final Context c, final ConstraintLayout clWifi, EditText etUsername, EditText etPassword) {

        String usernameEncode = UrlEncoderClass.urlEncoder("0"+etUsername.getText().toString());
        String passwordEncode = UrlEncoderClass.urlEncoder(etPassword.getText().toString());

        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=check_username_exist&username1=" + usernameEncode + "&password1=" + passwordEncode;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                /*if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (response.length() <= 0) {
                    Toast.makeText(c, "شماره تلفن یا کلمه عبور خود را صحیح وارد کنید.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String username = null,password = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //lastId = jsonObject.getString("id");
                        username = jsonObject.getString("username");
                        password = jsonObject.getString("password");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //Toast.makeText(c, username.toString(), Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = c.getSharedPreferences("file", c.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user", username);
                editor.commit();


                AppCompatActivity activity = (AppCompatActivity) c;
                Fragment myFragment = new TakmilEtelaat();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();


                /*AppCompatActivity activity = (AppCompatActivity) c;
                Fragment myFragment = new TaeidShomareTelepohe();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }



/*    public static void uploadFile(Context c, Uri fileUri, String desc) {



        File file = new File(getRealPathFromURI(c,fileUri));

        //creating request body for file
        RequestBody requestFile = RequestBody.create(MediaType.parse(c.getContentResolver().getType(fileUri)), file);
        RequestBody descBody = RequestBody.create(MediaType.parse("text/plain"), desc);

        //The gson builder
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        //creating retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrlForUpload)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //creating our api
        Api api = retrofit.create(Api.class);

        //creating a call and calling the upload image method
        Call<MyResponse> call = api.uploadImage(requestFile, descBody);

        //finally performing the call
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, retrofit2.Response<MyResponse> response) {
                if (response.body().toString().contains("File Uploaded Successfully")) {
                    Toast.makeText(c, "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(c, "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Toast.makeText(c, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/
    public static String getRealPathFromURI(Context c, Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(c, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    public static void editMoshakhasatMan(final Context c, final ConstraintLayout clWifi, String username, String nameFamily,String tarikhTavallod,String jensiyat,String vaziyatTaahol,
                                String vaziyatNezamVazife,String akharinMadrakTahsili,String moadelMadrakTahsili,String reshteTahsili,String zaminehMoredAlagheHamkari,
                                String mizanSabegheKarMortabet,String sematShoghli,String codePerseneli, String email,String shomaeTelephoneTamas,String keshvar,
                                String ostan,String shahrestan,String shahr,String rosta, String neshani,String moaref,String telephoneTamasMoaref,String tozihat,
                                String jensiyatSp,String vaziyatTaaholSp,String vaziyatNezamVazifeSp,String akharinMadrakTahsiliSp,
                                ConstraintLayout clcl) {

        String jensiyatSpEncode = UrlEncoderClass.urlEncoder(jensiyatSp);
        String vaziyatTaaholSpEncode = UrlEncoderClass.urlEncoder(vaziyatTaaholSp);
        String vaziyatNezamVazifeSpEncode = UrlEncoderClass.urlEncoder(vaziyatNezamVazifeSp);
        String akharinMadrakTahsiliSpEncode = UrlEncoderClass.urlEncoder(akharinMadrakTahsiliSp);

        String usernameEncode = UrlEncoderClass.urlEncoder(username);
        String nameFamilyEncode = UrlEncoderClass.urlEncoder(nameFamily);
        String tarikhTavallodEncode = UrlEncoderClass.urlEncoder(tarikhTavallod);
        String jensiyatEncode = UrlEncoderClass.urlEncoder(jensiyat);
        String vaziyatTaaholEncode = UrlEncoderClass.urlEncoder(vaziyatTaahol);
        String vaziyatNezamVazifeEncode = UrlEncoderClass.urlEncoder(vaziyatNezamVazife);
        String akharinMadrakTahsiliEncode = UrlEncoderClass.urlEncoder(akharinMadrakTahsili);
        String moadelMadrakTahsiliEncode = UrlEncoderClass.urlEncoder(moadelMadrakTahsili);
        String reshteTahsiliEncode = UrlEncoderClass.urlEncoder(reshteTahsili);
        String zaminehMoredAlagheHamkariEncode = UrlEncoderClass.urlEncoder(zaminehMoredAlagheHamkari);
        String mizanSabegheKarMortabetEncode = UrlEncoderClass.urlEncoder(mizanSabegheKarMortabet);
        String sematShoghliEncode = UrlEncoderClass.urlEncoder(sematShoghli);
        String codePerseneliEncode = UrlEncoderClass.urlEncoder(codePerseneli);
        String emailEncode = UrlEncoderClass.urlEncoder(email);
        String shomaeTelephoneTamasEncode = UrlEncoderClass.urlEncoder(shomaeTelephoneTamas);
        String keshvarEncode = UrlEncoderClass.urlEncoder(keshvar);
        String ostanEncode = UrlEncoderClass.urlEncoder(ostan);
        String shahrestanEncode = UrlEncoderClass.urlEncoder(shahrestan);
        String shahrEncode = UrlEncoderClass.urlEncoder(shahr);
        String rostaEncode = UrlEncoderClass.urlEncoder(rosta);
        String neshaniEncode = UrlEncoderClass.urlEncoder(neshani);
        String moarefEncode = UrlEncoderClass.urlEncoder(moaref);
        String telephoneTamasMoarefEncode = UrlEncoderClass.urlEncoder(telephoneTamasMoaref);
        String tozihatEncode = UrlEncoderClass.urlEncoder(tozihat);



        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=edit_moshakhasat_man&username1=" + usernameEncode
                + "&name_family=" + nameFamilyEncode
                + "&tarikh_tavallod=" + tarikhTavallodEncode
                + "&jensiyat=" + jensiyatEncode
                + "&vaziyat_taahol=" + vaziyatTaaholEncode
                + "&vaziyat_nezamvazife=" + vaziyatNezamVazifeEncode
                + "&akharin_madrak_tahsili=" + akharinMadrakTahsiliEncode
                + "&moadel_madrak_tahsili=" + moadelMadrakTahsiliEncode
                + "&reshte_tahsili=" + reshteTahsiliEncode
                + "&zamineh_mored_alaghe_hamkari=" + zaminehMoredAlagheHamkariEncode
                + "&mizan_sabeghe_kar_mortabet=" + mizanSabegheKarMortabetEncode
                + "&semat_shoghli=" + sematShoghliEncode
                + "&code_perseneli=" + codePerseneliEncode
                + "&email=" + emailEncode
                + "&shomare_telephone=" + shomaeTelephoneTamasEncode
                + "&keshvar=" + keshvarEncode
                + "&ostan=" + ostanEncode
                + "&shahestan=" + shahrestanEncode
                + "&shahr=" + shahrEncode
                + "&rosta=" + rostaEncode
                + "&neshani=" + neshaniEncode
                + "&moaref=" + moarefEncode
                + "&telephone_tamas_moaref=" + telephoneTamasMoarefEncode
                + "&tozihat=" + tozihatEncode;

        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                /*if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (response.length() <= 0) {
                    Toast.makeText(c, "مشکلی بوجود آمده است.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String username = null,password = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //lastId = jsonObject.getString("id");
                        username = jsonObject.getString("username");
                        password = jsonObject.getString("password");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(c, "مشخصات شما با موفقیت ویرایش شد", Toast.LENGTH_SHORT).show();
                /*Snackbar snackbar = Snackbar.make(clcl, "مشخصات شما با موفقیت ویرایش شد", Snackbar.LENGTH_LONG);
                snackbar.show();*/
                AppCompatActivity activity = (AppCompatActivity) c;
                activity.finish();



               /* AppCompatActivity activity = (AppCompatActivity) c;
                Fragment myFragment = new TaeidShomareTelepohe();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //line haye zir baraye khatayabiye  ( baraye vaghti ke connection va ... error mide )
                if(error instanceof NoConnectionError){
                    ConnectivityManager cm = (ConnectivityManager)c
                            .getSystemService(c.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                        Toast.makeText(c, "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(c, "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("connection"))){
                    Toast.makeText(c, "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException){
                    Toast.makeText(c, "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException){
                    Toast.makeText(c, "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError){
                    Toast.makeText(c, "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof AuthFailureError){
                    Toast.makeText(c, "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(c, "Server is not responding.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(c, "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.GONE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public static void loadMoshakhasatBaRetrofit(final Context c, final ConstraintLayout clWifi, String username, EditText etNameFamily, EditText etTarikhTavalod
            , EditText etJensiyat, EditText etVaziyatTaahol, EditText etVaziyatNezamVazife, EditText etAkharinMadrakTahsili, EditText etMoadelMadrakTahsili
            , EditText etReshteTahsili, EditText etZamineMoredAlaghaHamkari, EditText etMizanSabegheKarMortabet, EditText etSematShoghli, EditText etCodePerseneli
            , EditText etEmail, EditText etShomaeTelephoneTamas, EditText etKeshvar, EditText etOstan, EditText etShahrestan
            , EditText etShahr, EditText etRosta, EditText etNeshani, EditText etMoaref, EditText etTelephoneTamasMoaref, EditText etTozihat, ImageView imgProfileImage) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<TakmilEtelaatModel>> call = api.getTakmilEtelaat(username);
        call.enqueue(new Callback<List<TakmilEtelaatModel>>() {
            @Override
            public void onResponse(Call<List<TakmilEtelaatModel>> call, retrofit2.Response<List<TakmilEtelaatModel>> response) {

                List<TakmilEtelaatModel> takmilEtelaatModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (TakmilEtelaatModel takmilEtelaatModel:takmilEtelaatModels){
                    lastId = takmilEtelaatModel.getId();
                    etNameFamily.setText(takmilEtelaatModel.getName_family());
                    etTarikhTavalod.setText(takmilEtelaatModel.getTarikh_tavallod());
                    etJensiyat.setText(takmilEtelaatModel.getJensiyat());
                    etVaziyatTaahol.setText(takmilEtelaatModel.getVaziyat_taahol());
                    etVaziyatNezamVazife.setText(takmilEtelaatModel.getVaziyat_nezam_vazife());
                    etAkharinMadrakTahsili.setText(takmilEtelaatModel.getAkharin_madrak_tahsili());
                    etMoadelMadrakTahsili.setText(takmilEtelaatModel.getMoadel_madrak_tahsili());
                    etReshteTahsili.setText(takmilEtelaatModel.getReshte_tahsili());
                    etZamineMoredAlaghaHamkari.setText(takmilEtelaatModel.getZamine_morede_alaghe_hamkari());
                    etMizanSabegheKarMortabet.setText(takmilEtelaatModel.getMizan_sabeghe_kar_motabet());
                    etSematShoghli.setText(takmilEtelaatModel.getSemat_shoghli());
                    etCodePerseneli.setText(takmilEtelaatModel.getCode_perseneli());
                    etEmail.setText(takmilEtelaatModel.getEmail());
                    etShomaeTelephoneTamas.setText(takmilEtelaatModel.getShomare_telephone());
                    etKeshvar.setText(takmilEtelaatModel.getKeshvar());
                    etOstan.setText(takmilEtelaatModel.getOstan());
                    etShahrestan.setText(takmilEtelaatModel.getShahestan());
                    etShahr.setText(takmilEtelaatModel.getShahr());
                    etRosta.setText(takmilEtelaatModel.getRosta());
                    etNeshani.setText(takmilEtelaatModel.getNeshani());
                    etMoaref.setText(takmilEtelaatModel.getMoaref());
                    etTelephoneTamasMoaref.setText(takmilEtelaatModel.getTelephone_tamas_moaref());
                    etTozihat.setText(takmilEtelaatModel.getTozihat());

                    if (takmilEtelaatModel.getProfile_picture().isEmpty()) {

                        Picasso.get()
                                .load(R.drawable.logo)
                                .centerInside()
                                .fit()
                                .error(R.drawable.logo)
                                .placeholder(R.drawable.logo)
                                .into(imgProfileImage);

                    }else{
                        Picasso.get()
                                .load(takmilEtelaatModel.getProfile_picture() )
                                .centerInside()
                                .fit()
                                .error(R.drawable.logo)
                                .placeholder(R.drawable.logo)
                                .into(imgProfileImage);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<TakmilEtelaatModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void loadMoshakhasat(final Context c, final ConstraintLayout clWifi, EditText etNameFamily, EditText etTarikhTavalod
            , EditText etJensiyat, EditText etVaziyatTaahol, EditText etVaziyatNezamVazife, EditText etAkharinMadrakTahsili, EditText etMoadelMadrakTahsili
            , EditText etReshteTahsili, EditText etZamineMoredAlaghaHamkari, EditText etMizanSabegheKarMortabet, EditText etSematShoghli, EditText etCodePerseneli
            , EditText etEmail, EditText etShomaeTelephoneTamas, EditText etKeshvar, EditText etOstan, EditText etShahrestan
            , EditText etShahr, EditText etRosta, EditText etNeshani, EditText etMoaref, EditText etTelephoneTamasMoaref, EditText etTozihat, ImageView imgProfileImage) {

        String usernameEncode = UrlEncoderClass.urlEncoder(SharedPrefClass.getUserId(c,"user"));

        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=load_moshakhasat_user&username1=" + usernameEncode ;
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;

                /*if (response.length() <= 0) {
                    Toast.makeText(c, "اطلاعاتی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (response.length() <= 0) {
                    Toast.makeText(c, "شماره تلفن خود را صحیح وارد کنید.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String username = null;
                String nameFamily = null;
                String tarikhTavallod = null;
                String jensiyat = null;
                String vaziyatTaahol = null;
                String vaziyatNezamVazife = null;
                String akharinMadrakTahsili = null;
                String moadelMadrakTahsili = null;
                String reshteTahsili = null;
                String zaminehMoredAlagheHamkari = null;
                String mizanSabegheKarMortabet = null;
                String sematShoghli = null;
                String codePerseneli = null;
                String email = null;
                String shomaeTelephoneTamas = null;
                String keshvar = null;
                String ostan = null;
                String shahrestan = null;
                String shahr = null;
                String rosta = null;
                String neshani = null;
                String moaref = null;
                String telephoneTamasMoaref = null;
                String tozihat = null;
                String profile_picture = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                         lastId = jsonObject.getString("id");
                         profile_picture = jsonObject.getString("profile_picture");
                         username = jsonObject.getString("username");
                         nameFamily = jsonObject.getString("name_family");
                         tarikhTavallod = jsonObject.getString("tarikh_tavallod");
                         jensiyat = jsonObject.getString("jensiyat");
                         vaziyatTaahol = jsonObject.getString("vaziyat_taahol");
                         vaziyatNezamVazife = jsonObject.getString("vaziyat_nezam_vazife");
                         akharinMadrakTahsili = jsonObject.getString("akharin_madrak_tahsili");
                         moadelMadrakTahsili = jsonObject.getString("moadel_madrak_tahsili");
                         reshteTahsili = jsonObject.getString("reshte_tahsili");
                         zaminehMoredAlagheHamkari = jsonObject.getString("zamine_morede_alaghe_hamkari");
                         mizanSabegheKarMortabet = jsonObject.getString("mizan_sabeghe_kar_motabet");
                         sematShoghli = jsonObject.getString("semat_shoghli");
                         codePerseneli = jsonObject.getString("code_perseneli");
                         email = jsonObject.getString("email");
                         shomaeTelephoneTamas = jsonObject.getString("shomare_telephone");
                         keshvar = jsonObject.getString("keshvar");
                         ostan = jsonObject.getString("ostan");
                         shahrestan = jsonObject.getString("shahestan");
                         shahr = jsonObject.getString("shahr");
                         rosta = jsonObject.getString("rosta");
                         neshani = jsonObject.getString("neshani");
                         moaref = jsonObject.getString("moaref");
                         telephoneTamasMoaref = jsonObject.getString("telephone_tamas_moaref");
                         tozihat = jsonObject.getString("tozihat");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                etNameFamily.setText(nameFamily);
                etTarikhTavalod.setText(tarikhTavallod);
                etJensiyat.setText(jensiyat);
                etVaziyatTaahol.setText(vaziyatTaahol);
                etVaziyatNezamVazife.setText(vaziyatNezamVazife);
                etAkharinMadrakTahsili.setText(akharinMadrakTahsili);
                etMoadelMadrakTahsili.setText(moadelMadrakTahsili);
                etReshteTahsili.setText(reshteTahsili);
                etZamineMoredAlaghaHamkari.setText(zaminehMoredAlagheHamkari);
                etMizanSabegheKarMortabet.setText(mizanSabegheKarMortabet);
                etSematShoghli.setText(sematShoghli);
                etCodePerseneli.setText(codePerseneli);
                etEmail.setText(email);
                etShomaeTelephoneTamas.setText(shomaeTelephoneTamas);
                etKeshvar.setText(keshvar);
                etOstan.setText(ostan);
                etShahrestan.setText(shahrestan);
                etShahr.setText(shahr);
                etRosta.setText(rosta);
                etNeshani.setText(neshani);
                etMoaref.setText(moaref);
                etTelephoneTamasMoaref.setText(telephoneTamasMoaref);
                etTozihat.setText(tozihat);

                if (profile_picture.isEmpty()) {

                    Picasso.get()
                            .load(R.drawable.suje_icon)
                            .centerInside()
                            .fit()
                            .error(R.drawable.suje_icon)
                            .placeholder(R.drawable.suje_icon)
                            .into(imgProfileImage);

                }else{
                    Picasso.get()
                            .load(profile_picture)
                            .centerInside()
                            .fit()
                            .error(R.drawable.suje_icon)
                            .placeholder(R.drawable.suje_icon)
                            .into(imgProfileImage);
                }


                /*AppCompatActivity activity = (AppCompatActivity) c;
                Fragment myFragment = new TaeidShomareTelepohe();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public static void loadKhadamatVijehSliderBaRetrofit(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                                         CircleIndicator indicator, ArrayList<SliderModel> ImgArray, NestedScrollView nestedScrollView, ProgressBar progressBar) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getKhedmatHayeVijeh();

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){
                    lastId = farakhanVijehModel.getId();
                    ImgArray.add(new SliderModel(lastId, farakhanVijehModel.getOnvan(),farakhanVijehModel.getMozo(),farakhanVijehModel.getMatn_kholase(),""));
                    mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider_khadamat_vijeh", mPager));
                    indicator.setViewPager(mPager);

                }
                nestedScrollView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                clWifi.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void loadKhadamatVijehSlider(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                               CircleIndicator indicator, ArrayList<SliderModel> ImgArray) {


        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=load_khadamat_vijeh";
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;


                if (response.length() <= 0) {
                    Toast.makeText(c, "اسلایدی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String onvan = null;
                String mozo = null;
                String matn_kholase = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        onvan = jsonObject.getString("onvan");
                        mozo = jsonObject.getString("mozo");
                        matn_kholase = jsonObject.getString("matn_kholase");

                        ImgArray.add(new SliderModel(lastId, onvan,mozo,matn_kholase,""));
                        mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider_khadamat_vijeh", mPager));
                        indicator.setViewPager(mPager);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public static void loadSujeHayeVijehSliderBaRetrofit(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                               CircleIndicator indicator, ArrayList<SliderModel> ImgArray,ArrayList<FarakhanVijehModel> sujeModel) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<FarakhanVijehModel>> call = api.getSujeHayeVijeh();
        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    sujeModel.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),farakhanVijehModel.getMiyangin_rate(),"","",""));

                    ImgArray.add(new SliderModel(lastId, farakhanVijehModel.getOnvan(),farakhanVijehModel.getName_family(),"(" + farakhanVijehModel.getSemat_shoghli() + ")",farakhanVijehModel.getMatn_kholase()));
                    mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider_suje_haye_vijeh", mPager,sujeModel));
                    indicator.setViewPager(mPager);
                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void loadSujeHayeVijehSlider(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                          CircleIndicator indicator, ArrayList<SliderModel> ImgArray) {


        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=load_suje_haye_vijeh";
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;


                if (response.length() <= 0) {
                    Toast.makeText(c, "اسلایدی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String onvan = null;
                String name_family = null;
                String semat_shoghli = null;
                String matn_kholase = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        onvan = jsonObject.getString("onvan");
                        name_family = jsonObject.getString("name_family");
                        semat_shoghli = jsonObject.getString("semat_shoghli");
                        matn_kholase = jsonObject.getString("matn_kholase");

                        ImgArray.add(new SliderModel(lastId, onvan,name_family,"(" + semat_shoghli + ")",matn_kholase));
                        mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider_suje_haye_vijeh", mPager));
                        indicator.setViewPager(mPager);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }



    public static void addSujeJadid(Context c, final ConstraintLayout clWifi,EditText etOnvan,EditText etMozo,EditText etTozihat) {

        Call<List<FarakhanVijehModel>> call = new RetrofitProvider().getApi().addSujeJadid(etOnvan.getText().toString(),etMozo.getText().toString(),etTozihat.getText().toString());
        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){
                    //lastId = farakhanVijehModel.getId();
                    /*ImgArray.add(new SliderModel(lastId, farakhanVijehModel.getOnvan(),farakhanVijehModel.getName_family(),"(" + farakhanVijehModel.getSemat_shoghli() + ")",farakhanVijehModel.getMatn_kholase()));
                    mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider_suje_haye_vijeh"));
                    indicator.setViewPager(mPager);*/
                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, "سوژه جدید با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public static void loadPishkhanSlider(Context c, final ConstraintLayout clWifi, ViewPager mPager,
                                          CircleIndicator indicator, ArrayList<SliderModel> ImgArray) {


        String url= "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/load_pishkhan_slider";
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;


                if (response.length() <= 0) {
                    Toast.makeText(c, "اسلایدی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String picture = null;
                String link = null;
                String description = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        picture = jsonObject.getString("picture");
                        link = jsonObject.getString("link");
                        description = jsonObject.getString("description");

                        ImgArray.add(new SliderModel(lastId, picture,link,description,""));
                        mPager.setAdapter(new ViewPagerAdapterForSlider(c, ImgArray,"slider", mPager));
                        indicator.setViewPager(mPager);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

   public static void loadFarakhanHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getFarakhanHa();

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    rModels.add(new RecyclerModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan()
                            ,farakhanVijehModel.getModat_baghimande(),
                            farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getDate_create(),"","",farakhanVijehModel.getTedad_suje()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void registerBaRetrifit(Context c, final ConstraintLayout clcl,  final ConstraintLayout clWifi, String username, String password) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<UploadResponse> call = api.registerUser(username, password);

        call.enqueue(new Callback<UploadResponse>() {


            @Override
            public void onResponse(Call<UploadResponse> call, retrofit2.Response<UploadResponse> response) {


                Snackbar snackbar = Snackbar.make(clcl, response.body().getMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();

                if (response.body().getError() == false){

                    SharedPreferences sharedPreferences = c.getSharedPreferences("file", c.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user", username);
                    editor.commit();

                    AppCompatActivity activity = (AppCompatActivity) c;
                    Fragment myFragment = new TakmilEtelaat();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.clcl, myFragment).addToBackStack(null).commit();


                    /*Intent intent = new Intent(c, Login.class);
                    c.startActivity(intent);

                    Activity activity = (Activity)c;
                    activity.finish();*/
                }





                //List<RegisterModel> registerModels = response.body();


                /*for (RegisterModel registerModel:registerModels){

                    lastId = registerModel.get();
                    rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create()));
                    rAdapter.notifyDataSetChanged();

                }*/
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Toast.makeText(c, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }


    public static void loadTvBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels, RecyclerAdapterTv rAdapter
                                        ,String username1) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getTv(username1);

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),"","","",""));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void loadOstanHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CitysModel> rModels, RecyclerAdapterCitys rAdapter) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <CitysModel>> call = api.load_ostan();

        call.enqueue(new Callback<List<CitysModel>>() {
            @Override
            public void onResponse(Call<List<CitysModel>> call, retrofit2.Response<List<CitysModel>> response) {
                List<CitysModel> citysModels = response.body();
                for (CitysModel citysModel:citysModels){

                    lastId = citysModel.getId();
                    rModels.add(new CitysModel(lastId, citysModel.getName()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<CitysModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static void loadShahrestanHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CitysModel> rModels, RecyclerAdapterCitys rAdapter,String ostan_id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <CitysModel>> call = api.load_shahrestan_by_ostan_code(ostan_id);

        call.enqueue(new Callback<List<CitysModel>>() {
            @Override
            public void onResponse(Call<List<CitysModel>> call, retrofit2.Response<List<CitysModel>> response) {
                List<CitysModel> citysModels = response.body();
                for (CitysModel citysModel:citysModels){

                    lastId = citysModel.getId();
                    rModels.add(new CitysModel(lastId, citysModel.getName()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<CitysModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static void loadShahrHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CitysModel> rModels, RecyclerAdapterCitys rAdapter,String shahrestan_id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <CitysModel>> call = api.load_shahr_by_shahrestan_code(shahrestan_id);

        call.enqueue(new Callback<List<CitysModel>>() {
            @Override
            public void onResponse(Call<List<CitysModel>> call, retrofit2.Response<List<CitysModel>> response) {
                List<CitysModel> citysModels = response.body();
                for (CitysModel citysModel:citysModels){

                    lastId = citysModel.getId();
                    rModels.add(new CitysModel(lastId, citysModel.getName()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<CitysModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static void loadAbadiHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CitysModel> rModels, RecyclerAdapterCitys rAdapter,String shahrestan_id) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <CitysModel>> call = api.load_abadi_by_shahrestan_code(shahrestan_id);

        call.enqueue(new Callback<List<CitysModel>>() {
            @Override
            public void onResponse(Call<List<CitysModel>> call, retrofit2.Response<List<CitysModel>> response) {
                List<CitysModel> citysModels = response.body();
                for (CitysModel citysModel:citysModels){

                    lastId = citysModel.getId();
                    rModels.add(new CitysModel(lastId, citysModel.getName()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<CitysModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void sendRateBaRetrofit(Context c, final ConstraintLayout clWifi,String username ,String postId, String rate) {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        Api api = retrofit.create(Api.class);
        Call<JsonObject> call = api.sendRate(username, postId, rate);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {

                String message = null;
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        message = jsonObject.getString("message");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (message.matches("rate ersal shod")){

                    Toast.makeText(c, "ارسال شد", Toast.LENGTH_SHORT).show();

                }else if (message.matches("rate update shod")){
                    Toast.makeText(c, "ویرایش شد", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(c, "ارسال نشد", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void sendCommentsBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CommentsModel> rModels,
                                              RecyclerAdapterComments rAdapter,String username ,String postId, EditText etComment,
                                              RecyclerView rv1) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<JsonObject> call = api.sendComments(username, postId, etComment.getText().toString());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {

                String message = null;
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        message = jsonObject.getString("message");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (message.matches("comment ersal shod")){
                    Toast.makeText(c, "ارسال شد", Toast.LENGTH_LONG).show();
                    etComment.setText("");

                    ArrayList<CommentsModel> rModels = null;
                    RecyclerAdapterComments rAdapter = null;
                    //rv1.setAdapter(null);

                    rModels = new ArrayList<>();
                    rAdapter = new RecyclerAdapterComments("comments", c, rModels, rAdapter);
                    Recyclerview.defineRecyclerViewVerticalComment(c, rv1, rAdapter, rModels);
                    LoadData.loadCommentsBaRetrofit(c,clWifi,rModels,rAdapter, postId);

                }else {
                    Toast.makeText(c, "ارسال نشد", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void loadCommentsBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<CommentsModel> rModels,
                                              RecyclerAdapterComments rAdapter,String postId) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <CommentsModel>> call = api.getComments(postId);

        call.enqueue(new Callback<List<CommentsModel>>() {
            @Override
            public void onResponse(Call<List<CommentsModel>> call, retrofit2.Response<List<CommentsModel>> response) {
                List<CommentsModel> commentsModels = response.body();
                for (CommentsModel commentsModel:commentsModels){

                    lastId = commentsModel.getId();
                    rModels.add(new CommentsModel(lastId, commentsModel.getUsername(),commentsModel.getPostId(),
                            commentsModel.getComment(),commentsModel.getDate_create(),commentsModel.getName(),
                            commentsModel.getProfile_picture()));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<CommentsModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void loadTotalRateAndRateAvgBaRetrofit(Context c, final ConstraintLayout clWifi, String postId,
                                                         TextView txTedadRate, TextView txRateAvg) {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        Api api = retrofit.create(Api.class);
        Call<List <RatesModel>> call = api.getTotalRateAndRateAVG(postId);

        call.enqueue(new Callback<List<RatesModel>>() {
            @Override
            public void onResponse(Call<List<RatesModel>> call, retrofit2.Response<List<RatesModel>> response) {
                List<RatesModel> ratesModels = response.body();
                for (RatesModel ratesModel:ratesModels){
                    txTedadRate.setText(ratesModel.getTedad_rate());
                    txRateAvg.setText(ratesModel.getMiyangin_rate());
                    //ratingBar.setRating(Float.parseFloat(ratesModel.getMiyangin_rate()));
                }
            }

            @Override
            public void onFailure(Call<List<RatesModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void loadFarakhanHaBarAsasVaziyatBaVolley(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter,String vaziyat) {
        String vaziyatEncode = UrlEncoderClass.urlEncoder(vaziyat);
        String url= "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/load_farakhan_ha_bar_asas_vaziyat?vaziyat=" + vaziyatEncode;
     /*   itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
*/
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                /*clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;*/


                if (response.length() <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String picture = jsonObject.getString("p1");
                        String onvan = jsonObject.getString("onvan");
                        String modat_baghimande = jsonObject.getString("modat_baghimande");
                        String matn_kholase = jsonObject.getString("matn_kholase");
                        //String mozo = jsonObject.getString("mozo");
                        //String id_ferestande = jsonObject.getString("id_ferestande");
                        String motavali = jsonObject.getString("motavali");
                        //String type = jsonObject.getString("type");
                        //String type_vaziyat_farakhan = jsonObject.getString("type_vaziyat_farakhan");
                        //String name_family = jsonObject.getString("name_family");
                        //String semat_shoghli = jsonObject.getString("semat_shoghli");
                        String date_create = jsonObject.getString("date_create");
                        String vaziyat_like = jsonObject.getString("vaziyat_like");
                        String tedad_like = jsonObject.getString("tedad_like");
                        String tedad_suje = jsonObject.getString("tedad_suje");
                        //String link_video = jsonObject.getString("link_video");
                        /*String miyangin_rate = jsonObject.getString("miyangin_rate");
                        String tedad_comment = jsonObject.getString("tedad_comment");*/


                         rModels.add(new RecyclerModel(lastId, picture,onvan
                                    ,modat_baghimande,
                                 matn_kholase,motavali,date_create,vaziyat_like,tedad_like,tedad_suje));
                         rAdapter.notifyDataSetChanged();



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, "چیزی یافت نشد!", Toast.LENGTH_SHORT).show();

               /* itShouldLoadMore = true;
                progressDialog.dismissProgress();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*//*
                    }
                });*/

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }


    public static void loadFarakhanHaBarAsasVaziyat(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter,String vaziyat) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getFarakhanHaBarAsasVaziyat(vaziyat);

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {

                if (response.body() == null){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else {

                    List<FarakhanVijehModel> farakhanVijehModels = response.body();
                    for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                        lastId = farakhanVijehModel.getId();
                        rModels.add(new RecyclerModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan()
                                ,farakhanVijehModel.getModat_baghimande(),
                                farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getTedad_suje()));
                        rAdapter.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void loadSujeHaBarAsasVaziyatBaVolley(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels, RecyclerAdapterSujeHa rAdapter, String vaziyat) {
        String vaziyatEncode = UrlEncoderClass.urlEncoder(vaziyat);
        String url= "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/load_suje_ha_bar_asas_vaziyat?vaziyat=" + vaziyatEncode;
     /*   itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);
*/
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                /*clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;*/


                if (response.length() <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        String picture = jsonObject.getString("p1");
                        String onvan = jsonObject.getString("onvan");
                        //String modat_baghimande = jsonObject.getString("modat_baghimande");
                        String matn_kholase = jsonObject.getString("matn_kholase");
                        String mozo = jsonObject.getString("mozo");
                        String id_ferestande = jsonObject.getString("id_ferestande");
                        String motavali = jsonObject.getString("motavali");
                        String type = jsonObject.getString("type");
                        String type_vaziyat_farakhan = jsonObject.getString("type_vaziyat_farakhan");
                        String name_family = jsonObject.getString("name_family");
                        String semat_shoghli = jsonObject.getString("semat_shoghli");
                        String date_create = jsonObject.getString("date_create");
                        String vaziyat_like = jsonObject.getString("vaziyat_like");
                        String tedad_like = jsonObject.getString("tedad_like");
                        String link_video = jsonObject.getString("link_video");
                        String onvan_farakhan = jsonObject.getString("onvan_farakhan");
                        /*String miyangin_rate = jsonObject.getString("miyangin_rate");
                        String tedad_comment = jsonObject.getString("tedad_comment");*/

                        rModels.add(new FarakhanVijehModel(lastId, picture,onvan,
                                "",matn_kholase,mozo,id_ferestande,motavali,type,type_vaziyat_farakhan
                                ,name_family,semat_shoghli,date_create,vaziyat_like,tedad_like,link_video,"","",onvan_farakhan,""));
                        rAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, "چیزی یافت نشد!", Toast.LENGTH_SHORT).show();

               /* itShouldLoadMore = true;
                progressDialog.dismissProgress();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       *//* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*//*
                    }
                });*/

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

    public static void loadSujeHaBarAsasVaziyat(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels, RecyclerAdapterSujeHa rAdapter, String vaziyat) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getSujeHaBarAsasVaziyat(vaziyat);

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {

                if (response.body() == null){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }else {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),"","","",""));
                    rAdapter.notifyDataSetChanged();

                }
                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void loadSujeHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels, RecyclerAdapterSujeHa rAdapter) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getSujeHa();

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),"","",farakhanVijehModel.getOnvan_farakhan(),""));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void loadSujeHaBaMozo1Retrofit(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels,
                                                 RecyclerAdapterSujeHa rAdapter,String mozoName) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getSujeHaBaMozo1(mozoName);

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                    lastId = farakhanVijehModel.getId();
                    rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                            farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                            farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                            farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                            farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),"","",farakhanVijehModel.getOnvan_farakhan(),""));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void loadSearchResult(Context c, final ConstraintLayout clWifi, ArrayList<FarakhanVijehModel> rModels, RecyclerAdapterSujeHa rAdapter,String onvan) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <FarakhanVijehModel>> call = api.getSearchResult(onvan);

        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {

                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();

                }else{
                    List<FarakhanVijehModel> farakhanVijehModels = response.body();
                    for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){

                        lastId = farakhanVijehModel.getId();
                        rModels.add(new FarakhanVijehModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),
                                farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMozo(),
                                farakhanVijehModel.getId_ferestande(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getType(),
                                farakhanVijehModel.getType_vaziyat_farakhan(),farakhanVijehModel.getName_family(),farakhanVijehModel.getSemat_shoghli(),
                                farakhanVijehModel.getDate_create(),farakhanVijehModel.getVaziyat_like(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getLink_video(),"","","",""));
                        rAdapter.notifyDataSetChanged();

                }




                }
            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

  /*  public static void loadVaziyatSujeHaBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<VaziyatSujehaModel> rModels, RecyclerAdapterVaziyatSujeha rAdapter) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<VaziyatSujehaModel>> call = api.getVaziyatSujeHa();
        call.enqueue(new Callback<List<VaziyatSujehaModel>>() {
            @Override
            public void onResponse(Call<List<VaziyatSujehaModel>> call, retrofit2.Response<List<VaziyatSujehaModel>> response) {
                List<VaziyatSujehaModel> vaziyatSujehaModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (VaziyatSujehaModel vaziyatSujehaModel:vaziyatSujehaModels){
                    lastId = vaziyatSujehaModel.getId();
                    rModels.add(new VaziyatSujehaModel(lastId, vaziyatSujehaModel.getName_vaziyat_suje_ha()));
                    rAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<VaziyatSujehaModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }*/


    public static void loadVaziyatSujeHa(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List <VaziyatModel>> call = api.getVaziyatSujeHa();

        call.enqueue(new Callback<List<VaziyatModel>>() {
            @Override
            public void onResponse(Call<List<VaziyatModel>> call, retrofit2.Response<List<VaziyatModel>> response) {
                List<VaziyatModel> models = response.body();
                for (VaziyatModel model:models){

                    lastId = model.getId();
                    rModels.add(new RecyclerModel(lastId, model.getName_vaziyat_suje_ha(),""
                            ,"",
                            "","","","","",""));
                    rAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<VaziyatModel>> call, Throwable t) {
                Toast.makeText(c, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    public static void loadVaziyatFarakhan(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels, RadapterVaziyatFarakhan rAdapter) {


        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=load_vaziyat_farakhan";
        itShouldLoadMore = false;
       // final ProgressDialogClass progressDialog = new ProgressDialogClass();
       //progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                //progressDialog.dismissProgress();
                itShouldLoadMore = true;


                if (response.length() <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name_vaziyat_farakhan = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        name_vaziyat_farakhan = jsonObject.getString("name_vaziyat_farakhan");

                        rModels.add(new RecyclerModel(lastId, name_vaziyat_farakhan,"","","","","","","",""));
                        rAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                //progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }


    public static void loadCat_0(Context c, final ConstraintLayout clWifi, ArrayList<CatModel> rModels, CatAdapter rAdapter
            , NestedScrollView nestedScrollView, ProgressBar progressBar) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<CatModel>> call = api.getCat_0();
        call.enqueue(new Callback<List<CatModel>>() {
            @Override
            public void onResponse(Call<List<CatModel>> call, retrofit2.Response<List<CatModel>> response) {
                List<CatModel> catModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (CatModel catModel:catModels){
                    lastId = catModel.getId();
                    rModels.add(new CatModel(lastId, catModel.getCat(),catModel.getMain_cat(),catModel.getPicture(),catModel.getTedad_mataleb()));
                    rAdapter.notifyDataSetChanged();
                }
                nestedScrollView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<CatModel>> call, Throwable t) {
                clWifi.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void loadCat_1(Context c, final ConstraintLayout clWifi, ArrayList<CatModel> rModels, CatAdapter rAdapter
            , NestedScrollView nestedScrollView, ProgressBar progressBar,String mainCat) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<CatModel>> call = api.getCat_1(mainCat);
        call.enqueue(new Callback<List<CatModel>>() {
            @Override
            public void onResponse(Call<List<CatModel>> call, retrofit2.Response<List<CatModel>> response) {
                List<CatModel> catModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (CatModel catModel:catModels){
                    lastId = catModel.getId();
                    rModels.add(new CatModel(lastId, catModel.getCat(),catModel.getMain_cat(),catModel.getPicture(),catModel.getTedad_mataleb()));
                    rAdapter.notifyDataSetChanged();
                }
                nestedScrollView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<CatModel>> call, Throwable t) {
                clWifi.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void loadFarakhanVijehBaRetrofit(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter
            , NestedScrollView nestedScrollView, ProgressBar progressBar) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<FarakhanVijehModel>> call = api.getFarakhanVijeh();
        call.enqueue(new Callback<List<FarakhanVijehModel>>() {
            @Override
            public void onResponse(Call<List<FarakhanVijehModel>> call, retrofit2.Response<List<FarakhanVijehModel>> response) {
                List<FarakhanVijehModel> farakhanVijehModels = response.body();
                if (response.body().toString().length() <= 0){
                    Toast.makeText(c, "چیزی موجود نیست", Toast.LENGTH_SHORT).show();
                }

                for (FarakhanVijehModel farakhanVijehModel:farakhanVijehModels){
                    lastId = farakhanVijehModel.getId();
                    rModels.add(new RecyclerModel(lastId, farakhanVijehModel.getPicture(),farakhanVijehModel.getOnvan(),farakhanVijehModel.getModat_baghimande(),farakhanVijehModel.getMatn_kholase(),farakhanVijehModel.getMotavali(),farakhanVijehModel.getDate_create(),farakhanVijehModel.getTedad_like(),farakhanVijehModel.getTedad_comment(),""));
                    rAdapter.notifyDataSetChanged();
                }
                nestedScrollView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<FarakhanVijehModel>> call, Throwable t) {
                clWifi.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(c, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public static void loadFarakhanVijeh(Context c, final ConstraintLayout clWifi, ArrayList<RecyclerModel> rModels,RecyclerAdapter rAdapter) {


        String url= "http://robika.ir/ultitled/practice/sujeyab/sujeyab_load_data.php?action=load_farakhan_vijeh";
        itShouldLoadMore = false;
        final ProgressDialogClass progressDialog = new ProgressDialogClass();
        progressDialog.showProgress(c);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                clWifi.setVisibility(View.GONE);
                progressDialog.dismissProgress();
                itShouldLoadMore = true;


                if (response.length() <= 0) {
                    Toast.makeText(c, "چیزی موجود نیست.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String picture = null;
                String onvan = null;
                String modat_baghimande = null;
                String matn_kholase = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        lastId = jsonObject.getString("id");
                        picture = jsonObject.getString("picture");
                        onvan = jsonObject.getString("onvan");
                        modat_baghimande = jsonObject.getString("modat_baghimande");
                        matn_kholase = jsonObject.getString("matn_kholase");
                        rModels.add(new RecyclerModel(lastId, picture,onvan,modat_baghimande,matn_kholase,"","","","",""));
                        rAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                itShouldLoadMore = true;
                progressDialog.dismissProgress();
                Toast.makeText(c, "دسترسی به اینترنت موجود نیست!", Toast.LENGTH_SHORT).show();
                clWifi.setVisibility(View.VISIBLE);

                clWifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       /* LoadData.checUsernameExsist(c, recyclerAdapter, recyclerModels,
                                recyclerView, "", clWifi);*/
                    }
                });

            }
        });

        MySingleton.getInstance(c).addToRequestQueue(jsonArrayRequest);
    }

}

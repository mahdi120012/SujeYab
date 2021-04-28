package ir.e.sujeyab.Controller;

import com.google.gson.JsonObject;

import java.util.List;

import ir.e.sujeyab.SabtSuje.UploadImage;
import ir.e.sujeyab.SabtSuje.UploadResponse;
import ir.e.sujeyab.models.CitysModel;
import ir.e.sujeyab.models.CommentsModel;
import ir.e.sujeyab.models.RatesModel;
import ir.e.sujeyab.models.RegisterModel;
import ir.e.sujeyab.models.TakmilEtelaatModel;
import ir.e.sujeyab.models.FarakhanVijehModel;
import ir.e.sujeyab.models.SliderModel;
import ir.e.sujeyab.models.TasavirSujeModel;
import ir.e.sujeyab.models.VaziyatModel;
import ir.e.sujeyab.upload.MyResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    String baseUrl = "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/";
    String baseUrlForUpload = "http://robika.ir/ultitled/practice/sujeyab/upload_test/";
    @GET("load_pishkhan_slider")
    Call<List<SliderModel>> getSlider(@Query("type") String type);

    @GET("load_farakhan_vijeh")
    Call<List<FarakhanVijehModel>> getFarakhanVijeh();

    @GET("load_khadamat_vijeh")
    Call<List<FarakhanVijehModel>> getKhedmatHayeVijeh();

    @GET("load_suje_haye_vijeh")
    Call<List<FarakhanVijehModel>> getSujeHayeVijeh();

    @GET("load_moshakhasat_user")
    Call<List<TakmilEtelaatModel>> getTakmilEtelaat(@Query("username") String username);

    @GET("load_farakhan_ha")
    Call<List<FarakhanVijehModel>> getFarakhanHa();

    @GET("load_suje_ha")
    Call<List<FarakhanVijehModel>> getSujeHa();

    @GET("load_search_result")
    Call<List<FarakhanVijehModel>> getSearchResult(@Query("onvan") String onvan);

    @GET("load_comments")
    Call<List<CommentsModel>> getComments(@Query("post_id") String postId);

    @GET("send_comment")
    Call<JsonObject> sendComments(@Query("username1") String username, @Query("post_id") String postId, @Query("comment") String comment);

    @GET("send_rate")
    Call<JsonObject> sendRate(@Query("username1") String username, @Query("post_id") String postId, @Query("rate") String rate);

    @GET("load_total_rate_and_rate_avg")
    Call<List<RatesModel>> getTotalRateAndRateAVG(@Query("post_id") String postId);

    @GET("load_ostan")
    Call<List<CitysModel>> load_ostan();

    @GET("load_shahrestan_by_ostan_code")
    Call<List<CitysModel>> load_shahrestan_by_ostan_code(@Query("ostan_id") String ostan_id);

    @GET("load_shahr_by_shahrestan_code")
    Call<List<CitysModel>> load_shahr_by_shahrestan_code(@Query("shahrestan_id") String shahrestan_id);

    @GET("load_abadi_by_shahrestan_code")
    Call<List<CitysModel>> load_abadi_by_shahrestan_code(@Query("shahrestan_id") String shahrestan_id);

    @GET("load_tv")
    Call<List<FarakhanVijehModel>> getTv();

    @GET("load_vaziyat_suje_ha")
    Call<List<VaziyatModel>> getVaziyatSujeHa();

    @GET("add_suje_jadid")
    Call<List<FarakhanVijehModel>> addSujeJadid(@Query("onvan") String onvan, @Query("mozo") String mozo, @Query("tozihat") String tozihat);

    @Multipart
    @POST("upload")
    Call<UploadImage> uploadImage(@Part MultipartBody.Part Image, @Part("user") RequestBody User);

    /*@Multipart
    @POST("Api.php?apicall=upload")
    Call<MyResponse> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);*/

    @POST("register")
    Call<UploadResponse> registerUser(@Query("username") String username, @Query("password") String password);

    @GET("load_tasavir_suje")
    Call<List<TasavirSujeModel>> getTasavirSuje(@Query("suje_id") String suje_id);

    @GET("like_post")
    Call<JsonObject> likePost(@Query("username1") String username, @Query("post_id") String post_id);
}

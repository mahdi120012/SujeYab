package ir.e.sujeyab.Controller;

import java.util.List;

import ir.e.sujeyab.SabtSuje.UploadImage;
import ir.e.sujeyab.SabtSuje.UploadResponse;
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


}

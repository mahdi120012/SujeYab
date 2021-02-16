package ir.e.sujeyab.Controller;

import java.util.List;

import ir.e.sujeyab.models.TakmilEtelaatModel;
import ir.e.sujeyab.models.FarakhanVijehModel;
import ir.e.sujeyab.models.SliderModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String baseUrl = "http://robika.ir/ultitled/practice/sujeyab/laravel_app/api/";
    @GET("load_pishkhan_slider")
    Call<List<SliderModel>> getSlider();

    @GET("load_farakhan_vijeh")
    Call<List<FarakhanVijehModel>> getFarakhanVijeh();

    @GET("load_khadamat_vijeh")
    Call<List<FarakhanVijehModel>> getKhedmatHayeVijeh();

    @GET("load_suje_haye_vijeh")
    Call<List<FarakhanVijehModel>> getSujeHayeVijeh();

    @GET("load_moshakhasat_user")
    Call<List<TakmilEtelaatModel>> getTakmilEtelaat();

    @GET("load_farakhan_ha")
    Call<List<FarakhanVijehModel>> getFarakhanHa();

}

package ir.e.sujeyab.Controller;

import java.util.List;

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

}

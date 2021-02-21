package ir.e.sujeyab.Controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private Api api;
    public RetrofitProvider(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(Api.class);

    }

    public Api getApi() {
        return api;
    }
}

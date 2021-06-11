package ir.e.sujeyab.SabtSuje;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Akshay Raj on 05/02/18.
 * akshay@snowcorp.org
 * www.snowcorp.org
 */

public interface ApiService {
    String BASE_URL = "http://robika.ir/ultitled/practice/sujeyab/test_multi_upload/";

    @Multipart
    @POST("upload.php")
    Call<ResponseBody> uploadMultiple(
            @Part("id_ferestande") RequestBody id_ferestande,
            @Part("onvan") RequestBody onvan,
            @Part("mozo") RequestBody mozo,
            @Part("tozihat") RequestBody tozihat,
            @Part("type") RequestBody type,
            @Part("shenase_rahgiri") RequestBody shenase_rahgiri,
            @Part("id_farakhan") RequestBody id_farakhan,
            @Part("size") RequestBody size,
            @Part List<MultipartBody.Part> files);
}

package ir.e.sujeyab.Controller

import android.util.Xml
import ir.e.sujeyab.SabtSuje.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiForUpload {

    @Multipart
    @POST("Api.php?apicall=upload")
    fun uploadImage(@Part p1: MultipartBody.Part,
                    @Part p2: MultipartBody.Part,
                    @Part p3: MultipartBody.Part,
                    @Part p4: MultipartBody.Part,
                    @Part p5: MultipartBody.Part,
                    @Part p6: MultipartBody.Part,
                    @Part p7: MultipartBody.Part,
                    @Part p8: MultipartBody.Part,
                    @Part p9: MultipartBody.Part,
                    @Part p10: MultipartBody.Part,
                    @Part("desc") desc: RequestBody,
                    @Part("onvan") onvan: RequestBody,
                    @Part("mozo") mozo: RequestBody,
                    @Part("tozihat") tozihat: RequestBody,
                    @Part("id_ferestande") idFerestande: RequestBody,
                    @Part("type") type: RequestBody,
                    @Part("shenase_rahgiri") shenase_rahgiri: RequestBody,
                    @Part("link_video") linkVideo: RequestBody,
                    @Part("tozih_video") tozihVideo: RequestBody)
            : Call<UploadResponse>

    companion object {
        operator fun invoke(): ApiForUpload {
            return Retrofit.Builder()
                    .baseUrl("http://robika.ir/ultitled/practice/sujeyab/upload_test/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiForUpload::class.java)
        }
    }
}
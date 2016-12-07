package com.taptap;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface UploadService {

    @Multipart
    @POST
    Call<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Url String url
            ,@HeaderMap Map<String, String> headers);
}

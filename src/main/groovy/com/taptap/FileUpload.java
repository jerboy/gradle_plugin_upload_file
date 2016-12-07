package com.taptap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FileUpload {

    public File file;

    public String token;

    public String cookie;

    public String url;

    public String channel;

    Retrofit retrofit = null;

    public FileUpload(){
        OkHttpClient client = new OkHttpClient.Builder()
                .followRedirects(true)
                .connectTimeout(0, TimeUnit.MILLISECONDS)
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();
        retrofit = new Retrofit.Builder()
               .baseUrl("http://team.shafa.com/")
                .client(client)
               .build();
    }

    public void upload(){
        UploadService uploadService = retrofit.create(UploadService.class);
        HashMap<String, RequestBody> params = new HashMap<>();

        params.put("_token", RequestBody.create(MediaType.parse("text/plain"), token));
        params.put("channel", RequestBody.create(MediaType.parse("text/plain"), channel));
        params.put("file\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("application/zip"), file));
        params.put("notify", RequestBody.create(MediaType.parse("text/plain"), "1"));

        Map<String, String> header = new HashMap<>();
        header.put("Referer", url);
        header.put("Cookie", cookie);

        Call<ResponseBody> upload = uploadService.upload(params, url, header);
        Response<ResponseBody> execute = null;
        try {
            execute = upload.execute();
            if (execute.code() >= 200 && execute.code() <= 400) {
                // success
                System.out.println("DDDDDDDDDDDDsuccess");
            } else {
                // fail
                System.out.println("DDDDDDDDDDDDfailed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}

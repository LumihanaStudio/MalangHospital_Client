package kr.edcan.hospital.utils;

import java.util.List;

import kr.edcan.hospital.data.Advice;
import kr.edcan.hospital.data.Hospital;
import kr.edcan.hospital.data.Session;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Marzin-Oh on 2015-10-09.
 */
public interface NetworkService {
    @FormUrlEncoded
    @POST("/user/signup")
    void signUp(@Field("id") String id, @Field("pw") String pwd, @Field("confirm") String confirm, Callback<Session> callback);

    @FormUrlEncoded
    @POST("/user/login")
    void userLogin(@Field("id") String id, @Field("pw") String password, Callback<Session> callback);

    @FormUrlEncoded
    @POST("/hospital/search")
    void searchHospital(@Field("search") String search, Callback<List<Hospital>> callback);

    @GET("/service/advice")
    void getAdvice(Callback<Advice> callback);

    @GET("/hospital/all")
    void allHospital(Callback<List<Hospital>> callback);

//    @FormUrlEncoded
//    @POST("/user/logout")
//    void userLogout(@Field());


}

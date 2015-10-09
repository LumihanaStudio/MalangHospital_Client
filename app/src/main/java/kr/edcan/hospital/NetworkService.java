package kr.edcan.hospital;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
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

//    @FormUrlEncoded
//    @POST("/user/logout")
//    void userLogout(@Field());


}

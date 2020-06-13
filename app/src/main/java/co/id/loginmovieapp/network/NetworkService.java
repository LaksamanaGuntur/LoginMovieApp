package co.id.loginmovieapp.network;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public interface NetworkService {
    @GET("movie/{movieType}")
    Flowable<ApiResponse> getData(
            @Path("movieType") String movieType,
            @Query("api_key") String api_key
    );
}

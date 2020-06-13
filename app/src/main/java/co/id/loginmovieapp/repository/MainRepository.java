package co.id.loginmovieapp.repository;

import co.id.loginmovieapp.helper.Constant;
import co.id.loginmovieapp.network.ApiResponse;
import co.id.loginmovieapp.network.NetworkService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class MainRepository extends BaseRepository {
    public MainRepository(NetworkService networkService) {
        super(networkService);
    }

    /**
     * Get Data
     * @Param Sorting Type
     * */
    public Flowable<ApiResponse> getData(String movieType) {
        return networkService.getData(movieType, Constant.API_KEY)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

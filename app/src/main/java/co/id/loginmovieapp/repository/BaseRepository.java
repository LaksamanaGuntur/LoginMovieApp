package co.id.loginmovieapp.repository;

import co.id.loginmovieapp.network.NetworkService;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class BaseRepository {
    protected NetworkService networkService;

    public BaseRepository(NetworkService networkService) {
        this.networkService = networkService;
    }
}

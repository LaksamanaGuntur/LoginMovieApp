package co.id.loginmovieapp.model;

import co.id.loginmovieapp.data.DaoSession;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class BaseModel {
    protected final DaoSession mDaoSession;

    public BaseModel(DaoSession daoSession) {
        mDaoSession = daoSession;
    }
}

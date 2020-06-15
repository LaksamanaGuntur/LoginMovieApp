package co.id.loginmovieapp.model;

import java.util.List;

import co.id.loginmovieapp.data.DaoSession;
import co.id.loginmovieapp.data.FavoriteData;
import co.id.loginmovieapp.data.FavoriteDataDao;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.data.ResultDataDao;
import co.id.loginmovieapp.data.ResultDataLogin;
import co.id.loginmovieapp.data.ResultDataLoginDao;
import co.id.loginmovieapp.data.ResultDataRegister;
import co.id.loginmovieapp.data.ResultDataRegisterDao;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataDao mResultDataDao;
    private FavoriteDataDao mFavoriteDao;
    private ResultDataLoginDao mResultDataLoginDao;
    private ResultDataRegisterDao mResultDataRegisterDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
        mFavoriteDao = daoSession.getFavoriteDataDao();
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
        mResultDataRegisterDao = daoSession.getResultDataRegisterDao();
    }

    public void insertData(ResultData resultData){
        mResultDataDao.insertOrReplace(resultData);
    }

    public List<ResultData> getAllData(){
        return mResultDataDao.loadAll();
    }

    public void deleteDataList() {
        mResultDataDao.deleteAll();
    }

    public void insertFavoriteData(FavoriteData favoriteData){
        mFavoriteDao.insertOrReplace(favoriteData);
    }

    public List<FavoriteData> getAllFavoriteData(){
        return mFavoriteDao.loadAll();
    }

    public void deleteFavoriteData(FavoriteData favoriteData) {
//        mFavoriteDao.delete(favoriteData);
        mFavoriteDao.deleteByKey(favoriteData.getIdLocal());
    }

    public void insertDataLogin(ResultDataLogin resultDataLogin){
        mResultDataLoginDao.insertOrReplace(resultDataLogin);
    }

    public List<ResultDataLogin> getAllDataLogin(){
        return mResultDataLoginDao.loadAll();
    }

    public void deleteDataListLogin() {
        mResultDataLoginDao.deleteAll();
    }

    public void insertDataRegister(ResultDataRegister resultDataRegister){
        mResultDataRegisterDao.insertOrReplace(resultDataRegister);
    }

    public List<ResultDataRegister> getAllDataRegister(){
        return mResultDataRegisterDao.loadAll();
    }

    public void deleteDataListRegister() {
        mResultDataRegisterDao.deleteAll();
    }
}

package co.id.loginmovieapp.model;

import java.util.List;

import co.id.loginmovieapp.data.DaoSession;
import co.id.loginmovieapp.data.ResultData;
import co.id.loginmovieapp.data.ResultDataDao;
import co.id.loginmovieapp.data.ResultDataLogin;
import co.id.loginmovieapp.data.ResultDataLoginDao;

/**
 * Created by Laksamana Guntur Dzulfikar on 19/2/18.
 * Android Developer
 */

public class DataModel extends BaseModel {
    private ResultDataDao mResultDataDao;
    private ResultDataLoginDao mResultDataLoginDao;

    public DataModel(DaoSession daoSession) {
        super(daoSession);
        mResultDataDao = daoSession.getResultDataDao();
        mResultDataLoginDao = daoSession.getResultDataLoginDao();
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

    public void insertDataLogin(ResultDataLogin resultDataLogin){
        mResultDataLoginDao.insertOrReplace(resultDataLogin);
    }

    public List<ResultDataLogin> getAllDataLogin(){
        return mResultDataLoginDao.loadAll();
    }

    public void deleteDataListLogin() {
        mResultDataLoginDao.deleteAll();
    }
}
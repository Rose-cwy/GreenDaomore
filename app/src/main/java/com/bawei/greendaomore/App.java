package com.bawei.greendaomore;

import android.app.Application;

import com.bawei.greendaomore.gen.DaoMaster;
import com.bawei.greendaomore.gen.DaoSession;
import com.bawei.greendaomore.gen.UserDao;

/**
 * Created by c on 2017/11/22.
 */

public class App extends Application {
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}

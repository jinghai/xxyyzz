package com.ipet.server.service;

import com.ipet.server.domain.entity.App;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ipet.server.repository.AppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 应用服务类
 *
 * @author luocanfeng
 */
@Component
@Transactional(readOnly = true)
public class AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private AppDao AppDao;

    @Transactional(readOnly = false)
    public void updateApp(String appKey, Integer versionCode, String versionName, String downloadUrl) {
        App app = getAppDao().findByAppKey(appKey);
        if (app == null) {
            app = new App();
            app.setAppKey(appKey);
            //throw new RuntimeException("无效AppKey");
        }
        app.setVersionCode(versionCode);
        app.setVersionName(versionName);
        app.setAppDownloadUrl(downloadUrl);
        getAppDao().save(app);
    }

    public App checkAppUpdate(String appKey) {
        App app = getAppDao().findByAppKey(appKey);
        if (app == null) {
            throw new RuntimeException("无效AppKey");
        }
        return app;
    }

    /**
     * @return the AppDao
     */
    public AppDao getAppDao() {
        return AppDao;
    }

    /**
     * @param AppDao the AppDao to set
     */
    public void setAppDao(AppDao AppDao) {
        this.AppDao = AppDao;
    }

}

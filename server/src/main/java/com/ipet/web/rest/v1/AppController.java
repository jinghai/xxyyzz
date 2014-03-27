package com.ipet.web.rest.v1;

import com.ipet.server.domain.entity.App;
import com.ipet.server.service.AppService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 赞
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    /**
     * 更新软件版本
     *
     * @param appKey
     * @param versionCode
     * @param versionName
     * @param downloadUrl
     * @throws IOException
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void create(String appKey, String versionCode, String versionName, String downloadUrl) throws IOException {
        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(versionName)
                || StringUtils.isEmpty(versionCode) || StringUtils.isEmpty(downloadUrl)) {
            throw new RuntimeException("非法参数");
        }
        Integer vc = Integer.valueOf(versionCode);
        appService.updateApp(appKey, vc, versionName, downloadUrl);
    }

    @RequestMapping(value = "checkUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public App check(String appKey) throws IOException {
        if (StringUtils.isEmpty(appKey)) {
            throw new RuntimeException("非法参数");
        }
        return appService.checkAppUpdate(appKey);
    }
}

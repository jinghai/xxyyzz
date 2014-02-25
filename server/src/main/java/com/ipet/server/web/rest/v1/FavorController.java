package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.entity.Favor;
import com.ipet.server.service.FavorService;
import java.io.IOException;
import java.util.List;
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
 * 攒
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/favor")
public class FavorController {

    private static final Logger logger = LoggerFactory.getLogger(FavorController.class);

    @Autowired
    private FavorService favorService;

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Favor create(String uid, String photoId, String text) throws IOException {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(photoId)) {
            throw new RuntimeException("非法参数");
        }
        return favorService.favor(photoId, uid, text);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Favor> getList(String photoId) throws IOException {
        if (StringUtils.isEmpty(photoId)) {
            throw new RuntimeException("非法参数");
        }
        return favorService.getFavorList(photoId);
    }

}

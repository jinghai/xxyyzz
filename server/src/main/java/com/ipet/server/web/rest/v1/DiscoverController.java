package com.ipet.server.web.rest.v1;

import com.ipet.server.domain.entity.Photo;
import com.ipet.server.service.DiscoverService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * 人脉rest
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/discover")
public class DiscoverController {

    private static final Logger logger = LoggerFactory.getLogger(DiscoverController.class);

    @Autowired
    private DiscoverService discoverService;

    @RequestMapping(value = "listPage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Photo> getFollowForPage(String date, String pageNumber, String pageSize) throws IOException, ParseException {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(pageNumber) || StringUtils.isEmpty(pageSize)) {
            throw new RuntimeException("非法参数");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = format.parse(date);
        Integer page = Integer.valueOf(pageNumber);
        Integer size = Integer.valueOf(pageSize);
        return discoverService.getNewerPhotoForPage(datetime, page, size);
    }

}

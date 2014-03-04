package com.ipet.web.rest.v1;

import com.ipet.server.domain.entity.Photo;
import com.ipet.server.service.PhotoService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 人脉rest
 *
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/photo")
public class PhotoController {

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @Autowired
    private PhotoService photoService;

    /**
     * 发布图片
     *
     * @param uid
     * @param context 非必填
     * @param file
     * @return Photo
     * @throws java.io.IOException
     */
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Photo publish(String uid, @RequestParam(required = false) String context, MultipartFile file) throws IOException {
        if (StringUtils.isEmpty(uid)) {
            throw new RuntimeException("非法参数");
        }
        //String text = new String(context.getBytes("GBK"), "UTF-8");
        return photoService.publishPhoto(uid, context, file);
    }

    @RequestMapping(value = "listFollow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Photo> getFollowForPage(String uid, String date, String pageNumber, String pageSize) throws IOException, ParseException {
        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(date)
                || StringUtils.isEmpty(pageNumber) || StringUtils.isEmpty(pageSize)) {
            throw new RuntimeException("非法参数");
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = format.parse(date);
        Integer page = Integer.valueOf(pageNumber);
        Integer size = Integer.valueOf(pageSize);
        return photoService.getPhotosByTimeAndFollowForPage(uid, datetime, page, size);
    }

}

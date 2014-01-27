/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

import com.ipet.client.api.domain.IpetPhoto;
import java.io.File;
import java.util.List;
import org.springframework.core.io.FileSystemResource;

/**
 * 用户关系API
 *
 * @author xiaojinghai
 */
public interface PhotoApi {

    /**
     * 发布图片
     *
     * @param text
     * @param file
     * @return
     */
    public IpetPhoto publish(String text, FileSystemResource file);

    public IpetPhoto publish(String text, File file);

    public IpetPhoto publish(String text, String file);

    /**
     * 按时间线分页获取我关注的图片
     *
     * @param date
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public List<IpetPhoto> listFollowd(String date, String pageNumber, String pageSize);
}

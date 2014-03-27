package com.ipet.android.sdk;

import java.io.File;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

import com.ipet.android.sdk.domain.IpetPhoto;

/**
 * 用户关系API
 * 
 * @author xiaojinghai
 */
public interface PhotoApi {

	/**
	 * 发布图片
	 */
	public IpetPhoto publish(String text, FileSystemResource file);

	public IpetPhoto publish(String text, File file);

	public IpetPhoto publish(String text, String file);

	/**
	 * 按时间线分页获取我关注的图片
	 */
	public List<IpetPhoto> listFollowd(String date, String pageNumber, String pageSize);

}

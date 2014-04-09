package com.ipet.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipet.server.domain.entity.Favor;
import com.ipet.server.domain.entity.Photo;
import com.ipet.server.repository.FavorDao;
import com.ipet.server.repository.PhotoDao;
import com.ipet.server.util.ProjectUtil;

/**
 * 图片发布.
 * 
 * @author xiaojinghai
 */
@Service
@Transactional(readOnly = true)
public class DiscoverService extends BaseService {

	@Resource
	private PhotoDao photoDao;

	@Resource
	private FavorDao favorDao;

	/**
	 * 分页获取时间点之后最新的图片（发现）
	 */
	public List<Photo> listNewerPhotosForPage(String uid, Date date, Integer pageNumber, Integer pageSize) {
		PageRequest pageR = ProjectUtil.buildPageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createAt");
		Page<Photo> result = getPhotoDao().findByCreateAtBefore(date, pageR);

		// 填充favored（我是否赞过）信息
		List<Photo> photos = result.getContent();
		Map<String, Photo> photoIdMap = new HashMap<String, Photo>(photos.size());
		for (Photo photo : photos) {
			photoIdMap.put(photo.getId(), photo);
		}

		List<Favor> favors = this.getFavorDao().findByPhotoIdInAndUserId(photoIdMap.keySet(), uid);
		List<String> favoredPhotoIds = new ArrayList<String>(favors.size());
		for (Favor favor : favors) {
			favoredPhotoIds.add(favor.getPhotoId());
		}

		for (Map.Entry<String, Photo> photoItem : photoIdMap.entrySet()) {
			if (favoredPhotoIds.contains(photoItem.getKey())) {
				photoItem.getValue().setFavored(true);
			}
		}

		favors.clear();
		favoredPhotoIds.clear();
		photoIdMap.clear();

		return photos;
	}

	public PhotoDao getPhotoDao() {
		return photoDao;
	}

	public void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	public FavorDao getFavorDao() {
		return favorDao;
	}

	public void setFavorDao(FavorDao favorDao) {
		this.favorDao = favorDao;
	}

}

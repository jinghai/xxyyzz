package com.ipet.server.service;

import com.ipet.server.app.AppConfig;
import com.ipet.server.domain.UserState;
import com.ipet.server.domain.entity.FollowRelation;
import com.ipet.server.domain.entity.Photo;
import com.ipet.server.domain.entity.User;
import com.ipet.server.repository.FollowRelationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ipet.server.repository.PhotoDao;
import com.ipet.server.repository.UserDao;
import com.ipet.server.util.ProjectUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片发布.
 *
 * @author xiaojinghai
 */
@Component
@Transactional(readOnly = true)
public class PhotoService {

    private static final Logger logger = LoggerFactory.getLogger(PhotoService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private FollowRelationDao followRelationDao;

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private AppConfig appConfig;

    private final int smallWith = 480;
    private final int smallHeigth = 480;

    private final int originalWith = 800;
    private final int originalHeigth = 600;

    //允许上传的头像文件扩展名
    private final List<String> allowPrefix = new ArrayList<String>() {
        {
            add("jpg");
            add("gif");
            add("png");
        }
    };

    /**
     * 按时间和关注分页获取图片
     *
     * @param uid
     * @param data
     * @param index
     * @return
     */
    public List<Photo> getPhotosByTimeAndFollowForPage(String uid, Date date, Integer pageNumber, Integer pageSize) {

        PageRequest pageR = ProjectUtil.buildPageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createAt");

        List<FollowRelation> idsR = getFollowRelationDao().findByUserIdA(uid);
        List<String> followIds = new ArrayList<String>();
        followIds.add(uid);
        for (FollowRelation r : idsR) {
            followIds.add(r.getUserIdB());
        }

        Page<Photo> ret = getPhotoDao().findByCreateAtBeforeAndUserIdIn(date, followIds, pageR);
        return ret.getContent();
    }

    /**
     * 发布图片
     *
     * @param uid
     * @param context
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(readOnly = false)
    public Photo publishPhoto(String uid, String context, MultipartFile file) throws IOException {
        User user = this.getUserDao().findByIdAndUserState(uid, UserState.ENABLE);
        if (user == null) {
            throw new RuntimeException("无效用户");
        }
        checkFilePrefix(file);
        //临时文件
        File tempFile = new File(appConfig.getTempDirRealPath() + generateTempFileName(file));
        String originalRelativeFile = getPhotoRelativePath(generatePhotoFileName(uid, file));
        String originalRealFile = getPhotoRealPath(originalRelativeFile);
        String smallRelativeFile = getPhotoRelativePath(generatePhotoFileName(uid, file));
        String smallRealFile = getPhotoRealPath(smallRelativeFile);
        File originalFile = new File(originalRealFile);
        File smallFile = new File(smallRealFile);
        //文件冲突检查,避免shorUUID可能存在重复的bug
        if (originalFile.exists() || smallFile.exists()) {
            throw new RuntimeException("文件重复");
        }
        try {
            //上传至临时文件
            file.transferTo(tempFile);
            /*
             * 若图片横比with小，高比heigth小，不变
             * 若图片横比with小，高比heigth大，高缩小到heigth，图片比例不变
             * 若图片横比with大，高比heigth小，横缩小到with，图片比例不变
             * 若图片横比with大，高比heigth大，图片按比例缩小，横为with或高为heigth
             */
            Thumbnails.of(tempFile).size(originalWith, originalHeigth).toFile(originalRealFile);
            Thumbnails.of(tempFile).size(smallWith, smallHeigth).toFile(smallRealFile);

            Photo photo = new Photo();
            photo.setText(context);
            photo.setOriginalURL(originalRelativeFile);
            photo.setSmallURL(smallRelativeFile);
            photo.setUserId(uid);
            user.setPhotoCount(user.getPhotoCount() + 1);
            //更新记录
            this.getPhotoDao().save(photo);
            this.getUserDao().save(user);
            return photo;

        } catch (Exception e) {
            if (originalFile != null && originalFile.exists()) {
                originalFile.delete();
            }
            if (smallFile != null && smallFile.exists()) {
                smallFile.delete();
            }
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }

        }
    }

    //生成临时文件相对路径
    private String generateTempFileName(MultipartFile file) {
        String prefix = ProjectUtil.getPrefix(file.getOriginalFilename());
        return ProjectUtil.generateShortUUID() + "." + prefix;
    }

    //检查文件扩展名合法性
    private void checkFilePrefix(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String prefix = ProjectUtil.getPrefix(originalFilename);
        if (StringUtils.isEmpty(prefix) || !allowPrefix.contains(prefix)) {
            throw new RuntimeException("被禁止的文件类型");
        }
    }

    //得到相对路径
    private String getPhotoRelativePath(String avatarFileName) {
        String path = appConfig.getUploadDir() + avatarFileName;
        return path;
    }

    //生成相对路径
    private String generatePhotoFileName(String uid, MultipartFile file) {
        String prefix = ProjectUtil.getPrefix(file.getOriginalFilename());
        return uid + "/" + ProjectUtil.generateShortUUID() + "." + prefix;
    }

    //得到绝对地址
    private String getPhotoRealPath(String relativePath) {
        String path = appConfig.getWebContextRealPath() + relativePath;
        ProjectUtil.checkDirAndCreateIfNotExists(path);
        return path;
    }

    /**
     * @return the photoDao
     */
    public PhotoDao getPhotoDao() {
        return photoDao;
    }

    /**
     * @param photoDao the photoDao to set
     */
    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    /**
     * @return the userDao
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * @param userDao the userDao to set
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @return the followRelationDao
     */
    public FollowRelationDao getFollowRelationDao() {
        return followRelationDao;
    }

    /**
     * @param followRelationDao the followRelationDao to set
     */
    public void setFollowRelationDao(FollowRelationDao followRelationDao) {
        this.followRelationDao = followRelationDao;
    }
}

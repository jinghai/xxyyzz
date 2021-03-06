package com.ipet.android.sdk;

import android.content.Context;
import com.ipet.android.sdk.core.APIException;
import com.ipet.android.sdk.core.ApiBase;
import com.ipet.android.sdk.domain.IpetUser;
import com.ipet.android.sdk.domain.IpetUserUpdate;
import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;

/**
 *
 * @author xiaojinghai
 */
public class UserApi extends ApiBase {

    private IpetUser user;

    public UserApi(Context context) {
        super(context);
    }

    public synchronized IpetUser getUser(String userId) {
        final CountDownLatch latch = new CountDownLatch(1);
        final URI uri = buildUri("user/" + userId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                user = getRestTemplate().getForObject(uri, IpetUser.class);
                latch.countDown();
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException ex) {
            throw new APIException(ex);
        }
        return user;
    }

    public List<IpetUser> getUsers(String ids) {
        URI uri = buildUri("user/listByIds", "ids", ids);
        IpetUser[] users = getRestTemplate().getForObject(uri, IpetUser[].class);
        List<IpetUser> list = Arrays.asList(users);
        return list;
    }

    public IpetUser updateUserInfo(IpetUserUpdate update) {
        requireAuthorization();
        update.setId(getCurrUserId());
        URI uri = buildUri("user/updateInfo");
        IpetUser ret = getRestTemplate().postForObject(uri, update, IpetUser.class);
        return ret;
    }

    public IpetUser updateAvatar(FileSystemResource avatarFile) {
        requireAuthorization();
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("userId", getCurrUserId());
        body.add("file", avatarFile);

        URI uri = buildUri("user/uploadAvatar");

        IpetUser ret = getRestTemplate().postForObject(uri, body, IpetUser.class);
        return ret;
    }

    public IpetUser updateAvatar(File avatarFile) {
        FileSystemResource fsr = new FileSystemResource(avatarFile);
        IpetUser ret = updateAvatar(fsr);
        return ret;
    }

    public IpetUser updateAvatar(String avatarFilePath) {
        File f = new File(avatarFilePath);
        IpetUser ret = updateAvatar(f);
        return ret;
    }

}

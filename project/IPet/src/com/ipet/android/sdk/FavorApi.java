/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.android.sdk;

import com.ipet.android.sdk.domain.IpetFavor;
import java.util.List;

/**
 * 攒API
 *
 * @author xiaojinghai
 */
public interface FavorApi {

    public IpetFavor favor(String photoId, String text);

    public List<IpetFavor> list(String photoId);
}

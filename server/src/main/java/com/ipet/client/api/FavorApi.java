/*
 * To change this template, choose Tools | Templates,
 * and open the template in the editor.
 */
package com.ipet.client.api;

import com.ipet.client.api.domain.IpetFavor;
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

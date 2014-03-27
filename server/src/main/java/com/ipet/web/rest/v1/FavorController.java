package com.ipet.web.rest.v1;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipet.server.domain.entity.Favor;
import com.ipet.server.service.FavorService;

/**
 * 赞
 * 
 * @author xiaojinghai
 */
@Controller
@RequestMapping(value = "/v1/favor")
public class FavorController extends BaseController {

	@Resource
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
		return favorService.listFavors(photoId);
	}

}

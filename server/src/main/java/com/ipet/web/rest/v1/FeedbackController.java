package com.ipet.web.rest.v1;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipet.server.domain.entity.Feedback;
import com.ipet.server.service.FeedbackService;

/**
 * 意见反馈Controller
 * 
 * @author luocanfeng
 */
@Controller
@RequestMapping(value = "/v1/feedback")
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "feedback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean feedback(String title, String content, String contact) throws IOException {
		if (StringUtils.isEmpty(content)) {
			throw new RuntimeException("非法参数");
		}
		Feedback feedback = new Feedback();
		feedback.setTitle(title);
		feedback.setContent(content);
		feedback.setContact(contact);
		return feedbackService.feedback(feedback);
	}

}

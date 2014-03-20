package com.ipet.server.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipet.server.domain.entity.Feedback;
import com.ipet.server.repository.FeedbackDao;

/**
 * 意见反馈服务类
 * 
 * @author luocanfeng
 */
@Service("feedbackService")
@Transactional
public class FeedbackService {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

	@Resource
	private FeedbackDao feedbackDao;

	public boolean feedback(Feedback feedback) {
		getFeedbackDao().save(feedback);
		return true;
	}

	public FeedbackDao getFeedbackDao() {
		return feedbackDao;
	}

	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

}

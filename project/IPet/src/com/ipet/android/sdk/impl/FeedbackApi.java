package com.ipet.android.sdk.impl;

import android.content.Context;
import com.ipet.android.sdk.base.ApiBase;
import com.ipet.android.sdk.domain.IpetFeedback;
import java.net.URI;
import org.springframework.util.LinkedMultiValueMap;

public class FeedbackApi extends ApiBase {

    public FeedbackApi(Context context) {
        super(context);
    }

    private boolean feedback(IpetFeedback feedback) {
        URI uri = buildUri("feedback/feedback");
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
        body.add("title", feedback.getTitle());
        body.add("content", feedback.getContent());
        body.add("contact", feedback.getContact());
        body.add("userId", getCurrUserId());
        Boolean result = getRestTemplate().postForObject(uri, body, Boolean.class);
        return result;
    }

    public boolean feedback(String title, String content, String contact) {
        IpetFeedback feedback = new IpetFeedback();
        feedback.setTitle(title);
        feedback.setContent(content);
        feedback.setContact(contact);
        return feedback(feedback);
    }

}

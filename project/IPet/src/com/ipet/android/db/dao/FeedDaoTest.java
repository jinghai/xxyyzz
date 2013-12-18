package com.ipet.android.db.dao;

import java.util.Iterator;
import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;
import com.ipet.android.db.entity.Feed;

public class FeedDaoTest extends AndroidTestCase {

    private static final String TAG = "FeedDaoTest";
    private FeedDao dao = null;
    private Feed feed1 = null;
    private Feed feed2 = null;

    /**
     *
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.dao = new FeedDao(getContext());

        //init test data
        feed1 = new Feed("content", "zhangzuliang",
                "2013-12-12 12:12:12", "Shanghai", "/img/zhangzuliang20131212121212.gif");

        feed2 = new Feed("content2", "zhangzuliang",
                "2013-12-12 12:12:13", "Suzhou", "/img/zhangzuliang20131212121213.gif");

        Log.d(TAG, "start unit test feeDao");

    }

    /**
     *
     * @throws Exception
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSave() {

        this.dao.save(feed1);
        this.dao.save(feed2);
        Log.d(TAG, "save feeds: feed1,feed2");
    }

    public void testGetCount() {

        Log.d(TAG, "feeds count =" + this.dao.getCount());
    }

    public void testGetAll() {

        List<Feed> feeds = this.dao.getScrollData(0, 10000);
        int count = this.dao.getCount();
        AndroidTestCase.assertEquals(feeds.size(), count);

        Log.d(TAG, "return total feeds =" + feeds.size());

        Iterator<Feed> it = feeds.iterator();
        while (it.hasNext()) {
            Log.d(TAG, "feed=" + it.next().toString());
        }

    }

    public void testRemoveById() {
        List<Feed> feeds = this.dao.getScrollData(0, 10000);
        int count = this.dao.getCount();
        AndroidTestCase.assertEquals(feeds.size(), count);

        Log.d(TAG, "return total feeds =" + feeds.size());

        Iterator<Feed> it = feeds.iterator();
        while (it.hasNext()) {
            Feed feed = it.next();
            Log.d(TAG, "feed=" + feed.toString());

            // test getById method
            Feed f = this.dao.getById(feed.id);
            AndroidTestCase.assertEquals(feed.content, f.content);

            // test removeById
            this.dao.removeByIds(feed.id);
            Log.d("cats-feed", "start to remove feed.id=" + feed.id);
        }

        int zero = this.dao.getCount();
        AndroidTestCase.assertEquals(zero, 0);

    }

}

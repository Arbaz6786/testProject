package com.example.azurezoomi.testproject;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import android.content.Context;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by AzureZoomi on 5/13/2016.
 */

public class MyTest {

    protected WebService wsInterface;

    Context context;

    String versionCode;

    String query;

    @Before
    public void setup() {
        versionCode = "1.0";
        query = "Google Official Blogs";
        context = RuntimeEnvironment.application;

        wsInterface = RestApiBuilder.buildRetrofitService();

    }


    @Test
    public void testURL() throws Exception {

        Observable<Blog> blogObservable = wsInterface.findFeeds(versionCode, query);



        TestSubscriber<Blog> testSubscriber = new TestSubscriber<>();
        blogObservable.subscribe(testSubscriber);

        List<Throwable> onErrorEvents = testSubscriber.getOnErrorEvents();

        assertTrue(onErrorEvents.size() == 0);

        List<Blog> onNextEvents = testSubscriber.getOnNextEvents();

        assertNotNull(onNextEvents);

        assertTrue(onNextEvents.size() > 0);

        assertTrue(onNextEvents.get(0).getResponseStatus() == 200);

        assertTrue(onNextEvents.get(0).getResponseData().getEntries().size() > 10);

       


    }

}

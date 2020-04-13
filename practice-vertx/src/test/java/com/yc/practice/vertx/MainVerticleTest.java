package com.yc.practice.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.http.impl.HttpClientRequestImpl;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.net.impl.SocketAddressImpl;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class MainVerticleTest {

    private Vertx vertx;

    @Before
    public void setUp(TestContext tc) {
        vertx = Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName(), tc.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext tc) {
        vertx.close(tc.asyncAssertSuccess());
    }

    @Test
    public void testThatTheServerIsStarted(TestContext tc) {
        Async async = tc.async();
        HttpClient client = vertx.createHttpClient();
        client.request(HttpMethod.GET, 8080, "localhost", "/",response->{
            tc.assertEquals(response.statusCode(), 200);
            response.bodyHandler(body -> {
                System.out.println(body);
                tc.assertTrue(body.length() > 0);
                async.complete();
            });
        });
//        Async async = tc.async();
//        vertx.createHttpClient().getNow(8080, "localhost", "/", response -> {
//            tc.assertEquals(response.statusCode(), 200);
//            response.bodyHandler(body -> {
//                System.out.println(body);
//                tc.assertTrue(body.length() > 0);
//                async.complete();
//            });
//        });
    }
}

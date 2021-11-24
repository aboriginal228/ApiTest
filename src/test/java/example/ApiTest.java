package example;

import example.models.Post;
import example.models.User;
import example.utils.ApiAppRequest;
import example.utils.ApiUtils;
import example.utils.ConfigUtil;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.UUID;

public class ApiTest {

    private static final String DEFAULT_LINK = ConfigUtil.getDefaultUrl();

    @Parameters({ "link", "code" })
    @Test
    void getTestSortingCheck(String link, int code) {

        List<Post> posts = ApiAppRequest.getPosts(DEFAULT_LINK + link);
        Assert.assertEquals(code, ApiAppRequest.getResponseCode(), "Wrong response code");

        int startValue = 0;
        boolean sorted = true;
        for (Post p : posts) {
            if (p.getId() < startValue) {
                sorted = false;
                break;
            }
            else {
                startValue = p.getId();
            }
        }

        Assert.assertTrue(sorted, "Not sorted");
    }

    @Parameters({
            "link",
            "code",
            "expUserId",
            "expId"
    })
    @Test
    void getPost(String link, int code, int expUserId, int expId) {

        Post post = ApiAppRequest.getPost(DEFAULT_LINK + link);
        Assert.assertEquals(code, ApiAppRequest.getResponseCode(), "Wrong response code");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(post.getUserId(), expUserId, "UserId is not like expected");
        softAssert.assertEquals(post.getId(), expId, "Id is not like expected");
        softAssert.assertFalse(post.getBody().isEmpty(), "Body is not empty");
        softAssert.assertFalse(post.getTitle().isEmpty(), "Title is not empty");
        softAssert.assertAll();
    }

    @Parameters({ "postNumber", "code" })
    @Test
    void getEmpty(String postNumber, int code) {
        ApiUtils.getFromUrl(DEFAULT_LINK + "posts/" + postNumber);
        Assert.assertEquals(ApiAppRequest.getResponseCode(), code);
        Assert.assertTrue(ApiUtils.isEmpty);
    }

    @Parameters({ "link", "code", "userIdContains" })
    @Test
    void getPostResponse(String link, int code, String userIdContains) {

        String randomTitle = UUID.randomUUID().toString();
        String randomBody = UUID.randomUUID().toString();

        Post postResult = ApiAppRequest.getCreatedPost(DEFAULT_LINK + link, randomBody, randomTitle, userIdContains);
        Assert.assertEquals(ApiAppRequest.getResponseCode(), code, "Wrong response code");

        Assert.assertEquals(postResult.getBody(), randomBody, "Body is not like expected");
        Assert.assertEquals(postResult.getTitle(), randomTitle, "Title is not like expected");
        Assert.assertTrue(String.valueOf(postResult.getId()).contains(userIdContains), "Id doesn't contains expected part");
    }

    @Parameters({ "link", "code", "userId" })
    @Test
    void getUsers(String link, int code, int userId) {

        List<User> users = ApiAppRequest.getUsers(DEFAULT_LINK + link);
        Assert.assertEquals(ApiAppRequest.getResponseCode(), code, "Wrong response code");

        for (User user : users) {
            if (user.getId() == userId) {
                Assert.assertEquals(user, ApiAppRequest.getUserFromString(ConfigUtil.getExpectedJson()), "User from response not equal to expected");
                break;
            }
        }
    }

    @Parameters({ "link", "code" })
    @Test
    void getUser(String link, int code) {

        User userResult = ApiAppRequest.getUser(DEFAULT_LINK + link);
        Assert.assertEquals(ApiAppRequest.getResponseCode(), code, "Wrong response code");

        User userExp = ApiAppRequest.getUserFromString(ConfigUtil.getExpectedJson());

        Assert.assertEquals(userResult, userExp, "User from response not equal to expected");
    }
}

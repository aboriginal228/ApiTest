package example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.models.Post;
import example.models.User;

import java.util.List;

public class ApiAppRequest {

    public static List<Post> getPosts(String link) {
        ApiUtils.getFromUrl(link);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ApiUtils.getResponseMessage(), new TypeReference<List<Post>>() {});
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Post getPost(String link) {
        ApiUtils.getFromUrl(link);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ApiUtils.getResponseMessage(), Post.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Post getCreatedPost(String link, String body, String title, String id) {
        ApiUtils.postToUrl(link, body, title, id);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ApiUtils.getResponseMessage(), Post.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static List<User> getUsers(String link) {
        ApiUtils.getFromUrl(link);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ApiUtils.getResponseMessage(), new TypeReference<List<User>>() {});
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static User getUser(String link) {
        ApiUtils.getFromUrl(link);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(ApiUtils.getResponseMessage(), User.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static int getResponseCode() {
        return ApiUtils.getResponseCode();
    }

    public static User getUserFromString(String input) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(input, User.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

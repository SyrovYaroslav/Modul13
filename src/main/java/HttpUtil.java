import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    //Task 1
    public void methodPost(URI uri, User user) throws InterruptedException, IOException {
        String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.body() = " + response.body());
    }

    public void methodPut(URI uri, User user) throws InterruptedException, IOException {
        String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.body() = " + response.body());
    }

    public void methodDelete(URI uri) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.body() = " + response.body());
    }

    public void methodInformAllUsers(URI uri) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.body() = " + response.body());
    }

    public void Task2(String uri) throws InterruptedException, IOException {
        String response = Jsoup.connect(uri)
                .ignoreContentType(true)
                .get()
                .body()
                .text();


        Type type = TypeToken
                .getParameterized(List.class, Comment.class)
                .getType();

        List<Comment> comment = new Gson().fromJson(response, type);



        System.out.println("comment = " + comment);

    }
}

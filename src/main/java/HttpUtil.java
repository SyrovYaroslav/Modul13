import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

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

    public void Task2(URI uri) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type", "application/json")
                .GET()
                .build();

        String response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();

        Type type = TypeToken
                .getParameterized(List.class, Post.class)
                .getType();


        List<Post> json = GSON.fromJson(response, type);

        int x = 0;
        int y = 0;

        for (Post p: json) {
            if (p.getId() > y){
                x = p.getUserId();
                y = p.getId();
            }
        }

        String commentUri = "https://jsonplaceholder.typicode.com/posts/" + y + "/comments";

        HttpRequest requestComment = HttpRequest.newBuilder()
                .uri(URI.create(commentUri))
                .header("Content-type", "application/json")
                .GET()
                .build();

        String responseComment = CLIENT.send(requestComment, HttpResponse.BodyHandlers.ofString()).body();

        try (FileWriter writer = new FileWriter(new File("user-" + x + "-post-" + y + "-comments.json")))
        {
            writer.write(responseComment);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Task3(int namberOfUser) throws IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users/" + namberOfUser + "/todos";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-type", "application/json")
                .GET()
                .build();

        String response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString()).body();

        Type type = TypeToken
                .getParameterized(List.class, Todo.class)
                .getType();


        List<Todo> json = GSON.fromJson(response, type);
        List<Todo> filterJson = json.stream().filter(item -> !item.getCompleted()).collect(Collectors.toList());

        for (Todo j: filterJson) {
            System.out.println(j);
        }
    }


}

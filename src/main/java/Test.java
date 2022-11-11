import java.io.IOException;
import java.net.URI;
import java.util.Map;


public class Test {
    public static String POST_URI = "https://jsonplaceholder.typicode.com/users";
    public static String PUT_URI = "https://jsonplaceholder.typicode.com/users/11";
    public static String GET_ALL_URI = "https://jsonplaceholder.typicode.com/users";
    public static String GET_USERID_URI = "https://jsonplaceholder.typicode.com/users?id=1";
    public static String GET_USERNAME_URI = "https://jsonplaceholder.typicode.com/users?username=Bret";
    public static String GET_POSTS_URI = "https://jsonplaceholder.typicode.com/users/1/posts";



    public static void main(String[] args) throws IOException, InterruptedException {

        User user = new User();
        user.setId(10);
        user.setName("Kolya");
        user.setUsername("Fix");
        user.setEmail("KolyaFix@oil.org");
        user.setAddress(Map.of("street", "Kulas Light",
                "suite", "Apt. 556",
                "city", "Gwenborough",
                "zipcode", "92998-3874",
                "geo", String.valueOf(Map.of(
                    "lat", "-37.3159",
                    "lng", "81.1496")))
                );
        user.setPhone("010-692-6593 x09125");
        user.setWebsite("hildegard.org");
        user.setCompany(Map.of("name", "Romaguera-Crona",
                "catchPhrase", "Multi-layered client-server neural-net",
                "bs", "harness real-time e-markets"));

        //Task 1.1
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.methodPost(URI.create(POST_URI), user);

        //Task 1.2
        user.setName("Yaroslav");
        httpUtil.methodPut(URI.create(PUT_URI), user);

        //Task 1.3
        httpUtil.methodDelete(URI.create(PUT_URI));

        //Task 4.1
        httpUtil.methodInformAllUsers(URI.create(GET_ALL_URI));

        //Task 5.1
        httpUtil.methodInformAllUsers(URI.create(GET_USERID_URI));

        //Task 1.6
        httpUtil.methodInformAllUsers(URI.create(GET_USERNAME_URI));

        //Task 2
        httpUtil.Task2(URI.create(GET_POSTS_URI));

        //Task 3
        httpUtil.Task3(1);
    }
}

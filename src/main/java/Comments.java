//Завдання 2
//        Доповніть програму методом, що буде виводити всі коментарі до останнього поста певного користувача і записувати їх у файл.
//
//        https://jsonplaceholder.typicode.com/users/1/posts Останнім вважаємо пост з найбільшим id.
//
//        https://jsonplaceholder.typicode.com/posts/10/comments
//
//        Файл повинен називатись user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Comments {

    public static void main(String[] args) throws IOException {

        sendGET_AllCommentsUserInFile(7); //1

    }

    private static void sendGET_AllCommentsUserInFile(int userId) throws IOException {

        int postId = sendGET_LastPostUser(userId);
        Comment[] comments = sendGET_AllComments(postId);

        String fileJson = new File("").getAbsolutePath();

        //user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.
        String fileName = "user-"+ userId + "-post-" + postId +"-comments.json" ;
        fileJson.concat(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {

            for (Comment comment : comments) {

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(comment);

                writer.write(json);
                writer.flush();

            }

            System.out.println(fileName + "  created");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }




    }

    private static Comment [] sendGET_AllComments(int postId) throws IOException{

        Comment[] com = new Comment[0];
        String answer = "";

        String URL_Post_Com = "https://jsonplaceholder.typicode.com/posts/" + postId + "/comments";
        URL url = new URL(URL_Post_Com);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;


            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            Gson gsonR = new GsonBuilder().setPrettyPrinting().create();
            com = gsonR.fromJson(response.toString(), Comment[].class);
            answer = response.toString();

        } else {
            System.out.println("sendGET_AllComments request not worked");
        }

        return com ;
    }

    private static int sendGET_LastPostUser(int userId) throws IOException{

        int lastPost = 0;

        String URL_Post_User = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";
        URL url = new URL(URL_Post_User);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;


            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gsonR = new GsonBuilder().setPrettyPrinting().create();
            Post[] posts = gsonR.fromJson(response.toString(), Post[].class);

            for (Post post : posts) {
                if(lastPost < post.getId()){
                    lastPost = post.getId();
                }
            }

            //System.out.println(lastPost);
        } else {
            System.out.println("sendGET_LastPostUser request not worked");
        }


        return lastPost;
    }

}


class Post {

    private int userId;
    private int id;
    private String title;
    private String body;


    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

class Comment {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;


    public Comment(int postId, int id, String name,String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

}

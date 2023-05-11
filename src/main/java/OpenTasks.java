//Завдання 3
//        Доповніть програму методом, що буде виводити всі відкриті задачі для користувача з ідентифікатором X.
//
//        https://jsonplaceholder.typicode.com/users/1/todos.
//
//        Відкритими вважаються всі задачі, у яких completed = false.

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class OpenTasks {

    public static void main(String[] args) throws IOException {

        sendGET_AllOpenTodo(3);

    }

    private static void sendGET_AllOpenTodo(int userId) throws IOException {

        String URL_Todo_User = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";
        URL url = new URL(URL_Todo_User);
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
            Todos[] todos = gsonR.fromJson(response.toString(), Todos[].class);

            for (Todos todo : todos) {
                if(!todo.getCompleted()){
                    System.out.print(todo.getId() + ": ");
                    System.out.println(todo.getTitle());
                }
            }

            //System.out.println(lastPost);
        } else {
            System.out.println("sendGET_LastPostUser request not worked");
        }

    }

}

class Todos {

    private int userId;
    private int id;
    private String title;
    private boolean completed;


    public Todos(int userId, int id, String title,boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
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

    public boolean getCompleted() {
        return completed;
    }

}

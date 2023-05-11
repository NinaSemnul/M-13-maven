//Завдання 1
//        Програма повинна містити методи для реалізації наступного функціоналу:
//
//    +    1.  створення нового об'єкта в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті.
//        Метод працює правильно, якщо у відповідь на JSON з об'єктом повернувся такий самий JSON, але зі значенням id більшим на 1,
//        ніж найбільший id на сайті.
//
//    +    2. оновлення об'єкту в https://jsonplaceholder.typicode.com/users. Можливо, ви не побачите одразу змін на сайті.
//        Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений JSON (він повинен бути таким самим, що ви відправили).
//
//    +    3. видалення об'єкта з https://jsonplaceholder.typicode.com/users. Тут будемо вважати коректним результат -
//        статус відповіді з групи 2xx (наприклад, 200).
//
//    +    4. отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users
//
//    +    5. отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id}
//
//    +    6. отримання інформації про користувача за username - https://jsonplaceholder.typicode.com/users?username={username}


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class Users {

    private static final String TEST_URL =
            "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException {
        //sendPOST_NewUser(); //1
        //sendPUT(4) ;  //2
        //sendDELETE_User(3);//3
        //sendGET_AllUsers(); //4
        //sendGET_UserId(2); //5
        //sendGET_UserName("Kamren"); //6

    }

    //1
    private static void sendPOST_NewUser() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File("user.json").toPath()));
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("sendPOST_NewUser request not worked");
        }
    }

    //2
    private static void sendPUT(int id) throws IOException {
        URL url = new URL(TEST_URL  + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File("user.json").toPath()));
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("PATCH response code: " + responseCode);

            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());

    }

    //3
    private static void sendDELETE_User(int id) throws IOException {
        URL url = new URL(TEST_URL + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        System.out.println("DELETE response code: " + responseCode);
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
            System.out.println(response.toString());
        } else {
            System.out.println("sendDELETE_User request not worked");
        }
    }

    //4
    private static void sendGET_AllUsers() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
            System.out.println(response.toString());
        } else {
            System.out.println("sendGET_AllUsers request not worked");
        }
    }

    //5
    private static void sendGET_UserId(int id) throws IOException {
        URL url = new URL(TEST_URL + "/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
            System.out.println(response.toString());
        } else {
            System.out.println("sendGET_UserId request not worked");
        }
    }

    //6
    private static void sendGET_UserName(String name) throws IOException {
        URL url = new URL(TEST_URL + "?username=" + name);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
            System.out.println(response.toString());
        } else {
            System.out.println("sendGET_UserName request not worked");
        }
    }



}

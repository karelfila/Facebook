import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

@Named
@RequestScoped
public class PostManager {

    public void addPost(String author, String content){

            try (
                    Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/facebook?user=root&password=");
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO facebook.posts (author, content, created_at, updated_at ) VALUES (?, ?, now(), now())");
            ) {
                preparedStatement.setString(1, author);
                preparedStatement.setString(2, content);

                preparedStatement.execute();
                preparedStatement.close();
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void editPost(String author, String content) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/facebook?user=root&password=");
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE  posts " +
                                "SET  author = ?, content = ?, updated_at = now() " +
                                "WHERE post_id = ?")) {
            preparedStatement.setString(1, content);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, Integer.parseInt(getIDParam()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getIDParam() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get("post_id");
    }


    public ArrayList<Post> getPost() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/facebook?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM posts")
        ) {
            ArrayList<Post> posts = new ArrayList<>();

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
            }

            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePost(int id) throws SQLException {
        try(
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost/facebook?user=root&password=");
                PreparedStatement statement = connection.prepareStatement("DELETE FROM posts WHERE post_id = ?");)
        {
            statement.setInt(1, id);
            statement.execute();
        }
    }
}

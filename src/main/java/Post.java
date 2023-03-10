import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Post {

    private int postID;
    private String content;
    private String author;
    private int likes;
    private int disLikes;
    private String createdAt;
    private String updatedAt;

    public Post() {
    }

    public Post(int postID, String content, String author, int likes, int disLikes, String createdAt, String updatedAt) {
        this.postID = postID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.disLikes = disLikes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public void setDisLikes(int disLikes) {
        this.disLikes = disLikes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

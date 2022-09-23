package ke.co.safaricom.blog.dao;

import javax.validation.constraints.NotBlank;

public class PostCreateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;
    @NotBlank
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

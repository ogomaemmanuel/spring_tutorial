package ke.co.safaricom.blog.dto;

import ke.co.safaricom.blog.entities.Comment;
import ke.co.safaricom.blog.entities.Comment_;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

public class CommentsFilter {
    private  String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Example<Comment> commentExample() {
        Comment comment = new Comment();
        if(!(this.getContent()==null)){
            comment.setBody(this.getContent());
        }

        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher(Comment_.BODY, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<Comment> commentExample = Example.of(comment,customExampleMatcher);
        return  commentExample;
    }
}

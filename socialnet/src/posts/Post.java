package posts;
import users.*;

import java.time.LocalDateTime;

public class Post
{
    private LocalDateTime ldt;
    private User user;
    private boolean isAdult;
    private String title;
    private String content;


    public Post(LocalDateTime ldt, User user, boolean isAdult, String title, String content) 
    {
        this.ldt = ldt;
        this.user = user;
        this.isAdult = isAdult;
        this.title = title;
        this.content = content;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
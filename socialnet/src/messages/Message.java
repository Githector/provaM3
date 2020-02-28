package messages;
import java.time.LocalDate;
import java.time.LocalDateTime;

import users.*;

public class Message
{
    private LocalDateTime ld;
    private User user;
    private String content;
    private User dest;

    
   
    public Message(LocalDateTime ld, User user, String content) {
        this.ld = ld;
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

    public LocalDateTime getLd() {
        return ld;
    }

    public void setLd(LocalDateTime ld) {
        this.ld = ld;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getDest() {
        return dest;
    }

    public void setDest(User dest) {
        this.dest = dest;
    }

    


    
}
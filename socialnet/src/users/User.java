package users;

import java.util.ArrayList;
import java.util.Scanner;

import posts.Post;
import users.*;

public abstract class User
{
    private String username;
    private String password;
    private String role;
    private ArrayList<String> following = new ArrayList<>();
    private ArrayList<String> privateMessage = new ArrayList<>();
    private ArrayList<String> blocked = new ArrayList<>();

    
    public User(String username, String password) 
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    public ArrayList<String> getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(ArrayList<String> privateMessage) {
        this.privateMessage = privateMessage;
    }

    public ArrayList<String> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<String> blocked) {
        this.blocked = blocked;
    }


    

    public static User canLogIn(ArrayList<User> users, String username, String password)
    {
        

        for(User u : users)
        {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
            {
                System.out.println("Welcome "+u.getUsername()+"!");
                return u;
                
            }
        }
        return null;
    }

    
    public void printMenuWhenLogged()
    {
        System.out.println("");
    }

    public static void printEveryPost(ArrayList<Post> posts, User user)
    {
        Scanner scan = new Scanner(System.in);
        for(Post p : posts)
        {
            System.out.println("******************");
            System.out.println("Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear());
            System.out.println("Autor: "+p.getUser().getUsername());
            System.out.println("NSFW: "+p.isAdult());
            System.out.println("Title: "+p.getTitle());
            System.out.println("About: "+p.getContent());
            scan.nextLine();
        }
    }

    public void deletePost(int id, ArrayList<Post> posts, User currentUser)
    {
        for(Post p : posts)
        {
            if(posts.indexOf(p) == id-1)
            {
                posts.remove(p);
                break;
            }
        }
    }

    public void printPostsToDelete(ArrayList<Post> posts, User currentUser)
    {
        int i = 1;
        for(Post p : posts)
        {
            
            System.out.println(i+"."+" Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear()+" - "+p.getLdt().getHour()+":"+p.getLdt().getMinute()+" - "+ "Autor: "+p.getUser().getUsername()+" Title: "+p.getTitle());
            i++;
        }
    }

    public void following(User currentUser)
    {
        for(String s : currentUser.getFollowing())
        {
            System.out.println(s);
        }
    }

    public void checkHome(User currentUser, ArrayList<Post> posts)
    {
        for(Post p : posts)
        {
            for(String s : currentUser.getFollowing())
            {
                if(p.getUser().getUsername().equals(s))
                {
                    System.out.println("******************");
                    System.out.println("Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear());
                    System.out.println("Autor: "+p.getUser().getUsername());
                    System.out.println("NSFW: "+p.isAdult());
                    System.out.println("Title: "+p.getTitle());
                    System.out.println("About: "+p.getContent());
                    
                }
            }
        }
    }

    public static int[] bdToInt(String[] date)
    {
        int[] numb = new int[date.length];
        for(int x = 0; x < date.length;x++)
        {
            numb[x] = Integer.parseInt(date[x]);
        }

        return numb;
    }

    public static boolean checkIfUsernameExists(String username, ArrayList<User> users)
    {
        boolean exists = false;

        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                System.out.println("This username already exists, try with another one!");
                return exists = true;
            }
        }
        return exists;
    }

    public boolean checkIfFollowing(String username, User currentUser)
    {
        for(String s : currentUser.getFollowing())
        {
            if(s.equals(username)) return true;
            
        }

        return false;
    }

    public void sendMessage(User currentUser, User messaged, String message)
    {
        

    }

    public User getFollowerUser(String username, User currentUser, ArrayList<User> users)
    {
       for(User u : users)
       {
           if(u.getUsername().equals(username))
           {
               return u;
           }
       }
       return null;
        
    }

    public void seeMessages(User currentUser, User messaged)
    {
        for(String message : currentUser.getPrivateMessage())
        {
            System.out.println("You: "+message);

            for(String mess : messaged.getPrivateMessage())
            {
                System.out.println("\t"+messaged.getUsername()+": "+mess);
            }
            
        }
    }

    public boolean checkIfBlocked(String username, User currentUser)
    {
        for(String s : currentUser.getBlocked())
        {
            
            if(s.equals(username)) return true;
            
        }

        return false;
    }

   


   

    


    

    

    

}
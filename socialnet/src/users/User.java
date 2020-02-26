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
    private ArrayList<User> following;

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

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
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

    public static void printMenuWhenLogged()
    {
        System.out.println("");
    }

    public static void printEveryPost(ArrayList<Post> posts)
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


    

    

    

}
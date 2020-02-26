package users;
import posts.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User
{

    public Admin(String username, String password) 
    {
        super(username, password);
    }

    public static void printMenuWhenLogged()
    {
        System.out.println("**********************************");
        System.out.println("* 1- Post                        *");
        System.out.println("* 2- List all posts              *");
        System.out.println("* 3- Delete post                 *");
        System.out.println("* 4- Grant editor role to reader *");
        System.out.println("* 5- List editors                *");
        System.out.println("* 6- List readers                *");
        System.out.println("* 7- Log out                     *");
        System.out.println("**********************************");
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
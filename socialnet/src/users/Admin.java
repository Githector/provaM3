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

    @Override
    public void printMenuWhenLogged()
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

    @Override
    public void printPostsToDelete(ArrayList<Post> posts)
    {
        int i = 1;
        
        for(Post p : posts)
        {
            
            System.out.println(i+"."+" Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear()+" - "+p.getLdt().getHour()+":"+p.getLdt().getMinute()+" - "+ "Autor: "+p.getUser().getUsername()+" Title: "+p.getTitle());
            i++;
        }
    }

    @Override
    public void deletePost(int id, ArrayList<Post> posts)
    {
        for(Post p : posts)
        {
            if(id -1 == posts.indexOf(p))
            {
                System.out.println("Post deleted succesfully as admin");
                posts.remove(p);
                break;
            }
        }
    }

    public static void printEditors(ArrayList<User> users)
    {
        System.out.println("Listing users with editor role: ");
        System.out.println("--------------------------------------");
        for(User u : users)
        {
            if(u.getRole().equalsIgnoreCase("Editor"))
            {
                System.out.println(u.getUsername());
            }
        }
    }

    public static void printReaders(ArrayList<User> users)
    {
        System.out.println("Listing users with reader role: ");
        System.out.println("--------------------------------------");
        for(User u : users)
        {
            if(u.getRole().equalsIgnoreCase("reader"))
            {
                System.out.println(u.getUsername());
            }
        }
    }

    public static void grantEditorRole(String username, ArrayList<User> users)
    {
        boolean success = false;
        for(User u : users)
        {
            if(u.getUsername().equals(username))
            {
                Editor editor = new Editor(u.getUsername(), u.getPassword());
                editor.setRole("Editor");
                users.remove(u);
                users.add(editor);
                System.out.println("Editor role granted! ");
                success = true;
                break;
            }
        }

        if(!success)
        {
            System.out.println("Editor role could not be granted... Check that the username is written properly");
        }
    }

    
}
package users;

import users.*;
import posts.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader extends User
{
    
    private LocalDate birthDate;

    public Reader(String username, String password) {
        super(username, password);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
    public void printMenuWhenLogged()
    {
        System.out.println("**********************************");
        System.out.println("* 1- Follow a new editor         *");
        System.out.println("* 2- Following                   *");
        System.out.println("* 3- Home                        *");
        System.out.println("* 4- Log out                     *");
        System.out.println("**********************************");
    }

    @Override
    public void deletePost(int id, ArrayList<Post> posts)
    {
        for(Post p : posts)
        {
            if(posts.indexOf(p) == id-1 && p.getUser().getRole().equalsIgnoreCase("Reader"))
            {
                System.out.println("Post deleted succesfully! ");
                posts.remove(p);
                break;
            }
        }
    }

    @Override
    public void printPostsToDelete(ArrayList<Post> posts)
    {
        int i = 1;
        for(Post p : posts)
        {
            if(p.getUser().getRole().equalsIgnoreCase("Reader"))
            {
                System.out.println(i+"."+" Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear()+" - "+p.getLdt().getHour()+":"+p.getLdt().getMinute()+" - "+ "Autor: "+p.getUser().getUsername()+" Title: "+p.getTitle());
                i++;
            }
        }
    }

    @Override
    public void following(User currentUser)
    {
        for(String s : currentUser.getFollowing())
        {
            System.out.println(s);
        }
    }

    
    
}
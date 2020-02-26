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
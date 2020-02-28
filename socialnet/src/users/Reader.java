package users;

import users.*;
import posts.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader extends User
{
    private Boolean adult;
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

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
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
    public void following(User currentUser)
    {
        System.out.println("Following: ");
        System.out.println("********************");
        for(String s : currentUser.getFollowing())
        {
            System.out.println(s);
        }
    }

    @Override
    public void checkHome(User currentUser, ArrayList<Post> posts)
    {
        for(Post p : posts)
        {
            for(String s : currentUser.getFollowing())
            {
                if(p.getUser().getUsername().equals(s) && adult)
                {
                    System.out.println("******************");
                    System.out.println("Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear());
                    System.out.println("Autor: "+p.getUser().getUsername());
                    System.out.println("NSFW: "+p.isAdult());
                    System.out.println("Title: "+p.getTitle());
                    System.out.println("About: "+p.getContent());
                    
                }

                else if(p.getUser().getUsername().equals(s) && !adult && p.isAdult() == false)
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

    public static LocalDate toLocalDate(int[] numb)
    {
        LocalDate ld = LocalDate.of(numb[2], numb[1], numb[0]);
        return ld;
    }

    public static boolean checkPeriod(LocalDate ld)
    {
        Period p = Period.between(ld, LocalDate.now());

        if(p.getYears()> 17)
        {
            return true;
        }

        else
        {
            return false;
        }

    }

    @Override
    public boolean checkIfFollowing(String username, User currentUser)
    {
        for(String s : currentUser.getFollowing())
        {
            if(s.equals(username)) return true;
            
        }

        return false;
    }


    

    

    
    

    
    
}
package main;

import functions.*;
import posts.*;
import users.*;

import java.time.LocalDateTime;
import java.util.*;

public class Main 
{

    //  FUNCIONA PERÒ NO PRINTA EL MENÚ DE ADMIN PERÒ SI PREMS 1 O 2 FUNCIONA
    public static final Scanner scan = new Scanner(System.in);
    public static String[] roles = new String[] {"Admin, Reader, Editor"};
    public static void main(String[] args) 
    {
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        //hello
        boolean out = false;
        boolean wrongpass = true;
        Function.initCirvi();

        if(wrongpass)
        {
            System.out.println("Introduce the username that the admin role will have: ");
            String username = scan.next();
            while(wrongpass)
            {
                System.out.println("Introduce the password: ");
                String password = scan.next();
                System.out.println("Introduce the password again: ");
                String pass = scan.next();
            
                if(password.equals(pass))
                {
                    Admin admin = new Admin(username, password);
                    User user = admin;
                    user.setRole("Admin");
                    users.add(user);
                    System.out.println("Admin user created succesfully! ");
                    scan.nextLine();
                    wrongpass = false;
                    


                }
                else
                {
                    System.out.println("Wrong password introduced, try again! ");
                }
            }            
        
        }

        while(!out)
        {
            Function.clear();
            Function.initMenu();
            int option = scan.nextInt();
            scan.nextLine();
            switch(option)
            {
                case 1:
                    System.out.println("Introduce your username: ");
                    String username = scan.next();
                    System.out.println("Introduce your password: ");
                    String password = scan.next();
                    User currentUser = User.canLogIn(users, username, password);
                    if(currentUser == null)
                    {
                        System.out.println("Username or Password are incorrect ");
                        
                    }

                    else
                    {
                        boolean logged = true;
                        while(logged)
                        {
                            Function.clear();
                            System.out.println("Role: "+currentUser.getRole());
                            currentUser.printMenuWhenLogged();
                            option = scan.nextInt();
                            scan.nextLine();
                            
                            switch(currentUser.getRole()){
                                case "Admin":
                                    switch(option)
                                    {
                                        case 1:
                                        
                                        System.out.println("Introduce the post title: ");
                                        String postTitle = scan.nextLine();
                                        System.out.println("What is it about: ");
                                        String postText = scan.nextLine();
                                        boolean adultContent;
                                        System.out.println("Is it NSFW content? (Y/N)");
                                        char isIt = scan.next().charAt(0);
                                        if(isIt == 'Y' || isIt == 'y') adultContent = true;
                                        else adultContent = false;
                                        System.out.println(adultContent);
                                        LocalDateTime ldt = LocalDateTime.now();
                                        Post post = new Post(ldt, currentUser, adultContent, postTitle, postText);
                                        posts.add(post);
                                        break;
                                    case 2:
                                        Admin.printEveryPost(posts);
                                        break;
                                    case 3: 
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        break;
                                    case 6:
                                        break;
                                    case 7:
                                        logged = false;
                                        break;
                                }
    

                                break;

                                case "E":
                            }
                            
                            
                        }
                    }

                    break;
                case 2:

                    break;
                case 3:
                    out = true;
                    break;
            }

        }

    }
}
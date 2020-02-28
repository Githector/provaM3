package main;

import functions.*;
import posts.*;
import users.*;
import messages.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class Main 
{

    public static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) 
    {
        int pendingMessages = 0;
        ArrayList<Message> messages = new ArrayList<>();
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
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
                            if((currentUser.getRole().equals("Editor"))) System.out.println("Pending messages: "+currentUser.getPendingMessages());
                            System.out.println("Role: "+currentUser.getRole());
                            currentUser.printMenuWhenLogged();
                            option = scan.nextInt();
                            scan.nextLine();
                            
                            switch(currentUser.getRole())
                            {
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
                                        currentUser.printPostsToDelete(posts, currentUser);
                                        option = scan.nextInt();
                                        scan.nextLine();
                                        currentUser.deletePost(option, posts, currentUser);
                                        break;
                                    case 4:
                                        
                                        Admin.printReaders(users);
                                        System.out.println("Who would you like to grant Editor permissions to? ");
                                        username = scan.next();
                                        Admin.grantEditorRole(username, users);
                                        break;
                                    case 5:
                                        Admin.printEditors(users);
                                        break;
                                    case 6:
                                        Admin.printReaders(users);
                                        break;
                                    case 7:
                                        logged = false;
                                        break;
                                }
    

                                break;

                                case "Editor":
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
                                            System.out.println("Introduce the username of the editor you want to follow: (Press enter to display all of them)");
                                            scan.nextLine();
                                            Editor.printEditorsToFollow(users, currentUser);
                                            username = scan.next();
                                            boolean notBlocked = currentUser.amaIBlocked(username, currentUser, users);
                                            if(!notBlocked)
                                            {
                                                System.out.println("You can't follow this user because you're in his/her blocklist!");
                                            }
                                            else
                                            {
                                                Editor.followEditor(users, username, currentUser);
                                            }
                                            break;
                                        case 3:
                                            currentUser.following(currentUser);
                                            break;
                                        case 4:
                                            currentUser.checkHome(currentUser, posts);
                                            break;
                                        case 5:
                                            currentUser.printPostsToDelete(posts, currentUser);
                                            option = scan.nextInt();
                                            scan.nextLine();
                                            currentUser.deletePost(option, posts, currentUser);
                                            break;
                                        case 6:
                                            currentUser.following(currentUser);
                                            System.out.println("To who you'd like to send the message to? ");
                                            username = scan.next();
                                            scan.nextLine();
                                            boolean canMessage = currentUser.checkIfFollowing(username, currentUser);
                                           
                                            if(canMessage)
                                            {
                                                System.out.println("What is the message about: ");
                                                String text = scan.nextLine();
                                                User messaged = currentUser.getFollowerUser(username, currentUser, users);
                                                Message message = new Message( ldt = LocalDateTime.now(),currentUser, text);
                                                message.setDest(messaged);
                                                messages.add(message);
                                                messaged.setPendingMessages(messaged.getPendingMessages()+1);
                                                System.out.println("Message sent!");
                                                 
                                                
                                                
                                            }
                                            else System.out.println("You're not following '"+ username+"', follow them first!");

                                            break;
                                        case 7:
                                          
                                            currentUser.printMessages(messages, currentUser);
                                            scan.nextLine();
                                            
                                          

                                            break;
                                        case 8:
                                            Editor.printFollowing(currentUser);
                                            username = scan.next();
                                            User blocked = currentUser.getFollowerUser(username, currentUser, users);
                                            Editor.blockUser(currentUser, blocked);
                                            
                                            break;
                                        case 9:
                                            Editor.printBlockedUsers(currentUser);
                                            username = scan.next();
                                            Editor.unblockUser(currentUser, username);
                                            break;
                                        case 10:
                                            logged = false;
                                            break;
                                    }
                                    break;

                                case "Reader":
                                    
                                    switch(option)
                                    {
                                        case 1:
                                            System.out.println("Introduce the username of the editor you want to follow: (Press enter to display all of them)");
                                            scan.nextLine();
                                            Editor.printEditorsToFollow(users, currentUser);
                                            username = scan.next();
                                            Editor.followEditor(users, username, currentUser);
                                            break;
                                        case 2:
                                            currentUser.following(currentUser);
                                            break;
                                        case 3:
                                            
                                            currentUser.checkHome(currentUser, posts);
                                            break;
                                        case 4:
                                            logged = false;
                                            break;
                                    }
                                    break;
                            }
                            
                            
                        }
                    }

                    break;
                case 2:
                    System.out.println("Introduce the username: ");
                    username = scan.next();
                    boolean exists = User.checkIfUsernameExists(username, users);
                    if(!exists)
                    {
                    
                        System.out.println("Introduce the password ");
                        password = scan.next();
                        System.out.println("Introduce your birth date: ");
                        String[] birthdate = scan.next().split("/");
                        int[] birthday = Reader.bdToInt(birthdate);
                        LocalDate bd = Reader.toLocalDate(birthday);
                        Reader reader = new Reader(username, password);
                        reader.setAdult(Reader.checkPeriod(bd));
                        reader.setBirthDate(bd);
                        User user = reader;
                        user.setRole("Reader");
                        users.add(user);
                        
                    }

                    break;
                case 3:
                    out = true;
                    break;
            }

        }

    }
}
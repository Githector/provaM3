package users;
import posts.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Editor extends User
{
    


    public Editor(String username, String password) {
        super(username, password);
    }


    @Override
    public void printMenuWhenLogged()
    {
        System.out.println("**********************************");
        System.out.println("* 1- Post                        *");
        System.out.println("* 2- Follow an editor            *");
        System.out.println("* 3- Following                   *");
        System.out.println("* 4- Home                        *");
        System.out.println("* 5- Delete post                 *");
        System.out.println("* 6- Send Message                *");
        System.out.println("* 7- Read Messages               *");
        System.out.println("* 8- Block editor                *");
        System.out.println("* 9- Unblock editor              *");
        System.out.println("* 10 - Log out                   *");
        System.out.println("**********************************");
    }


    @Override
    public void deletePost(int id, ArrayList<Post> posts, User currentUser)
    {
        for(Post p : posts)
        {
            if(posts.indexOf(p) == id -1 && p.getUser().getRole() != "Admin" && p.getUser().getUsername().equals(currentUser.getUsername()))
            {
                System.out.println("Post deleted succesfully!");
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
            if(p.getUser().getRole() != "Admin" && p.getUser().getUsername().equals(currentUser.getUsername()))
            {
                System.out.println(i+"."+" Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear()+" - "+p.getLdt().getHour()+":"+p.getLdt().getMinute()+" - "+ "Autor: "+p.getUser().getUsername()+" Title: "+p.getTitle());
                i++;
            }
        }
    }


    public static void printEditorsToFollow(ArrayList<User> users, User currentUser)
    {
        System.out.println("Listing users with editor role: ");
        System.out.println("--------------------------------------");
        for(User u : users)
        {
            if(u.getRole().equalsIgnoreCase("Editor") && u.getUsername()!= currentUser.getUsername())
            {
                System.out.println(u.getUsername());
            }
        }
    }

    public static void followEditor(ArrayList<User> users, String username, User currentUser)
    {
        boolean success = false;
        for(User u : users)
        {
            if(u.getUsername().equals(username) && u.getRole().equalsIgnoreCase("Editor") && u.getUsername()!= currentUser.getUsername() && !currentUser.checkIfFollowing(username, currentUser) && !currentUser.checkIfBlocked(username, currentUser))
            {
                success = true;
                System.out.println(u.getUsername()+" followed!");
                currentUser.getFollowing().add(username);
                break;
            }
        }

        if(!success)
        {
            System.out.println("Check that you introduced the username properly, perhaps you're following this editor already or you blocked this user");
        }
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

    //when blocking remove this person from followers and if they try to foloow back display error message
    @Override
    public void checkHome(User currentUser, ArrayList<Post> posts)
    {
        Scanner scan = new Scanner(System.in);
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

        scan.nextLine();


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

    @Override
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

    @Override
    public void sendMessage(User currentUser, User messaged, String message)
    {
        currentUser.getPrivateMessage().add(message);
        
    }


    @Override
    public void seeMessages(User currentUser, User messaged)
    {
        for(String message : messaged.getPrivateMessage())
        {
            System.out.println(messaged.getUsername()+": "+message+"\n");

            for(String mess : currentUser.getPrivateMessage())
            {
                System.out.println("\t"+"You:" +mess);
                break;
                
            }
        }
    }

    public static void blockUser(User currentUser, User blocked)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("User "+blocked.getUsername()+" will now be blocked!");
        currentUser.getFollowing().remove(blocked.getUsername());
        currentUser.getBlocked().add(blocked.getUsername());
        scan.nextLine();
    }

    public boolean checkIfBlocked(String username, User currentUser)
    {
        for(String s : currentUser.getBlocked())
        {
            
            if(s.equals(username)) return true;
            
        }

        return false;
    }

    public static void printBlockedUsers(User currentUser)
    {
        System.out.println("Currentlu blocked users: ");
        System.out.println("******************************");
        for(String s : currentUser.getBlocked())
        {
            System.out.println(s);
        }
    }



    public static void unblockUser(User currentUser, String username)
    {
        Scanner scan = new Scanner(System.in);
        boolean success = false;
        for(String s : currentUser.getBlocked())
        {
            if(s.equals(username))
            {
                
                System.out.println(username+ " is now unblocked!");
                currentUser.getBlocked().remove(s);
                success = true;
                break;
            }
        }

        if(!success)
        {
            System.out.println("The introduced user is currently not blocked or doesn't exist");
        }
        scan.nextLine();
        
    }


    
    
}
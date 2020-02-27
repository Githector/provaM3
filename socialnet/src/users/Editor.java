package users;
import posts.*;
import java.util.ArrayList;
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
        System.out.println("* 5- Log out                     *");
        System.out.println("**********************************");
    }


    @Override
    public void deletePost(int id, ArrayList<Post> posts)
    {
        for(Post p : posts)
        {
            if(posts.indexOf(p) == id -1 && p.getUser().getRole() != "Admin")
            {
                System.out.println("Post deleted succesfully!");
                posts.remove(p);
                break;
            }
        }
    }

    public void printPostsToDelete(ArrayList<Post> posts)
    {
        int i = 1;
        for(Post p : posts)
        {
            if(p.getUser().getRole() != "Admin")
            System.out.println(i+"."+" Date: "+p.getLdt().getDayOfMonth()+"/"+p.getLdt().getMonthValue()+"/"+p.getLdt().getYear()+" - "+p.getLdt().getHour()+":"+p.getLdt().getMinute()+" - "+ "Autor: "+p.getUser().getUsername()+" Title: "+p.getTitle());
            i++;
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
            if(u.getUsername().equals(username) && u.getRole().equalsIgnoreCase("Editor") && u.getUsername()!= currentUser.getUsername() && !currentUser.checkIfFollowing(username, currentUser))
            {
                success = true;
                System.out.println(u.getUsername()+" followed!");
                currentUser.getFollowing().add(username);
                break;
            }
        }

        if(!success)
        {
            System.out.println("Check that you introduced the username properly or perhaps you're following this editor already");
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

    @Override
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
package com.company;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {


    public Scanner scan = new Scanner(System.in); // Initialising a new Scanner object Called Scan
    public static void main(String args[]) throws Exception { // Main method
        Main BigBoi = new Main();  // Initialising a new class object Called BigBoi

        System.out.println("Please Enter the Persons Email ID");
        String grabbedNameAdd = BigBoi.scan.nextLine(); // Grabs the input and saves it as string 'GrabbedURL'
        BigBoi.websiteReader(grabbedNameAdd); // Calls the URLReader Method with the entered URL as the input
    }

    public void websiteReader(String importedName) throws Exception{
        // Setter URLReader method Takes the URL and pulls in the raw HTML website data
        // Then Searches the data line by line until its found the name
        URL eCS_Website = new URL("https://www.ecs.soton.ac.uk/people/"+importedName);
        String websiteUrlString = ("https://www.ecs.soton.ac.uk/people/"+importedName);
        // System.out.println(websiteUrlString); For Debugging
        //Creat a new URL object called eCS_Website using the users entered Email ID


        BufferedReader in = new BufferedReader(new InputStreamReader(eCS_Website.openStream()));
        // Initialise  a buffered reader object using the URL object
        String inputStream;
        while ((inputStream = in.readLine()) != null){
            if(inputStream.contains("name") || inputStream.contains(urlNameGrabber(websiteUrlString))){
                // Filters the raw html to only save lines of code that contain the word name or the name in the email
                Pattern patLookingFor = Pattern.compile( "<h1 class=\"uos-page-title uos-main-title uos-page-title-compressed\" property=\"name\">(.*?)<em property=\"honorificSuffix\">&nbsp;</em></h1>" );
                // Pattern object which is used by me to filter the code down to just the name using some regex
                Matcher m = patLookingFor.matcher(inputStream);
                if( m.find() ) {
                    String finalName = m.group(1);
                    System.out.println(finalName);
                    break;
                }

            }

        } // Loop runs until all the code has been passed through, being filtered out by the if statements
    }




    public String urlNameGrabber(String CurrentURL){ // Method grabs the name off the end of the url to be used with filtering (Not needed)
        // This method is only useful if the user gives you a url and not the email ID
        String endOfURL = "0";
        try {
            URI uri = new URI(CurrentURL);
            String path = uri.getPath();
            endOfURL = path.substring(path.lastIndexOf('/') + 1);
            // Creats a substring EndOfURL which holds the name used as the last path in the url
        }catch(URISyntaxException e){
            e.printStackTrace();
            System.out.println("Nothing at end of URL");
        }

        return endOfURL;
    }

}


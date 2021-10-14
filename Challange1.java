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

        System.out.println("Please Enter the URL you want to search");
        String GrabbedURL = BigBoi.scan.nextLine(); // Grabs the input and saves it as string 'GrabbedURL'
        BigBoi.URLReader(GrabbedURL); // Calls the URLReader Method with the entered URL as the input
    }

    public void URLReader(String ImportedUrl) throws Exception{
        // Setter URLReader method Takes the URL and pulls in the raw HTML website data
        // Then Searches the data line by line until its found the name
        URL ECS_Website = new URL(ImportedUrl);
        // Creat a new URL object called ECS_Website using the users entered URL


        BufferedReader in = new BufferedReader(new InputStreamReader(ECS_Website.openStream()));
        // Initialise  a buffered reader object using the URL object
        String InputStream;
        while ((InputStream = in.readLine()) != null){
            if(InputStream.contains("name") & InputStream.contains(URLNameGrabber(ImportedUrl))){
                // Filters the raw html to only save lines of code that contain the word name and the name in the email
                Pattern PatLookingFor = Pattern.compile( "<h1 class=\"uos-page-title uos-main-title uos-page-title-compressed\" property=\"name\">(.*?)<em property=\"honorificSuffix\">&nbsp;</em></h1>" );
                // Pattern object which is used by me to filter the code down to just the name using some regex
                Matcher m = PatLookingFor.matcher(InputStream);
                if( m.find() ) {
                    String FinalName = m.group(1);
                    System.out.println(FinalName);
                }else {
                    System.out.println("No Name Could Be Found");
                }


            }else {
            System.out.println("Name Could Not Be Found");
            }

        } // Loop runs until all the code has been passed through, being filtered out by the if statements
    }

    public String URLNameGrabber(String CurrentURL){ // Method grabs the name off the end of the url to be used with filtering
        String EndOfURL = "0";
        try {
            URI uri = new URI(CurrentURL);
            String path = uri.getPath();
            EndOfURL = path.substring(path.lastIndexOf('/') + 1);
            // Creats a substring EndOfURL which holds the name used as the last path in the url
        }catch(URISyntaxException e){
            e.printStackTrace();
            System.out.println("No Name In URL");
        }

        return EndOfURL;
    }

}


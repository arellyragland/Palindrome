/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ragland
 */
public class Palindrome 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        
        System.out.println ("Server is running");
        ServerSocket serverSoc = new ServerSocket(6004);
        System.out.println("Accepting the connection");
        while (true)
        {
            Socket sock = serverSoc.accept();
            System.out.println ("Accepted Connection");
            determinePalindrome(sock);
        }
        
    }
    static private void determinePalindrome (Socket s) throws IOException
    {  
        BufferedReader in = new BufferedReader
                  (new InputStreamReader(s.getInputStream())) ;
        PrintWriter out = new  PrintWriter(s.getOutputStream(), true);
        
        out.println("Enter the string to check for palindrome,Enter bye to exit");
        String input = in.readLine();
        
        while (!input.equals("bye"))
        {
            String original, reverse = "";
           
            System.err.println("Got the String: " + input);
            Scanner scanner = new Scanner(input);
            original = scanner.next();            
            int length = original.length();
            for ( int i = length - 1; i >= 0; i-- )
                reverse = reverse + original.charAt(i);
 
            if (original.equals(reverse))
            {
                out.println("Entered string is a palindrome");
            }
            else
            {
                out.println("Entered string is NOT a palindrome");
            }
            
            input = in.readLine();
            while(input.isEmpty())
            {
            out.println("Enter the string to check for palindrome.Enter bye to exit");
            input = in.readLine();
            }
        }
        
       in.close();
       out.close();
       s.close();
    }
    
}

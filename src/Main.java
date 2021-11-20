/**
 * @author: Omar Khaled Al Haj   20190351
 * @author: Alaa Mahmoud Ebrahim 20190105
 */


import java.io.*;
import java.util.Scanner;

public class Main {
 
    public static void compress(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        Scanner sc = new Scanner(br);
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.out"));
        String s1 = sc.nextLine();
        for(int i = 0; i < s1.length(); i++){
            String searchingFor = "";
            String sub = s1.substring(0, i);
            String foundSoFar = "";
            boolean endOfString = false;
            for(int j = i; j < s1.length(); j++){
                searchingFor += s1.charAt(j);
                if(sub.contains(searchingFor)){
                    foundSoFar = searchingFor;
                    if(j == s1.length() - 1){
                        endOfString = true;
                        i = j;
                        break;
                    }
                }else{
                    i = j;
                    break;
                }
            }
            int position = s1.indexOf(foundSoFar);
            String next = "";
            next += s1.charAt(i);
            if(endOfString){
                next = "NULL";
            }
            writer.write("<" + position + "," + foundSoFar.length() + "," + next + ">\n");
        }
        writer.write(" ");
        writer.close();
    }
 
    public static void decompress(String s) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(s));
        Scanner sc = new Scanner(br);
        String s1 = "";
        StringBuilder result = new StringBuilder();
        while(true){
            s1 = sc.nextLine();
            if(s1.equals(" ")) break;
            int position = -1, length = -1;
            String nextChar = ".";
            for(int i = 1; i < s1.length() - 1;){
                StringBuilder x = new StringBuilder();
                while(s1.charAt(i) != ',' && s1.charAt(i) != '>'){
                    x.append(s1.charAt(i));
                    i++;
                }
                i++;
                if(position == -1){
                    position = Integer.parseInt(x.toString());
                }else if(length == -1){
                    length = Integer.parseInt(x.toString());
                }else{
                    if(!x.toString().equals(" ")){
                        nextChar = x.toString();
                    }
                }
            }
            for(int i = position; i < position + length; i++){
                result.append(result.charAt(i));
            }
            if(!nextChar.equals("NULL"))  result.append(nextChar);
        }
        System.out.println(result);
    }
 
    public static void main(String[] args) throws IOException {
        compress("input.in");
        decompress("output.out");
    }
}
package com.dsa.learning.string.striver.easy;

/**
 * THOUGHT PROCESS:
 * Approach #01: We can create another string which will be the REVERSE of this String. Then compare the 2 strings, if they are equal than it means that they are Pallindrome.
 * Approach #02: We can use the 2 pointers approach and start comparing the first and last characters. And keep moving them towards the center and keep comparing characters.
 *               If any character till middle is not equal, then string not Pallindrome.
 */
public class PallindromeCheck {

    public static void main(String[] args) {
        String str = "hannah";
        String reversed = ReverseAString.reverseString(str);

        if(str.equals(reversed)){
            System.out.println("Pallindrome");
        } else {
            System.out.println("NOT Pallindrome");
        }

        System.out.println("Pallindrome Approach2: " + approach2(str));
    }

    public static boolean approach2(String str){
        boolean flag = true;

        char[] charArray = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;

        do{
            if(charArray[i] != charArray[j]){
                flag = false;
                break;
            }
            i++;
            j--;
        }while(i < j);

        return flag;
    }

}

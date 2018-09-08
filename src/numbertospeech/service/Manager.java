/**
 * Methods of this class accomplishes few tasks.
 * Thats why we called it Manager.
 */
package numbertospeech.service;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 * riyadnoapara@gmail.com
 * https://github.com/riyad-ctg/Number-to-speech
 * +8801829443600
 * 
 * @author RIYAD
 */
public class Manager {
    
    public static final int EMPTY_STRING = 456,FALSE_STRING = 983,VALID_STRING = 786;
    
    
    /**
     * Centering a frame with respect to screen resolution.
     * @param frame The frame that is being centered.
     */
    public void setOnCenterLocation(JFrame frame){
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenDimension.width-frame.getWidth())/2,(screenDimension.height-frame.getHeight())/2);
    }
    
    /**
     * Converts a single integer digit to string
     * @param num the single digit integer number
     * @return String converted from number
     */
    public String getTranslatedDigit(char num){
        switch(num){
            case '0':
                return "zero";
            case '1':
                return "one";
            case '2':
                return "two";
            case '3':
                return "three";
            case '4':
                return "four";
            case '5':
                return "five";
            case '6':
                return "six";
            case '7':
                return "seven";
            case '8':
                return "eight";
            case '9':
                return "nine";
            default:
                return "";
        }
    }
    
    /**
     * Converts two digit integer number to string
     * @param num1 the first digit of two digit integer number
     * @param num2 the second digit of two digit integer number
     * @return String converted from number
     */
    public String getTranslatedDigit(char num1,char num2){
        if(num1 == '0'){
            if(num2 == '0') return "";
            else return getTranslatedDigit(num2);
        }else if(num1 == '1'){
            switch(num2){
                case '0':
                    return "ten";
                case '1':
                    return "eleven";
                case '2':
                    return "twelve";
                case '3':
                    return "thirteen";
                case '4':
                    return "fourteen";
                case '5':
                    return "fifteen";
                case '6':
                    return "sixteen";
                case '7':
                    return "seventeen";
                case '8':
                    return "eighteen";
                case '9':
                    return "nineteen";
                default:
                    return "";
            }
        }else{
            switch(num1){
                case '2':
                    return "twenty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '3':
                    return "thirty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '4':
                    return "forty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '5':
                    return "fifty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '6':
                    return "sixty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '7':
                    return "seventy" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '8':
                    return "eighty" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                case '9':
                    return "ninety" + ((num2!='0')?" " + getTranslatedDigit(num2):"");
                default:
                    return "";
            }
        }
    }
    
    /**
     * 
     * Converts three digit integer number to string
     * @param num1 first digit of the three digit integer number
     * @param num2 second digit of the three digit integer number
     * @param num3 third digit of the three digit integer number
     * @return converted from three digit number
     */
    public String getTranslatedDigit(char num1,char num2,char num3){
        if(num1 == '0') return getTranslatedDigit(num2, num3);
        else return getTranslatedDigit(num1) + " hundred " + getTranslatedDigit(num2, num3);
    }
    
    /**
     * Just formating the number with deleting front zeroes
     * @param number the number that being formated
     * @return the formated number
     */
    public String formatTheNumber(String number){
        boolean allZeores = true;
        int len = number.length();
        int got = 0; // where from leading zeroes end.
        for(int i = 0;i<len;i++){
            if(number.charAt(i)!='0'){
                got = i;
                allZeores = false;
                break;
            }
        }
        if(allZeores) return "0";
        else return (String) number.subSequence(got, len);
    }
    
    /**
     * 
     * Checking string is invalid or not.
     * @param numString the number that being checked.
     * @return The result is invalid or not.
     */
    public int stringChecker(String numString){
        int len = numString.length();
        if(len == 0) return EMPTY_STRING;
        else{
            for(int i = 0;i<len;i++){
                if(numString.charAt(i)<'0'||numString.charAt(i)>'9'){
                    return FALSE_STRING;
                }
            }
        }
        return VALID_STRING;
    }
    
    /**
     * Converting a number string to a list of instructions.
     * @param number the number being converted
     * @return  a list of strings to play/show
     */
    public List<String> instructionsGenerator(String number){
        /**
         * First we generate a string that contains instructions.
         * Then we generate a list with the help of getInstructionListFromString method.
         */
        String []otherInstructions = {"thousand","million","billion","trillion"};
        int instructionIndex = -1;
        String str = "";
        
        for(int i = number.length()-1;i>=0;i--){
            String tempStr = "";
            if(i == 0){
                tempStr+=getTranslatedDigit(number.charAt(i));
            }else if(i == 1){
                tempStr+=getTranslatedDigit(number.charAt(i-1),number.charAt(i));
            }else if(i >= 2){
                tempStr+=getTranslatedDigit(number.charAt(i-2),number.charAt(i-1),number.charAt(i));
            }
            
            if(instructionIndex>=0){
                if(!("".equals(tempStr)))
                    str = tempStr + " " + otherInstructions[instructionIndex] + " " + str;
            }else{
                str = tempStr;
            }
            instructionIndex++;
            i-=2;
        }
        
        return getInstructionListFromString(str);
    }
    
    /**
     * Generate a list of instructions.
     * @param instructionString A string that contains instructions.
     * @return List of instructions.
     */
    private List<String> getInstructionListFromString(String instructionString){
        List<String> instructorList = new ArrayList<>();
        String temp = "";
        instructionString+=' ';
        for(int i = 0;i<instructionString.length();i++){
            char c = instructionString.charAt(i);
            if(c == ' ' && !temp.equals("")){
                instructorList.add(temp);
                temp = "";
            }else if(c!=' '){
                temp+=c;
            }
        }
        return instructorList;
    } 
}

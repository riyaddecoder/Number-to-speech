/**
 * This class accomplishes few task.
 */
package numbertospeech.service;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RIYAD
 */
public final class Manager {
    
    public static final int EMPTY_STRING = 456,FALSE_STRING = 983,VALID_STRING = 786;
    
    
    /**
     * This method does returns a point to centering to frame with respect to screen resolution.
     * @param frameSize The size of the frame
     * @return A point to reposition the frame.
     */
    public Point setOnCenterLocation(Dimension frameSize){
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point pp = new Point((screenDimension.width-frameSize.width)/2,(screenDimension.height-frameSize.height)/2);
        return pp;
    }
    
    /**
     * This method converts a single integer value to string
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
    
    public String getTranslatedDigit(char num1,char num2,char num3){
        if(num1 == '0') return getTranslatedDigit(num2, num3);
        else return getTranslatedDigit(num1) + " hundred " + getTranslatedDigit(num2, num3);
    }
    
    /**
     * Just formating the number with deleting front zeroes
     * @param number
     * @return 
     */
    public String formatTheNumber(String number){
        boolean allZeores = true;
        int len = number.length();
        int got = 0; // When got a digit that is not zero
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
    
    
    public List<String> instructionsGenerator(String number){
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

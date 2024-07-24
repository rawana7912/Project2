/*
----------------------------------------------------------------------------------------------------------
    Name:		Driver
    Authors:	Ollie Peel, Rawan Alhachami
    Language:	Java
    Date:		2024-07-20
    Purpose:	The purpose of this program is to create a driver class that can read data from the input
    file, convert the data into usable expressions, use the evalExpression method in the ExpressionParser
    class to parse those expressions, and output the results to the console.
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Reason
    OSP		2024-07-20	Original Version of Code
    OSP     2024-07-23  Deleted Converter class, added code to remove spaces from each expression, and
    added clarifying comments
----------------------------------------------------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws Exception {
        ArrayList<String> arrInput = new ArrayList<String>();
            // File path is to a copy of the Expressions.txt file on my personal computer
        File fileInput = new File("C:\\Users\\5555l\\OneDrive\\Desktop\\Expressions.txt");
        BufferedReader bufRead = new BufferedReader(new FileReader(fileInput));
        String strTemp;

        // Reads the input file and adds each line as a different element of an ArrayList
        while ((strTemp = bufRead.readLine()) != null){
            arrInput.add(strTemp);
        }
        for (int i = 0; i < arrInput.size(); i++){
            String currExp = arrInput.get(i);
            // Print original expression to console
            System.out.println("Expression " + i + ": " + arrInput.get(i));
            // Prints the result of the parsed expression to the console
            System.out.print("\tResult: ");

            // Remove spaces from current expression
            StringBuilder sbUsableExp = new StringBuilder();
            for (int j = 0; j < currExp.length(); j++){
                char charCurr = currExp.charAt(j);
                // Add char to string builder if not a space
                if (charCurr != ' '){
                    sbUsableExp.append(charCurr);
                }
            }
            currExp = sbUsableExp.toString();

            /* FIXME result of ExpressionParser.evalExpression here */
        }
        bufRead.close();
    }
}

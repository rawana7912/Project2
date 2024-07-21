/*
----------------------------------------------------------------------------------------------------------
    Name:		Driver
    Authors:	Ollie Peel, Rawan Alhachami
    Language:	Java
    Date:		2024-07-20
    Purpose:	The purpose of this program is to create a driver class that can read data from the input
    file, use the Converter and ExpressionParser classes to convert the data into usable expressions and
    parse those expressions, and output the results to the console.
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Reason
    OSP		2024-07-20	Original Version of Code
----------------------------------------------------------------------------------------------------------
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) throws Exception {
        ArrayList<String> arrInput = new ArrayList<String>();
        File fileInput = new File("Expressions.txt");
        BufferedReader bufRead = new BufferedReader(new FileReader(fileInput));
        String strTemp;

        // Reads the input file and adds each line as a different element of an ArrayList
        while ((strTemp = bufRead.readLine()) != null){
            arrInput.add(strTemp);
        }
        for (int i = 0; i < arrInput.size(); i++){
            // Converts the current expression string into an ArrayList
            String strUsableExp = Converter.toUsableExpression(arrInput.get(i));
            System.out.println("Expression " + i + ": " + arrInput.get(i));
            // Prints the result of the parsed expression to the console
            System.out.print("\tResult: " /* FIXME result of expression parsing here */);
        }
        bufRead.close();
    }
}

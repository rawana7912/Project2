/*
----------------------------------------------------------------------------------------------------------
    Name:		Converter
    Authors:	Ollie Peel, Rawan Alhachami
    Language:	Java
    Date:		2024-07-20
    Purpose:	The purpose of this program is to convert the original string form of an expression into
    a usable string with adequate whitespace between each element.
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Reason
    OSP		2024-07-20	Created the toUsableExpression method
----------------------------------------------------------------------------------------------------------
*/

public class Converter {
    /**
     * Converts an expression string into an ArrayList of the same expression
     * @param strOriginalExp A string of an expression
     * @return An ArrayList of the expression with separate elements for each operand and operator
     */
    public static String toUsableExpression(String strOriginalExp){
        StringBuilder sbOperand = new StringBuilder();
        StringBuilder sbOperator = new StringBuilder();
        StringBuilder sbUsableExp = new StringBuilder();

        for (int i = 0; i < strOriginalExp.length(); i++){
            char charCurr = strOriginalExp.charAt(i);
            // If the current character is a number, add char to the operand string builder
            if (Character.isDigit(charCurr)){
                sbOperand.append(charCurr);
                if (!sbOperator.isEmpty()){ // Add operator string builder to final string if sb isn't empty
                    sbUsableExp.append(sbOperator.toString()).append(' ');
                    sbOperator.delete(0, sbOperand.length() - 1); // Clear string builder
                }
                
            } else { // If current character is an operator (or parentheses), add the char to the operator string builder
                sbOperator.append(charCurr);
                if (!sbOperand.isEmpty()){ // Add operand string builder to final string if sb isn't empty
                    sbUsableExp.append(sbOperand.toString()).append(' ');
                    sbOperand.delete(0, sbOperand.length() - 1); // Clear string builder
                }
            }
        }
        // Add any extra operators or operands left in the string builders to the final array
        if (!sbOperand.isEmpty()){
            sbUsableExp.append(sbOperand.toString());
            sbOperand.delete(0, sbOperand.length() - 1);
        }
        if (!sbOperator.isEmpty()){
            sbUsableExp.append(sbOperator.toString());
            sbOperator.delete(0, sbOperand.length() - 1);
        }
        return sbUsableExp.toString();
    }
}

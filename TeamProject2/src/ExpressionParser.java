/*
----------------------------------------------------------------------------------------------------------
    Name:		ExpressionParser
    Authors:	Ollie Peel, Rawan Alhachami
    Language:	Java
    Date:		2024-07-20
    Purpose:	The purpose of this program is to 
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Reason
    OSP		2024-07-20	Created the infixToPostfix and precedence methods, finished the precedence method.
----------------------------------------------------------------------------------------------------------
*/

import java.util.Scanner;
import java.util.Stack;

public class ExpressionParser {

    /**
     * Converts an infix expression to a postfix expression
     * @param strOriginalExp : An infix expression with tokens separated by whitespaces
     * @return : A postfix version of the infix expression separated by whitespaces
     */
    public static String infixToPostfix(String strOriginalExp){
        Scanner scn = new Scanner(strOriginalExp);
        Stack<String> stack = new Stack<>();
        StringBuilder sbPostfixExp = new StringBuilder();
        StringBuilder sbOperand = new StringBuilder();
        StringBuilder sbOperator = new StringBuilder();
        while (scn.hasNext()){
            String strCurrent = scn.next();
            if (Character.isDigit(strCurrent.charAt(0))){
                sbPostfixExp.append(strCurrent).append(' '); }
            else if (strCurrent.equals("(")){ stack.push(strCurrent); }
            else if (strCurrent.equals(")")){
                while (!stack.peek().equals("(")) {
                    sbPostfixExp.append(stack.pop()).append(' ');
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && !stack.peek().equals("(") &&
                precedence(strCurrent) <= precedence(stack.peek())){
                    sbPostfixExp.append(stack.pop()).append(' ');
                } 
                stack.push(strCurrent);
            }
        }
        while (!stack.isEmpty()){ sbPostfixExp.append(stack.pop()).append(' '); }
        scn.close();
        return sbPostfixExp.toString();
    }
    
    /**
     * 
     * @param operator : Operator to find precedence of
     * @return : Precedence of the operator
     * @throws IllegalArgumentException : Operator not supported
     */
    public static int precedence(String operator){
        if (operator.equals("^")){ return 7; }
        if (operator.equals("*") || operator.equals("/") || operator.equals("%")){
            return 6;}
        if (operator.equals("+") || operator.equals("-")){ return 4; }
        if (operator.equals(">") || operator.equals(">=") || operator.equals("<") ||
        operator.equals("<=")){ return 3; }
        if (operator.equals("==") || operator.equals("!=")){ return 2; }
        if (operator.equals("&&")){ return 2; }
        if (operator.equals("||")){ return 1; }
        throw new IllegalArgumentException("Operator not supported: " + operator);
    }
}

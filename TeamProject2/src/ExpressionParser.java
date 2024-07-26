/*
----------------------------------------------------------------------------------------------------------
    Name:		ExpressionParser
    Authors:	Ollie Peel, Rawan Alhachami
    Language:	Java
    Date:		2024-07-20
    Purpose:	The purpose of this program is to parse and evaluate an expression string using the
    evalExpression method. This method uses the other methods evaluate and precedence.
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Reason
    OSP		2024-07-20	Created the infixToPostfix and precedence methods, finished the precedence method.
    OSP     2024-07-23  Got rid of the infixToPostfix method, started the evalExpression method, created
    and finished the evaluate method
    RTA     2024-07-25  Finished evalExpression method
----------------------------------------------------------------------------------------------------------
*/

import java.util.Stack;

public class ExpressionParser {

    /**
     * Parses and evaluates an infix expression, returns the result
     * @param expression : The infix expression to be parsed and evaluated
     * @return : The resulting integer from the parsed and evaluated expression
     */
    public static int evalExpression(String expression){
        // Create separate stacks for integers and operators
        Stack<Integer> ints = new Stack<>();
        Stack<String> operators = new Stack<>();
        
        // Traverse the infix expression character by character (no spaces)
        for (int i = 0; i < expression.length(); i++){
            char curr = expression.charAt(i);
            // If current char is a number, first make sure all digits are accounted for
            if (Character.isDigit(curr)){
                StringBuilder numStr = new StringBuilder();
                int j = i;
                // Put entire number into string builder (e.g. 23 instead of 2 and 3 separately)
                while (j < expression.length() && Character.isDigit(expression.charAt(j))){
                    numStr.append(expression.charAt(j));
                    // Make sure the placement of i reflects possible further traversement
                    i = j;
                    j++;
                }
                // When all digits are accounted for, push current number into ints stack
                ints.push(Integer.valueOf(expression.charAt(j)));

            } else if (curr == '('){
                // Push '(' to the operaters stack
                operators.push(String.valueOf(i));

            } else if (curr == ')'){
                // Evaluate ints and operands in stack until '(' is encountered
                while (!operators.peek().equals("(")){
                    ints.push(evaluate(operators, ints));
                }
                // '(' is encountered, remove '(' from stack
                operators.pop();

            } else {
                // current char is an operator
                 while (!operators.isEmpty() && precedence(String.valueOf(curr)) <= precedence(operators.peek())) {
                       ints.push(evaluate(operators, ints));
                       operators.push(String.valueOf(curr));
                    }
                  }
                while (!operators.isEmpty()) {
                      ints.push(evaluate(operators, ints));
                 }
                   return ints.pop();
              }

    
                /* FIXME continue operator evaluation from here
                 * code so far is almost exactly the same as the ExpToBT method from project 3,
                 * so make sure that method's code and the algorithm from the website I sent line up as you continue
                 */
            }
        }
    }

    /**
     * Evaluates an expression made of the top operator of a stack and the top two integers of a stack
     * @param ops : A stack of operators
     * @param ints : A stack of integers
     * @return The result of the evaluation
     */
    public static int evaluate(Stack<String> ops, Stack<Integer> ints){
        String operator = ops.pop();
        Integer rightInt = ints.pop();
        Integer leftInt = ints.pop();

        switch (operator) {
            case "^":
                return ((int)Math.pow(leftInt, rightInt));
            case "*":
                return (leftInt * rightInt);
            case "/":
                return (leftInt / rightInt);
            case "%":
                return (leftInt % rightInt);
            case "+":
                return (leftInt + rightInt);
            case "-":
                return (leftInt - rightInt);
            case ">":
                return (leftInt > rightInt) ? 1 : 0;
            case ">=":
                return (leftInt >= rightInt) ? 1 : 0;
            case "<":
                return (leftInt < rightInt) ? 1 : 0;
            case "<=":
                return (leftInt <= rightInt) ? 1 : 0;
            case "==":
                return (leftInt == rightInt) ? 1 : 0;
            case "!=":
                return (leftInt != rightInt) ? 1 : 0;
            case "&&":
                return (((leftInt != 0) ? true : false) && ((rightInt != 0) ? true : false)) ? 1 : 0;
            case "||":
                return (((leftInt != 0) ? true : false) || ((rightInt != 0) ? true : false)) ? 1 : 0;
            default:
                throw new IllegalArgumentException("Operator not supported: " + operator);
        }
    }
    
    /**
     * Finds and returns the precedence of an operator. 7 is highest precedence, 1 is lowest
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

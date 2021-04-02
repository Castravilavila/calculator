package com.company;

public class ExpressionCalculator {
    private static int pos = -1, ch;
    private static String expression="";

    private ExpressionCalculator(){

    }

    public static double calculateExpression(final String str) throws Exception {
        pos=-1;
        expression=str;
        if (expression.isBlank() || expression.isEmpty()) return 0;
        return parseFullExpression();
    }

    private static void incrementPosition() {
        pos=pos+1;
        if (pos<expression.length()){
            ch=expression.charAt(pos);
        }else{
            ch=-1;
        }
    }

    private static boolean checkChar(int charToCheck) {
        while (ch == ' '){ incrementPosition();}
        if (ch == charToCheck) {
            incrementPosition();
            return true;
        }
        return false;
    }

    private static double parseFullExpression() throws Exception {
        incrementPosition();
        double number = parseExpressionIfSecondPriorityOperation();
        if (pos < expression.length()) throw new RuntimeException("Unexpected char: " + (char)ch);
        pos=-1;
        return number;
    }

    private static double parseExpressionIfSecondPriorityOperation() throws Exception {
        double number = parseExpressionIfFirstPriorityOperation();
        for (;;) {
            if      (checkChar('+')) number += parseExpressionIfFirstPriorityOperation(); // addition
            else if (checkChar('-')) number -= parseExpressionIfFirstPriorityOperation(); // subtraction
            else return number;
        }
    }

    private static double parseExpressionIfFirstPriorityOperation() throws Exception {
        double number = evaluateEveryCharFromExpression();
        for (;;) {
            if      (checkChar('*')) number *= evaluateEveryCharFromExpression(); // multiplication
            else if (checkChar('/')) number /= evaluateEveryCharFromExpression(); // division
            else return number;
        }
    }

    private static double evaluateEveryCharFromExpression() throws Exception {
        if (checkChar('+')) return evaluateEveryCharFromExpression(); // unary plus
        if (checkChar('-')) return -evaluateEveryCharFromExpression(); // unary minus


        int startPos = pos;
        double number= checkIfParanthesisExistInExpression();
        number=checkAndAssignIfCharIsNumber(startPos,number);
        return number;
    }

    private static double checkAndAssignIfCharIsNumber(int startPos, double number) throws Exception{
        if (number==-1) {
            if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                while ((ch >= '0' && ch <= '9') || ch == '.') incrementPosition();
                number = Double.parseDouble(expression.substring(startPos, pos));
            } else {
                throw new RuntimeException("Unexpected char: " + (char) ch);
            }
        }
        return number;
    }

    private static double checkIfParanthesisExistInExpression() throws Exception {
        double number=-1;
        if (checkChar('(')) { // parentheses
            number = parseExpressionIfSecondPriorityOperation();
            checkChar(')');
        }
        return number;
    }


}

package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Calc extends JFrame implements ActionListener
{
    JButton bPlus, bMinus, bMultiply, bDivide, bEqual, bClear, bOpenParanth, bCloseParanth, bDot;
    JButton b[]=new JButton[10];
    JTextField res;

    public Calc()
    {
        super("Calulator");
        JPanel p=createPanel();
        addNumberButtonsToPanel(p);
        addOperationButtonsToPanel(p);
        addTextFieldToPanel(p);
    }

    public void actionPerformed(ActionEvent ae) {
        JButton pb=(JButton)ae.getSource();

        try {
            if(!checkIfClearButtonWasClicked(pb) && !checkAndComputeIfEqualButtonWasClicked(pb)) {
                addTextToTextField(pb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel createPanel(){
        setLayout(new BorderLayout());
        JPanel p=new JPanel();
        p.setLayout(new GridLayout(5,5));
        return p;
    }

    private void addTextFieldToPanel(JPanel p) {
        res=new JTextField(10);
        add(p,BorderLayout.CENTER);
        add(res,BorderLayout.NORTH);
        setVisible(true);
        setSize(200,200);
    }

    public void addNumberButtonsToPanel(JPanel jp){
        for(int i=0;i<=9;i++)
        {
            b[i]=new JButton(i+"");
            jp.add(b[i]);
            b[i].addActionListener(this);
        }
    }
    private void addOperationButtonsToPanel(JPanel p) {
        bPlus =new JButton("+");
        p.add(bPlus);
        bPlus.addActionListener(this);

        bMinus =new JButton("-");
        p.add(bMinus);
        bMinus.addActionListener(this);

        bMultiply =new JButton("*");
        p.add(bMultiply);
        bMultiply.addActionListener(this);

        bDivide =new JButton("/");
        p.add(bDivide);
        bDivide.addActionListener(this);

        bEqual =new JButton("=");
        p.add(bEqual);
        bEqual.addActionListener(this);

        bClear =new JButton("C");
        p.add(bClear);
        bClear.addActionListener(this);

        bOpenParanth =new JButton("(");
        p.add(bOpenParanth);
        bOpenParanth.addActionListener(this);

        bCloseParanth =new JButton(")");
        p.add(bCloseParanth);
        bCloseParanth.addActionListener(this);

        bDot =new JButton(".");
        p.add(bDot);
        bDot.addActionListener(this);
    }

    private boolean checkAndComputeIfEqualButtonWasClicked(JButton pb) throws Exception {
        if(pb== bEqual) {
            String text=res.getText();
            double result = ExpressionCalculator.calculateExpression(text);
            res.setText(""+result);
            return true;
        }
        return false;
    }

    private boolean checkIfClearButtonWasClicked(JButton pb) {
        if(pb== bClear) {
            res.setText("");
            return true;
        }
        return false;
    }

    private void addTextToTextField(JButton pb) {
        String t = res.getText();
        t += pb.getText();
        res.setText(t);
    }

    public static void main(String arg[])
    {
        new Calc();
    }
}
package com.dmlab;

import com.dmlab.util.EmptyQueueException;
import com.dmlab.util.MyQueue;
import com.dmlab.util.MyStack;

import java.util.EmptyStackException;

public class StackCalculator {

    private MyStack<String> mStack;
    private MyQueue<String> mQueue;
    private MyStack<Integer> mStackCalc;

    private static final int TYPE_OPERATOR = 0;
    private static final int TYPE_BRACE = 1;
    private static final int TYPE_NUMBER = 2;

    public StackCalculator() {
        mStack = new MyStack<String>();
        mQueue = new MyQueue<String>();
        mStackCalc = new MyStack<Integer>();
    }

    /**
     * Solve the given infix form of equation
     *
     * @param infix the infix form of an equation
     *              e.g. 1 + 2 - ( 3 - 4 )
     * @return the result from the calculation of given equation
     */
    public int solve(String infix) throws EmptyQueueException {
        mStackCalc.clear();

        transform(infix);

        int num1, num2, ans;


        while (!mQueue.empty()) {
            if (typeOf(mQueue.peek()) == 0) {
                num2 = mStackCalc.pop();
                num1 = mStackCalc.pop();

                if (mQueue.peek().equals("+")) {
                    mQueue.poll();
                    ans = num1 + num2;
                } else if (mQueue.peek().equals("-")) {
                    mQueue.poll();
                    ans = num1 - num2;
                } else if (mQueue.peek().equals("*")) {
                    mQueue.poll();
                    ans = num1 * num2;
                } else {
                    mQueue.poll();
                    ans = num1 / num2;
                }
                mStackCalc.push(ans);
            } else {
                mStackCalc.push(Integer.valueOf(mQueue.poll()));
            }
        }

        return mStackCalc.pop();
    }

    public void transform(String infix) {
        mStack.clear();
        mQueue.clear();

        String[] str = infix.split(" ");
        int size = str.length;

        for (int i = 0; i < size; i++) {
            if (typeOf(str[i]) == 0) {
                while (!mStack.empty() && !mStack.peek().equals("(")) {
                    if (prior(mStack.peek()) >= prior(str[i])) {
                        mQueue.add(mStack.pop());
                    } else {
                        break;
                    }
                }
                mStack.push(str[i]);
            } else if (str[i].equals("(")) {
                mStack.push(str[i]);
            } else if (str[i].equals(")")) {
                while (!mStack.empty() && !mStack.peek().equals("(")) {
                    mQueue.add(mStack.pop());
                }
                if (mStack.peek().equals("(")) {
                    mStack.pop();
                }
            } else if (typeOf(str[i]) == 2) {
                mQueue.add(str[i]);
            }
        }
        while (!mStack.empty()) {
            mQueue.add(mStack.pop());
        }
    }

    private int typeOf(String entry) {
        if (entry.equals("+") || entry.equals("-") || entry.equals("*") || entry.equals("/")) {
            return TYPE_OPERATOR;
        } else if (entry.equals("(") || entry.equals(")")) {
            return TYPE_BRACE;
        } else {
            return TYPE_NUMBER;
        }
    }

    private int prior(String item) {
        if (item.equals("+") || item.equals("-")) {
            return 0;
        }
        return 1;
    }
}

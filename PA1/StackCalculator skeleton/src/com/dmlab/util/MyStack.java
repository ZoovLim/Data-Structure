package com.dmlab.util;

import java.util.EmptyStackException;

import com.dmlab.interfaces.Stack;

/**
 * Array-based Stack
 * The size of the internal array should be 128
 */
public class MyStack<E> implements Stack<E> {

    private E[] mData;
    private int mCursor;

    public MyStack() {
        clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        mData = (E[]) new Object[128];
        mCursor = -1;
    }

    @Override
    public void push(E item) throws RuntimeException {
        mCursor++;
        if (mCursor < 128) {
            mData[mCursor] = item;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public E pop() throws EmptyStackException {
        if (mCursor >= 0) {
            E it = mData[mCursor];
            mCursor--;
            return it;
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public E peek() throws EmptyStackException {
        if (mCursor >= 0 && mCursor < 128) {
            return mData[mCursor];
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public boolean empty() {
        return (mCursor < 0);
    }

}

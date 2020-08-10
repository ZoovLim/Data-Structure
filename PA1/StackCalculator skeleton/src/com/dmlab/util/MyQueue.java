package com.dmlab.util;

import com.dmlab.interfaces.Queue;

/**
 * Array-based Queue
 * The size of the internal array should be 128
 */
public class MyQueue<E> implements Queue<E> {

    private E[] mData;
    private int mCursor, mHead;

    public MyQueue() {
        clear();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        mData = (E[]) new Object[128];
        mCursor = -1;
        mHead = 0;
    }

    @Override
    public void add(E item) throws RuntimeException {
        mCursor++;
        if (mCursor < 128) {
            mData[mCursor] = item;
        } else if (mHead > 0) {
            for (int i = mHead; i < mCursor; i++) {
                mData[i - mHead] = mData[i - mHead + 1];
            }
            mCursor -= mHead;
            mHead = 0;
            mData[mCursor] = item;
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public E poll() throws EmptyQueueException {
        if (mHead < mCursor) {
            E it = mData[mHead];
            mHead++;
            return it;
        } else if (mHead == mCursor) {
            E it = mData[mHead];
            mCursor = -1;
            mHead = 0;
            return it;
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (mCursor >= mHead && mCursor < 128) {
            return mData[mHead];
        } else {
            throw new EmptyQueueException();
        }
    }

    @Override
    public boolean empty() {
        return (mCursor < mHead);
    }

}

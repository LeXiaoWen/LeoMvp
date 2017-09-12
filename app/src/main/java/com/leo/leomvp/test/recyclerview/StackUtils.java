package com.leo.leomvp.test.recyclerview;

import java.util.Stack;

/**
 * Created by Leo on 2017/8/24.
 */

public class StackUtils<T> {
    public Stack<T> mStack = new Stack<>();


    public void addStack(T t){
        mStack.push(t);
    }
    public T peekStack(){
        if (!mStack.isEmpty()){
            return mStack.peek();
        }
        return null;
    }
    public T popStack(){
        return mStack.pop();
    }

}

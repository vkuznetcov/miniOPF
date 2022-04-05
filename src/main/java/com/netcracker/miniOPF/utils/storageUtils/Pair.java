package com.netcracker.miniOPF.utils.storageUtils;

public class Pair<K, V>
{
    private K leftValue;
    private V rightValue;

    public Pair()
    {
        leftValue=null;
        rightValue=null;
    }

    public Pair(K leftValue, V rightValue)
    {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public K getLeftValue()
    {
        return leftValue;
    }

    public void setLeftValue(K leftValue)
    {
        this.leftValue = leftValue;
    }

    public V getRightValue()
    {
        return rightValue;
    }

    public void setRightValue(V rightValue)
    {
        this.rightValue = rightValue;
    }
}

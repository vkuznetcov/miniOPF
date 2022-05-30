package com.netcracker.miniOPF.utils.storageUtils;

import java.util.List;

public class ListUtils
{

    public static <T> void checkListIsEmptyOrNull(List<T> values) throws NullPointerException
    {
        if(values == null){
            throw new NullPointerException("List is null");
        }
        else if(values.isEmpty()){
            throw new NullPointerException("List is empty");
        }
    }
}

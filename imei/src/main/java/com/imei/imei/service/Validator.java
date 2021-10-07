package com.imei.imei.service;


import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class Validator
{
    static Integer num = 0;

    static int doSum(int n){ return n==0 ? 0 : n%10 + doSum(n/10); }

    public static boolean isValid(String value)
    {
        int sum = 0;

        for(int i = value.length()-1; i >= 0; i--)
        {
            int tmp = 0;
            if(i % 2 != 0) tmp = Integer.parseInt(String.valueOf(value.charAt(i))) * 2;
            else    tmp = Integer.parseInt(String.valueOf(value.charAt(i)));
            sum+=doSum(tmp);
        }
        return sum%10==0 ? true : false;
    }

    static int correctIme(String value)
    {
        if(num < 9) num++;
        else return -1;


        System.out.println("NUmber: " + num);
        StringBuilder builder = new StringBuilder(value);
        builder.replace(value.length()-1, value.length(), "");
        builder.append(num.toString());

        return isValid(builder.toString()) ? num : correctIme(builder.toString());

    }

    public static String getImei(String imeiNumber)
    {
        Function<String, Boolean> doValidation = Validator::isValid;
        boolean flag = (Boolean)doValidation.apply(imeiNumber);
        if(!flag){
            Function<String, Integer> makeValid = Validator::correctIme;
            Integer ans = makeValid.apply(imeiNumber);
            num = 0;
            if(ans == -1)   return("NOT POSSIBLE TO CONVERT THIS NUMBER ");
            return("IMEI NUMBER IS INVALID REPLACE LAST NUMBER WITH: " + ans);
        }else{
            num = 0;
            return( "IMEI NUMBER IS VALID" );
        }
    }

}

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
        int len = value.length();
        Long n = Long.parseLong(value);

        if (len != 15)
            return false;

        int sum = 0;
        for (int i = len; i >= 1; i--)
        {
            int d = (int)(n % 10);
            if (i % 2 == 0)
                d = 2 * d;
            sum += doSum(d);
            n = n / 10;
        }

        return (sum % 10 == 0);
    }

    static int correctIme(String value)
    {
        if(num < 9) num++;
        else return -1;


        
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
            if(ans == -1)   return("IMEI NUMBER IS INVALID");
            return("IMEI NUMBER IS INVALID REPLACE LAST NUMBER WITH: " + ans);
        }else{
            num = 0;
            return( "IMEI NUMBER IS VALID" );
        }
    }

}

package com.starter.mugisha.util;

import com.starter.mugisha.exceptions.BadRequestException;

public interface Constants {
    public String DEFAULT_PAGE_NUMBER = "0";
    public String DEFAULT_PAGE_SIZE = "100";
    public int MAX_PAGE_SIZE=1000;
    public static void validatePageNumberAndPageSize(int pageNumber,int pageSize){
        if(pageNumber<0){
            throw new BadRequestException("Page number is less than zero");
        }
        if(pageSize>Constants.MAX_PAGE_SIZE){
            throw new BadRequestException("page size is greater then "+Constants.MAX_PAGE_SIZE);
        }
    }
}

package com.com.atguigu.bookstore;

import java.util.List;

public class Page<T> {
    //how many pages
    private int pageNo;
    private List<T> list;
    //how many records in one page
    private int pageSize=3;
    //total number of records
    private long totalItemNumber;

    public Page(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        if(pageNo<0)
            pageNo=1;
        if(pageNo>getTotalPageNumber()){
            pageNo=getTotalPageNumber();
        }
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public int getTotalPageNumber(){
        int totalPageNumber=(int)totalItemNumber/pageSize;
        if (totalPageNumber% pageSize!=0)
            totalPageNumber++;
        return totalPageNumber;
    }

    public void setTotalItemNumber(long totalItemNumber){
        this.totalItemNumber=totalItemNumber;
    }

    public boolean isHasNext(){
        if(getPageNo()<getTotalPageNumber()){
            return true;
        }
        return false;
    }

    public boolean isHasPrev(){
        if(getPageNo()>1){
            return true;
        }
        return false;

    }

    public int getPrevPage() {
        if(isHasPrev()){
            return getPageNo()-1;
        }
        return getPageNo();

    }

    public int getNextPage(){
        if(isHasNext()){
            return getPageNo()+1;
        }
        return getPageNo();
    }

}

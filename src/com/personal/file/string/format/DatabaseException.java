package com.personal.file.string.format;

/**
 * Created by mj on 2017/10/12.
 * 注意：在格式化中：d代表整数，f代表小数，s代表字符串
 */
public class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String message){
        super(String.format("(t%d, q%d) %s", transactionID, queryID, message));
    }

    public static void main(String[] args) {
        try{
            throw new DatabaseException(3, 7, "Write failed");
        } catch (DatabaseException e) {
            System.out.println(e);
        }
    }
}

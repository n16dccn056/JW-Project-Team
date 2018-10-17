/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Admin
 */
public class Constant {
    public static final String QUERY_LOAI_SAN_PHAM ="select * from";
    public static final String QUERY_SAN_PHAM ="SELECT * FROM PRODUCT";
    public static final String QUERY_GET_PURCHASE_ORDER = "SELECT MAX(PURCHASE_ORDER.PURCHASE_ORDER_ID) FROM PURCHASE_ORDER";
   public static final String QUERY_SET_PURCHASE_ORDER ="insert into PURCHASE_ORDER(PURCHASE_ORDER_ID,STAFF_ID,CUSTOMER_NAME,CUSTOMER_PHONENUMBER) values(?,?,?,?)";
    public static final String QUERY_SET_DETAIL_ORDER ="insert into DETAIL_ORDER(PURCHASE_ORDER_ID,PRODUCT_ID,PRODUCT_QUANTITY,PRODUCT_TOTAL_MONEY) values(%s,%s,%s,%s)";
}

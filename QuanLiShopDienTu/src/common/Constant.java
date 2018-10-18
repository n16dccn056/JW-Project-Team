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
    public static final String QUERY_LOAI_SAN_PHAM ="select * from PRODUCT_TYPE";
    public static final String QUERY_THUONG_HIEU ="select * from TRADEMARK";
    public static final String QUERY_SAN_PHAM ="select * from PRODUCT";
    public static final String QUERY_TON_KHO ="select * from product where product_quantity >= 10 order by product_quantity DESC";
    public static final String QUERY_HET_HANG ="select * from product where product_quantity <= 3 order by product_quantity desc";
    public static final String QUERY_BAN_CHAY ="SELECT TOP 5 PRODUCT.PRODUCT_ID,PRODUCT.PRODUCT_NAME,PRODUCT.PRODUCT_PRICE,PRODUCT.PRODUCT_QUANTITY,PRODUCT.PRODUCT_TYPE_ID,PRODUCT.STATE, SUM(DETAIL_ORDER.PRODUCT_QUANTITY) FROM PRODUCT,DETAIL_ORDER WHERE PRODUCT.PRODUCT_ID = DETAIL_ORDER.PRODUCT_ID GROUP BY PRODUCT_NAME,PRODUCT.PRODUCT_ID,PRODUCT.PRODUCT_PRICE,PRODUCT.PRODUCT_QUANTITY,PRODUCT.PRODUCT_TYPE_ID,PRODUCT.STATE ORDER BY SUM(DETAIL_ORDER.PRODUCT_QUANTITY) DESC";
}

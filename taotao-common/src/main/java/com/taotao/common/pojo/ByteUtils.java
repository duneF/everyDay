package com.taotao.common.pojo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午8:37 2017/12/8
 * @Modified By:
 */
public class ByteUtils {
    private static byte[] bytes;
    private static ByteArrayOutputStream bo;
    private static ObjectOutputStream oo;
    private static ByteArrayInputStream bi;
    private static ObjectInputStream oi;

    public static byte[] ObjectToByte(java.lang.Object obj) {
        bytes = null;
        try {
            // object to bytearray
            bo = new ByteArrayOutputStream ();
            oo = new ObjectOutputStream ( bo );
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;
        try {
            // bytearray to object
            bi = new ByteArrayInputStream ( bytes );
            oi = new ObjectInputStream ( bi );

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

}

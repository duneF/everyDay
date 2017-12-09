import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class testRedisO {

    private static String abc;
    private static Stu stu;
    private static byte[] b;

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("192.168.99.134", 7002);
        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
        //  jedis.auth("123456");
        jedis.set("TT2", "AAAB");
        String string = jedis.get("TT2");
        System.out.println(string);
//        jedis.set("str","1503");
//        stu = new Stu("1", "大毛");
//        jedis.set("stu1".getBytes(),stu.toString().getBytes());
//        b = jedis.get("stu1".getBytes());
//        ByteToObject(b);
        //关闭jedis对象
        jedis.close();
        pool.close();
    }
    public static byte[] ObjectToByte(java.lang.Object obj) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
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
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

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

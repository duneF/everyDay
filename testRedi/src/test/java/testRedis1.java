import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class testRedis1 {

    private static String abc;

    static  public void mTest(){
        //连接redis服务器得到一个操作redis的对象
        Jedis jedis = new Jedis("191.168.99.132", 6379);
        jedis.auth("123456");

        //通过对象调用api操作5钟数据
        jedis.set("abc", "ccc");
        abc = jedis.get("abc");
        System.out.println(abc);
        jedis.close();
    }
  static   public void testJedisPool() {
        //创建jedis连接池
        JedisPool pool = new JedisPool("192.168.99.132", 6379);
        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
      //  jedis.auth("123456");
      jedis.set("key","AAA");
        String string = jedis.get("key");
        System.out.println(string);
        //关闭jedis对象
        jedis.close();
        pool.close();
    }
    public static void main(String[] args) {
//        //连接redis服务器得到一个操作redis的对象
//        Jedis jedis= new Jedis("191.168.99.132",6379);
//  //      jedis.auth("123456");
//
//        //通过对象调用api操作5钟数据
//        jedis.set("abcaaaa","ccc");
//        jedis.close();
//     //   jedis.connect();
       // testJedisPool();
        mTest();

    }
}

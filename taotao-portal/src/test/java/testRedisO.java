import redis.clients.jedis.Jedis;

public class testRedisO {
    public static void main(String[] args) {
        //连接redis服务器得到一个操作redis的对象
        Jedis jedis= new Jedis("191.168.99.132",6379);
      //  jedis.auth("123456");

        //通过对象调用api操作5钟数据
        jedis.set("abc","ccc");
        jedis.close();
     //   jedis.connect();

    }
}

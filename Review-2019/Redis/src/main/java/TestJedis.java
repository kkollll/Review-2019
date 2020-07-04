import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;

public class TestJedis {

    private static final String HOST = "192.168.116.131";

    @Test
    public void testJedis() {
        Jedis jedis = new Jedis(HOST, 6379);
        System.out.println(jedis.get("name"));
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);

        JedisPool pool = new JedisPool(config, HOST, 6379);

        System.out.println(pool.getResource().get("name"));

        pool.close();
    }


    @Test
    public void testShard() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);

        ArrayList<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(new JedisShardInfo(HOST, 6379));
        list.add(new JedisShardInfo(HOST, 6380));
        list.add(new JedisShardInfo(HOST, 6381));
        ShardedJedisPool pool = new ShardedJedisPool(config, list);
        for (int i = 0; i < 10; i++) {
            pool.getResource().set("u" + i, "" + i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(pool.getResource().get("u" + i));
        }
        pool.close();
    }

    @Test
    public void testCodis() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);

        JedisPool pool = new JedisPool(config, HOST, 19000);

        Jedis jedis = pool.getResource();
        for (int i = 0; i < 100000; i++) {
            jedis.set("0618_" + i, "0620_" + i);
        }
        pool.close();
    }

}

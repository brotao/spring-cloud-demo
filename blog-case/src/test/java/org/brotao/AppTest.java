package org.brotao;

import static org.junit.Assert.assertTrue;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import lombok.Data;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testArray() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

//        Integer[] a = new Integer[0];
        Integer[] integers1 = integers.toArray(new Integer[0]);

        System.out.println(Arrays.toString(integers1));
    }

    @Test
    public void testIterator() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");

        for (String item : list2) {
            if ("2".equals(item)) {
                list2.remove(item);
            }
        }

        System.out.println(list2);

    }

    @Test
    public void testSwitchString() {
        // throw NullPointerException
        String str = null;
		/*switch (str) {
			// 肯定不是进入这里
			case "sth":
				System.out.println("it's sth");
				break;
			// 也不是进入这里
			case "null":
				System.out.println("it's null");
				break;
			// 也不是进入这里
			default:
				System.out.println("default");
		}*/
    }

    @Test
    public void testForEach() {
        Integer[] ints = new Integer[0];
        for (Integer anInt : ints) {
            System.out.println(anInt);
        }
    }

	@Test
	public void testSplit() {
		String str= "/aaa/20220218/TestCSV.csv.zip";
		for (String s : str.split("/")) {
			System.out.println(s);
		}
	}

    @Test
    public void testMSSQL() throws ClassNotFoundException, SQLException {
        ResultSet results = null;
        PreparedStatement preparedStatement = null;
        Connection conn = null;

        String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(SQL_SERVER_DRIVER);

        String dbString = "jdbc:sqlserver://10.101.0.109:1433;applicationIntent=ReadOnly;databaseName=TaskConfig;user=TaskConfig;password=TaskConfig@qwe;";

        conn = DriverManager.getConnection(dbString);
        preparedStatement = conn.prepareStatement("select 1 union select 2");

        results = preparedStatement.executeQuery();
    }

    @Test
    public void testLogicAnd() {
        int a = 1;
        int b = 2;
        String c = "aaa";
        String d = null;
        if ((a == b) & d.equals(c)) {
            System.out.println("aaa");
        }
    }

    @Test
    public void testTypeConversion() {
        byte a = (byte) 0xFF;
        System.out.println(a);

        System.out.println((int) a);

        System.out.println(0xFF);
    }

    @Test
    public void replace() {
        System.out.println("   ".replace("", ""));
    }

    @Test
    public void test() {
        class Person {
            Integer id;

            public Person(Integer id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                        .add("id=" + id)
                        .toString();
            }
        }

        AtomicInteger num = new AtomicInteger(1);

        List<Person> people = Arrays.asList(new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()),
                new Person(num.getAndIncrement()));
        ArrayList<Person> list = new ArrayList<>(people);

        new Thread(() -> {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.get(i).id  = list.get(i).id + 10;
            }

        }).start();

        List<Person> collect = list.stream().filter(e -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            return e.id % 2 == 0;

        }).collect(Collectors.toList());

        System.out.println(collect);
    }


    @Data
    class WebLog {
        /**
         * 操作描述
         */
        private String description;

        /**
         * 操作用户
         */
        private String username;

        /**
         * 操作时间
         */
        private Long startTime;

        /**
         * 消耗时间
         */
        private Integer spendTime;

        /**
         * 根路径
         */
        private String basePath;

        /**
         * URI
         */
        private String uri;

        /**
         * URL
         */
        private String url;

        /**
         * 请求类型
         */
        private String method;

        /**
         * IP地址
         */
        private String ip;

        /**
         * 请求参数
         */
        private Object parameter;

        /**
         * 返回结果
         */
        private Object result;

    }

    @Test
    public void testBigdecimal() {
        BigDecimal a = new BigDecimal("9999999999999999999999999999999999999999.99");
        BigDecimal b = new BigDecimal("2222222222222222222222222222222222222222.22");
        BigDecimal c = BigDecimal.valueOf(Double.MAX_VALUE);
        BigDecimal res = a.add(c).add(c).add(c).add(c);
        System.out.println(res.doubleValue()+1);
    }

    @Test
    public void testCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger cnt = new AtomicInteger(0);
        for (int i = 0; i < 100000; i++) {
            executorService.execute(() -> {
                LockSupport.parkNanos(1000*1000*8L);
                cnt.getAndIncrement();
            });
        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt.get());

    }



}

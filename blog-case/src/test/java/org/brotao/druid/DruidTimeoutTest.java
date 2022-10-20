package org.brotao.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * @Author luotao
 * @Date 2022-07-13 15:47
 * @Description druid 数据库连接池 超时配置测试
 */

public class DruidTimeoutTest {

    @Test
    public void test() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/devdb?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        ds.setMaxActive(10);
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMaxActive(10);
        ds.setMinIdle(5);
        ds.setInitialSize(1);
        ds.setMaxWait(10000);
        ds.setValidationQuery("select 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(true);
        ds.setValidationQueryTimeout(3);
        ds.setTestOnReturn(false);
        ds.setTimeBetweenEvictionRunsMillis(5000);
        ds.setDbType(JdbcConstants.MYSQL);
        ds.setQueryTimeout(2);
        ds.init();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.setQueryTimeout(7);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from page_info limit 10");
    }

    @Test
    public void test2() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/devdb?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        ds.setMaxActive(10);
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMaxActive(10);
        ds.setMinIdle(5);
        ds.setInitialSize(1);
        ds.setMaxWait(10000);
        ds.setValidationQuery("select 1");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(true);
        ds.setValidationQueryTimeout(3);
        ds.setTestOnReturn(false);
        ds.setTimeBetweenEvictionRunsMillis(5000);
        ds.setDbType(JdbcConstants.MYSQL);
        ds.setQueryTimeout(7);
        ds.init();

        //定义事务隔离级别，传播行为，
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setTimeout(60);

        DataSourceTransactionManager manager = new DataSourceTransactionManager(ds);

        TransactionStatus transaction = manager.getTransaction(def);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.setQueryTimeout(2);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from page_info limit 10");

        manager.commit(transaction);
    }
}

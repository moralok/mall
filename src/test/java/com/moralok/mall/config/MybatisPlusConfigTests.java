package com.moralok.mall.config;

import com.moralok.mall.dao.UmsUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author moralok
 * @since 2020/9/7 3:22 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MybatisPlusConfigTests {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Test
    public void testSqlSessionFactory() {
        // 和使用 FactoryBean 创建并不相同吗
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UmsUserMapper umsUserMapper0 = sqlSession.getMapper(UmsUserMapper.class);
        log.info("Spring 容器中的 Mapper 代理对象和 SqlSession.getMapper 得到的代理对象是否相同，结果 {}", umsUserMapper0.equals(this.umsUserMapper));
        log.info("1号用户 {}", umsUserMapper0.selectById(1));
    }
}

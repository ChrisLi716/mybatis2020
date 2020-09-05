import com.mybatis.dao.mapper.CacheMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestCacheSql {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试Cache
     */
    @Test
    public void testCache() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            CacheMapper cacheMapper = openSession.getMapper(CacheMapper.class);

            // 第一次查询
            Employee emp = cacheMapper.getEmpById(1);
            System.out.println(emp.toString());

            openSession.clearCache();

            // 第二次查询
            Employee emp2 = cacheMapper.getEmpById(1);
            System.out.println(emp2.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            closeSession(openSession);
        }
    }

    /**
     * 测试Cache失效
     * sqlsession不是同一个，则一级缓存失效
     */
    @Test
    public void testCacheInvalid() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession_1 = null;
        SqlSession openSession_2 = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession_1 = sqlSessionFactory.openSession(true);
            CacheMapper cacheMapper1 = openSession_1.getMapper(CacheMapper.class);

            // 第一次查询
            Employee emp = cacheMapper1.getEmpById(1);
            System.out.println(emp.toString());

            openSession_2 = sqlSessionFactory.openSession(true);
            CacheMapper cacheMapper2 = openSession_2.getMapper(CacheMapper.class);

            // 第二次查询
            Employee emp2 = cacheMapper2.getEmpById(1);
            System.out.println(emp2.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            closeSession(openSession_1, openSession_2);
        }
    }


    /**
     * 关闭SqlSession
     */
    private void closeSession(SqlSession... sqlSession) {
        if (sqlSession != null) {
            for (SqlSession session : sqlSession) {
                if (null != session) {
                    session.close();
                }
            }
        }
    }

}

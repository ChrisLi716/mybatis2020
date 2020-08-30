import com.mybatis.dao.mapper.EmployeeAnnotationMapper;
import com.mybatis.dao.mapper.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class HelloWorld2 {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试 mapUnderscoreToCamelCase 为true
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById2(1);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


    /**
     * 测试默认别名
     */
    @Test
    public void testDefaultAlias() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.testDefaultAlias(1);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }


    /**
     * 测试指定别名
     */
    @Test
    public void testSpecifiedAlias() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.testSpecifiedAlias(1);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }

    /**
     * 多数据库标识DatabaseIdProvider
     */
    @Test
    public void testDatabaseIdProvider() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.testDatabaseIdProvider(1);
            System.out.println(employeeMapper.getClass());
            System.out.println(employee);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }

    @Test
    public void testAnnotationMapper() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeAnnotationMapper employeeAnnotationMapper = openSession.getMapper(EmployeeAnnotationMapper.class);
            Employee employee = employeeAnnotationMapper.getEmployeeById(1);
            System.out.println(employeeAnnotationMapper.getClass());
            System.out.println(employee);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (null != openSession) {
                openSession.close();
            }
        }
    }
}

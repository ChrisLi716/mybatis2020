import com.mybatis.dao.mapper.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisHelloWorld {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
     * 3将sql映射文件注册在全局配置文件中
     * 4、写代码：
     * 1）、根据全局配置文件得到SqlSessionFactory；
     * 2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查 一个sqlSession就是代表和数据库的一次会话，用完关闭
     * 3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // 2、获取sqlSession实例，能直接执行已经映射的sql语句
        // sql的唯一标识：statement Unique identifier matching the statement to use.
        // 执行sql要用的参数：parameter A parameter object to pass to the statement.

        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            // 加上namesapce abc 可以防止调用同名方法时冲突
            Employee employee = openSession.selectOne("abc.getEmpById", 1);

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
     * 通过接口绑定来查询数据
     *
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = employeeMapper.getEmployeeById(1);
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
}

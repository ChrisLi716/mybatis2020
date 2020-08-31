import com.mybatis.dao.mapper.EmployeeMapper2;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MapperTest2 {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试resultMap
     */
    @Test
    public void testResultMap() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper2 employeeMapper2 = openSession.getMapper(EmployeeMapper2.class);
            Map<Integer, Employee> employeeMap = employeeMapper2.getEmployeeByGenderReturnMap("F");
            openSession.commit();
            for (Map.Entry<Integer, Employee> entry : employeeMap.entrySet()) {
                System.out.println(entry.getKey() + ", " + entry.getValue());
            }
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
     * 测试resultMap级联查询
     */
    @Test
    public void testAssociateResultMap() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper2 employeeMapper2 = openSession.getMapper(EmployeeMapper2.class);
            Employee employee = employeeMapper2.getEmpAndDept(1);
            openSession.commit();
            System.out.println(employee.toString());
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

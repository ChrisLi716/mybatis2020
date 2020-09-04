import com.mybatis.dao.mapper.DepartmentMapper;
import com.mybatis.dao.mapper.EmployeeMapper2;
import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
     * 通过级联属性的方式进行联合查询
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


    /**
     * 使用association进入级联查询
     */
    @Test
    public void testAssociateResultMap2() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper2 employeeMapper2 = openSession.getMapper(EmployeeMapper2.class);
            Employee employee = employeeMapper2.getEmpAndDept2(1);
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

    /**
     * 使用association进行分步查询
     */
    @Test
    public void testEmpDeptBySteps() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            EmployeeMapper2 employeeMapper2 = openSession.getMapper(EmployeeMapper2.class);
            Employee employee = employeeMapper2.getEmpById(4);
            openSession.commit();
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
     * 使用collection进行关联对象的集合查询
     */
    @Test
    public void testDeptWithEmps() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = openSession.getMapper(DepartmentMapper.class);
            Department dept = departmentMapper.getDeptById2(1);
            openSession.commit();
            System.out.println(dept.toString());
            System.out.println(dept.getEmps().toString());
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
     * 使用collection进行关联对象的集合分步查询
     */
    @Test
    public void testDeptWithEmpsBySteps() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = openSession.getMapper(DepartmentMapper.class);
            Department dept = departmentMapper.getDeptByIdStep(2);
            openSession.commit();
            System.out.println(dept.toString());
            System.out.println(dept.getEmps().toString());
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
     * 使用discriminator进行关联对象查询
     */
    @Test
    public void testEmpByUsingDiscriminator() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            EmployeeMapper2 employeeMapper2 = openSession.getMapper(EmployeeMapper2.class);
            List<Employee> emps = employeeMapper2.getEmpByIdwithDiscriminator();
            for (Employee emp : emps) {
                System.out.println(emp.toString());
                if (null != emp.getDept()) {
                    System.out.println(emp.getDept().toString());
                }
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

    public static void main(String[] args) {
    }

}

import com.mybatis.dao.mapper.DynamicSqlMapper;
import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class TestDynamicSql {

    private SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 测试IF
     */
    @Test
    public void testIF() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            // Employee employee = new Employee(1, "Chris", "sss@1734.com", "F");
            Employee employee = new Employee("Chris", "sss@1734.com", "F");
            List<Employee> emps = dynamicSqlMapper.getempsbyconditionIf(employee);
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试Trim
     */
    @Test
    public void testTrim() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("Chris");
            employee.setEmail("sss@1734.com");

            List<Employee> emps = dynamicSqlMapper.getempsbyconditionTrim(employee);
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试Choose
     */
    @Test
    public void testChoose() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            List<Employee> emps = dynamicSqlMapper.getempsbyconditionChoose(employee);
            for (Employee emp : emps) {
                System.out.println(emp.toString());
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
     * 测试set
     */
    @Test
    public void testSet() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setEmail("chris@gmail.com");

            int count = dynamicSqlMapper.updateEmp(employee);
            System.out.println("update lines account:" + count);
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
     * 测试foreach
     */
    @Test
    public void testForeach() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            List<Employee> employees = dynamicSqlMapper.getEmpsByConditionForeach(Arrays.asList(1, 3, 4));
            for (Employee employee : employees) {
                System.out.println(employee.toString());
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
     * 测试foreach insert
     */
    @Test
    public void testForeachInsert() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            Employee emp1 = new Employee("S4", "s4@gmail.com", "F", new Department(1));
            Employee emp2 = new Employee("S5", "s5@gmail.com", "M", new Department(2));
            Employee emp3 = new Employee("S6", "s6@gmail.com", "F", new Department(2));
            int count = dynamicSqlMapper.addEmpsInBatchInMySql(Arrays.asList(emp1, emp2, emp3));
            System.out.println("new added employee count:" + count);
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
     * 测试bind
     */
    @Test
    public void testBind() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            Employee emp = new Employee();
            // 查询名称中包括a的员工记录
            emp.setLastName("a");

            List<Employee> emps = dynamicSqlMapper.getEmpByBind(emp);
            for (Employee employee : emps) {
                System.out.println(employee.toString());
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
     * 测试foreach insert
     */
    @Test
    public void testIncludeSQL() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        SqlSession openSession = null;
        try {
            sqlSessionFactory = getSqlSessionFactory();
            openSession = sqlSessionFactory.openSession(true);
            DynamicSqlMapper dynamicSqlMapper = openSession.getMapper(DynamicSqlMapper.class);
            Employee emp1 = new Employee("S4", "s4@gmail.com", "F", new Department(1));
            Employee emp2 = new Employee("S5", "s5@gmail.com", "M", new Department(2));
            Employee emp3 = new Employee("S6", "s6@gmail.com", "F", new Department(2));
            int count = dynamicSqlMapper.addEmpsWithCondistionInclude(Arrays.asList(emp1, emp2, emp3));
            System.out.println("new added employee count:" + count);
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

package cn.ly.sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.ly.utils.JdbcUtil;

import com.mysql.jdbc.Driver;


public class SqlTest {
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
		//DriverManager.registerDriver(new Driver()); 会导致重复注册,采用反射解决->解耦和方式注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		//url可以加参数->字符编码
		//connection 可以操作事务 rollback() commit()
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","985910");
		
		//1 执行sql(dml->executeUpdate,dql->executeQuery,任意语句->execute) 
		//2 批处理 addBatch executeBatch clearBatch
		//PreparedStatement CallableStatement(可以获取存储过程)
		//可获得滚动结果集 定位，向前遍历... connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement st=connection.createStatement();
		
		//ResultSet最开始在第一行之前，通过next移动
		//getInt|String|...(int columnIndex | String columnName) 不知道类型时->getObject 
		String sqlString="select * from user";
		ResultSet rs=st.executeQuery(sqlString);
		while (rs.next()) {                 //rs中只有一条数据时，用if即可
			int id=rs.getInt("id");
			String nameString=rs.getString("name");
			System.out.println("id->"+id+" name->"+nameString);
			
		}
		String updateString="update user set salary=10000";
		int ret=st.executeUpdate(updateString); //非0表示成功 0表示没成功 而不表示影响的记录数
		System.out.println("ret->"+ret); 
		
		//释放资源，关闭连接
		rs.close();
		st.close();
		connection.close(); //很消耗资源，必须即是关闭，否则可能会卡死。最好写在finnally里
		
	}
	@Test
	public void test() throws SQLException{
		Statement statement=JdbcUtil.getStateMent();
		
		if(statement!=null){
			int ret=statement.executeUpdate("insert into dept values(null,'sale');");
			System.out.println("ret->"+ret);
		}
		
		
		JdbcUtil.release();
	}
	
}

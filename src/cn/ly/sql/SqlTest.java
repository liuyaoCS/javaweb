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
		
		//DriverManager.registerDriver(new Driver()); �ᵼ���ظ�ע��,���÷�����->����ͷ�ʽע������
		Class.forName("com.mysql.jdbc.Driver");
		
		//url���ԼӲ���->�ַ�����
		//connection ���Բ������� rollback() commit()
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","985910");
		
		//1 ִ��sql(dml->executeUpdate,dql->executeQuery,�������->execute) 
		//2 ������ addBatch executeBatch clearBatch
		//PreparedStatement CallableStatement(���Ի�ȡ�洢����)
		//�ɻ�ù�������� ��λ����ǰ����... connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement st=connection.createStatement();
		
		//ResultSet�ʼ�ڵ�һ��֮ǰ��ͨ��next�ƶ�
		//getInt|String|...(int columnIndex | String columnName) ��֪������ʱ->getObject 
		String sqlString="select * from user";
		ResultSet rs=st.executeQuery(sqlString);
		while (rs.next()) {                 //rs��ֻ��һ������ʱ����if����
			int id=rs.getInt("id");
			String nameString=rs.getString("name");
			System.out.println("id->"+id+" name->"+nameString);
			
		}
		String updateString="update user set salary=10000";
		int ret=st.executeUpdate(updateString); //��0��ʾ�ɹ� 0��ʾû�ɹ� ������ʾӰ��ļ�¼��
		System.out.println("ret->"+ret); 
		
		//�ͷ���Դ���ر�����
		rs.close();
		st.close();
		connection.close(); //��������Դ�����뼴�ǹرգ�������ܻῨ�������д��finnally��
		
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

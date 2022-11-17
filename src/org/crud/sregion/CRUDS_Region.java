package org.crud.sregion;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import oracle.net.aso.p;

//CRUD: CREATE, READ, UPDATE, DELETE

public class CRUDS_Region {
	
	@SuppressWarnings("unused")
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	//oracle.jdbc.driver.OracleDriver.class
	private static String URL = "jdbc:oracle:thin:@localhost:1522:orcl";
	
	public static void connectDataBase() throws IOException, SQLException{
		try {
			
			Class.forName(driver).newInstance();
			System.out.println("EL DRIVER SE HA CARGADO CORRECTAMENTE: ojdbc6.jar");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			// TODO: handle exception
		}
		try {
			connection = DriverManager.getConnection(URL,"System", "admin");
			System.out.println("CONEXION EXITOSA: oracle 11g");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void insertS_Region(int id, String name) throws IOException, SQLException {
		
		try {
			connectDataBase();
			//
			String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			System.out.println("INSERTADO EL REGISTRO: "+ id + "," + name);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
	public static void updateS_Region(String name, int id) throws IOException, SQLException {
		
		try {
			connectDataBase();
			//
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			System.out.println("ACTUALIZADO EL REGISTRO: "+ id + "," + name);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
	public static void deleteS_Region(int id) throws IOException, SQLException {
		
		try {
			connectDataBase();
			//
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			System.out.println("ELIMINADO EL REGISTRO: "+ id);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
	public static void selectS_Region(int id) throws IOException, SQLException{
		
		try {
			connectDataBase();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet =ps.executeQuery();
			if(rSet.next()) {
				System.out.println(rSet.getInt("id")+", "+ rSet.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	
public static void selectALLS_Region() throws IOException, SQLException{
		
		try {
			connectDataBase();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rSet =ps.executeQuery();
			while(rSet.next()) {
				System.out.println(rSet.getInt("id")+", "+ rSet.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		
	}

public static void invocaProcedureS_Region(int id, String name) throws IOException, SQLException{
	
	try {
		connectDataBase();
		String sql = "CALL proc(?,?)";
	    CallableStatement cs = connection.prepareCall(sql);
	    cs.setInt(1, id);
	    cs.setString(2, name);
	    cs.execute();
 	    System.out.println("SE EJECUTO CORRECTAMENTE EL PRECEDIMIENTO!!");
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Exception: " + e.getMessage());
	}
	
}
	
	public static void main(String[] args) throws IOException, SQLException {
		//insertS_Region(14,"NAYARIT");
		//updateS_Region("NAYARIT EL GRANDE", 13);
		//deleteS_Region(9);
		//selectS_Region(8);
		//selectALLS_Region();
		invocaProcedureS_Region(17, "QUINTANA ROO");
	}
	
}

package DBlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.Encryption;
import utils.ClassFactory;
import entity.Med_Product;


public class DBController implements IDBController{
	
	private IDBpool DBpool = null;
	private Connection conn = null;
	
	private Encryption CP = new Encryption();
	
	
	//============================================================
	private void GetConnectToBase() throws SQLException {
		this.addDBPool(ClassFactory.injectDBPool());
		this.conn = DBpool.getConnection();
	}
	
	//============================================================
	private ResultSet GetSelectResult(String select) throws SQLException{
		this.GetConnectToBase();
		PreparedStatement preparedStatement = this.conn.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        this.ReturnConnectToPool();
		return resultSet;
	}
	//============================================================
	private void EnterQwery(String qwery) throws SQLException {
		this.GetConnectToBase();
		Statement statement = this.conn.createStatement();
        statement.executeUpdate(qwery);
        this.ReturnConnectToPool();
	}
	//============================================================
	@Override
	public boolean DataIsCorrect(String login, String password) throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from users");
		String a_login;
		String a_password;
		while (result.next()) {
            a_login = result.getString("u_login");
            a_password = result.getString("u_password");
            
            if(a_login.equals(login) && a_password.equals(CP.getCryptPassword(password))) {
            	return true;
            }
        }
		return false;
	}
	
	//============================================================
	@Override
	public boolean RegistrationNewUser(String login, String password){
		int size = 0;
		try {
			ResultSet result = this.GetSelectResult("Select * from users");
			while (result.next()) {
				size++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String SELECT_SQL = "insert into users(u_login, u_password, u_id) values('"+login +"', '"+CP.getCryptPassword(password)+"'," + ++size + ")";
		try {
			
			if(!login.isEmpty() && !password.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//============================================================
	@Override
	public ArrayList<Med_Product> GetCatalog() throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from medicines");
		ArrayList<Med_Product> return_list = new ArrayList<Med_Product>();
		int iter = 0;
		
		while (result.next()){
			Med_Product add_prod = new Med_Product();
			
			add_prod.medic_id = result.getInt("medic_id");
			add_prod.medic_cost = result.getInt("medic_cost");
			add_prod.medic_names = result.getString("medic_names");
			add_prod.medic_count = result.getInt("medic_count");
			add_prod.img = result.getString("img");
			return_list.add(iter, add_prod);
            iter++;
		}
		return return_list;
	}
	
	
	@Override
	public boolean DeleteProduct(String name) {
		String SELECT_SQL = "DELETE from medicines where medic_names = '"+ name +"'";
				
		try {
			
			if(!name.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	
	@Override
	public boolean AddProduct(String name, String cost, String count, String img) {
		int size = 0;
		try {
			ResultSet result = this.GetSelectResult("Select * from medicines");
			while (result.next()) {
				size++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String SELECT_SQL = "insert into medicines(medic_id, medic_cost, medic_names, medic_count, img)" +
					"values("+ ++size +", "+ Integer.parseInt(cost) +" , '" + name + "' ,  "+ Integer.parseInt(count) +", '"+img+"')";
		
		try {
			
			if(!name.isEmpty() && !cost.isEmpty() && !count.isEmpty() && !img.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	//============================================================
	@Override
	public void ReturnConnectToPool() {
		try {
			this.DBpool.returnConnection(this.conn);
			this.conn = null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//============================================================
	
	private void addDBPool(IDBpool pool) {
		if(this.DBpool == null) {
			this.DBpool = pool;
		}
	}
	

}

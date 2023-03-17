package DBlayer;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Med_Product;


public interface IDBController{
	boolean DataIsCorrect(String login, String password) throws SQLException;
	boolean RegistrationNewUser(String login, String password);
	
	boolean DeleteProduct(String name);
	boolean AddProduct(String name, String cost, String count, String img);
	
	ArrayList<Med_Product> GetCatalog() throws SQLException;
	void ReturnConnectToPool();
}

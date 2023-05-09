package modelLayer;

import java.util.ArrayList;
import DBlayer.IDBController;
import entity.Med_Product;


public interface IModel {
	boolean checkUserData(String login, String password);
	boolean registrateNewUser(String login, String password);
	boolean addNewProduct(String name, String cost, String count, String img);
	boolean delProduct(String name);
	ArrayList<Med_Product> 	getFullCatalog();
	void injectIDBController(IDBController repository);
}
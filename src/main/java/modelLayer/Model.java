package modelLayer;

import java.util.ArrayList;
import entity.Med_Product;
import utils.ClassFactory;
import DBlayer.IDBController;


public class Model implements IModel{
	
	private IDBController IDBController = null;
	
	
	@Override
	public boolean checkUserData(String login, String password) {
		this.injectIDBController(ClassFactory.injectDBController());
		
      	try { 
    	  	
    	  	if(IDBController.DataIsCorrect(login, password)) {
    	  		return true;
    	  	}else {
    	  		return false;
    	  	}
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO CHECK DATA FROM DB: " + e.getMessage());
      		return false;
      	}
	}
	
	
	@Override
	public boolean registrateNewUser(String login, String password) {
		this.injectIDBController(ClassFactory.injectDBController());
		
      	try {       
    	  	
    	  	if(IDBController.RegistrationNewUser(login, password)) {
    	  		return true;
    	  	}else
    	  	{
    	  		return false;
    	  	}
    	  	
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO REGISTRATE NEW DATA INTO DB: " + e.getMessage());
      		return false;
      	}
	}
	
	
	@Override
	public ArrayList<Med_Product> getFullCatalog() {
		this.injectIDBController(ClassFactory.injectDBController());
        
	 	ArrayList<Med_Product> catalog;
      	try { 
      		catalog = IDBController.GetCatalog();
      		return catalog;
      	}
      	catch (Exception e) {
      		System.out.println("GET CATALOG ERROR: " + e.getMessage());
      		return null;
      	}
	}	

	
	@Override
	public boolean addNewProduct(String name, String cost, String count, String img) {
		this.injectIDBController(ClassFactory.injectDBController());
		
		try { 
    	  	if(IDBController.AddProduct(name, cost, count, img)) {
    	  		return true;
    	  	}else
    	  	{
    	  		return false;
    	  	}
    	  	
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO REGISTRATE NEW DATA INTO DB: " + e.getMessage());
      		return false;
      	}
		
	}
	
	
	@Override
	public boolean delProduct(String name) {
		this.injectIDBController(ClassFactory.injectDBController());
		
		try { 
    	  	if(IDBController.DeleteProduct(name)) {
    	  		return true;
    	  	}else
    	  	{
    	  		return false;
    	  	}
    	  	
      	}
      	catch (Exception e) {
      		System.out.println("ERROR TO REGISTRATE NEW DATA INTO DB: " + e.getMessage());
      		return false;
      	}
	}
	
	
	
	@Override
	public void injectIDBController(IDBController controller) {
		if(this.IDBController == null)
			this.IDBController = controller;
	}
}

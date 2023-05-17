package infrastructure.controller.rest;

import java.util.List;

import application.shop.IShop;
import application.shop.medicine.Medicine;
import infrastructure.builder.Build.Built;
import infrastructure.interceptor.TokenRequired;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/medicine") 
public class MedicationsService {

    @Inject @Built
    private IShop shop;

	private Jsonb jsonb = JsonbBuilder.create();

    @POST
	@TokenRequired
	@Path("/add")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response addNewProd(String dataJSON) {             
        Medicine medicine = jsonb.fromJson(dataJSON, Medicine.class);
	 	boolean isAdd = shop.add(medicine);
		if(isAdd) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
 	}

	@POST
	@TokenRequired
	@Path("/delete")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response delProd(String dataJSON) {             
		Medicine medicine = jsonb.fromJson(dataJSON, Medicine.class);
		boolean isDelete = shop.delete(medicine);
		if(isDelete) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

	@GET
	@TokenRequired
	@Produces("application/json")
 	public Response pushCatalog() {            
		List<Medicine> medicines = shop.getAll();
		if(medicines == null){
			return Response.serverError().build();
		}
		String medicinesJSON = jsonb.toJson(medicines);
		return Response.ok(medicinesJSON).build();

 	}
}
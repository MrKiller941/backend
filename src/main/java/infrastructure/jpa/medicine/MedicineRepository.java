package infrastructure.jpa.medicine;

import java.util.ArrayList;
import java.util.List;

import application.shop.medicine.IMedicineRepository;
import application.shop.medicine.Medicine;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class MedicineRepository implements IMedicineRepository {

    @PersistenceContext(unitName = "Postgres")
    private EntityManager entityManager;

    @Override
    public boolean add(Medicine medicine) {
        try {
            EMedicine eMedicine = new EMedicine();
            eMedicine.setAll(medicine);
            entityManager.persist(eMedicine);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByName(String name) {
        String query = "delete from EMedicine p where p.name=:name";
        boolean status = true;
        try {
            entityManager.createQuery(query, EMedicine.class).setParameter("name", name).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    @Override
    public List<Medicine> getAll() {
        try {
            List<Medicine> medicines = new ArrayList<>();
            List<EMedicine> eMedicines = entityManager
            .createQuery("SELECT p FROM EMedicine p", EMedicine.class)
            .getResultList();
            for(EMedicine eMedicine : eMedicines){
                Medicine medicine = new Medicine();
                medicine.setName(eMedicine.getName());
                medicine.setCost(eMedicine.getCost());
                medicine.setCount(eMedicine.getCount());
                medicine.setImg(eMedicine.getImg());
                medicines.add(medicine);
            }
            return medicines;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

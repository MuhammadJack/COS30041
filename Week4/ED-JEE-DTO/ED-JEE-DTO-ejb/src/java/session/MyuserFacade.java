/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Myuser;
import entity.MyuserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gamal
 */
@Stateless
public class MyuserFacade implements MyuserFacadeRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "ED-JEE-DTO-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Myuser myuser) {
        em.persist(myuser);
    }

    private void edit(Myuser myuser) {
        em.merge(myuser);
    }

    private void remove(Myuser myuser) {
        em.remove(em.merge(myuser));
    }

    private Myuser find(Object id) {
        return em.find(Myuser.class, id);
    }

    @Override
    public boolean createRecord(MyuserDTO myuserDTO) {
        if (find(myuserDTO.getUserid()) != null) {
// user whose userid can be found
            return false;
        }
// user whose userid could not be found
        try {
            Myuser myuser = this.myDTO2DAO(myuserDTO);
            this.create(myuser); // add to database
            return true;
        } catch (Exception ex) {
            return false; // something is wrong, should not be here though
        }
    }

    private Myuser myDTO2DAO(MyuserDTO myuserDTO) {
        Myuser myuser = new Myuser();
        myuser.setUserid(myuserDTO.getUserid());
        myuser.setName(myuserDTO.getName());
        myuser.setPassword(myuserDTO.getPassword());
        myuser.setEmail(myuserDTO.getEmail());
        myuser.setPhone(myuserDTO.getPhone());
        myuser.setAddress(myuserDTO.getAddress());
        myuser.setSecqn(myuserDTO.getSecQn());
        myuser.setSecans(myuserDTO.getSecAns());
        return myuser;
    }

    private MyuserDTO myDAO2DTO(Myuser myuser) {
        MyuserDTO myuserDTO = new MyuserDTO(myuser.getUserid(),
                myuser.getName(),
                myuser.getPassword(),
                myuser.getEmail(),
                myuser.getPhone(),
                myuser.getAddress(),
                myuser.getSecqn(),
                myuser.getSecans());
        return myuserDTO;

    }

    @Override
    public MyuserDTO getRecord(String userId) {
        Myuser myuser = this.find(userId);
        if (myuser == null) {
            return null;
        } else {
            MyuserDTO myuserDTO = myDAO2DTO(myuser);
            return myuserDTO;
        }
    }

    @Override
    public boolean updateRecord(MyuserDTO myuserDTO) {
        Myuser myuser = myDTO2DAO(myuserDTO);
        Myuser myuser2 = this.find(myuser.getUserid());
        if (myuser2 == null) {
            return false;
        } else {
            this.edit(myuser);
            return true;
        }
    }

    @Override
    public boolean deleteRecord(String userId) {
        Myuser myuser = this.find(userId);
        if (myuser == null) {
            return false;
        } else {
            this.remove(myuser);
            return true;
        }
    }

    @Override
    public ArrayList<MyuserDTO> getRecordsByAddress(String address) {
        javax.persistence.Query query;
        ArrayList<MyuserDTO> DTOList = new ArrayList<>();

        query = em.createNamedQuery("Myuser.findByAddress").setParameter("address", address);
        List<Myuser> DAOList = query.getResultList();
        if (DAOList.isEmpty()) {
            return null;
        }
        for (Myuser user : DAOList) {
            DTOList.add(myDAO2DTO(user));
        }

        return DTOList;
    }

}


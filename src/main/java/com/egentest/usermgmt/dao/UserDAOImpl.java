package com.egentest.usermgmt.dao;

import java.util.List;

import com.egentest.usermgmt.model.User;
import com.egentest.usermgmt.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @Override
    @Transactional
    public List<User> list() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	List list = session.createCriteria(User.class).list();
        return list;
    }

	@Override
	public User list(long id) {
		User usr = null;
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		usr = (User)session.get(User.class, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return usr;
	}

	@Override
	public void add(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
		
		System.out.println("session--> " + session);
		//session.save(user);
        tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
		}catch (Exception e) {
			if (tx != null)
                tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
    public void deleteUser(long userId) {
        User user = (User) HibernateUtil.getSessionFactory().openSession().get(
                User.class, userId);
        if (null != user) {
        	Session session = HibernateUtil.getSessionFactory().openSession();
            session.delete(user);
        	session.flush();
        }
 
    }
	
	 @Override
	 @Transactional
	    public boolean updateUser(long id, User userUpdates) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 boolean result = false;
		 try{
			User user = (User)session.get(User.class, id); 
	        //user.setUserId(userUpdates.getUserId());
	        user.setFirstName(userUpdates.getFirstName());
			user.setMiddleName(userUpdates.getMiddleName());
			user.setLastName(userUpdates.getLastName());
			user.setAge(userUpdates.getAge());
			user.setUserGender(userUpdates.getUserGender());
			user.setPhoneNumber(userUpdates.getPhoneNumber());
			user.setZipCode(userUpdates.getZipCode());
			session.update(user);
			session.flush();
	            result = true;
		 } catch (Exception e){
	            result = false;
	            e.printStackTrace();
		 }
	        return result;
	    }
}

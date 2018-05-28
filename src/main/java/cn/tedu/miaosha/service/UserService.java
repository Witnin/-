package cn.tedu.miaosha.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.tedu.miaosha.dao.UserDao;
import cn.tedu.miaosha.domain.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
    public User getById(int id){ 
    	return userDao.getById(id);
    }
//@Transactional
	public boolean tx() {
		User u1 = new User();
		u1.setId(3);
		u1.setName("haha");
		userDao.insert(u1);
		
		User u2 = new User();
		u2.setId(1);
		u2.setName("just");
		userDao.insert(u2);
		return true;
	}
}

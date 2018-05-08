package com.userportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userportal.dao.RoleRpository;
import com.userportal.dao.UserRepository;
import com.userportal.entites.Role;
import com.userportal.entites.User;
import com.userportal.service.UserService;
import com.userportal.vo.UserVO;

import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRpository roleRepo;
    
    @Override
    public User create(UserVO userVO) {
        return repository.save(this.getUser(userVO));
    }

    private User getUser(UserVO userVO) {
    	User user = new User();
    	if(null!=userVO.getId())
    		user.setId(userVO.getId());
    	user.setEmail(userVO.getEmail());
    	user.setFirstName(userVO.getFirstName());
    	user.setLastName(userVO.getLastName());
    	user.setPassword(userVO.getPassword());
    	user.setUserName(userVO.getUserName());
    	Role role = new Role();
    	role.setId(userVO.getRole());
    	user.setRole(role);
    	return user;
    }
    @Override
    public User delete(int id) {
        User user = findById(id);
        if(user != null){
            repository.delete(user);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(int id) {
        return repository.findOne(id);
    }

    @Override
    public User update(User user) {
        return null;
    }

	@Override
	public User findByName(String user) {
		// TODO Auto-generated method stub
		return repository.findOneByUserName(user);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findOneByUserName(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
	}

	private List<SimpleGrantedAuthority> getAuthority(User user) {
		return Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRole()));
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}
}

package my.pro.job.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import my.pro.job.entity.Account;
import my.pro.job.entity.Role;
import my.pro.job.repository.AccountRepository;

@Service
public class CustomUserDetailsImplService implements UserDetailsService{

	@Autowired
	private AccountRepository accountRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account==null) throw new UsernameNotFoundException("username "+username+" not found");
		List<Role> roles = account.getRoles();
		List<GrantedAuthority> authorities = 
				roles.stream()
					.map(r->new SimpleGrantedAuthority(r.getRole()))
					.peek(r->System.err.println(r.getAuthority()))
					.collect(Collectors.toList());
		return new User(account.getUsername(),account.getPassword(),authorities);
	}

}

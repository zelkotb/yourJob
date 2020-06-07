package my.pro.job.service;

import my.pro.job.dto.AuthenticationDTO;

public interface AuthenticationService {

	public String authenticateUser(AuthenticationDTO authenticationDTO);
}

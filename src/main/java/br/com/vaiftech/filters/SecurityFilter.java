package br.com.vaiftech.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.vaiftech.models.entities.SystemUser;
import br.com.vaiftech.repositories.UserRepository;

public class SecurityFilter implements Filter {
	
	private UserRepository userRepository;
	private ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
	
    public SecurityFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		try{
			validateUser(httpServletRequest);
			chain.doFilter(request, response);
			
		}catch(IOException ex){
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			httpServletResponse.sendError(401, "Access denied");
			
		}
	}
	
	private void validateUser(HttpServletRequest httpServletRequest) throws IOException {
		String userJson = httpServletRequest.getHeader("user");
		
		if(userJson==null) throw new IOException();
		ObjectMapper mapper = new ObjectMapper();
		SystemUser user = mapper.readValue(userJson, SystemUser.class);
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getUsername()));
		if(userRepository.isInvalidUser(user))
			throw new IOException();
	}

	public void init(FilterConfig fConfig) throws ServletException {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(fConfig.getServletContext());
		userRepository = webApplicationContext.getBean(UserRepository.class);
	}
	
	public static void main(String[] args) {
		ShaPasswordEncoder enc = new ShaPasswordEncoder(256);
		System.out.println(enc.encodePassword("vaiftech", "vaiftech"));
	}

}

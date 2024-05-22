package com.empresa.struts.configuration;

import com.empresa.struts.dao.UsuarioDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class SessionInterceptor extends AbstractInterceptor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO usuarioDAO;
	
	public SessionInterceptor() {
        this.usuarioDAO = new UsuarioDAO();
    }

	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();

        if (session.getAttribute("currentUser") != null) {
            return invocation.invoke();
        } else {
        	if (this.usuarioDAO.hasUsuarios()) {        	
        		return Action.LOGIN;
        	} else {
        		return "error412";
        	}
        }
    }
}
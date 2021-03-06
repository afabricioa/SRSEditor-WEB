package br.ufpi.dc.controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import br.ufpi.dc.dao.UsuarioDAO;
import br.ufpi.dc.modelo.Usuario;
import br.ufpi.dc.utilidades.SessionUtil;

@ManagedBean(name="login")
@RequestScoped
public class LoginController {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
		
	
	public String loginControl(){
		System.out.println("autenticando...");
		if(usuarioDAO.loginControl(username, password)){
//		if (usuario.getUsername().equals("admin")&&usuario.getPwd().equals("admin")) {
			Object b = new Object();
			SessionUtil.setParam("UsuarioLogado", b);
			
			return "home.xhtml?faces-redirect=true";
		}
		RequestContext.getCurrentInstance().update("growl");
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Credenciais Invalidas", "Usuario ou senha invalido"));
		return "";
	}
	
	public String logout(){
		return "login.xhtml?faces-redirect=true";
	}
		

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

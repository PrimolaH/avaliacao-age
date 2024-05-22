package com.empresa.struts.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class IndexController extends ActionSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(IndexController.class.getName());

	@Override
	public String execute() {

		HttpSession session = ServletActionContext.getRequest().getSession();

		if (session.getAttribute("currentUser") == null) {
			return Action.LOGIN;
		}
		return SUCCESS;
	}
}
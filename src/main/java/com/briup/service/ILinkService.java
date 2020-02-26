package com.briup.service;

import java.util.List;

import com.briup.bean.Link;
import com.briup.exception.CustomerException;

public interface ILinkService {
	public void SaveOrUpdate(Link link);
	
	public List<Link> findAll();
	
	public Link findById(Integer id);
	
	public void deleteById(Integer id)throws Exception;

}

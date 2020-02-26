package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Link;
import com.briup.dao.LinkDao;
import com.briup.exception.CustomerException;
import com.briup.service.ILinkService;
@Service
public class LinkServiceImpl implements ILinkService{
	@Autowired
	private LinkDao linkDao;
	
	@Override
	public void SaveOrUpdate(Link link) {
		// TODO Auto-generated method stub
		if(link!=null) {
			Integer id = link.getId();
			if(id==null) {
				linkDao.save(link);
			}else {
				//根据id 查link
				Link link_db = linkDao.findById(id).get();
				if(link.getName()!=null) {
					link_db.setName(link.getName());
				}
				if(link.getUrl()!=null) {
					link_db.setUrl(link.getUrl());
				}
				linkDao.save(link_db);
				
			}
			
			
		}else {
			throw new CustomerException(500, "参数为空");
		}
	}

	@Override
	public List<Link> findAll() {
		// TODO Auto-generated method stub
		List<Link> list = linkDao.findAll();
		return list;
	}

	@Override
	public Link findById(Integer id) {
		// TODO Auto-generated method stub
		Link link = linkDao.findById(id).get();
		return link;
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		 Link link = linkDao.findById(id).get();
		 //若link不存在，说明id在数据库中不存在
		 if(link!=null) {
			 linkDao.deleteById(id);
		 }else {
			 throw new Exception("该id在数据库中不存在");
		 }
	}
	
	

}

package com.briup.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Link;
import com.briup.exception.CustomerException;
import com.briup.service.ILinkService;
import com.briup.untils.Message;
import com.briup.untils.MessageUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/link")
@Api(description="链接相关接口")
public class LinkController {
	@Autowired
	private ILinkService linkService;
	
	@PutMapping("/saveOrUpdate")//导入一般用put
	@ApiOperation("保存或者更新链接信息，如果id为空则为保存，否则为更新")
	public Message<String> saveOrUpdate(Link link){
		linkService.SaveOrUpdate(link);
		return MessageUtil.success("保存成功");
		
	}
	
	@GetMapping("/findAll")
	@ApiOperation("查询所有链接信息")
	public Message<List<Link>> findAll(){
		List<Link> list = linkService.findAll();
		return MessageUtil.success(list);
	}
	
	@GetMapping("/findById")
	@ApiOperation("根据id查询链接")
	@ApiImplicitParam(name="id",value="链接id",paramType="query",dataType="int",required=true)
	public Message<Link> findById(Integer id){
		if(id!=null) {
			Link link = linkService.findById(id);
			return MessageUtil.success(link);
		}else {
			
			return MessageUtil.error(500, "id不存在");
		}
	}
	
	@DeleteMapping("/deleteById")
	@ApiOperation("根据id删链接")
	@ApiImplicitParam(name="id",value="链接id",paramType="query",dataType="int",required=true)
	public Message<String> deleteById(Integer id){
		Message<String> message=null;
		try {
			linkService.deleteById(id);
			message=MessageUtil.success("删除成功");
			
		} catch (Exception e) {
			// TODO: handle exception
			message=MessageUtil.error(500,"该id在数据库中不存在");
		}
		return message;
	}
	
}

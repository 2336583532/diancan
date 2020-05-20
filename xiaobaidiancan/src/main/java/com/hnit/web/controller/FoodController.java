package com.hnit.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.github.pagehelper.PageInfo;
import com.hnit.bean.OrderFood;
import com.hnit.biz.FoodBiz;
import com.hnit.biz.Impl.FoodBizImpl;
import com.hnit.utils.OutputObject;

@RestController
public class FoodController {
	@Autowired
	FoodBiz foodBiz;
	
	@Value("${file.uploadFolder}")
	private String path;
	
	@Value("${pagehelper.pageSize}")
	private int pageSize;
	
	@RequestMapping("/toAddFood")
	public ModelAndView toAddFood() {
		ModelAndView model = new ModelAndView("freemarker/addFood");
		
		return model;
	}
	
	
	@RequestMapping("/addFood")
	@ResponseBody
	public OutputObject addFood(OrderFood food,Model model) {
		
		OutputObject out = new OutputObject();
		int result=foodBiz.addFood(food);
		if(result>0) {
			out.setCode(1);
		}else {
			out.setCode(-1);
		}
		return out;
	}
	
	/*
	 * 上传菜品图片
	 */
	@RequestMapping("/foodPicUpload")
	@ResponseBody
	public OutputObject foodPicUpload(MultipartFile file) {
		OutputObject out = new OutputObject();
		if(file.isEmpty()){
			out.setCode(-1);
			out.setMessage("上传失败");
			
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        File dest = new File(path + java.io.File.separator + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        if(dest.exists()) {
        	dest.delete();
        }
        try {
            file.transferTo(dest); //保存文件
            out.setCode(1);
            
            //model.setViewName("freemarker/addFood");
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.setCode(-1);
			out.setMessage("上传失败");
			
			
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            out.setCode(-1);
			out.setMessage("上传失败");
			
        }
        return out;
	}
	
	@RequestMapping("/toQueryFoodList")
	public ModelAndView toQueryFoodList() {
		 ModelAndView model = new ModelAndView("freemarker/foodList");
		 return model;
	}
	//查询菜品列表
	@RequestMapping("/queryFoodList")
	@ResponseBody
	public Map<String, Object> queryFoodList(@RequestParam(name = "limit")Integer pageSize,@RequestParam(name = "page")Integer pageNumber) {
		if(pageSize!=null) {
			this.pageSize=pageSize;
		}
		PageInfo<OrderFood> foodList = foodBiz.queryFoodList(pageNumber, this.pageSize);
		Map<String,Object> foods = new HashMap<String, Object>();
		foods.put("code",0);
		foods.put("msg", "");
		foods.put("data", foodList.getList());
		foods.put("count", foodList.getTotal());
		return foods;
	}
	
	@RequestMapping("/toUpdateFood")
	public ModelAndView toUpdateFood(@RequestParam(name = "fid" ) int fid,ModelAndView model) {
		OrderFood food=foodBiz.queryFoodByFid(fid);
		model.addObject("food", food);
		model.setViewName("freemarker/updateFood");
		return model;
	}
	
	@RequestMapping("/updateFood")
	@ResponseBody
	public OutputObject updateFood(OrderFood food) {
		int result = foodBiz.updateFood(food);
		OutputObject out = new OutputObject();
		if(result<0) {
			out.setCode(-1);
		}else {
			out.setCode(1);
		}
		return out;
	}
	
	@RequestMapping("/deleteFood")
	@ResponseBody
	public OutputObject deleteFood(@RequestParam(name="fid")int fid,Model model) {
		int result = foodBiz.deleteFoodByFid(fid);
		OutputObject out = new OutputObject();
		if(result<0) {
			out.setCode(-1);
		}else {
			out.setCode(1);
		}
		return out;
	}
}

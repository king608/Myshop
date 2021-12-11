package cn.edu.lnc.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import cn.edu.lnc.domain.Category;
import cn.edu.lnc.domain.Product;
import cn.edu.lnc.service.AdminProductService;
import cn.edu.lnc.utils.CommonsUtils;

public class AdminProductServlet extends AdminBeasServlet {
	
	public void delProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AdminProductService service = new AdminProductService();
		service.delProduct(pid);
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=productList");
	}
	
	//更新数据----z暂时废弃
	public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		Product product = new Product();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			//获取磁盘文件
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//获取服务
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> parseRequest = upload.parseRequest(request);
			for (FileItem fileItem : parseRequest) {
				boolean formField = fileItem.isFormField();
				if(formField){
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString();
					map.put(fieldName, fieldValue);
				}else{
					String name = fileItem.getName();
//					String path = this.getServletContext().getRealPath("");
//					InputStream in = fileItem.getInputStream();
//					OutputStream os = new FileOutputStream(path+"/"+name);
//					IOUtils.copy(in, os);
//					in.close();
//					os.close();
					map.put("pimage","/upload"+name);
				}
			}
			
			try {
				BeanUtils.populate(product, map);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			
			//封装数据
			product.setPid(pid);
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			AdminProductService service = new AdminProductService();
			service.updateProduct(product);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=productList");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	//展示数据到编辑页
	public void transitProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		AdminProductService service = new AdminProductService();
		Product product = service.findProductByPid(pid);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
	}
	
	//添加商品
	public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析request获得文件项对象集合

			List<FileItem> parseRequest = upload.parseRequest(request);
			
			for (FileItem item : parseRequest) {
				boolean formField = item.isFormField();
				if(formField){
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					
					map.put(fieldName, fieldValue);
					
				}else{
					String name = item.getName();
					String path = this.getServletContext().getRealPath("upload");
					InputStream in = item.getInputStream();
					OutputStream on = new FileOutputStream(path+"/"+name);
					IOUtils.copy(in, on);
					in.close();
					on.close();
					
					map.put("pimage","/upload"+name);
					
				}
					
			}
			try {
				BeanUtils.populate(product, map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			product.setPid(CommonsUtils.getUUID());
			product.setPdate(new Date());
			product.setPflag(0);
			Category category = new Category();
			category.setCid(map.get("cid").toString());
			product.setCategory(category);
			
			AdminProductService service = new AdminProductService();
			service.addProduct(product);
			
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=productList");
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//异步加载分类
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminProductService service = new AdminProductService();
		//查找所有的分类
		List<Category> category = service.findCategory();
		
		Gson gson = new Gson();
		String json = gson.toJson(category);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	
	
	//展示所有的商品
	public void productList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminProductService service = new AdminProductService();
		List<Product> productList = service.findProduct();
		
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
		
	}
	
	//删除分类信息
	public void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminProductService service = new AdminProductService();
		service.delCategory(cid);
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=categoryList");
	}
	
	//更新分类的信息
	public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> parameterMap = request.getParameterMap();
		Category category = new Category();
		try {
			BeanUtils.populate(category, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		AdminProductService service = new AdminProductService();
		service.updateCategory(category);
		
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=categoryList");
	}
	

	//展示数据到分类的编辑页
	public void transitCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminProductService service = new AdminProductService();
		Category category = service.findCategoryByCid(cid);
		
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	}
	
	//添加分类
	public void addCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//获取表单数据，并封装到Category
		String cname = request.getParameter("cname");
		Category category = new Category();
		category.setCid(CommonsUtils.getUUID());
		category.setCname(cname);
		AdminProductService service = new AdminProductService();
		//添加分类
		service.addCategory(category);
		
		response.sendRedirect(request.getContextPath()+"/adminProduct?method=categoryList");
		
	}
	

	//展示分类信息
	public void categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminProductService service = new AdminProductService();
		//查找所有的分类
		List<Category> category = service.findCategory();
		
		//存域，转发
		request.setAttribute("category", category);
		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}

	
}
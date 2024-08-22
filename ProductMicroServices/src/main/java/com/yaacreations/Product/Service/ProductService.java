package com.yaacreations.Product.Service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaacreations.Product.Entity.ProductDto;
import com.yaacreations.Product.Entity.ProductItemsDto;
import com.yaacreations.Product.Model.ProductItemModel;
import com.yaacreations.Product.Model.ProductModel;
import com.yaacreations.Product.Repository.ProductItemReppo;
import com.yaacreations.Product.Repository.ProductReppo;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class ProductService {
	
	@Autowired
	ProductReppo productReppo;
	
	@Autowired
	ProductItemReppo productItemReppo;

	public String addproduct(ProductModel productModel) {
		ProductDto productDto=new ProductDto(productModel.getProductname(),productModel.getProductdescription(),productModel.getStatus());
		
		productDto=  productReppo.save(productDto);
		
		for (ProductItemModel productItemModel : productModel.getProductItemsmodel()) {
			
			ProductItemsDto productItemsDto=new ProductItemsDto();
			productItemsDto.setProduct(productDto);
			productItemsDto.setProductprice(productItemModel.getProductprice());
			productItemsDto.setProducttype(productItemModel.getProducttype());
			
			productItemReppo.save(productItemsDto);
		}
		 
		return "product saved";
	}
	

	public ProductModel getproduct(int id) {
		
	ProductDto productDto=productReppo.findById(id).get();

	   ProductModel model=new ProductModel();
	   BeanUtils.copyProperties(productDto, model);
	   
	   List<ProductItemsDto> productItemsDto=productItemReppo.findByProduct(productDto);
	 
	   List<ProductItemModel> productItemModels =new LinkedList<ProductItemModel>();	 
	 
	 for (ProductItemsDto productItem : productItemsDto) {
		 
		 ProductItemModel productitemmodel=new ProductItemModel();
		 productitemmodel.setProductid(productItem.getProduct().getProductid());
		 productitemmodel.setProductitemid(productItem.getProductitemid());
		 productitemmodel.setProductprice(productItem.getProductprice());
		 productitemmodel.setProducttype(productItem.getProducttype());
		 productItemModels.add(productitemmodel);
		 
	}
	    model.setProductItemsmodel(productItemModels);
		     
		return  model;
	}
	

	public ProductItemModel getproductitem(int id) {
		 ProductItemsDto productItem=productItemReppo.findById(id).get();
		 ProductItemModel productitemmodel=new ProductItemModel();
		 productitemmodel.setProductid(productItem.getProduct().getProductid());
		 productitemmodel.setProductitemid(productItem.getProductitemid());
		 productitemmodel.setProductprice(productItem.getProductprice());
		 productitemmodel.setProducttype(productItem.getProducttype());
		 return productitemmodel;
	}

	
	
	
}

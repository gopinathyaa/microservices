package com.yaacreations.OrderMicroServices.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderDto {
	
	public OrderDto(int ordernumber, int userid, float gst, float discount, Double totalamount, Double subtotal,
			int status) {
		 this.ordernumber=ordernumber;
		 this.userid=userid;
		 this.gst=gst;
		 this.discount=discount;
		 this.totalamount=totalamount;
		 this.subtotal=subtotal;
		 this.status=status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	
	@Column(unique = true ,nullable = false)
	private int ordernumber;
	
    private int userid;
    
 
    private LocalDateTime orderDateTime;
    
    private Double subtotal;
    
    private float gst;
    
    private float discount;
    
    private Double totalamount;
    
    private int status;
    
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private  List<OrderitemsDto> orderitemsDto;
}

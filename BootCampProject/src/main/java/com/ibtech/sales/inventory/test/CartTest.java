package com.ibtech.sales.inventory.test;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.ibtech.sales.inventory.entity.Adress;
import com.ibtech.sales.inventory.entity.Cart;
import com.ibtech.sales.inventory.entity.Province;
import com.ibtech.sales.inventory.manager.AdressManager;
import com.ibtech.sales.inventory.manager.CartManager;

public class CartTest {
	
	public static void main(String[] args) throws Exception {
		
		Cart cart = new Cart();
		cart.setCartId(45);
		Province province = new Province();
		province.setProvinceId(1);
		Adress adress = new Adress();
		adress.setAdressLine1("sda");
		adress.setAdressLine2("asdasd");
		adress.setCart(cart);
		adress.setProvince(province);
		
		AdressManager adressManager = new AdressManager();
		adressManager.insert(adress);
		
	}
}

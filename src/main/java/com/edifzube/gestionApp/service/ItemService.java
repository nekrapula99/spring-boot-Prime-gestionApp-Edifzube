package com.edifzube.gestionApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edifzube.gestionApp.model.Item;
import com.edifzube.gestionApp.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItems(){
		return itemRepository.findAll();
	}
	
	public void save(Item item) {
		itemRepository.save(item);
	}
	
	public Optional<Item> findById(int id) {
		System.out.println("item servi find: "+id);
		return itemRepository.findById(id);
	}
	
	public void delete(Integer id) {
		itemRepository.deleteById(id);
	}


}

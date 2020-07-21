package com.edifzube.gestionApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edifzube.gestionApp.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

}

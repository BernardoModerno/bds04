package com.devsuperior.bds04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repository.CityRepository;
import com.devsuperior.bds04.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = false)
	public EventDTO save(EventDTO dto) {
		Event entity = new Event();
		entity.setName(dto.getName());
		entity.setUrl(dto.getUrl());
		entity.setDate(dto.getDate());
		City city = cityRepository.findById(dto.getCityId()).get();
		entity.setCity(city);
		entity = repository.save(entity);
		return new EventDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(PageRequest pageRequest){
		Page<Event> pages = repository.findAll(pageRequest);
		return pages.map(pageone -> new EventDTO(pageone));
	}
	
}

package com.bootCamp.TarefaBootCamp.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootCamp.TarefaBootCamp.dto.ClientDTO;
import com.bootCamp.TarefaBootCamp.entites.Client;
import com.bootCamp.TarefaBootCamp.repositores.ClientRepository;
import com.bootCamp.TarefaBootCamp.services.exception.RNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
		Page<Client> page = repository.findAll(pageRequest);
		return page.map(x -> new ClientDTO(x));	
	}
	
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new RNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}
}
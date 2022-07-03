package com.axyya.project.restproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axyya.project.restproject.exception.NoSuchServerFoundException;
import com.axyya.project.restproject.model.Server;
import com.axyya.project.restproject.repository.ServerRepository;

@Service
public class ServerService {
	@Autowired
	ServerRepository serverRepo;

	public Server getServer(String id) throws NoSuchServerFoundException {
		Optional<Server> server = serverRepo.findById(id);
		if (server.isEmpty())
			throw new NoSuchServerFoundException("Invalid ID");
		return server.get();
	}

	public List<Server> getServers() {
		return serverRepo.findAll();
	}

	public boolean addServer(Server server) {
		return serverRepo.save(server) != null;
	}

	public void deleteServer(String id) throws NoSuchServerFoundException {
		if (serverRepo.findById(id).isEmpty())
			throw new NoSuchServerFoundException("Invalid ID");
		serverRepo.deleteById(id);
	}

	public List<Server> findServer(String name){
		return serverRepo.findByNameContaining(name);
	}
}

package com.axyya.project.restproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.axyya.project.restproject.exception.NoSuchServerFoundException;
import com.axyya.project.restproject.model.Server;
import com.axyya.project.restproject.service.ServerService;
@RequestMapping("/api")
@RestController
@CrossOrigin
public class ServerController {

	@Autowired
	ServerService service;

	@GetMapping(path = "/servers")
	public ResponseEntity<List<Server>> getServers() {
		return new ResponseEntity<List<Server>>(service.getServers(), HttpStatus.OK);
	}

	@GetMapping(path = "/servers/{id}")
	public ResponseEntity<Server> getServer(@PathVariable String id) {
		try {
			return new ResponseEntity<Server>(service.getServer(id), HttpStatus.OK);
		} catch (NoSuchServerFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/servers")
	public ResponseEntity<String> addServer(@RequestBody Server server) {
		return service.addServer(server) ? 
			new ResponseEntity<String>("Server Added / Updated", HttpStatus.ACCEPTED) :
			new ResponseEntity<String>("Invalid Request", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(path = "/servers/{id}")
	public ResponseEntity<String> deleteServer(@PathVariable String id) {
		try {
			service.deleteServer(id);
			return new ResponseEntity<String>("server with id :" + id + " deleted", HttpStatus.OK);
		} catch (NoSuchServerFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/servers/find")
	public ResponseEntity<List<Server>> findServers(@RequestParam(name = "name") String name) {
		List<Server> servers = service.findServer(name);
		return servers.size() > 0 ? new ResponseEntity<List<Server>>(servers, HttpStatus.OK) :
			new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}

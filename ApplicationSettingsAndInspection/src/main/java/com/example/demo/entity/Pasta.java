package com.example.demo.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Pasta {
    @Id
    private String id; // 고유 식별값
	private String name; // 파스타 이름

	public Pasta() {

	}

	public Pasta(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Pasta(String name) {
		this(UUID.randomUUID().toString(), name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

    public void setId(String id) {
        this.id = id;
    }

	public void setName(String name) {
		this.name = name;
	}


}


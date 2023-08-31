package com.example.demo;

public record CreateUserDto(String name){

	public String getName(){ return this.name; }

 }


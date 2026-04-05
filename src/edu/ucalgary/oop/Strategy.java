package edu.ucalgary.oop;

public interface Strategy <T, U> {
	U execute(T param);
}

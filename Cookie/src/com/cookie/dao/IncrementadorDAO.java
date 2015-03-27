package com.cookie.dao;

import java.util.ArrayList;
import java.util.List;

import com.cookie.incrementador.Incrementador;

public class IncrementadorDAO {
	
	public List<Incrementador> getLista() {
		Incrementador i1 = new Incrementador();
		i1.setId(Long.valueOf(1));
		i1.setNome("Vovó");
		i1.setPreco((long)10);
		i1.setQuantidadeComprada((long)0);
		i1.setValorIncremento((long)1);
		
		Incrementador i2 = new Incrementador();
		i2.setId(Long.valueOf(2));
		i2.setNome("Forno");
		i2.setPreco((long)50);
		i2.setQuantidadeComprada((long)0);
		i2.setValorIncremento((long)3);
		
		Incrementador i3 = new Incrementador();
		i3.setId(Long.valueOf(3));
		i3.setNome("Forno Grande");
		i3.setPreco((long)150);
		i3.setQuantidadeComprada((long)0);
		i3.setValorIncremento((long)5);
		
		List<Incrementador> listaIncrementadores = new ArrayList<Incrementador>();
		listaIncrementadores.add(i1);
		listaIncrementadores.add(i2);
		listaIncrementadores.add(i3);
		
		return listaIncrementadores;
		
	}

}

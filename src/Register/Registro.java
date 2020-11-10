package Register;

import java.util.ArrayList;
import java.util.List;

import Actions.Leitor;
import Model.Animal;
import Model.Arroba;
import Model.Bovino;
import Model.Quilo;
import Model.Racas;
import Model.Suino;
import View.Menu;

public class Registro {
	private int opcao;
	private Leitor leitor;
	Racas racaAnimal = new Racas();
	Animal animal;
	
	public	List<Animal> listaDeAnimais = new ArrayList<Animal>();
	
	// FUN��O GETTER PARA LISTA DE ANIMAIS
	public List<Animal> getListaDeAnimais(){
		return listaDeAnimais;
	}
	
	public List<Animal> getListaDeBovinos(){
		List<Animal> listaDeBovinos = new ArrayList<Animal>();
		
		for(Animal a : listaDeAnimais) {
			if(a instanceof Bovino) {
				listaDeBovinos.add(a);
			}
		}
		return listaDeBovinos;
	}
	
	public List<Animal> getListaDeSuinos(){
		List<Animal> listaDeSuinos = new ArrayList<Animal>();
		
		for(Animal a : listaDeAnimais) {
			if(a instanceof Suino) {
				listaDeSuinos.add(a);
			}
		}
		return listaDeSuinos;
	}
	
	// ------------------ SE��O DEDICADA AO CADASTRO DO ANIMAL ------------------
	
	// M�TODO ADICIONA BOVINO NA LISTA DE CADASTRO
	public void cadastraBovino(int cont) {
		
		animal = cadastraAnimal(cont, new Bovino());
		listaDeAnimais.add(animal);
	}
	
	// M�TODO ADICIONA SUINO NA LISTA DE CADASTRO
	public void cadastraSuino(int cont) {
		animal = cadastraAnimal(cont, new Suino());
		listaDeAnimais.add(animal);
	}
	
	// M�TODO DE DEFINI��O DA RA�A
	public String registraRaca(Animal animal) {
		String r;
		
		if(animal instanceof Bovino) {
			r = racaAnimal.racaBoi();
		} else {
			r = racaAnimal.racaPorco();
		}
		
		return r;
	}
	
	// M�TODO DE DEFINI��O DE G�NERO
	public String registraGenero() {
		String g = "";
		int op = 0;
		Menu m = new Menu();
	
		System.out.println("G�NERO:\n [1] F�MEA\n [2] MACHO ");	
		leitor = new Leitor();
		op = m.opcaoInvalida(1, 2, op);
		
		// ATRIBUINDO GENERO DE ACORDO COM A OP��O SELECIONADA
		if(op == 1) g = "F";
		else if(op == 2) g = "M";
		
		return g;
	}
	
	// M�TODO DE DEFINI��O DE ID
	public String registraId(int posicaoId, Animal animal) {
		if(animal instanceof Bovino) {
			return "B" + posicaoId;
		}
		else {
			return "P" + posicaoId;
		} 
	}
	
	// M�TODO PRINCIPAL DE CADASTRO DO ANIMAL
	public Animal cadastraAnimal(int posicaoId, Animal animal) {
		Menu m = new Menu();
		Arroba arroba = new Arroba();
		Quilo kg = new Quilo();
		
		System.out.println("______CADASTRO______");
		System.out.println("INFORMA��ES SOBRE O ANIMAL:\n");
		String id, raca, genero;
		int op;
		double pesoKg, pesoArroba;
		
		// ATRIBUINDO O ID DO ANIMAL
		id = registraId(posicaoId, animal);
		
		// ATRIBUINDO RA�A DO ANIMAL DE ACORDO COM A ESPECIE
		raca = registraRaca(animal);
		
		// ATRIBUINDO O GENERO DO ANIMAL DE ACORDO COM A OP��O SELECIONADA
		genero = registraGenero();
		
		// ATRIBUINDO PESO DO ANIMAL EM KG E ARROBA
		op = m.printTipoDePesoCadastrar();
		if(op == 1) {
			System.out.print("PESO(Kg): ");
			leitor = new Leitor();
			pesoKg = leitor.getValorDecimal();
			
			pesoArroba = arroba.convertePeso(pesoKg);
		}
		else {
			System.out.print("PESO(@): ");
			leitor = new Leitor();
			pesoArroba = leitor.getValorDecimal();
			
			pesoKg = kg.convertePeso(pesoArroba);
		}		
		
		// ATRIBUINDO DATA DE NASCIMENTO DO ANIMAL
		System.out.println("DATA DO NASCIMENTO DO ANIMAL:");
		leitor = new Leitor();
		String data = leitor.getTexto();
		
		if(animal instanceof Bovino) {
			return new Bovino(id, raca, pesoArroba, pesoKg, genero, data);
		}
		else {
			return new Suino(id, raca, pesoArroba, pesoKg, genero, data);
		}
		
	}
	
	
	// ------------------ SE��O DEDICADA AO DESCADASTRO DO ANIMAL ------------------
	
	// M�TODO PRINCIPAL DE DESCADASTRO DO ANIMAL
	public void descadastraAnimal(int index) {
		listaDeAnimais.remove(index);
	}

}

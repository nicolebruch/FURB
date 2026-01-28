package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVeiculos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Veiculo> veiculos = new ArrayList<>();

	public void adicionar(Veiculo v) {
		veiculos.add(v);
	}

	public List<Veiculo> listar() {
		return veiculos;
	}

	public void salvar(String arquivo) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
			out.writeObject(veiculos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregar(String arquivo) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
			veiculos = (List<Veiculo>) in.readObject();
		} catch (Exception e) {
			veiculos = new ArrayList<>();
		}
	}

}


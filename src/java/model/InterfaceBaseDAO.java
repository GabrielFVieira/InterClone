package model;

import java.util.List;

public interface InterfaceBaseDAO<T extends Object> {
    
    public List<T> listar();
    
    public T buscarPorId(int id);
    
    public void salvar(T obj);
    
    public boolean excluir(int id);
}

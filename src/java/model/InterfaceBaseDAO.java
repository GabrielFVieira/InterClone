package model;

import java.util.List;
import aplicacao.Session;

public interface InterfaceBaseDAO<T extends Object> {
    
    public List<T> listar();
    
    public T buscarPorId(int id);
    
    public void salvar(T obj) throws Exception;
    
    public boolean excluir(int id, Session session) throws Exception;
}

package service;

import model.Pagamento;
import repository.PagamentoRepository;

import java.util.List;
import java.util.Objects;

public class PagamentoService {

    PagamentoRepository pagamentoRepository = new PagamentoRepository();


//validação de pagamento
    public void adicionarPagamento(Pagamento p){

        Objects.requireNonNull(p,"O pagamento não pode ser null! ");
        pagamentoRepository.adiciona(p);

    }


//cancelar pagamento
    public void cancelaPagamento(Long id) {
        Objects.requireNonNull(id, "O ID não pode ser nulo!");

        //Crio uma lista que implementa o método de busca id
        List<Pagamento> resultado = pagamentoRepository.buscaPorId(id);

        //verifica se algo foi encontrado
        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("Pagamento não encontrado!");
        }

        //se encontrar algum id ele retorna o método cancelar
        resultado.get(0).cancelar();
    }

//aprovar pagamento
    public void aprovarPagamento(Long id){
        Objects.requireNonNull(id,"O id não pode ser null");

        List<Pagamento> resultado = pagamentoRepository.buscaPorId(id);

        if (resultado.isEmpty()){
            throw  new IllegalArgumentException("Pagamento não encontrado");
        }
        resultado.get(0).aprovar();
    }

    public List<Pagamento> getPagamentos() {
        return pagamentoRepository.getPagamentos();
    }
}

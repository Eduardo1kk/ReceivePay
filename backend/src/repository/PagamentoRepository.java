package repository;

import model.Pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagamentoRepository {

    //Temporário

    private List<Pagamento> pagamentos = new ArrayList<>();


    public  void  adiciona(Pagamento p){
        Objects.requireNonNull(p,"O pagamento não pode ser nulo!");


        //verifica se existe pagamentos duplicados
        for (Pagamento t  : pagamentos){
            if (t.getId().equals(p.getId())){
                throw new ObjetosParecidosExepition("Pagamento duplicado! ");
            }
        }

        pagamentos.add(p);
    }


    public List<Pagamento> buscaPorId(Long resultado){

        List<Pagamento> pagamentoList = new ArrayList<>();

        if (resultado == null) {
            return pagamentoList;
        }
        for (Pagamento p : pagamentos){
            if (p.getId().equals(resultado)){
                pagamentoList.add(p);
            }

        }
        return pagamentoList;

    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
}

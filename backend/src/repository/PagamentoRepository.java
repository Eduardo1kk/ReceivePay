package repository;

import model.Pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PagamentoRepository {

    //Temporário

    private List<Pagamento> pagamentos = new ArrayList<>();


    public  void  adiciona(Pagamento p){
        Objects.requireNonNull(p,"O Cliente  não pode ser nulo !");
        pagamentos.add(p);
    }


    public List<Pagamento> buscaPorId(String resultado){

        List<Pagamento> pagamentoList = new ArrayList<>();

        for (Pagamento p : pagamentos){
            if (p.getId().toString().contains(resultado)){
                pagamentoList.add(p);
            }

        }
        return pagamentoList;

    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }
}

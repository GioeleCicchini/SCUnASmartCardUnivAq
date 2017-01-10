package Domain;

/**
 * Created by agost on 10/01/2017.
 */
public class Pagamento {

    public Pagamento() {
    }

    private int Amount;
    private String idPagamento;

    public String getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(String idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}

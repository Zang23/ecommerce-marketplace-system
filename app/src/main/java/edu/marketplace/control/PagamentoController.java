package edu.marketplace.control;

// Resumo:
// Pagar com crédito
// Pagar com débito
// Pagar com pix
// Confirmar pagamento
// Obs: É tudo falso, só pra simular mesmo

public class PagamentoController {


  // Função se for cartão de crédito, valida o número e, se tiver ok, finaliza o pedido
  public String pagarComCredito(String numeroCartao, Long idPedido) {


    // Usa a função pra saber se é válido
    if (cartaoValido(numeroCartao) == false) {
        return "Cartão inválido, arrume o número";
    }


    return confirmarPagamento(idPedido);
  }

  // Função pra pagar com débito
  public String pagarComDebito(String numeroCartao, Long idPedido) {

    if (cartaoValido(numeroCartao) == false) {
        return "Cartão inválido, arrume o número";
    }

    return confirmarPagamento(idPedido);
  }


  // Função pra PIX, essa já confirma direto
  public String pagarComPix(Long idPedido) {

    return confirmarPagamento(idPedido);
  }

  // Função pra confirmar o pagamento e acabar o pedido
  public String confirmarPagamento(Long idPedido) {

    PedidoController pedido = new PedidoController();

    String resultado = pedido.finalizarPedido(idPedido);

    return resultado;
  }

  // Validação bem simples do número do cartão, não pode ser nulo e tem que ter 16 números
  private boolean cartaoValido(String numeroCartao) {

    if (numeroCartao == null) {
        return false;
    }


    if (numeroCartao.length() == 16) {
        return true;
    } else {
        return false;
    }
  }

  // Acho que é isso, vou baixar o Docker Desktop e testar, se der certo dou merge, se puderem ver e dar feedback eu agradeço
}
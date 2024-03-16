package br.com.fiap.soat.tech_challenge.fase4msproducao.entities;

public enum StatusDoPedido {
    RECEBIDO {
        @Override
        public boolean podeAtualizarPara(StatusDoPedido statusDoPedido) {
            return statusDoPedido.equals(EM_PREPARACAO) || statusDoPedido.equals(RECUSADO);
        }
    },
    EM_PREPARACAO {
        @Override
        public boolean podeAtualizarPara(StatusDoPedido statusDoPedido) {
            return statusDoPedido.equals(PRONTO);
        }
    },
    PRONTO {
        @Override
        public boolean podeAtualizarPara(StatusDoPedido statusDoPedido) {
            return statusDoPedido.equals(FINALIZADO);
        }
    },
    FINALIZADO {
        @Override
        public boolean podeAtualizarPara(StatusDoPedido statusDoPedido) {
            return false;
        }
    },
    RECUSADO {
        @Override
        public boolean podeAtualizarPara(StatusDoPedido statusDoPedido) {
            return false;
        }
    };

    public abstract boolean podeAtualizarPara(StatusDoPedido statusDoPedido);
}

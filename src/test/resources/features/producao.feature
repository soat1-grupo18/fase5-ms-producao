# language: pt

    Funcionalidade: API - Producao - Gerenciar Fila de Pedidos

        Cenário: Evoluir Pedidos
            Dado que o pedido entrou na fila
            Quando o status inicial e recebido
            Então direciono o pedido para o status de andamento

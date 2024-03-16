#!/bin/bash

awslocal sqs create-queue \
    --queue-name ms-producao-evento-pedido-recebido

awslocal sqs create-queue \
    --queue-name ms-producao-evento-pagamento-aprovado

awslocal sqs create-queue \
    --queue-name ms-producao-evento-pagamento-recusado

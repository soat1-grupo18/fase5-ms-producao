#!/bin/bash

awslocal sqs create-queue \
    --queue-name pedidoRecebido \
    --region us-east-1

awslocal sqs create-queue \
    --queue-name pagamentoAprovado \
    --region us-east-1

awslocal sqs create-queue \
    --queue-name pagamentoRecusado \
    --region us-east-1
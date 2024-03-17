data "aws_sns_topic" "ms_pedido" {
  name = "ms-pedido"
}

data "aws_sns_topic" "ms_pagamento" {
  name = "ms-pagamento"
}

resource "kubernetes_config_map" "application_config" {
  metadata {
    name      = "application-config"
    namespace = "ms-producao"
  }

  data = {
    aws_region                           = data.aws_region.current.name
    aws_sqs_endpoint                     = "https://sqs.${data.aws_region.current.name}.amazonaws.com"
    aws_sqs_queue_pedido_recebido_arn    = aws_sqs_queue.ms_producao_evento_pedido_recebido.arn
    aws_sqs_queue_pagamento_aprovado_arn = aws_sqs_queue.ms_producao_evento_pagamento_aprovado.arn
    aws_sqs_queue_pagamento_recusado_arn = aws_sqs_queue.ms_producao_evento_pagamento_recusado.arn
    spring_data_source_url               = "jdbc:postgresql://${aws_db_instance.fiap_ms_producao.endpoint}/${aws_db_instance.fiap_ms_producao.db_name}"
  }

  depends_on = [kubernetes_namespace.ms_producao]
}

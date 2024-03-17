// Bad practive. It should be an IAM Role.
resource "aws_iam_user" "ms_producao" {
  name = "ms-producao"
  path = "/fiap-store/"
}

resource "aws_iam_access_key" "ms_producao" {
  user = aws_iam_user.ms_producao.name
}

data "aws_iam_policy_document" "ms_producao" {
  statement {
    effect = "Allow"
    actions = [
      "sqs:ChangeMessageVisibility",
      "sqs:DeleteMessage",
      "sqs:GetQueueAttributes",
      "sqs:GetQueueUrl",
      "sqs:ReceiveMessage",
      "sqs:SendMessage",
    ]
    resources = [
      aws_sqs_queue.ms_producao_evento_pedido_recebido.arn,
      aws_sqs_queue.ms_producao_evento_pagamento_aprovado.arn,
      aws_sqs_queue.ms_producao_evento_pagamento_recusado.arn,
    ]
  }
}

resource "aws_iam_user_policy" "ms_producao" {
  name   = "application"
  user   = aws_iam_user.ms_producao.name
  policy = data.aws_iam_policy_document.ms_producao.json
}

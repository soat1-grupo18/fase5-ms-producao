resource "kubernetes_namespace" "ms_producao" {
  metadata {
    name = "ms-producao"
  }
}

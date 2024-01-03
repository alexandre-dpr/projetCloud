resource "local_file" "kubeconfig" {
  depends_on = [azurerm_kubernetes_cluster.cluster]
  filename   = "kubeconfig"
  content    = azurerm_kubernetes_cluster.cluster.kube_config_raw
}

output "ingress_ip" {
  description = "IP de l'ingress"
  sensitive   = true
  value       = azurerm_kubernetes_cluster.cluster.kube_config[0].host
}
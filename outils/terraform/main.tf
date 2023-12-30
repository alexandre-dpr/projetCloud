terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "=3.0.0"
    }
  }
}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "rg" {
  name     = "${var.projectName}-${var.env_name}"
  location = "France Central"
}

resource "azurerm_kubernetes_cluster" "cluster" {
  name                = "${var.cluster_name}-${var.env_name}"
  location            = azurerm_resource_group.rg.location
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = var.env_name

  default_node_pool {
    name       = "default"
    node_count = "1"
    vm_size    = "standard_d2_v2"
  }

  identity {
    type = "SystemAssigned"
  }
}

resource "null_resource" "helm_local" {
  depends_on = [azurerm_kubernetes_cluster.cluster]

  provisioner "local-exec" {
    # Récupération des credentials AKS
    command = "az aks get-credentials --resource-group ${var.projectName}-${var.env_name} --name ${var.cluster_name}-${var.env_name} --overwrite-existing"
  }

  provisioner "local-exec" {
    # Création du secret Docker pour le registre
    command = "kubectl create secret docker-registry cloud-secret --docker-server=https://ghcr.io/ --docker-username=notneeded --docker-password=${var.githubToken}"
  }

  provisioner "local-exec" {
    # Build du chart Helm local
    command = "helm dependency build ${var.helm_folder}"
  }

  provisioner "local-exec" {
    # Installation du chart Helm local
    command = "helm install projetcloud-${var.env_name} ${var.helm_folder} --set environment=${var.env_name}"
  }

  provisioner "local-exec" {
    # Ajout du repo Helm nginx-stable
    command = "helm repo add nginx-stable https://helm.nginx.com/stable"
  }

  provisioner "local-exec" {
    # Mise à jour du repo Helm
    command = "helm repo update"
  }

  provisioner "local-exec" {
    # Installation de l'Ingress nginx
    command = "helm install nginx-ingress nginx-stable/nginx-ingress --set rbac.create=true"
  }

  # Procédure de destruction
  provisioner "local-exec" {
    when    = destroy
    command = "kubectl delete secret cloud-secret"
  }
}
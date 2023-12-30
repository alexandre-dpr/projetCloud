variable "projectName" {
  description = "Project name"
  default     = "ProjetCloud"
}

variable "cluster_name" {
  description = "The name for the AKS cluster"
  default     = "AKS"
}

variable "env_name" {
  type        = string
  description = "The environment for the AKS cluster"
}

variable "githubToken" {
  type        = string
  description = "GitHub token for authentication"
}

variable "helm_folder" {
  description = "Relative path of Helm folder"
  default     = "app/"
}
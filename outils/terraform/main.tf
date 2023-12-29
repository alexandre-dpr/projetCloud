module "recette_cluster" {
  source   = "./main"
  env_name = "recette"
}

#module "prod_cluster" {
#  source   = "./main"
#  env_name = "prod"
#}
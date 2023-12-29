module "recette_cluster" {
  source      = "./main"
  env_name    = "recette"
  githubToken = null
}

module "prod_cluster" {
  source   = "./main"
  env_name = "prod"
  githubToken = null
}
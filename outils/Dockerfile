FROM ubuntu

RUN apt update && apt upgrade -y

RUN apt install sudo wget gpg lsb-core curl -y

# installation terraform
RUN wget -O- https://apt.releases.hashicorp.com/gpg | sudo gpg --dearmor -o /usr/share/keyrings/hashicorp-archive-keyring.gpg
RUN echo "deb [signed-by=/usr/share/keyrings/hashicorp-archive-keyring.gpg] https://apt.releases.hashicorp.com $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/hashicorp.list
RUN sudo apt update && sudo apt install terraform -y

# installation az cli
RUN curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash

WORKDIR /

# installation Docker
# Add Docker's official GPG key:
RUN sudo apt-get update
RUN sudo apt-get install ca-certificates curl gnupg -y
RUN sudo install -m 0755 -d /etc/apt/keyrings
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
RUN sudo chmod a+r /etc/apt/keyrings/docker.gpg

# Add the repository to Apt sources:
RUN echo \
  "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
RUN sudo apt-get update

RUN sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

# installation kubectl
# https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/
RUN sudo apt-get install -y apt-transport-https ca-certificates curl
RUN curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
RUN echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /' | sudo tee /etc/apt/sources.list.d/kubernetes.list

RUN sudo apt-get update
RUN sudo apt-get install -y kubectl

WORKDIR /temp

# Kubelogin
RUN apt install 7zip -y
RUN wget https://github.com/Azure/kubelogin/releases/download/v0.0.32/kubelogin-linux-arm64.zip
RUN 7zz e kubelogin-linux-arm64.zip
RUN mv kubelogin /usr/local/bin/kubelogin

# Installation de HELM
RUN curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
RUN chmod 700 get_helm.sh
RUN ./get_helm.sh

WORKDIR /
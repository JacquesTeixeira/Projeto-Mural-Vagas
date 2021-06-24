# Projeto-Mural-Vagas

###################### CLONANDO REPO #############################
Projeto para aula de Desenvolvimento de Sistemas II

- Com Docker, docker-compose e Git instalado na máquina, faça o clone do projeto.
git clone https://github.com/JacquesTeixeira/Projeto-Mural-Vagas.git

- entre na pasta do projeto backend
cd /Projeto-Mural-Vagas/mural_api/

- execute na pasta no mesmo nível do arquivo docker-compose.yml
compose up --build --force-recreate

####################### GIT FLOW ###################################


git flow init -d

Se tiver instalado o git no windows o git flow já vem por padrão, caso seja linux necessitará instalar

Com este comando, cria-se toda estrutura de branch padrão que o git-flow necessita, sem precisar da confirmação
OBS: Esses passos, todos envolvidos no projeto precisam executar, inclusive o git-flow
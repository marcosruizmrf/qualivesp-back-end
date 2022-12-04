![image](https://user-images.githubusercontent.com/77700262/143724421-da38f4a5-c5fe-4fd1-a75a-007144d47f91.png)

# QUALIVESP

Projeto Integrador em Computação II - 2022

📚 Projeto QUALIVESP (Relatórios e Gestão de Ferramentaria):

Polo: Diadema

Orientadora do PI: Carolina Rosa Silva de Lucena

Integrantes do grupo:
David Barbosa de Araújo;
Elizabeth Fernandes Oliveira;
Ernando Ferreira da Costa;
Hideki Oliveira Igarashi;
Lucas Rodrigues Ribeiro;
Marcos Ruiz Freire; e
Vagner Danilo David.


🟡 Sobre o problema encontrado e solução implementada

Aplicação web direcionada para auxiliar o departamento de Desenvolvimento de Produtos a melhorar a comunicação do status do andamento dos desenvolvimentos. Este software, mesmo dispondo de poucos recursos e sendo de simples operação, tem um grande potencial para atender as necessidades da empresa e produzir um relatório, em tempo real, dos status dos itens de desenvolvimento e seus requisitos.

### Back-end:

1 - Criação de projeto utilizando o framework Spring boot, o Java 11 como linguagem de desenvolvimento e a IDE: Spring Tool Suite 4;

2 - Utilização do Apache Maven como ferramenta para automatizar e padronizar a construção do projeto; e

3 - Realizado testes unitários utilizando o JUnit 5.


🟡 Utilizamos o banco de dados H2

### Database h2

1 - Conecta-se ao servidor usando um navegador. Para abrir a interface é necessário inserir a URL que foi configurada durante o desenvolvimento do projeto. “http://localhost:8080/h2-console”.

2 - Configuração no arquivo do banco de dados do Sistema:

| Database                             |  H2                                               |
| ------------------------------------ | ------------------------------------------------- |
|  spring.h2.console.enabled           |  true                                             |
|  spring.h2.console.path              |  /h2-console                                      |
|  spring.datasource.url               |  jdbc:h2:~/test;MODE=MySQL;DATABASE_TO_LOWER=TRUE |
|  spring.datasource.driver-class-name |  org.h2.Driver                                    |
|  spring.datasource.username          |  sa                                               |

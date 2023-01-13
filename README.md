# PAGAR-ME API
Projeto realizado como treinamento no Gerador de Devs.  
A API faz o controle de transações financeiras, passando as informações de compra e cartão.  

### Pré-requisitos
O que você precisa instalar para rodar o projeto?

* [Gradle](https://gradle.org/)
* [Docker](https://www.docker.com/)
* [Docker-Compose](https://docs.docker.com/compose/)
* [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [MySQL](https://www.mysql.com/)

### Como fazer a instalação da aplicação
Faça o clone do arquivo usando uma das opções abaixo:
#### SSH
```
git@github.com:mariodemartini/pagar-me.git
```  
#### HTTPS
```
https://github.com/mariodemartini/pagar-me.git
```  

### Boas práticas de versionamento de código
* Utilização de GitFlow
* Utilização de commit semantico

### Como rodar a aplicação?
Execute o comando abaixo no terminal:
```
sh docker-compose-dev.sh
```  

### Como rodar os testes unitários?
Execute o comando abaixo no terminal
```
./gradlew test
```

### Para acessar a documentação Swagger da API
```
http://localhost:8080/swagger-ui.html
```

### Tecnologias utilizadas no projeto
* [Gradle](https://gradle.org/) - De aplicativos móveis a microsserviços, de pequenas empresas a grandes empresas, a Gradle ajuda as equipes a construir, automatizar e fornecer software melhor, mais rapidamente.
* [Spring Boot Web Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web) - Starter para construção de web, incluindo aplicativos RESTful, usando o Spring MVC. Usa o Tomcat como o contêiner incorporado padrão.
* [Sprint Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - Starter para testar aplicativos Spring Boot com bibliotecas, incluindo JUnit, Hamcrest e Mockito.
* [Lombok](https://projectlombok.org/) - O Projeto Lombok é uma biblioteca java que se conecta automaticamente ao seu editor e cria ferramentas, apimentando seu java. Nunca escreva outro método getter ou equals novamente, com uma anotação sua classe tem um construtor com todos os recursos, Automatize suas variáveis ​​de registro e muito mais.
* [Model Mapper](https://modelmapper.org/) - Os aplicativos geralmente consistem em modelos de objetos semelhantes, mas diferentes, em que os dados em dois modelos podem ser semelhantes, mas a estrutura e as preocupações dos modelos são diferentes. O mapeamento de objetos facilita a conversão de um modelo em outro, permitindo que modelos separados permaneçam segregados.
* [Swagger](https://swagger.io/) - Simplifique o desenvolvimento de API para usuários, equipes e empresas com o conjunto de ferramentas open source e profissional Swagger.
* [Power Mock](https://powermock.github.io/) -  O PowerMock é uma estrutura que estende outras bibliotecas simuladas, como o EasyMock, com recursos mais poderosos. O PowerMock usa um carregador de classes personalizado e manipulação de bytecode para permitir a simulação de métodos estáticos, construtores, classes e métodos finais, métodos particulares, remoção de inicializadores estáticos e muito mais.
* [SonarQube](https://www.sonarsource.com/products/sonarqube/) - Desenvolvido pela SonarSource, o SonarQube é uma ferramenta de análise de código que auxilia na detecção de problemas que podem ocorrer no código, sejam eles problemas com code smells, segurança ou bugs.

# 🚀 SkillBridge - Plataforma de Requalificação Profissional com IA

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen?style=for-the-badge&logo=springboot)
![Oracle](https://img.shields.io/badge/Oracle-Database-red?style=for-the-badge&logo=oracle)
![Spring AI](https://img.shields.io/badge/Spring%20AI-1.0.0--M4-blue?style=for-the-badge)

**Uma solução inovadora para requalificação profissional na era da IA e economia verde**

[Características](#-características) • [Tecnologias](#-tecnologias) • [Instalação](#-instalação) • [Documentação](#-documentação) • [API](#-api)

</div>

## 📋 Sobre o Projeto

O **SkillBridge** é uma plataforma completa desenvolvida para revolucionar o futuro do trabalho, combinando tecnologia de ponta com foco nas relações humanas. A plataforma utiliza IA generativa para fornecer recomendações personalizadas de carreira, identificar lacunas de habilidades e sugerir caminhos de requalificação na economia verde e digital.

### 🎯 Objetivos

- 🔗 **Conectar** profissionais a oportunidades na nova economia
- 🎓 **Qualificar** e requalificar para as profissões do futuro  
- 🤖 **Utilizar IA** como parceira no desenvolvimento humano
- 🌱 **Promover** sustentabilidade e inclusão produtiva
- 🏢 **Preparar** organizações para os novos tempos

## ✨ Características

### 🧠 Inteligência Artificial
- **Recomendações de Carreira Personalizadas** usando OpenAI
- **Análise de Lacunas de Habilidades** com IA generativa
- **Sugestões de Treinamentos** baseadas em metas profissionais
- **Análise de Tendências** do mercado de trabalho

### 👥 Gestão de Usuários
- **Sistema de Roles** (Admin, User, Mentor, Career Advisor)
- **Autenticação JWT** segura
- **Perfis completos** com histórico profissional
- **Avaliações de habilidades** com tracking de progresso

### 📊 Analytics e Insights
- **Dashboard de Habilidades** com métricas detalhadas
- **Progresso de Carreira** visual e intuitivo
- **Relatórios de Lacunas** e oportunidades
- **Análise de Mercado** em tempo real

### 🛠️ Funcionalidades Técnicas
- **API RESTful** completa com documentação Swagger
- **Cache inteligente** para melhor performance
- **Mensageria assíncrona** com RabbitMQ
- **Internacionalização** (Português/Inglês)
- **Paginação e ordenação** em todos os endpoints
- **Validação robusta** de dados
- **Tratamento de erros** global

## 🛠 Tecnologias

### Backend
- **Java 21** - Linguagem principal
- **Spring Boot 3.3.0** - Framework core
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Spring AI** - Integração com OpenAI
- **Spring AMQP** - Mensageria com RabbitMQ

### Banco de Dados
- **Oracle Database** - Banco de dados principal
- **H2 Database** - Para testes e desenvolvimento

### Segurança
- **JWT** - Tokens de autenticação
- **BCrypt** - Hash de senhas
- **CORS** - Configuração de origens

### Ferramentas e Bibliotecas
- **Lombok** - Redução de boilerplate
- **ModelMapper** - Mapeamento DTO/Entity
- **SpringDoc OpenAPI** - Documentação da API
- **Hibernate Types** - Suporte a JSON no Oracle
- **Caffeine** - Cache em memória

## 📦 Estrutura do Projeto
skillbridge-platform/
├── src/main/java/com/skillbridge/
│ ├── config/ # 🔧 Configurações Spring
│ ├── controller/ # 🌐 Controladores REST
│ ├── dto/ # 📦 Data Transfer Objects
│ │ ├── request/ # 📥 DTOs de entrada
│ │ └── response/ # 📤 DTOs de saída
│ ├── model/ # 🗃️ Entidades JPA e Enums
│ ├── repository/ # 💾 Interfaces de persistência
│ ├── service/ # 💼 Lógica de negócio
│ │ └── impl/ # 🔨 Implementações
│ ├── security/ # 🔐 Configurações de segurança
│ ├── exception/ # ⚠️ Tratamento de exceções
│ ├── ai/ # 🤖 Integração com IA
│ ├── messaging/ # 📨 Mensageria assíncrona
│ └── util/ # 🛠️ Utilitários
├── src/main/resources/
│ ├── application.properties # ⚙️ Configuração principal
│ ├── application-dev.properties # 🏗️ Config desenvolvimento
│ ├── application-prod.properties # 🚀 Config produção
│ └── messages.properties # 🌐 Internacionalização


## 🚀 Instalação

### Pré-requisitos

- **Java 21** ou superior
- **Oracle Database** 11g ou superior
- **Maven** 3.6 ou superior
- **RabbitMQ** (opcional para desenvolvimento)

### 1. Clone o Repositório
bash
git clone https://github.com/EnricoGaldino/skillbridge-platform.git
cd skillbridge-platform


### 2. Configuração do Banco de Dados
-- Conecte-se como SYS ou SYSTEM e execute:
CREATE USER skillbridge IDENTIFIED BY skillbridge123;
GRANT CONNECT, RESOURCE TO skillbridge;
GRANT UNLIMITED TABLESPACE TO skillbridge;

-- Execute o script do schema:
@sql/schema-oracle.sql

### 3. Instalação e Execução
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Executar a aplicação
mvn spring-boot:run

# Ou criar o JAR e executar
mvn clean package
java -jar target/skillbridge-platform-1.0.0.jar

5. Acesse a Aplicação
API: http://localhost:8080/skillbridge
Swagger UI: http://localhost:8080/skillbridge/swagger-ui.html
Health Check: http://localhost:8080/skillbridge/actuator/health

📚 Documentação
🔗 API Documentation
A documentação completa da API está disponível via Swagger UI:

text
http://localhost:8080/skillbridge/swagger-ui.html

📖 Endpoints Principais
Autenticação
Método	Endpoint	Descrição
POST	/api/auth/register	Registrar novo usuário
POST	/api/auth/login	Login e obter token JWT
Usuários
Método	Endpoint	Descrição
GET	/api/users	Listar usuários (com paginação)
GET	/api/users/{id}	Buscar usuário por ID
POST	/api/users	Criar usuário
PUT	/api/users/{id}	Atualizar usuário
DELETE	/api/users/{id}	Excluir usuário
Carreira e IA

Método	Endpoint	Descrição
POST	/api/career/advice/user/{userId}	Gerar recomendação de carreira
POST	/api/career/advice	Recomendação baseada em skills

Método	Endpoint	Descrição
GET	/api/assessments/user/{userId}	Avaliações do usuário
POST	/api/assessments/user/{userId}	Criar avaliação
Treinamentos
Método	Endpoint	Descrição
GET	/api/trainings	Listar treinamentos
POST	/api/trainings	Criar treinamento

🗃️ Modelo de Dados
Entidades Principais:
User: Usuários do sistema com roles específicas
SkillAssessment: Avaliações de habilidades dos usuários
CareerPath: Recomendações de carreira geradas por IA
Training: Catálogo de treinamentos disponíveis

🤝 Contribuição
Contribuições são bem-vindas! Por favor:

Fork o projeto

Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

Commit suas mudanças (git commit -m 'Add some AmazingFeature')

Push para a branch (git push origin feature/AmazingFeature)

Abra um Pull Request

📋 Checklist para Contribuição
✅ Código segue o estilo do projeto
✅ Testes passando
✅ Documentação atualizada
✅ Commits seguem o padrão convencional

👥 Autor
🎓 Enrico do Nascimento Ferreira Galdino - RM552082

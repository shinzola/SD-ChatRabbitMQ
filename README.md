# SdChat: Sistema de Chat com RabbitMQ e Docker

Um sistema de mensagens simples, porém robusto, construído em **Java**, que utiliza o **RabbitMQ** como *message broker* para comunicação assíncrona. A aplicação é totalmente **containerizada com Docker** e orquestrada com **Docker Compose**, permitindo um ambiente de desenvolvimento e execução padronizado e isolado.

---

## 📑 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Como Executar](#como-executar)
  - [Passo 1: Clonar o Repositório](#passo-1-clonar-o-repositório)
  - [Passo 2: Construir a Aplicação Java](#passo-2-construir-a-aplicação-java)
  - [Passo 3: Iniciar os Serviços com Docker Compose](#passo-3-iniciar-os-serviços-com-docker-compose)
  - [Passo 4: Interagir com o Producer](#passo-4-interagir-com-o-producer)
  - [Passo 5: Observar o Consumer](#passo-5-observar-o-consumer)
  - [Passo 6: Parar o Sistema](#passo-6-parar-o-sistema)
- [🔍 Monitorando o RabbitMQ](#monitorando-o-rabbitmq)
- [📁 Estrutura do Projeto](#estrutura-do-projeto)

---

## 💬 Sobre o Projeto

Este projeto demonstra a comunicação entre dois serviços Java independentes (*producer* e *consumer*) através de uma fila de mensagens gerenciada pelo **RabbitMQ**.

- **Producer**: Uma aplicação de console interativa que envia mensagens digitadas pelo usuário para uma fila no RabbitMQ.
- **Consumer**: Uma aplicação que escuta continuamente a fila e imprime no console as mensagens recebidas.
- **RabbitMQ**: Atua como intermediário confiável entre os dois serviços.

### Arquitetura:

```
+----------+      +------------------+      +-----------+
|          |      |                  |      |           |
| Producer |----->|   RabbitMQ       |----->| Consumer  |
|          |      | (Fila: chat_fila) |     |           |
+----------+      +------------------+      +-----------+
```

---

## ✅ Pré-requisitos

- [Docker](https://www.docker.com/) e Docker Compose
- [JDK 21+](https://adoptium.net/)
- [Apache Maven](https://maven.apache.org/) (caso compile localmente)
- [Git](https://git-scm.com/)

---

## ▶️ Como Executar

### Passo 1: Clonar o Repositório

```bash
git clone https://github.com/shinzola/SD-ChatRabbitMQ
cd SdChat
```

### Passo 2: Construir a Aplicação Java

#### Via Terminal:

```bash
mvn clean package
```

#### Via IntelliJ IDEA:

1. Abra a aba **Maven**.
2. Vá até o projeto **SdChat [chat.rabbitmq]**.
3. Acesse **Lifecycle** → clique duas vezes em `clean` e depois em `package`.
4. Aguarde pela mensagem `BUILD SUCCESS`.

### Passo 3: Iniciar os Serviços com Docker Compose

```bash
docker-compose up --build -d
```

Esse comando irá:

- Construir as imagens Docker do `producer` e `consumer`
- Baixar e iniciar a imagem oficial do RabbitMQ
- Executar os contêineres em segundo plano

### Passo 4: Interagir com o Producer

1. Descubra o nome do contêiner:

```bash
docker ps
```

2. Conecte-se ao terminal do contêiner `sdchat-producer-1`:

```bash
docker attach sdchat-producer-1
```

3. Você verá a mensagem:

```
Produtor iniciado. Digite mensagens ('sair' para encerrar):
```

4. Digite suas mensagens e pressione Enter.

> Para sair do terminal sem parar o contêiner:  
> **Ctrl + P**, depois **Ctrl + Q**

### Passo 5: Observar o Consumer

Veja as mensagens recebidas pelo consumer em tempo real:

```bash
docker-compose logs -f consumer
```

Para sair dos logs: `Ctrl + C`.

### Passo 6: Parar o Sistema

```bash
docker-compose down
```

Esse comando encerra os contêineres e remove a rede criada.

---

## 🔍 Monitorando o RabbitMQ

Acesse o painel web do RabbitMQ:

- **URL**: [http://localhost:15672](http://localhost:15672)
- **Usuário**: `guest`
- **Senha**: `guest`

---

## 📁 Estrutura do Projeto

```
.
├── consumer/                 # Módulo Maven para o consumidor
│   ├── src/main/java/...     
│   ├── Dockerfile            # Dockerfile do consumer
│   └── pom.xml
├── producer/                 # Módulo Maven para o produtor
│   ├── src/main/java/...     
│   ├── Dockerfile            # Dockerfile do producer
│   └── pom.xml
├── docker-compose.yml        # Orquestra os serviços com Docker
├── pom.xml                   # POM pai do projeto (multi-módulo)
└── README.md                 # Este arquivo
```

---

Feito por Marcos Dipardi, Pedro Coelho e Rodrigo Noelli

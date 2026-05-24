# Como executar o projeto

## Pré-requisitos

- Java 21
- Docker
- Docker Compose

---

## Clonando o repositório

```bash
git clone https://github.com/Zang23/ecommerce-marketplace-system.git
```

Entre na pasta do projeto:

```bash
cd ecommerce-marketplace-system
```

---

## Subindo o banco de dados

Execute:

```bash
docker compose up -d
```

Isso irá:

- baixar a imagem do SQL Server
- criar o container do banco
- expor a porta `1433`

---

## Executando o script SQL inicial

Após subir o container, execute:

```bash
docker exec -it sqlserver_bd /opt/mssql-tools18/bin/sqlcmd -S localhost -U sa -P "123Elkt!" -C -i /scripts/init.sql
```

Isso irá:

- criar o banco `MarketPlace`
- criar as tabelas iniciais

---

## Executando a aplicação

Abra o projeto na IDE e execute a aplicação JavaFX normalmente.

---

## Credenciais do banco

| Campo | Valor |
|---|---|
| Banco | MarketPlace |
| Usuário | sa |
| Senha | 123Elkt! |
| Porta | 1433 |

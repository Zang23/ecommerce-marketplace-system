IF DB_ID('MarketPlace') IS NULL
BEGIN
    CREATE DATABASE MarketPlace;
END
GO

USE MarketPlace;
GO

IF OBJECT_ID('comprador', 'U') IS NULL
BEGIN
    CREATE TABLE comprador (
        id INT PRIMARY KEY IDENTITY(1,1),

        senha VARCHAR(35) NOT NULL,
        email VARCHAR(100) NOT NULL,
        nome VARCHAR(50) NOT NULL,
        telefone VARCHAR(11) NOT NULL,

        endereco_logradouro VARCHAR(100) NOT NULL,
        endereco_numero INT NOT NULL,
        endereco_cep VARCHAR(8) NOT NULL,
        endereco_cidade VARCHAR(40) NOT NULL
    );
END
GO

IF OBJECT_ID('vendedor', 'U') IS NULL
BEGIN
    CREATE TABLE vendedor (
        id_comprador INT PRIMARY KEY,

        CONSTRAINT fk_vendedor_comprador
            FOREIGN KEY (id_comprador)
            REFERENCES comprador(id)
            ON DELETE CASCADE
    );
END
GO

IF OBJECT_ID('produto', 'U') IS NULL
BEGIN
    CREATE TABLE produto (
        codigo INT PRIMARY KEY IDENTITY(1,1),

        id_vendedor INT NOT NULL,

        valor_unitario DECIMAL(10,2) NOT NULL,
        categoria VARCHAR(35) NOT NULL,
        qtd_estoque INT NOT NULL,
        desconto DECIMAL(5,2),
        descricao VARCHAR(100) NOT NULL,

        CONSTRAINT fk_produto_vendedor
            FOREIGN KEY (id_vendedor)
            REFERENCES vendedor(id_comprador)
    );
END
GO

IF OBJECT_ID('pedido', 'U') IS NULL
BEGIN
    CREATE TABLE pedido (
        id INT PRIMARY KEY IDENTITY(1,1),

        id_comprador INT NOT NULL,

        valor_total DECIMAL(10,2) NOT NULL,
        data_finalizacao DATE NOT NULL,
        status VARCHAR(15) NOT NULL,

        CONSTRAINT fk_pedido_comprador
            FOREIGN KEY (id_comprador)
            REFERENCES comprador(id)
            ON DELETE CASCADE
    );
END
GO

IF OBJECT_ID('produto_carrinho', 'U') IS NULL
BEGIN
    CREATE TABLE produto_carrinho (
        cod_produto INT NOT NULL,
        id_pedido INT NOT NULL,

        qtd_carrinho INT NOT NULL,

        PRIMARY KEY (cod_produto, id_pedido),

        CONSTRAINT fk_pc_produto
            FOREIGN KEY (cod_produto)
            REFERENCES produto(codigo),

        CONSTRAINT fk_pc_pedido
            FOREIGN KEY (id_pedido)
            REFERENCES pedido(id)
    );
END
GO

IF OBJECT_ID('usuario_cartao', 'U') IS NULL
BEGIN
    CREATE TABLE usuario_cartao (
        numero_cartao VARCHAR(16) PRIMARY KEY,

        id_comprador INT NOT NULL,

        validade DATE NOT NULL,
        cvv CHAR(3) NOT NULL,
        nome_titular VARCHAR(50) NOT NULL,

        CONSTRAINT fk_cartao_comprador
            FOREIGN KEY (id_comprador)
            REFERENCES comprador(id)
            ON DELETE CASCADE
    );
END
GO
